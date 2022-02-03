package com.kataacademy.schoolportal.common.mappers;

import com.kataacademy.schoolportal.common.dto.DirectorDTO;
import com.kataacademy.schoolportal.common.models.persons.Director;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface DirectorMapper {
    DirectorDTO directorToDirectorDTO(Director director);

    Director directorDtoToDirector(DirectorDTO directorDTO);
}
