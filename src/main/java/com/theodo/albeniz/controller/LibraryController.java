package com.theodo.albeniz.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.theodo.albeniz.model.Tune;

@RestController
@RequestMapping("library")
public class LibraryController {

 
    private final static Map<Integer, Tune> LIBRARY = new HashMap<>();
        
    static {
            LIBRARY.put(1, new Tune(1, "You belong with me", "Taylor Swift"));
            LIBRARY.put(2, new Tune(2, "Girls just want to have fun", "Cyndi Lauper"));
            LIBRARY.put(3, new Tune(3, "Why not me?", "The Judds"));
        }


    @GetMapping("music")
    public Collection<Tune> getMusic(@RequestParam(required = false) String query) {
        return LIBRARY
                .values()  // ne prendre que les values et pas les keys de la map
                .stream()
                .sorted(Comparator.comparing(Tune::getId))    // sort unsorted stream by IDs
                .filter(tune -> query == null || tune.getTitle().toUpperCase().contains(query.toUpperCase()))   // if query == null, ne filtre pas --> prend tous les tunes
                .collect(Collectors.toList());
    }


    @GetMapping("music/{id}")
    public Tune getOneMusic(@PathVariable("id") int tuneID) {
        return LIBRARY.get(tuneID);
    }
}


