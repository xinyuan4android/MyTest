package com.example;

public class MyClass {
    public static void main(String[] args) {
        int[] a = new int[11];
        for (int i = 1; i < 5; i++) {
            a[1] = i;

            if ((a[2] == a[5]) &&
                    ((a[3] == a[6] && a[3] == a[2] && a[3] != a[4]) ||
                            (a[3] == a[6] && a[3] == a[4] && a[3] != a[2]) ||
                            (a[3] == a[2] && a[3] == a[4] && a[3] != a[6]) ||
                            (a[2] == a[6] && a[6] == a[4] && a[3] != a[6])) &&
                    (a[1] == a[5] || a[2] == a[7] || a[1] == a[9] || a[6] == a[10]) &&
                    (a[5] == a[8] || a[5] == a[4] || a[5] == a[9] || a[5] == a[7]) &&
                    ((a[8] == a[2] && a[8] == a[4]) || (a[8] == a[1] && a[8] == a[6]) ||
                            (a[8] == a[3] && a[8] == a[10]) || (a[8] == a[5] && a[8] == a[9]))
                    ) {
                for (int j = 1; j < a.length; j++) {
                    System.out.print(a[j]);
                }
            }
        }
    }
}
