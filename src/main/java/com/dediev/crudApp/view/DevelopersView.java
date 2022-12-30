package com.dediev.crudApp.view;

import com.dediev.crudApp.controller.DevelopersController;
import com.dediev.crudApp.controller.SkillsController;
import com.dediev.crudApp.controller.SpecialtyController;
import com.dediev.crudApp.model.Developer;
import com.dediev.crudApp.model.Skill;
import com.dediev.crudApp.model.Specialty;
import com.dediev.crudApp.model.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DevelopersView {

    private final SkillsController skillsController = new SkillsController();
    private final SpecialtyController specialtyController = new SpecialtyController();
    private final DevelopersController devsController = new DevelopersController();
    private Scanner scanner;

    public void startWorkWithDevelopers() {
        scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в меню специальностей!\n" +
                "1) Добавить разработчика\n" +
                "2) Изменить разработчика в списке\n" +
                "3) Удалить разработчика из списка\n" +
                "4) Просмотр всех разработчиков в базе\n" +
                "5) Вернуться в главное меню");

        final int choice = scanner.nextInt();
        while (true) {
            switch (choice) {
                case 1:
                    addDeveloper();
                    break;
                case 2:
                    updateDeveloper();
                    break;
                case 3:
                    deleteDeveloper();
                    break;
                case 4:
                    showAllDevs();
                    break;
                default:
                    MainView mainView = new MainView();
                    mainView.start();
            }
        }
    }


    private void addDeveloper() {
        scanner = new Scanner(System.in);

        System.out.println("Пожалуйста! Введите фамилию разработчика:");
        String developersFirstName = scanner.nextLine();
        System.out.println("Пожалуйста! Введите имя разработчика:");
        String developersName = scanner.nextLine();
        final List<Skill> skill = skillChooser();
        final Specialty specialty = specialtyChooser();
        Developer createdDeveloper = devsController.create(developersFirstName, developersName, skill, specialty);
        System.out.println(createdDeveloper);
        startWorkWithDevelopers();
    }

    private void deleteDeveloper() {
        scanner = new Scanner(System.in);

        System.out.println("Пожалуйста введите ID разработчика, которого хотите удалить");
        int idOfDeveloper = scanner.nextInt();
        devsController.delete(idOfDeveloper);
        System.out.println(devsController.read(idOfDeveloper));
        startWorkWithDevelopers();
    }

    private void updateDeveloper() {
        scanner = new Scanner(System.in);

        System.out.println(devsController.readAll());
        System.out.println("Введите ID разработчика, которого хотите отредактировать");
        int id = scanner.nextInt();
        System.out.println("Введите новую фамилию для разработчика");
        String firstName = scanner.nextLine();
        System.out.println("Введите новое имя для разработчика");
        String lastName = scanner.nextLine();
        final List<Skill> skills = skillChooser();
        final Specialty specialty = specialtyChooser();
        devsController.update(id, firstName, lastName, skills, specialty);
        System.out.println(devsController.read(id));
        startWorkWithDevelopers();
    }

    private void showAllDevs() {
        System.out.println(devsController.readAll());
        startWorkWithDevelopers();
    }

    private List<Skill> skillChooser() {
        scanner = new Scanner(System.in);

        List<Skill> currentDeveloperSkills = new ArrayList<>();

        while (true) {
            System.out.println("Хотите добавить скилл?\n" +
                    "1) Да\n" +
                    "2) Нет");
            final int addSkillsChoice = scanner.nextInt();
            switch (addSkillsChoice) {
                case 1:
                    System.out.println(skillsController.readAll());
                    System.out.println("Пожалуйста, выберите skill по id ");
                    final int skillId = scanner.nextInt();
                    currentDeveloperSkills.add(skillsController.read(skillId));
                    System.out.println("Вы успешно добавили " + skillsController.read(skillId));
                case 2:
                    break;
                default:
                    System.out.println("Пожалуйста, введите корректные данные!");
            }

            return currentDeveloperSkills;
        }
    }

    private Specialty specialtyChooser() {
        scanner = new Scanner(System.in);

        System.out.println("Пожалуйста, выберите специальность по ID");
        System.out.println(specialtyController.readAll());
        final int specialtyId = scanner.nextInt();

        return specialtyController.read(specialtyId);
    }
}

