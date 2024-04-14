package ru.vsu.cs.zmaev.alg;

public class StrAlgs {

    public static int naiveMaxBorders(String inputString) {
        char[] chars = inputString.toCharArray();
        int size = inputString.length();
        int br = 0;
        for (int i = size - 1; br < i ; i--) {
            int j = 0;
            while (j < i && chars[j] == chars[size - i + j]) {
                j++;
                if (j == i) {
                    br = i;
                }
            }
        }
        return br;
    }

    public static int[] prefixBorderArray(String inputString) {
        int size = inputString.length();
        int[] bp = new int[size];
        bp[0] = 0;
        for (int i = 1; i < size; ++i) {
            int bpRight = bp[i - 1];

            while (bpRight > 0 && inputString.charAt(i) != inputString.charAt(bpRight)) {
                bpRight = bp[bpRight - 1];
            }

            if (inputString.charAt(i) == inputString.charAt(bpRight)) {
                bp[i] = bpRight + 1;
            } else {
                bp[i] = 0;
            }
        }
        return bp;
    }

    public static int[] calculateZArray(String s) {
        int n = s.length();
        int[] zp = new int[n];
        int l = 0;
        int r = 0;
        zp[0] = 0;
        for (int i = 1; i < n; ++i) {
            zp[i] = 0;
            if (i >= r) {
                zp[i] = strComp(s, n, 0, i);
                l = i;
                r = l + zp[i];
            } else {
                int j = i - l;
                if (zp[j] < r - i) {
                    zp[i] = zp[j];
                } else {
                    zp[i] = r - i + strComp(s, n, r - i, r);
                    l = i;
                    r = l + zp[i];
                }
            }
        }
        return zp;
    }

    public static int strComp(String s, int n, int i1, int i2) {
        int eqLen = 0;
        while (i1 < n && i2 < n && s.charAt(i1++) == s.charAt(i2++)) {
            ++eqLen;
        }
        return eqLen;
    }
}
