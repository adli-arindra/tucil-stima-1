package objects;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public List<List<Character>> state = new ArrayList<>();
    public static final char EMPTY = '.';
    public int n, m;
    public int piecesCount = 0;

    public Board(int n, int m) {
        this.n = n;
        this.m = m;
        for (int i = 0; i < m; ++i) {
            List<Character> line = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                line.add(EMPTY);
            }
            this.state.add(line);
        }
    }

    public Board(Board b) {
        this.n = b.n;
        this.m = b.m;
        this.piecesCount = b.piecesCount;
        this.state = new ArrayList<>();

        for (List<Character> row : b.state) {
            this.state.add(new ArrayList<>(row));
        }
    }

    public boolean isComplete() {
        boolean ret = true;
        for (List<Character> line : this.state) {
            for (int i = 0; i < line.size(); ++i) {
                if (line.get(i) == EMPTY) ret = false;
            }
        }
        return ret;
    }

    public boolean addPiece(List<List<Integer>> piece, int x, int y, char letter) {
        int lenX = piece.get(0).size();
        int lenY = piece.size();

        for (int i = 0; i < lenX; ++i) {
            for (int j = 0; j < lenY; ++j) {
                if (!insideBoard(i + x, j + y)) return false;
                if (piece.get(j).get(i) == 1) {
                    if (this.state.get(j + y).get(i + x) != '.') return false;
                }
            }
        }

        for (int i = 0; i < lenX; ++i) {
            for (int j = 0; j < lenY; ++j) {
                if (piece.get(j).get(i) == 1) {
                    this.state.get(j + y).set(i + x, letter);
                }
            }
        }
        this.piecesCount ++;

        return true;
    }

    private boolean insideBoard(int x, int y) {
        if (x < 0 || x >= this.n || y < 0 || y >= this.m) return false;
        return true;
    }
    
    public void print() {
        for (List<Character> line : this.state) {
            for (Character c : line) {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println();
    }
}
