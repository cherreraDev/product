package com.chstore.product.faker;

import java.util.Random;

public class DataGenerator {
    private static final String ALPHANUMERIC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String CYRILLIC = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String GREEK = "αβγδεζηθικλμνξοπρστυφχψωΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩ";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_+=-`~[]\\{}|;':\",./<>?";

    private static final String ALL_CHARS = ALPHANUMERIC + CYRILLIC + GREEK + SPECIAL_CHARS + " ";
    private static final Random RANDOM = new Random();
    public static String randomString(int minLength, int maxLength) {
        if (minLength > maxLength || minLength < 0) {
            throw new IllegalArgumentException("invalid string length.");
        }

        int length = RANDOM.nextInt(maxLength - minLength + 1) + minLength;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(ALL_CHARS.charAt(RANDOM.nextInt(ALL_CHARS.length())));
        }

        if (RANDOM.nextDouble() < 0.1) {
            sb.appendCodePoint(getRandomUnicode());
        }
        if (RANDOM.nextDouble() < 0.05) {
            sb.appendCodePoint(getRandomUnicode());
        }

        return sb.toString();
    }

    private static int getRandomUnicode() {
        int[] codePoints = {
                getRandomInt(0x0080, 0x00FF), // Latin-1 Supplement
                getRandomInt(0x0100, 0x017F), // Latin Extended-A
                getRandomInt(0x3040, 0x309F), // Hiragana
                getRandomInt(0x30A0, 0x30FF), // Katakana
                getRandomInt(0x4E00, 0x9FFF), // CJK Unified Ideographs
                getRandomInt(0xAC00, 0xD7AF)  // Hangul Syllables
        };
        return codePoints[RANDOM.nextInt(codePoints.length)];
    }

    private static int getRandomInt(int min, int max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }
}
