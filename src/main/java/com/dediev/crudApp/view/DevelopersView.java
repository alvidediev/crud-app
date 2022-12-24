package com.dediev.crudApp.view;

import com.dediev.crudApp.controller.DevelopersController;
import com.dediev.crudApp.controller.SkillsController;
import com.dediev.crudApp.controller.SpecialtyController;
import com.dediev.crudApp.model.Developer;
import com.dediev.crudApp.model.Status;

import java.util.Scanner;

public class DevelopersView {

    private final SkillsController skillsController = new SkillsController();
    private final SpecialtyController specialtyController = new SpecialtyController();
    private final DevelopersController devsController = new DevelopersController();
    private Scanner scanner;

    public void startWorkWithDevelopers() {
        scanner = new Scanner(System.in);

        boolean stopper = true;

        System.out.println("Добро пожаловать в меню специальностей!\n" +
                "1) Добавить разработчика\n" +
                "2) Изменить разработчика в списке\n" +
                "3) Удалить разработчика из списка\n" +
                "4) Просмотр всех разработчиков в базе\n" +
                "5) Вернуться в главное меню");

        final int choice = scanner.nextInt();
        while (stopper) {
            if (choice == 1) {
                addDeveloper();
                stopper = false;
            } else if (choice == 2) {
                updateDeveloper();
                stopper = false;
            } else if (choice == 3) {
                deleteDeveloper();
                stopper = false;
            } else if (choice == 4) {
                showAllDevs();
                stopper = false;
            } else if (choice == 5) {
                MainView mainView = new MainView();
                mainView.start();
                stopper = false;
            }
        }
    }


    private void addDeveloper() {
        scanner = new Scanner(System.in);
        Developer developerToSave = new Developer();

        System.out.println("Пожалуйста! Введите фамилию разработчика:");
        String developersFirstName = scanner.nextLine();
        developerToSave.setFirstName(developersFirstName);
        System.out.println("Пожалуйста! Введите имя разработчика:");
        String developersName = scanner.nextLine();
        developerToSave.setLastName(developersName);
        developerToSave.setSkill(skillsController.readAll());
        System.out.println("Выберите специальность разработчика по ID");
        System.out.println(specialtyController.readAll());
        final int idSpecialty = scanner.nextInt();
        developerToSave.setSpecialty(specialtyController.read(idSpecialty));
        developerToSave.setStatus(Status.ACTIVE);
        Developer createdDeveloper = devsController.create(developerToSave);
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

        System.out.println("Введите ID разработчика, которого хотите отредактировать");
        final int id = scanner.nextInt();
        System.out.println("Введите новую фамилию для разработчика");
        final String firstName = scanner.nextLine();
        System.out.println("Введите новое имя для разработчика");
        final String lastName = scanner.nextLine();
        devsController.update(id, firstName, lastName);
        System.out.println(devsController.read(id));
        startWorkWithDevelopers();
    }

    private void showAllDevs() {
        System.out.println(devsController.readAll());
        startWorkWithDevelopers();
    }
}
