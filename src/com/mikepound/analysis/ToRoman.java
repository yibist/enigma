package com.mikepound.analysis;

public class ToRoman {

    public static String[] NumberToRoman(int[] numberArray) {
        String [] convertedArray =  new String[numberArray.length];
        for (int i = 0; i < numberArray.length; i++) {

            switch (numberArray[i]) {
                case 1: convertedArray[i] = "I"; break;
                case 2: convertedArray[i] = "II"; break;
                case 3: convertedArray[i] = "III"; break;
                case 4: convertedArray[i] = "IV"; break;
                case 5: convertedArray[i] = "V"; break;
                case 6: convertedArray[i] = "VI"; break;
                case 7: convertedArray[i] = "VII"; break;
                case 8: convertedArray[i] = "VIII"; break;
            }
        }
        return convertedArray;
    }
}
