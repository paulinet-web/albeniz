package com.theodo.albeniz.controller;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<Tune> getOneMusic(@PathVariable("id") int tuneID) {
        Tune tuneSelected = libraryService.getOne(tuneID);
        if (tuneSelected == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tuneSelected, HttpStatus.OK);
    }

    @PostMapping("music")
    public ResponseEntity<Tune> add(@Valid @RequestBody Tune tune) {
        boolean created = libraryService.addTune(tune);
        if (created){
            return new ResponseEntity<>(tune, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @DeleteMapping("music/{id}")
    public ResponseEntity remove(@PathVariable("id") int tuneID){
        boolean removed = libraryService.remove(tuneID);
        if (removed){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("music/{id}")
    public ResponseEntity<Tune> modifyTune(@PathVariable("id") int tuneID, @Valid @RequestBody Tune tune){
        boolean modified = libraryService.modifyTune(tuneID, tune);
        if (modified){
            return new ResponseEntity<>(tune, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


