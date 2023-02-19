package ru.javarush.cryptoanaliser.rakhimov.controler;

import ru.javarush.cryptoanaliser.rakhimov.commands.Action;
import ru.javarush.cryptoanaliser.rakhimov.entity.Result;

public class MainController {
    public Result execute(String command, String[] parameters) {
        Action action = Actions.find(command);
        return action.execute(parameters);
    }
}
