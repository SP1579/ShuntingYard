import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // TODO: Check if c is one of: +, -, *, /, ^

        System.out.println(isNumber("1"));

    }

    private boolean isNumber(String token) {
        // TODO: Determine if the token is a valid number
        boolean isValidNumber = false;
        if (token.equals(false)){
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

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        return isValidNumber;
/*
        int currentIndex = 0;


        // 1 + 1 + 3
        // 1 +
        ArrayList<String> tokenList = new ArrayList<String>();
        String newExpression = expression;

        //


        for(int i = 0; i < newExpression.length(); i++) {
            System.out.println("New Expression is: " + newExpression);
            System.out.println("TokenList is: " + tokenList);
            System.out.println();


            int space = newExpression.indexOf(" ");
            System.out.println("Space index is: " + space);
            if(!newExpression.substring(i,space).equals(" ")) {
                tokenList.add(newExpression.substring(i,space));
            }
            newExpression = newExpression.substring(space++);
            System.out.println("New Expression 2 is: " + newExpression);
            i = -1;
        }
        return tokenList;
        */
    }
}