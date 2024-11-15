package com.mikepound;

import com.mikepound.analysis.Converter;
import com.mikepound.analysis.EnigmaAnalysis;
import com.mikepound.analysis.EnigmaKey;
import com.mikepound.analysis.ScoredEnigmaKey;
import com.mikepound.analysis.fitness.*;
import com.mikepound.enigma.Enigma;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        FitnessFunction ioc = new IoCFitness();
        FitnessFunction bigrams = new BigramFitness();
        FitnessFunction quadgrams = new QuadramFitness();
        char[] ciphertext = "KYYUGIWKSEYPQDFYPIJNTGNDIAHNBROXDIKEKPTMOUHBEJRRJPVBAOCUZRDFSAZDCNUNNMRPCCMCHJBWSTIKZIREBBVJQAXZARIYVANIJVOLDNBUMXXFNZVRQEGOYXEVVNMPWEBSKEUTJJOKPBKLHIYWGNFFPXKIEWSNTLMDKYIDMOFPTDFJAZOHVVQETNIPVZGTUMYJCMSEAKTYELPZUNHEYFCLAADYPEEXMHQMVAVZZDOIMGLERBBLATHQJIYCBSUPVVTRADCRDDSTYIXYFEAFZYLNZZDPNNXXZJNRCWEXMTYRJOIAOEKNRXGXPNMTDGKFZDSYHMUJAPOBGANCRCZTMEPXESDZTTJZGNGQRMKNCZNAFMDAXXTJSRTAZTZKRTOXHAHTNPEVNAAVUZMHLPXLMSTWELSOBCTMBKGCJKMDPDQQGCZHMIOCGRPDJEZTYVDQGNPUKCGKFFWMNKWPSCLENWHUEYCLYVHZNKNVSCZXUXDPZBDPSYODLQRLCGHARLFMMTPOCUMOQLGJJAVXHZZVBFLXHNNEJXS".toCharArray();
        String plugBoard = "";
        String[] wordList = "NULL, EINS, ZWO, DREI, VIER, FUNF, SECHS, SIEBEN, ACHT, NEUN,PLANUNG, OPERATION, SOVIET, OKW, VON, WINTER, DIVISION, PANZER, AFRIKA, KORPS, TRIPOLI, BERG, WEHR, MACHT,ROMMEL, WAFFE, ERLAUBNIS, DEUTSCH,GRUPPE,NORD,OST,SUD,WEST,FELD,MARSCH,SCHUTZ,FUHRER,VOSGE,UBOOT,BRITISCH, TUNIS, ICH, NAZAIR, CALAIS, LORIENT, SCHUTZ, ADOLF, HITLER, BRUSSEL, NACHT, WEIH, BERLIN, BUNKER, NACH, DURCH".replaceAll(" ", "").split(",");
        String indicatorSettings = "EDF";
        String messageKeys = "GXT";
        String[] reflectors = new String[]{"C", "B"};
        System.out.println(ciphertext.length);
        final long startTime = System.currentTimeMillis();

        // For those interested, these were the original settings
        // II V III / 7 4 19 / 12 2 20 / AF TV KO BL RW

        // Begin by finding the best combination of rotors and start positions (returns top n)
        List<String[]> rotorConfigurations = EnigmaAnalysis.getThreeRotorCombinations(List.of("I", "II", "III", "IV", "V", "VI", "VII", "VIII"));
        ArrayList<ScoredEnigmaKey> goodKeys = new ArrayList<>();
        for (String reflector : new String[]{"C", "B"}) {
            rotorConfigurations.parallelStream().forEach(rotors -> {
                //System.out.println(rotors[0] + " " + rotors[1] + " " + rotors[2]);
                for (int i = 0; i < 26; i++) {
                    for (int j = 0; j < 26; j++) {
                        for (int k = 0; k < 26; k++) {
                            Enigma enigma = new Enigma(rotors, reflector, Converter.StringToNumberArray(indicatorSettings), new int[]{i, j, k}, "");
                            String decriptedMessageKey = new String(enigma.encrypt(messageKeys.toCharArray()));
                            int[] ringSettings = Converter.StringToNumberArray(decriptedMessageKey);
                            enigma = new Enigma(rotors, reflector, ringSettings, new int[]{i, j, k}, "");
                            char[] decriptedText = enigma.encrypt(ciphertext);
                            float iocScore = ioc.score(decriptedText);
                            if (iocScore > 0.04f) {
                                EnigmaKey key = new EnigmaKey(rotors, ringSettings, new int[]{i, j, k}, "");
                                goodKeys.add(new ScoredEnigmaKey(key, iocScore));
                            }
                        }
                    }
                }
            });
        }
        /*
        for (char c : decriptedText) {

        }
        */
        System.out.println(goodKeys.size());
        goodKeys.parallelStream().forEach(keys -> {
            ScoredEnigmaKey key = EnigmaAnalysis.findPlugs(keys, 10, ciphertext, ioc);
            HashMap<Character, Integer> mostCommonLetters = new HashMap<>();
            Enigma enigma = new Enigma(key);
            for (char c : enigma.encrypt(ciphertext)) {
                if (!mostCommonLetters.containsKey(c)) {
                    mostCommonLetters.put(c, 0);
                }
                mostCommonLetters.put(c, mostCommonLetters.get(c) + 1);
            }
            if (mostCommonLetters.get('E') == null) {return;}
            if (mostCommonLetters.get('N') == null) {return;}
            if (mostCommonLetters.get('E')>40 || mostCommonLetters.get('N')>40) {
                ArrayList<String> strings = new ArrayList<>();
                strings.add(new String(enigma.encrypt(ciphertext)));


                for (String s: Converter.getWordCount(strings,wordList,5)) {

                    System.out.println(key.getScore());
                    System.out.println(s);
                }
            }

        });

        final long endTime = System.currentTimeMillis();

        System.out.println("Total execution time: " + (endTime - startTime));
    }
}
