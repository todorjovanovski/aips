package lab3.minRastojanie;

import java.util.Scanner;

public class MinDistance {

    public static float minimalDistance(float points[][]) {
        //d=√((x2 – x1)² + (y2 – y1)²)
        //Vasiot kod ovde

        float minDistance = 0;
        int first = 0;
        for(int i=0; i<points.length - 1; i++) {
            float x1 = points[i][0], y1 = points[i][1];
            for(int j=i+1; j<points.length; j++) {
                float x2 = points[j][0], y2 = points[j][1];
                float d = (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
                if(first == 0) {
                    minDistance = d;
                    first = 1;
                }
                else if(d < minDistance)
                    minDistance = d;
            }
        }

        return minDistance;
    }

    public static void main(String [] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();

        float points[][] = new float[N][2];

        for(int i=0;i<N;i++) {
            points[i][0] = input.nextFloat();
            points[i][1] = input.nextFloat();
        }

        System.out.printf("%.2f\n", minimalDistance(points));
    }
}