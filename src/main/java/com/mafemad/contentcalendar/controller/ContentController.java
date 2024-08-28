package com.mafemad.contentcalendar.controller;

import com.mafemad.contentcalendar.model.Content;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.mafemad.contentcalendar.repository.ContentCollectionRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {

    private final ContentCollectionRepository repository;

    public ContentController(ContentCollectionRepository repository) {
        this.repository = repository;
    }

    // request find all
    @GetMapping("")
    public List<Content> findAll(){
        return repository.findAll();
    }

    // request by id
    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id){
        return repository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "content not found") );
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody  Content content){
        repository.save(content);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable  Integer id){
        if(repository.existsByID(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  "Content not found");
        }
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        if(repository.existsByID(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  "Content not found");
        }
        repository.delete(id);
    }

}
