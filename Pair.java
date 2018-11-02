public class Pair {
    private char letter;
    private double prob;
    //constructor
    public Pair(char letter,double prob){
           this.letter=letter;
           this.prob=prob;
    }
    //getter and setter methods
    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public double getProb() {
        return prob;
    }

    public void setProb(double prob) {
        this.prob = prob;
    }

    public String toString(){
        return "( "+letter+" , "+Double.toString(prob)+")";
    }

}
