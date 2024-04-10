package lab7._4_rutirackaTabela;


/*class IP {
    private String address;

    public IP(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public
}*//*

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

*//*class SubNet {
    private String address;

    public SubNet(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}*//*

class Hosts {
    private String address;

    public Hosts(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public boolean contains(String ip) {
        String[] subnet = address.split("\\.");
        String[] input = ip.split(",");
        List<String> list = new ArrayList<>();



        for(int i=0; i<subnet.length - 1; i++) {
            if(!subnet[i].equals(input[i])) return false;
        }
        return Integer.parseInt(input[3]) >= 1 && Integer.parseInt(input[3]) <= 254;
    }
}

public class RoutingHashJava {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        Hashtable<String, Hosts> routing = new Hashtable<>();

        for(int i=0; i<n; i++) {
            String sub = scanner.next();
            String hosts = scanner.next();
            routing.put(sub, new Hosts(hosts));
        }

        n = scanner.nextInt();
        for(int i=0; i<n; i++) {
            String sub = scanner.next();
            String host = scanner.next();
            if(routing.containsKey(sub)) {
                if(routing.get(sub).contains(host))
                    System.out.println("postoi");
                else
                    System.out.println("ne postoi");
            } else {
                System.out.println("ne postoi");
            }
        }
    }

}*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    // Each MapEntry object is a pair consisting of a key (a Comparable
    // object) and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo (K that) {
        // Compare this map entry to that map entry.
        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString () {
        return "<" + key + "," + value + ">";
    }
}
class CBHT<K extends Comparable<K>, E> {

    // An object of class CBHT is a closed-bucket hash table, containing
// entries of class MapEntry.
    private final SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
// Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
// Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
// Find which if any node of this CBHT contains an entry whose key is
// equal
// to targetKey. Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) { // Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
// Make newEntry replace the existing entry ...
                curr.element = newEntry;
                return;
            }
        }
// Insert newEntry at the front of the 1WLL in bucket b ...
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
// Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K,E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < buckets.length; i++) {
            temp.append(i).append(":");
            for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp.append(curr.element.toString()).append(" ");
            }
            temp.append("\n");
        }
        return temp.toString();
    }

}

public class RoutingHashJava {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        CBHT<String,String> table = new CBHT<>(2*N);
        for(int i=1;i<=N;i++){
            String ruter = br.readLine();
            String mreza = br.readLine();
            table.insert(ruter,mreza);
        }

        int M = Integer.parseInt(br.readLine());
        while(M>0)
        {
            String ruter = br.readLine();
            String mreza = br.readLine();
            String[] pom = mreza.split("\\.");
            SLLNode<MapEntry<String,String>> curr = table.search(ruter);
            if(curr==null)
            {
                System.out.println("ne postoi");
            }
            else
            {
                String [] value=curr.element.value.split(",");
                boolean flag=false;
                for (String s : value) {
                    String[] value1 = s.split("\\.");
                    if (value1[0].equals(pom[0]) && value1[1].equals(pom[1]) && value1[2].equals(pom[2])) {
                        System.out.println("postoi");
                        flag = true;
                        break;
                    }

                }
                if(!flag)
                    System.out.println("ne postoi");
            }
            M--;
        }
    }

}

