package ru.javarush.cryptoanaliser.rakhimov.commands;

import ru.javarush.cryptoanaliser.rakhimov.constants.Strings;
import ru.javarush.cryptoanaliser.rakhimov.entity.Result;
import ru.javarush.cryptoanaliser.rakhimov.entity.ResultCode;
import ru.javarush.cryptoanaliser.rakhimov.exception.ApplicationException;
import ru.javarush.cryptoanaliser.rakhimov.util.PathFinder;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

public class Decoder implements Action {
    @Override
    public Result execute(String[] parameters) {
        String txtFile = parameters[0];
        String encryptFile = parameters[1];
        int key = Integer.parseInt(parameters[2]);
        Path path = Path.of(PathFinder.getRoot() + txtFile);
        try {
            char[] strings = Files.readAllLines(path).
                    toString().
                    toCharArray();
            char[] alphabet = Strings.ALPHABET.toCharArray();
            ArrayList<Character> alphabetList = new ArrayList<>();
            for (char c : alphabet) {
                alphabetList.add(c);
            }
            Collections.rotate(alphabetList, (Math.abs(key - alphabet.length)));
            char[] resultChars = decode(strings, alphabet, alphabetList);
            writeDecoderText(encryptFile, resultChars);
        } catch (IOException e) {
            throw new ApplicationException("IO error", e);
        }
        return new Result(ResultCode.OK, "read all lines" + path);
    }

    char[] decode(char[] strings, char[] alphabet, ArrayList<Character> alphabetList) {
        char[] resultChars = new char[strings.length];
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                int charCode1 = strings[i];
                int charCode2 = alphabet[j];
                if (charCode1 == charCode2) {
                    resultChars[i] = alphabetList.get(j);
                    break;
                }
            }
        }
        return resultChars;
    }

    void writeDecoderText(String encryptFile, char[] resultChars) {
        StringWriter writer = new StringWriter();
        writer.write(resultChars, 0, resultChars.length);
        Path pathOut = Path.of(encryptFile);
        try {
            Files.writeString(pathOut, writer.toString().
                    trim());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}