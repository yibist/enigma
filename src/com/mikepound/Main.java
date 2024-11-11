package com.mikepound;

import com.mikepound.analysis.Converter;
import com.mikepound.enigma.Enigma;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        char[][] ciphertexts = {"SIAZKQGEMLIVDBIYWAKCAMPYKCFLOPQDCWPVMITCWAYWKBRUJAVGRYYCISIJZSGRMTZEKGEQLWUXIXYPMQLUHODQFPNRKBZDISWXPHYDBNEQHJUZJRZFWWMVTGIXFSFCQIBVMHGENWKNKYXMQRYSMAWCMBWFHYPNWJEBVYBZEZRCUFZYLIFFJCQFKGOGBYGXMDJLUJMMKZDLNNNJIYEAOYUVDFRFCCUVPWYPJHWFSGGRLXQDFFOKLSKGXZ".toCharArray(),
                "YNDXIHNTJYETDDJVBPCAPORBPPASUKHYHTHETMFGJNPUFWAMEBFIKQBZGGFZZXJMUYNJDWXJXZDMEEVPYRDGPYMAXWTWHUGDQZTMJWKYQRDQXKVGTZYIIMPBVDJPQVJLOIOSXQENZZHCNTWCQYQYMHCOXPNTDXMTZWABTWRVYIGMJEICMHXHHEITFPKXEFWMICOVTIVIBIEACPFVXZILJXWTBRVBEFENEWQZTCCDMWVWGLDZTXGUDJWSTR".toCharArray(),
                "YNDXIHNTJYETDDJVBPCAPORBPPASUKHYHTHETMFGJNPUFWAMEBFIKQBZGGFZZXJMUYNJDWXJXZDMEEVPYRDGPYMAXWTWHUGDQZTMJWKYQRDQXKVGTZYIIMPBVDJPQVJLOIOSXQENZZHCNTWCQYQYMHCOXPNTDXMTZWABTWRVYIGMJEICMHXHHEITFPKXEFWMICOVTIVIBIEACPFVXZILJXWTBRVBEFENEWQZTCCDMWVWGLDZTXGUDJWSTR".toCharArray()};
        String plugBoard = "AU CM DP EV HL IZ JW NO QX ST";
        String[] wordList = "NULL, EINS, ZWO, DREI, VIER, FUNF, SECHS, SIEBEN, ACHT, NEUN,PLANUNG, OPERATION, SOVIET, OKW, VON, WINTER, DIVISION, PANZER, AFRIKA, KORPS, TRIPOLI, BERG, WEHR, MACHT,ROMMEL, WAFFE, ERLAUBNIS, DEUTSCH,GRUPPE,NORD,OST,SUD,WEST,FELD,MARSCH,SCHUTZ,FUHRER,VOSGE,UBOOT,BRITISCH, TUNIS, ICH, NAZAIR, CALAIS, LORIENT, SCHUTZ, ADOLF, HITLER, BRUSSEL, NACHT, WEIH, BERLIN, BUNKER, NACH, DURCH".replaceAll(" ", "").split(",");

        String[] indicatorSettings = "AEG,VSF".split(",");
        String[] messageKeys = "GJW,DNA".split(",");
        /*
        int[][] permutations = {
                {0, 1, 2},
                {0, 2, 1},
                {1, 0, 2},
                {1, 2, 0},
                {2, 0, 1},
                {2, 1, 0}
        };

        */
            for (int n = 0; n < ciphertexts.length; n++) {
                char[] ciphertext = ciphertexts[n];
                String indicatorSetting = indicatorSettings[n];
                String messageKey = messageKeys[n];
                ArrayList<String> list = new ArrayList<>();

                for (String reflector : new String[]{"B", "C" }) {
                    for (int i = 1; i < 9; i++) {
                        for (int j = 1; j < 9; j++) {
                            for (int k = 1; k < 9; k++) {
                                int[] rotors = new int[]{i, j, k};
                                int[] ringSettings = new int[]{4, 21, 10};
                                int[] startingPositions = Converter.StringToNumberArray(indicatorSetting);
                                Enigma enigma = new Enigma(Converter.NumberToRoman(rotors), reflector, startingPositions, ringSettings, plugBoard);
                                String sol = new String(enigma.encrypt(messageKey.toCharArray()));
                                enigma = new Enigma(Converter.NumberToRoman(rotors), reflector, Converter.StringToNumberArray(sol), ringSettings, plugBoard);
                                sol = new String(enigma.encrypt(ciphertext));

                                if (!list.contains(sol)) {
                                    list.add(sol);
                                }
                            }
                        }
                    }
                }
                for (
                        String s : Converter.getWordCount(list, wordList, 7)) {
                    System.out.println(s);
                }
                System.out.println("---------------------------------------------------------");
        }
    }
}