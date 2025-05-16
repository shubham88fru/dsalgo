package algorithms;

/*
* Matrix exponentiation is a powerful optimization technique,
* especially useful in problems involving linear recurrences.
* e.g. recurrences like fibonacci --> F(n) = F(n-1) + F(n-2)
*
* Per matrix exponentiation, for above such linear recurrence,
* of degree 2, we can say -
* [F(n) F(n-1)]' = T^(n-1) * [F(1) F(0)]'
*
* where T is a M*M matrix where M is the degree of recurrence
* relation (i.e. the number of previous states the recurrence
* relation depends on. In case of fibonacci its 2)
*
* e.g. for F(n) = F(n-1) + F(n-2) + F(n-3)
* The equations will be -
* [F(n) F(n-1) F(n-2)]' = T^(n-1) * [F(2) F(1) F(0)]'
*
* and T will be  a 3*3 matrix.
*
* Idea of matrix exponentiation is to calculate
* the power of a matrix i.e. T^n in this case.
* Which is very similar to the binary exponentiation problem.
* */
public class MatrixExponentiation {

}
