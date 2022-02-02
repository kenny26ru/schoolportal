package com.kataacademy.schoolportal.common.repository.persons;

import com.kataacademy.schoolportal.common.models.persons.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
}
