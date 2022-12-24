package com.dediev.crudApp.repository.impl;


import com.dediev.crudApp.model.Skill;
import com.dediev.crudApp.model.Status;
import com.dediev.crudApp.repository.SkillRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import static com.dediev.crudApp.util.Const.*;

public class GsonSkillRepositoryImpl implements SkillRepository {

    private final Gson GSON = new Gson();

    private List<Skill> readSkillsFromFile() {
        String fileContent;

        try {
            fileContent = new String(Files.readAllBytes(Paths.get(PATH_TO_SKILLS_JSON)));
            Type type = new TypeToken<ArrayList<Skill>>() {
            }.getType();
            return GSON.fromJson(fileContent, type);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private void writeSkillsToFile(List<Skill> skills) {
        String json = GSON.toJson(skills);
        try {
            Files.write(Paths.get(PATH_TO_SKILLS_JSON), json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer generateNewId(List<Skill> skills) {
        Skill maxIdSkill = skills.stream().max(Comparator.comparing(Skill::getId)).orElse(null);
        return Objects.nonNull(maxIdSkill) ? maxIdSkill.getId() + 1 : 1;

    }

    @Override
    public Skill getById(Integer id) {
        return readSkillsFromFile().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Skill> getAll() {
        return readSkillsFromFile();
    }

    @Override
    public Skill save(Skill skillToSave) {
        List<Skill> currentSkills = readSkillsFromFile();

        Integer id = generateNewId(currentSkills);
        skillToSave.setId(id);
        currentSkills.add(skillToSave);
        writeSkillsToFile(currentSkills);
        return skillToSave;
    }


    /**
     * id-1 сделан потому что индексация массивов, в т.ч. коллекций в java начинается с 0.
     *
     */

    @Override
    public void update(Integer id, String name) {
        List<Skill> currentSkills = readSkillsFromFile();

        currentSkills.get(id-1).setName(name);
        writeSkillsToFile(currentSkills);
    }

    @Override
    public void deleteById(Integer id) {
        List<Skill> currentSkill = readSkillsFromFile();

        currentSkill.get(id-1).setStatus(Status.DELETED);
        writeSkillsToFile(currentSkill);
    }
}
