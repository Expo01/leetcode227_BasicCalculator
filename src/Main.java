import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String test = "22-3*5";
        System.out.println(new Solution().calculate(test));
    }
}

class Solution { // assumes all ints in expression are + so no leading -'s like (-5-5+2*5)
    public int calculate(String s) {

        if (s == null || s.isEmpty()) return 0;
        int len = s.length();
        Stack<Integer> stack = new Stack<Integer>();
        int currentNumber = 0;
        char operation = '+';
        for (int i = 0; i < len; i++) { // section handles creating of multi-digit int from chars
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) { // i == -1 handles final int char
                if (operation == '-') {
                    stack.push(-currentNumber);
                }
                else if (operation == '+') { //  sign change begins here. note that if say currentChar is * it can only change operation by first
                    stack.push(currentNumber); //pushing current number to stack.
                }
                else if (operation == '*') {
                    stack.push(stack.pop() * currentNumber); // if * or / found, handled immediately
                }
                else if (operation == '/') {
                    stack.push(stack.pop() / currentNumber);
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
}


//
//class Solution {
//    public int calculate(String s) {
//        Stack<Character> s = new Stack<>();
//        for(char c: s.toCharArray){
//            if(s.peek() == '*' ){
//                s.pop();
//                int a = Character.getNumericValues.pop(); // can't makee multi digit a char'
//                int b =
//            } else if (s.peek() == '/'){
//                // need 2 stacks. an int stack and a char stack. build int, push int, find sign
//                // push sign if peek sign /* then build current int and pop int to compute. push int
//                // back
//                // return int stack .pop
//
//            }
//
//        }
//
//    }
//}


// how strict are order of ops? 4/2*5 . mult before /. 4/10 = .4, 2*5 = 10
// 3 + 2 + 1 * 4 / 2 - 1
// looks like 3+2+ ((1*4)/2) -1
// if + or - , must say if next sign != * or / then execute, otherwise continu pushing until */
// */ handled mid problm, but once only -+ left, order doesnt matter so we can say
// 1*4 = 2 / 2 = 2.  --> 3 + 2 + 2  all pushed, then push till loop finished
// 3 + 2 + 2 - 1 --> second loop pop  2 - 1 = 1. 2 + 1 = 3. 3 + 3 = 6