//import java.util.List;
//
///**
// * Mini Parser
// * https://leetcode.com/problems/mini-parser/
// *
// * @author milos.davitkovic@gmail.com
// */
//
//public interface NestedInteger {
//    // Constructor initializes an empty nested list.
//    public NestedInteger();
//
//    // Constructor initializes a single integer.
//    public NestedInteger(int value);
//
//    // @return true if this NestedInteger holds a single integer, rather than a nested list.
//    public boolean isInteger();
//
//    // @return the single integer that this NestedInteger holds, if it holds a single integer
//    // Return null if this NestedInteger holds a nested list
//    public Integer getInteger();
//
//    // Set this NestedInteger to hold a single integer.
//    public void setInteger(int value);
//
//    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
//    public void add(NestedInteger ni);
//
//    // @return the nested list that this NestedInteger holds, if it holds a nested list
//    // Return null if this NestedInteger holds a single integer
//    public List<NestedInteger> getList();
//}
//
//public class MiniParser {
//
//    public NestedInteger deserialize(String s) {
//
//        if (s == null || s.equals("")) {
//            return null;
//        }
//
//        if (!isEnclosed(s)) return new NestedInteger(Integer.parseInt(s)); //if not enclosed its number so simply parse out result
//
//        NestedInteger root = new NestedInteger();
//        s = s.substring(1, s.length() - 1); //strip out outer most layer
//        int i = 0;
//        while (i < s.length()) {
//            //if we encounter comma, skip as this means nothing
//            if (s.charAt(i) == ',') {
//                i++;
//                continue;
//            }
//            boolean isNumber = Character.isDigit(s.charAt(i)) || s.charAt(i) == '-';
//            int end = isNumber ? getNumberEnd(s, i) : getBracketEnd(s, i);
//            //if it is number, simply parse the number and add it to the current list
//            if (isNumber) root.add(new NestedInteger(Integer.parseInt(s.substring(i, end))));
//                //since we encountered a bracket, find the end of the bracket, and the recursive result to the current list
//            else root.add(deserialize(s.substring(i, end)));
//            //advance i to the end as we are done processing the current block
//            i = end;
//        }
//        return root;
//    }
//
//    private int getBracketEnd(String s, int start) {
//        int open = 0, close = 0;
//        while (start < s.length()) {
//            if (s.charAt(start) == '[') open++;
//            else if (s.charAt(start) == ']') open--;
//            if (open == 0 && close == 0) break; //all the brackets i care about is closed at this point
//            start++;
//        }
//        return start + 1;
//    }
//
//    private int getNumberEnd(String s, int start) {
//        while (start < s.length()) {
//            if (s.charAt(start) == ',') break;
//            start++;
//        }
//        return start;
//    }
//
//    private boolean isEnclosed(String s) {
//        return s.charAt(0) == '[';
//    }
//
//}
//
////
