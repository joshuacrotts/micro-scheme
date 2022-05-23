<h1 align="center">μScheme Interpreter</h1>

<p align="center">
  <img width="200" height="200" src="docs/lambda.png">
</p>

[![CodeFactor](https://www.codefactor.io/repository/github/joshuacrotts/MicroScheme/badge)](https://www.codefactor.io/repository/github/joshuacrotts/MicroScheme) ![](https://github.com/JoshuaCrotts/MicroScheme/workflows/tests/badge.svg) ![GitHub contributors](https://img.shields.io/github/contributors/JoshuaCrotts/MicroScheme) ![GitHub commit activity](https://img.shields.io/github/commit-activity/m/JoshuaCrotts/MicroScheme) ![GitHub repo size](https://img.shields.io/github/repo-size/JoshuaCrotts/MicroScheme) [![GitHub issues open](https://img.shields.io/github/issues/JoshuaCrotts/MicroScheme)]()
[![GitHub issues closed](https://img.shields.io/github/issues-closed-raw/JoshuaCrotts/MicroScheme)]()

This is a small Scheme-subset interpreter written in Java with the ANTLR4 parsing library. Right now, it supports:

- Arbitrarily Large Numbers & Complex Numbers
- Primitive Operations
- Pairs
- Lists
- Let/Let*/Letrec 
- Lambda Expressions (can be created with `lambda` or `λ`)
- Procedures
- Variadic Arguments
- Closures with Lexical Scoping
- Vectors
- Symbols/Atoms
- Quoted Expressions
- Quasiquote Expressions
- Random Number Generators
- Format String Printing

## Example μScheme Programs

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
              [else (+ 1 (length (cdr l))))])))
```

5. Iteration with `do` loop:

```
(define sum-from-iterative
    (λ (a b) 
        (do ((i a (+ i 1)) (total 0))
            ((> i b) total)
            (set! total (+ total i)))))

(sum-from-iterative 1 10)
>>> 55
(sum-from-iterative 1 100)
>>> 5050
(sum-from-iterative 55 10000)
>>> 50003515
c
>>> 4726979370
```

6. Filter procedure:

```
(define (odd? x) (= 1 (remainder x 2)))
(define (filter predicate sequence)
    (cond [(null? sequence) '()]
          [(predicate (car sequence))
          (cons (car sequence)
                (filter predicate (cdr sequence)))]
          [else (filter predicate (cdr sequence))]))
    
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

8. Format printing via `printf`:
```
(define val 3.1415+0.75i)
(define val2 78.55)
(define val3 65093)
(printf "The ~s of ~d and ~d is ~d." "sum" val val2 (+ val val2))
(printf "~d in hex is ~x, in binary is ~b, in octal is ~o." val3 val3 val3 val3)

>>> The sum of 3.1415+0.75i and 78.55 is 81.6915+0.75i."
    65073 in hex is fe45, in binary is 1111111001000101, in octal is 177105. 
```

## Planned Features

The following is a list of features that I'd like to include in the interpreter, but are not guaranteed.

- Drawing capabilities (i.e., drawing shapes, images and other functionality)
- Structs (user-defined objects)
- File I/O
- Call stack optimization

## Dependencies

This project uses Maven, and was developed using IntelliJ. Though, it works with any IDE, so long as the ANTLR plugin is
available.

## Reporting Bugs

See the Issues Tab.

## Version History

The **master** branch encompasses all changes. The **development** branches have in-progress additions and updates that
are not yet ready for the master branch. There will most likely be other branches present in the future, each with the "
development" prefix, and a suffix denoting its purpose with a hyphen (-).
