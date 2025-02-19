package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Transformer {
    public static List<List<Integer>> flipX(List<List<Integer>> shape) {
        List<List<Integer>> ret = new ArrayList<>(shape);
        for (List<Integer> row : ret) {
            Collections.reverse(row);
        }
        return ret;
    }

    public static List<List<Integer>> flipY(List<List<Integer>> shape) {
        List<List<Integer>> ret = new ArrayList<>(shape);
        Collections.reverse(ret);
        return ret;
    }

    public static List<List<Integer>> rotateClockwise(List<List<Integer>> matrix) {
        int rows = matrix.size();
        int cols = matrix.get(0).size();
        List<List<Integer>> rotated = new ArrayList<>();
        
        for (int i = 0; i < cols; i++) {
            List<Integer> newRow = new ArrayList<>();
            for (int j = rows - 1; j >= 0; j--) {
                newRow.add(matrix.get(j).get(i));
            }
            rotated.add(newRow);
        }
        return rotated;
    }
}
