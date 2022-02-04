package com.kataacademy.schoolportal.special.impl;


import com.kataacademy.schoolportal.common.models.persons.Pupil;
import com.kataacademy.schoolportal.common.models.schoolatribute.Form;
import com.kataacademy.schoolportal.common.queue.PupilQueue;
import com.kataacademy.schoolportal.common.services.persons.impl.PupilServiceImpl;
import com.kataacademy.schoolportal.common.services.schooattribute.impl.FormServiceImpl;
import com.kataacademy.schoolportal.special.QueueService;
import com.kataacademy.schoolportal.special.exception.FullClassException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueServiceImpl implements QueueService {

    private final PupilServiceImpl pupilService;
    private final FormServiceImpl formService;
    private final PupilQueue queue;

    public QueueServiceImpl(PupilServiceImpl pupilService, FormServiceImpl formService, PupilQueue queue) {
        this.formService = formService;
        this.pupilService = pupilService;
        this.queue = queue;
    }


    @Override
    public Pupil addPupilIntoForm() {
        Pupil pupil;

        pupil = queue.getPupilFromQueue();

        byte classNumber = pupil.getForm().getNumber();
        int valid = 0;
        List<Form> forms = formService.getFormsByNumber(classNumber);

        for (Form form : forms) {
            if (form.getPupilSet().size() <= 30) {
                pupil.setForm(form);
                pupilService.savePupil(pupil);
                formService.edit(form);
                valid++;
                break;
            }
        }
        if (valid == 0) {
            throw new FullClassException(classNumber);
        }
        return pupil;
    }
}
