package kolok1._3_finTownTickets;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bus {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        br.close();

        // Vasiot kod tuka
        int max, min;
        if(M < N) {
            min = N*100;
        } else{
            min = M * 100;
        }
        if(M > 0) {
            max = (N+M-1) * 100;
        } else {
            max = min;
        }


        System.out.println(min);
        System.out.println(max);
    }
}
