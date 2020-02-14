package milos.davitkovic.utills.services.impl.utils.matrixs;

import org.springframework.stereotype.Service;

/**
 * Word Search
 * https://leetcode.com/problems/word-search/
 *
 * @author milos.davitkovic@gmail.com
 */
@Service
public class WordSearch {

    private static boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        char[] c = word.toCharArray();

        boolean b = false;
        visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int count = 0;
                b = check(board, i, j, count, c);
                if(b == true) {
                    return b;
                }
            }
        }
        return b;
    }

    private boolean check(final char[][] board, final int i, final int j, int count, final char[] c) {
        boolean b1 = false;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        if(count == c.length) {
            return true;
        }
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j] == true) {
            return false;
        }

        if (board[i][j] == c[count]) {
            visited[i][j] = true;
            count++;
            b1 = check(board, i - 1, j, count, c);
            b2 = check(board, i, j - 1, count, c);
            b3 = check(board, i + 1, j, count, c);
            b4 = check(board, i, j + 1, count, c);
            if (b1 || b2 || b3 || b4) {
                return true;
            }
        }
        visited[i][j] = false;
        return false;
    }


    // Create an Object with i, j, isUsed values
    // Get the first letter of the world
    // Try to find that letter
        // if yes, get that value and put it in the list of objects
    // try to find next letter around my previous letter
        // if that object exist put in the list
        // if not return signal that we need to restart the search

    // we need only to remember the first element, to not start searching around him once again

    // get dimensions of the matricxs, length and height
    // try to find first letter
    //


    //
}


