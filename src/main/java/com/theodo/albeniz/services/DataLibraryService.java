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

    public final Map<Integer, Tune> library = new HashMap<>();
        

    @Override
    public Collection<Tune> getAll(String query) {
        return library
            .values()  // ne prendre que les values et pas les keys de la map
            .stream()
            .sorted(Comparator.comparing(Tune::getId))    // sort unsorted stream by IDs
            .filter(tune -> query == null || tune.getTitle().toUpperCase().contains(query.toUpperCase()))   // if query == null, ne filtre pas --> prend tous les tunes
            .collect(Collectors.toList());
            
        }

    @Override
    public Tune getOne(int id) {
        return library.get(id);
        }

    @Override
    public void addTune(Tune tune){
        library.put(tune.getId(), tune);
    }

    @Override
    public void clean() {
        library.clear();
    }
        
    @Override
    public void remove(int id){
        library.remove(id);
    }
}
