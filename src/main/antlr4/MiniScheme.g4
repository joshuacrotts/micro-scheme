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

LOGICAL_NOT: 'not';
LOGICAL_AND: 'and';
LOGICAL_OR: 'or';
LOGICAL_EQ: '=';
LOGICAL_LE: '<=';
LOGICAL_GE: '>=';
LOGICAL_LT: '<';
LOGICAL_GT: '>';
LOGICAL_NE: '!=';

BITWISE_SHL: '<<';
BITWISE_SHR: '>>';
BITWISE_NEG: '~' ;

NUMBERLIT: [+-]?[0-9]+('.'[0-9]*)?;
CHARLIT: '\'' .? '\'' ;
STRINGLIT: '"' ( QUOTCHAR | ~ ["\\] )* '"';
BOOLLIT: '#'[tf];

DEFINE: 'define';
IF:  'if';
COND: 'cond';
ELSE: 'else';
LAMBDA: 'lambda' | 'λ';

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
CAR: 'car';
CDR: 'cdr';
CONS: 'cons';

DISPLAY: 'display';
STRING_APPEND: 'string-append';
CREATE_LIST_FN: 'list';
NUMBER_FN: 'number?';
BOOL_FN: 'bool?';
STRING_FN: 'string?';
LIST_FN: 'list?';
NULL_FN: 'null?';
ATOM_FN: 'atom?';
EQ_FN: 'eq?';
EQUAL_FN: 'equal?';
MEMBER_FN: 'member?';
STRLEN_FN: 'string-length';
RANDINT_FN: 'random-integer';
RANDDOUBLE_FN: 'random-double';
RAND_FN: 'random';
READLINE_FN: 'read-line';
READINT_FN: 'read-integer';
READCHAR_FN: 'read-char';
READDOUBLE_FN: 'read-double';
STRTONUM_FN: 'string-to-number';
NUMTOSTR_FN: 'number-to-string';

ID: [a-zA-Z_-][a-zA-Z0-9_-]*('?')?;

// ================= Parser rules. ==================== //

// This is the root rule applied.
minischeme: (vardecl | procdecl | expr)* EOF;

// Variable declaration. Takes the form (define var <expr>)
vardecl: OPEN_PAREN DEFINE term expr CLOSE_PAREN;

// Procedure declaration. Takes the form (define (proc <params ...>) (<expr>))
procdecl: (OPEN_PAREN DEFINE (OPEN_PAREN term procparams? CLOSE_PAREN) procbody CLOSE_PAREN);
procparams: expr+;
procbody: expr;

// Defines an expression. An expression is either a term, "cons", an operator, a list construction,
// a procedure call, an if expression, or a cond expression.
expr: (OPEN_PAREN CONS expr expr CLOSE_PAREN)                                           #exprCons
    | (OPEN_PAREN (unaryop | naryop) expr* CLOSE_PAREN)                                 #exprOp
    | ((unaryop | naryop) expr*)                                                        #exprOp
    | (OPEN_PAREN
      (READLINE_FN | READDOUBLE_FN | READINT_FN | READCHAR_FN) CLOSE_PAREN)             #exprRead
    | (QUOTE OPEN_PAREN expr* CLOSE_PAREN)                                              #exprList
    | (OPEN_PAREN CREATE_LIST_FN expr* CLOSE_PAREN)                                     #exprList
    | (OPEN_PAREN term expr* CLOSE_PAREN)                                               #exprProcCall
    | (OPEN_PAREN LAMBDA (OPEN_PAREN lambdaParams CLOSE_PAREN)
        (OPEN_PAREN lambdaBody? CLOSE_PAREN) CLOSE_PAREN)                               #exprLambdaDecl
    | (OPEN_PAREN (OPEN_PAREN LAMBDA (OPEN_PAREN lambdaParams CLOSE_PAREN)
        (OPEN_PAREN lambdaBody? CLOSE_PAREN) CLOSE_PAREN) lambdaArgs CLOSE_PAREN)       #exprLambdaDeclCall
    | (OPEN_PAREN (OPEN_PAREN term procparams? CLOSE_PAREN) lambdaArgs CLOSE_PAREN)     #exprLambdaCall
    | (OPEN_PAREN IF OPEN_PAREN ifcond CLOSE_PAREN ifbody ifelse CLOSE_PAREN)           #exprIf
    | (OPEN_PAREN COND (OPEN_BRACKET OPEN_PAREN
        condcond CLOSE_PAREN condbody CLOSE_BRACKET)*
        (OPEN_BRACKET (ELSE)? condbody CLOSE_BRACKET) CLOSE_PAREN)                      #exprCond
    | term                                                                              #exprTerm
    ;

lambdaParams: expr+;
lambdaBody: expr;
lambdaArgs: expr+;


// Separates the "expressions" for a cond or if expression to make it clearer in the parser.
condcond: expr;
condbody: expr;
ifcond: expr;
ifbody: expr;
ifelse: expr;

// All unary operators.
unaryop: SIN | COS | TAN | ASIN | ACOS | ATAN | SQRT | ROUND
        | FLOOR | CEILING | TRUNCATE | LOGICAL_NOT | LOGICAL_AND
        | LOGICAL_OR | DISPLAY | NUMBER_FN | BOOL_FN | LIST_FN
        | EQ_FN | EQUAL_FN | NULL_FN | ATOM_FN | CAR | CDR
        | STRLEN_FN
        ;

// All n-ary operators. An n-ary operator is an operator that takes at least two parameters. The
// semantic analyzer should check to make sure the argument count is correct for binary operators.
naryop: PLUS | MINUS | STAR | SLASH | MODULO | EXPONENTIATION
      | LOGICAL_GT  | LOGICAL_GE | LOGICAL_LT | LOGICAL_LE
      | LOGICAL_EQ | LOGICAL_NE | STRING_APPEND | MEMBER_FN
      | RANDINT_FN | RANDDOUBLE_FN | RAND_FN
      ;

term: NUMBERLIT
    | CHARLIT
    | STRINGLIT
    | BOOLLIT
    | ID;
