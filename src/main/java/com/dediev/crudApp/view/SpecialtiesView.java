package com.dediev.crudApp.view;

import com.dediev.crudApp.controller.SpecialtyController;
import com.dediev.crudApp.model.Specialty;
import com.dediev.crudApp.model.Status;

import java.util.Scanner;

public class SpecialtiesView {

    Scanner scanner;
    private final SpecialtyController specialtyController = new SpecialtyController();

    public void startWorkWithSpecialties() {
        scanner = new Scanner(System.in);
        boolean stopper = true;

        System.out.println("Добро пожаловать в меню специальностей!\n" +
                "1) Добавить специальностей\n" +
                "2) Изменить специальность в списке\n" +
                "3) Удалить специальности из списка\n" +
                "4) Просмотр всех специальностей в базе\n" +
                "5) Вернуться в главное меню");

        final int choice = scanner.nextInt();
        while (stopper) {
            if (choice == 1) {
                addSpecialty();
                stopper = false;
            } else if (choice == 2) {
                updateSpecialty();
                stopper = false;
            } else if (choice == 3) {
                deleteSpecialty();
                stopper = false;
            } else if (choice == 4) {
                showAllSpecialties();
                stopper = false;
            } else if (choice == 5) {
                MainView mainView = new MainView();
                mainView.start();
                stopper = false;
            }
        }
    }


    private void addSpecialty() {
        scanner = new Scanner(System.in);
        Specialty specialty = new Specialty();

        System.out.println("Пожалуйста, Введите название умения:");
        String nameOfSpecialty = scanner.nextLine();
        specialty.setName(nameOfSpecialty);
        specialty.setStatus(Status.ACTIVE);
        Specialty createdSkill = specialtyController.create(specialty);
        System.out.println(createdSkill);
        startWorkWithSpecialties();
    }

    private void updateSpecialty() {
        scanner = new Scanner(System.in);

        System.out.println("Пожалуйста, введите ID специальности, который хотите изменить:");
        final int id = scanner.nextInt();
        System.out.println("Введите новое имя специальности:");
        final String changedSpecialtyName = scanner.nextLine();
        specialtyController.update(id, changedSpecialtyName);
        System.out.println("Вы успешно изменили специальность " + specialtyController.read(id));
        startWorkWithSpecialties();
    }

    private void deleteSpecialty() {
        scanner = new Scanner(System.in);

        System.out.println("Введите ID специальности, которую хотите удалить");
        final int id = scanner.nextInt();
        specialtyController.delete(id);
        System.out.println(specialtyController.read(id));
        startWorkWithSpecialties();
    }

    private void showAllSpecialties() {
        System.out.println(specialtyController.readAll());
        startWorkWithSpecialties();
    }
}
