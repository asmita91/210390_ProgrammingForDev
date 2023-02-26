import java.util.ArrayList;

/*Assume an electric vehicle must go from source city s to destination city d.
You can locate many service centers along the journey that allow for
the replacement of batteries; however, each service center provides batteries with a specific capacity.
You are given a 2D array in which servicecenter[i]=[xi,yj] indicates that the ith service center is xi miles from the source city and offers yj miles after the automobile can travel after replacing batteries at specific service centers.
Return the number of times the car's batteries need to be replaced before arriving at the destination.
Input: serviceCenters = [{10,60},{20,30},{30,30},{60,40}], targetMiles= 100, startChargeCapacity = 10
Output: 2
Explanation: The car can go 10 miles on its initial capacity; after 10 miles, the car replaces batteries with a capacity of 60 miles; and after travelling 50 miles, at position 60 we change batteries with a capacity of 40 miles; and ultimately, we may arrive at our destination.
*/

public class Question5B {
    public int numBatteryReplacements(int[][] serviceCenters, int targetMiles, int startChargeCapacity) {
        // Initialize variables
        int count = 0;                          // Counter for number of battery replacements
        int currentMiles = startChargeCapacity;  // Current miles that the car can travel on its battery charge
        ArrayList<Integer> distances = new ArrayList<>();  // List of distances between service centers
        ArrayList<Integer> capacities = new ArrayList<>(); // List of battery capacities at each service center

        // Extract distances and capacities from the serviceCenters 2D array and add them to the ArrayLists
        for (int i = 0; i < serviceCenters.length; i++) {
            //adds the distance value of the current service center to the distances ArrayList.
            distances.add(serviceCenters[i][0]);
            //adds the capacity value of the current service center to the capacities ArrayList.
            capacities.add(serviceCenters[i][1]);
        }

        // Loop through each service center and check if the car can reach it on its current battery charge
        for (int i = 0; i < distances.size(); i++) {
            // If the distance is greater than currentMiles
            if (distances.get(i) > currentMiles) {
                // Set currentMiles to the previous service center's battery capacity
                currentMiles = capacities.get(i - 1);
                // Increment the battery replacement counter
                count++;
            }
        }

        // If the car can't reach the target distance on its current battery charge, it needs one more battery replacement
        if (currentMiles < targetMiles) {
            count++;
        }

        // Return the number of battery replacements required
        return count;
    }

    public static void main(String[] args) {
        // providing input values for serviceCenterList
        int[][] serviceCenterList = {{10,60},{20,30},{30,30},{60,40}};
        // providing targetMiles
        int targetMiles = 100;
        // providing startChargeCapacity
        int startChargeCapacity = 10;

        // Call the numBatteryReplacements method with the input values
        Question5B question5 = new Question5B();
        int finalAnswer = question5.numBatteryReplacements(serviceCenterList, targetMiles, startChargeCapacity);

        // Print the result in the console
        System.out.println("The car's batteries need to be replaced: " + finalAnswer + " times.");
    }
}
