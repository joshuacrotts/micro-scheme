grammar MiniScheme;

//=========== Lexeme patterns and tokens start here ==============

/* Miscellaneous and skippable lexemes. */
WHITESPACE: [ \r\n\t]+ -> skip;
COMMENT: ';' (.)*? NEWLINE -> skip; // Match any text that has ; preceding.
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

// Arbitrary tokens.
OPEN_PAREN: '(';
CLOSE_PAREN: ')';
OPEN_BRACKET: '[';
CLOSE_BRACKET: ']';
SINGLE_QUOTE: '\'';
BACK_TICK: '`';
COMMA: ',';
HASH: '#';
ATSIGN: '@';
PERIOD: '.';

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
APPLY: 'apply';
EVAL: 'eval';
DO: 'do';
LET: 'let';
LETSTAR: 'let*';
LETREC: 'letrec';
SET: 'set!';
SETCAR: 'set-car!';
SETCDR: 'set-cdr!';
SETVECTOR: 'vector-set!';

ID: [-+*/<>=a-zA-Z_][-+*/<>=?!a-zA-Z0-9_]*;

// ================= Parser rules. ==================== //

// This is the root rule applied.
miniScheme: (decl | expr)*;

decl: variableDeclaration
    | procedureDeclaration;

// Variable declarations take the form (define <var> <expr>.
variableDeclaration: '(' DEFINE variable expr ')';

// Procedure declarations take the form (define (<var> <expr>*) <expr>)
procedureDeclaration: '(' DEFINE '(' variable procedureParameters ')' expr ')';
procedureParameters: expr*;

// There are several different types of declarations.
expr: beginExpr
    | evalExpr
    | applyExpr
    | doExpr
    | letExpr
    | letStarExpr
    | letRecExpr
    | setExpr
    | setListExpr
    | lambdaExpr
    | condExpr
    | ifExpr
    | symbolExpr
    | quasiSymbolExpr
    | applicationExpr
    | constant
    | variable;

// A begin expression is a sequence of expressions, evaluated from left ro right.
beginExpr: '(' BEGIN expr+ ')';

// An eval expression is just EVAL followed by the expression.
evalExpr: '(' EVAL expr ')';

// Apply is an operator/procedure followed by a list.
applyExpr: '(' APPLY expr expr ')';

// A do expression takes the form (do ((<var> <expr> <expr>)*) (<test> <expr>) <seq>)
doExpr: '(' DO '(' doDecl* ')' '(' doTest doTrueExpr* ')' doBody ')';
doDecl: '(' variable expr ')'
      | '(' variable expr expr ')';
doTest: expr;
doTrueExpr: expr;
doBody: expr+;

// Let expression takes the form (let ((<var> <expr>)*) (<expr>))
letExpr: '(' LET '(' letParameters* ')' expr ')';
letStarExpr: '(' LETSTAR '(' letParameters* ')' expr ')';
letRecExpr: '(' LETREC '(' letParameters* ')' expr ')';
letParameters: ('(' expr expr ')')
             | ('[' expr expr ']');

// Set expression takes the form (set! <var> <expr>). <expr> should not be evaluated.
setExpr: '(' SET variable expr ')';

// Set-list expressions are either set-car, set-cdr, or vector-set.
setListExpr: '(' (SETCAR | SETCDR | SETVECTOR) expr+')';

// Lambda expressions take the form (lambda (<params>) <body>).
lambdaExpr: '(' LAMBDA '(' lambdaParameters ')' expr ')';
lambdaParameters: expr*;

// Cond expressions take the form (cond (<condForm>))
condExpr: '(' COND ('(' condForm ')')+ ')'
        | '(' COND ('[' condForm ']')+ ')'
        | '(' COND ('(' condForm ')')+ '(' ELSE expr ')'')'
        | '(' COND ('[' condForm ']')+ '[' ELSE expr ']'')';
condForm: expr expr;

// If expressions take the form (if expr expr expr).
ifExpr: '(' IF expr expr expr ')';

// Applications take the form (<expr> <expr>*)
applicationExpr: ( '(' expr applicationArgs ')' );
applicationArgs: expr*;

// Symbols take the form (quote | '(<expr>*) or <expr>)
symbolExpr: (QUOTE | SINGLE_QUOTE | BACK_TICK) symbolDatum;
symbolDatum: constant | variable | '(' symbolDatum PERIOD symbolDatum ')' | '(' symbolDatum* ')';

// A quasi-quoted symbol is a normal quoted symbol with 'unquote' sections.
quasiSymbolExpr: BACK_TICK quasiSymbolDatum;
quasiSymbolDatum: ((COMMA | (COMMA ATSIGN))? (constant | variable | symbolExpr | applicationExpr | '(' quasiSymbolDatum* ')'));

// Variables are, realistically, any symbol.
constant: STRINGLIT | CHARLIT | BOOLLIT | NUMBERLIT;
variable: ID;
