<h1 align="center">MiniScheme Interpreter</h1>

<p align="center">
  <img width="200" height="200" src="docs/lambda.png">
</p>

[![CodeFactor](https://www.codefactor.io/repository/github/joshuacrotts/MiniScheme/badge)](https://www.codefactor.io/repository/github/joshuacrotts/MiniScheme) ![](https://github.com/JoshuaCrotts/MiniScheme/workflows/tests/badge.svg) ![GitHub contributors](https://img.shields.io/github/contributors/JoshuaCrotts/MiniScheme) ![GitHub commit activity](https://img.shields.io/github/commit-activity/m/JoshuaCrotts/MiniScheme) ![GitHub repo size](https://img.shields.io/github/repo-size/JoshuaCrotts/MiniScheme) [![GitHub issues open](https://img.shields.io/github/issues/JoshuaCrotts/MiniScheme)]() 
[![GitHub issues closed](https://img.shields.io/github/issues-closed-raw/JoshuaCrotts/MiniScheme)]()

This is a small Scheme interpreter written in Java with the ANTLR4 parsing library. Right now, it supports primitive operations, pairs, lists, (simple) lambdas, procedures, and other operators not native to Scheme (e.g., user I/O, random number generators, and other operators such as ** for exponentiation).

## Example MiniScheme Program
... to be written.

## Planned Features

The following is a list of features that I'd like to include in the interpreter, but are not guaranteed.

- Arbitrary large numbers (right now only double-precision numbers are available)
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
