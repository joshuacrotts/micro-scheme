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
HASH: '#';

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
STRINGLIT: '"' ( QUOTCHAR | ~ ["\\] )* '"';
BOOLLIT: HASH ([tf] | ([Tt]'rue') | ([Ff]'alse'));

// Special keywords.
DEFINE: 'define';
IF:  'if';
COND: 'cond';
ELSE: 'else';
DO: 'do';
LET: 'let';
LETSTAR: 'let*';
LETREC: 'letrec';
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

// Vector procedures.
CREATE_VECTOR_FN: 'vector';
VECTOR_REF_FN: 'vector-ref';
VECTORLEN_FN: 'vector-length';

// Miscellaneous procedures.
DISPLAY: 'display';

// String procedures.
STRAPPEND_FN: 'string-append';
STRLEN_FN: 'string-length';
STREQ_FN: 'string=?';
STRLE_FN: 'string<=?';
STRGE_FN: 'string>=?';
STRLT_FN: 'string<?';
STRGT_FN: 'string>?';
STRSUBSTR: 'substring';

// T/F predicate procedures.
NUMBER_FN: 'number?';
BOOL_FN: 'bool?';
STRING_FN: 'string?';
LIST_FN: 'list?';
VECTOR_FN: 'vector?';
NULL_FN: 'null?';
SYMBOL_FN: 'symbol?';
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
SETVAR_FN: 'set!';
SETCAR_FN: 'set-car!';
SETCDR_FN: 'set-cdr!';
SETVEC_FN: 'vector-set!';

ID: [a-zA-Z_=][<>a-zA-Z0-9_=-]*[=?!]?;

// ================= Parser rules. ==================== //

// This is the root rule applied.
miniScheme: (decl | expr)*;


// Declarations of lambdas, variables, procedures.
decl: lambdaDecl
    | varDecl
    | varDeclRead
    | procDecl;


// Different definitions.
varDecl:     '(' DEFINE term expr ')';
varDeclRead: '(' DEFINE term '(' readop ')' ')';
procDecl:    '(' DEFINE '(' term procParams? ')' procBody ')';
lambdaDecl:  '(' DEFINE term '(' LAMBDA '(' lambdaParams? ')' lambdaBody ')' ')';


// Defines an expression.
expr: exprCons
    | exprSet
    | exprSetRead
    | exprOp
    | exprVector
    | exprList
    | exprCall
    | exprLambdaDecl
    | exprLambdaDeclCall
    | exprIf
    | exprCond
    | exprDo
    | exprLetDecl
    | exprSymbol
    | exprTerm;


// Different types of expressions.
// Cons pair creation.
exprCons: '(' CONS expr expr ')';


// Set! a variable to an expr.
exprSet: '(' setop term expr+ ')';


// Set! a variable to some value read in from the user.
exprSetRead: '(' setop term '(' readop ')' ')';


// Operator expression.
exprOp: ('(' (unaryop | binaryop | ternaryop | naryop) expr* ')')
      | ((unaryop | binaryop | ternaryop | naryop) expr*);


// Creation of a vector.
exprVector: '(' CREATE_VECTOR_FN '(' expr* ')'')';


// Creation of a list.
exprList: ('(' CREATE_LIST_FN expr* ')');


// Calling a procedure or procedure with lambda args.
exprCall: ('(' term args? ')')
        | ('(' '(' term args? ')' lambdaArgs? ')');


// Declaration of a lambda inside an expression.
exprLambdaDecl: '(' LAMBDA '(' lambdaParams? ')' lambdaBody ')';


// Declaration of a lambda followed by immediately calling it.
exprLambdaDeclCall: '(' '(' LAMBDA '(' lambdaParams? ')' lambdaBody ')' lambdaArgs? ')';


// If expression.
exprIf: '(' IF ifCond ifBody ifElse ')';


// Cond expression.
exprCond: ('(' COND ('[' condCond condBody ']')+ ('[' ELSE condBody ']')? ')')
        | ('(' COND ('(' condCond condBody ')')+ ('(' ELSE condBody ')')? ')');


// Do expression.
exprDo: '(' DO '(' doDecl? ')' '(' doTest ')' expr ')';


// Let declaration.
exprLetDecl: '(' (exprLetNamed | LET | LETSTAR | LETREC) '(' letDecl? ')' expr ')';
exprLetNamed: LET ID;

// Symbol declaration.
exprSymbol: (QUOTE exprSymbolComponent) ;
exprSymbolComponent: ('(' exprSymbolComponent* ')') | term | op | exprCall | exprSymbol;


// Term expression.
exprTerm: term;


// Components of expressons.
procParams      : expr+;
procBody        : expr;
args            : expr+;
lambdaParams    : expr+;
lambdaBody      : expr;
lambdaArgs      : expr+;
letDecl         : ('[' term expr ']')*
                | ('(' term expr ')')*;
doDecl          : ( '[' term expr '[' expr ']' ']' )*
                | ( '(' term expr '(' expr ')' ')' )*;
doTest          : ('[' expr ']')
                | ('(' expr ')');

// Separates the "expressions" for a cond or if expression to make it clearer in the parser.
condCond: expr;
condBody: expr;
ifCond: expr;
ifBody: expr;
ifElse: expr;

// All operators.
op: unaryop | binaryop | ternaryop | naryop;

// All unary operators.
unaryop: SIN | COS | TAN | ASIN | ACOS | ATAN | SQRT | ROUND
        | FLOOR | CEILING | TRUNCATE | DISPLAY | NUMBER_FN | STRING_FN
        | BOOL_FN | LIST_FN | NULL_FN | SYMBOL_FN | VECTOR_FN
        | CAR | CDR | STRLEN_FN | PAIR_FN | STRTONUM_FN | NUMTOSTR_FN
        | TODEG_FN | TORAD_FN | LOGICAL_NOT | TRUE_FN | FALSE_FN
        | VECTORLEN_FN;


// All binary operators.
binaryop: LOGICAL_GT | LOGICAL_GE | LOGICAL_LT | LOGICAL_LE
        | LOGICAL_EQ | LOGICAL_NE | STREQ_FN | STRLT_FN
        | STRLE_FN | STRGT_FN | STRGE_FN | MEMBER_FN
        | RANDINT_FN | RANDDOUBLE_FN | VECTOR_REF_FN;


// All ternary operators.
ternaryop: STRSUBSTR;


// All n-ary operators. An n-ary operator is an operator that takes either 0 or >= 2 arguments. The
// semantic analyzer should check to make sure the argument count is correct for binary operators.
naryop: PLUS | MINUS | STAR | SLASH | MODULO | EXPONENTIATION
      | STRAPPEND_FN | RAND_FN | EQ_FN | EQUAL_FN | LOGICAL_AND
      | LOGICAL_OR;


// "Set" operations - allows redefining of variables.
setop: SETCAR_FN | SETCDR_FN | SETVAR_FN | SETVEC_FN;


// "Read" operations.
readop: READLINE_FN | READNUMBER_FN;


// Identifier and literals.
term: NUMBERLIT
    | STRINGLIT
    | BOOLLIT
    | ID;
