package checksodukosolution;

import java.util.Scanner;

public class CheckSodukoSolution {

    public static void main(String[] args) {
        // Read a Sudoku solution
        int[][] grid = readASolution();

        System.out.println(isValid(grid) ? "Valid solution"
                : "Invalid solution");
        
          // Solve the puzzle and print the solution
        if (solveSudoku(grid)) {
            System.out.println("Solved Sudoku:");
            printGrid(grid);
        } else {
            System.out.println("No solution exists.");
        }
    }

    /* Read a Sudoku solution from the console*/
    public static int[][] readASolution() {
        // Create a Scanner
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a Sudoku puzzle (0 represent empty blocks):");
        
        int[][] grid = new int[9][9];
        
        for (int i = 0; i < 9; i++) {
            String line = input.nextLine().trim();
            String[] row = line.split("\\s+"); // Read and split row
            
           if (row.length != 9) {
            System.out.println("Invalid input! Each row must have exactly 9 numbers.");
            System.exit(1); // Exit the program if input is incorrect
        }
            
            for (int j = 0; j < 9; j++) {
                grid[i][j] = Integer.parseInt(row[j]);
            }
        }
        return grid;
    }

    /** Check whether a solution is valid*/
    public static boolean isValid(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] != 0 && !isValid(i, j, grid)) { // Igonre 0's
                    return false;
                }
            }
        }
        return true; // The solution is valid
    }

    /*Check whether grid[i][j] is valid in the grid*/
    public static boolean isValid(int i, int j, int[][] grid) {
        // Check whether grid[i][j] is unique in i's row
        for (int column = 0; column < 9; column++) {
            if (column != j && grid[i][column] == grid[i][j]) {
                return false;
            }
        }

        // Check whether grid[i][j] is unique in j's column
        for (int row = 0; row < 9; row++) {
            if (row != i && grid[row][j] == grid[i][j]) {
                return false;
            }
        }

        // Check whether grid[i][j] is unique in the 3-by-3 box
        for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++) {
            for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++) {
                if (grid[row][col] == grid[i][j] && (row != i || col != j)) {
                    return false;
                }
            }
        }

        return true;// The current value at grid[i][j] is valid
    }
        /** Solve the Sudoku using Backtracking */
    public static boolean solveSudoku(int[][] grid) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col] == 0) { // Find an empty space
                    for (int num = 1; num <= 9; num++) {
                        if (isSafe(grid, row, col, num)) {
                            grid[row][col] = num; // Place the number

                            if (solveSudoku(grid)) { 
                                return true; // Found a solution
                            }

                            grid[row][col] = 0; // Backtrack
                        }
                    }
                    return false; // No valid number found
                }
            }
        }
        return true; // No empty spaces left, puzzle is solved
    } 
 /** Check if placing number in grid[row][col] is safe */
    public static boolean isSafe(int[][] grid, int row, int col, int num) {
        // Check row and column
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == num || grid[i][col] == num) {
                return false;
            }
        }

        // Check 3x3 box
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true; // Number can be placed
    }

    /** Print the Sudoku grid */
    public static void printGrid(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
    }






