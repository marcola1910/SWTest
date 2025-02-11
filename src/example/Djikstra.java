import java.util.*;
import java.util.Map.Entry;

class NodeDij {

    private String name;

    private LinkedList<NodeDij> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    private Map<NodeDij, Integer> adjacentNodes = new HashMap<>();

    public NodeDij(String name) {
        this.name = name;
    }

    public void addDestination(NodeDij destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<NodeDij, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<NodeDij, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public List<NodeDij> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<NodeDij> shortestPath) {
        this.shortestPath = shortestPath;
    }

}

class GraphDij {

    private Set<NodeDij> nodes = new HashSet<>();

    void addNode(NodeDij nodeA) {
        nodes.add(nodeA);
    }

    Set<NodeDij> getNodes() {
        return nodes;
    }

    void setNodes(Set<NodeDij> nodes) {
        this.nodes = nodes;
    }
}

class Dijkstra {

    static GraphDij calculateShortestPathFromSource(GraphDij graph, NodeDij source) {

        source.setDistance(0);

        Set<NodeDij> settledNodes = new HashSet<>();
        Set<NodeDij> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            NodeDij currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Entry<NodeDij, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                NodeDij adjacentNode = adjacencyPair.getKey();
                Integer edgeWeigh = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    static void CalculateMinimumDistance(NodeDij evaluationNode, Integer edgeWeigh, NodeDij sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<NodeDij> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    static NodeDij getLowestDistanceNode(Set<NodeDij> unsettledNodes) {
        NodeDij lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (NodeDij node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }


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

//Enter the number of nodes:
//        3
//Enter the name of node 1:
//A
//Enter the name of node 2:
//B
//Enter the name of node 3:
//C
//Enter the number of edges:
//        3
//Enter the source node, destination node, and distance (separated by space) for edge 1:
//A B 10
//Enter the source node, destination node, and distance (separated by space) for edge 2:
//B C 5
//Enter the source node, destination node, and distance (separated by space) for edge 3:
//A C 15
//Enter the source node for finding the shortest path:
//A
//Shortest paths from node A:
//Node A: Distance = 0, Path = Direct Source
//Node B: Distance = 10, Path = A
//Node C: Distance = 15, Path = A -> B
