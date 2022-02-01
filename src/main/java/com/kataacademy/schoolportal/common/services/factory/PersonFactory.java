package com.kataacademy.schoolportal.common.services.factory;

import com.kataacademy.schoolportal.common.models.persons.Person;

public interface PersonFactory {
    Person savePersonByPersonRole (String roleName, Person person);
}
