package objects;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import utils.Transformer;

public class Piece {
    private char c;
    private List<List<List<Integer>>> variations;

    public Piece(List<String> piece) {
        this.c = '!';
        String firstline = piece.get(0);
        for (int i = 0; i < firstline.length(); ++i) {
            if (this.c == '!' && firstline.charAt(i) != ' ') {
                this.c = firstline.charAt(i);
            }
        }
        this.makeVariations(piece);
    }

    private void makeVariations(List<String> piece) {
        this.variations = new ArrayList<>();
        List<List<Integer>> shape = new ArrayList<>();
        int maxLen = 0;
        for (int i = 0; i < piece.size(); ++i) {
            maxLen = Math.max(maxLen, piece.get(i).length());
            List<Integer> currentLine = new ArrayList<>();
            for (int j = 0; j < piece.get(i).length(); ++j) {
                char currentChar = piece.get(i).charAt(j);
                if (currentChar != ' ') {
                    currentLine.add(1);
                }
            }
            shape.add(currentLine);
        }
        for (int i = 0; i < shape.size(); ++i) {
            while (shape.get(i).size() < maxLen) shape.get(i).add(0);
        }
        variations.add(shape);
        for (int i = 1; i <= 3; ++i) {
            shape = Transformer.rotateClockwise(variations.get(i - 1));
            variations.add(shape);
        }
        for (int i = 0; i < 4; ++i) {
            variations.add(Transformer.flipX(variations.get(i)));
            variations.add(Transformer.flipY(variations.get(i)));
            variations.add(Transformer.flipX(Transformer.flipY(variations.get(i))));
        }

        removeDuplicates();
    }

    public void removeDuplicates() {
        Set<List<List<Integer>>> uniqueSet = new LinkedHashSet<>(this.variations);
        this.variations =  new ArrayList<>(uniqueSet);
    }

    public char getC() {
        return this.c;
    }

    public List<List<List<Integer>>> getVariations() {
        return this.variations;
    }
}
