package com.mikepound.analysis;

import java.util.ArrayList;
import java.util.Arrays;

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
                    newArr.set(i,text.replace(word, "_"));
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

}
