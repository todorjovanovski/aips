package kolok1._5_expressionEvaluator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class ExpressionEvaluator {

    public static int evaluateExpression(String expression) {
        // Vasiot kod tuka
        int total = 0;
        int product = 1;

        for (int i = 0; i < expression.length(); i++) {
            String temp = "";
            while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                temp += expression.charAt(i);
                i++;
            }
            int curr = Integer.parseInt(temp);
            if (i >= expression.length()) {
                product *= curr;
                total += product;
                break;
            }

            if (expression.charAt(i) == '*') {
                product *= curr;
            } else if (expression.charAt(i) == '+') {
                product *= curr;
                total += product;
                product = 1;
            }
        }

        return total;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }
}