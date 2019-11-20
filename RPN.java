import java.util.Scanner;
import java.util.Stack;

class RPN {

	public RPN() {
	}

	// parses a line and returns the result
	public double parse(String command) {
		Stack<Double> stack = new Stack<Double>();		
		
		// loop through string
		for (int i = 0; i < command.length(); i++) {
			// if it's a digit
			char currentChar = command.charAt(i);
			
			if (Character.isDigit(currentChar)) {
				
				// get a string of the number
				String temp = "";
				for (int j = 0; (j < 100)
						&& (Character.isDigit(command.charAt(i)) || (command.charAt(i) == '.')); j++, i++) {
					temp = temp + String.valueOf(command.charAt(i));
				}
				
				// convert char to double and add to stack
				stack.push(Double.parseDouble(String.valueOf(temp)));
			} else if (currentChar == '+') {
				stack.push(stack.pop() + stack.pop());
			} else if (currentChar == '-') {
				stack.push(stack.pop() - stack.pop());
			} else if (currentChar == '*') {
				stack.push(stack.pop() * stack.pop());
			} else if (currentChar == '/') {
				double b = stack.pop();
				double a = stack.pop();				
				stack.push(a / b);
			} else if (currentChar == '^') {
				double b = stack.pop();
				double a = stack.pop();				
				stack.push(Math.pow(a, b));
			} else if (currentChar != ' ') {
				throw new IllegalArgumentException();
			}
		}		

		// final value
		return stack.pop();
	}

	/* main method */
	public static void main(String args[]) {
		while (true) {

			try {
				Scanner in = new Scanner(System.in);
				System.out.println("Enter RPN expression or \"quit\".");
				String line = in.nextLine();

				if (line.equals("quit")) {
					break;
				} else {
					System.out.printf("Answer is %f\n", new RPN().parse(line));
				}
			} catch (Exception e) {
				System.out.print("Exception occured: " + e.getMessage());
			}
		}
	}
}