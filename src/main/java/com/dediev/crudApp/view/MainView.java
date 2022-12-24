package com.dediev.crudApp.view;

import java.util.Scanner;

public class MainView {

    private final Scanner scanner = new Scanner(System.in);
    private final SpecialtiesView specialtiesView = new SpecialtiesView();
    private final SkillsView skillsView = new SkillsView();
    private final DevelopersView developersView = new DevelopersView();

    public void start() {
        System.out.println("ГЛАВНОЕ МЕНЮ!\n" +
                "Выберите один из вариантов\n" +
                "1)Меню skill's\n" +
                "2)Меню specialties\n" +
                "3)Меню разработчиков\n" +
                "4)Завершить программу");
        int choice = scanner.nextInt();
        if (choice == 1) {
            skillsView.startWorkWithSkills();
        } else if (choice == 2) {
            specialtiesView.startWorkWithSpecialties();
        } else if (choice == 3) {
            developersView.startWorkWithDevelopers();
        }else if(choice == 4){
            System.exit(0);
        }
    }
}
