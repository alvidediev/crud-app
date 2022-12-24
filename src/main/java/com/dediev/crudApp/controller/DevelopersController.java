package com.dediev.crudApp.controller;

import com.dediev.crudApp.model.Developer;
import com.dediev.crudApp.model.Skill;
import com.dediev.crudApp.model.Specialty;
import com.dediev.crudApp.repository.DevelopersRepository;
import com.dediev.crudApp.repository.impl.GsonDevelopersRepositoryImpl;

import java.util.List;

public class DevelopersController {

    private final DevelopersRepository repository = new GsonDevelopersRepositoryImpl();

    public Developer create(Developer developer) {
        repository.save(developer);
        return developer;
    }

    public Developer read(Integer id) {
        return repository.getById(id);
    }

    public List<Developer> readAll() {
        return repository.getAll();
    }

    public void update(Integer id, String firstName, String lastName){
        repository.update(id, firstName, lastName);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
