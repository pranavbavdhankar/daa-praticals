import java.util.*;

public class OptimalStorageTape {

    public static List<List<Integer>> optimalStorageTape1(int tapes, List<Integer> prgmLengths) {

        Collections.sort(prgmLengths);

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < tapes; i++) {
            res.add(new ArrayList<>());
        }

        // Round-robin distribution
        for (int i = 0; i < prgmLengths.size(); i++) {
            res.get(i % tapes).add(prgmLengths.get(i));
        }

        return res;
    }

    // Correct MRT calculation
    public static double calculateMRT(List<Integer> list) {
        int cumulative = 0;
        int total = 0;

        for (int val : list) {
            cumulative += val;
            total += cumulative;
        }

        return (double) total / list.size();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of programs: ");
        int n = sc.nextInt();

        List<Integer> prgms = new ArrayList<>();

        System.out.println("Enter program lengths:");
        for (int i = 0; i < n; i++) {
            prgms.add(sc.nextInt());
        }

        System.out.print("Enter number of tapes: ");
        int tapes = sc.nextInt();

        List<List<Integer>> res = optimalStorageTape1(tapes, prgms);

        double overallTotal = 0;
        int totalFiles = 0;

        System.out.println("\n--- Result ---");

        for (int i = 0; i < tapes; i++) {
            List<Integer> tape = res.get(i);

            double mrt = calculateMRT(tape);

            overallTotal += mrt * tape.size();
            totalFiles += tape.size();

            System.out.println("Tape " + (i + 1) + " : " + tape + " -> MRT = " + mrt);
        }

        System.out.println("\nOverall MRT = " + (overallTotal / totalFiles));

        sc.close();
    }
}