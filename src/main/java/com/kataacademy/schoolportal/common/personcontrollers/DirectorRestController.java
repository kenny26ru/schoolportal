package com.kataacademy.schoolportal.common.personcontrollers;

import com.kataacademy.schoolportal.common.models.persons.Director;
import com.kataacademy.schoolportal.common.personcontrollers.exception.BadRequestException;
import com.kataacademy.schoolportal.common.personcontrollers.exception.PersonNotFoundException;
import com.kataacademy.schoolportal.common.services.persons.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/directors")
public class DirectorRestController {

    @Autowired
    private DirectorService directorService;

    /**
     * Возвращает список всех директоров
     * @return
     */
    @GetMapping("")
    public ResponseEntity<List<Director>> getAllDirectors(
            @RequestParam(name = "page", required = false, defaultValue = "-1") String pageString,
            @RequestParam(name = "size", required = false, defaultValue = "-1") String sizeString,
            @RequestParam(name = "sort", required = false, defaultValue = "-1") String sortString
    ) {
        try {
            int page = Integer.parseInt(pageString);
            int size = Integer.parseInt(sizeString);
            int sort = Integer.parseInt(sortString);
        } catch (NumberFormatException e) {
            throw new BadRequestException("выдать сообщение о неправильном формате запроса");
        }
        List<Director> directors = directorService.getAllDirectors();
        return ResponseEntity.ok().body(directors);
    }

    /**
     * Возвращает директора с заданым идентификатором
     * или бросает исключение PersonNotFoundException
     * @param id идентификатор директора
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Director> getOneDirector(@PathVariable Long id) throws PersonNotFoundException {
        Director director = directorService.getDirectorById(id);
        if(director == null) {
            throw new PersonNotFoundException("ERROR. Director with id=" + id + " not found");
        }
        return ResponseEntity.ok().body(director);
    }

    /**
     * Создает нового директора
     * @param director
     * @return
     */
    @PostMapping("")
    public ResponseEntity<Director> createDirector(@RequestBody @Valid Director director) {
        directorService.saveDirector(director);
        return ResponseEntity.status(HttpStatus.CREATED).body(director);
    }

    /**
     * Изменяет параметры персоны
     * @param director персона с новыми значениями
     * @return персона с новыми значениями
     * @throws PersonNotFoundException персона не существует в БД
     */
    @PutMapping("")
    public ResponseEntity<Director> updateDirector(@RequestBody Director director) throws PersonNotFoundException {
        Long id = director.getId();
        Director oldDirector = directorService.getDirectorById(id);
        if(oldDirector == null) {
            throw new PersonNotFoundException("ERROR. Director with id=" + id + " not found");
        }
        directorService.editDirector(director);
        return ResponseEntity.ok().body(director);
    }

    /**
     * Удаляет персону с заданым id
     * @param id идентификатор персоны
     * @return персона со старыми значениями
     * @throws PersonNotFoundException персона не существует в БД
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDirector(@PathVariable Long id) throws PersonNotFoundException {
        Director director = directorService.getDirectorById(id);
        if(director == null) {
            throw new PersonNotFoundException("ERROR. Director with id=" + id + " not found");
        }
        directorService.deleteDirectorById(id);
        return ResponseEntity.ok().body("OK. Director with id=" + id + " was deleted");
    }

}
