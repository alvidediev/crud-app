package com.dediev.crudApp.repository.impl;

import com.dediev.crudApp.model.Developer;
import com.dediev.crudApp.model.Status;
import com.dediev.crudApp.repository.DevelopersRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static com.dediev.crudApp.util.Const.PATH_TO_DEVELOPERS_JSON;

public class GsonDevelopersRepositoryImpl implements DevelopersRepository {

    private final Gson GSON = new Gson();

    private List<Developer> readDevelopersFromFile() {
        String fileContent;

        try {
            fileContent = new String(Files.readAllBytes(Paths.get(PATH_TO_DEVELOPERS_JSON)));
            Type type = new TypeToken<ArrayList<Developer>>() {
            }.getType();
            return GSON.fromJson(fileContent, type);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private void writeDevelopersToFile(List<Developer> developers) {
        String json = GSON.toJson(developers);
        try {
            Files.write(Paths.get(PATH_TO_DEVELOPERS_JSON), json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeDeveloperToFile(Developer developer) {
        String json = GSON.toJson(developer);
        try {
            Files.write(Paths.get(PATH_TO_DEVELOPERS_JSON), json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer generateNewId(List<Developer> developer) {
        Developer maxIdDevelopers = developer.stream().max(Comparator.comparing(Developer::getId)).orElse(null);
        return Objects.nonNull(maxIdDevelopers) ? maxIdDevelopers.getId() + 1 : 1;

    }

    @Override
    public Developer getById(Integer id) {
        return readDevelopersFromFile().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Developer> getAll() {
        return readDevelopersFromFile();
    }

    @Override
    public Developer save(Developer developerToSave) {
        List<Developer> currentDeveloper = readDevelopersFromFile();

        Integer id = generateNewId(currentDeveloper);
        developerToSave.setId(id);
        currentDeveloper.add(developerToSave);
        writeDevelopersToFile(currentDeveloper);
        return developerToSave;
    }

    @Override
    public Developer update(Developer developerToUpdate) {
        List<Developer> currentDeveloper = readDevelopersFromFile();
        currentDeveloper.forEach(existingDeveloper -> {
            if(existingDeveloper.getId().equals(developerToUpdate.getId())) {
                existingDeveloper.setFirstName(developerToUpdate.getFirstName());
                existingDeveloper.setLastName(developerToUpdate.getLastName());
                existingDeveloper.setSkill(developerToUpdate.getSkill());
                existingDeveloper.setSpecialty(developerToUpdate.getSpecialty());
            }
        });
        writeDevelopersToFile(currentDeveloper);
        return developerToUpdate;
    }


    @Override
    public void deleteById(Integer id) {
        List<Developer> currenDeveloper = readDevelopersFromFile();

        currenDeveloper.forEach(existingDeveloper -> {
            if(existingDeveloper.getId().equals(id)){
                existingDeveloper.setStatus(Status.DELETED);
            }
        });

        writeDevelopersToFile(currenDeveloper);
    }
}
