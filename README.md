<h1 align="center">MiniScheme Interpreter</h1>

<p align="center">
  <img width="200" height="200" src="docs/lambda.png">
</p>

[![CodeFactor](https://www.codefactor.io/repository/github/joshuacrotts/MiniScheme/badge)](https://www.codefactor.io/repository/github/joshuacrotts/MiniScheme) ![](https://github.com/JoshuaCrotts/MiniScheme/workflows/tests/badge.svg) ![GitHub contributors](https://img.shields.io/github/contributors/JoshuaCrotts/MiniScheme) ![GitHub commit activity](https://img.shields.io/github/commit-activity/m/JoshuaCrotts/MiniScheme) ![GitHub repo size](https://img.shields.io/github/repo-size/JoshuaCrotts/MiniScheme) [![GitHub issues open](https://img.shields.io/github/issues/JoshuaCrotts/MiniScheme)]() 
[![GitHub issues closed](https://img.shields.io/github/issues-closed-raw/JoshuaCrotts/MiniScheme)]()

This is a small Scheme interpreter written in Java with the ANTLR4 parsing library. Right now, it supports:

- Primitive Operations 
- Pairs
- Lists 
- Lambda Expressions (can be created with `lambda` or `λ`)
- Procedures
- Vectors
- Symbols/Atoms
- Quoted Expressions
- User I/O
- Random Number Generators

## Example MiniScheme Programs
1. Defining a variable:
```
(define var 5)
```

2. Defining a procedure:
```
(define (factorial n)
    (if (<= n 1)
        1
        (* n (factorial (- n 1)))))
        
(factorial 6) 
>>> 720        
```

3. Defining a lambda:
```
(define fib
    (λ (n)
       (if (<= n 2)
           1
           (+ (fib (- n 1)) (fib (- n 2))))))

(fib 5)
>>> 5            
```

4. Length of a list with `cond`:
```
(define length 
    (λ (l)
        (cond [(null? l) 0]
              [else (+ 1 (length (cdr l))))]
        )))
```

5. Iteration with `do` loop:
```
(define sum-from-iterative
    (λ (a b) 
        (do ((i a) (total 0)) ((i (+ i 1)))
            ((> i b) total)
            (set! total (+ total i))
        )))

(sum-from-iterative 1 10)
>>> 55
(sum-from-iterative 1 100)
>>> 5050
(sum-from-iterative 55 10000)
>>> 50003515
(sum-from-iterative 77 97231)
>>> 4.72697937E9
```

6. Filter procedure:
```
(define (odd? x) (= 1 (remainder x 2)))
(define (filter predicate sequence)
    (cond [(null? sequence) '()]
          [(predicate (car sequence))
          (cons (car sequence)
                (filter predicate (cdr sequence)))]
          [else (filter predicate (cdr sequence))]
    ))
    
(filter odd? (list 1 2 3 4 5))
>>> (1 3 5)
```

7. Accumulate procedure (using passed lambda):
```
(define (accumulate op initial sequence)
  (if (null? sequence)
      initial
      (op (car sequence)
          (accumulate op initial (cdr sequence)))))
                              
(accumulate (λ (x y) (+ x y)) 0 (list 1 2 3 4 5))
>>> 15
```

## Planned Features

The following is a list of features that I'd like to include in the interpreter, but are not guaranteed.

- Arbitrary large numbers (right now, only Java double-precision numbers are available)
- Drawing capabilities (i.e., drawing shapes, images and other functionality)
- Structs (user-defined objects)
- File I/O
- Format printing
- Variable number of arguments (varargs)
- Call stack optimization

## Dependencies

This project uses Maven, and was developed using IntelliJ. Though, it works with any IDE, so long as the ANTLR plugin is available. 

## Reporting Bugs

See the Issues Tab.

## Version History
The **master** branch encompasses all changes. The **development** branches have in-progress additions and updates that are not yet ready for the master branch. There will most likely be other branches present in the future, each with the "development" prefix, and a suffix denoting its purpose with a hyphen (-).
