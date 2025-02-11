import java.util.*;

import java.util.Collections;

class BreadthFirstSearchtest {
    public static void main(String[] args) {
        // Create and populate Tree structure
        Tree<Integer> root = Tree.of(0);
        Tree<Integer> node1 = root.addChild(3);
        Tree<Integer> node2 = node1.addChild(2);
        node2.addChild(-1);
        Tree<Integer> node3 = node1.addChild(6);
        node3.addChild(5);
        node3.addChild(4);
        Tree<Integer> node4 = node3.addChild(-3);
        node4.addChild(3);
        node4.addChild(7);
        node4.addChild(2);
        node4.addChild(3);

        // Perform BFS search on Tree
        System.out.println("Tree BFS Search:");
        Tree<Integer> treeResult = BreadthFirstSearch.search(3, root).get();
        if (treeResult != null) {
            System.out.println("Found node with value: " + treeResult.getValue());
        } else {
            System.out.println("Node not found");
        }

        // Create and populate Graph structure
        cfsNode<Integer> graphRoot = new cfsNode<>(0);
        cfsNode<Integer> graphNode1 = new cfsNode<>(1);
        cfsNode<Integer> graphNode2 = new cfsNode<>(2);
        cfsNode<Integer> graphNode3 = new cfsNode<>(3);
        cfsNode<Integer> graphNode4 = new cfsNode<>(4);
        cfsNode<Integer> graphNode5 = new cfsNode<>(5);
        cfsNode<Integer> graphNode6 = new cfsNode<>(6);
        cfsNode<Integer> graphNode7 = new cfsNode<>(7);
        cfsNode<Integer> graphNode8 = new cfsNode<>(8);
        cfsNode<Integer> graphNode9 = new cfsNode<>(9);
        cfsNode<Integer> graphNode10 = new cfsNode<>(10);
        cfsNode<Integer> graphNode11 = new cfsNode<>(11);

        graphRoot.connect(graphNode1); //A
        graphNode1.connect(graphNode2); //B
        graphNode2.connect(graphNode3); //C
        graphNode1.connect(graphNode4); //D
        graphNode4.connect(graphNode5); //E
        graphNode4.connect(graphNode6); //F
        graphNode4.connect(graphNode7); //G
        graphNode7.connect(graphNode8); //H
        graphNode7.connect(graphNode9); //I


        // Perform BFS search on Graph
        System.out.println("Graph BFS Search:");
        cfsNode<Integer> graphResult = BreadthFirstSearch.search(7, graphRoot).get();
        if (graphResult != null) {
            System.out.println("Found node with value: " + graphResult.getValue());
        } else {
            System.out.println("Node not found");
        }

        printGraphWithLevels(graphNode1);
    }

    // Method to print the graph structure with levels
    private static void printGraphWithLevels(cfsNode<?> root) {
        if (root == null) {
            System.out.println("Graph is empty.");
            return;
        }

        Map<cfsNode<?>, Integer> levels = new HashMap<>();
        Queue<cfsNode<?>> queue = new ArrayDeque<>();
        queue.add(root);
        levels.put(root, 0);

        while (!queue.isEmpty()) {
            cfsNode<?> current = queue.poll();
            int level = levels.get(current);

            System.out.print("Level " + level + ": Node " + current.getValue() + " -> ");

            List<cfsNode<?>> neighbors = new ArrayList<>(current.getNeighbors());

            // Sort neighbors using an anonymous inner class
            Collections.sort(neighbors, new Comparator<cfsNode<?>>() {
                @Override
                public int compare(cfsNode<?> n1, cfsNode<?> n2) {
                    // Ensure that the values can be compared
                    Comparable<Object> v1 = (Comparable<Object>) n1.getValue();
                    Comparable<Object> v2 = (Comparable<Object>) n2.getValue();
                    return v1.compareTo(v2);
                }
            });

            boolean first = true;
            for (cfsNode<?> neighbor : neighbors) {
                if (first) {
                    first = false;
                } else {
                    System.out.print(", ");
                }
                System.out.print(neighbor.getValue());
                if (!levels.containsKey(neighbor)) {
                    levels.put(neighbor, level + 1);
                    queue.add(neighbor);
                }
            }
            System.out.println();
        }
    }
}


class BreadthFirstSearch {

    public static <T> Optional<Tree<T>> search(T value, Tree<T> root) {
        Queue<Tree<T>> queue = new ArrayDeque<>();
        queue.add(root);

        Tree<T> currentNode;
        while (!queue.isEmpty()) {
            currentNode = queue.remove();
            System.out.println("Visited node with value: {} " + currentNode.getValue());

            if (currentNode.getValue().equals(value)) {
                return Optional.of(currentNode);
            } else {
                queue.addAll(currentNode.getChildren());
            }
        }

        return Optional.empty();
    }

    public static <T> Optional<cfsNode<T>> search(T value, cfsNode<T> start) {
        Queue<cfsNode<T>> queue = new ArrayDeque<>();
        queue.add(start);

        cfsNode<T> currentNode;
        Set<cfsNode<T>> alreadyVisited = new HashSet<>();

        while (!queue.isEmpty()) {
            currentNode = queue.remove();
            System.out.println("Visited node with value: {} " + currentNode.getValue());

            if (currentNode.getValue().equals(value)) {
                return Optional.of(currentNode);
            } else {
                alreadyVisited.add(currentNode);
                queue.addAll(currentNode.getNeighbors());
                queue.removeAll(alreadyVisited);
            }
        }

        return Optional.empty();
    }

}

class Tree<T> {

    private T value;
    private List<Tree<T>> children;

    private Tree(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public static <T> Tree<T> of(T value) {
        return new Tree<>(value);
    }

    public T getValue() {
        return value;
    }

    public List<Tree<T>> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public Tree<T> addChild(T value) {
        Tree<T> newChild = new Tree<>(value);
        children.add(newChild);
        return newChild;
    }
}

class cfsNode<T> {

    private T value;
    private Set<cfsNode<T>> neighbors;

    public cfsNode(T value) {
        this.value = value;
        this.neighbors = new HashSet<>();
    }

    public T getValue() {
        return value;
    }

    public Set<cfsNode<T>> getNeighbors() {
        return Collections.unmodifiableSet(neighbors);
    }

    public void connect(cfsNode<T> node) {
        if (this == node) throw new IllegalArgumentException("Can't connect node to itself");
        this.neighbors.add(node);
        node.neighbors.add(this);
    }

}