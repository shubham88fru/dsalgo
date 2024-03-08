package ptrn.toposort;

import java.util.*;

//@link - https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/description/
//@check - https://www.educative.io/module/page/Z4JLg2tDQPVv6QjgO/10370001/4976190424350720/4699729134092288
         //https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/solutions/1736422/java-topological-sort-with-comments/
public class FindAllPossibleRecipesFromGivenSupplies {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        //For easier lookup of supplies.
        Set<String> suppl = new HashSet<>();
        for (String supply: supplies) suppl.add(supply);

        //actual graph - nodes will be (only) recipies and ingredients
        //that are not in supplies array.
        Map<String, List<String>> graph = new HashMap<>();

        //to store indegree of each node.
        Map<String, Integer> indegrees = new HashMap<>();

        //Create the graph.
        for (int i=0; i<recipes.length; i++) {
            //start with an indegree of zero.
            indegrees.put(recipes[i], 0);
            //For each recipe, check its ingredients and
            //for the graph.
            for (String ingredient: ingredients.get(i)) {
                //If an ingredient is from supply, we have
                //infinite quantity of it and its as good as no
                //dependency.
                if (suppl.contains(ingredient)) continue;

                //Otherwise, create an edge, such that the ingredient
                //is a necessity for the current recipe.
                graph.putIfAbsent(ingredient, new ArrayList<>());
                graph.get(ingredient).add(recipes[i]);
                indegrees.put(recipes[i], indegrees.getOrDefault(recipes[i],0)+1);
            }
        }

        //Typical Kahn's alogrithm for topo sort.
        Deque<String> q = new ArrayDeque<String>();
        //Add all recipies with indegree 0. Note that
        //here, even those recipes which had only `supply` in
        //their ingredients will be added because their indegree was set to 0.
        for (String recipe: recipes) {
            if (indegrees.get(recipe) == 0) q.addLast(recipe);
        }

        List<String> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            String recipe = q.removeFirst();
            ans.add(recipe);

            //If a recipe is not the part of graph,
            //(likely because it was skipped while forming the
            //graph because its ingredients were only supply),
            //we don't need to do anything.
            if (!graph.containsKey(recipe)) continue;

            List<String> neighbours = graph.get(recipe);
            for (String neighbour: neighbours) {
                indegrees.put(neighbour, indegrees.get(neighbour)-1);
                if (indegrees.get(neighbour) == 0) q.addLast(neighbour);
            }
        }

        return ans;
    }
}
