package com.dediev.crudApp.controller;

import com.dediev.crudApp.model.Specialty;
import com.dediev.crudApp.repository.SpecialtyRepository;
import com.dediev.crudApp.repository.impl.GsonSpecialtyRepositoryImpl;

import java.util.List;

public class SpecialtyController {

    private final SpecialtyRepository gsonSpecialtyRepository = new GsonSpecialtyRepositoryImpl();

    public Specialty create(Specialty specialty){
        gsonSpecialtyRepository.save(specialty);
        return specialty;
    }

    public Specialty read(Integer id){
        return gsonSpecialtyRepository.getById(id);
    }

    public List<Specialty> readAll(){
        return gsonSpecialtyRepository.getAll();
    }

    public void update(Integer id, String name){
        Specialty specialty = new Specialty();
        specialty.setId(id);
        specialty.setName(name);
        gsonSpecialtyRepository.update(specialty);
    }

    public void delete(Integer id){
        gsonSpecialtyRepository.deleteById(id);
    }
}
