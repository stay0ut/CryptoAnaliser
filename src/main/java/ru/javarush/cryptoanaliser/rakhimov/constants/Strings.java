package ru.javarush.cryptoanaliser.rakhimov.constants;

public class Strings {
    private static final String rus = "йцукенгшщзхъфывапролджэячсмитьбю";
    private static final String eng = "qwertyuiopasdfghjklzxcvbnm";
    private static final String cyphers = "0123456789";
    private static final String symbols = "!?.,:;'-–+=*«„“><»{}[]()/\\\\\\\" \"";
    public static final String ALPHABET = rus + rus.toUpperCase() + eng + eng.toUpperCase() + cyphers + symbols;
}
