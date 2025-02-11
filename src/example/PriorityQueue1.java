
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueue1 {
    public static void main(String[] args) {

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new MyComparator() );

        for (int i = 0; i < 10; i++) {
            priorityQueue.offer(i);
        }
        System.out.println("The head is " + priorityQueue.peek().toString());
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll().toString());
        }

    }



}

class MyComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2)*(-1);
    }

    public MyComparator() {}
}