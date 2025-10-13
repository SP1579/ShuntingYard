import com.sun.jdi.InvalidTypeException;

import java.util.*;



/**
 * Implementation of Dijkstra's Shunting Yard Algorithm
 * Converts infix expressions to postfix (Reverse Polish Notation)
 *
 * Example: "3 + 4 * 2" becomes "3 4 2 * +"
 *
 * Algorithm Overview:
 * 1. Read tokens from left to right
 * 2. If operand (number), add to output
 * 3. If operator, pop operators from stack based on precedence rules
 * 4. If '(', push to stack
 * 5. If ')', pop until matching '(' is found
 * 6. At end, pop remaining operators to output
 */

public class ShuntingYard {
Stack<String> myStack = new Stack<String>();
Queue<String> myQueue = new LinkedList<>();

    /**
     * Converts an infix expression to postfix notation
     *
     * @param infix The infix expression as a string (e.g., "3 + 4 * 2")
     * @return The postfix expression as a string (e.g., "3 4 2 * +")
     *
     * Hint: Use a Stack for operators and a Queue or List for output
     * Consider how to handle:
     * - Operands (numbers)
     * - Operators (+, -, *, /)
     * - Parentheses
     */
    public String infixToPostfix(String infix) {
        // TODO: Implement the shunting yard algorithm
        if (infix == null) throw new ExpressionException("Input is null");

        myStack.clear();
        myQueue.clear();
        // You'll need:
        // - A stack to hold operators temporarily
        // - An output queue or list for the result
        // - Logic to tokenize the input string
        // - Logic to handle operator precedence
        List<String> tokens = tokenize(infix);

        for (int i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);

            if (isNumber(token)) {
                // numbers go straight to the output queue
                myQueue.add(token);

            } else if ("(".equals(token)) {
                myStack.push(token);

            } else if (")".equals(token)) {
                // pop until matching '('
                while (!myStack.isEmpty() && !"(".equals(myStack.peek())) {
                    myQueue.add(myStack.pop());
                }
            } else if (isOperator(token)) {
                String top = myStack.peek();
                // pop higher precedence, or same precedence if current is left-associative
                while (!myStack.isEmpty() && isOperator(top)) {
                    int precedenceTop = getPrecedence(top);
                    int precedenceCur = getPrecedence(token);

                    if (precedenceTop > precedenceCur || (precedenceTop == precedenceCur && isLeftAssoc(token))) {
                        myQueue.add(myStack.pop());
                    } else {
                        break;
                    }
                }
                myStack.push(token);
            }
            // drain remaining operators
            while (!myStack.isEmpty()) {
                String top = myStack.pop();
                if (top.equals(")") || top.equals("(")) {
                    throw new RuntimeException("parentheses error");
                }
                myQueue.add(top);
            }

            String output = "";
            while (!myQueue.isEmpty()) {
                if (!output.isEmpty()){}

                output += myQueue.remove() + " ";
            }
            return output;

        }
    }



    /**
     * Determines the precedence of an operator
     * Higher number = higher precedence
     *
     * @param operator The operator character
     * @return The precedence level (higher is more important)
     *
     * Hint: Multiplication and division have higher precedence than addition and subtraction
     * Standard precedence: *, / = 2; +, - = 1
     */


    private int getPrecedence(String operator) {
        if (operator.equals("+") || operator.equals("-")){
            return 2;
        }
        else if (operator.equals("*") || operator.equals("/")){
            return 3;
        }
        else if (operator.equals("^")){
            return 4;
        }
        else if (operator.equals("=")){
            return 1;
        }
        return 0;
    }


    /**
     * Checks if a character is an operator
     *
     * @param c The character to check
     * @return true if the character is an operator (+, -, *, /)
     *
     * Hint: Compare against the four basic operators
     */
    private boolean isOperator(String c) {
        if(c.equals("X")){
            System.out.println("use * instead of x for multiplying");
        }
        if ((c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") || c.equals("^"))){
            return true;
        }
        return false;

    }
    private boolean isLeftAssoc(String c) {
        if ((c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/"))){
            return true;
        }
        else if (c.equals("^")){
            return false;
        }


    }
    /**
     * Checks if a string is a number (operand)
     *
     * @param token The string to check
     * @return true if the string represents a valid number
     *
     * Hint: You can use try-catch with parsing, or check character by character
     **/

    private boolean isNumber(String token) {

        boolean isValidNumber = false;
        if (token == null){
            return isValidNumber;
        }
        try {
            if(token.contains(".")){
                System.out.println(Double.parseDouble(token));
            }
            else{
                System.out.println(Integer.parseInt(token));
            }
            isValidNumber = true;

        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid number used");
        }


        return isValidNumber;
    }


    /**
     * Evaluates a postfix expression and returns the result
     *
     * @param postfix The postfix expression to evaluate
     * @return The numerical result of the expression
     *
     * Hint: Use a stack to store operands
     * When you encounter an operator:
     * 1. Pop two operands
     * 2. Apply the operator
     * 3. Push the result back
     */

    // Classwork
    public double evaluatePostfix(String postfix) {
        // TODO: Implement postfix evaluation
        // Algorithm:
        // - For each token:
        //   - If number: push to stack
        //   - If operator: pop two values, compute, push result
        // - Final stack value is the answer


        return 0.0;
    }


    /**
     * Tokenizes an expression string into individual tokens
     *
     * @param expression The expression to tokenize
     * @return A list of tokens (numbers and operators)
     *
     * Hint: Split by spaces, or iterate character by character
     * Consider multi-digit numbers
     */
    private List<String> tokenize(String expression) {

        // Example: "3 + 4 * 2" -> ["3", "+", "4", "*", "2"]
        ArrayList<String> tokenList = new ArrayList<String>();
        // Example: "3 + 4 * 2" -> ["3", "+", "4", "*", "2"]

        String newExpression = expression;
        if (newExpression.indexOf(" ") == 0){
            newExpression.substring(1);
        }
        if(newExpression.lastIndexOf(" ") != newExpression.length() -1 ){
            newExpression += " ";
        }

        while (0 < newExpression.length()){
            int end = newExpression.indexOf(" ");
            String expressionValue = newExpression.substring(0,end);
            //expressionValue.replace(" ","");
            newExpression = newExpression.substring(end+1);
            tokenList.addLast(expressionValue);
        }

        return tokenList;

    }


}

