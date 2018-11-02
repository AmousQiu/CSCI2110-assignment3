import java.io.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class HuffmanTree {
    public static void main(String args[]) throws IOException {
        ArrayList<BinaryTree<Pair>> S = new ArrayList<>();
        ArrayList<BinaryTree<Pair>> T = new ArrayList<>();
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the filename to read from: ");
        String filename = keyboard.nextLine();
        BinaryTree<Pair> A = new BinaryTree();
        BinaryTree<Pair> B = new BinaryTree();
        File file = new File(filename);
        Scanner inputFile = new Scanner(file);
        char letter;//the letter in pair
        double freq;//the frequency of this letter
        Pair p = null;
        boolean flag = true;//used to judge if there is already a team created
        //input from the file stats
        while (inputFile.hasNext()) {
            letter = inputFile.next().charAt(0);
            freq = inputFile.nextDouble();
            Pair nPair = new Pair(letter, freq);
            BinaryTree newT = new BinaryTree();
            newT.setData(nPair);
            //S.add(newT);
            S.add(0, newT);
        }
        inputFile.close();
        System.out.println(S.get(0).getData());
        while (!S.isEmpty()) {
            if (T.isEmpty()) {
                A = S.get(0);
                B = S.get(1);
                S.remove(0);
                S.remove(0);
            } else if (!T.isEmpty()) {
                if (S.get(0).getData().getProb() < T.get(0).getData().getProb()) {
                    A = S.get(0);
                    S.remove(0);
                    B = T.get(0);
                    T.remove(0);
                } else {
                    A = T.get(0);
                    T.remove(0);
                    B = S.get(0);
                    S.remove(0);
                }

            }
            BinaryTree P = new BinaryTree();
            Pair root = new Pair('&', A.getData().getProb() + B.getData().getProb());
            P.makeRoot(root);
            P.attachLeft(A);
            P.attachRight(B);
            T.add(P);
        }
        while (T.size() > 1) {
            A = T.get(0);
            B = T.get(1);
            T.remove(0);
            T.remove(0);
            BinaryTree P = new BinaryTree();
            Pair root = new Pair('&', A.getData().getProb() + B.getData().getProb());
            P.makeRoot(root);
            P.attachLeft(A);
            P.attachRight(B);
            T.add(P);
        }
        String result[] = findEncoding(T.get(0));
        File writename = new File("Encode.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
        writename.createNewFile(); // create new file;
        BufferedWriter out = new BufferedWriter(new FileWriter(writename));
        for (int i = 0; i < result.length; i++) {
            out.write(result[0]);
        }
        out.flush();
        out.close();
    }

    public static void findEncoding(BinaryTree<Pair> t, String[] a, String prefix) {
        if (t.getLeft() == null && t.getRight() == null) {
            a[((byte) (t.getData().getLetter())) - 65] = prefix;
        } else {
            findEncoding(t.getLeft(), a, prefix + "0");
            findEncoding(t.getRight(), a, prefix + "1");
        }
    }

    public static String findDecoding(ArrayList<Integer> alist, BinaryTree<Pair> t) {
        String result = "";
        while (!alist.isEmpty()) {
            while (t.getRight() != null || t.getLeft() != null) {
                if (alist.get(0) == 0) {
                    alist.remove(0);
                    t.getLeft();
                } else if (alist.get(0) == 1) {
                    alist.remove(0);
                    t.getRight();
                }
            }
            result += t.getData().getLetter();
        }
        return result;
    }


    public static String[] findEncoding(BinaryTree<Pair> t) {
        String[] result = new String[28];
        result[26] = " ";
        result[27] = "/n";
        findEncoding(t, result, "");
        return result;
    }
}
