import java.util.ArrayList;
import java.util.Queue;

public class HuffmanTree {
    public static void main(String args[]) {
        ArrayList<BinaryTree<Pair>> list = new ArrayList<>();
        ArrayList<BinaryTree<Pair>> aList = new ArrayList<>();


    }

    public static void findEncoding(BinaryTree<Pair> t, String[] a, String prefix) {
        if (t.getLeft() == null && t.getRight() == null) {
            a[((byte) (t.getData().getValue())) - 65] = prefix;
        } else {
            findEncoding(t.getLeft(), a, prefix + "0");
            findEncoding(t.getRight(), a, prefix + "1");
        }

    }

    public static String[] findEncoding(BinaryTree<Pair> t) {
        String[] result = new String[26];
        findEncoding(t, result, "");
        return result;
    }
}
