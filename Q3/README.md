Task-3 Debugging
Problem Statement
Given below is a Bash/Python script that performs the following computations on an integer input (n):

If n is less than 10: Calculate its square
Example: 4 => 16

If n is between 10 and 20: Calculate the factorial of (n-10)
Example: 15 => 120

If n is greater than 20: Calculate the sum of all integers between 1 and (n-20)
Example: 25 => 15

The task is to identify the bugs in the script, fix them, and share the new script. Only one of the two scripts (Bash or Python) is required.

Bash Script Bug Fixes:

1) In the for loop, the condition should be i<=$LIM instead of i<$LIM to include the limit value.

Python Script Bug Fixes:

1) In the for loop, changed the range from range(1, n-10) to range(1, n-9) to include the limit value.

2) Used floor division operator (//) instead of regular division (/) in the last computation.