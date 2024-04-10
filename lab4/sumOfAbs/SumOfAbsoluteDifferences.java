package lab4.sumOfAbs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumOfAbsoluteDifferences {

    static int solve(int[] numbers, int N, int K) {
        // vasiot kod ovde
        // mozete da napisete i drugi funkcii dokolku vi se potrebni
          int max = 0;
/*        for(int i=0; i<=N-K; i++) {
            int k = K, size = 0;
            int[] subArray = new int[K];
            subArray[size++] = numbers[i];
            int currJ = 0;
            while(k > 1) {
                int subMax = 0;
                for(int j=i+1; j<=N-k+1; j++) {
                    if(Math.abs(numbers[j] - subArray[size-1]) > subMax && j>currJ) {
                        subMax = Math.abs(numbers[j] - subArray[size-1]);
                        subArray[size] = numbers[j];
                        currJ = j;
                    }
                }
                if(subMax == 0) subArray[size] = numbers[N-k+1];
                size++;
                k--;
            }
            int curr = checkSumOfAbs(subArray);
            if(curr > max) {
                for(int m=0; m<K; m++) {
                    System.out.println(subArray[m] + " ");
                }
                System.out.println();
                max = curr;
            }
        }*/

        int[][] matrix = new int[N][K];

        for (int i = 0; i < N; i++) {
            for (int j = 1; j < K; j++) {
                for (int k = 0; k < i; k++) {
                    int absolute = Math.abs(numbers[i] - numbers[k]);
                    if (matrix[i][j] < matrix[k][j-1] + absolute){
                        matrix[i][j] = matrix[k][j-1] + absolute;
                    }
                    if (matrix[i][j] > max){
                        max = matrix[i][j];
                    }
                }
            }
        }
        return max;
    }

    static int checkSumOfAbs(int[] array) {
        int sum = 0;
        for(int i=1; i<array.length; i++) {
            sum += Math.abs(array[i] - array[i-1]);
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        int i;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] numbers = new int[N];

        st = new StringTokenizer(br.readLine());
        for (i=0;i<N;i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int res = solve(numbers, N, K);
        System.out.println(res);

        br.close();

    }

}
