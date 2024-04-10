package lab5.xmlZapis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Stack;

public class CheckXML {

    public static boolean checkTag(String str1, String str2) {
        StringBuilder tag1 = new StringBuilder();
        StringBuilder tag2 = new StringBuilder();
        for(int i=0; i<str1.length(); i++) {
            if(Character.isAlphabetic(str1.charAt(i)))
                tag1.append(str1.charAt(i));
        }
        for(int i=0; i<str2.length(); i++) {
            if(Character.isAlphabetic(str2.charAt(i)))
                tag2.append(str2.charAt(i));
        }

        return tag1.toString().equals(tag2.toString());
    }

    public static int checkXml(String[] rows, int n) {
        Stack<String> strings = new Stack<>();

        for(int i=0; i<n; i++) {
            if (rows[i].charAt(0) == '[' && rows[i].charAt(rows[i].length()-1) == ']' && rows[i].charAt(1) != '/')
                strings.push(rows[i]);
            else if (rows[i].charAt(0) == '[' && rows[i].charAt(rows[i].length()-1) == ']' && rows[i].charAt(1) == '/') {
                if (strings.empty()) return 0;
                else if (!checkTag(strings.peek(), rows[i])) return 0;
                else strings.pop();
            }
        }

        if (!strings.empty()) return 0;
        else return 1;
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String [] redovi = new String[n];

        for(int i=0;i<n;i++)
            redovi[i] = br.readLine();

        int valid;

        // Vasiot kod tuka
        // Moze da koristite dopolnitelni funkcii ako vi se potrebni
        valid = checkXml(redovi, n);

        System.out.println(valid);

        br.close();
    }
}