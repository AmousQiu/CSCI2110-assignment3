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
        //finish build tree

        String result[] = findEncoding(T.get(0));
        System.out.println("Which file do you want to transfer?");
        String transform = "";
        String filename1 = keyboard.nextLine();

        file = new File(filename1);
        inputFile = new Scanner(file);
        while (inputFile.hasNextLine()) {
            String line = inputFile.nextLine();//read the file line by line
            char[] linec = line.toCharArray();//convert the string to char array
            for (int i = 0; i < linec.length; i++) {
<<<<<<< HEAD
                if (linec[i] == ' ') {
                    transform += " ";
                }
                else if((linec[i] >= 'A' && linec[i] <= 'Z')) {
=======
                if(linec[i]==' '){
                    transform+=" ";
                }
                else {
>>>>>>> 03f7335167204c227a000b751835249db804db8f
                    byte ascii = (byte) linec[i];
                    ascii = (byte) (ascii - 65);
                    transform += result[ascii];
                }
<<<<<<< HEAD
            }
            transform += "\n";
=======
            }transform+="\n";
>>>>>>> 03f7335167204c227a000b751835249db804db8f
        }
        inputFile.close();

        File writename = new File("Encode.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
        writename.createNewFile(); // create new file;
        BufferedWriter out = new BufferedWriter(new FileWriter(writename));
        out.write(transform);
        //for (int i = 0; i < result.length; i++) {
        //    out.write(result[0]);
        // }
        out.flush();
        out.close();

        Byte num;
        ArrayList<Byte> nList = new ArrayList<>();
<<<<<<< HEAD
        char trans[] = transform.toCharArray();
        for (int i = 0; i < trans.length; i++) {
            num = (byte) trans[i];
            nList.add(num);
        }
        System.out.println("Which file do you want to transfer?");
        transform = "";
        String filename2 = keyboard.nextLine();

        file = new File(filename2);
        inputFile = new Scanner(file);
        String decodeLetter = "";
        String decodeLine = "";
        while (inputFile.hasNextLine()) {

            String line = inputFile.nextLine();//read the file line by line
            char[] linec = line.toCharArray();//convert the string to char array
            for (int i = 0; i < linec.length; i++) {

                System.out.println(linec[i]);
                if (linec[i] == ' ') {
                    decodeLine += " ";
                }
                else {
                    decodeLetter += linec[i];
                    for (int j = 0; j < result.length; j++) {
                        if (decodeLetter.equals(result[j])) {
                            char newLetter = (char) (j + 65);
                            decodeLine += newLetter;
                            System.out.println(decodeLetter);
                            decodeLetter = "";
                        }
                    }
                }
            }
            decodeLine += "\n";
=======
        char trans[]=transform.toCharArray();
        for(int i=0;i<trans.length;i++){
            num=(byte)trans[i];
            nList.add(num);
>>>>>>> 03f7335167204c227a000b751835249db804db8f
        }
        inputFile.close();
        writename = new File("Decode.txt");
        writename.createNewFile();
        out = new BufferedWriter(new FileWriter(writename));
<<<<<<< HEAD
        //String decode = findDecoding(nList, T.get(0));
        out.write(decodeLine);
=======
        String decode = findDecoding(nList, T.get(0));
        out.write(decode);
>>>>>>> 03f7335167204c227a000b751835249db804db8f
        out.flush();
        out.close();

    }


<<<<<<< HEAD
=======

>>>>>>> 03f7335167204c227a000b751835249db804db8f
    public static void findEncoding(BinaryTree<Pair> t, String[] a, String prefix) {
        if (t.getLeft() == null && t.getRight() == null) {
            a[((byte) (t.getData().getLetter())) - 65] = prefix;
        } else {
            findEncoding(t.getLeft(), a, prefix + "0");
            findEncoding(t.getRight(), a, prefix + "1");
        }
    }


    public static String[] findEncoding(BinaryTree<Pair> t) {
        String[] result = new String[28];
        result[26] = " ";
        result[27] = "/n";
        findEncoding(t, result, "");
        return result;
    }

    public static String findDecoding(ArrayList<Byte> alist, BinaryTree<Pair> t) {

}
