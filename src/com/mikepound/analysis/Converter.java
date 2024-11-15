package com.mikepound.analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Converter {

    public static int[] StringToNumberArray(String string) {
        string = string.toLowerCase();
        int[] numberArray = new int[string.length()];
        for (int i = 0; i < string.length(); i++) {
            numberArray[i] = string.charAt(i) - 'a';
        }
        return numberArray;
    }

    public static String[] NumberToRoman(int[] numberArray) {
        String[] convertedArray = new String[numberArray.length];
        for (int i = 0; i < numberArray.length; i++) {

            switch (numberArray[i]) {
                case 1:
                    convertedArray[i] = "I";
                    break;
                case 2:
                    convertedArray[i] = "II";
                    break;
                case 3:
                    convertedArray[i] = "III";
                    break;
                case 4:
                    convertedArray[i] = "IV";
                    break;
                case 5:
                    convertedArray[i] = "V";
                    break;
                case 6:
                    convertedArray[i] = "VI";
                    break;
                case 7:
                    convertedArray[i] = "VII";
                    break;
                case 8:
                    convertedArray[i] = "VIII";
                    break;
            }
        }
        return convertedArray;
    }

    public static int StringToNumber(String string) {
        string = string.toLowerCase();
        return string.charAt(0) - 'a';
    }


    public static ArrayList<String> getWordCount(ArrayList<String> arr, String[] wordList, int top) {
        ArrayList<String> newArr = new ArrayList<>(arr);
        for (int i = 0; i < newArr.size(); i++) {
            for (String word : wordList) {
                String text = newArr.get(i);
                if (text.contains(word)) {
                    newArr.set(i, text.replace(word, "_"));
                }
            }
        }

        ArrayList<String> newArr2 = new ArrayList<>();
        for (int i = 0; i < newArr.size(); i++) {
            int count = newArr.get(i).split("_").length;
            if (top <= count) {
                newArr2.add("count: " + count + " " + arr.get(i));
            }
        }

        return newArr2;
    }


    private static String[] proonAlphabet(String plugBoard) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (String plugString : plugBoard.replaceAll(" ", "").split("")) {
           alphabet = alphabet.replace(plugString, "");
        }
        return alphabet.split("");
    }

    public static ArrayList<String> getPossiblePlugboards(String plugBoard) {
        String[] possibleLetters = Converter.proonAlphabet(plugBoard);
        ArrayList<String> result = new ArrayList<>();
        for (String pL1 : possibleLetters) {
            for (String pL2 : possibleLetters) {
                if (!pL1.equals(pL2)) {
                    result.add(pL1 + pL2);
                }
            }
        }
        return result;
    }

    public static boolean hasSameLetters(String[] strings) {
        Set<Character> seenLetters = new HashSet<>();

        for (String str : strings) {
            for (char c : str.toCharArray()) {
                if (seenLetters.contains(c)) {
                    return true;  // Shared letter found
                }
                seenLetters.add(c);
            }
        }
        return false;  // No shared letters
    }

}