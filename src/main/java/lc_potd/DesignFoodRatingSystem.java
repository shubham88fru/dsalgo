package lc_potd;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//@link - https://leetcode.com/problems/design-a-food-rating-system/?
public class DesignFoodRatingSystem {
    Map<String, FoodItem> foodMap = new HashMap<>();
    Map<String, PriorityQueue<FoodItem>> cuisineMap = new HashMap<>();

    public DesignFoodRatingSystem(String[] foods, String[] cuisines, int[] ratings) {
        int n = foods.length;
        for (int i=0; i<n; i++) {
            FoodItem item = new FoodItem(ratings[i], foods[i], cuisines[i], false);
            foodMap.put(foods[i], item);

            if (!cuisineMap.containsKey(cuisines[i])) {
                Comparator<FoodItem> cmp1 = (f1, f2) -> f2.rating - f1.rating;
                Comparator<FoodItem> cmp2 = (f1, f2) -> f1.food.compareTo(f2.food);
                PriorityQueue<FoodItem> pq = new PriorityQueue<>(cmp1.thenComparing(cmp2));
                cuisineMap.put(cuisines[i], pq);
            }
            cuisineMap.get(cuisines[i]).add(item);
        }
    }

    public void changeRating(String food, int newRating) {
        foodMap.get(food).stale = true;
        String cuisine = foodMap.get(food).cuisine;
        FoodItem item = new FoodItem(newRating, food, cuisine, false);
        foodMap.put(food, item);
        cuisineMap.get(cuisine).add(item);
    }

    public String highestRated(String cuisine) {
        PriorityQueue<FoodItem> pq = cuisineMap.get(cuisine);
        while (!pq.isEmpty()) {
            FoodItem item = pq.peek();
            if (item.stale) pq.remove();
            else return item.food;
        }
        return null;
    }
}

class FoodItem {
    int rating;
    String food;
    String cuisine;
    boolean stale; //pro tip

    public FoodItem(int rating, String food, String cuisine, boolean stale) {
        this.rating = rating;
        this.food = food;
        this.cuisine = cuisine;
        this.stale = stale;
    }
}
