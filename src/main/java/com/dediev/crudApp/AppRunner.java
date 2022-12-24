package com.dediev.crudApp;

import com.dediev.crudApp.view.MainView;

public class AppRunner {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в CrudApp!");

        MainView mainView = new MainView();

        mainView.start();

    }
}
