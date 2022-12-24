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

    @Override  // true s'il n'existe pas (valid request on peut l'ajouter), else false
    public boolean addTune(Tune tune){  
        boolean tuneAbsent = !library.containsKey(tune.getId());
        if (tuneAbsent) {
            library.put(tune.getId(), tune);
        }
        return tuneAbsent;
        }

    @Override
    public void clean() {
        library.clear();
    }
        
    @Override  //true if exists, else false (invalid request)
    public boolean remove(int id){
        boolean tunePresent = library.containsKey(id);
        if (tunePresent){
            library.remove(id);
    }
    return tunePresent;
    }

    @Override // true si l'objet existe et a été modifié, false si l'objet n'existe pas
    public boolean modifyTune(int tuneId, Tune tune) {
        if (library.containsKey(tuneId)){
            Tune previousTune = library.get(tuneId);
            previousTune.setTitle(tune.getTitle());
            previousTune.setAuthor(tune.getAuthor());
            library.put(tuneId, previousTune);
            return true;
        }
        return false;
    }
}
