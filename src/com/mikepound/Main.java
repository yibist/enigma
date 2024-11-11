package com.mikepound;

import com.mikepound.analysis.Converter;
import com.mikepound.enigma.Enigma;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        char[][] ciphertexts = {"INAVHYMVHIAGMJOPKVJHSGJYYKNHLFKRZWHWLAKKEGGHZFEAKVVIDDSYYVEYQFQJPVYHLFUZESAOLGNHTXTTBDZJVOAGEAWHBBWCADYYTHSLRXMPEDICATSMALBZYLBPZMQDSXZHPFSXVYCBKGEBTGQGZIIDQJDBYDACSWJGXUCUXLTRTMZHHWXZPESSYEEPFCQAOWOSPLUZUCVOKYJXCPYGNJHSPNCFSWTLLMSGACQBSUTPSAVGUYFVKSUBSQEGVZKVNRLXFIXZQWFKSXCPPFRIMWQHTQSB".toCharArray()};
        String plugBoard = "AT BG DV EW FR HN IQ JX KZ LU";
        String[] wordList = "NULL, EINS, ZWO, DREI, VIER, FUNF, SECHS, SIEBEN, ACHT, NEUN,PLANUNG, OPERATION, SOVIET, OKW, VON, WINTER, DIVISION, PANZER, AFRIKA, KORPS, TRIPOLI, BERG, WEHR, MACHT,ROMMEL, WAFFE, ERLAUBNIS, DEUTSCH,GRUPPE,NORD,OST,SUD,WEST,FELD,MARSCH,SCHUTZ,FUHRER,VOSGE,UBOOT,BRITISCH, TUNIS, ICH, NAZAIR, CALAIS, LORIENT, SCHUTZ, ADOLF, HITLER, BRUSSEL, NACHT, WEIH, BERLIN, BUNKER, NACH, DURCH".replaceAll(" ", "").split(",");

        String[] indicatorSettings = "BKL,SPL,DVB".split(",");
        String[] messageKeys = "UPR,BKK,LTK".split(",");

        String[] reflectors = new String[]{"C" };
        ArrayList<String> possiblePlugOptions = Converter.getPossiblePlugboards(plugBoard);
        for (int n = 0; n < ciphertexts.length; n++) {
            char[] ciphertext = ciphertexts[n];
            String indicatorSetting = indicatorSettings[n];
            String messageKey = messageKeys[n];
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
               for (int j = 0; j < 26; j++) {
                   for (int k = 0; k < 26; k++) {
                       for (String reflector : reflectors) {
                           int[] rotors = new int[]{4, 3, 1};
                           int[] ringSettings = new int[]{6,23,14};
                           int[] startingPositions = new int[]{i,j,k};

                           Enigma enigma = new Enigma(Converter.NumberToRoman(rotors), reflector, startingPositions, ringSettings, plugBoard);
                           String sol = new String(enigma.encrypt(ciphertext));

                           if (!list.contains(sol)) {
                               list.add(sol);
                           }


                       }
                   }
               }
            }

            for (String s : Converter.getWordCount(list, wordList, 5)) {
                System.out.println(s);
            }
            System.out.println("---------------------------------------------------------");
        }
    }
}