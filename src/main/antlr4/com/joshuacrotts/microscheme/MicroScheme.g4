grammar MicroScheme;

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
NUMBERLIT: [-]?[0-9]+('.'[0-9]*)?([+-]?[0-9]+('.'[0-9]*)?[Ii])?;
STRINGLIT: '"' ( QUOTCHAR | ~ ["\\] )* '"';
CHARLIT: HASH '\\' ANYCHAR_MOD;
BOOLLIT: HASH ([tf] | ([Tt]'rue') | ([Ff]'alse'));

// Special keywords.
DEFINE: 'define' | 'DEFINE';
IF:  'if' | 'IF';
COND: 'cond' | 'COND';
OR: 'or' | 'OR';
AND: 'and' | 'AND';
ELSE: 'else' | 'ELSE';
LAMBDA: 'lambda' | 'LAMBDA' | 'λ';
BEGIN: 'begin' | 'BEGIN';
QUOTE: 'quote' | 'QUOTE';
UNQUOTE: 'unquote' | 'UNQUOTE';
UNQUOTESPLICING: 'unquote-splicing' | 'UNQUOTE-SPLICING';
WHEN: 'when' | 'WHEN';
UNLESS: 'unless' | 'UNLESS';
APPLY: 'apply' | 'APPLY';
EVAL: 'eval' | 'EVAL';
DO: 'do' | 'DO';
LET: 'let' | 'LET';
LETSTAR: 'let*' | 'LET*';
LETREC: 'letrec' | 'LETREC';
SET: 'set!' | 'SET!';
SETCAR: 'set-car!' | 'SET-CAR!';
SETCDR: 'set-cdr!' | 'SET-CDR!';
SETVECTOR: 'vector-set!' | 'VECTOR-SET!';

ID: [-+*/<>=a-zA-Z_$][-+*/<>=?!a-zA-Z0-9_$]*;

// ================= Parser rules. ==================== //

// This is the root rule applied.
microScheme: (decl | expr)*;

// Declarations are definitions - either variables or procedures.
decl: variableDeclaration
    | procedureDeclaration;

// Variable declarations take the form (define <var> <expr>.
variableDeclaration: '(' DEFINE variable expr ')';

// Procedure declarations take the form (define (<var> <expr>*) <expr>)
procedureDeclaration: ('(' DEFINE '(' variable procedureParameters ')' procedureBody ')')
                    | ('(' DEFINE  '(' variable PERIOD procedureParameters ')' procedureBody ')' );
procedureParameters: expr*;
procedureBody: expr+;

// There are several different types of declarations.
expr: beginExpr
    | evalExpr
    | applyExpr
    | whenExpr
    | unlessExpr
    | setExpr
    | setListExpr
    | letExpr
    | letStarExpr
    | letRecExpr
    | lambdaExpr
    | booleanExpr
    | condExpr
    | ifExpr
    | doExpr
    | symbolExpr
    | quasiSymbolExpr
    | applicationExpr
    | constant
    | variable;

// A begin expression is a sequence of expressions, evaluated from left ro right.
beginExpr: '(' BEGIN expr+ ')';

// We have to define AND and OR as expressions to prevent their evaluation.
booleanExpr: '(' (AND | OR) expr* ')';

// An eval expression is just EVAL followed by the expression.
evalExpr: '(' EVAL expr ')';

// Apply is an operator/procedure followed by a list.
applyExpr: '(' APPLY expr expr ')';

// When expression takes the form (WHEN expr seq)
whenExpr: '(' WHEN whenCond expr+ ')';
whenCond: expr;

// Unless expression takes the same form as WHEN.
unlessExpr: '(' UNLESS unlessCond expr+ ')';
unlessCond: expr;

// Set expression takes the form (set! <var> <expr>). <expr> should not be evaluated.
setExpr: '(' SET variable expr ')';

// Set-list expressions are either set-car, set-cdr, or vector-set.
setListExpr: '(' (SETCAR | SETCDR | SETVECTOR) expr+')';

// Let expression takes the form (let ((<var> <expr>)*) (<expr>))
letExpr: '(' LET '(' letParameters* ')' letBody ')';
letStarExpr: '(' LETSTAR '(' letParameters* ')' letBody ')';
letRecExpr: '(' LETREC '(' letParameters* ')' letBody ')';
letParameters: ('(' expr expr ')')
             | ('[' expr expr ']');
letBody: expr+;

// Lambda expressions take the form (lambda (<params>) <body>).
lambdaExpr: ('(' LAMBDA '(' lambdaParameters ')' lambdaBody ')')
          | ('(' LAMBDA '(' lambdaParameters PERIOD PERIOD PERIOD ')' lambdaBody ')');
lambdaParameters: expr*;
lambdaBody: expr+;

// A do expression takes the form (do ((<var> <expr> <expr>)*) (<test> <expr>) <seq>)
doExpr: '(' DO '(' doDecl* ')' '(' doTest doTrueExpr* ')' doBody ')';
doDecl: '(' variable expr ')'
      | '(' variable expr expr ')';
doTest: expr;
doTrueExpr: expr;
doBody: expr+;

// Cond expressions take the form (cond (<condForm>))
condExpr: '(' COND ('(' condForm ')')+ ')'
        | '(' COND ('[' condForm ']')+ ')'
        | '(' COND ('(' condForm ')')+ '(' ELSE expr ')'')'
        | '(' COND ('[' condForm ']')+ '[' ELSE expr ']'')';
condForm: expr expr;

// If expressions take the form (if expr expr expr).
ifExpr: '(' IF expr expr expr? ')';

// Applications take the form (<expr> <expr>*)
applicationExpr: ( '(' expr applicationArgs ')' );
applicationArgs: expr*;

// Symbols take the form (quote | '(<expr>*) or <expr>)
symbolExpr: (QUOTE | SINGLE_QUOTE | BACK_TICK) symbolDatumRep;
symbolDatum: constant | variable | LAMBDA;
symbolDatumRep: (symbolDatum
                | ('(' symbolDatumRep PERIOD symbolDatumRep ')')
                | ('(' symbolDatumRep* ')'));

// A quasi-quoted symbol is a normal quoted symbol with 'unquote' sections.
quasiSymbolExpr: BACK_TICK quasiSymbolDatumRep;
quasiSymbolDatum: (constant | variable | symbolExpr | evalExpr | applyExpr | applicationExpr);
quasiSymbolDatumRep: ((COMMA | (COMMA ATSIGN))?
                      (quasiSymbolDatum
                      | ('(' quasiSymbolDatumRep PERIOD quasiSymbolDatumRep ')')
                      | ('(' quasiSymbolDatumRep* ')')));

// Variables are, realistically, any symbol.
constant: STRINGLIT | CHARLIT | BOOLLIT | NUMBERLIT;
variable: ID;
