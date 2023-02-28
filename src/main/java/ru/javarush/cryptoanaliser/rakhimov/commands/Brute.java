package ru.javarush.cryptoanaliser.rakhimov.commands;

import ru.javarush.cryptoanaliser.rakhimov.constants.Strings;
import ru.javarush.cryptoanaliser.rakhimov.entity.Result;
import ru.javarush.cryptoanaliser.rakhimov.entity.ResultCode;
import ru.javarush.cryptoanaliser.rakhimov.exception.ApplicationException;
import ru.javarush.cryptoanaliser.rakhimov.util.PathFinder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Brute implements Action {

    @Override
    public Result execute(String[] parameters) {
        String txtFile = parameters[0];
        String encryptFile = parameters[1];
        String exampleFile = parameters[2];
        Path path = Path.of(PathFinder.getRoot() + txtFile);
        Path path2 = Path.of(PathFinder.getRoot() + exampleFile);
        String[] exampleFileInStringArray = fileToStringArray(path2);
        try {
            char[] strings = Files.readAllLines(path).toString().toCharArray();
            char[] alphabet = Strings.ALPHABET.toCharArray();
            HashMap<Integer, Integer> countOfCoincidence = new HashMap<>();
            for (int key = 1; key < alphabet.length; key++) {
                ArrayList<Character> alphabetList = new ArrayList<>();
                for (char c : alphabet) {
                    alphabetList.add(c);
                }
                Collections.rotate(alphabetList, key);
                char[] resultChars = decode(strings, alphabet, alphabetList);
                String input = Arrays.toString(resultChars);
                StringBuilder sb = new StringBuilder(input);
                for (int i = 2; i < sb.length(); i += 2) {
                    sb.delete(i, i + 1);
                }
                String[] resultStringArray = sb.toString().split(" {2}");
                for (String s : exampleFileInStringArray) {
                    int count = 0;
                    for (String value : resultStringArray) {
                        if (s.equalsIgnoreCase(value)) {
                            count++;
                        }
                    }
                    countOfCoincidence.put(count, key);
                }
            }

            ArrayList<Character> alphabetList2 = new ArrayList<>();
            for (char c : alphabet) {
                alphabetList2.add(c);
            }
            Collections.rotate(alphabetList2, getTrueKey(countOfCoincidence));
            char[] result = decode(strings, alphabet, alphabetList2);
            writeDecodedText(encryptFile, result);
        } catch (IOException e) {
            throw new ApplicationException("IO error", e);
        }
        return new Result(ResultCode.OK, "read all lines" + path);
    }

    private String[] fileToStringArray(Path filename) {
        List<String> text = new ArrayList<>();
        try {
            String a = Files.readAllLines(filename).toString();
            String[] stringArray = a.split(" ");
            text = Arrays.asList(stringArray);
        } catch (IOException exc) {
            System.out.println("Ошибка преобразования файла в массив слов " + exc);
        }
        return text.toArray(new String[0]);
    }

    private char[] decode(char[] strings, char[] alphabet, ArrayList<Character> alphabetList) {
        char[] resultChars = new char[strings.length];
        for (int i = 0; i < strings.length; i++) {
            for (int b = 0; b < alphabet.length; b++) {
                int charCode1 = strings[i];
                int charCode2 = alphabet[b];
                if (charCode1 == charCode2) {
                    resultChars[i] = alphabetList.get(b);
                    break;
                }
            }
        }
        return resultChars;
    }

    private void writeDecodedText(String outFile, char[] resultChars) {
        StringWriter writer = new StringWriter();
        writer.write(resultChars, 0, resultChars.length);
        Path pathOut = Path.of(outFile);
        try {
            Files.writeString(pathOut, writer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static int getTrueKey(HashMap<Integer, Integer> countOfCoincidence) {
        int maxValue = Collections.max(countOfCoincidence.keySet());
        return countOfCoincidence.get(maxValue);
    }
}

