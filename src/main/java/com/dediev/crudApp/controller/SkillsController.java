package com.dediev.crudApp.controller;

import com.dediev.crudApp.model.Skill;
import com.dediev.crudApp.repository.SkillRepository;
import com.dediev.crudApp.repository.impl.GsonSkillRepositoryImpl;

import java.util.List;

public class SkillsController {

    private final SkillRepository gsonSkillRepository = new GsonSkillRepositoryImpl();

    public Skill create(Skill skill){
        gsonSkillRepository.save(skill);
        return skill;
    }

    public Skill read(Integer id){
        return gsonSkillRepository.getById(id);
    }

    public List<Skill> readAll(){
        return gsonSkillRepository.getAll();
    }

    public void update(Integer id, String name){
        Skill skill = new Skill();
        skill.setId(id);
        skill.setName(name);
        gsonSkillRepository.update(skill);
    }

    public void delete(Integer id){
        gsonSkillRepository.deleteById(id);
    }
}
