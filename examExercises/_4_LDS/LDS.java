package kolok1._4_LDS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class LDS {

    private static int najdolgaOpagackaSekvenca(int[] a) {

        // Vasiot kod tuka
/*      int n = a.length;
        int max = 1;
        for(int i=0; i<n - 1; i++) {
            int count = 1;
            int prev = a[i];
            for(int j=i+1; j<n; j++) {
                if(a[j] < prev) {
                    count++;
                    prev = a[j];
                }
            }
            if(count > max) {
                max = count;
            }
        }*/

        int n = a.length;
        int[] count = new int[n];
        int max = 1;

        for (int i = 0; i < n; i++) {
            count[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] < a[j] && count[i] < count[j] + 1) {
                    count[i] = count[j] + 1;
                }
            }
            if (count[i] > max) {
                max = count[i];
            }
        }
        System.out.println(Arrays.toString(count));
        return max;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }


}