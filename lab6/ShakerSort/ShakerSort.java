package lab6.ShakerSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ShakerSort {

    static void printArray(int[] a) {
        for (int number : a) {
            System.out.print(number + " ");
        }
        System.out.println();
    }

    static void shakerSort(int[] a, int n) {
        boolean flag = false;
        for (int i = 0, k = n - 1; i < n; i++, k--) {
            if (i > k || flag) break;

            flag = true;
            for (int j = n - 1; j > i; j--) {
                if (a[j] < a[j - 1]) {
                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                    flag = false;
                }
            }
            printArray(a);

            for (int j = i; j < k; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = false;
                }
            }
            printArray(a);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String[] pom = s.split(" ");
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(pom[i]);
        shakerSort(a, n);
    }
}