package com.dediev.crudApp.repository;

import com.dediev.crudApp.model.Developer;
import com.dediev.crudApp.model.Skill;
import com.dediev.crudApp.model.Specialty;

import java.util.List;

public interface DevelopersRepository extends GenericRepository<Developer, Integer> {

    void update(Integer id, String firstName, String lastName);
}
