grammar MiniScheme;

//=========== Lexeme patterns and tokens start here ==============

/* Miscellaneous and skippable lexemes. */
WHITESPACE: [ \r\n\t]+ -> skip;
COMMENT:
	';' (.)*? NEWLINE -> skip; // Match any text that has ; preceding.
fragment DIGIT: [0-9];
fragment UPPER_CASE_LTR: [a-z];
fragment LOWER_CASE_LTR: [A-Z];
fragment ANY_CASE_LTR: [a-zA-Z];
fragment UNDERSCORE: '_';
fragment SINGLE_QUOTE: '\'';
fragment QUOTCHAR: '\\' .;
fragment DOUBLE_QUOTE: '"';
fragment ANYCHAR: .;
fragment NEWLINE: '\n';
fragment CARRIAGE_RET: '\r';
fragment TAB: '\t';
fragment NULL_CHAR: '\\0';
fragment ESCAPED_CHAR: ('\\' .);
fragment ANYCHAR_MOD: (.+?);
// Requires at least ONE character, whether it's special or not. If it's an empty char, that's the parser's problem.

// Arbitrary tokens.
OPEN_PAREN: '(';
CLOSE_PAREN: ')';
OPEN_BRACKET: '[';
CLOSE_BRACKET: ']';
PLUS: '+';
MINUS: '-';
STAR: '*';
SLASH: '/';
AMPERSAND: '&';
PIPE: '|';
CARAT: '^';
MODULO: '%';
EXPONENTIATION: '**';
QUOTE: '\'';

// Logical operators.
LOGICAL_NOT: 'not';
LOGICAL_AND: 'and';
LOGICAL_OR: 'or';
LOGICAL_EQ: '=';
LOGICAL_LE: '<=';
LOGICAL_GE: '>=';
LOGICAL_LT: '<';
LOGICAL_GT: '>';
LOGICAL_NE: '!=';

// Bitwise operations.
BITWISE_SHL: '<<';
BITWISE_SHR: '>>';
BITWISE_NEG: '~' ;

