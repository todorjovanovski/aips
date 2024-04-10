package lab7._3_preveduvac;

import java.util.Scanner;

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

class OBHT<K extends Comparable<K>,E> {
    protected MapEntry<K,E>[] buckets;
    // buckets[b] is null if bucket b has never been occupied.
    // buckets[b] is former if bucket b is formerly-occupied
    // by an entry that has since been deleted (and not yet replaced).
    private final MapEntry<K, E> former = new MapEntry<>(null, null);
    private int occupancy = 0;
    @SuppressWarnings("unchecked")
    public OBHT (int m) {
        // Construct an empty OBHT with m buckets.
        buckets = (MapEntry<K,E>[]) new MapEntry[m];
    }
    private int hash (K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }
    static final int NONE = -1; // ... distinct from any bucket index.
    public int search (K targetKey) {
        // Find which, if any bucket of this OBHT is occupied by an entry whose key
        // is equal to targetKey. Return the index of that bucket.
        int b = hash(targetKey); int n_search=0;
        for (;;) {
            MapEntry<K,E> oldEntry = buckets[b];
            if (oldEntry == null)
                return NONE;
            else if (targetKey.equals(oldEntry.key))
                return b;
            else{ b = (b + 1) % buckets.length;
                n_search++;
                if(n_search==buckets.length) return NONE;
            }
        }
    }

    public void insert (K key, E val) {
        // Insert the entry <key, val> into this OBHT.
        MapEntry<K,E> newEntry = new MapEntry<K,E>(key, val);
        int b = hash(key); int n_search=0;
        for (;;) {
            MapEntry<K,E> oldEntry = buckets[b];
            if (oldEntry == null) {
                if (++occupancy == buckets.length) {
                    System.out.println("Hash tabelata e polna!!!");
                }
                buckets[b] = newEntry;
                return;
            } else if (oldEntry == former
                    || key.equals(oldEntry.key)) {
                buckets[b] = newEntry;
                return;
            }
            else{ b = (b + 1) % buckets.length;
                n_search++;
                if(n_search==buckets.length) return;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void delete (K key) {
        // Delete the entry (if any) whose key is equal to key from this OBHT.
        int b = hash(key); int n_search=0;
        for (;;) {
            MapEntry<K,E> oldEntry = buckets[b];
            if (oldEntry == null)
                return;
            else if (key.equals(oldEntry.key)) {
                buckets[b] = former;//(MapEntry<K,E>)former;
                return;
            }
            else {
                b = (b + 1) % buckets.length;
                n_search++;
                if(n_search==buckets.length) return;
            }
        }
    }

    public OBHT<K,E> clone ()
    {   //creates copy of the OBHT and returns the copy
        OBHT<K,E> copy = new OBHT<K,E>(buckets.length);
        for (int i = 0; i < buckets.length; i++) {
            MapEntry<K,E> e = buckets[i];
            if (e != null && e != former)
                copy.buckets[i] = new MapEntry<K,E>(e.key, e.value);
            else
                copy.buckets[i] = e;
        }
        return copy;
    }
}


public class Preveduvac {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        OBHT<String, String> translator = new OBHT<>((int)(n/0.75));
        for(int i=0; i<n; i++) {
            String mk = scanner.next();
            String en = scanner.next();
            translator.insert(en, mk);
        }

        while(true) {
            String en = scanner.next();
            if(en.equals("KRAJ")) break;
            if(translator.search(en) != OBHT.NONE) {
                System.out.println(translator.buckets[translator.search(en)].value);
            } else {
                System.out.println("/");
            }
        }
    }
}
