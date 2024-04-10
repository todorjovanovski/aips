package lab1.initials;
import java.util.Scanner;

public class Initials {

    static void printInitials(String name)
    {
        String initials = "";
        for(int i=0; i<name.length(); i++) {
            if(i == 0 || name.charAt(i-1) == ' ') {
                initials += name.charAt(i);
            }
        }
        System.out.print(initials.toUpperCase());
    }

    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        int n=input.nextInt();
        String name;
        input.nextLine();

        for(int i=0; i<n; i++){
            name = input.nextLine();
            printInitials(name);
            System.out.println();
        }
    }
}