// Literals.
NUMBERLIT: [+-]?[0-9]+('.'[0-9]*)?;
CHARLIT: '\'' .? '\'' ;
STRINGLIT: '"' ( QUOTCHAR | ~ ["\\] )* '"';
BOOLLIT: '#'[tf];

// Special keywords.
DEFINE: 'define';
IF:  'if';
COND: 'cond';
ELSE: 'else';
LET: 'let';
LAMBDA: 'lambda' | 'λ';

// Math procedures.
SIN: 'sin';
COS: 'cos';
TAN: 'tan';
ASIN: 'asin';
ACOS: 'acos';
ATAN: 'atan';
SQRT: 'sqrt';
ROUND: 'round';
FLOOR: 'floor';
CEILING: 'ceiling';
TRUNCATE: 'truncate';
TODEG_FN: 'radians->degrees';
TORAD_FN: 'degrees->radians';

// List procedures.
CREATE_LIST_FN: 'list';
CAR: 'car';
CDR: 'cdr';
CONS: 'cons';

// Miscellaneous procedures.
DISPLAY: 'display';

// String procedures.
STRING_APPEND: 'string-append';
STRLEN_FN: 'string-length';

// T/F predicate procedures.
NUMBER_FN: 'number?';
BOOL_FN: 'bool?';
STRING_FN: 'string?';
LIST_FN: 'list?';
NULL_FN: 'null?';
ATOM_FN: 'atom?';
PAIR_FN: 'pair?';
EQ_FN: 'eq?';
EQUAL_FN: 'equal?';
MEMBER_FN: 'member?';
TRUE_FN: 'true?';
FALSE_FN: 'false?';

// Random number procedures.
RANDINT_FN: 'random-integer';
RANDDOUBLE_FN: 'random-double';
RAND_FN: 'random';

// Input procedures.
READLINE_FN: 'read-line';
READNUMBER_FN: 'read-number';

// String cast procedures.
STRTONUM_FN: 'string->number';
NUMTOSTR_FN: 'number->string';

// Set (setting variable) procedures.
SETCAR_FN: 'set-car!';
SETCDR_FN: 'set-cdr!';
SETVAR_FN: 'set!';

ID: [a-zA-Z_-][<>a-zA-Z0-9_-]*('?')?;

// ================= Parser rules. ==================== //

// This is the root rule applied.
miniScheme: (decl | expr)* EOF;

// Declaration of an identifier. Takes the form (define var <expr>)
decl: (OPEN_PAREN DEFINE term expr CLOSE_PAREN)                                             #varDecl
    | (OPEN_PAREN DEFINE term OPEN_PAREN readop CLOSE_PAREN CLOSE_PAREN)                    #varDeclRead
    | (OPEN_PAREN DEFINE (OPEN_PAREN term procParams? CLOSE_PAREN) procBody CLOSE_PAREN)    #procDecl
    ;

// Defines an expression. An expression is either a term, "cons", an operator, a list construction,
// a procedure call, an if expression, or a cond expression.
expr: (OPEN_PAREN CONS expr expr CLOSE_PAREN)                                       #exprCons
    | (OPEN_PAREN setop term expr CLOSE_PAREN)                                      #exprSet
    | (OPEN_PAREN setop term OPEN_PAREN readop CLOSE_PAREN CLOSE_PAREN)             #exprSetRead
    | (OPEN_PAREN (unaryop | naryop) expr* CLOSE_PAREN)                             #exprOp
    | ((unaryop | naryop) expr*)                                                    #exprOp
    | (QUOTE OPEN_PAREN expr* CLOSE_PAREN)                                          #exprList
    | (OPEN_PAREN CREATE_LIST_FN expr* CLOSE_PAREN)                                 #exprList
    | (OPEN_PAREN term args? CLOSE_PAREN)                                           #exprProcCall
    | (OPEN_PAREN (OPEN_PAREN LAMBDA (OPEN_PAREN lambdaParams? CLOSE_PAREN)
        lambdaBody CLOSE_PAREN) lambdaArgs? CLOSE_PAREN)                            #exprLambdaDeclCall
    | (OPEN_PAREN LAMBDA (OPEN_PAREN lambdaParams? CLOSE_PAREN)
        lambdaBody CLOSE_PAREN)                                                     #exprLambdaDecl
    | (OPEN_PAREN (OPEN_PAREN term args? CLOSE_PAREN) lambdaArgs? CLOSE_PAREN)      #exprLambdaCall
    | (OPEN_PAREN IF OPEN_PAREN ifCond CLOSE_PAREN ifBody ifElse CLOSE_PAREN)       #exprIf
    | (OPEN_PAREN COND (OPEN_BRACKET OPEN_PAREN
        condCond CLOSE_PAREN condBody CLOSE_BRACKET)*
        (OPEN_BRACKET (ELSE)? condBody CLOSE_BRACKET) CLOSE_PAREN)                  #exprCond
    | term                                                                          #exprTerm
    ;

procParams: expr+;
procBody: expr;
args: expr+;
lambdaParams: expr+;
lambdaBody: expr;
lambdaArgs: expr+;

// Separates the "expressions" for a cond or if expression to make it clearer in the parser.
condCond: expr;
condBody: expr;
ifCond: expr;
ifBody: expr;
ifElse: expr;

// All unary operators.
unaryop: SIN | COS | TAN | ASIN | ACOS | ATAN | SQRT | ROUND
        | FLOOR | CEILING | TRUNCATE | LOGICAL_NOT | LOGICAL_AND
        | LOGICAL_OR | DISPLAY | NUMBER_FN | BOOL_FN | LIST_FN
        | EQ_FN | EQUAL_FN | NULL_FN | ATOM_FN | CAR | CDR
        | STRLEN_FN | PAIR_FN | TRUE_FN | FALSE_FN | STRTONUM_FN
        | NUMTOSTR_FN | TODEG_FN | TORAD_FN
        ;

// All n-ary operators. An n-ary operator is an operator that takes at least two parameters. The
// semantic analyzer should check to make sure the argument count is correct for binary operators.
naryop: PLUS | MINUS | STAR | SLASH | MODULO | EXPONENTIATION
      | LOGICAL_GT  | LOGICAL_GE | LOGICAL_LT | LOGICAL_LE
      | LOGICAL_EQ | LOGICAL_NE | STRING_APPEND | MEMBER_FN
      | RANDINT_FN | RANDDOUBLE_FN | RAND_FN
      ;

// "Set" operations - allows redefining of variables.
setop: SETCAR_FN | SETCDR_FN | SETVAR_FN
     ;

readop: READLINE_FN | READNUMBER_FN
      ;

term: NUMBERLIT
    | CHARLIT
    | STRINGLIT
    | BOOLLIT
    | ID;
