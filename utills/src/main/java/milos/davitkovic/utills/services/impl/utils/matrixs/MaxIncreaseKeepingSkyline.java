package milos.davitkovic.utills.services.impl.matrixs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Max Increase to Keep City Skyline
 *
 * author milos.davitkovic@gmail.com
 */
public class MaxIncreaseKeepingSkyline {

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        final List<List<Integer>> gridList = get2DimList(grid);
        int m = grid.length;
        System.out.println("m: " + m);
        int n = grid[0].length;
        System.out.println("n: " + n);

        final List<Integer> topBottomSkyline = new ArrayList();
        topBottomSkyline.addAll(getVerticalMax(gridList, m, n));
        System.out.println("topBottomSkyline: " + topBottomSkyline);

        final List<Integer> leftRightSkyline = new ArrayList();
        leftRightSkyline.addAll(getHorizontalMax(gridList));
        System.out.println("leftRightSkyline: " + leftRightSkyline);

        return getSum(gridList, leftRightSkyline, topBottomSkyline, m, n);
    }

    private List<Integer> getHorizontalMax(final List<List<Integer>> gridList) {
        final List<Integer> leftRightSkyline = new ArrayList<>();
        gridList.forEach(array -> {
            final Integer maxValue = array.stream()
                    .max(Comparator.comparing(Integer::valueOf))
                    .orElse(0);
            leftRightSkyline.add(maxValue);
        });
        return leftRightSkyline;
    }

    private List<Integer> getVerticalMax(final List<List<Integer>> gridList, int m, int n) {
        final List<Integer> topBottom = new ArrayList<>();

        for (int j = 0; j < n; j++) {
            final List<Integer> element = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                element.add(gridList.get(i).get(j));
            }
            final Integer maxValue = element.stream()
                    .max(Comparator.comparing(Integer::valueOf))
                    .get();
            topBottom.add(maxValue);
        }
        return topBottom;
    }

    private int getSum(final List<List<Integer>> gridList, final List<Integer> leftRightSkyline, final List<Integer> topBottomSkyline, int m, int n) {
        int sum =  0;
        for(int i =  0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum += getMaxValueToIncrease(gridList.get(i).get(j), topBottomSkyline.get(j), leftRightSkyline.get(i));
            }
        }
        System.out.println("Total Sum: " + sum);
        return sum;
    }

    private int getMaxValueToIncrease(final Integer value, final Integer maxHorizontal, final Integer maxVertical) {
        System.out.println("getMaxValueToIncrease: " + value + " " + maxHorizontal + " " + maxVertical);
        final List<Integer> values = Arrays.asList(maxHorizontal, maxVertical);
        final Integer maxValue = values.stream()
                .min(Comparator.comparing(Integer::valueOf))
                .get();
        System.out.println("maxValue: " + maxValue);
        int increaseValue = maxValue - value;
        System.out.println("Increase Value: " + increaseValue);
        return increaseValue;
    }

    private List<List<Integer>> get2DimList(int[][] grid) {
        final List<List<Integer>> gridList = new ArrayList<>();
        for (int[] array : grid) {
            List<Integer> elements = new ArrayList<>();
            for (int a : array) {
                elements.add(a);
            }
            gridList.add(elements);
        }
        return gridList;
    }
}
