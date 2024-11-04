package com.mikepound;

import com.mikepound.analysis.Converter;
import com.mikepound.enigma.Enigma;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        char[] ciphertext = "RGOXTIVDHRFWGIKPUKXKCTISBGRIKMMYAAASMQFHZGLBUZGOFGMGOJFBOPYUKMPPXVGIBTFYGKOLISPXCLRREQXOUQOMXIJKLMNOPQRSTUVWXYZ".toCharArray();
        String indicatorSetting = "XLT";
        String messageKey = "VPM";
        String plugBoard = "BL CK DG FP IR MO QW ST VY UZ";
        /*
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                for (int k = 0; k < 25; k++) {
                    Enigma enigma = new Enigma(ToRoman.NumberToRoman(new int[]{8,2,4}), "B", new int[]{i,j,k}, new int[]{18,6,11}, plugBoard);
                    String sol = new String(enigma.encrypt(ciphertext));
                    if (sol.contains("UBOOT")) {
                        System.out.println(sol);
                    }
                }
            }

        }
        */
        int[][] permutations = {
                {0, 1, 2},
                {0, 2, 1},
                {1, 0, 2},
                {1, 2, 0},
                {2, 0, 1},
                {2, 1, 0}
        };
        int[] rottors = new int[]{1, 3, 5};
        int[] ringSettings = new int[]{7, 25, 3};
        for (int[] permutation : permutations) {
            int[] rot = new int[]{rottors[permutation[0]], rottors[permutation[1]], rottors[permutation[2]]};
            int[] set = new int[]{ringSettings[permutation[0]], ringSettings[permutation[1]], ringSettings[permutation[2]]};
            Enigma enigma = new Enigma(Converter.NumberToRoman(rot), "B", Converter.StringToNumberArray(indicatorSetting), set, plugBoard);
            enigma = new Enigma(Converter.NumberToRoman(rot), "B", Converter.StringToNumberArray(new String(enigma.encrypt(messageKey.toCharArray()))), set, plugBoard);
            System.out.println(new String(enigma.encrypt(ciphertext)));
        }
        ciphertext = "NGWTVVGTHGZPQGFEXSWNFKSXHPOJCMPGHLLCAJCPUVIQUUZLQWAVOTSCPZFABNUORZZAFMCJPGPTGTDRGWTZAXNQRYJCBMQZVCQUCQOCOJGNYNUCAVOMA".toCharArray();
        indicatorSetting ="HNB";
        messageKey = "SFA";
        System.out.println("-------------------------");
        for (int[] permutation : permutations) {
            int[] rot = new int[]{rottors[permutation[0]], rottors[permutation[1]], rottors[permutation[2]]};
            int[] set = new int[]{ringSettings[permutation[0]], ringSettings[permutation[1]], ringSettings[permutation[2]]};
            Enigma enigma = new Enigma(Converter.NumberToRoman(rot), "B", Converter.StringToNumberArray(indicatorSetting), set, plugBoard);
            enigma = new Enigma(Converter.NumberToRoman(rot), "B", Converter.StringToNumberArray(new String(enigma.encrypt(messageKey.toCharArray()))), set, plugBoard);
            System.out.println(new String(enigma.encrypt(ciphertext)));
        }
    }
}
