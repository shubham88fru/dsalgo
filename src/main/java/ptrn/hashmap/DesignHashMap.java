package ptrn.hashmap;

import java.util.ArrayList;
import java.util.Arrays;

public class DesignHashMap { }

/*********************************************************************/
//1) Edctv soln. Handle collision through chaining.
// Define DesignHashMap class to implement the HashMap functionality
class DesignHashMapEdctv {
    public int keySpace;
    public Bucket[] buckets;

    // Constructor to initialize the HashMap
    public DesignHashMapEdctv() {
        keySpace = 2069;
        buckets = new Bucket[keySpace];
        for (int i = 0; i < keySpace; i++) {
            buckets[i] = new Bucket();
        }
    }

    // Function to add a key-value pair to the hash map
    public void put(int key, int value) {
        int hashKey = key % keySpace;
        buckets[hashKey].update(key, value);
    }

    // Function to retrieve the value associated with a given key from the hash map
    public int get(int key) {
        int hashKey = key % keySpace;
        return buckets[hashKey].get(key);
    }

    // Function to remove a key-value pair from the hash map given a key
    public void remove(int key) {
        int hashKey = key % keySpace;
        buckets[hashKey].remove(key);
    }
}

class Bucket {
    private ArrayList<Pair<Integer, Integer>> bucket;

    public Bucket() {
        // Constructor to initialize an empty list to store key-value pairs
        bucket = new ArrayList<>();
    }

    public int get(int key) {
        // Iterate through each key-value pair in the bucket
        for (Pair<Integer, Integer> kv : bucket) {
            // If the key matches the provided key, return the corresponding value
            if (kv.getKey() == key) {
                return kv.getValue();
            }
        }
        // If the key is not found, return -1
        return -1;
    }

    public void update(int key, int value) {
        // Flag to indicate whether the key is found in the bucket
        boolean found = false;
        // Iterate through each key-value pair in the bucket
        for (int i = 0; i < bucket.size(); i++) {
            Pair<Integer, Integer> kv = bucket.get(i);
            // If the key matches the key of the current key-value pair
            if (key == kv.getKey()) {
                // Update the value of the key-value pair
                bucket.set(i, new Pair<>(key, value));
                // Set the flag to true, indicating that the key is found
                found = true;
                break;
            }
        }
        // If the key is not found in the bucket, add it along with its value
        if (!found) {
            bucket.add(new Pair<>(key, value));
        }
    }

    public void remove(int key) {
        // Iterate through each key-value pair in the bucket
        for (int i = 0; i < bucket.size(); i++) {
            Pair<Integer, Integer> kv = bucket.get(i);
            // If the key matches the key of the current key-value pair
            if (key == kv.getKey()) {
                // Delete the key-value pair from the bucket
                bucket.remove(i);
                // Exit the loop as the key has been removed
                break;
            }
        }
    }
}

// Define Pair class to store key-value pairs
class Pair<K, V> {
    private K key;
    private V value;

    // Constructor to initialize key-value pair
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    // Getter methods for key and value
    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
/*********************************************************************/

/*********************************************************************/
//2) My Naive soln.
class MyHashMap {
    int[] map = new int[1000001];
    public MyHashMap() {
        Arrays.fill(map, -1);
    }

    public void put(int key, int value) {
        map[key] = value;
    }

    public int get(int key) {
        return map[key];
    }

    public void remove(int key) {
        map[key] = -1;
    }
}
/*********************************************************************/