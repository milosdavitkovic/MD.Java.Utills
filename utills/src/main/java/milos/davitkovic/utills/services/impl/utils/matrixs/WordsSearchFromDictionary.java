package milos.davitkovic.utills.services.impl.matrixs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * matrixs.WordsSearchFromDictionary
 * https://leetcode.com/problems/word-search-ii/
 *
 * @author milos.davitkovic@gmail.com
 */
public class WordsSearchFromDictionary {

    private class TrieNode {
        public char c;
        public TrieNode[] childs;
        public int wordIdx;
        public TrieNode(char c, int wordIdx) {
            this.c = c;
            this.childs = new TrieNode[26];
            this.wordIdx = wordIdx;
        }
    }
    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 || board[0].length == 0 ||
                words == null || words.length == 0) {
            return new ArrayList<>();
        }
        // build trie
        TrieNode root = new TrieNode('\0', -1);
        for (int wIdx = 0; wIdx < words.length; wIdx++) {
            String w = words[wIdx];
            TrieNode node = root;
            for (int i = 0; i < w.length(); i++) {
                int charIdx = w.charAt(i) - 'a';
                if (node.childs[charIdx] == null) {
                    node.childs[charIdx] = new TrieNode(w.charAt(i), -1);
                }
                if (i == w.length() - 1) node.childs[charIdx].wordIdx = wIdx;
                node = node.childs[charIdx];
            }
        }
        // dfs with trie
        Set<Integer> resIdx = new HashSet<>();
        int height = board.length, width = board[0].length;
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                boolean[][] visited = new boolean[height][width];
                dfs(board, resIdx, h, w, visited, root);
            }
        }
        List<String> res = new ArrayList<>();
        for (int i: resIdx) {
            res.add(words[i]);
        }
        return res;
    }
    private void dfs(final char[][] board, final Set<Integer> resIdx, final int h, final int w,
                     final boolean[][] visited, final TrieNode node) {
        int height = board.length, width = board[0].length;
        if (h < 0 || h >= height || w < 0 || w >= width ||
                visited[h][w] || node == null) {
            return;
        }
        // check if hit a word
        final int charIdx = board[h][w] - 'a';
        final TrieNode next = node.childs[charIdx];
        if (next == null) {
            return;
        }
        if (next.wordIdx >= 0) {
            resIdx.add(next.wordIdx);
        }
        // dfs
        visited[h][w] = true;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] d: dirs) {
            dfs(board, resIdx, h + d[0], w + d[1], visited, next);
        }
        visited[h][w] = false;
    }
}
