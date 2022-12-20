package com.theodo.albeniz.services;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.theodo.albeniz.model.Tune;

@Service
public class LibraryService {
    

    public final static Map<Integer, Tune> LIBRARY = new HashMap<>();
        
    static {
            LIBRARY.put(1, new Tune(1, "You belong with me", "Taylor Swift"));
            LIBRARY.put(2, new Tune(2, "Girls just want to have fun", "Cyndi Lauper"));
            LIBRARY.put(3, new Tune(3, "Why not me?", "The Judds"));
        }


        public Collection<Tune> getAll(String query) {
        return LIBRARY
                .values()  // ne prendre que les values et pas les keys de la map
                .stream()
                .sorted(Comparator.comparing(Tune::getId))    // sort unsorted stream by IDs
                .filter(tune -> query == null || tune.getTitle().toUpperCase().contains(query.toUpperCase()))   // if query == null, ne filtre pas --> prend tous les tunes
                .collect(Collectors.toList());
        }

        public Tune getOne(int id) {
            return LIBRARY.get(id);
        }
        
}
