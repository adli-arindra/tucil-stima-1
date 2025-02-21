import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import objects.Board;
import objects.Piece;
import utils.Input;
import utils.Timer;

public class Main {
    public static List<Piece> pieces;
    public static Stack<Board> boards;
    public static int iterations = 0;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Masukan nama file txt: ");
        String path = scanner.nextLine();
        try {
            Input.read("input/" + path);
            pieces = Input.getPieces();
    
            boards = new Stack<>();
            Board emptyBoard = new Board(Input.n, Input.m);
            boards.push(emptyBoard);
            
            Timer.start();
            solve();
        } catch (Exception e) {
            System.out.println("Pastikan input sudah benar dan sesuai format!");
        }
    }

    public static void solve() {
        while (!boards.isEmpty()) {
            Board currentBoard = boards.pop();
            if (currentBoard.isComplete()) {
                onComplete(currentBoard);
                return;
            }
            Piece currentPiece = pieces.get(currentBoard.piecesCount);
            for (List<List<Integer>> variation : currentPiece.getVariations()) {
                int pieceWidth = variation.get(0).size();
                int pieceHeight = variation.size();

                for (int i = 0; i <= Input.n - pieceWidth; ++i) {
                    for (int j = 0; j <= Input.m - pieceHeight; ++j) {
                        Board newBoard = new Board(currentBoard);
                        boolean status = newBoard.addPiece(variation, i, j, currentPiece.getC());
                        if (status) {
                            boards.push(newBoard);
                        }
                        iterations++;
                    }
                }
            }
        }
        System.out.println("Configuration not found!");
    }

    public static void onComplete(Board completedBoard) {
        if (completedBoard.piecesCount != Input.p) {
            System.out.println("Pieces that is given is bigger than the board!");
            return;
        }

        System.out.println("Configuration found!\n");
        completedBoard.print();

        Timer.stop();
        System.out.println("Total iterations: " + iterations);
        System.out.println("Time spent: " + Timer.get() + " ms");
        System.out.print("Apakah anda ingin menyimpan solusi? (y/n): ");
        Character command = scanner.next().charAt(0);
        
        if (command == 'y') {
            completedBoard.writeToFile(iterations, Timer.get());
            System.out.println();
            System.out.println("Berhasil menyimpan file dengan nama\u001B[34m output.txt \u001B[0m");
            System.out.println();
        }
        
        scanner.close();
    }
}
