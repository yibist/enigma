package com.mikepound;

import com.mikepound.analysis.Converter;
import com.mikepound.enigma.Enigma;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        char[][] ciphertexts = {"SIAZKQGEMLIVDBIYWAKCAMPYKCFLOPQDCWPVMITCWAYWKBRUJAVGRYYCISIJZSGRMTZEKGEQLWUXIXYPMQLUHODQFPNRKBZDISWXPHYDBNEQHJUZJRZFWWMVTGIXFSFCQIBVMHGENWKNKYXMQRYSMAWCMBWFHYPNWJEBVYBZEZRCUFZYLIFFJCQFKGOGBYGXMDJLUJMMKZDLNNNJIYEAOYUVDFRFCCUVPWYPJHWFSGGRLXQDFFOKLSKGXZ".toCharArray(), "YNDXIHNTJYETDDJVBPCAPORBPPASUKHYHTHETMFGJNPUFWAMEBFIKQBZGGFZZXJMUYNJDWXJXZDMEEVPYRDGPYMAXWTWHUGDQZTMJWKYQRDQXKVGTZYIIMPBVDJPQVJLOIOSXQENZZHCNTWCQYQYMHCOXPNTDXMTZWABTWRVYIGMJEICMHXHHEITFPKXEFWMICOVTIVIBIEACPFVXZILJXWTBRVBEFENEWQZTCCDMWVWGLDZTXGUDJWSTR".toCharArray(), "BKWVQICHPWRRYJDAXQEIQJKQQYMLTPVAKYCJZZTDAODOLSTOKLSSXJRTQCKIKGRRDRJZYZWWJPTABZJEOWGRUKLASPPBMKZBJRHIOKPAKYFZPCOUAAXDMZQMTLDFNNKEZDGRNUZQA".toCharArray()};
        String plugBoard = "BM DV KT LN RS UP XZ";
        String[] wordList = "NULL, EINS, ZWO, DREI, VIER, FUNF, SECHS, SIEBEN, ACHT, NEUN,PLANUNG, OPERATION, SOVIET, OKW, VON, WINTER, DIVISION, PANZER, AFRIKA, KORPS, TRIPOLI, BERG, WEHR, MACHT,ROMMEL, WAFFE, ERLAUBNIS, DEUTSCH,GRUPPE,NORD,OST,SUD,WEST,FELD,MARSCH,SCHUTZ,FUHRER,VOSGE,UBOOT,BRITISCH, TUNIS, ICH, NAZAIR, CALAIS, LORIENT, SCHUTZ, ADOLF, HITLER, BRUSSEL, NACHT, WEIH, BERLIN, BUNKER, NACH, DURCH".replaceAll(" ", "").split(",");

        String[] indicatorSettings = "BKL,SPL,DVB".split(",");
        String[] messageKeys = "UPR,BKK,LTK".split(",");

        String[] reflectors = new String[]{"B" };
        ArrayList<String> possiblePlugOptions = Converter.getPossiblePlugboards(plugBoard);
        for (int n = 0; n < ciphertexts.length; n++) {
            char[] ciphertext = ciphertexts[n];
            String indicatorSetting = indicatorSettings[n];
            String messageKey = messageKeys[n];
            ArrayList<String> list = new ArrayList<>();
            for (String reflector : reflectors) {
                int[] rotors = new int[]{2, 1, 3};
                int[] ringSettings = new int[]{25, 2, 15};
                int[] startingPositions = Converter.StringToNumberArray(indicatorSetting);
                int ptr1 = 1;
                int ptr2 = 2;
                int ptr3 = 3;
                final int plugBoardSize = possiblePlugOptions.size();




                while (ptr1 < plugBoardSize && ptr2 < plugBoardSize && ptr3 < plugBoardSize) {
                    String option1 = possiblePlugOptions.get(ptr1);
                    String option2 = possiblePlugOptions.get(ptr2);
                    String option3 = possiblePlugOptions.get(ptr3);

                    if (ptr1 == plugBoardSize - 3) {
                        break;
                    }
                    if (ptr3 == plugBoardSize - 1 && ptr2 == plugBoardSize - 2) {
                        ptr3 = ptr1 + 3;
                        ptr2 = ptr1 + 2;
                        ptr1++;
                    } else if (ptr3 == plugBoardSize - 1) {
                        ptr3 = ptr2 + 2;
                        ptr2++;
                    } else {
                        ptr3++;
                    }

                    if (Converter.hasSameLetters(new String[]{option1, option2, option3})) {
                        continue;
                    }


                    String newPlugs = plugBoard + " " + option1 + " " + option2 + " " + option3;
                    Enigma enigma = new Enigma(Converter.NumberToRoman(rotors), reflector, startingPositions, ringSettings, newPlugs);
                    String sol = new String(enigma.encrypt(messageKey.toCharArray()));
                    //System.out.println(newPlugs);
                    enigma = new Enigma(Converter.NumberToRoman(rotors), reflector, Converter.StringToNumberArray(sol), ringSettings, newPlugs);
                    sol = new String(enigma.encrypt(ciphertext));
                        if (!list.contains(sol)) {
                        list.add(sol);
                    }


                }
            }
            for (String s : Converter.getWordCount(list, wordList, 4)) {
                System.out.println(s);
            }
            System.out.println("---------------------------------------------------------");
        }
    }
}