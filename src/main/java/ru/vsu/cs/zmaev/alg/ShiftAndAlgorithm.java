package ru.vsu.cs.zmaev.alg;

public class ShiftAndAlgorithm {
    public static void main(String[] args) {
        String pattern = "abra";
        String text = "abracadabra";
        shiftAnd(pattern, text);
    }

    public static void shiftAnd(String P, String T) {
        int m = P.length();
        int n = T.length();
        char chBeg = '0';
        char chEnd = 'z';
        int nA = chEnd - chBeg + 1;
        int[] B = new int[nA];

        for (int k = 0; k < nA; ++k)
            B[k] = 0;

        for (int j = 0; j < m; ++j) {
            B[P.charAt(j) - chBeg] |= 1 << (m - 1 - j);
        }

        int uHigh = 1 << (m - 1);
        int M = 0;

        for (int i = 0; i < n; ++i) {
            M = (M >> 1 | uHigh) & B[T.charAt(i) - chBeg];
            if ((M & 1) == 1) {
                System.out.println("Вхождение с позиции " + (i - m + 1));
            }
        }
    }
}

