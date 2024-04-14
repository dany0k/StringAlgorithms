package ru.vsu.cs.zmaev.alg;

import java.util.Arrays;
import java.util.List;

public class GFG {
    public static class Suffix implements Comparable<Suffix> {
        int index;
        int rank;
        int next;

        public Suffix(int ind, int r, int nr) {
            index = ind;
            rank = r;
            next = nr;
        }

        public int compareTo(Suffix s) {
            if (rank != s.rank) return Integer.compare(rank, s.rank);
            return Integer.compare(next, s.next);
        }

        @Override
        public String toString() {
            return "Suffix{" +
                    "index=" + index +
                    ", rank=" + rank +
                    ", next=" + next +
                    '}';
        }
    }

    public static Integer[] suffixArray(String s) {
        int n = s.length();
        Suffix[] su = new Suffix[n];

        for (int i = 0; i < n; i++) {
            su[i] = new Suffix(i, s.charAt(i) - '$', 0);
        }

        for (int i = 0; i < n; i++)
            su[i].next = (i + 1 < n ? su[i + 1].rank : -1);

        printArr(Arrays.stream(su).toList());

        Arrays.sort(su);

        int[] ind = new int[n];

        for (int length = 4; length < 2 * n; length <<= 1) {

            int rank = 0;
            int prev = su[0].rank;
            su[0].rank = rank;
            ind[su[0].index] = 0;
            for (int i = 1; i < n; i++) {

                if (su[i].rank == prev && su[i].next == su[i - 1].next) {
                    prev = su[i].rank;
                    su[i].rank = rank;
                } else {
                    prev = su[i].rank;
                    su[i].rank = ++rank;
                }
                ind[su[i].index] = i;
            }

            for (int i = 0; i < n; i++) {
                int nextP = su[i].index + length / 2;
                su[i].next = nextP < n ? su[ind[nextP]].rank : -1;
            }
            Arrays.sort(su);
        }

        Integer[] suf = new Integer[n];

        for (int i = 0; i < n; i++)
            suf[i] = su[i].index;

        return suf;
    }

    public static <T> void printArr(List<T> sd) {
        sd.forEach(System.out::println);
    }

    public static void main(String[] args) {
        String txt = "banana";
        Integer[] suff_arr = suffixArray(txt);
        System.out.printf("Following is suffix array for %s\n", txt);
        printArr(Arrays.stream(suff_arr).toList());

        // Вывод суффиксов для каждого индекса
        System.out.println("Suffixes:");
        for (int i = 0; i < suff_arr.length; i++) {
            int index = suff_arr[i];
            String suffix = txt.substring(index);
            System.out.printf("%d - %s\n", index, suffix);
        }
    }
}
