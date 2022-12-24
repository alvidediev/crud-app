package com.dediev.crudApp.view;

import com.dediev.crudApp.controller.SkillsController;
import com.dediev.crudApp.model.Skill;
import com.dediev.crudApp.model.Status;

import java.util.Scanner;

public class SkillsView {

    private Scanner scanner;
    private final SkillsController skillsController = new SkillsController();

    public void startWorkWithSkills() {
        scanner = new Scanner(System.in);

        boolean stopper = true;

        System.out.println("Добро пожаловать в меню специальностей!\n" +
                "1) Добавить навыков\n" +
                "2) Изменить навыки в списке\n" +
                "3) Удалить навык из списка\n" +
                "4) Просмотр всех навыков в базе\n" +
                "5) Вернуться в главное меню");

        final int choice = scanner.nextInt();
        while (stopper) {
            if (choice == 1) {
                addSkill();
                stopper = false;
            } else if (choice == 2) {
                updateSkill();
                stopper = false;
            } else if (choice == 3) {
                deleteSkill();
                stopper = false;
            } else if (choice == 4) {
                showAllSkills();
                stopper = false;
            } else if (choice == 5) {
                MainView mainView = new MainView();
                mainView.start();
                stopper = false;
            }
        }
    }

    private void addSkill() {
        scanner = new Scanner(System.in);
        Skill skillToSave = new Skill();

        System.out.println("Пожалуйста, введите название умения:");
        String nameOfSkill = scanner.nextLine();
        skillToSave.setName(nameOfSkill);
        skillToSave.setStatus(Status.ACTIVE);
        Skill createdSkill = skillsController.create(skillToSave);
        System.out.println("Вы успешно добавили навык: " + createdSkill);
        startWorkWithSkills();
    }

    private void updateSkill() {
        scanner = new Scanner(System.in);

        System.out.println("Введите ID навыка, который хотите изменить:");
        final int id = scanner.nextInt();
        System.out.println("Введите новое имя навыка");
        final String name = scanner.nextLine();
        skillsController.update(id, name);
        System.out.println("Вы успешно изменили навык " + skillsController.read(id));
        startWorkWithSkills();
    }

    private void deleteSkill() {
        scanner = new Scanner(System.in);

        System.out.println("Введите ID навыка, который хотите удалить");
        final int id = scanner.nextInt();
        skillsController.delete(id);
        System.out.println(skillsController.read(id));
        startWorkWithSkills();
    }

    private void showAllSkills() {
        System.out.println(skillsController.readAll());
        startWorkWithSkills();
    }
}
