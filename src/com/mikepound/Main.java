package com.mikepound;

import com.mikepound.analysis.Converter;
import com.mikepound.enigma.Enigma;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        char[] ciphertext = "KGBJNTWBQYFFJWQKKCTNZJVRKBWPQOFZQTBLCYCMWCWTRXSGKAWIZEZKFIWCKPEYBOBUBWVUHBOBKEGFWGGSQWUMIOBKHSFTXAGYXPKAXAOJQJANZKZREKYXTXWWRHJHSTEJAJSQFZMZFLTSEQXBAZWDSJRWHVGFKIXLMPUYINNQSAWQHXAJQJCGUCQUFIHWAFNAAFPRZSMTRKYLUGAOZKYNMXFCHQQEVMTTINCHTSWCYCRZFBKMBVSHEKXDYCYPWSZJWVZAKIRMSQDZKTFDDEUXWKXMNPDMKDRKASAORATLJAEHWINMVRSWASF".toCharArray();
        String plugBoard = "BD CV EL GN IZ JO KW MT PR SX";
        String reflector = "C";
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
        int[] rottors = new int[]{2, 4, 1};
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                for (int k = 0; k < 25; k++) {
                    int[] ringSettings = new int[]{j,k, 20};
                    int[] startingPositions = new int[]{i, Converter.StringToNumber("K"), Converter.StringToNumber("E")};
                    Enigma enigma = new Enigma(Converter.NumberToRoman(rottors), reflector, startingPositions, ringSettings, plugBoard);
                    String sol = new String(enigma.encrypt(ciphertext));
                    if (!list.contains(sol)) {
                        list.add(sol);
                    }
                }
            }
        }

        for (String s : list) {
            if (s.contains("PLANUNG")) {

                System.out.println(s);
            }
        }
    }
}
