import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class DijkstraMaina {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a new graph
        GraphDij graph = new GraphDij();

        // Input the number of nodes
        System.out.println("Enter the number of nodes:");
        int numberOfNodes = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Create nodes
        Map<String, NodeDij> nodeMap = new HashMap<>();
        for (int i = 0; i < numberOfNodes; i++) {
            System.out.println("Enter the name of node " + (i + 1) + ":");
            String nodeName = scanner.nextLine();
            NodeDij node = new NodeDij(nodeName);
            nodeMap.put(nodeName, node);
            graph.addNode(node);
        }

        // Input the number of edges
        System.out.println("Enter the number of edges:");
        int numberOfEdges = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Create edges
        for (int i = 0; i < numberOfEdges; i++) {
            System.out.println("Enter the source node, destination node, and distance (separated by space) for edge " + (i + 1) + ":");
            String[] edgeInput = scanner.nextLine().split(" ");
            String sourceName = edgeInput[0];
            String destinationName = edgeInput[1];
            int distance = Integer.parseInt(edgeInput[2]);

            NodeDij sourceNode = nodeMap.get(sourceName);
            NodeDij destinationNode = nodeMap.get(destinationName);
            sourceNode.addDestination(destinationNode, distance);
        }

        // Input the source node for Dijkstra's algorithm
        System.out.println("Enter the source node for finding the shortest path:");
        String sourceNodeName = scanner.nextLine();
        NodeDij sourceNode = nodeMap.get(sourceNodeName);

        // Calculate the shortest path
        graph = Dijkstra.calculateShortestPathFromSource(graph, sourceNode);

        // Output the shortest paths
        System.out.println("Shortest paths from node " + sourceNodeName + ":");
        for (NodeDij node : graph.getNodes()) {
            System.out.print("Node " + node.getName() + ": ");
            if (node.getDistance() == Integer.MAX_VALUE) {
                System.out.println("No path");
            } else {
                System.out.println("Distance = " + node.getDistance() + ", Path = " + formatPath(node.getShortestPath()));
            }
        }
    }

    private static String formatPath(List<NodeDij> path) {
        StringBuilder sb = new StringBuilder();
        for (NodeDij node : path) {
            sb.append(node.getName()).append(" -> ");
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 4) : "Direct Source";
    }
}
