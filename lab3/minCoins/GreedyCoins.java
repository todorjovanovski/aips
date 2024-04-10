package lab3.minCoins;

import java.util.Arrays;
import java.util.Scanner;

public class GreedyCoins {

    public static int minNumCoins(int coins[], int sum) {
        //Vasiot kod ovde

        Arrays.sort(coins);
        int counter = 0;
        for(int i=coins.length - 1; i>=0; i--) {
            int del = sum/coins[i];
            if(del > 0) {
                sum -= del*coins[i];
                counter += del;
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String coinsStringLine = input.nextLine();
        String coinsString[] = coinsStringLine.split(" ");
        int coins[] = new int[coinsString.length];
        for(int i=0;i<coinsString.length;i++) {
            coins[i] = Integer.parseInt(coinsString[i]);
        }

        int sum = input.nextInt();

        System.out.println(minNumCoins(coins, sum));
    }
}