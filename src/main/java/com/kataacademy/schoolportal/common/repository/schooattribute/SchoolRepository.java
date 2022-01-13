package com.kataacademy.schoolportal.common.repository.schooattribute;

import com.kataacademy.schoolportal.common.models.schoolatribute.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

}
