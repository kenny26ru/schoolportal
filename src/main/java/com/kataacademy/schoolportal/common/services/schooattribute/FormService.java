package com.kataacademy.schoolportal.common.services.schooattribute;

import com.kataacademy.schoolportal.common.models.schoolatribute.Form;

import java.util.List;

public interface FormService {
    List<Form> getAllForm();
    List<Form> getFormsByNumber(Byte number);
    Form getFormById(Long id);
    void save(Form form);
    void edit(Form form);
    void deleteFormById(Long id);
}
