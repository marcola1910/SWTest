
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

public class TreeSet1 {
    public static void main(String[] args) {

        TreeSet<Integer> treeset = new TreeSet<Integer>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        TreeMap<Integer, Integer> treemap = new TreeMap<Integer,Integer>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        int keys[] = {1,4,2,5,7,3,1};
        int values[] = {2,3,1,5,1,4,1};

        for (int i = 0; i < keys.length; i++) {
            treeset.add(keys[i]);
            treemap.put(keys[i], values[i]);
        }

        if(treeset.contains(1))
            treeset.remove(1);

        for(int key : treeset){
            System.out.print(key + " ");
        }
        System.out.println();

        if(treemap.containsKey(5)){
            treemap.remove(5);
        }

        Iterator<Integer> it = treemap.keySet().iterator();
        while(it.hasNext()){
            Integer key = it.next();
            Integer value = treemap.get(key);

            System.out.print(key + " , " + value +  "\n");
        }
    }
}
