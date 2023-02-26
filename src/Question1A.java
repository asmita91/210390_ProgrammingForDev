import java.util.*;

/*There are n nations linked by train routes.
You are given a 2D array indicating routes between countries and the time required to
reach the target country, such that E[i]=[xi,yi,ki], where xi represents the source country,
yi represents the destination country, and ki represents the time required to go from xi to yi.
If you are also given information on the charges, you must pay while entering any country.
Create an algorithm that returns the cheapest route from county A to county B with a time constraint.*/
/*Input: edge= {{0,1,5}, {0,3,2}, {1,2,5}, {3,4,5}, {4,5,6}, {2,5,5}}
Charges = {10,2,3,25,25,4}
Source: 0
Destination: 5
Output: 64
Time Constraint=14 min
Note: the path 0, 3, 4, 5 will take minimum time i.e., 13 minutes and which costs around $64.
We cannot take path 0,1,2,5 as it takes 15 min and violates time constraint which in 14 min.
*/// Import the necessary Java classes
import java.util.*;

// Define the class Question1A
class Question1A {
    // Define the Edge class to represent a route between two nodes in the graph
    static class Edge {
        int src, dest, cost;
        Edge(int src, int dest, int cost) {
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }
    }

    // Define the method to find the cheapest route from the source node to the destination node
    public static int findCheapestRoute(int[][] edges, int[] charges, int source, int destination, int timeConstraint) {
        // Construct the graph using an adjacency list
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            int cost = edge[2];
            graph.computeIfAbsent(src, k -> new ArrayList<>()).add(new Edge(src, dest, cost));
        }

        // Initialize the priority queue and the visited set
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // [node, time]
        Set<Integer> visited = new HashSet<>();

        // Add the source node to the priority queue
        pq.offer(new int[]{source, 0, charges[source]}); // [node, time, cost]

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int time = curr[1];
            int cost = curr[2];

            // If the destination node is reached, return the cost
            if (node == destination) {
                return cost;
            }

            // Mark the current node as visited
            visited.add(node);

            // Explore the neighboring nodes
            if (graph.containsKey(node)) {
                for (Edge edge : graph.get(node)) {
                    int nextNode = edge.dest;
                    int nextTime = time + edge.cost;
                    int nextCost = cost + charges[nextNode];

                    // Check if the next node can be visited within the time constraint and has not been visited before
                    if (nextTime <= timeConstraint && !visited.contains(nextNode)) {
                        pq.offer(new int[]{nextNode, nextTime, nextCost});
                    }
                }
            }
        }

        // If the destination node is not reachable, return -1
        return -1;
    }

    // Define the main method to test the program
    public static void main(String[] args) {
        // Define the input parameters
        int[][] edges = {{0,1,5}, {0,3,2}, {1,2,5}, {3,4,5}, {4,5,6}, {2,5,5}};
        int[] charges = {10,2,3,25,25,4};
        int source = 0;
        int destination = 5;
        int timeConstraint = 14;

        // Find the cheapest route and print the result
        int cheapestCost = findCheapestRoute(edges, charges, source, destination, timeConstraint);
        System.out.println("Cheapest route cost: " + cheapestCost);
    }
}