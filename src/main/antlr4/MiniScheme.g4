grammar MiniScheme;

//=========== Lexeme patterns and tokens start here ==============

/* Miscellaneous and skippable lexemes. */
WHITESPACE: [ \r\n\t]+ -> skip;
COMMENT:
	'//' (.)*? NEWLINE -> skip; // Match any text that has // preceding.
fragment DIGIT: [0-9];
fragment UPPER_CASE_LTR: [a-z];
fragment LOWER_CASE_LTR: [A-Z];
fragment ANY_CASE_LTR: [a-zA-Z];
fragment UNDERSCORE: '_';
fragment SINGLE_QUOTE: '\'';
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

LOGICAL_NOT: '!';
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
STRINGLIT: '"' [.]* '"';
BOOLLIT: '#'[tf];

DEFINE: 'define';
IF:  'if';
COND: 'cond';
ELSE: 'else';

NOT: 'not';
SIN: 'sin';
COS: 'cos';
TAN: 'tan';
ASIN: 'asin';
ACOS: 'acos';
ATAN: 'atan';
SQRT: 'sqrt';
CAR: 'car';
CDR: 'cdr';
CONS: 'cons';

DISPLAY: 'display';
STRING_APPEND: 'string-append';

NUMBER_FN: 'number?';
BOOL_FN: 'bool?';
STRING_FN: 'string?';
LIST_FN: 'list?';
ZERO_FN: 'zero?';
NULL_FN: 'null?';
ATOM_FN: 'atom?';
POSITIVE_FN: 'positive?';
NEGATIVE_FN: 'negative?';
MEMBER_FN: 'member?';

ID: [a-zA-Z_-][a-zA-Z0-9_-]*;

// ================= Parser rules. ==================== //
minischeme: (vardecl | procdecl | expr)* EOF;

vardecl: OPEN_PAREN DEFINE term expr CLOSE_PAREN
       ;

procdecl: (OPEN_PAREN DEFINE (OPEN_PAREN term procparams CLOSE_PAREN)
                    procbody CLOSE_PAREN)
        ;

procparams: expr*;
procbody: expr;

expr: term                                                                      #exprTerm
    | (OPEN_PAREN CONS expr expr CLOSE_PAREN)                                   #exprCons
    | (OPEN_PAREN (PLUS | MINUS | STAR | SLASH | MODULO | EXPONENTIATION
                           | LOGICAL_GT  | LOGICAL_GE | LOGICAL_LT | LOGICAL_LE
                           | LOGICAL_EQ | LOGICAL_NE | STRING_APPEND | MEMBER_FN
                           | SIN | COS | TAN | ASIN | ACOS | ATAN | SQRT | NOT
                           | LOGICAL_AND | LOGICAL_OR | DISPLAY | NUMBER_FN
                           | BOOL_FN | STRING_FN | LIST_FN | ZERO_FN | NULL_FN
                           | ATOM_FN | CAR | CDR
                           | POSITIVE_FN | NEGATIVE_FN) expr* CLOSE_PAREN)      #exprOp
    | ((PLUS | MINUS | STAR | SLASH | MODULO | EXPONENTIATION
                | LOGICAL_GT  | LOGICAL_GE | LOGICAL_LT | LOGICAL_LE
                | LOGICAL_EQ | LOGICAL_NE | STRING_APPEND | MEMBER_FN
                | SIN | COS | TAN | ASIN | ACOS | ATAN | SQRT | NOT
                | LOGICAL_AND | LOGICAL_OR | DISPLAY | NUMBER_FN
                | BOOL_FN | STRING_FN | LIST_FN | ZERO_FN | NULL_FN
                | ATOM_FN | CAR | CDR | POSITIVE_FN | NEGATIVE_FN) expr*)       #exprOp
    | (QUOTE OPEN_PAREN expr* CLOSE_PAREN)                                      #exprList
    | (OPEN_PAREN term expr* CLOSE_PAREN)                                       #exprProcCall
    | (OPEN_PAREN IF OPEN_PAREN ifcond CLOSE_PAREN ifbody ifelse CLOSE_PAREN)   #exprIf
    | (OPEN_PAREN COND (OPEN_BRACKET OPEN_PAREN
      condcond CLOSE_PAREN condbody CLOSE_BRACKET)*
      (OPEN_BRACKET ELSE? condbody CLOSE_BRACKET) CLOSE_PAREN)                  #exprCond
    ;

unaryop: SIN | COS | TAN | ASIN | ACOS | ATAN | SQRT | NOT | LOGICAL_AND
       | LOGICAL_OR | DISPLAY | NUMBER_FN | BOOL_FN | STRING_FN | LIST_FN
       | ZERO_FN | NULL_FN | ATOM_FN | CAR | CDR | POSITIVE_FN | NEGATIVE_FN;
naryop: PLUS | MINUS | STAR | SLASH | MODULO | EXPONENTIATION
        | LOGICAL_GT  | LOGICAL_GE | LOGICAL_LT | LOGICAL_LE
        | LOGICAL_EQ | LOGICAL_NE | STRING_APPEND | MEMBER_FN;

condcond: expr;
condbody: expr;
ifcond: expr;
ifbody: expr;
ifelse: expr;

term: NUMBERLIT | CHARLIT | STRINGLIT | BOOLLIT | ID;
