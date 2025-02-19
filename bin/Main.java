import java.util.List;
import java.util.Stack;

import objects.Board;
import objects.Piece;
import utils.Input;

public class Main {
    public static List<Piece> pieces;
    public static Stack<Board> boards;
    public static int counter = 0;

    public static void main(String[] args) {
        Input.read("input.txt");
        pieces = Input.getPieces();


        boards = new Stack<>();
        Board emptyBoard = new Board(Input.n, Input.m);
        boards.push(emptyBoard);

        solve();
    }

    public static void solve() {
        if (boards.isEmpty()) {
            System.out.println("NOT FOUND");
            return;
        }
        Board currentBoard = boards.peek();
        if (currentBoard.isComplete()) {
            currentBoard.print();
            System.out.println("FOUND!");
            return;
        }
        if (currentBoard.piecesCount == (Input.p - 1)) {
            boards.pop();
        }
        else {
            Piece currentPiece = pieces.get(currentBoard.piecesCount);
            boolean placed = false;
            for (List<List<Integer>> variation : currentPiece.getVariations()) {
                for (int i = 0; i < Input.n; ++i) {
                    for (int j = 0; j < Input.m; ++j) {
                        Board newBoard = new Board(currentBoard);
                        boolean status = newBoard.addPiece(variation, i, j, currentPiece.getC());
                        if (status) {
                            boards.push(newBoard);
                            placed = true;
                            boards.peek().print();
                        }
                        counter ++;
                    }
                }
            }
            if (!placed) boards.pop();
        }
        solve();
    }
}
