package kolok1._6_cardTrick;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class card_trick {
    public static int count(int N){
        // Vasiot kod tuka
        Queue<Integer> deck = new LinkedList<>();

        for(int i=1; i<=51; i++) {
            deck.add(i);
        }

        int countShuffles = 0;
        while (deck.peek() != N) {
            Stack<Integer> seven = new Stack<>();
            for(int i=0; i<7; i++) {
                seven.push(deck.remove());
            }
            for(int i=0; i<7; i++) {
                deck.add(seven.pop());
                deck.add(deck.remove());
            }
            countShuffles++;
        }

        return countShuffles;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
        System.out.println(count(Integer.parseInt(br.readLine())));
    }

}