//Name: Jarrad Self
//Class: CS3642
//Assignment 2
//Object class for chromosome

package realWorldGA;

import java.util.*;


public class Chromo implements Comparable <Chromo> {
    private final List<Integer> sequence;
    private final int size;
    private Random random = new Random();

    Chromo(int size){ //new Chromosome created with permutation sequence
        this.size = size;
        this.sequence = new ArrayList<>(size);
        generateSequence();
    }
    
    Chromo(List<Integer> sequence) { //new Chromosome created and its sequence will be set to a list sequence passed as a parameter
        this.sequence = sequence;
        this.size = sequence.size();
    }

    public int getSize() { //returns size of Chromosome sequence
        return this.size;
    }

    public List<Integer> getSequence() { //returns a reference to the list object sequence
        return sequence;
    }

    private void generateSequence(){ //this is called by the constructor to generate a permutation
        for(int i = 0; i <getSize(); i++){
            sequence.add(i);
        }
        java.util.Collections.shuffle(sequence);
    }

    public int get(int i)throws IndexOutOfBoundsException{ //returns the index of the sequence
        return sequence.get(i);
    }

    public String toString() { //returns a string representation of the sequence
        return sequence.toString();
    }
    public Object[] toArray(){
        return sequence.toArray();
    }

    public static Chromo createChild(Chromo p1, Chromo p2){ //uses parents 1 && 2 sequence to create a new child
        List<Integer> childSequence = crossOver(p1,p2);
        return new Chromo(childSequence);
    }

    public static Chromo createChromo(int size){ //method for creating Chromos
        return new Chromo(size);
    }

    private static void mutation(List<Integer> childSequence){ //two random variables are generated one has a 80% chance the other has 40% chance
        Random random = new Random();							// if mutation is good, mutationHelp is called
        int prob1 = random.nextInt(100);
        int prob2 = random.nextInt(100);

        if(prob1 <= 80){
            mutationHelp(childSequence);
        }
        if(prob2 <= 40){
            mutationHelp(childSequence);
        }

    }

    private static void mutationHelp(List<Integer> childSequence){ //two indexes randomly selected and swapped
        Random random = new Random();
        int i = random.nextInt(childSequence.size()/2);
        int j = i + random.nextInt(childSequence.size()-i);
        Collections.swap(childSequence,i,j);
    }

    public static int getChromoFitness(Chromo Chromo){ //calculates Chromosome fitness levels
        int t1 = 0, t2 = 0;
        int size = Chromo.getSize();
        int f1 [] = new int[size];
        int f2 [] = new int[size];
        for(int i = 0; i < size; i++){
            f1[i] = (Chromo.get(i)-i);
            f2[i] = ((size)-Chromo.get(i)-i);
        }
        Arrays.sort(f1);
        Arrays.sort(f2);
        for (int i = 1; i < size; i++){
            if(f1[i] == f1[i-1]){
                t1 = t1 + 1;
            }
            if (f2[i] == f2[i-1]){
                t2 = t2 + 1;
            }
        }
        return t1 + t2;
    }

    public static List<Integer> crossOver(Chromo p1, Chromo p2){  //helper function to create the childs sequence
        Random random = new Random();										//new sequence is generated using parents attributes
        int i = random.nextInt(p1.getSize());
        int j = random.nextInt(p1.getSize());
        List<Integer> child;
        while (j == i){
            j = random.nextInt(p1.getSize());
        }

        List<Integer> subList;
        if(i < j){
            subList = p1.getSequence().subList(i,j+1);
            child = childSequence(subList, p2.getSequence(), i);
        }else {
            subList = p1.getSequence().subList(j,i+1);
            child = childSequence(subList, p2.getSequence(), j);
        }
        mutation(child);
        return child;
    }

    private static List<Integer> childSequence(List<Integer> subList, List<Integer> p2, int i){ //function used to create childs sequence
        List<Integer> child = new ArrayList<>(p2.size());
        for (int k = 0; k < p2.size(); k++){
            if (k == i){
                child.addAll(subList);
            }
            if (!subList.contains(p2.get(k))){
                child.add(p2.get(k));
            }
        }
        return child;
    }

    public int compareTo(Chromo Chromo) { //sorting Chromosome by fitness level
        int fitness = getChromoFitness(Chromo);
        return getChromoFitness(this) - fitness;
    }
}
