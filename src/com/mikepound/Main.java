package com.mikepound;

import com.mikepound.analysis.Converter;
import com.mikepound.enigma.Enigma;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        char[] ciphertext = "ZJTPLTJNETNLLGOPQVSWXSRHCOSHUTFGUSHHTVPOUMBMVGKLAAFDUBNUVCUVPOCFJXDMIQCCAUCBQOKPHUMCIZAJVIQESVGCFHDTISREHFCMBPJCRTWTTMXCNOIEUWRPOMCEMSUNBBCTWZZRBLFLUFIFBNYOYJGXUMNKPTCQHTGVYWSQDFFMSWVECIDWILZBYLIPRXYICFCLPDQZNOZWSKVNJURTGKMWUNFPNLEPOFQLJMEDEFNMLRRRRJYTBVRKBQQGSUWVAWAFUUWFLMPKPHLDML".toCharArray();
        String indicatorSetting = "AST";
        String messageKey = "SGT";
        String plugBoard = "AL FP HX JO KT NV QR SU WY EC";
        String reflector = "C";
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
        int[] rottors = new int[]{3,2,5};
        int[] ringSettings = new int[]{7, 18, 2};
        for (int[] permutation : permutations) {
            int[] rot = new int[]{rottors[permutation[0]], rottors[permutation[1]], rottors[permutation[2]]};
            for (int[] permutation2 : permutations) {
                int[] set = new int[]{ringSettings[permutation2[0]], ringSettings[permutation2[1]], ringSettings[permutation2[2]]};
                Enigma enigma = new Enigma(Converter.NumberToRoman(rot), reflector, Converter.StringToNumberArray(indicatorSetting), set, plugBoard);
                enigma = new Enigma(Converter.NumberToRoman(rot), reflector, Converter.StringToNumberArray(new String(enigma.encrypt(messageKey.toCharArray()))), set, plugBoard);
                String sol = new String(enigma.encrypt(ciphertext));
                if (sol.contains("UBOOT")) {
                    System.out.println(sol);
                }
            }
        }
    }
}
