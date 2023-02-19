package ru.javarush.cryptoanaliser.rakhimov.app;

import ru.javarush.cryptoanaliser.rakhimov.entity.Result;
import ru.javarush.cryptoanaliser.rakhimov.controler.MainController;

import java.util.Arrays;

public class Application {

    private final MainController mainController;

    public Application(MainController mainCOntroller) {
        this.mainController = mainCOntroller;
    }

    public Result run(String[] args) {
        String command = args[0];
        String[] parameters = Arrays.copyOfRange(args, 1, args.length);
        return mainController.execute(command, parameters);
    }
}
