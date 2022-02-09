package com.kataacademy.schoolportal.common.services.persons.impl;

import com.kataacademy.schoolportal.common.models.persons.Director;
import com.kataacademy.schoolportal.common.repository.persons.DirectorRepository;
import com.kataacademy.schoolportal.common.services.persons.DirectorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;

    public DirectorServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    @Override
    public Director getDirectorById(Long id) {
        return directorRepository.findById(id).orElse(null);
    }

    @Override
    public Director saveDirector(Director director) {
        return directorRepository.save(director);
    }

    @Override
    public Director editDirector(Director director) {
        return directorRepository.save(director);
    }

    @Override
    public void deleteDirectorById(Long id) {
        directorRepository.deleteById(id);
    }

//    // DirectorDTO (get Teachers)
//    @Override
//    public DirectorDTO getTeachersByDirectorIdFrom5Grade(Long id) {
//        DirectorDTO directorDTO = new DirectorDTO();
//        Set<Teacher> teacherSetForCurrentDirector = getDirectorById(id).getTeacherSet();
//        Set<Teacher> teacherSetFor5Grade = new HashSet<>();
//
//        for (Teacher teacher : teacherSetForCurrentDirector) {
//            Grade grade = teacher.getGrade();
//            if (!grade.equals(Grade.ONE) && !grade.equals(Grade.TWO) && !grade.equals(Grade.THREE) && !grade.equals(Grade.FOUR)) {
//                teacherSetFor5Grade.add(teacher);
//            }
//            directorDTO.setTeacherSet(teacherSetFor5Grade);
//        }
//        return directorDTO;
//    }
}
