import java.util.*;

class HuffmanNode {
    char ch;
    int freq;
    HuffmanNode left, right;

    HuffmanNode(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
        left = right = null;
    }
}

public class HuffmanCoding {

    // Comparator for min heap
    static class MyComparator implements Comparator<HuffmanNode> {
        public int compare(HuffmanNode x, HuffmanNode y) {
            return x.freq - y.freq;
        }
    }

    // Print Huffman Codes
    static void printCodes(HuffmanNode root, String code) {
        if (root == null) return;

        if (root.left == null && root.right == null) {
            System.out.println(root.ch + " : " + code);
        }

        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of characters: ");
        int n = sc.nextInt();

        char[] chars = new char[n];
        int[] freq = new int[n];

        System.out.println("Enter characters:");
        for (int i = 0; i < n; i++) {
            chars[i] = sc.next().charAt(0);
        }

        System.out.println("Enter frequencies:");
        for (int i = 0; i < n; i++) {
            freq[i] = sc.nextInt();
        }

        PriorityQueue<HuffmanNode> pq =
                new PriorityQueue<>(n, new MyComparator());

        // Step 1: Add all nodes
        for (int i = 0; i < n; i++) {
            pq.add(new HuffmanNode(chars[i], freq[i]));
        }

        // Step 2: Build tree
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();

            HuffmanNode newNode =
                    new HuffmanNode('-', left.freq + right.freq);

            newNode.left = left;
            newNode.right = right;

            pq.add(newNode);
        }

        HuffmanNode root = pq.peek();

        // Step 3: Print codes
        System.out.println("\nHuffman Codes:");
        printCodes(root, "");

        sc.close();
    }
}