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
HASH: '#';

// Logical operators.
LOGICAL_NOT: 'not';
LOGICAL_AND: 'and';
LOGICAL_OR: 'or';

// Bitwise operations.
BITWISE_SHL: '<<';
BITWISE_SHR: '>>';
BITWISE_NEG: '~' ;

// Literals.
NUMBERLIT: [+-]?[0-9]+('.'[0-9]*)?;
STRINGLIT: '"' ( QUOTCHAR | ~ ["\\] )* '"';
CHARLIT: HASH '\\' ANYCHAR_MOD;
BOOLLIT: HASH ([tf] | ([Tt]'rue') | ([Ff]'alse'));

// Special keywords.
DEFINE: 'define';
IF:  'if';
COND: 'cond';
ELSE: 'else';
LAMBDA: 'lambda' | 'λ';
BEGIN: 'begin';
QUOTE: 'quote';


ID: [-+*/<>=a-zA-Z_][-+*/<>=?!a-zA-Z0-9_]*;

// ================= Parser rules. ==================== //

// This is the root rule applied.
miniScheme: (decl | expr)*;

decl: variableDeclaration
    | procedureDeclaration;

// Variable declarations take the form (define <var> <expr>.
variableDeclaration: '(' DEFINE variable expr ')';

// Procedure declarations take the form (define (<var> <expr>*) <expr>)
procedureDeclaration: '(' DEFINE '(' variable expr* ')' expr ')';


// There are several different types of declarations.
expr: lambdaExpr
    | condExpr
    | ifExpr
    | applicationExpr
    | constant
    | variable;


// Lambda expressions take the form (lambda (<params>) <body>).
lambdaExpr: '(' LAMBDA '(' lambdaParameters ')' expr ')';
lambdaParameters: expr*;

// Cond expressions take the form (cond (<condForm>))
condExpr: '(' COND ('(' condForm ')')+ ')'
        | '(' COND ('(' condForm ')')+ '(' ELSE expr ')'')';
condForm: expr expr;

// If expressions take the form (if expr expr) (if expr expr expr).
ifExpr: '(' IF expr expr expr ')'
      | '(' IF expr expr ')';

// Applications take the form (<expr> <expr>*)
applicationExpr: '(' expr applicationArgs ')';
applicationArgs: expr*;

// Variables are, realistically, any symbol.
constant: STRINGLIT | CHARLIT | BOOLLIT | NUMBERLIT;
variable: ID;
