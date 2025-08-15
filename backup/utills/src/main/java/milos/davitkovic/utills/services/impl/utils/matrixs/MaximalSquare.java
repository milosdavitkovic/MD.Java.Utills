package milos.davitkovic.utills.services.impl.utils.matrixs;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Maximal Square Size in Matrix
 * https://leetcode.com/problems/maximal-square
 *
 * author milos.davitkovic@gmail.com
 */
@Service
public class MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        final List<List<Integer>> matrixList = get2DimList(matrix);
        return getSquareMatrixSize(matrixList);
    }

    private int getSquareMatrixSize(final List<List<Integer>> matrix) {
        final List<Integer> squaresSizes = new ArrayList<>();
        for (int i = 0; i < matrix.size() - 1; i++) {
            for (int j = 0; j < matrix.get(0).size() - 1; j++) {
                int squareSize = squareSize(matrix, i, j);
                if (squareSize != 0) {
                    squaresSizes.add(squareSize);
                }
            }
        }
        System.out.println("squaresSizes: " + squaresSizes);
        return squaresSizes.stream()
                .max(Comparator.comparing(Integer::valueOf))
                .orElse(0);
    }

    private int squareSize(final List<List<Integer>> matrix, final int i, final int j) {
        int size = 0;
        final List<Integer> scanMatrixSize = Arrays.asList(matrix.size() - i, matrix.get(0).size() - j);
        final int maxSquareLength = scanMatrixSize.stream()
                .min(Comparator.comparing(Integer::valueOf))
                .orElse(0);
        for (int n = 0; n < maxSquareLength; n++) {
            if (!isSquare(matrix, i, j, n)) {
                size = (int) Math.pow(n + 1, 2);
            } else {
                return size;
            }
        }
        return size;
    }

    private boolean isSquare(final List<List<Integer>> matrix, final int i, final int j, final int n) {
        for(int l = 0; l <= n; l++) {
            for(int k = 0; k <= n; k++) {
                if(!matrix.get(i + l).get(j + k).equals(1)) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<List<Integer>> get2DimList(char[][] grid) {
        final List<List<Integer>> gridList = new ArrayList<>();
        for (char[] array : grid) {
            List<Integer> elements = new ArrayList<>();
            for (char c : array) {
                elements.add(Integer.parseInt(String.valueOf(c)));
            }
            gridList.add(elements);
        }
        System.out.println("gridList: " + gridList);
        return gridList;
    }

    // ["1","0","1","0","0"],
    // ["1","0","1","1","1"],
    // ["1","1","1","1","1"],
    // ["1","0","0","1","0"]]

    // ["1"]

    // ["0","1","1","0","0","1","0","1","0","1"],
    // ["0","0","1","0","1","0","1","0","1","0"],
    // ["1","0","0","0","0","1","0","1","1","0"],
    // ["0","1","1","1","1","1","1","0","1","0"],
    // ["0","0","1","1","1","1","1","1","1","0"],
    // ["1","1","0","1","0","1","1","1","1","0"],
    // ["0","0","0","1","1","0","0","0","1","0"],
    // ["1","1","0","1","1","0","0","1","1","1"],
    // ["0","1","0","1","1","0","1","0","1","1"]]


}
