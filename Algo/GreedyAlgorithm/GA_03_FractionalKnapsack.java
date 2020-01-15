import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/*
https://www.geeksforgeeks.org/fractional-knapsack-problem/
- Given weights and values of n items, we need to put these items in a knapsack of capacity W to get the 
    maximum total value in the knapsack.
- In Fractional Knapsack, we can break items for maximizing the total value of knapsack. (In the 0-1 Knapsack 
    problem, we are not allowed to break items. We either take the whole item or don’t take it.)
- The basic idea of the greedy approach is to calculate the ratio value/weight for each item and sort the item 
    on basis of this ratio. Then take the item with the highest ratio and add them until we can’t add the next 
    item as a whole and at the end add the next item as much as we can. Which will always be the optimal solution 
    to this problem.
*/
class GA_03_FractionalKnapsack {
    static class KnapsackItem {
        private int index;
        private int weight;
        private int value;
        private double valuePerWeight;

        KnapsackItem(int index, int weight, int value) {
            this.index = index;
            this.weight = weight;
            this.value = value;
            this.valuePerWeight = value / weight;
        }
        public int getIndex() {
            return index;
        }
        public int getWeight() {
            return weight;
        }
        public int getValue() {
            return value;
        }
        public double getValuePerWeight() {
            return valuePerWeight;
        }
        @Override
        public String toString() {
            return "index: " + index + ", weight: " + weight + ", value: " + value;
        }
    }
    static class KnapsackItemWithFraction {
        private KnapsackItem knapsackItem;
        private double fraction;

        KnapsackItemWithFraction(KnapsackItem knapsackItem, double fraction) {
            this.knapsackItem = knapsackItem;
            this.fraction = fraction;
        }
        public KnapsackItem getKnapsackItem() {
            return knapsackItem;
        }
        public double getFraction() {
            return fraction;
        }
        @Override
        public String toString() {
            return "{ item: [" + knapsackItem + "], fraction: " + fraction + " }";
        }
    }
    static void fractionalKnapSack(int [] weight, int [] value, int capacity) {

        // Preparing an List
        List<KnapsackItem> knapsackItems = new ArrayList<>();
        for (int i = 0; i < weight.length; i++) {
            knapsackItems.add(new KnapsackItem(i, weight[i], value[i]));
        }

        // Sorting the List
        Collections.sort(knapsackItems, (item1, item2) -> {
            double difference = item1.getValuePerWeight() - item2.getValuePerWeight();
            if (difference == 0.0) {
                return 0;
            } else if (difference > 0.0) {
                return 1;
            } else {
                return -1;
            }
        });

        // Get the list of items selected to be put in Knapsack
        List<KnapsackItemWithFraction> selectedItems = new ArrayList<>();

        for (int index = knapsackItems.size() - 1; index >= 0; index--) {
            KnapsackItem currentItem = knapsackItems.get(index);
            if (currentItem.getWeight() <= capacity) {
                capacity = capacity - currentItem.getWeight();
                selectedItems.add(new KnapsackItemWithFraction(currentItem, 1));
            } else {
                double fraction = (double) capacity / (double) currentItem.getWeight();
                selectedItems.add(new KnapsackItemWithFraction(currentItem, fraction));
                break;
            }
        }

        // Display
        System.out.println("Chosen Items:\n");
        Iterator<KnapsackItemWithFraction> it = selectedItems.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
    public static void main(String[] args) {
        int[] wt = {10, 40, 20, 30}; 
        int[] val = {60, 40, 100, 120}; 
        int capacity = 50; 
  
        fractionalKnapSack(wt, val, capacity); 
    }
}