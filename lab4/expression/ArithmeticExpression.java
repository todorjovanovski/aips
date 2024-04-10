package lab4.expression;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    static int presmetaj(char[] c, int l, int r) {
        // Vasiot kod tuka
        ArrayList<Character> characters = new ArrayList<>();
        ArrayList<Character> operators = new ArrayList<>();
        ArrayList<Integer> brackets = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            if (Character.isDigit(c[i])) {
                characters.add(c[i]);
            } else if (c[i] == '+' || c[i] == '-') {
                operators.add(c[i]);
                int check = i, count = 0;
                while(c[++check] == '(') count++;
                brackets.add(count);
            }
        }

        int b = -1, j = 0, result = 0, first = 0;
        for (int i = 1; i < characters.size(); i++) {
            int curr = 0;
            if (operators.get(j) == '+')
                curr =  Integer.parseInt(String.valueOf(characters.get(i - 1))) +
                        Integer.parseInt(String.valueOf(characters.get(i)));
            else if (operators.get(j) == '-')
                curr =  Integer.parseInt(String.valueOf(characters.get(i - 1))) -
                        Integer.parseInt(String.valueOf(characters.get(i)));

            if (first == 0) {
                result += curr;
                first = 1;
            } else if (operators.get(j - 1) == '+') {
                result += curr;
            } else if (operators.get(j - 1) == '-') {
                int k = 1, br = brackets.get(b);
                while (br > 1 && j + k < operators.size()) {
                    if(operators.get(j+k) == '-') operators.set(j+k, '+');
                    else if(operators.get(j+k) == '+') operators.set(j+k, '-');
                    k += 2;
                    br--;
                }
                result -= curr;
            }
            i++;
            j += 2;
            b += 2;
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length - 1);
        System.out.println(rez);

        br.close();

    }

}
