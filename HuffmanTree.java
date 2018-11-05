import java.io.*;
import java.util.ArrayList;
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
        //build the huffman tree
        while (inputFile.hasNext()) {
            letter = inputFile.next().charAt(0);
            freq = inputFile.nextDouble();
            Pair nPair = new Pair(letter, freq);
            BinaryTree newT = new BinaryTree();
            newT.setData(nPair);//set the data of the new binary tree
            S.add(0, newT);//put the new pair to the beginning of the arraylist
        }
        //start to add the thing to huffman tree
        while (!S.isEmpty()) {//keep adding until arraylist S is empty
            if (T.isEmpty()) {//while the arraylist T is empty
                A = S.get(0);//set the two minimum object of S as the A,B tree
                B = S.get(1);
                S.remove(0);//remove A and B from the Arraylist
                S.remove(0);
            } else if (!T.isEmpty()) {
                //if T arrylist is not empty , get the smallest weight tree from S and T ,and then remove them from the
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
            //create a new tree which is the huffman tree store the pairs
            BinaryTree P = new BinaryTree();
            Pair root = new Pair('&', A.getData().getProb() + B.getData().getProb());
            P.makeRoot(root);//make the root with a default letter &
            P.attachLeft(A);//give it a left child
            P.attachRight(B);//give it a right child
            T.add(P);//add this to the arraylist
        }
        while (T.size() > 1) {//we just want this arraylist have one tree,so we do a combbination here
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
//Now we start to process the pokemon text
        file = new File(filename1);
        inputFile = new Scanner(file);
        while (inputFile.hasNextLine()) {
            String line = inputFile.nextLine();//read the file line by line
            char[] linec = line.toCharArray();//convert the string to char array
            for (int i = 0; i < linec.length; i++) {
                if (linec[i] == ' ') {//keep the spaces as they were
                    transform += " ";
                } else if ((linec[i] >= 'A' && linec[i] <= 'Z')) {//avoid there is strange letters inside the text and cause indexOutOfBound error
                    byte ascii = (byte) linec[i];
                    ascii = (byte) (ascii - 65);//transfer the ascii code to the position of the letter in the alpha table
                    transform += result[ascii];//add it to the output string
                }
            }
            transform += "\n";
        }
        inputFile.close();//finish read the text

        File writename = new File("Encode.txt"); // create a new encode txt
        writename.createNewFile(); // create new file;
        BufferedWriter out = new BufferedWriter(new FileWriter(writename));
        out.write(transform);
        out.flush();
        out.close();

        //the decode part
        System.out.println("Which file do you want to transfer?");
        transform = "";//clear the output string since I'm using the same variable
        String filename2 = keyboard.nextLine();

        file = new File(filename2);
        inputFile = new Scanner(file);
        String decodeLetter = "";
        String decodeLine = "";
        while (inputFile.hasNextLine()) {
            String line = inputFile.nextLine();//read the file line by line
            char[] linec = line.toCharArray();//convert the string to char array
            for (int i = 0; i < linec.length; i++) {
                if (linec[i] == ' ') {
                    decodeLine += " ";//space default
                } else {
                    decodeLetter += linec[i];
                    for (int j = 0; j < result.length; j++) {
                        if (decodeLetter.equals(result[j])) {//get the position of the letter in the result array
                            char newLetter = (char) (j + 65);//turn it back to the ascii code
                            decodeLine += newLetter;
                            decodeLetter = "";
                        }
                    }
                }
            }
            decodeLine += "\n";
        }
        inputFile.close();
        writename = new File("Decode.txt");
        writename.createNewFile();
        out = new BufferedWriter(new FileWriter(writename));
        //String decode = findDecoding(nList, T.get(0));
        out.write(decodeLine);
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


    public static String[] findEncoding(BinaryTree<Pair> t) {
        String[] result = new String[26];
        findEncoding(t, result, "");
        return result;
    }
}
