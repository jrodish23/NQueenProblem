//Name: Jarrad Self
//Class: CS3642
//Assignment 2
//Main method for N-Queen GA solution

package realWorldGA;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int popSize = 50;
        int chromoSize = 10;
        int selFactor = 5;
        runSim(chromoSize, popSize, selFactor);

    }

    public static List<Chromo> population (int popSize, int chromoSize){ //returns new population of a specific size
        List<Chromo> population = new ArrayList<>(popSize);
        for (int i = 0; i < popSize; i ++){
            population.add(new Chromo(chromoSize));
        }
        return population;
    }

    public static int selectionOperator(int popSize, int selFactor){ //randomly generates a number based on the population size 
        double rand = Math.random();
        return (int) (popSize * Math.pow(rand, selFactor));
    }

    public static String getBoard(int rows, int columns, List chessBoard){ //method for creating the chessboard
        char[][] board = new char[rows][columns];
        for (int i = 0; i < chessBoard.size(); i ++){
            board[(int)chessBoard.get(i)][i] = 'Q';
        }
        StringBuilder boardBuilder = new StringBuilder(rows * columns);
        for (int i = 0; i < rows; i++ ){
            boardBuilder.append("|");
            for (int j = 0; j < columns ; j++){
                if(board[i][j] == 'Q'){
                    boardBuilder.append(board[i][j]);
                }else {
                    boardBuilder.append(" ");
                }
                if(j != columns-1){
                    boardBuilder.append(",");
                }
            }
            boardBuilder.append("|");
            boardBuilder.append("\n");
        }
        return boardBuilder.toString();
    }

    public static void runSim(int chromoSize, int popSize, int selFactor){         //random population is created and sorted by fitness level.
        int randomIndex1;																			//selection operator will randomly select Chromosome from the population that has been made
        int randomIndex2;																			//lower fitness level Chromosome have a higher chance of being selected for breeding
        Chromo parent1;																			//then when two Chromosome are selected they create two children via crossover and mutation
        Chromo parent2;																			//process is then repeated until the next population grows to max population size
        Chromo child1;																			//all steps are repeated until you reach 0 fitness level, which shows a solution was found
        Chromo child2;
        List<Chromo> population = population(popSize, chromoSize);
        Collections.sort(population);
        int generation = 0;
        while (Chromo.getChromoFitness(population.get(0)) != 0){
            List<Chromo> nextPop = new ArrayList<>(popSize);

            while (nextPop.size() < popSize){
                randomIndex1 = selectionOperator(popSize,selFactor);
                randomIndex2 = selectionOperator(popSize,selFactor);

                while (randomIndex2 == randomIndex1){
                    randomIndex1 = selectionOperator(popSize,selFactor);
                    randomIndex2 = selectionOperator(popSize,selFactor);
                }

                parent1 = population.get(randomIndex1);
                parent2 = population.get(randomIndex2);
                child1 = Chromo.createChild(parent1, parent2);
                child2 = Chromo.createChild(parent2, parent1);
                nextPop.add(parent1);
                nextPop.add(parent2);
                nextPop.add(child1);
                nextPop.add(child2);

            }
            population = nextPop;
            Collections.sort(population);
            generation++;

        }
        
        System.out.println("Population :" + population);
        System.out.println(getBoard(chromoSize,chromoSize,population.get(0).getSequence()));
        System.out.println("Fitness level: " + Chromo.getChromoFitness(population.get(0)));
        System.out.println("It took " + generation + " generations");

    }
    
}


