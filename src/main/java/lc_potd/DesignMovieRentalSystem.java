package lc_potd;

import java.util.*;

//@link - https://leetcode.com/problems/design-movie-rental-system/?
//@check - https://www.youtube.com/watch?v=17UaJS5dYVc&t=11s
public class DesignMovieRentalSystem {
    /**
     I was on the right track, but my Initial intuition was to use
     priority queue, which was getting pretty complicated.
     Below is still the similar approach which I was trying
     but slightly more straightforward and easier.
     Completely coded by me but with slight hints from mik.
     */

    Comparator<Movie> cmp1 = (m1, m2) -> m1.price - m2.price;
    Comparator<Movie> cmp2 = (m1, m2) -> m1.shop - m2.shop;
    Comparator<Movie> cmp3 = (m1, m2) -> m1.movie - m2.movie;
    Comparator<Movie> combined = cmp1.thenComparing(cmp2).thenComparing(cmp3);

    Map<Integer, TreeSet<Movie>> moviesMap = new HashMap<>();
    Map<Integer, Map<Integer, Movie>> shopsMap = new HashMap<>();
    TreeSet<Movie> rented = new TreeSet<>(combined);

    public DesignMovieRentalSystem(int n, int[][] entries) {
        for (int[] entry: entries) {
            int shop = entry[0];
            int movie = entry[1];
            int price = entry[2];

            Movie m = new Movie(shop, movie, price);
            if (!moviesMap.containsKey(movie)) {
                moviesMap.put(movie, new TreeSet<>(cmp1.thenComparing(cmp2)));
            }
            moviesMap.get(movie).add(m);

            if (!shopsMap.containsKey(shop)) {
                shopsMap.put(shop, new HashMap<>());
            }
            shopsMap.get(shop).put(movie, m);
        }
    }

    public List<Integer> search(int movie) {
        List<Integer> ans = new ArrayList<>();
        if (moviesMap.containsKey(movie)) {
            for (Movie m: moviesMap.get(movie)) {
                ans.add(m.shop);
                if (ans.size() >= 5) return ans;
            }
        }

        return ans;
    }

    public void rent(int shop, int movie) {
        Movie m = shopsMap.get(shop).get(movie);
        rented.add(m);
        moviesMap.get(movie).remove(m);
    }

    public void drop(int shop, int movie) {
        Movie m = shopsMap.get(shop).get(movie);
        rented.remove(m);
        moviesMap.get(movie).add(m);
    }

    public List<List<Integer>> report() {
        List<List<Integer>> ans = new ArrayList<>();

        for (Movie m: rented) {
            List<Integer> pair = new ArrayList<>();
            pair.add(m.shop);
            pair.add(m.movie);
            ans.add(pair);

            if (ans.size() >= 5) return ans;
        }

        return ans;
    }
}

class Movie {
    int movie;
    int shop;
    int price;

    public Movie(int shop, int movie, int price) {
        this.movie = movie;
        this.shop = shop;
        this.price = price;
    }

    public String toString() {
        return "[" + shop + ", " + movie + ", " + price + "]";
    }
}
