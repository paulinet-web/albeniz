package com.theodo.albeniz.services;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.theodo.albeniz.configs.LibraryProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.theodo.albeniz.model.Tune;

@Service
@Profile("mock")
@RequiredArgsConstructor
public class MockLibraryService implements LibraryService {

    public final LibraryProperties libraryProperties;

    public final static Map<Integer, Tune> LIBRARY = new HashMap<>();

    static {
            LIBRARY.put(1, new Tune(1, "You belong with me", "Taylor Swift"));
            LIBRARY.put(2, new Tune(2, "Girls just want to have fun", "Cyndi Lauper"));
            LIBRARY.put(3, new Tune(3, "Why not me?", "The Judds"));
        }


    @Override
    public Collection<Tune> getAll(String query) {
        return LIBRARY
                .values()
                .stream()
                .sorted(Comparator.comparing(Tune::getId))
                .filter(tune -> query == null || tune.getTitle().toUpperCase().contains(query.toUpperCase()))
                .limit(libraryProperties.getMaxCollection())
                .collect(Collectors.toList());
        }

    @Override
    public Tune getOne(int id) {
        return LIBRARY.get(id);
        }

    @Override
    public boolean addTune(Tune tune){
        return false;
    }

    @Override
    public void clean() {
        LIBRARY.clear();
    }
    
    @Override
    public boolean remove(int id){
        return true;
    }

    @Override
    public boolean modifyTune(int tuneID, Tune tune) {
        return false;
    }
}
