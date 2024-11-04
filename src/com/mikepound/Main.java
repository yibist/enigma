package com.mikepound;

import com.mikepound.analysis.EnigmaAnalysis;
import com.mikepound.analysis.EnigmaKey;
import com.mikepound.analysis.ScoredEnigmaKey;
import com.mikepound.analysis.ToRoman;
import com.mikepound.analysis.fitness.*;
import com.mikepound.enigma.Enigma;

public class Main {

    public static void main(String[] args) {
        int bi = 0, bj = 0, bk = 0;
        char[] ciphertext = "MUUQJZVQLORVMCOLYKXEPMCDCWGHNTQVMEHGECOEULBULBOCZPGBIXIFWCYXZKZKLYAEVCJDGXJZQKQGVXSORRQNZMATPZDOEXITXFIUVJFIZUAYLIJWVVGFYXGRDQKAGUUWBNUUOUXQQUCXKUXPTYUIIXPAYXRLTZPZQRNLOPAODDUSVFWMILZEOBVOPIPWHXVYADCORXPIIEUZVTXBRJRECTGLCPKQAJDAMI".toCharArray();
        String plugBoard = "BD CO EI GL JS KT NV PM QR WZ";
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

        Enigma enigma = new Enigma(new String[]{"VIII", "II", "IV"}, "B", new int[]{14,14,18}, new int[]{18, 6, 11}, plugBoard);
        System.out.println(new String(enigma.encrypt(ciphertext)));
    }
}
