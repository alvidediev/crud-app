package com.dediev.crudApp.controller;

import com.dediev.crudApp.model.Developer;
import com.dediev.crudApp.model.Skill;
import com.dediev.crudApp.model.Specialty;
import com.dediev.crudApp.model.Status;
import com.dediev.crudApp.repository.DevelopersRepository;
import com.dediev.crudApp.repository.impl.GsonDevelopersRepositoryImpl;

import java.util.List;

public class DevelopersController {

    private final DevelopersRepository repository = new GsonDevelopersRepositoryImpl();

    public Developer create(String firstName, String lastName, List<Skill> skills, Specialty specialty) {
        Developer developer  = new Developer();
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setSkill(skills);
        developer.setSpecialty(specialty);
        developer.setStatus(Status.ACTIVE);
        return repository.save(developer);
    }

    public Developer read(Integer id) {
        return repository.getById(id);
    }

    public List<Developer> readAll() {
        return repository.getAll();
    }

    public Developer update(Integer id, String firstName, String lastName, List<Skill> skills, Specialty specialty){
        Developer developer  = new Developer();
        developer.setId(id);
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setSkill(skills);
        developer.setSpecialty(specialty);
        return repository.update(developer);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
