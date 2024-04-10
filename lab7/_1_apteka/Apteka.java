package lab7._1_apteka;


import java.util.Hashtable;
import java.util.Scanner;

class MedicineInfo {
    private int isPositive;
    private int price;
    private int supply;

    public MedicineInfo (int isPositive, int price, int supply) {
        this.isPositive = isPositive;
        this.price = price;
        this.supply = supply;
    }

    public int getIsPositive() {
        return isPositive;
    }

    public int getPrice() {
        return price;
    }

    public int getSupply() {
        return supply;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }

    @Override
    public String toString() {
        if (isPositive == 0) {
            return String.format("NEG\n%d\n%d\n", price, supply);
        } else {
            return String.format("POZ\n%d\n%d\n", price, supply);
        }
    }
}

public class Apteka {

    public static void main(String[] args) {
        Hashtable<String, MedicineInfo> meds = new Hashtable<>();

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for(int i=0; i<n; i++) {
            String name = scanner.next();
            int isPositive = scanner.nextInt();
            int price = scanner.nextInt();
            int supply = scanner.nextInt();
            meds.put(name.toUpperCase(), new MedicineInfo(isPositive, price, supply));
        }

        while (scanner.hasNext()) {
            String name = scanner.next().toUpperCase();
            if(name.equals("KRAJ")) {
                break;
            }
            int quantity = scanner.nextInt();
            if(meds.containsKey(name)) {
                if(meds.get(name).getSupply() < quantity)
                    System.out.println(name + "\n" + meds.get(name) + "Nema dovolno lekovi");
                else {
                    System.out.println(name + "\n" + meds.get(name) + "Napravena naracka");
                    meds.get(name).setSupply(meds.get(name).getSupply() - quantity);
                }
            }
            else {
                System.out.println("Nema takov lek");
            }
        }
    }
}
