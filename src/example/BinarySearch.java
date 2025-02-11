import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BinarySearch {

    public int runBinarySearchIteratively(int[] sortedArray, int key, int low, int high) {

        int index = Integer.MAX_VALUE;

        while (low <= high) {

            int mid = low + ((high - low) / 2);

            if (sortedArray[mid] < key) {
                low = mid + 1;
            } else if (sortedArray[mid] > key) {
                high = mid - 1;
            } else if (sortedArray[mid] == key) {
                index = mid;
                break;
            }
        }
        return index;
    }

    public int runBinarySearchRecursively(int[] sortedArray, int key, int low, int high) {

        int middle = low + ((high - low) / 2);
        if (high < low) {
            return -1;
        }

        if (key == sortedArray[middle]) {
            return middle;
        } else if (key < sortedArray[middle]) {
            return runBinarySearchRecursively(sortedArray, key, low, middle - 1);
        } else {
            return runBinarySearchRecursively(sortedArray, key, middle + 1, high);
        }
    }

    public int runBinarySearchUsingJavaArrays(int[] sortedArray, Integer key) {
        int index = Arrays.binarySearch(sortedArray, key);
        return index;
    }

    public int runBinarySearchUsingJavaCollections(List<Integer> sortedList, Integer key) {
        int index = Collections.binarySearch(sortedList, key);
        return index;
    }

    public List<Integer> runBinarySearchOnSortedArraysWithDuplicates(int[] sortedArray, Integer key) {
        int startIndex = startIndexSearch(sortedArray, key);
        int endIndex = endIndexSearch(sortedArray, key);
        return IntStream.rangeClosed(startIndex, endIndex)
                .boxed()
                .collect(Collectors.toList());
    }

    private int endIndexSearch(int[] sortedArray, int target) {
        int left = 0;
        int right = sortedArray.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sortedArray[mid] == target) {
                result = mid;
                left = mid + 1;
            } else if (sortedArray[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    private int startIndexSearch(int[] sortedArray, int target) {
        int left = 0;
        int right = sortedArray.length - 1;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sortedArray[mid] == target) {
                result = mid;
                right = mid - 1;
            } else if (sortedArray[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinarySearch binarySearch = new BinarySearch();

        // Read the size of the array
        System.out.println("Enter the size of the array:");
        int size = scanner.nextInt();

        // Read the array elements
        int[] sortedArray = new int[size];
        System.out.println("Enter the sorted array elements:");
        for (int i = 0; i < size; i++) {
            sortedArray[i] = scanner.nextInt();
        }

        // Read the key to search
        System.out.println("Enter the key to search:");
        int key = scanner.nextInt();

        // Ask user for the search method
        System.out.println("Choose the search method:");
        System.out.println("1: Iterative Binary Search");
        System.out.println("2: Recursive Binary Search");
        System.out.println("3: Binary Search using Java Arrays");
        System.out.println("4: Binary Search using Java Collections");
        System.out.println("5: Search in sorted array with duplicates");

        int choice = scanner.nextInt();

        // Perform the chosen search method
        switch (choice) {
            case 1:
                int iterativeResult = binarySearch.runBinarySearchIteratively(sortedArray, key, 0, sortedArray.length - 1);
                System.out.println("Iterative Binary Search result: " + (iterativeResult >= 0 ? "Found at index " + iterativeResult : "Not Found"));
                break;

            case 2:
                int recursiveResult = binarySearch.runBinarySearchRecursively(sortedArray, key, 0, sortedArray.length - 1);
                System.out.println("Recursive Binary Search result: " + (recursiveResult >= 0 ? "Found at index " + recursiveResult : "Not Found"));
                break;

            case 3:
                int javaArraysResult = binarySearch.runBinarySearchUsingJavaArrays(sortedArray, key);
                System.out.println("Binary Search using Java Arrays result: " + (javaArraysResult >= 0 ? "Found at index " + javaArraysResult : "Not Found"));
                break;

            case 4:
                // Convert the sorted array to a List for Java Collections binary search
                List<Integer> sortedList = Arrays.stream(sortedArray).boxed().collect(Collectors.toList());
                int javaCollectionsResult = binarySearch.runBinarySearchUsingJavaCollections(sortedList, key);
                System.out.println("Binary Search using Java Collections result: " + (javaCollectionsResult >= 0 ? "Found at index " + javaCollectionsResult : "Not Found"));
                break;

            case 5:
                List<Integer> duplicateIndices = binarySearch.runBinarySearchOnSortedArraysWithDuplicates(sortedArray, key);
                System.out.println("Search in sorted array with duplicates result: " + (duplicateIndices.isEmpty() ? "Not Found" : "Found at indices " + duplicateIndices));
                break;

            default:
                System.out.println("Invalid choice.");
                break;
        }

        scanner.close();
    }
}