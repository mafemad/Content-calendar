package com.mafemad.contentcalendar.controller;

import com.mafemad.contentcalendar.model.Content;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.mafemad.contentcalendar.repository.ContentCollectionRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
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
    public void create(@RequestBody  Content content){
        repository.save(content);
    }



}
