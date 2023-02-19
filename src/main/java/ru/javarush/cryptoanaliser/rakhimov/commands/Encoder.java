package ru.javarush.cryptoanaliser.rakhimov.commands;

import ru.javarush.cryptoanaliser.rakhimov.entity.Result;
import ru.javarush.cryptoanaliser.rakhimov.entity.ResultCode;
import ru.javarush.cryptoanaliser.rakhimov.exception.ApplicationException;
import ru.javarush.cryptoanaliser.rakhimov.util.PathFinder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Encoder implements Action {
    @Override
    public Result execute(String[] parameters) {
        String txtFile = parameters[0];
        String encryptFile = parameters[0];
        Path path = Path.of(PathFinder.getRoot() + txtFile);
        try {
            List<String> strings = Files.readAllLines(path);
        } catch (IOException e) {
            throw new ApplicationException("Not found", e);
        }
        return new Result(ResultCode.OK, "read all bytes" + path);
    }
}
