package com.kataacademy.schoolportal.common.repository.schooattribute;

import com.kataacademy.schoolportal.common.models.schoolatribute.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormRepository extends JpaRepository<Form, Long> {
    List<Form> findAllByNumber(Byte number);
}
