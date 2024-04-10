package lab6.OddEvenSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class OddEvenSort {

    static void oddEvenSort(int[] a, int n) {
        LinkedList<Integer> list = new LinkedList<>();
        // Vasiot kod tuka
        int odd = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] % 2 != 0) {
                list.add(a[i]);
                odd++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0) {
                list.add(a[i]);
            }
        }
        for (int i = 0; i < odd - 1; i++) {
            for (int j = i + 1; j < odd; j++) {
                if (list.get(i) > list.get(j)) {
                    int temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
        for (int i = odd; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (list.get(i) < list.get(j)) {
                    int temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            a[i] = list.removeFirst();
        }
    }

    public static void main(String[] args) throws IOException {
        int i;
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String[] pom = s.split(" ");
        int[] a = new int[n];
        for (i = 0; i < n; i++)
            a[i] = Integer.parseInt(pom[i]);
        oddEvenSort(a, n);
        for (i = 0; i < n - 1; i++)
            System.out.print(a[i] + " ");
        System.out.print(a[i]);
    }
}