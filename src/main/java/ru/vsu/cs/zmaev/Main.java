package ru.vsu.cs.zmaev;

import ru.vsu.cs.zmaev.alg.StrAlgs;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        final String input = "AAA";
        System.out.println("Input String: " + input);
        System.out.println("=========Naive Max Borders=========");
        int naiveMaxBorders = StrAlgs.naiveMaxBorders(input);
        System.out.println("res:" + naiveMaxBorders);
        System.out.println("=========Prefix Border Array=========");
        System.out.println(Arrays.toString(StrAlgs.prefixBorderArray(input)));
        System.out.println("=========Zet Block=========");
        System.out.println(Arrays.toString(input.toCharArray()));
        System.out.println(Arrays.toString(StrAlgs.calculateZArray(input)));
    }
}
