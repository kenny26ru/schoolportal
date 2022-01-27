package com.kataacademy.schoolportal.common.repository.persons;

import com.kataacademy.schoolportal.common.models.persons.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
    Optional<Director> findDirectorByFirstName(String firstName);
}
