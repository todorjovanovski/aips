package lab1.rabotnaNedela;

import java.util.Arrays;
import java.util.Scanner;

class RabotnaNedela {
    private int[] hours;
    private int numberOfWeek;

    public RabotnaNedela(int[] hours, int numberOfWeek) {
        this.hours = hours;
        this.numberOfWeek = numberOfWeek;
    }

    public int[] getHours() {
        return hours;
    }

    public int getNumberOfWeek() {
        return numberOfWeek;
    }

    public int sumHours() {
        int sum = 0;
        for(int i=0; i<5; i++) {
            sum += hours[i];
        }
        return sum;
    }
}

class Rabotnik {
    private String name;
    private RabotnaNedela[] weeks;

    public Rabotnik(String name, RabotnaNedela[] weeks) {
        this.name = name;
        this.weeks = weeks;
    }

    public String getName() {
        return name;
    }

    public RabotnaNedela[] getWeeks() {
        return weeks;
    }

    public int sumWeeks() {
        int sum = 0;
        for(int i=0; i<4; i++) {
            sum += weeks[i].sumHours();
        }
        return sum;
    }

    @Override
    public String toString() {
        String os = name + "   ";
        for(int i=0; i<weeks.length; i++) {
            os += weeks[i].sumHours() + "   ";
        }
        os += sumWeeks();
        return os;
    }
}

public class Main {

    public static Rabotnik najvredenRabotnik(Rabotnik[] niza) {
        Rabotnik max = niza[0];
        for(int i=0; i<niza.length; i++) {
            if(niza[i].sumWeeks() > max.sumWeeks()) {
                max = niza[i];
            }
        }
        return max;
    }

    public static void table(Rabotnik[] niza) {
        System.out.println("Rab" + "   1" + "   2" + "   3" + "   4" + "   Vkupno");
        for(int i=0; i< niza.length; i++) {
            System.out.println(niza[i].toString());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();

        Rabotnik [] niza = new Rabotnik[n];
        //RabotnaNedela [] rn = new RabotnaNedela[4];
        //int[] hours = new int[5];
        String name;

        for(int i=0;i<n;i++)
        {
            RabotnaNedela [] rn = new RabotnaNedela[4];
            name = input.next();
            for(int p=0; p<4; p++) {
                int[] hours = new int[5];
                for(int q = 0; q<5; q++) {
                    hours[q] = input.nextInt();
                }
                rn[p] = new RabotnaNedela(hours, p+1);
            }
            niza[i] = new Rabotnik(name, rn);
        }

        table(niza);
        System.out.println("NAJVREDEN RABOTNIK: " +najvredenRabotnik(niza).getName());



/*
        int niza[] = {35, 33, 42, 42, 40};
        int niza1[] = {31, 57, 52, 54, 44};
        int niza2[] = {56, 32, 51, 52, 35};
        int niza3[] = {43, 39, 34, 54, 55};
        RabotnaNedela[] rn = new RabotnaNedela[4];
        rn[0] = new RabotnaNedela(niza, 1);
        rn[1] = new RabotnaNedela(niza1, 2);
        rn[2] = new RabotnaNedela(niza2, 3);
        rn[3] = new RabotnaNedela(niza3, 4);

        int niza4[] = {1, 2, 3, 4, 5};
        int niza5[] = {1, 1, 1, 1, 1};
        int niza6[] = {2, 2, 2, 2, 2};
        int niza7[] = {3, 3, 3, 3, 3};
        RabotnaNedela[] ab = new RabotnaNedela[4];
        ab[0] = new RabotnaNedela(niza4, 1);
        ab[1] = new RabotnaNedela(niza5, 2);
        ab[2] = new RabotnaNedela(niza6, 3);
        ab[3] = new RabotnaNedela(niza7, 4);

        Rabotnik[] r = new Rabotnik[2];
        r[0] = new Rabotnik("todor", rn);
        r[1] = new Rabotnik("vedran", ab);

        table(r);
*/

    }
}
