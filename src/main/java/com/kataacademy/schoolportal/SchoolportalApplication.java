package com.kataacademy.schoolportal;

import com.kataacademy.schoolportal.common.generator.persons.PupilGenerator;
import com.kataacademy.schoolportal.common.generator.persons.TeacherGenerator;
import com.kataacademy.schoolportal.common.models.enums.Grade;
import com.kataacademy.schoolportal.common.models.persons.Pupil;
import com.kataacademy.schoolportal.common.models.persons.Teacher;
import com.kataacademy.schoolportal.common.models.schoolatribute.Form;
import com.kataacademy.schoolportal.common.models.schoolatribute.School;
import com.kataacademy.schoolportal.common.services.persons.PupilService;
import com.kataacademy.schoolportal.common.services.persons.TeacherService;
import com.kataacademy.schoolportal.common.services.schooattribute.FormService;
import com.kataacademy.schoolportal.common.services.schooattribute.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SchoolportalApplication {

	private final FormService formService;
	private final PupilService pupilService;
	private final TeacherService teacherService;
	private final SchoolService schoolService;
	private final PupilGenerator pupilGenerator;
	private final TeacherGenerator teacherGenerator;

	@Autowired
	public SchoolportalApplication(FormService formService, PupilService pupilService, TeacherService teacherService, SchoolService schoolService, PupilGenerator pupilGenerator, TeacherGenerator teacherGenerator) {
		this.formService = formService;
		this.pupilService = pupilService;
		this.teacherService = teacherService;
		this.schoolService = schoolService;
		this.pupilGenerator = pupilGenerator;
		this.teacherGenerator = teacherGenerator;
	}

	@PostConstruct
	public void myinit() {

		// Создаем класс
		Form formForPupil1 = new Form();
		formForPupil1.setNumber((byte) 6);
		formForPupil1.setName("Б");
		formForPupil1.setFormName("6Б");
		formService.save(formForPupil1);

		// Создаем ученика
		LocalDate birthDay = LocalDate.of(2015, 10, 8);
		Pupil firstPupil = pupilGenerator.generatePupilByGrade(Grade.ALL);
		firstPupil.setForm(formForPupil1);
		pupilService.savePupil(firstPupil);

		// Создаем школу
		School school1 = new School();
		schoolService.save(school1);

		// Создаем учителей и помещаем их в школу
		LocalDate birthDay2 = LocalDate.of(1990, 2, 3);
		LocalDate birthDay3 = LocalDate.of(1990, 2, 3);
		LocalDate birthDay4 = LocalDate.of(1990, 2, 3);
//		Teacher teacher1 = teacherGenerator.teacherGenerator();
//		Teacher teacher2 = teacherGenerator.teacherGenerator();
//		Teacher teacher3 = teacherGenerator.teacherGenerator();
		Teacher teacher1 = new Teacher("Marina", "Ivanova", "Sergeevna", "Ж", birthDay2);
		Teacher teacher2 = new Teacher("Marina", "Petrova", "Sergeevna", "Ж", birthDay3);
		Teacher teacher3 = new Teacher("Marina", "Sidorova", "Sergeevna", "Ж", birthDay4);
		teacher1.setSchool(school1);
		teacher2.setSchool(school1);
		teacher3.setSchool(school1);
		teacherService.saveTeacher(teacher1);
		teacherService.saveTeacher(teacher2);
		teacherService.saveTeacher(teacher3);

		// Помещаем учителей в List
		List<Teacher> teacherList = new ArrayList<>();
		teacherList.add(teacher1);
		teacherList.add(teacher2);
		teacherList.add(teacher3);

//		// Создаем школу и помещаем в неё учителей
//		School school1 = new School();
//		school1.setTeacherList(teacherList);
//		schoolService.save(school1);
	}

	public static void main(String[] args) {
		SpringApplication.run(SchoolportalApplication.class, args);
	}
}
