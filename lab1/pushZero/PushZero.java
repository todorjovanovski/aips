package lab1.pushZero;

import java.io.*;
import java.util.Scanner;

public class PushZero
{
    static void pushZerosToEnd(int[] arr, int n)
    {
        int counter = 0;
        for(int i=0; i<n; i++) {
            counter++;
            if(counter == n)
                break;
            if(arr[i] == 0) {
                for(int j=i; j<n; j++)
                    arr[j] = arr[j+1];
                arr[n-1] = 0;
                i--;
            }
        }

        System.out.println("Transformiranata niza e:");
        for (int i=0; i<n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main (String[] args)
    {
        Scanner input = new Scanner(System.in);
        int[] arr = new int[100];
        int n = input.nextInt();
        for(int i=0; i<n; i++) {
            arr[i] = input.nextInt();
        }

        pushZerosToEnd(arr, n);

    }
}