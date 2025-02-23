package objects;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    public List<List<Character>> state = new ArrayList<>();
    public static final char EMPTY = '.';
    public int n, m;
    public int piecesCount = 0;

    private static final String RESET = "\u001B[0m";
    private static final String[] COLORS = {
        "\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m",
        "\u001B[35m", "\u001B[36m", "\u001B[91m", "\u001B[92m",
        "\u001B[93m", "\u001B[94m", "\u001B[95m", "\u001B[96m",
        "\u001B[97m", "\u001B[90m", "\u001B[38;5;198m", "\u001B[38;5;208m",
        "\u001B[38;5;214m", "\u001B[38;5;220m", "\u001B[38;5;226m", "\u001B[38;5;82m",
        "\u001B[38;5;46m", "\u001B[38;5;21m", "\u001B[38;5;201m", "\u001B[38;5;93m",
        "\u001B[38;5;129m", "\u001B[38;5;166m"
    };
    private final Map<Character, String> colorMap = new HashMap<>();

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
    
    private void assignColors() {
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (int i = 0; i < alphabet.length; i++) {
            colorMap.put(alphabet[i], COLORS[i]);
        }
    }

    public void print() {
        assignColors();
        for (List<Character> line : this.state) {
            for (Character c : line) {
                String color = colorMap.getOrDefault(Character.toUpperCase(c), RESET);
                System.out.print(color + c + RESET);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void writeToFile(int iterations, long time) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("../output.txt"))) {
            for (List<Character> line : this.state) {
                for (Character ch : line) {
                    writer.write(ch);
                }
                writer.newLine();
            }
            writer.newLine();
            writer.write("Total iterations: " + iterations + '\n');
            writer.write("Time spent: " + time + " ms\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
