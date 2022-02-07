package com.kataacademy.schoolportal.common.dto;

import com.kataacademy.schoolportal.common.models.schoolatribute.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDto> findAllTeachersBySchool(School school) {
        return employeeRepository.findAllTeachersBySchool(school).stream()
                .map(EmployeeMappingUtils::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

//    //для одиночного продукта обошлись проще
//    public EmployeeDto findById(Integer id) {
//        return mappingUtils.mapToProductDto( //в метод mapToProductDto
//                productRepository.findById(id) //поместили результат поиска по id
//                        .orElse(new ProductEntity()) //если ни чего не нашли, то вернем пустой entity
//        );
//    }
}
