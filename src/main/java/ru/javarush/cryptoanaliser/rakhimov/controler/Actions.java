package ru.javarush.cryptoanaliser.rakhimov.controler;

import ru.javarush.cryptoanaliser.rakhimov.commands.Action;
import ru.javarush.cryptoanaliser.rakhimov.commands.Decoder;
import ru.javarush.cryptoanaliser.rakhimov.commands.Encoder;

public enum Actions {
    ENCODE(new Encoder()),
    DECODE(new Decoder());

    private final Action action;

    Actions(Action action) {
        this.action = action;
    }

    public static Action find(String command) {
        return Actions.valueOf(command.toUpperCase()).action;
    }
}
