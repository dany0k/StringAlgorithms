package ru.vsu.cs.zmaev.alg;

import java.util.Arrays;

public class KMP {

    public static void main(String[] args) {
        String pattern = "ЛИЛИЛА"; // Ваш образец
        String text = "ЛИЛИЛОСЬ ЛИЛИЛАСЬ"; // Ваш текст
        System.out.println(Arrays.toString(text.toCharArray()));
         kmp(pattern, text);
    }

    public static void kmp(String pattern, String text) {
        int m = pattern.length();
        int n = text.length();
        int[] bp = new int[m];
        int[] bpm = new int[m];

        prefixBorderArray(pattern, bp);

        bpToBPM(bp, bpm, m);

        int k = 0; // Текущий индекс в образце

        for (int i = 0; i < n; ++i) {
            while (k > 0 && pattern.charAt(k) != text.charAt(i)) {
                k = bpm[k - 1];
            }
            if (pattern.charAt(k) == text.charAt(i)) {
                ++k;
            }
            if (k == m) {
                System.out.printf("Вхождение с позиции %d\n", i - k + 1);
                k = bpm[k - 1];
            }
        }
    }

    public static void prefixBorderArray(String pattern, int[] bpm) {
        int m = pattern.length();
        int k = 0;
        bpm[0] = 0;

        for (int i = 1; i < m; ++i) {
            while (k > 0 && pattern.charAt(k) != pattern.charAt(i)) {
                k = bpm[k - 1];
            }
            if (pattern.charAt(k) == pattern.charAt(i)) {
                ++k;
            }
            bpm[i] = k;
        }
    }

    public static void bpToBPM(int[] bp, int[] bpm, int n) {
        bpm[0] = 0;
        bpm[n - 1] = bp[n - 1];
        for (int i = 1; i < n - 1; ++i) {
            // Проверка «совпадения следующих символов»
            if (bp[i] != 0 && bp[i] + 1 == bp[i + 1]) {
                bpm[i] = bpm[bp[i] - 1];
            } else {
                bpm[i] = bp[i];
            }
        }
    }
}
