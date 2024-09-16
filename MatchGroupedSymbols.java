//code created by Elijah Gonzalez, September 15th 2024

//we must import
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

//the main class. the program reads from a file, and makes sure that each bracket is paired together with another, such as [] {} and ()
//it makes sure that there are no extras or any mismatching.
public class MatchGroupedSymbols {
    public static void main(String[] args) {
        //ensure that the file is provided
        if (args.length < 1) {
            System.out.println("Input file address: ");
            return;
        }

        //we make the file path this so that we can access it later.
        String filePath = args[0];

        //read the file using a scanner, we use the try method
        try (Scanner scanner = new Scanner(new File(filePath))) {

            //we process every line that is in the file, at least the ones that are filled
            while (scanner.hasNextLine()) {

                //read next line if its there
                String line = scanner.nextLine(); 

                //we print it out for the user cause we are feeling nice
                System.out.println(line);  

                //check to see if the lines are balanced. 
                if (isBalanced(line)) {

                    //if they are, then we get this.
                    System.out.println("The pairs and orders of the symbols are correct!");
                } else {

                    //otherwise, we dont.
                    System.out.println("The pairs and orders of symbols are incorrect.");
                }
            }
        } catch (FileNotFoundException e) {
            //if the file isnt found, we hit them with one of these.
            System.out.println("File not found: " + filePath);
            e.printStackTrace(); 
        }
    }

    //this is the main code where we check to see if the symbols are matching or not
    private static boolean isBalanced(String str) {

        //we use a stack to store the openings of each symbol
        Stack<Character> stack = new Stack<>();

        //we gotta check every character in the file, so.
        for (char ch : str.toCharArray()) {
            switch (ch) {
                //if we find an opening symbol, we put it into the stack.
                case '(':
                case '{':
                case '[':

                    //which is done specifically here.
                    stack.push(ch);
                    break;

                //if we find a closing symbol. we have to make sure that there is a matching opening one.
                case ')':
                    //if the stack is empty, or if there is a mismatch, we return false. there only needs to be one wrong in order for this to happen.
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                    break;

                case '}':
                    // we do the same here, but with the curly bracket.
                    if (stack.isEmpty() || stack.pop() != '{') {
                        return false;
                    }
                    break;

                case ']':
                    // and again, but we use the regular bracket.
                    if (stack.isEmpty() || stack.pop() != '[') {
                        return false;
                    }
                    break;
            }
        }

        //now that we are done, we ensure it is empty. program complete. :)
        return stack.isEmpty();
    }
}
