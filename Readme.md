## A better way:
 - No messing around with mutable variable
 - Iteration steps wrapped under the hood
 - Less clutter
 - Better clarity: retrains our focus
 - Less impedance: code closely trails the business intent
 - Less error prone
 - Easier to understand and maintain

## Evolution, not revolution
 - Be declarative
 - Promote immutability ("Treat objects as immutable" Effective Java)
 - Avoid side effects (side effects in function)
 - Prefer expressions to statements (statements are stubborn and force mutation; Expression promote immutability and function composition)
 - Design with-higher order functions (reuse small, focused, cohesive, and well-written functions)

## Higher order functions
 - Pass functions to function
 - Create functions within functions
 - Return functions from functions

## Java streams
A Java Stream is a component that is capable of internal iteration of its elements, meaning it can iterate its elements itself. In contrast, when you are using the Java Collections iteration features (e.g a Java Iterator or the Java for-each loop used with a Java Iterable) you have to implement the iteration of the elements yourself. 

## Todo
### Java functional interfaces

name                  | paramater | return| Finished |
----------------------|-------|----|-----------------|
   Supplier       | ()    |  x | :white_check_mark: |
   Consumer       | x     | () | :heavy_check_mark:|
   Callable       | ()    | x throws ex | :heavy_check_mark:|
   Runnable       | ()    | () | :white_check_mark:|
   Function       | x     | y | :white_check_mark:|
   BiFunction     | x,y   | z | :white_check_mark:|
   Predicate      | x     | boolean | :white_check_mark:|
   UnaryOperator  | x1    | x2 | :white_check_mark:|
   BinaryOperator | x1,x2 | x3 | :white_check_mark:|

Explanation and basic examples