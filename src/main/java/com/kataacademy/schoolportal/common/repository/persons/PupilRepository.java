package com.kataacademy.schoolportal.common.repository.persons;

import com.kataacademy.schoolportal.common.models.persons.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PupilRepository extends JpaRepository<Pupil, Integer> {

}
