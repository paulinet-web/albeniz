package com.theodo.albeniz.services;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.theodo.albeniz.model.Tune;

@Service
@Profile("data")
public class DataLibraryService implements LibraryService{

    public final Map<Integer, Tune> LIBRARY = new HashMap<>();
        

    @Override
    public Collection<Tune> getAll(String query) {
        return LIBRARY
            .values()
            .stream()
            .sorted(Comparator.comparing(Tune::getId))
            .filter(tune -> query == null || tune.getTitle().toUpperCase().contains(query.toUpperCase()))
            .collect(Collectors.toList());
        }

    @Override
    public Tune getOne(int id) {
        return LIBRARY.get(id);
        }

    @Override
    public boolean addTune(Tune tune){  
        boolean isTuneAbsent = !LIBRARY.containsKey(tune.getId());
        if (isTuneAbsent) {
            LIBRARY.put(tune.getId(), tune);
        }
        return isTuneAbsent;
        }

    @Override
    public void clean() {
        LIBRARY.clear();
    }
        
    @Override
    public boolean remove(int id){
        boolean isTunePresent = LIBRARY.containsKey(id);
        if (isTunePresent){
            LIBRARY.remove(id);
    }
        return isTunePresent;
    }

    @Override
    public boolean modifyTune(int tuneId, Tune tune) {
        boolean tuneExists = LIBRARY.containsKey(tuneId);
        if (tuneExists){
            Tune previousTune = LIBRARY.get(tuneId);
            previousTune.setTitle(tune.getTitle());
            previousTune.setAuthor(tune.getAuthor());
            LIBRARY.put(tuneId, previousTune);
        }
        return tuneExists;
    }
}
