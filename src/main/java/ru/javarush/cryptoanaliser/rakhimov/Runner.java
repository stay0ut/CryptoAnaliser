package ru.javarush.cryptoanaliser.rakhimov;

import ru.javarush.cryptoanaliser.rakhimov.app.Application;
import ru.javarush.cryptoanaliser.rakhimov.controler.MainController;
import ru.javarush.cryptoanaliser.rakhimov.entity.Result;

public class Runner {
    public static void main(String[] args) {
        MainController mainController = new MainController();
        Application application = new Application(mainController);
        Result result = application.run(args);

    }
}
