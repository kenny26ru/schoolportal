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

        pupil = queue.getPupilFromQueue(); // получаем ученика

        byte classNumber = pupil.getForm().getNumber(); // получаем намер класса из сохраненной в нем Form затычке, которая создается в методе доставания из очереди
        int valid = 0; // переменная для валидации добавления ученика в класс
        List<Form> forms = formService.getFormsByNumber(classNumber); // поем лист Form из бд по номеру класса

        for (Form form : forms) { // перебираем все формы из листа
            if (form.getPupilSet().size() <= 30) { // ищем Form в которой еще есть место
                pupil.setForm(form); // такого добаления хватит, чтобы в Form тоже добавлся этот ученик
                pupilService.savePupil(pupil); // вот на этом моменте? (тут добавляем ученика в бд уже с нормальной формой)
                formService.edit(form); // меняем форму в бд
                valid++; // увиличиваем значение валидационной переменной
                break; // выходим из цикла for так как ученик уже добавлен
            }
        }
        if (valid == 0) { // если valid 0, значит ученик не добавился и надо выбросить ошибку, что все классы заполнены
            throw new FullClassException(classNumber);
        }
        return pupil; // метод возвращает добавленного ученика
    }
}
