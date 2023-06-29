Q2
Problem Statement
Write a program that accepts multiple mathematical expressions and evaluates each of them using a public Web API. The program should display the result of each expression on the console.

The code provided uses the mathjs.org API to evaluate the expressions. It makes HTTP requests to the API and reads the response to obtain the results.

Execution
Follow these steps to execute the code:

Open a text editor and create a new file.
Copy the Java code provided into the file.
Save the file with the name Q2.java. Make sure to use the .java extension.
Open a terminal or command prompt and navigate to the directory where you saved the Java file.
Compile the Java file by running the following command: javac Q2.java
This will generate a bytecode file named Q2.class in the same directory if there are no compilation errors.
Run the compiled Java program by executing the following command: java Q2
This will run the program, and the output will be displayed in the terminal or command prompt.
Note: Make sure you have Java Development Kit (JDK) installed on your system and the javac and java commands are accessible from the command line.


Unit Test Cases

Unit Test Cases are attached that includes a single test method testEvaluateExpressions(), which tests the evaluateExpression() method of the Q2 class. It verifies the correctness of the expression evaluation by comparing the expected results with the actual results returned by the method.

To run these test cases, you'll need to set up a testing framework such as JUnit in your development environment. Make sure to include the necessary dependencies and configurations for running JUnit tests.