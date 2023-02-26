import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/*You are given a 2D array containing coordinates and height of rectangle such that height[i]=[xi,yi,hi],
where xi the x coordinate of left edge, yi represents x coordinate of right edge of rectangle and hi represents the height of the peaks of each rectangle.
If you want to construct a border line over the peaks of rectangle represented in bar chart, return the key coordinates required to build a border line that contacts the peaks of the given chart.
Note: key points are the left coordinates of shape representing peaks where you need to draw boarder line.
Input: height={{1,4,10},{2,5,15},{5,8,12},{9,11,1},{11,13,15}}
Output: {{1,10},{2,15},{5,12},{8,0},{9,1},{11,15},{13,0}}
*/


class Question5A {
    public int[][] getSkyline(int[][] buildings) {
        int[][] heights = new int[buildings.length * 2][2]; // create a new array to store all the left and right edges of the buildings
        int index = 0;

        // Add the left and right edges of each building to the heights array
        for (int i = 0; i < buildings.length; i++) {
            int[] building = buildings[i];
            heights[index++] = new int[]{building[0], -building[2]}; // left edge with negative height
            heights[index++] = new int[]{building[1], building[2]}; // right edge with positive height
        }

        // Sort the heights array by x-coordinate and then by height
        Arrays.sort(heights, new Comparator<int[]>() {
            public int compare(int[] h1, int[] h2) {
                if (h1[0] != h2[0]) { // sort by x-coordinate
                    return h1[0] - h2[0];
                } else { // if two edges have the same x-coordinate, sort by height
                    return h1[1] - h2[1];
                }
            }
        });

        // Use a priority queue to keep track of the current maximum height
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // create a new priority queue to store heights, sorted in descending order
        pq.offer(0); // Add a zero height to the queue to handle the case when all buildings end

        // Traverse the heights array and update the result array and priority queue as needed
        int prevHeight = 0;
        int[][] result = new int[heights.length][2]; // create a new array to store the key points of the skyline
        index = 0;
        // Traverse the heights array and update the result array and priority queue as needed
        for (int i = 0; i < heights.length; i++) {
            int[] height = heights[i];
            if (height[1] < 0) { // left edge
                pq.offer(-height[1]); // add height to priority queue
            } else { // right edge
                pq.remove(height[1]); // remove height from priority queue
            }

            int currHeight = pq.peek(); // current maximum height

            if (currHeight != prevHeight) { // add key point to result array if the height changes
                result[index++] = new int[]{height[0], currHeight};
                prevHeight = currHeight;
            }
        }

        result = Arrays.copyOf(result, index); // remove empty elements from result array
        return result;


    }

    public static void main(String[] args) {
        int[][] height = {{1,4,10},{2,5,15},{5,8,12},{9,11,1},{11,13,15}}; // create an example input

        Question5A que = new Question5A(); // create a new object

        int[][] result = que.getSkyline(height); // call the getSkyline method to compute the skyline

        System.out.println(Arrays.deepToString(result)); // print the result to the console
    }
}
