import java.util.*;

class Question1B {
    public static void main(String[] args) {
        // Define the input data
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 6}, {2, 4}, {4, 6}, {4, 5}, {5, 7}};
        int target = 4;

        // Find the set of nodes directly connected to the target node
        Set<Integer> impacted = findImpactedDevices(edges, target);
        System.out.println("Nodes directly connected to target node " + impacted);

        // Remove the impacted node from the graph and find the connected components
        System.out.println("Nodes set:");
        int[][] newEdges = removeImpactedNode(edges, target);
        List<Set<Integer>> components = findConnectedComponents(newEdges);
        for (int i = 0; i < components.size(); i++) {
            Set<Integer> component = components.get(i);
            System.out.println(component);
        }
    }

    // Method to find the set of nodes directly connected to the target node
    public static Set<Integer> findImpactedDevices(int[][] edges, int target) {
        Set<Integer> visited = new HashSet<>(); // Keep track of visited nodes
        visited.add(target); // Add the target node to the visited set
        Set<Integer> impacted = new HashSet<>(); // Set of impacted nodes
        for (int i = 0; i < edges.length; i++) { // Loop through each edge
            int[] edge = edges[i];
            if (edge[0] == target && !visited.contains(edge[1])) { // If edge starts at target node and end node is not visited
                impacted.add(edge[1]); // Add the end node to impacted set
            } else if (edge[1] == target && !visited.contains(edge[0])) { // If edge ends at target node and start node is not visited
                impacted.add(edge[0]); // Add the start node to impacted set
            }
        }
        return impacted; // Return the set of impacted nodes
    }

    // Method to remove the impacted node from the graph
    public static int[][] removeImpactedNode(int[][] edges, int target) {
        List<int[]> newEdges = new ArrayList<>(); // List to store new edges after removing impacted node
        for (int i = 0; i < edges.length; i++) { // Loop through each edge
            int[] edge = edges[i];
            if (edge[0] != target && edge[1] != target) { // If edge does not involve the impacted node
                newEdges.add(edge); // Add the edge to newEdges list
            }
        }
        int[][] result = new int[newEdges.size()][2]; // Initialize 2D array to store new edges
        for (int i = 0; i < newEdges.size(); i++) {
            result[i] = newEdges.get(i); // Copy edges from the list to the 2D array
        }
        return result; // Return the new edges
    }

    // Method to find the connected components of the graph
    public static List<Set<Integer>> findConnectedComponents(int[][] edges) {
        Map<Integer, Set<Integer>> graph = buildGraph(edges); // Build the graph
        List<Set<Integer>> components = new ArrayList<>(); // List to store connected components
        Set<Integer> visited = new HashSet<>(); // Keep track of visited nodes
        for (int i = 0; i < graph.size(); i++) { // Loop through each node in the graph
            int node = (int) graph.keySet().toArray()[i];
            if (!visited.contains(node)) { // If the node has not been visited yet
                Set<Integer> component = new HashSet<>(); // Initialize new connected component set
                dfs(node, graph, visited, component); // Traverse the graph using dfs
                components.add(component); // Add component to the list of components
            }
        }
        return components; // Return the list of connected components
    }

    public static Map<Integer, Set<Integer>> buildGraph(int[][] edges) {
        // Create an empty map to store the graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        // Loop through each edge in the input array
        for (int i = 0; i < edges.length; i++) {
            // Get the start and end nodes of the edge
            int u = edges[i][0];
            int v = edges[i][1];
            // Add the start and end nodes to the graph if they don't exist
            graph.putIfAbsent(u, new HashSet<>());
            graph.putIfAbsent(v, new HashSet<>());
            // Add the end node as a neighbor of the start node, and vice versa
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        // Return the completed graph
        return graph;
    }

    public static void dfs(int node, Map<Integer, Set<Integer>> graph, Set<Integer> visited, Set<Integer> component) {
        // Mark the current node as visited and add it to the current connected component
        visited.add(node);
        component.add(node);
        // Loop through the neighbors of the current node
        Set<Integer> neighbors = graph.get(node);
        for (Iterator<Integer> it = neighbors.iterator(); it.hasNext(); ) {
            // Get the next neighbor
            int neighbor = it.next();
            // If the neighbor has not been visited, recursively call dfs on it
            if (!visited.contains(neighbor)) {
                dfs(neighbor, graph, visited, component);
            }
        }
    }
}