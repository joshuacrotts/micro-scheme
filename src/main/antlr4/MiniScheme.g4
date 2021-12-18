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
PLUS: '+';
MINUS: '-';
STAR: '*';
SLASH: '/';
AMPERSAND: '&';
PIPE: '|';
CARAT: '^';
MODULO: '%';
EXPONENTIATION: '**';
EQUAL: '=';

LOGICAL_NOT: '!';
LOGICAL_AND: '&&';
LOGICAL_OR: '||';
LOGICAL_EQ: '==';
LOGICAL_LE: '<=';
LOGICAL_GE: '>=';
LOGICAL_LT: '<';
LOGICAL_GT: '>';
LOGICAL_NE: '!=';

BITWISE_SHL: '<<';
BITWISE_SHR: '>>';
BITWISE_NEG: '~' ;

INCREMENT: '++';
DECREMENT: '--';

NUMBERLIT: [+-]?[0-9]+('.'[0-9]*)?;
CHARLIT: '\'' .? '\'' ;
STRINGLIT: '"' [.]* '"';

DEFINE: 'define';
ID: [a-zA-Z_][a-zA-Z0-9_]*;

// ================= Parser rules. ==================== //
minischeme: (vardecl | procdecl | expr)* EOF;

vardecl: OPEN_PAREN DEFINE term expr CLOSE_PAREN
       ;

procdecl: (OPEN_PAREN DEFINE (OPEN_PAREN term procparams CLOSE_PAREN)
                    procbody CLOSE_PAREN)
        ;

procparams: expr*;
procbody: expr;

expr: (OPEN_PAREN (PLUS | MINUS | STAR | SLASH) expr* CLOSE_PAREN)      #exprOp
    | (OPEN_PAREN term expr* CLOSE_PAREN)                               #exprProcCall
    | term                                                              #exprTerm
    ;

term: NUMBERLIT | CHARLIT | STRINGLIT | ID;
