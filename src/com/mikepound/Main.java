package com.mikepound;

import com.mikepound.analysis.Converter;
import com.mikepound.enigma.Enigma;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        char[] ciphertext = "ACINZCRVFJRPETNUGAVDZIHXFWTPKKTTVXZJTAYNXRQMAJKKFIWUXTNHFCMZPUUYPPKILQYBYRDMHFBMHPGYLCEIJUNMWGQOKGHMDLGJWBQBZBVWDTSUWHGXZRFUXWQTHTRHYPTPJZROFTCNMXCKSDDNHIWYSUGQRZUIYIUOTDZPQRTCXVQXPMZGJBWLHXULWLUNLWPWNTONBQUFMMIKNTVWNK".toCharArray();
        String plugBoard = "AS CK DE FV GJ LU MW OT PX RZ";
        String reflector = "B";
        String[] wordList = new String[] {"EINS", "ZWO", "DREI", "VIER", "FUNF", "SECHS", "SIEBEN", "ACHT", "NEUN", "NULL", "ANFANG", "OPERATION", "VON", "OKW", "WINTER"};
        /*
        String indicatorSetting = "AST";
        String messageKey = "SGT";

        int[][] permutations = {
                {0, 1, 2},
                {0, 2, 1},
                {1, 0, 2},
                {1, 2, 0},
                {2, 0, 1},
                {2, 1, 0}
        };

        */
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            for (int j = 0; j < 25; j++) {
                for (int k = 0; k < 25; k++) {
                    int[] rotors = new int[]{2, 4, i};
                    int[] ringSettings = new int[]{8,1, j};
                    int[] startingPositions = new int[]{Converter.StringToNumber("E"), Converter.StringToNumber("F"), k};
                    Enigma enigma = new Enigma(Converter.NumberToRoman(rotors), reflector, startingPositions, ringSettings, plugBoard);
                    String sol = new String(enigma.encrypt(ciphertext));
                    if (!list.contains(sol)) {
                        list.add(sol);
                    }
                }
            }
        }

        for (String s : Converter.getWordCount(list,wordList, 4)) {
                System.out.println(s);
        }
    }
}