import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sudoku {
    //Array to hold sudoku board
    public int[][] board = new int[9][9];
    //Constant Representing size of the board
    public int SIZE = 9;
    //Constant Representing the size of the subgrid
    public int SUBGRID_SIZE = 3;

    public void loadboard() {

        try {
            Scanner scanner = new Scanner(new File("SudkoBT/sudoku.txt"));
            for(int i = 0; i < SIZE; i++)
            {
                for(int j=0; j < SIZE; j++)
                {
                    if (scanner.hasNextInt())
                    {
                        board[i][j] = scanner.nextInt();
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e)
            {
                System.out.println("File not found");
                e.printStackTrace();
            }

    }

    public void solve() {

        if (solveSudoku())
        {
            printBoard();
        }
        else {
            System.out.println("No solution exists");
        }


    }
    public boolean solveSudoku()
    {
        for(int row = 0; row < SIZE; row++)
        {
            for(int col = 0; col < SIZE; col++)
            {
                if(board[row][col] == 0)
                {
                    for(int num = 1; num <= 9; num++)
                    {
                        if(isSafe(row,col,num))
                        {
                            board[row][col] = num;
                            if(solveSudoku())
                            {
                                return true;
                            }

                            board[row][col] = 0;
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }
    public boolean isSafe(int row, int col, int num)
    {
        //Checks row for current number
        for(int i = 0; i < SIZE; i++)
        {
            //If number is in the row return false
            if(board[row][i] == num)
            {
                return false;
            }
        }

        //Check column for current number
        for(int i = 0; i < SIZE; i++)
        {
            //If number is in the column return false
            if(board[i][col] == num)
            {
                return false;
            }
        }

        //These formulas find the starting rows and column for the subgrid
        int startRow = row - row % SUBGRID_SIZE;
        int startCol = col - col % SUBGRID_SIZE;

        //Checks all the numbers in the subgrid looking to see if number is present
        for(int i = 0; i < SUBGRID_SIZE; i++)
        {
            for(int j = 0; j < SUBGRID_SIZE; j++)
            {
                if(board[startRow + i][startCol + j] == num)
                {
                    return false;
                }
            }
        }

        return true;
    }

    public void printBoard() {

        for(int i = 0; i < SIZE; i++)
        {
            for(int j = 0; j < SIZE; j++)
            {
                System.out.print(board[i][j] + " ");
            }

            System.out.println();
        }
    }



}
