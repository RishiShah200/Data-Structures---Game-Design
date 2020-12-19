import java.util.*;

public class Sets {

    public Sets() {

        //Task 1
        ArrayList<HashSet<Integer>> list = new ArrayList<HashSet<Integer>>();

        int rand = (int) (Math.random() * 12) + 2;
        for (int i = 0; i < rand; i++) {
            HashSet<Integer> set = new HashSet<Integer>();
            for (int k = 0; k < 20; k++) {
                set.add((int) (Math.random() * 30) + 1);
                if (set.size() == 10)
                    k = 20;
            }
            list.add(set);
        }

        System.out.println("List:" + list);
        //Task 2
        HashSet<Integer> inter1 = new HashSet<Integer>();
        inter1.add(2);
        inter1.add(3);
        inter1.add(4);
        inter1.add(9);
        inter1.add(10);
        inter1.add(12);

        HashSet<Integer> inter2 = new HashSet<Integer>();
        inter2.add(2);
        inter2.add(3);
        inter2.add(9);
        inter2.add(5);
        inter2.add(3);
        inter2.add(8);

        System.out.println("Intersection: " + intersection(inter1, inter2));
        System.out.println("Union: " + union(inter1, inter2));
        System.out.println("Even Intersection: " + evenIntersection(inter1, inter2));
        System.out.println("Even Union: " + evenIntersection(inter1, inter2));


    }

    public Set<Integer> intersection(Set<Integer> h1, Set<Integer> h2) {
        Set<Integer> set = new TreeSet<Integer>();
        set.addAll(h1);
        set.retainAll(h2);
        return set;
    }

    public Set<Integer> union(Set<Integer> h1, Set<Integer> h2) {
        Set<Integer> set = new TreeSet<Integer>();
        set.addAll(h1);
        set.addAll(h2);

        return set;
    }

    public Set<Integer> evenIntersection(Set<Integer> h1, Set<Integer> h2) {
        Set<Integer> set = intersection(h1, h2);
        Iterator<Integer> i1 = set.iterator();
        for (int x = 0; x < set.size(); x++) {
            int temp = i1.next();
            if (temp % 2 == 1) {
                i1.remove();
                x--;
            }
        }
        return set;
    }

    public Set<Integer> evenUnion(Set<Integer> h1, Set<Integer> h2) {
        Set<Integer> set = union(h1, h2);
        Iterator<Integer> i1 = set.iterator();
        for (int x = 0; x < set.size(); x++) {
            int temp = i1.next();
            if (temp % 2 == 1) {
                i1.remove();
                x--;
            }
        }
        return set;
    }


    public static void main(String[] args) {
        Sets app = new Sets();
    }
}
