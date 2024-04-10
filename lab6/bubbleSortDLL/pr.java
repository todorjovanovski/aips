package lab6.bubbleSortDLL;

import java.util.Arrays;

public class pr {
    public static void main(String[] args) {
        int [] arr = {5, 4, 3, 2, 1};
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
        System.out.println(Arrays.toString(arr));
    }
}
