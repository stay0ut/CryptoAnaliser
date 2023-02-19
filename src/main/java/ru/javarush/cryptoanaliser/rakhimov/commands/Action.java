package ru.javarush.cryptoanaliser.rakhimov.commands;

import ru.javarush.cryptoanaliser.rakhimov.entity.Result;

public interface Action {
    Result execute(String[] parameters);
}
