public class Main {
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        sudoku.loadboard(); // load from "sudoku.txt"
        sudoku.solve();             // try solving and print
    }
}