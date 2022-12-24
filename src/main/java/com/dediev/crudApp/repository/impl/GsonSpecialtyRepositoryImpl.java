package com.dediev.crudApp.repository.impl;

import com.dediev.crudApp.model.Specialty;
import com.dediev.crudApp.model.Status;
import com.dediev.crudApp.repository.SpecialtyRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static com.dediev.crudApp.util.Const.PATH_TO_SPECIALTIES_JSON;

public class GsonSpecialtyRepositoryImpl implements SpecialtyRepository {

    private final Gson GSON = new Gson();

    private List<Specialty> readSpecialtyFromFile() {
        String fileContent;

        try {
            fileContent = new String(Files.readAllBytes(Paths.get(PATH_TO_SPECIALTIES_JSON)));
            Type type = new TypeToken<ArrayList<Specialty>>() {
            }.getType();
            return GSON.fromJson(fileContent, type);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private void writeSpecialtyToFile(List<Specialty> specialties) {
        String json = GSON.toJson(specialties);
        try {
            Files.write(Paths.get(PATH_TO_SPECIALTIES_JSON), json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer generateNewId(List<Specialty> specialty) {
        Specialty maxIdSpecialty = specialty.stream().max(Comparator.comparing(Specialty::getId)).orElse(null);
        return Objects.nonNull(maxIdSpecialty) ? maxIdSpecialty.getId() + 1 : 1;
    }

    @Override
    public Specialty getById(Integer id) {
        return readSpecialtyFromFile().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Specialty> getAll() {
        return readSpecialtyFromFile();
    }

    @Override
    public Specialty save(Specialty specialty) {
        List<Specialty> currentSpecialty = readSpecialtyFromFile();

        Integer id = generateNewId(currentSpecialty);
        specialty.setId(id);
        currentSpecialty.add(specialty);
        writeSpecialtyToFile(currentSpecialty);
        return specialty;
    }

    /**
     * id-1 сделан потому что индексация массивов, в т.ч. коллекций в java начинается с 0.
     *
     */

    @Override
    public void deleteById(Integer id) {
        List<Specialty> currentSpecialty = readSpecialtyFromFile();

        currentSpecialty.get(id-1).setStatus(Status.DELETED);
        writeSpecialtyToFile(currentSpecialty);
    }

    @Override
    public void update(Integer id, String name) {
        List<Specialty> currentSpecialty = readSpecialtyFromFile();

        currentSpecialty.get(id-1).setName(name);
        writeSpecialtyToFile(currentSpecialty);
    }
}
