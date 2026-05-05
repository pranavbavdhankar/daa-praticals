import java.util.*;

public class MinMax {

    private static List<Integer> minMax(List<Integer> list, int start, int end) {

        if (start == end) {
            return List.of(list.get(start), list.get(start));
        }

        if (end == start + 1) {
            int first = list.get(start);
            int second = list.get(end);

            if (first > second) {
                return List.of(second, first);
            } else {
                return List.of(first, second);
            }
        }

        int mid = (start + end) / 2;

        List<Integer> left = minMax(list, start, mid);
        List<Integer> right = minMax(list, mid + 1, end);

        int finalMin = Math.min(left.get(0), right.get(0));
        int finalMax = Math.max(left.get(1), right.get(1));

        return List.of(finalMin, finalMax);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        List<Integer> list = new ArrayList<>();

        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }

        List<Integer> result = minMax(list, 0, list.size() - 1);

        System.out.println("\nMinimum: " + result.get(0));
        System.out.println("Maximum: " + result.get(1));

        sc.close();
    }
}