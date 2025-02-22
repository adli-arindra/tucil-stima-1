package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import objects.Piece;

public class Input {
    public static int n, m, p;
    public static String s;
    public static List<Piece> pieces = new ArrayList<Piece>();

    public static void read(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int idx = 0;
            String line;
            char lastChar = '!';
            List<String> currentPiece = new ArrayList<String>();
            while ((line = reader.readLine()) != null) {
                if (idx == 0) {
                    int[] numbers = parseStringToInts(line);
                    n = numbers[0];
                    m = numbers[1];
                    p = numbers[2];
                }
                else if (idx == 1) {
                    s = line;
                }
                else {
                    if (lastChar == '!') lastChar = getCharFromString(line);
                    if (lastChar != getCharFromString(line)) {
                        pieces.add(new Piece(currentPiece));
                        currentPiece = new ArrayList<String>();
                        lastChar = getCharFromString(line);
                    }
                    currentPiece.add(line);
                }
                idx ++;
            }
            pieces.add(new Piece(currentPiece));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] parseStringToInts(String input) {
        String[] parts = input.split(" ");
        int[] numbers = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i]);
        }
        return numbers;
    }

    private static char getCharFromString(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') return str.charAt(i);
        }
        return '!';
    }

    public static List<Piece> getPieces() {
        return pieces;
    }
}
