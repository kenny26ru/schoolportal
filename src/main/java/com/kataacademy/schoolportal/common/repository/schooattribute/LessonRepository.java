package com.kataacademy.schoolportal.common.repository.schooattribute;

import com.kataacademy.schoolportal.common.models.schoolatribute.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
