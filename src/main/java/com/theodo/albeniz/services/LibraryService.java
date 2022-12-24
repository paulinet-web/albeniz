package com.theodo.albeniz.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.theodo.albeniz.model.Tune;

@Service
public interface LibraryService {
    Collection<Tune> getAll(String query);
    Tune getOne(int id);
    boolean addTune(Tune tune);
    boolean remove(int id);
    boolean modifyTune(int tuneID, Tune tune);
    void clean();
}
