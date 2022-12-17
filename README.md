# NQueenProblem
Solving the N-Queen Problem

II. N-Queen Problem Design 
When it comes to solving the N-Queen problem I implemented the genetic algorithm. The problem of N-Queen places N number of queens on a N x N chessboard to where there is only 
one queen in a column, row, or diagonal. First, a random selection of solutions, or chromosomes, are created. They will then be sorted by fitness. My selection operator will then select random chromosomes from the current random generation, since they are already sorted by fitness it has a high chance of selecting fit chromosomes. Two children are then created by crossover and mutation functions by the two chromosomes. The parents and the children that were created are added to the population and it repeated until it grows to the max population size. Once that is done the next population is sorted by fitness, and it becomes the new population. This whole process is repeated until the fitness level is 0, which indicates a solution was found.  

III. Task that your agent will solve 
My program solves the N-Queen problem by implementing the genetic algorithm.  
