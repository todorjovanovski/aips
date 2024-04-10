package lab5.mvrSalter;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Gragjanin {
    private String imePrezime;
    private int licna;
    private int pasos;
    private int vozacka;

    public Gragjanin(String imePrezime, int licna, int pasos, int vozacka) {
        this.imePrezime = imePrezime;
        this.licna = licna;
        this.pasos = pasos;
        this.vozacka = vozacka;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public int getLicna() {
        return licna;
    }

    public int getPasos() {
        return pasos;
    }

    public int getVozacka() {
        return vozacka;
    }
}


public class MVR {

    public static String solve(Gragjanin[] gs, int n) {
        StringBuilder redosled = new StringBuilder();
        Queue<Gragjanin> licna = new LinkedList<>();
        Queue<Gragjanin> pasos = new LinkedList<>();
        Queue<Gragjanin> vozacka = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (gs[i].getLicna() == 1) licna.add(gs[i]);
            else if (gs[i].getPasos() == 1) pasos.add(gs[i]);
            else if (gs[i].getVozacka() == 1) vozacka.add(gs[i]);
        }

        while (!licna.isEmpty()) {
            if (licna.peek().getPasos() == 1) pasos.add(licna.remove());
            else if (licna.peek().getVozacka() == 1) vozacka.add(licna.remove());
            else redosled.append(licna.remove().getImePrezime()).append("\n");
        }
        while (!pasos.isEmpty()) {
            if (pasos.peek().getVozacka() == 1) vozacka.add(pasos.remove());
            else redosled.append(pasos.remove().getImePrezime()).append("\n");
        }
        while (!vozacka.isEmpty()) {
            redosled.append(vozacka.remove().getImePrezime()).append("\n");
        }

        return redosled.toString();
    }

    public static void main(String[] args) {

        Scanner br = new Scanner(System.in);
        int N = Integer.parseInt(br.nextLine());
        Gragjanin[] gragjani = new Gragjanin[N];
        for (int i = 0; i < N; i++) {
            String imePrezime = br.nextLine();
            int lKarta = Integer.parseInt(br.nextLine());
            int pasos = Integer.parseInt(br.nextLine());
            int vozacka = Integer.parseInt(br.nextLine());
            gragjani[i] = new Gragjanin(imePrezime, lKarta, pasos, vozacka);
        }

        System.out.println(solve(gragjani, N));
    }
}