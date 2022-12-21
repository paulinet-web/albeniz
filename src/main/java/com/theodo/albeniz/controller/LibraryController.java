package com.theodo.albeniz.controller;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.theodo.albeniz.model.Tune;
import com.theodo.albeniz.services.LibraryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("music")
    public Collection<Tune> getMusic(@RequestParam(required = false) String query) {
        return libraryService.getAll(query);
    }


    @GetMapping("music/{id}")
    public Tune getOneMusic(@PathVariable("id") int tuneID) {
        return libraryService.getOne(tuneID);
    }

    @PostMapping("music")
    void add(@Valid @RequestBody Tune tune) {
        libraryService.addTune(tune);
    }
}


