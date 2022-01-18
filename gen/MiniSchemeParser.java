// Generated from C:/Users/Joshua/Desktop/Files/Java/MiniScheme/src/main/antlr4/com/joshuacrotts/minischeme\MiniScheme.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniSchemeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WHITESPACE=1, COMMENT=2, OPEN_PAREN=3, CLOSE_PAREN=4, OPEN_BRACKET=5, 
		CLOSE_BRACKET=6, PLUS=7, MINUS=8, STAR=9, SLASH=10, AMPERSAND=11, PIPE=12, 
		CARAT=13, PERCENT=14, EXPONENTIATION=15, SINGLE_QUOTE=16, HASH=17, LOGICAL_NOT=18, 
		LOGICAL_AND=19, LOGICAL_OR=20, LOGICAL_EQ=21, LOGICAL_LE=22, LOGICAL_GE=23, 
		LOGICAL_LT=24, LOGICAL_GT=25, BITWISE_SHL=26, BITWISE_SHR=27, BITWISE_NEG=28, 
		NUMBERLIT=29, STRINGLIT=30, CHARLIT=31, BOOLLIT=32, DEFINE=33, IF=34, 
		COND=35, DO=36, ELSE=37, LET=38, LETSTAR=39, LETREC=40, LAMBDA=41, BEGIN=42, 
		QUOTE=43, SIN=44, COS=45, TAN=46, ASIN=47, ACOS=48, ATAN=49, SINH=50, 
		COSH=51, TANH=52, SQRT=53, ROUND=54, FLOOR=55, CEILING=56, TRUNCATE=57, 
		REMAINDER=58, MODULO=59, TODEG_FN=60, TORAD_FN=61, CREATE_LIST_FN=62, 
		CAR=63, CDR=64, CONS=65, CREATE_VECTOR_FN=66, VECTOR_REF_FN=67, VECTORLEN_FN=68, 
		DISPLAY=69, STRAPPEND_FN=70, STRLEN_FN=71, STREQ_FN=72, STRLE_FN=73, STRGE_FN=74, 
		STRLT_FN=75, STRGT_FN=76, STRSUBSTR=77, NUMBER_FN=78, BOOL_FN=79, CHAR_FN=80, 
		STRING_FN=81, LIST_FN=82, VECTOR_FN=83, NULL_FN=84, SYMBOL_FN=85, PAIR_FN=86, 
		EQ_FN=87, EQUAL_FN=88, RANDINT_FN=89, RANDDOUBLE_FN=90, RAND_FN=91, READLINE_FN=92, 
		READNUMBER_FN=93, STRTONUM_FN=94, NUMTOSTR_FN=95, STRTOLIST_FN=96, LISTTOSTR_FN=97, 
		SETVAR_FN=98, SETCAR_FN=99, SETCDR_FN=100, SETVEC_FN=101, ID=102;
	public static final int
		RULE_miniScheme = 0, RULE_decl = 1, RULE_expr = 2, RULE_exprCons = 3, 
		RULE_exprSet = 4, RULE_exprSetRead = 5, RULE_exprOp = 6, RULE_exprVector = 7, 
		RULE_exprList = 8, RULE_exprCall = 9, RULE_exprLambdaDecl = 10, RULE_exprLambdaDeclCall = 11, 
		RULE_exprIf = 12, RULE_exprCond = 13, RULE_exprDo = 14, RULE_exprLetDecl = 15, 
		RULE_exprLetNamed = 16, RULE_exprSymbol = 17, RULE_exprSymbolComponent = 18, 
		RULE_exprTerm = 19, RULE_exprBegin = 20, RULE_procParams = 21, RULE_procBody = 22, 
		RULE_args = 23, RULE_lambdaParams = 24, RULE_lambdaBody = 25, RULE_lambdaArgs = 26, 
		RULE_letDecl = 27, RULE_letBody = 28, RULE_doDecl = 29, RULE_doStepDecl = 30, 
		RULE_doTestDecl = 31, RULE_doTrueExpr = 32, RULE_doBody = 33, RULE_seq = 34, 
		RULE_condCond = 35, RULE_condBody = 36, RULE_ifCond = 37, RULE_ifBody = 38, 
		RULE_ifElse = 39, RULE_op = 40, RULE_unaryop = 41, RULE_binaryop = 42, 
		RULE_ternaryop = 43, RULE_naryop = 44, RULE_setop = 45, RULE_readop = 46, 
		RULE_term = 47, RULE_variable = 48, RULE_constant = 49;
	private static String[] makeRuleNames() {
		return new String[] {
			"miniScheme", "decl", "expr", "exprCons", "exprSet", "exprSetRead", "exprOp", 
			"exprVector", "exprList", "exprCall", "exprLambdaDecl", "exprLambdaDeclCall", 
			"exprIf", "exprCond", "exprDo", "exprLetDecl", "exprLetNamed", "exprSymbol", 
			"exprSymbolComponent", "exprTerm", "exprBegin", "procParams", "procBody", 
			"args", "lambdaParams", "lambdaBody", "lambdaArgs", "letDecl", "letBody", 
			"doDecl", "doStepDecl", "doTestDecl", "doTrueExpr", "doBody", "seq", 
			"condCond", "condBody", "ifCond", "ifBody", "ifElse", "op", "unaryop", 
			"binaryop", "ternaryop", "naryop", "setop", "readop", "term", "variable", 
			"constant"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'('", "')'", "'['", "']'", "'+'", "'-'", "'*'", "'/'", 
			"'&'", "'|'", "'^'", "'%'", "'**'", "'''", "'#'", "'not'", "'and'", "'or'", 
			"'='", "'<='", "'>='", "'<'", "'>'", "'<<'", "'>>'", "'~'", null, null, 
			null, null, "'define'", "'if'", "'cond'", "'do'", "'else'", "'let'", 
			"'let*'", "'letrec'", null, "'begin'", "'quote'", "'sin'", "'cos'", "'tan'", 
			"'asin'", "'acos'", "'atan'", "'sinh'", "'cosh'", "'tanh'", "'sqrt'", 
			"'round'", "'floor'", "'ceiling'", "'truncate'", "'remainder'", "'modulo'", 
			"'radians->degrees'", "'degrees->radians'", "'list'", "'car'", "'cdr'", 
			"'cons'", "'vector'", "'vector-ref'", "'vector-length'", "'display'", 
			"'string-append'", "'string-length'", "'string=?'", "'string<=?'", "'string>=?'", 
			"'string<?'", "'string>?'", "'substring'", "'number?'", "'bool?'", "'char?'", 
			"'string?'", "'list?'", "'vector?'", "'null?'", "'symbol?'", "'pair?'", 
			"'eq?'", "'equal?'", "'random-integer'", "'random-double'", "'random'", 
			"'read-line'", "'read-number'", "'string->number'", "'number->string'", 
			"'string->list'", "'list->string'", "'set!'", "'set-car!'", "'set-cdr!'", 
			"'vector-set!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WHITESPACE", "COMMENT", "OPEN_PAREN", "CLOSE_PAREN", "OPEN_BRACKET", 
			"CLOSE_BRACKET", "PLUS", "MINUS", "STAR", "SLASH", "AMPERSAND", "PIPE", 
			"CARAT", "PERCENT", "EXPONENTIATION", "SINGLE_QUOTE", "HASH", "LOGICAL_NOT", 
			"LOGICAL_AND", "LOGICAL_OR", "LOGICAL_EQ", "LOGICAL_LE", "LOGICAL_GE", 
			"LOGICAL_LT", "LOGICAL_GT", "BITWISE_SHL", "BITWISE_SHR", "BITWISE_NEG", 
			"NUMBERLIT", "STRINGLIT", "CHARLIT", "BOOLLIT", "DEFINE", "IF", "COND", 
			"DO", "ELSE", "LET", "LETSTAR", "LETREC", "LAMBDA", "BEGIN", "QUOTE", 
			"SIN", "COS", "TAN", "ASIN", "ACOS", "ATAN", "SINH", "COSH", "TANH", 
			"SQRT", "ROUND", "FLOOR", "CEILING", "TRUNCATE", "REMAINDER", "MODULO", 
			"TODEG_FN", "TORAD_FN", "CREATE_LIST_FN", "CAR", "CDR", "CONS", "CREATE_VECTOR_FN", 
			"VECTOR_REF_FN", "VECTORLEN_FN", "DISPLAY", "STRAPPEND_FN", "STRLEN_FN", 
			"STREQ_FN", "STRLE_FN", "STRGE_FN", "STRLT_FN", "STRGT_FN", "STRSUBSTR", 
			"NUMBER_FN", "BOOL_FN", "CHAR_FN", "STRING_FN", "LIST_FN", "VECTOR_FN", 
			"NULL_FN", "SYMBOL_FN", "PAIR_FN", "EQ_FN", "EQUAL_FN", "RANDINT_FN", 
			"RANDDOUBLE_FN", "RAND_FN", "READLINE_FN", "READNUMBER_FN", "STRTONUM_FN", 
			"NUMTOSTR_FN", "STRTOLIST_FN", "LISTTOSTR_FN", "SETVAR_FN", "SETCAR_FN", 
			"SETCDR_FN", "SETVEC_FN", "ID"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MiniScheme.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniSchemeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class MiniSchemeContext extends ParserRuleContext {
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MiniSchemeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_miniScheme; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterMiniScheme(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitMiniScheme(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitMiniScheme(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MiniSchemeContext miniScheme() throws RecognitionException {
		MiniSchemeContext _localctx = new MiniSchemeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_miniScheme);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << EXPONENTIATION) | (1L << SINGLE_QUOTE) | (1L << LOGICAL_NOT) | (1L << LOGICAL_AND) | (1L << LOGICAL_OR) | (1L << LOGICAL_EQ) | (1L << LOGICAL_LE) | (1L << LOGICAL_GE) | (1L << LOGICAL_LT) | (1L << LOGICAL_GT) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << SIN) | (1L << COS) | (1L << TAN) | (1L << ASIN) | (1L << ACOS) | (1L << ATAN) | (1L << SINH) | (1L << COSH) | (1L << TANH) | (1L << SQRT) | (1L << ROUND) | (1L << FLOOR) | (1L << CEILING) | (1L << TRUNCATE) | (1L << REMAINDER) | (1L << MODULO) | (1L << TODEG_FN) | (1L << TORAD_FN) | (1L << CAR))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CDR - 64)) | (1L << (VECTOR_REF_FN - 64)) | (1L << (VECTORLEN_FN - 64)) | (1L << (DISPLAY - 64)) | (1L << (STRAPPEND_FN - 64)) | (1L << (STRLEN_FN - 64)) | (1L << (STREQ_FN - 64)) | (1L << (STRLE_FN - 64)) | (1L << (STRGE_FN - 64)) | (1L << (STRLT_FN - 64)) | (1L << (STRGT_FN - 64)) | (1L << (STRSUBSTR - 64)) | (1L << (NUMBER_FN - 64)) | (1L << (BOOL_FN - 64)) | (1L << (CHAR_FN - 64)) | (1L << (STRING_FN - 64)) | (1L << (LIST_FN - 64)) | (1L << (VECTOR_FN - 64)) | (1L << (NULL_FN - 64)) | (1L << (SYMBOL_FN - 64)) | (1L << (PAIR_FN - 64)) | (1L << (EQ_FN - 64)) | (1L << (EQUAL_FN - 64)) | (1L << (RANDINT_FN - 64)) | (1L << (RANDDOUBLE_FN - 64)) | (1L << (RAND_FN - 64)) | (1L << (STRTONUM_FN - 64)) | (1L << (NUMTOSTR_FN - 64)) | (1L << (STRTOLIST_FN - 64)) | (1L << (LISTTOSTR_FN - 64)) | (1L << (ID - 64)))) != 0)) {
				{
				setState(102);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(100);
					decl();
					}
					break;
				case 2:
					{
					setState(101);
					expr();
					}
					break;
				}
				}
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
	 
		public DeclContext() { }
		public void copyFrom(DeclContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VarDeclReadContext extends DeclContext {
		public List<TerminalNode> OPEN_PAREN() { return getTokens(MiniSchemeParser.OPEN_PAREN); }
		public TerminalNode OPEN_PAREN(int i) {
			return getToken(MiniSchemeParser.OPEN_PAREN, i);
		}
		public TerminalNode DEFINE() { return getToken(MiniSchemeParser.DEFINE, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public ReadopContext readop() {
			return getRuleContext(ReadopContext.class,0);
		}
		public List<TerminalNode> CLOSE_PAREN() { return getTokens(MiniSchemeParser.CLOSE_PAREN); }
		public TerminalNode CLOSE_PAREN(int i) {
			return getToken(MiniSchemeParser.CLOSE_PAREN, i);
		}
		public VarDeclReadContext(DeclContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterVarDeclRead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitVarDeclRead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitVarDeclRead(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ProcDeclContext extends DeclContext {
		public List<TerminalNode> OPEN_PAREN() { return getTokens(MiniSchemeParser.OPEN_PAREN); }
		public TerminalNode OPEN_PAREN(int i) {
			return getToken(MiniSchemeParser.OPEN_PAREN, i);
		}
		public TerminalNode DEFINE() { return getToken(MiniSchemeParser.DEFINE, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public List<TerminalNode> CLOSE_PAREN() { return getTokens(MiniSchemeParser.CLOSE_PAREN); }
		public TerminalNode CLOSE_PAREN(int i) {
			return getToken(MiniSchemeParser.CLOSE_PAREN, i);
		}
		public ProcBodyContext procBody() {
			return getRuleContext(ProcBodyContext.class,0);
		}
		public ProcParamsContext procParams() {
			return getRuleContext(ProcParamsContext.class,0);
		}
		public ProcDeclContext(DeclContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterProcDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitProcDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitProcDecl(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarDeclContext extends DeclContext {
		public TerminalNode OPEN_PAREN() { return getToken(MiniSchemeParser.OPEN_PAREN, 0); }
		public TerminalNode DEFINE() { return getToken(MiniSchemeParser.DEFINE, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode CLOSE_PAREN() { return getToken(MiniSchemeParser.CLOSE_PAREN, 0); }
		public VarDeclContext(DeclContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitVarDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			setState(132);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				_localctx = new VarDeclContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				match(OPEN_PAREN);
				setState(108);
				match(DEFINE);
				setState(109);
				variable();
				setState(110);
				expr();
				setState(111);
				match(CLOSE_PAREN);
				}
				break;
			case 2:
				_localctx = new ProcDeclContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				match(OPEN_PAREN);
				setState(114);
				match(DEFINE);
				setState(115);
				match(OPEN_PAREN);
				setState(116);
				term();
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << EXPONENTIATION) | (1L << SINGLE_QUOTE) | (1L << LOGICAL_NOT) | (1L << LOGICAL_AND) | (1L << LOGICAL_OR) | (1L << LOGICAL_EQ) | (1L << LOGICAL_LE) | (1L << LOGICAL_GE) | (1L << LOGICAL_LT) | (1L << LOGICAL_GT) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << SIN) | (1L << COS) | (1L << TAN) | (1L << ASIN) | (1L << ACOS) | (1L << ATAN) | (1L << SINH) | (1L << COSH) | (1L << TANH) | (1L << SQRT) | (1L << ROUND) | (1L << FLOOR) | (1L << CEILING) | (1L << TRUNCATE) | (1L << REMAINDER) | (1L << MODULO) | (1L << TODEG_FN) | (1L << TORAD_FN) | (1L << CAR))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CDR - 64)) | (1L << (VECTOR_REF_FN - 64)) | (1L << (VECTORLEN_FN - 64)) | (1L << (DISPLAY - 64)) | (1L << (STRAPPEND_FN - 64)) | (1L << (STRLEN_FN - 64)) | (1L << (STREQ_FN - 64)) | (1L << (STRLE_FN - 64)) | (1L << (STRGE_FN - 64)) | (1L << (STRLT_FN - 64)) | (1L << (STRGT_FN - 64)) | (1L << (STRSUBSTR - 64)) | (1L << (NUMBER_FN - 64)) | (1L << (BOOL_FN - 64)) | (1L << (CHAR_FN - 64)) | (1L << (STRING_FN - 64)) | (1L << (LIST_FN - 64)) | (1L << (VECTOR_FN - 64)) | (1L << (NULL_FN - 64)) | (1L << (SYMBOL_FN - 64)) | (1L << (PAIR_FN - 64)) | (1L << (EQ_FN - 64)) | (1L << (EQUAL_FN - 64)) | (1L << (RANDINT_FN - 64)) | (1L << (RANDDOUBLE_FN - 64)) | (1L << (RAND_FN - 64)) | (1L << (STRTONUM_FN - 64)) | (1L << (NUMTOSTR_FN - 64)) | (1L << (STRTOLIST_FN - 64)) | (1L << (LISTTOSTR_FN - 64)) | (1L << (ID - 64)))) != 0)) {
					{
					setState(117);
					procParams();
					}
				}

				setState(120);
				match(CLOSE_PAREN);
				setState(121);
				procBody();
				setState(122);
				match(CLOSE_PAREN);
				}
				break;
			case 3:
				_localctx = new VarDeclReadContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(124);
				match(OPEN_PAREN);
				setState(125);
				match(DEFINE);
				setState(126);
				term();
				setState(127);
				match(OPEN_PAREN);
				setState(128);
				readop();
				setState(129);
				match(CLOSE_PAREN);
				setState(130);
				match(CLOSE_PAREN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprBeginContext exprBegin() {
			return getRuleContext(ExprBeginContext.class,0);
		}
		public ExprConsContext exprCons() {
			return getRuleContext(ExprConsContext.class,0);
		}
		public ExprSetContext exprSet() {
			return getRuleContext(ExprSetContext.class,0);
		}
		public ExprSetReadContext exprSetRead() {
			return getRuleContext(ExprSetReadContext.class,0);
		}
		public ExprOpContext exprOp() {
			return getRuleContext(ExprOpContext.class,0);
		}
		public ExprVectorContext exprVector() {
			return getRuleContext(ExprVectorContext.class,0);
		}
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public ExprCallContext exprCall() {
			return getRuleContext(ExprCallContext.class,0);
		}
		public ExprLambdaDeclContext exprLambdaDecl() {
			return getRuleContext(ExprLambdaDeclContext.class,0);
		}
		public ExprLambdaDeclCallContext exprLambdaDeclCall() {
			return getRuleContext(ExprLambdaDeclCallContext.class,0);
		}
		public ExprIfContext exprIf() {
			return getRuleContext(ExprIfContext.class,0);
		}
		public ExprCondContext exprCond() {
			return getRuleContext(ExprCondContext.class,0);
		}
		public ExprDoContext exprDo() {
			return getRuleContext(ExprDoContext.class,0);
		}
		public ExprLetDeclContext exprLetDecl() {
			return getRuleContext(ExprLetDeclContext.class,0);
		}
		public ExprSymbolContext exprSymbol() {
			return getRuleContext(ExprSymbolContext.class,0);
		}
		public ExprTermContext exprTerm() {
			return getRuleContext(ExprTermContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expr);
		try {
			setState(150);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
				exprBegin();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(135);
				exprCons();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(136);
				exprSet();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(137);
				exprSetRead();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(138);
				exprOp();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(139);
				exprVector();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(140);
				exprList();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(141);
				exprCall();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(142);
				exprLambdaDecl();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(143);
				exprLambdaDeclCall();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(144);
				exprIf();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(145);
				exprCond();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(146);
				exprDo();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(147);
				exprLetDecl();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(148);
				exprSymbol();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(149);
				exprTerm();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprConsContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(MiniSchemeParser.OPEN_PAREN, 0); }
		public TerminalNode CONS() { return getToken(MiniSchemeParser.CONS, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode CLOSE_PAREN() { return getToken(MiniSchemeParser.CLOSE_PAREN, 0); }
		public ExprConsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprCons; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterExprCons(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitExprCons(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitExprCons(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprConsContext exprCons() throws RecognitionException {
		ExprConsContext _localctx = new ExprConsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_exprCons);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(OPEN_PAREN);
			setState(153);
			match(CONS);
			setState(154);
			expr();
			setState(155);
			expr();
			setState(156);
			match(CLOSE_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprSetContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(MiniSchemeParser.OPEN_PAREN, 0); }
		public SetopContext setop() {
			return getRuleContext(SetopContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SeqContext seq() {
			return getRuleContext(SeqContext.class,0);
		}
		public TerminalNode CLOSE_PAREN() { return getToken(MiniSchemeParser.CLOSE_PAREN, 0); }
		public ExprSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprSet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterExprSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitExprSet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitExprSet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprSetContext exprSet() throws RecognitionException {
		ExprSetContext _localctx = new ExprSetContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_exprSet);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(OPEN_PAREN);
			setState(159);
			setop();
			setState(160);
			expr();
			setState(161);
			seq();
			setState(162);
			match(CLOSE_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprSetReadContext extends ParserRuleContext {
		public List<TerminalNode> OPEN_PAREN() { return getTokens(MiniSchemeParser.OPEN_PAREN); }
		public TerminalNode OPEN_PAREN(int i) {
			return getToken(MiniSchemeParser.OPEN_PAREN, i);
		}
		public SetopContext setop() {
			return getRuleContext(SetopContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReadopContext readop() {
			return getRuleContext(ReadopContext.class,0);
		}
		public List<TerminalNode> CLOSE_PAREN() { return getTokens(MiniSchemeParser.CLOSE_PAREN); }
		public TerminalNode CLOSE_PAREN(int i) {
			return getToken(MiniSchemeParser.CLOSE_PAREN, i);
		}
		public ExprSetReadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprSetRead; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterExprSetRead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitExprSetRead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitExprSetRead(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprSetReadContext exprSetRead() throws RecognitionException {
		ExprSetReadContext _localctx = new ExprSetReadContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_exprSetRead);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(OPEN_PAREN);
			setState(165);
			setop();
			setState(166);
			expr();
			setState(167);
			match(OPEN_PAREN);
			setState(168);
			readop();
			setState(169);
			match(CLOSE_PAREN);
			setState(170);
			match(CLOSE_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprOpContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(MiniSchemeParser.OPEN_PAREN, 0); }
		public TerminalNode CLOSE_PAREN() { return getToken(MiniSchemeParser.CLOSE_PAREN, 0); }
		public UnaryopContext unaryop() {
			return getRuleContext(UnaryopContext.class,0);
		}
		public BinaryopContext binaryop() {
			return getRuleContext(BinaryopContext.class,0);
		}
		public TernaryopContext ternaryop() {
			return getRuleContext(TernaryopContext.class,0);
		}
		public NaryopContext naryop() {
			return getRuleContext(NaryopContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterExprOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitExprOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitExprOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprOpContext exprOp() throws RecognitionException {
		ExprOpContext _localctx = new ExprOpContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_exprOp);
		int _la;
		try {
			int _alt;
			setState(199);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_PAREN:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(172);
				match(OPEN_PAREN);
				setState(177);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LOGICAL_NOT:
				case SIN:
				case COS:
				case TAN:
				case ASIN:
				case ACOS:
				case ATAN:
				case SINH:
				case COSH:
				case TANH:
				case SQRT:
				case ROUND:
				case FLOOR:
				case CEILING:
				case TRUNCATE:
				case TODEG_FN:
				case TORAD_FN:
				case CAR:
				case CDR:
				case VECTORLEN_FN:
				case DISPLAY:
				case STRLEN_FN:
				case NUMBER_FN:
				case BOOL_FN:
				case CHAR_FN:
				case STRING_FN:
				case LIST_FN:
				case VECTOR_FN:
				case NULL_FN:
				case SYMBOL_FN:
				case PAIR_FN:
				case RAND_FN:
				case STRTONUM_FN:
				case NUMTOSTR_FN:
				case STRTOLIST_FN:
				case LISTTOSTR_FN:
					{
					setState(173);
					unaryop();
					}
					break;
				case LOGICAL_EQ:
				case LOGICAL_LE:
				case LOGICAL_GE:
				case LOGICAL_LT:
				case LOGICAL_GT:
				case REMAINDER:
				case MODULO:
				case VECTOR_REF_FN:
				case STREQ_FN:
				case STRLE_FN:
				case STRGE_FN:
				case STRLT_FN:
				case STRGT_FN:
				case RANDINT_FN:
				case RANDDOUBLE_FN:
					{
					setState(174);
					binaryop();
					}
					break;
				case STRSUBSTR:
					{
					setState(175);
					ternaryop();
					}
					break;
				case PLUS:
				case MINUS:
				case STAR:
				case SLASH:
				case EXPONENTIATION:
				case LOGICAL_AND:
				case LOGICAL_OR:
				case STRAPPEND_FN:
				case EQ_FN:
				case EQUAL_FN:
					{
					setState(176);
					naryop();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << EXPONENTIATION) | (1L << SINGLE_QUOTE) | (1L << LOGICAL_NOT) | (1L << LOGICAL_AND) | (1L << LOGICAL_OR) | (1L << LOGICAL_EQ) | (1L << LOGICAL_LE) | (1L << LOGICAL_GE) | (1L << LOGICAL_LT) | (1L << LOGICAL_GT) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << SIN) | (1L << COS) | (1L << TAN) | (1L << ASIN) | (1L << ACOS) | (1L << ATAN) | (1L << SINH) | (1L << COSH) | (1L << TANH) | (1L << SQRT) | (1L << ROUND) | (1L << FLOOR) | (1L << CEILING) | (1L << TRUNCATE) | (1L << REMAINDER) | (1L << MODULO) | (1L << TODEG_FN) | (1L << TORAD_FN) | (1L << CAR))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CDR - 64)) | (1L << (VECTOR_REF_FN - 64)) | (1L << (VECTORLEN_FN - 64)) | (1L << (DISPLAY - 64)) | (1L << (STRAPPEND_FN - 64)) | (1L << (STRLEN_FN - 64)) | (1L << (STREQ_FN - 64)) | (1L << (STRLE_FN - 64)) | (1L << (STRGE_FN - 64)) | (1L << (STRLT_FN - 64)) | (1L << (STRGT_FN - 64)) | (1L << (STRSUBSTR - 64)) | (1L << (NUMBER_FN - 64)) | (1L << (BOOL_FN - 64)) | (1L << (CHAR_FN - 64)) | (1L << (STRING_FN - 64)) | (1L << (LIST_FN - 64)) | (1L << (VECTOR_FN - 64)) | (1L << (NULL_FN - 64)) | (1L << (SYMBOL_FN - 64)) | (1L << (PAIR_FN - 64)) | (1L << (EQ_FN - 64)) | (1L << (EQUAL_FN - 64)) | (1L << (RANDINT_FN - 64)) | (1L << (RANDDOUBLE_FN - 64)) | (1L << (RAND_FN - 64)) | (1L << (STRTONUM_FN - 64)) | (1L << (NUMTOSTR_FN - 64)) | (1L << (STRTOLIST_FN - 64)) | (1L << (LISTTOSTR_FN - 64)) | (1L << (ID - 64)))) != 0)) {
					{
					{
					setState(179);
					expr();
					}
					}
					setState(184);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(185);
				match(CLOSE_PAREN);
				}
				}
				break;
			case PLUS:
			case MINUS:
			case STAR:
			case SLASH:
			case EXPONENTIATION:
			case LOGICAL_NOT:
			case LOGICAL_AND:
			case LOGICAL_OR:
			case LOGICAL_EQ:
			case LOGICAL_LE:
			case LOGICAL_GE:
			case LOGICAL_LT:
			case LOGICAL_GT:
			case SIN:
			case COS:
			case TAN:
			case ASIN:
			case ACOS:
			case ATAN:
			case SINH:
			case COSH:
			case TANH:
			case SQRT:
			case ROUND:
			case FLOOR:
			case CEILING:
			case TRUNCATE:
			case REMAINDER:
			case MODULO:
			case TODEG_FN:
			case TORAD_FN:
			case CAR:
			case CDR:
			case VECTOR_REF_FN:
			case VECTORLEN_FN:
			case DISPLAY:
			case STRAPPEND_FN:
			case STRLEN_FN:
			case STREQ_FN:
			case STRLE_FN:
			case STRGE_FN:
			case STRLT_FN:
			case STRGT_FN:
			case STRSUBSTR:
			case NUMBER_FN:
			case BOOL_FN:
			case CHAR_FN:
			case STRING_FN:
			case LIST_FN:
			case VECTOR_FN:
			case NULL_FN:
			case SYMBOL_FN:
			case PAIR_FN:
			case EQ_FN:
			case EQUAL_FN:
			case RANDINT_FN:
			case RANDDOUBLE_FN:
			case RAND_FN:
			case STRTONUM_FN:
			case NUMTOSTR_FN:
			case STRTOLIST_FN:
			case LISTTOSTR_FN:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(191);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LOGICAL_NOT:
				case SIN:
				case COS:
				case TAN:
				case ASIN:
				case ACOS:
				case ATAN:
				case SINH:
				case COSH:
				case TANH:
				case SQRT:
				case ROUND:
				case FLOOR:
				case CEILING:
				case TRUNCATE:
				case TODEG_FN:
				case TORAD_FN:
				case CAR:
				case CDR:
				case VECTORLEN_FN:
				case DISPLAY:
				case STRLEN_FN:
				case NUMBER_FN:
				case BOOL_FN:
				case CHAR_FN:
				case STRING_FN:
				case LIST_FN:
				case VECTOR_FN:
				case NULL_FN:
				case SYMBOL_FN:
				case PAIR_FN:
				case RAND_FN:
				case STRTONUM_FN:
				case NUMTOSTR_FN:
				case STRTOLIST_FN:
				case LISTTOSTR_FN:
					{
					setState(187);
					unaryop();
					}
					break;
				case LOGICAL_EQ:
				case LOGICAL_LE:
				case LOGICAL_GE:
				case LOGICAL_LT:
				case LOGICAL_GT:
				case REMAINDER:
				case MODULO:
				case VECTOR_REF_FN:
				case STREQ_FN:
				case STRLE_FN:
				case STRGE_FN:
				case STRLT_FN:
				case STRGT_FN:
				case RANDINT_FN:
				case RANDDOUBLE_FN:
					{
					setState(188);
					binaryop();
					}
					break;
				case STRSUBSTR:
					{
					setState(189);
					ternaryop();
					}
					break;
				case PLUS:
				case MINUS:
				case STAR:
				case SLASH:
				case EXPONENTIATION:
				case LOGICAL_AND:
				case LOGICAL_OR:
				case STRAPPEND_FN:
				case EQ_FN:
				case EQUAL_FN:
					{
					setState(190);
					naryop();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(196);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(193);
						expr();
						}
						} 
					}
					setState(198);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				}
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprVectorContext extends ParserRuleContext {
		public List<TerminalNode> OPEN_PAREN() { return getTokens(MiniSchemeParser.OPEN_PAREN); }
		public TerminalNode OPEN_PAREN(int i) {
			return getToken(MiniSchemeParser.OPEN_PAREN, i);
		}
		public TerminalNode CREATE_VECTOR_FN() { return getToken(MiniSchemeParser.CREATE_VECTOR_FN, 0); }
		public List<TerminalNode> CLOSE_PAREN() { return getTokens(MiniSchemeParser.CLOSE_PAREN); }
		public TerminalNode CLOSE_PAREN(int i) {
			return getToken(MiniSchemeParser.CLOSE_PAREN, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprVectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprVector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterExprVector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitExprVector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitExprVector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprVectorContext exprVector() throws RecognitionException {
		ExprVectorContext _localctx = new ExprVectorContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_exprVector);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(OPEN_PAREN);
			setState(202);
			match(CREATE_VECTOR_FN);
			setState(203);
			match(OPEN_PAREN);
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << EXPONENTIATION) | (1L << SINGLE_QUOTE) | (1L << LOGICAL_NOT) | (1L << LOGICAL_AND) | (1L << LOGICAL_OR) | (1L << LOGICAL_EQ) | (1L << LOGICAL_LE) | (1L << LOGICAL_GE) | (1L << LOGICAL_LT) | (1L << LOGICAL_GT) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << SIN) | (1L << COS) | (1L << TAN) | (1L << ASIN) | (1L << ACOS) | (1L << ATAN) | (1L << SINH) | (1L << COSH) | (1L << TANH) | (1L << SQRT) | (1L << ROUND) | (1L << FLOOR) | (1L << CEILING) | (1L << TRUNCATE) | (1L << REMAINDER) | (1L << MODULO) | (1L << TODEG_FN) | (1L << TORAD_FN) | (1L << CAR))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CDR - 64)) | (1L << (VECTOR_REF_FN - 64)) | (1L << (VECTORLEN_FN - 64)) | (1L << (DISPLAY - 64)) | (1L << (STRAPPEND_FN - 64)) | (1L << (STRLEN_FN - 64)) | (1L << (STREQ_FN - 64)) | (1L << (STRLE_FN - 64)) | (1L << (STRGE_FN - 64)) | (1L << (STRLT_FN - 64)) | (1L << (STRGT_FN - 64)) | (1L << (STRSUBSTR - 64)) | (1L << (NUMBER_FN - 64)) | (1L << (BOOL_FN - 64)) | (1L << (CHAR_FN - 64)) | (1L << (STRING_FN - 64)) | (1L << (LIST_FN - 64)) | (1L << (VECTOR_FN - 64)) | (1L << (NULL_FN - 64)) | (1L << (SYMBOL_FN - 64)) | (1L << (PAIR_FN - 64)) | (1L << (EQ_FN - 64)) | (1L << (EQUAL_FN - 64)) | (1L << (RANDINT_FN - 64)) | (1L << (RANDDOUBLE_FN - 64)) | (1L << (RAND_FN - 64)) | (1L << (STRTONUM_FN - 64)) | (1L << (NUMTOSTR_FN - 64)) | (1L << (STRTOLIST_FN - 64)) | (1L << (LISTTOSTR_FN - 64)) | (1L << (ID - 64)))) != 0)) {
				{
				{
				setState(204);
				expr();
				}
				}
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(210);
			match(CLOSE_PAREN);
			setState(211);
			match(CLOSE_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprListContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(MiniSchemeParser.OPEN_PAREN, 0); }
		public TerminalNode CREATE_LIST_FN() { return getToken(MiniSchemeParser.CREATE_LIST_FN, 0); }
		public TerminalNode CLOSE_PAREN() { return getToken(MiniSchemeParser.CLOSE_PAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterExprList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitExprList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitExprList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_exprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			match(OPEN_PAREN);
			setState(214);
			match(CREATE_LIST_FN);
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << EXPONENTIATION) | (1L << SINGLE_QUOTE) | (1L << LOGICAL_NOT) | (1L << LOGICAL_AND) | (1L << LOGICAL_OR) | (1L << LOGICAL_EQ) | (1L << LOGICAL_LE) | (1L << LOGICAL_GE) | (1L << LOGICAL_LT) | (1L << LOGICAL_GT) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << SIN) | (1L << COS) | (1L << TAN) | (1L << ASIN) | (1L << ACOS) | (1L << ATAN) | (1L << SINH) | (1L << COSH) | (1L << TANH) | (1L << SQRT) | (1L << ROUND) | (1L << FLOOR) | (1L << CEILING) | (1L << TRUNCATE) | (1L << REMAINDER) | (1L << MODULO) | (1L << TODEG_FN) | (1L << TORAD_FN) | (1L << CAR))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CDR - 64)) | (1L << (VECTOR_REF_FN - 64)) | (1L << (VECTORLEN_FN - 64)) | (1L << (DISPLAY - 64)) | (1L << (STRAPPEND_FN - 64)) | (1L << (STRLEN_FN - 64)) | (1L << (STREQ_FN - 64)) | (1L << (STRLE_FN - 64)) | (1L << (STRGE_FN - 64)) | (1L << (STRLT_FN - 64)) | (1L << (STRGT_FN - 64)) | (1L << (STRSUBSTR - 64)) | (1L << (NUMBER_FN - 64)) | (1L << (BOOL_FN - 64)) | (1L << (CHAR_FN - 64)) | (1L << (STRING_FN - 64)) | (1L << (LIST_FN - 64)) | (1L << (VECTOR_FN - 64)) | (1L << (NULL_FN - 64)) | (1L << (SYMBOL_FN - 64)) | (1L << (PAIR_FN - 64)) | (1L << (EQ_FN - 64)) | (1L << (EQUAL_FN - 64)) | (1L << (RANDINT_FN - 64)) | (1L << (RANDDOUBLE_FN - 64)) | (1L << (RAND_FN - 64)) | (1L << (STRTONUM_FN - 64)) | (1L << (NUMTOSTR_FN - 64)) | (1L << (STRTOLIST_FN - 64)) | (1L << (LISTTOSTR_FN - 64)) | (1L << (ID - 64)))) != 0)) {
				{
				{
				setState(215);
				expr();
				}
				}
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(221);
			match(CLOSE_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprCallContext extends ParserRuleContext {
		public List<TerminalNode> OPEN_PAREN() { return getTokens(MiniSchemeParser.OPEN_PAREN); }
		public TerminalNode OPEN_PAREN(int i) {
			return getToken(MiniSchemeParser.OPEN_PAREN, i);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public List<TerminalNode> CLOSE_PAREN() { return getTokens(MiniSchemeParser.CLOSE_PAREN); }
		public TerminalNode CLOSE_PAREN(int i) {
			return getToken(MiniSchemeParser.CLOSE_PAREN, i);
		}
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public LambdaArgsContext lambdaArgs() {
			return getRuleContext(LambdaArgsContext.class,0);
		}
		public ExprCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterExprCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitExprCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitExprCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprCallContext exprCall() throws RecognitionException {
		ExprCallContext _localctx = new ExprCallContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_exprCall);
		int _la;
		try {
			setState(242);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(223);
				match(OPEN_PAREN);
				setState(224);
				term();
				setState(226);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << EXPONENTIATION) | (1L << SINGLE_QUOTE) | (1L << LOGICAL_NOT) | (1L << LOGICAL_AND) | (1L << LOGICAL_OR) | (1L << LOGICAL_EQ) | (1L << LOGICAL_LE) | (1L << LOGICAL_GE) | (1L << LOGICAL_LT) | (1L << LOGICAL_GT) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << SIN) | (1L << COS) | (1L << TAN) | (1L << ASIN) | (1L << ACOS) | (1L << ATAN) | (1L << SINH) | (1L << COSH) | (1L << TANH) | (1L << SQRT) | (1L << ROUND) | (1L << FLOOR) | (1L << CEILING) | (1L << TRUNCATE) | (1L << REMAINDER) | (1L << MODULO) | (1L << TODEG_FN) | (1L << TORAD_FN) | (1L << CAR))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CDR - 64)) | (1L << (VECTOR_REF_FN - 64)) | (1L << (VECTORLEN_FN - 64)) | (1L << (DISPLAY - 64)) | (1L << (STRAPPEND_FN - 64)) | (1L << (STRLEN_FN - 64)) | (1L << (STREQ_FN - 64)) | (1L << (STRLE_FN - 64)) | (1L << (STRGE_FN - 64)) | (1L << (STRLT_FN - 64)) | (1L << (STRGT_FN - 64)) | (1L << (STRSUBSTR - 64)) | (1L << (NUMBER_FN - 64)) | (1L << (BOOL_FN - 64)) | (1L << (CHAR_FN - 64)) | (1L << (STRING_FN - 64)) | (1L << (LIST_FN - 64)) | (1L << (VECTOR_FN - 64)) | (1L << (NULL_FN - 64)) | (1L << (SYMBOL_FN - 64)) | (1L << (PAIR_FN - 64)) | (1L << (EQ_FN - 64)) | (1L << (EQUAL_FN - 64)) | (1L << (RANDINT_FN - 64)) | (1L << (RANDDOUBLE_FN - 64)) | (1L << (RAND_FN - 64)) | (1L << (STRTONUM_FN - 64)) | (1L << (NUMTOSTR_FN - 64)) | (1L << (STRTOLIST_FN - 64)) | (1L << (LISTTOSTR_FN - 64)) | (1L << (ID - 64)))) != 0)) {
					{
					setState(225);
					args();
					}
				}

				setState(228);
				match(CLOSE_PAREN);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(230);
				match(OPEN_PAREN);
				setState(231);
				match(OPEN_PAREN);
				setState(232);
				term();
				setState(234);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << EXPONENTIATION) | (1L << SINGLE_QUOTE) | (1L << LOGICAL_NOT) | (1L << LOGICAL_AND) | (1L << LOGICAL_OR) | (1L << LOGICAL_EQ) | (1L << LOGICAL_LE) | (1L << LOGICAL_GE) | (1L << LOGICAL_LT) | (1L << LOGICAL_GT) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << SIN) | (1L << COS) | (1L << TAN) | (1L << ASIN) | (1L << ACOS) | (1L << ATAN) | (1L << SINH) | (1L << COSH) | (1L << TANH) | (1L << SQRT) | (1L << ROUND) | (1L << FLOOR) | (1L << CEILING) | (1L << TRUNCATE) | (1L << REMAINDER) | (1L << MODULO) | (1L << TODEG_FN) | (1L << TORAD_FN) | (1L << CAR))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CDR - 64)) | (1L << (VECTOR_REF_FN - 64)) | (1L << (VECTORLEN_FN - 64)) | (1L << (DISPLAY - 64)) | (1L << (STRAPPEND_FN - 64)) | (1L << (STRLEN_FN - 64)) | (1L << (STREQ_FN - 64)) | (1L << (STRLE_FN - 64)) | (1L << (STRGE_FN - 64)) | (1L << (STRLT_FN - 64)) | (1L << (STRGT_FN - 64)) | (1L << (STRSUBSTR - 64)) | (1L << (NUMBER_FN - 64)) | (1L << (BOOL_FN - 64)) | (1L << (CHAR_FN - 64)) | (1L << (STRING_FN - 64)) | (1L << (LIST_FN - 64)) | (1L << (VECTOR_FN - 64)) | (1L << (NULL_FN - 64)) | (1L << (SYMBOL_FN - 64)) | (1L << (PAIR_FN - 64)) | (1L << (EQ_FN - 64)) | (1L << (EQUAL_FN - 64)) | (1L << (RANDINT_FN - 64)) | (1L << (RANDDOUBLE_FN - 64)) | (1L << (RAND_FN - 64)) | (1L << (STRTONUM_FN - 64)) | (1L << (NUMTOSTR_FN - 64)) | (1L << (STRTOLIST_FN - 64)) | (1L << (LISTTOSTR_FN - 64)) | (1L << (ID - 64)))) != 0)) {
					{
					setState(233);
					args();
					}
				}

				setState(236);
				match(CLOSE_PAREN);
				setState(238);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << EXPONENTIATION) | (1L << SINGLE_QUOTE) | (1L << LOGICAL_NOT) | (1L << LOGICAL_AND) | (1L << LOGICAL_OR) | (1L << LOGICAL_EQ) | (1L << LOGICAL_LE) | (1L << LOGICAL_GE) | (1L << LOGICAL_LT) | (1L << LOGICAL_GT) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << SIN) | (1L << COS) | (1L << TAN) | (1L << ASIN) | (1L << ACOS) | (1L << ATAN) | (1L << SINH) | (1L << COSH) | (1L << TANH) | (1L << SQRT) | (1L << ROUND) | (1L << FLOOR) | (1L << CEILING) | (1L << TRUNCATE) | (1L << REMAINDER) | (1L << MODULO) | (1L << TODEG_FN) | (1L << TORAD_FN) | (1L << CAR))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CDR - 64)) | (1L << (VECTOR_REF_FN - 64)) | (1L << (VECTORLEN_FN - 64)) | (1L << (DISPLAY - 64)) | (1L << (STRAPPEND_FN - 64)) | (1L << (STRLEN_FN - 64)) | (1L << (STREQ_FN - 64)) | (1L << (STRLE_FN - 64)) | (1L << (STRGE_FN - 64)) | (1L << (STRLT_FN - 64)) | (1L << (STRGT_FN - 64)) | (1L << (STRSUBSTR - 64)) | (1L << (NUMBER_FN - 64)) | (1L << (BOOL_FN - 64)) | (1L << (CHAR_FN - 64)) | (1L << (STRING_FN - 64)) | (1L << (LIST_FN - 64)) | (1L << (VECTOR_FN - 64)) | (1L << (NULL_FN - 64)) | (1L << (SYMBOL_FN - 64)) | (1L << (PAIR_FN - 64)) | (1L << (EQ_FN - 64)) | (1L << (EQUAL_FN - 64)) | (1L << (RANDINT_FN - 64)) | (1L << (RANDDOUBLE_FN - 64)) | (1L << (RAND_FN - 64)) | (1L << (STRTONUM_FN - 64)) | (1L << (NUMTOSTR_FN - 64)) | (1L << (STRTOLIST_FN - 64)) | (1L << (LISTTOSTR_FN - 64)) | (1L << (ID - 64)))) != 0)) {
					{
					setState(237);
					lambdaArgs();
					}
				}

				setState(240);
				match(CLOSE_PAREN);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprLambdaDeclContext extends ParserRuleContext {
		public List<TerminalNode> OPEN_PAREN() { return getTokens(MiniSchemeParser.OPEN_PAREN); }
		public TerminalNode OPEN_PAREN(int i) {
			return getToken(MiniSchemeParser.OPEN_PAREN, i);
		}
		public TerminalNode LAMBDA() { return getToken(MiniSchemeParser.LAMBDA, 0); }
		public List<TerminalNode> CLOSE_PAREN() { return getTokens(MiniSchemeParser.CLOSE_PAREN); }
		public TerminalNode CLOSE_PAREN(int i) {
			return getToken(MiniSchemeParser.CLOSE_PAREN, i);
		}
		public LambdaBodyContext lambdaBody() {
			return getRuleContext(LambdaBodyContext.class,0);
		}
		public LambdaParamsContext lambdaParams() {
			return getRuleContext(LambdaParamsContext.class,0);
		}
		public ExprLambdaDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprLambdaDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterExprLambdaDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitExprLambdaDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitExprLambdaDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprLambdaDeclContext exprLambdaDecl() throws RecognitionException {
		ExprLambdaDeclContext _localctx = new ExprLambdaDeclContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_exprLambdaDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			match(OPEN_PAREN);
			setState(245);
			match(LAMBDA);
			setState(246);
			match(OPEN_PAREN);
			setState(248);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << EXPONENTIATION) | (1L << SINGLE_QUOTE) | (1L << LOGICAL_NOT) | (1L << LOGICAL_AND) | (1L << LOGICAL_OR) | (1L << LOGICAL_EQ) | (1L << LOGICAL_LE) | (1L << LOGICAL_GE) | (1L << LOGICAL_LT) | (1L << LOGICAL_GT) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << SIN) | (1L << COS) | (1L << TAN) | (1L << ASIN) | (1L << ACOS) | (1L << ATAN) | (1L << SINH) | (1L << COSH) | (1L << TANH) | (1L << SQRT) | (1L << ROUND) | (1L << FLOOR) | (1L << CEILING) | (1L << TRUNCATE) | (1L << REMAINDER) | (1L << MODULO) | (1L << TODEG_FN) | (1L << TORAD_FN) | (1L << CAR))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CDR - 64)) | (1L << (VECTOR_REF_FN - 64)) | (1L << (VECTORLEN_FN - 64)) | (1L << (DISPLAY - 64)) | (1L << (STRAPPEND_FN - 64)) | (1L << (STRLEN_FN - 64)) | (1L << (STREQ_FN - 64)) | (1L << (STRLE_FN - 64)) | (1L << (STRGE_FN - 64)) | (1L << (STRLT_FN - 64)) | (1L << (STRGT_FN - 64)) | (1L << (STRSUBSTR - 64)) | (1L << (NUMBER_FN - 64)) | (1L << (BOOL_FN - 64)) | (1L << (CHAR_FN - 64)) | (1L << (STRING_FN - 64)) | (1L << (LIST_FN - 64)) | (1L << (VECTOR_FN - 64)) | (1L << (NULL_FN - 64)) | (1L << (SYMBOL_FN - 64)) | (1L << (PAIR_FN - 64)) | (1L << (EQ_FN - 64)) | (1L << (EQUAL_FN - 64)) | (1L << (RANDINT_FN - 64)) | (1L << (RANDDOUBLE_FN - 64)) | (1L << (RAND_FN - 64)) | (1L << (STRTONUM_FN - 64)) | (1L << (NUMTOSTR_FN - 64)) | (1L << (STRTOLIST_FN - 64)) | (1L << (LISTTOSTR_FN - 64)) | (1L << (ID - 64)))) != 0)) {
				{
				setState(247);
				lambdaParams();
				}
			}

			setState(250);
			match(CLOSE_PAREN);
			setState(251);
			lambdaBody();
			setState(252);
			match(CLOSE_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprLambdaDeclCallContext extends ParserRuleContext {
		public List<TerminalNode> OPEN_PAREN() { return getTokens(MiniSchemeParser.OPEN_PAREN); }
		public TerminalNode OPEN_PAREN(int i) {
			return getToken(MiniSchemeParser.OPEN_PAREN, i);
		}
		public TerminalNode LAMBDA() { return getToken(MiniSchemeParser.LAMBDA, 0); }
		public List<TerminalNode> CLOSE_PAREN() { return getTokens(MiniSchemeParser.CLOSE_PAREN); }
		public TerminalNode CLOSE_PAREN(int i) {
			return getToken(MiniSchemeParser.CLOSE_PAREN, i);
		}
		public LambdaBodyContext lambdaBody() {
			return getRuleContext(LambdaBodyContext.class,0);
		}
		public LambdaParamsContext lambdaParams() {
			return getRuleContext(LambdaParamsContext.class,0);
		}
		public LambdaArgsContext lambdaArgs() {
			return getRuleContext(LambdaArgsContext.class,0);
		}
		public ExprLambdaDeclCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprLambdaDeclCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterExprLambdaDeclCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitExprLambdaDeclCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitExprLambdaDeclCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprLambdaDeclCallContext exprLambdaDeclCall() throws RecognitionException {
		ExprLambdaDeclCallContext _localctx = new ExprLambdaDeclCallContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_exprLambdaDeclCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			match(OPEN_PAREN);
			setState(255);
			match(OPEN_PAREN);
			setState(256);
			match(LAMBDA);
			setState(257);
			match(OPEN_PAREN);
			setState(259);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << EXPONENTIATION) | (1L << SINGLE_QUOTE) | (1L << LOGICAL_NOT) | (1L << LOGICAL_AND) | (1L << LOGICAL_OR) | (1L << LOGICAL_EQ) | (1L << LOGICAL_LE) | (1L << LOGICAL_GE) | (1L << LOGICAL_LT) | (1L << LOGICAL_GT) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << SIN) | (1L << COS) | (1L << TAN) | (1L << ASIN) | (1L << ACOS) | (1L << ATAN) | (1L << SINH) | (1L << COSH) | (1L << TANH) | (1L << SQRT) | (1L << ROUND) | (1L << FLOOR) | (1L << CEILING) | (1L << TRUNCATE) | (1L << REMAINDER) | (1L << MODULO) | (1L << TODEG_FN) | (1L << TORAD_FN) | (1L << CAR))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CDR - 64)) | (1L << (VECTOR_REF_FN - 64)) | (1L << (VECTORLEN_FN - 64)) | (1L << (DISPLAY - 64)) | (1L << (STRAPPEND_FN - 64)) | (1L << (STRLEN_FN - 64)) | (1L << (STREQ_FN - 64)) | (1L << (STRLE_FN - 64)) | (1L << (STRGE_FN - 64)) | (1L << (STRLT_FN - 64)) | (1L << (STRGT_FN - 64)) | (1L << (STRSUBSTR - 64)) | (1L << (NUMBER_FN - 64)) | (1L << (BOOL_FN - 64)) | (1L << (CHAR_FN - 64)) | (1L << (STRING_FN - 64)) | (1L << (LIST_FN - 64)) | (1L << (VECTOR_FN - 64)) | (1L << (NULL_FN - 64)) | (1L << (SYMBOL_FN - 64)) | (1L << (PAIR_FN - 64)) | (1L << (EQ_FN - 64)) | (1L << (EQUAL_FN - 64)) | (1L << (RANDINT_FN - 64)) | (1L << (RANDDOUBLE_FN - 64)) | (1L << (RAND_FN - 64)) | (1L << (STRTONUM_FN - 64)) | (1L << (NUMTOSTR_FN - 64)) | (1L << (STRTOLIST_FN - 64)) | (1L << (LISTTOSTR_FN - 64)) | (1L << (ID - 64)))) != 0)) {
				{
				setState(258);
				lambdaParams();
				}
			}

			setState(261);
			match(CLOSE_PAREN);
			setState(262);
			lambdaBody();
			setState(263);
			match(CLOSE_PAREN);
			setState(265);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << EXPONENTIATION) | (1L << SINGLE_QUOTE) | (1L << LOGICAL_NOT) | (1L << LOGICAL_AND) | (1L << LOGICAL_OR) | (1L << LOGICAL_EQ) | (1L << LOGICAL_LE) | (1L << LOGICAL_GE) | (1L << LOGICAL_LT) | (1L << LOGICAL_GT) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << SIN) | (1L << COS) | (1L << TAN) | (1L << ASIN) | (1L << ACOS) | (1L << ATAN) | (1L << SINH) | (1L << COSH) | (1L << TANH) | (1L << SQRT) | (1L << ROUND) | (1L << FLOOR) | (1L << CEILING) | (1L << TRUNCATE) | (1L << REMAINDER) | (1L << MODULO) | (1L << TODEG_FN) | (1L << TORAD_FN) | (1L << CAR))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CDR - 64)) | (1L << (VECTOR_REF_FN - 64)) | (1L << (VECTORLEN_FN - 64)) | (1L << (DISPLAY - 64)) | (1L << (STRAPPEND_FN - 64)) | (1L << (STRLEN_FN - 64)) | (1L << (STREQ_FN - 64)) | (1L << (STRLE_FN - 64)) | (1L << (STRGE_FN - 64)) | (1L << (STRLT_FN - 64)) | (1L << (STRGT_FN - 64)) | (1L << (STRSUBSTR - 64)) | (1L << (NUMBER_FN - 64)) | (1L << (BOOL_FN - 64)) | (1L << (CHAR_FN - 64)) | (1L << (STRING_FN - 64)) | (1L << (LIST_FN - 64)) | (1L << (VECTOR_FN - 64)) | (1L << (NULL_FN - 64)) | (1L << (SYMBOL_FN - 64)) | (1L << (PAIR_FN - 64)) | (1L << (EQ_FN - 64)) | (1L << (EQUAL_FN - 64)) | (1L << (RANDINT_FN - 64)) | (1L << (RANDDOUBLE_FN - 64)) | (1L << (RAND_FN - 64)) | (1L << (STRTONUM_FN - 64)) | (1L << (NUMTOSTR_FN - 64)) | (1L << (STRTOLIST_FN - 64)) | (1L << (LISTTOSTR_FN - 64)) | (1L << (ID - 64)))) != 0)) {
				{
				setState(264);
				lambdaArgs();
				}
			}

			setState(267);
			match(CLOSE_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprIfContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(MiniSchemeParser.OPEN_PAREN, 0); }
		public TerminalNode IF() { return getToken(MiniSchemeParser.IF, 0); }
		public IfCondContext ifCond() {
			return getRuleContext(IfCondContext.class,0);
		}
		public IfBodyContext ifBody() {
			return getRuleContext(IfBodyContext.class,0);
		}
		public IfElseContext ifElse() {
			return getRuleContext(IfElseContext.class,0);
		}
		public TerminalNode CLOSE_PAREN() { return getToken(MiniSchemeParser.CLOSE_PAREN, 0); }
		public ExprIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterExprIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitExprIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitExprIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprIfContext exprIf() throws RecognitionException {
		ExprIfContext _localctx = new ExprIfContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_exprIf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			match(OPEN_PAREN);
			setState(270);
			match(IF);
			setState(271);
			ifCond();
			setState(272);
			ifBody();
			setState(273);
			ifElse();
			setState(274);
			match(CLOSE_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprCondContext extends ParserRuleContext {
		public List<TerminalNode> OPEN_PAREN() { return getTokens(MiniSchemeParser.OPEN_PAREN); }
		public TerminalNode OPEN_PAREN(int i) {
			return getToken(MiniSchemeParser.OPEN_PAREN, i);
		}
		public TerminalNode COND() { return getToken(MiniSchemeParser.COND, 0); }
		public List<TerminalNode> CLOSE_PAREN() { return getTokens(MiniSchemeParser.CLOSE_PAREN); }
		public TerminalNode CLOSE_PAREN(int i) {
			return getToken(MiniSchemeParser.CLOSE_PAREN, i);
		}
		public List<TerminalNode> OPEN_BRACKET() { return getTokens(MiniSchemeParser.OPEN_BRACKET); }
		public TerminalNode OPEN_BRACKET(int i) {
			return getToken(MiniSchemeParser.OPEN_BRACKET, i);
		}
		public List<CondCondContext> condCond() {
			return getRuleContexts(CondCondContext.class);
		}
		public CondCondContext condCond(int i) {
			return getRuleContext(CondCondContext.class,i);
		}
		public List<CondBodyContext> condBody() {
			return getRuleContexts(CondBodyContext.class);
		}
		public CondBodyContext condBody(int i) {
			return getRuleContext(CondBodyContext.class,i);
		}
		public List<TerminalNode> CLOSE_BRACKET() { return getTokens(MiniSchemeParser.CLOSE_BRACKET); }
		public TerminalNode CLOSE_BRACKET(int i) {
			return getToken(MiniSchemeParser.CLOSE_BRACKET, i);
		}
		public TerminalNode ELSE() { return getToken(MiniSchemeParser.ELSE, 0); }
		public ExprCondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprCond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterExprCond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitExprCond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitExprCond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprCondContext exprCond() throws RecognitionException {
		ExprCondContext _localctx = new ExprCondContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_exprCond);
		int _la;
		try {
			int _alt;
			setState(316);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(276);
				match(OPEN_PAREN);
				setState(277);
				match(COND);
				setState(283); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(278);
						match(OPEN_BRACKET);
						setState(279);
						condCond();
						setState(280);
						condBody();
						setState(281);
						match(CLOSE_BRACKET);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(285); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(292);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPEN_BRACKET) {
					{
					setState(287);
					match(OPEN_BRACKET);
					setState(288);
					match(ELSE);
					setState(289);
					condBody();
					setState(290);
					match(CLOSE_BRACKET);
					}
				}

				setState(294);
				match(CLOSE_PAREN);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(296);
				match(OPEN_PAREN);
				setState(297);
				match(COND);
				setState(303); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(298);
						match(OPEN_PAREN);
						setState(299);
						condCond();
						setState(300);
						condBody();
						setState(301);
						match(CLOSE_PAREN);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(305); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(312);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPEN_PAREN) {
					{
					setState(307);
					match(OPEN_PAREN);
					setState(308);
					match(ELSE);
					setState(309);
					condBody();
					setState(310);
					match(CLOSE_PAREN);
					}
				}

				setState(314);
				match(CLOSE_PAREN);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprDoContext extends ParserRuleContext {
		public List<TerminalNode> OPEN_PAREN() { return getTokens(MiniSchemeParser.OPEN_PAREN); }
		public TerminalNode OPEN_PAREN(int i) {
			return getToken(MiniSchemeParser.OPEN_PAREN, i);
		}
		public TerminalNode DO() { return getToken(MiniSchemeParser.DO, 0); }
		public List<TerminalNode> CLOSE_PAREN() { return getTokens(MiniSchemeParser.CLOSE_PAREN); }
		public TerminalNode CLOSE_PAREN(int i) {
			return getToken(MiniSchemeParser.CLOSE_PAREN, i);
		}
		public DoTestDeclContext doTestDecl() {
			return getRuleContext(DoTestDeclContext.class,0);
		}
		public DoTrueExprContext doTrueExpr() {
			return getRuleContext(DoTrueExprContext.class,0);
		}
		public DoBodyContext doBody() {
			return getRuleContext(DoBodyContext.class,0);
		}
		public DoDeclContext doDecl() {
			return getRuleContext(DoDeclContext.class,0);
		}
		public DoStepDeclContext doStepDecl() {
			return getRuleContext(DoStepDeclContext.class,0);
		}
		public ExprDoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprDo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterExprDo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitExprDo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitExprDo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprDoContext exprDo() throws RecognitionException {
		ExprDoContext _localctx = new ExprDoContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_exprDo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			match(OPEN_PAREN);
			setState(319);
			match(DO);
			setState(320);
			match(OPEN_PAREN);
			setState(322);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(321);
				doDecl();
				}
				break;
			}
			setState(324);
			match(CLOSE_PAREN);
			setState(325);
			match(OPEN_PAREN);
			setState(327);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(326);
				doStepDecl();
				}
				break;
			}
			setState(329);
			match(CLOSE_PAREN);
			setState(330);
			match(OPEN_PAREN);
			setState(331);
			match(OPEN_PAREN);
			setState(332);
			doTestDecl();
			setState(333);
			match(CLOSE_PAREN);
			setState(334);
			doTrueExpr();
			setState(335);
			match(CLOSE_PAREN);
			setState(336);
			doBody();
			setState(337);
			match(CLOSE_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprLetDeclContext extends ParserRuleContext {
		public List<TerminalNode> OPEN_PAREN() { return getTokens(MiniSchemeParser.OPEN_PAREN); }
		public TerminalNode OPEN_PAREN(int i) {
			return getToken(MiniSchemeParser.OPEN_PAREN, i);
		}
		public List<TerminalNode> CLOSE_PAREN() { return getTokens(MiniSchemeParser.CLOSE_PAREN); }
		public TerminalNode CLOSE_PAREN(int i) {
			return getToken(MiniSchemeParser.CLOSE_PAREN, i);
		}
		public LetBodyContext letBody() {
			return getRuleContext(LetBodyContext.class,0);
		}
		public ExprLetNamedContext exprLetNamed() {
			return getRuleContext(ExprLetNamedContext.class,0);
		}
		public TerminalNode LET() { return getToken(MiniSchemeParser.LET, 0); }
		public TerminalNode LETSTAR() { return getToken(MiniSchemeParser.LETSTAR, 0); }
		public TerminalNode LETREC() { return getToken(MiniSchemeParser.LETREC, 0); }
		public LetDeclContext letDecl() {
			return getRuleContext(LetDeclContext.class,0);
		}
		public ExprLetDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprLetDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterExprLetDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitExprLetDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitExprLetDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprLetDeclContext exprLetDecl() throws RecognitionException {
		ExprLetDeclContext _localctx = new ExprLetDeclContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_exprLetDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339);
			match(OPEN_PAREN);
			setState(344);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(340);
				exprLetNamed();
				}
				break;
			case 2:
				{
				setState(341);
				match(LET);
				}
				break;
			case 3:
				{
				setState(342);
				match(LETSTAR);
				}
				break;
			case 4:
				{
				setState(343);
				match(LETREC);
				}
				break;
			}
			setState(346);
			match(OPEN_PAREN);
			setState(348);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				{
				setState(347);
				letDecl();
				}
				break;
			}
			setState(350);
			match(CLOSE_PAREN);
			setState(351);
			letBody();
			setState(352);
			match(CLOSE_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprLetNamedContext extends ParserRuleContext {
		public TerminalNode LET() { return getToken(MiniSchemeParser.LET, 0); }
		public TerminalNode ID() { return getToken(MiniSchemeParser.ID, 0); }
		public ExprLetNamedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprLetNamed; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterExprLetNamed(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitExprLetNamed(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitExprLetNamed(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprLetNamedContext exprLetNamed() throws RecognitionException {
		ExprLetNamedContext _localctx = new ExprLetNamedContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_exprLetNamed);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354);
			match(LET);
			setState(355);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprSymbolContext extends ParserRuleContext {
		public ExprSymbolComponentContext exprSymbolComponent() {
			return getRuleContext(ExprSymbolComponentContext.class,0);
		}
		public TerminalNode QUOTE() { return getToken(MiniSchemeParser.QUOTE, 0); }
		public TerminalNode SINGLE_QUOTE() { return getToken(MiniSchemeParser.SINGLE_QUOTE, 0); }
		public ExprSymbolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprSymbol; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterExprSymbol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitExprSymbol(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitExprSymbol(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprSymbolContext exprSymbol() throws RecognitionException {
		ExprSymbolContext _localctx = new ExprSymbolContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_exprSymbol);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(357);
			_la = _input.LA(1);
			if ( !(_la==SINGLE_QUOTE || _la==QUOTE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(358);
			exprSymbolComponent();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprSymbolComponentContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(MiniSchemeParser.OPEN_PAREN, 0); }
		public TerminalNode CLOSE_PAREN() { return getToken(MiniSchemeParser.CLOSE_PAREN, 0); }
		public List<ExprSymbolComponentContext> exprSymbolComponent() {
			return getRuleContexts(ExprSymbolComponentContext.class);
		}
		public ExprSymbolComponentContext exprSymbolComponent(int i) {
			return getRuleContext(ExprSymbolComponentContext.class,i);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public OpContext op() {
			return getRuleContext(OpContext.class,0);
		}
		public ExprCallContext exprCall() {
			return getRuleContext(ExprCallContext.class,0);
		}
		public ExprSymbolContext exprSymbol() {
			return getRuleContext(ExprSymbolContext.class,0);
		}
		public ExprSymbolComponentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprSymbolComponent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterExprSymbolComponent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitExprSymbolComponent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitExprSymbolComponent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprSymbolComponentContext exprSymbolComponent() throws RecognitionException {
		ExprSymbolComponentContext _localctx = new ExprSymbolComponentContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_exprSymbolComponent);
		int _la;
		try {
			setState(373);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(360);
				match(OPEN_PAREN);
				setState(364);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << EXPONENTIATION) | (1L << SINGLE_QUOTE) | (1L << LOGICAL_NOT) | (1L << LOGICAL_AND) | (1L << LOGICAL_OR) | (1L << LOGICAL_EQ) | (1L << LOGICAL_LE) | (1L << LOGICAL_GE) | (1L << LOGICAL_LT) | (1L << LOGICAL_GT) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << SIN) | (1L << COS) | (1L << TAN) | (1L << ASIN) | (1L << ACOS) | (1L << ATAN) | (1L << SINH) | (1L << COSH) | (1L << TANH) | (1L << SQRT) | (1L << ROUND) | (1L << FLOOR) | (1L << CEILING) | (1L << TRUNCATE) | (1L << REMAINDER) | (1L << MODULO) | (1L << TODEG_FN) | (1L << TORAD_FN) | (1L << CAR))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CDR - 64)) | (1L << (VECTOR_REF_FN - 64)) | (1L << (VECTORLEN_FN - 64)) | (1L << (DISPLAY - 64)) | (1L << (STRAPPEND_FN - 64)) | (1L << (STRLEN_FN - 64)) | (1L << (STREQ_FN - 64)) | (1L << (STRLE_FN - 64)) | (1L << (STRGE_FN - 64)) | (1L << (STRLT_FN - 64)) | (1L << (STRGT_FN - 64)) | (1L << (STRSUBSTR - 64)) | (1L << (NUMBER_FN - 64)) | (1L << (BOOL_FN - 64)) | (1L << (CHAR_FN - 64)) | (1L << (STRING_FN - 64)) | (1L << (LIST_FN - 64)) | (1L << (VECTOR_FN - 64)) | (1L << (NULL_FN - 64)) | (1L << (SYMBOL_FN - 64)) | (1L << (PAIR_FN - 64)) | (1L << (EQ_FN - 64)) | (1L << (EQUAL_FN - 64)) | (1L << (RANDINT_FN - 64)) | (1L << (RANDDOUBLE_FN - 64)) | (1L << (RAND_FN - 64)) | (1L << (STRTONUM_FN - 64)) | (1L << (NUMTOSTR_FN - 64)) | (1L << (STRTOLIST_FN - 64)) | (1L << (LISTTOSTR_FN - 64)) | (1L << (ID - 64)))) != 0)) {
					{
					{
					setState(361);
					exprSymbolComponent();
					}
					}
					setState(366);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(367);
				match(CLOSE_PAREN);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(368);
				variable();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(369);
				constant();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(370);
				op();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(371);
				exprCall();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(372);
				exprSymbol();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprTermContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public ExprTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterExprTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitExprTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitExprTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprTermContext exprTerm() throws RecognitionException {
		ExprTermContext _localctx = new ExprTermContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_exprTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			term();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprBeginContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(MiniSchemeParser.OPEN_PAREN, 0); }
		public TerminalNode BEGIN() { return getToken(MiniSchemeParser.BEGIN, 0); }
		public TerminalNode CLOSE_PAREN() { return getToken(MiniSchemeParser.CLOSE_PAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprBeginContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprBegin; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterExprBegin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitExprBegin(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitExprBegin(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprBeginContext exprBegin() throws RecognitionException {
		ExprBeginContext _localctx = new ExprBeginContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_exprBegin);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(377);
			match(OPEN_PAREN);
			setState(378);
			match(BEGIN);
			setState(380); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(379);
				expr();
				}
				}
				setState(382); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << EXPONENTIATION) | (1L << SINGLE_QUOTE) | (1L << LOGICAL_NOT) | (1L << LOGICAL_AND) | (1L << LOGICAL_OR) | (1L << LOGICAL_EQ) | (1L << LOGICAL_LE) | (1L << LOGICAL_GE) | (1L << LOGICAL_LT) | (1L << LOGICAL_GT) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << SIN) | (1L << COS) | (1L << TAN) | (1L << ASIN) | (1L << ACOS) | (1L << ATAN) | (1L << SINH) | (1L << COSH) | (1L << TANH) | (1L << SQRT) | (1L << ROUND) | (1L << FLOOR) | (1L << CEILING) | (1L << TRUNCATE) | (1L << REMAINDER) | (1L << MODULO) | (1L << TODEG_FN) | (1L << TORAD_FN) | (1L << CAR))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CDR - 64)) | (1L << (VECTOR_REF_FN - 64)) | (1L << (VECTORLEN_FN - 64)) | (1L << (DISPLAY - 64)) | (1L << (STRAPPEND_FN - 64)) | (1L << (STRLEN_FN - 64)) | (1L << (STREQ_FN - 64)) | (1L << (STRLE_FN - 64)) | (1L << (STRGE_FN - 64)) | (1L << (STRLT_FN - 64)) | (1L << (STRGT_FN - 64)) | (1L << (STRSUBSTR - 64)) | (1L << (NUMBER_FN - 64)) | (1L << (BOOL_FN - 64)) | (1L << (CHAR_FN - 64)) | (1L << (STRING_FN - 64)) | (1L << (LIST_FN - 64)) | (1L << (VECTOR_FN - 64)) | (1L << (NULL_FN - 64)) | (1L << (SYMBOL_FN - 64)) | (1L << (PAIR_FN - 64)) | (1L << (EQ_FN - 64)) | (1L << (EQUAL_FN - 64)) | (1L << (RANDINT_FN - 64)) | (1L << (RANDDOUBLE_FN - 64)) | (1L << (RAND_FN - 64)) | (1L << (STRTONUM_FN - 64)) | (1L << (NUMTOSTR_FN - 64)) | (1L << (STRTOLIST_FN - 64)) | (1L << (LISTTOSTR_FN - 64)) | (1L << (ID - 64)))) != 0) );
			setState(384);
			match(CLOSE_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProcParamsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ProcParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procParams; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterProcParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitProcParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitProcParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcParamsContext procParams() throws RecognitionException {
		ProcParamsContext _localctx = new ProcParamsContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_procParams);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(386);
				expr();
				}
				}
				setState(389); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << EXPONENTIATION) | (1L << SINGLE_QUOTE) | (1L << LOGICAL_NOT) | (1L << LOGICAL_AND) | (1L << LOGICAL_OR) | (1L << LOGICAL_EQ) | (1L << LOGICAL_LE) | (1L << LOGICAL_GE) | (1L << LOGICAL_LT) | (1L << LOGICAL_GT) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << SIN) | (1L << COS) | (1L << TAN) | (1L << ASIN) | (1L << ACOS) | (1L << ATAN) | (1L << SINH) | (1L << COSH) | (1L << TANH) | (1L << SQRT) | (1L << ROUND) | (1L << FLOOR) | (1L << CEILING) | (1L << TRUNCATE) | (1L << REMAINDER) | (1L << MODULO) | (1L << TODEG_FN) | (1L << TORAD_FN) | (1L << CAR))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CDR - 64)) | (1L << (VECTOR_REF_FN - 64)) | (1L << (VECTORLEN_FN - 64)) | (1L << (DISPLAY - 64)) | (1L << (STRAPPEND_FN - 64)) | (1L << (STRLEN_FN - 64)) | (1L << (STREQ_FN - 64)) | (1L << (STRLE_FN - 64)) | (1L << (STRGE_FN - 64)) | (1L << (STRLT_FN - 64)) | (1L << (STRGT_FN - 64)) | (1L << (STRSUBSTR - 64)) | (1L << (NUMBER_FN - 64)) | (1L << (BOOL_FN - 64)) | (1L << (CHAR_FN - 64)) | (1L << (STRING_FN - 64)) | (1L << (LIST_FN - 64)) | (1L << (VECTOR_FN - 64)) | (1L << (NULL_FN - 64)) | (1L << (SYMBOL_FN - 64)) | (1L << (PAIR_FN - 64)) | (1L << (EQ_FN - 64)) | (1L << (EQUAL_FN - 64)) | (1L << (RANDINT_FN - 64)) | (1L << (RANDDOUBLE_FN - 64)) | (1L << (RAND_FN - 64)) | (1L << (STRTONUM_FN - 64)) | (1L << (NUMTOSTR_FN - 64)) | (1L << (STRTOLIST_FN - 64)) | (1L << (LISTTOSTR_FN - 64)) | (1L << (ID - 64)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProcBodyContext extends ParserRuleContext {
		public SeqContext seq() {
			return getRuleContext(SeqContext.class,0);
		}
		public ProcBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterProcBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitProcBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitProcBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcBodyContext procBody() throws RecognitionException {
		ProcBodyContext _localctx = new ProcBodyContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_procBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
			seq();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(394); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(393);
				expr();
				}
				}
				setState(396); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << EXPONENTIATION) | (1L << SINGLE_QUOTE) | (1L << LOGICAL_NOT) | (1L << LOGICAL_AND) | (1L << LOGICAL_OR) | (1L << LOGICAL_EQ) | (1L << LOGICAL_LE) | (1L << LOGICAL_GE) | (1L << LOGICAL_LT) | (1L << LOGICAL_GT) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << SIN) | (1L << COS) | (1L << TAN) | (1L << ASIN) | (1L << ACOS) | (1L << ATAN) | (1L << SINH) | (1L << COSH) | (1L << TANH) | (1L << SQRT) | (1L << ROUND) | (1L << FLOOR) | (1L << CEILING) | (1L << TRUNCATE) | (1L << REMAINDER) | (1L << MODULO) | (1L << TODEG_FN) | (1L << TORAD_FN) | (1L << CAR))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CDR - 64)) | (1L << (VECTOR_REF_FN - 64)) | (1L << (VECTORLEN_FN - 64)) | (1L << (DISPLAY - 64)) | (1L << (STRAPPEND_FN - 64)) | (1L << (STRLEN_FN - 64)) | (1L << (STREQ_FN - 64)) | (1L << (STRLE_FN - 64)) | (1L << (STRGE_FN - 64)) | (1L << (STRLT_FN - 64)) | (1L << (STRGT_FN - 64)) | (1L << (STRSUBSTR - 64)) | (1L << (NUMBER_FN - 64)) | (1L << (BOOL_FN - 64)) | (1L << (CHAR_FN - 64)) | (1L << (STRING_FN - 64)) | (1L << (LIST_FN - 64)) | (1L << (VECTOR_FN - 64)) | (1L << (NULL_FN - 64)) | (1L << (SYMBOL_FN - 64)) | (1L << (PAIR_FN - 64)) | (1L << (EQ_FN - 64)) | (1L << (EQUAL_FN - 64)) | (1L << (RANDINT_FN - 64)) | (1L << (RANDDOUBLE_FN - 64)) | (1L << (RAND_FN - 64)) | (1L << (STRTONUM_FN - 64)) | (1L << (NUMTOSTR_FN - 64)) | (1L << (STRTOLIST_FN - 64)) | (1L << (LISTTOSTR_FN - 64)) | (1L << (ID - 64)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LambdaParamsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public LambdaParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaParams; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterLambdaParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitLambdaParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitLambdaParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaParamsContext lambdaParams() throws RecognitionException {
		LambdaParamsContext _localctx = new LambdaParamsContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_lambdaParams);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(398);
				expr();
				}
				}
				setState(401); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << EXPONENTIATION) | (1L << SINGLE_QUOTE) | (1L << LOGICAL_NOT) | (1L << LOGICAL_AND) | (1L << LOGICAL_OR) | (1L << LOGICAL_EQ) | (1L << LOGICAL_LE) | (1L << LOGICAL_GE) | (1L << LOGICAL_LT) | (1L << LOGICAL_GT) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << SIN) | (1L << COS) | (1L << TAN) | (1L << ASIN) | (1L << ACOS) | (1L << ATAN) | (1L << SINH) | (1L << COSH) | (1L << TANH) | (1L << SQRT) | (1L << ROUND) | (1L << FLOOR) | (1L << CEILING) | (1L << TRUNCATE) | (1L << REMAINDER) | (1L << MODULO) | (1L << TODEG_FN) | (1L << TORAD_FN) | (1L << CAR))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CDR - 64)) | (1L << (VECTOR_REF_FN - 64)) | (1L << (VECTORLEN_FN - 64)) | (1L << (DISPLAY - 64)) | (1L << (STRAPPEND_FN - 64)) | (1L << (STRLEN_FN - 64)) | (1L << (STREQ_FN - 64)) | (1L << (STRLE_FN - 64)) | (1L << (STRGE_FN - 64)) | (1L << (STRLT_FN - 64)) | (1L << (STRGT_FN - 64)) | (1L << (STRSUBSTR - 64)) | (1L << (NUMBER_FN - 64)) | (1L << (BOOL_FN - 64)) | (1L << (CHAR_FN - 64)) | (1L << (STRING_FN - 64)) | (1L << (LIST_FN - 64)) | (1L << (VECTOR_FN - 64)) | (1L << (NULL_FN - 64)) | (1L << (SYMBOL_FN - 64)) | (1L << (PAIR_FN - 64)) | (1L << (EQ_FN - 64)) | (1L << (EQUAL_FN - 64)) | (1L << (RANDINT_FN - 64)) | (1L << (RANDDOUBLE_FN - 64)) | (1L << (RAND_FN - 64)) | (1L << (STRTONUM_FN - 64)) | (1L << (NUMTOSTR_FN - 64)) | (1L << (STRTOLIST_FN - 64)) | (1L << (LISTTOSTR_FN - 64)) | (1L << (ID - 64)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LambdaBodyContext extends ParserRuleContext {
		public SeqContext seq() {
			return getRuleContext(SeqContext.class,0);
		}
		public LambdaBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterLambdaBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitLambdaBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitLambdaBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaBodyContext lambdaBody() throws RecognitionException {
		LambdaBodyContext _localctx = new LambdaBodyContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_lambdaBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(403);
			seq();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LambdaArgsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public LambdaArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterLambdaArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitLambdaArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitLambdaArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaArgsContext lambdaArgs() throws RecognitionException {
		LambdaArgsContext _localctx = new LambdaArgsContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_lambdaArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(406); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(405);
				expr();
				}
				}
				setState(408); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << EXPONENTIATION) | (1L << SINGLE_QUOTE) | (1L << LOGICAL_NOT) | (1L << LOGICAL_AND) | (1L << LOGICAL_OR) | (1L << LOGICAL_EQ) | (1L << LOGICAL_LE) | (1L << LOGICAL_GE) | (1L << LOGICAL_LT) | (1L << LOGICAL_GT) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << SIN) | (1L << COS) | (1L << TAN) | (1L << ASIN) | (1L << ACOS) | (1L << ATAN) | (1L << SINH) | (1L << COSH) | (1L << TANH) | (1L << SQRT) | (1L << ROUND) | (1L << FLOOR) | (1L << CEILING) | (1L << TRUNCATE) | (1L << REMAINDER) | (1L << MODULO) | (1L << TODEG_FN) | (1L << TORAD_FN) | (1L << CAR))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CDR - 64)) | (1L << (VECTOR_REF_FN - 64)) | (1L << (VECTORLEN_FN - 64)) | (1L << (DISPLAY - 64)) | (1L << (STRAPPEND_FN - 64)) | (1L << (STRLEN_FN - 64)) | (1L << (STREQ_FN - 64)) | (1L << (STRLE_FN - 64)) | (1L << (STRGE_FN - 64)) | (1L << (STRLT_FN - 64)) | (1L << (STRGT_FN - 64)) | (1L << (STRSUBSTR - 64)) | (1L << (NUMBER_FN - 64)) | (1L << (BOOL_FN - 64)) | (1L << (CHAR_FN - 64)) | (1L << (STRING_FN - 64)) | (1L << (LIST_FN - 64)) | (1L << (VECTOR_FN - 64)) | (1L << (NULL_FN - 64)) | (1L << (SYMBOL_FN - 64)) | (1L << (PAIR_FN - 64)) | (1L << (EQ_FN - 64)) | (1L << (EQUAL_FN - 64)) | (1L << (RANDINT_FN - 64)) | (1L << (RANDDOUBLE_FN - 64)) | (1L << (RAND_FN - 64)) | (1L << (STRTONUM_FN - 64)) | (1L << (NUMTOSTR_FN - 64)) | (1L << (STRTOLIST_FN - 64)) | (1L << (LISTTOSTR_FN - 64)) | (1L << (ID - 64)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LetDeclContext extends ParserRuleContext {
		public List<TerminalNode> OPEN_BRACKET() { return getTokens(MiniSchemeParser.OPEN_BRACKET); }
		public TerminalNode OPEN_BRACKET(int i) {
			return getToken(MiniSchemeParser.OPEN_BRACKET, i);
		}
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> CLOSE_BRACKET() { return getTokens(MiniSchemeParser.CLOSE_BRACKET); }
		public TerminalNode CLOSE_BRACKET(int i) {
			return getToken(MiniSchemeParser.CLOSE_BRACKET, i);
		}
		public List<TerminalNode> OPEN_PAREN() { return getTokens(MiniSchemeParser.OPEN_PAREN); }
		public TerminalNode OPEN_PAREN(int i) {
			return getToken(MiniSchemeParser.OPEN_PAREN, i);
		}
		public List<TerminalNode> CLOSE_PAREN() { return getTokens(MiniSchemeParser.CLOSE_PAREN); }
		public TerminalNode CLOSE_PAREN(int i) {
			return getToken(MiniSchemeParser.CLOSE_PAREN, i);
		}
		public LetDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterLetDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitLetDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitLetDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetDeclContext letDecl() throws RecognitionException {
		LetDeclContext _localctx = new LetDeclContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_letDecl);
		int _la;
		try {
			setState(430);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(417);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPEN_BRACKET) {
					{
					{
					setState(410);
					match(OPEN_BRACKET);
					setState(411);
					term();
					setState(412);
					expr();
					setState(413);
					match(CLOSE_BRACKET);
					}
					}
					setState(419);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(427);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPEN_PAREN) {
					{
					{
					setState(420);
					match(OPEN_PAREN);
					setState(421);
					term();
					setState(422);
					expr();
					setState(423);
					match(CLOSE_PAREN);
					}
					}
					setState(429);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LetBodyContext extends ParserRuleContext {
		public SeqContext seq() {
			return getRuleContext(SeqContext.class,0);
		}
		public LetBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterLetBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitLetBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitLetBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetBodyContext letBody() throws RecognitionException {
		LetBodyContext _localctx = new LetBodyContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_letBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(432);
			seq();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoDeclContext extends ParserRuleContext {
		public List<TerminalNode> OPEN_BRACKET() { return getTokens(MiniSchemeParser.OPEN_BRACKET); }
		public TerminalNode OPEN_BRACKET(int i) {
			return getToken(MiniSchemeParser.OPEN_BRACKET, i);
		}
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> CLOSE_BRACKET() { return getTokens(MiniSchemeParser.CLOSE_BRACKET); }
		public TerminalNode CLOSE_BRACKET(int i) {
			return getToken(MiniSchemeParser.CLOSE_BRACKET, i);
		}
		public List<TerminalNode> OPEN_PAREN() { return getTokens(MiniSchemeParser.OPEN_PAREN); }
		public TerminalNode OPEN_PAREN(int i) {
			return getToken(MiniSchemeParser.OPEN_PAREN, i);
		}
		public List<TerminalNode> CLOSE_PAREN() { return getTokens(MiniSchemeParser.CLOSE_PAREN); }
		public TerminalNode CLOSE_PAREN(int i) {
			return getToken(MiniSchemeParser.CLOSE_PAREN, i);
		}
		public DoDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterDoDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitDoDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitDoDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoDeclContext doDecl() throws RecognitionException {
		DoDeclContext _localctx = new DoDeclContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_doDecl);
		int _la;
		try {
			setState(454);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(441);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPEN_BRACKET) {
					{
					{
					setState(434);
					match(OPEN_BRACKET);
					setState(435);
					term();
					setState(436);
					expr();
					setState(437);
					match(CLOSE_BRACKET);
					}
					}
					setState(443);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(451);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPEN_PAREN) {
					{
					{
					setState(444);
					match(OPEN_PAREN);
					setState(445);
					term();
					setState(446);
					expr();
					setState(447);
					match(CLOSE_PAREN);
					}
					}
					setState(453);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoStepDeclContext extends ParserRuleContext {
		public List<TerminalNode> OPEN_BRACKET() { return getTokens(MiniSchemeParser.OPEN_BRACKET); }
		public TerminalNode OPEN_BRACKET(int i) {
			return getToken(MiniSchemeParser.OPEN_BRACKET, i);
		}
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> CLOSE_BRACKET() { return getTokens(MiniSchemeParser.CLOSE_BRACKET); }
		public TerminalNode CLOSE_BRACKET(int i) {
			return getToken(MiniSchemeParser.CLOSE_BRACKET, i);
		}
		public List<TerminalNode> OPEN_PAREN() { return getTokens(MiniSchemeParser.OPEN_PAREN); }
		public TerminalNode OPEN_PAREN(int i) {
			return getToken(MiniSchemeParser.OPEN_PAREN, i);
		}
		public List<TerminalNode> CLOSE_PAREN() { return getTokens(MiniSchemeParser.CLOSE_PAREN); }
		public TerminalNode CLOSE_PAREN(int i) {
			return getToken(MiniSchemeParser.CLOSE_PAREN, i);
		}
		public DoStepDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doStepDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterDoStepDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitDoStepDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitDoStepDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoStepDeclContext doStepDecl() throws RecognitionException {
		DoStepDeclContext _localctx = new DoStepDeclContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_doStepDecl);
		int _la;
		try {
			setState(476);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(463);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPEN_BRACKET) {
					{
					{
					setState(456);
					match(OPEN_BRACKET);
					setState(457);
					term();
					setState(458);
					expr();
					setState(459);
					match(CLOSE_BRACKET);
					}
					}
					setState(465);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(473);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPEN_PAREN) {
					{
					{
					setState(466);
					match(OPEN_PAREN);
					setState(467);
					term();
					setState(468);
					expr();
					setState(469);
					match(CLOSE_PAREN);
					}
					}
					setState(475);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoTestDeclContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public DoTestDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doTestDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterDoTestDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitDoTestDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitDoTestDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoTestDeclContext doTestDecl() throws RecognitionException {
		DoTestDeclContext _localctx = new DoTestDeclContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_doTestDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(478);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoTrueExprContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public DoTrueExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doTrueExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterDoTrueExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitDoTrueExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitDoTrueExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoTrueExprContext doTrueExpr() throws RecognitionException {
		DoTrueExprContext _localctx = new DoTrueExprContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_doTrueExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(483);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << EXPONENTIATION) | (1L << SINGLE_QUOTE) | (1L << LOGICAL_NOT) | (1L << LOGICAL_AND) | (1L << LOGICAL_OR) | (1L << LOGICAL_EQ) | (1L << LOGICAL_LE) | (1L << LOGICAL_GE) | (1L << LOGICAL_LT) | (1L << LOGICAL_GT) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << SIN) | (1L << COS) | (1L << TAN) | (1L << ASIN) | (1L << ACOS) | (1L << ATAN) | (1L << SINH) | (1L << COSH) | (1L << TANH) | (1L << SQRT) | (1L << ROUND) | (1L << FLOOR) | (1L << CEILING) | (1L << TRUNCATE) | (1L << REMAINDER) | (1L << MODULO) | (1L << TODEG_FN) | (1L << TORAD_FN) | (1L << CAR))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CDR - 64)) | (1L << (VECTOR_REF_FN - 64)) | (1L << (VECTORLEN_FN - 64)) | (1L << (DISPLAY - 64)) | (1L << (STRAPPEND_FN - 64)) | (1L << (STRLEN_FN - 64)) | (1L << (STREQ_FN - 64)) | (1L << (STRLE_FN - 64)) | (1L << (STRGE_FN - 64)) | (1L << (STRLT_FN - 64)) | (1L << (STRGT_FN - 64)) | (1L << (STRSUBSTR - 64)) | (1L << (NUMBER_FN - 64)) | (1L << (BOOL_FN - 64)) | (1L << (CHAR_FN - 64)) | (1L << (STRING_FN - 64)) | (1L << (LIST_FN - 64)) | (1L << (VECTOR_FN - 64)) | (1L << (NULL_FN - 64)) | (1L << (SYMBOL_FN - 64)) | (1L << (PAIR_FN - 64)) | (1L << (EQ_FN - 64)) | (1L << (EQUAL_FN - 64)) | (1L << (RANDINT_FN - 64)) | (1L << (RANDDOUBLE_FN - 64)) | (1L << (RAND_FN - 64)) | (1L << (STRTONUM_FN - 64)) | (1L << (NUMTOSTR_FN - 64)) | (1L << (STRTOLIST_FN - 64)) | (1L << (LISTTOSTR_FN - 64)) | (1L << (ID - 64)))) != 0)) {
				{
				{
				setState(480);
				expr();
				}
				}
				setState(485);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoBodyContext extends ParserRuleContext {
		public SeqContext seq() {
			return getRuleContext(SeqContext.class,0);
		}
		public DoBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterDoBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitDoBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitDoBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoBodyContext doBody() throws RecognitionException {
		DoBodyContext _localctx = new DoBodyContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_doBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(486);
			seq();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SeqContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public SeqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterSeq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitSeq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitSeq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SeqContext seq() throws RecognitionException {
		SeqContext _localctx = new SeqContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_seq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(489); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(488);
				expr();
				}
				}
				setState(491); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << EXPONENTIATION) | (1L << SINGLE_QUOTE) | (1L << LOGICAL_NOT) | (1L << LOGICAL_AND) | (1L << LOGICAL_OR) | (1L << LOGICAL_EQ) | (1L << LOGICAL_LE) | (1L << LOGICAL_GE) | (1L << LOGICAL_LT) | (1L << LOGICAL_GT) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << SIN) | (1L << COS) | (1L << TAN) | (1L << ASIN) | (1L << ACOS) | (1L << ATAN) | (1L << SINH) | (1L << COSH) | (1L << TANH) | (1L << SQRT) | (1L << ROUND) | (1L << FLOOR) | (1L << CEILING) | (1L << TRUNCATE) | (1L << REMAINDER) | (1L << MODULO) | (1L << TODEG_FN) | (1L << TORAD_FN) | (1L << CAR))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CDR - 64)) | (1L << (VECTOR_REF_FN - 64)) | (1L << (VECTORLEN_FN - 64)) | (1L << (DISPLAY - 64)) | (1L << (STRAPPEND_FN - 64)) | (1L << (STRLEN_FN - 64)) | (1L << (STREQ_FN - 64)) | (1L << (STRLE_FN - 64)) | (1L << (STRGE_FN - 64)) | (1L << (STRLT_FN - 64)) | (1L << (STRGT_FN - 64)) | (1L << (STRSUBSTR - 64)) | (1L << (NUMBER_FN - 64)) | (1L << (BOOL_FN - 64)) | (1L << (CHAR_FN - 64)) | (1L << (STRING_FN - 64)) | (1L << (LIST_FN - 64)) | (1L << (VECTOR_FN - 64)) | (1L << (NULL_FN - 64)) | (1L << (SYMBOL_FN - 64)) | (1L << (PAIR_FN - 64)) | (1L << (EQ_FN - 64)) | (1L << (EQUAL_FN - 64)) | (1L << (RANDINT_FN - 64)) | (1L << (RANDDOUBLE_FN - 64)) | (1L << (RAND_FN - 64)) | (1L << (STRTONUM_FN - 64)) | (1L << (NUMTOSTR_FN - 64)) | (1L << (STRTOLIST_FN - 64)) | (1L << (LISTTOSTR_FN - 64)) | (1L << (ID - 64)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CondCondContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CondCondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condCond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterCondCond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitCondCond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitCondCond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondCondContext condCond() throws RecognitionException {
		CondCondContext _localctx = new CondCondContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_condCond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(493);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CondBodyContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CondBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterCondBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitCondBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitCondBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondBodyContext condBody() throws RecognitionException {
		CondBodyContext _localctx = new CondBodyContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_condBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(495);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfCondContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IfCondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifCond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterIfCond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitIfCond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitIfCond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfCondContext ifCond() throws RecognitionException {
		IfCondContext _localctx = new IfCondContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_ifCond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(497);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfBodyContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IfBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterIfBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitIfBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitIfBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfBodyContext ifBody() throws RecognitionException {
		IfBodyContext _localctx = new IfBodyContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_ifBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(499);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfElseContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IfElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifElse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterIfElse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitIfElse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitIfElse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfElseContext ifElse() throws RecognitionException {
		IfElseContext _localctx = new IfElseContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_ifElse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(501);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OpContext extends ParserRuleContext {
		public UnaryopContext unaryop() {
			return getRuleContext(UnaryopContext.class,0);
		}
		public BinaryopContext binaryop() {
			return getRuleContext(BinaryopContext.class,0);
		}
		public TernaryopContext ternaryop() {
			return getRuleContext(TernaryopContext.class,0);
		}
		public NaryopContext naryop() {
			return getRuleContext(NaryopContext.class,0);
		}
		public OpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OpContext op() throws RecognitionException {
		OpContext _localctx = new OpContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_op);
		try {
			setState(507);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LOGICAL_NOT:
			case SIN:
			case COS:
			case TAN:
			case ASIN:
			case ACOS:
			case ATAN:
			case SINH:
			case COSH:
			case TANH:
			case SQRT:
			case ROUND:
			case FLOOR:
			case CEILING:
			case TRUNCATE:
			case TODEG_FN:
			case TORAD_FN:
			case CAR:
			case CDR:
			case VECTORLEN_FN:
			case DISPLAY:
			case STRLEN_FN:
			case NUMBER_FN:
			case BOOL_FN:
			case CHAR_FN:
			case STRING_FN:
			case LIST_FN:
			case VECTOR_FN:
			case NULL_FN:
			case SYMBOL_FN:
			case PAIR_FN:
			case RAND_FN:
			case STRTONUM_FN:
			case NUMTOSTR_FN:
			case STRTOLIST_FN:
			case LISTTOSTR_FN:
				enterOuterAlt(_localctx, 1);
				{
				setState(503);
				unaryop();
				}
				break;
			case LOGICAL_EQ:
			case LOGICAL_LE:
			case LOGICAL_GE:
			case LOGICAL_LT:
			case LOGICAL_GT:
			case REMAINDER:
			case MODULO:
			case VECTOR_REF_FN:
			case STREQ_FN:
			case STRLE_FN:
			case STRGE_FN:
			case STRLT_FN:
			case STRGT_FN:
			case RANDINT_FN:
			case RANDDOUBLE_FN:
				enterOuterAlt(_localctx, 2);
				{
				setState(504);
				binaryop();
				}
				break;
			case STRSUBSTR:
				enterOuterAlt(_localctx, 3);
				{
				setState(505);
				ternaryop();
				}
				break;
			case PLUS:
			case MINUS:
			case STAR:
			case SLASH:
			case EXPONENTIATION:
			case LOGICAL_AND:
			case LOGICAL_OR:
			case STRAPPEND_FN:
			case EQ_FN:
			case EQUAL_FN:
				enterOuterAlt(_localctx, 4);
				{
				setState(506);
				naryop();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryopContext extends ParserRuleContext {
		public TerminalNode SIN() { return getToken(MiniSchemeParser.SIN, 0); }
		public TerminalNode COS() { return getToken(MiniSchemeParser.COS, 0); }
		public TerminalNode TAN() { return getToken(MiniSchemeParser.TAN, 0); }
		public TerminalNode ASIN() { return getToken(MiniSchemeParser.ASIN, 0); }
		public TerminalNode ACOS() { return getToken(MiniSchemeParser.ACOS, 0); }
		public TerminalNode ATAN() { return getToken(MiniSchemeParser.ATAN, 0); }
		public TerminalNode SQRT() { return getToken(MiniSchemeParser.SQRT, 0); }
		public TerminalNode ROUND() { return getToken(MiniSchemeParser.ROUND, 0); }
		public TerminalNode FLOOR() { return getToken(MiniSchemeParser.FLOOR, 0); }
		public TerminalNode CEILING() { return getToken(MiniSchemeParser.CEILING, 0); }
		public TerminalNode TRUNCATE() { return getToken(MiniSchemeParser.TRUNCATE, 0); }
		public TerminalNode DISPLAY() { return getToken(MiniSchemeParser.DISPLAY, 0); }
		public TerminalNode NUMBER_FN() { return getToken(MiniSchemeParser.NUMBER_FN, 0); }
		public TerminalNode STRING_FN() { return getToken(MiniSchemeParser.STRING_FN, 0); }
		public TerminalNode CHAR_FN() { return getToken(MiniSchemeParser.CHAR_FN, 0); }
		public TerminalNode BOOL_FN() { return getToken(MiniSchemeParser.BOOL_FN, 0); }
		public TerminalNode LIST_FN() { return getToken(MiniSchemeParser.LIST_FN, 0); }
		public TerminalNode NULL_FN() { return getToken(MiniSchemeParser.NULL_FN, 0); }
		public TerminalNode SYMBOL_FN() { return getToken(MiniSchemeParser.SYMBOL_FN, 0); }
		public TerminalNode VECTOR_FN() { return getToken(MiniSchemeParser.VECTOR_FN, 0); }
		public TerminalNode CAR() { return getToken(MiniSchemeParser.CAR, 0); }
		public TerminalNode CDR() { return getToken(MiniSchemeParser.CDR, 0); }
		public TerminalNode STRLEN_FN() { return getToken(MiniSchemeParser.STRLEN_FN, 0); }
		public TerminalNode PAIR_FN() { return getToken(MiniSchemeParser.PAIR_FN, 0); }
		public TerminalNode STRTONUM_FN() { return getToken(MiniSchemeParser.STRTONUM_FN, 0); }
		public TerminalNode NUMTOSTR_FN() { return getToken(MiniSchemeParser.NUMTOSTR_FN, 0); }
		public TerminalNode STRTOLIST_FN() { return getToken(MiniSchemeParser.STRTOLIST_FN, 0); }
		public TerminalNode LISTTOSTR_FN() { return getToken(MiniSchemeParser.LISTTOSTR_FN, 0); }
		public TerminalNode TODEG_FN() { return getToken(MiniSchemeParser.TODEG_FN, 0); }
		public TerminalNode TORAD_FN() { return getToken(MiniSchemeParser.TORAD_FN, 0); }
		public TerminalNode LOGICAL_NOT() { return getToken(MiniSchemeParser.LOGICAL_NOT, 0); }
		public TerminalNode VECTORLEN_FN() { return getToken(MiniSchemeParser.VECTORLEN_FN, 0); }
		public TerminalNode SINH() { return getToken(MiniSchemeParser.SINH, 0); }
		public TerminalNode COSH() { return getToken(MiniSchemeParser.COSH, 0); }
		public TerminalNode TANH() { return getToken(MiniSchemeParser.TANH, 0); }
		public TerminalNode RAND_FN() { return getToken(MiniSchemeParser.RAND_FN, 0); }
		public UnaryopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterUnaryop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitUnaryop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitUnaryop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryopContext unaryop() throws RecognitionException {
		UnaryopContext _localctx = new UnaryopContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_unaryop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(509);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LOGICAL_NOT) | (1L << SIN) | (1L << COS) | (1L << TAN) | (1L << ASIN) | (1L << ACOS) | (1L << ATAN) | (1L << SINH) | (1L << COSH) | (1L << TANH) | (1L << SQRT) | (1L << ROUND) | (1L << FLOOR) | (1L << CEILING) | (1L << TRUNCATE) | (1L << TODEG_FN) | (1L << TORAD_FN) | (1L << CAR))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CDR - 64)) | (1L << (VECTORLEN_FN - 64)) | (1L << (DISPLAY - 64)) | (1L << (STRLEN_FN - 64)) | (1L << (NUMBER_FN - 64)) | (1L << (BOOL_FN - 64)) | (1L << (CHAR_FN - 64)) | (1L << (STRING_FN - 64)) | (1L << (LIST_FN - 64)) | (1L << (VECTOR_FN - 64)) | (1L << (NULL_FN - 64)) | (1L << (SYMBOL_FN - 64)) | (1L << (PAIR_FN - 64)) | (1L << (RAND_FN - 64)) | (1L << (STRTONUM_FN - 64)) | (1L << (NUMTOSTR_FN - 64)) | (1L << (STRTOLIST_FN - 64)) | (1L << (LISTTOSTR_FN - 64)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryopContext extends ParserRuleContext {
		public TerminalNode LOGICAL_GT() { return getToken(MiniSchemeParser.LOGICAL_GT, 0); }
		public TerminalNode LOGICAL_GE() { return getToken(MiniSchemeParser.LOGICAL_GE, 0); }
		public TerminalNode LOGICAL_LT() { return getToken(MiniSchemeParser.LOGICAL_LT, 0); }
		public TerminalNode LOGICAL_LE() { return getToken(MiniSchemeParser.LOGICAL_LE, 0); }
		public TerminalNode LOGICAL_EQ() { return getToken(MiniSchemeParser.LOGICAL_EQ, 0); }
		public TerminalNode STREQ_FN() { return getToken(MiniSchemeParser.STREQ_FN, 0); }
		public TerminalNode STRLT_FN() { return getToken(MiniSchemeParser.STRLT_FN, 0); }
		public TerminalNode STRLE_FN() { return getToken(MiniSchemeParser.STRLE_FN, 0); }
		public TerminalNode STRGT_FN() { return getToken(MiniSchemeParser.STRGT_FN, 0); }
		public TerminalNode STRGE_FN() { return getToken(MiniSchemeParser.STRGE_FN, 0); }
		public TerminalNode RANDINT_FN() { return getToken(MiniSchemeParser.RANDINT_FN, 0); }
		public TerminalNode RANDDOUBLE_FN() { return getToken(MiniSchemeParser.RANDDOUBLE_FN, 0); }
		public TerminalNode VECTOR_REF_FN() { return getToken(MiniSchemeParser.VECTOR_REF_FN, 0); }
		public TerminalNode MODULO() { return getToken(MiniSchemeParser.MODULO, 0); }
		public TerminalNode REMAINDER() { return getToken(MiniSchemeParser.REMAINDER, 0); }
		public BinaryopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterBinaryop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitBinaryop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitBinaryop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryopContext binaryop() throws RecognitionException {
		BinaryopContext _localctx = new BinaryopContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_binaryop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(511);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LOGICAL_EQ) | (1L << LOGICAL_LE) | (1L << LOGICAL_GE) | (1L << LOGICAL_LT) | (1L << LOGICAL_GT) | (1L << REMAINDER) | (1L << MODULO))) != 0) || ((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (VECTOR_REF_FN - 67)) | (1L << (STREQ_FN - 67)) | (1L << (STRLE_FN - 67)) | (1L << (STRGE_FN - 67)) | (1L << (STRLT_FN - 67)) | (1L << (STRGT_FN - 67)) | (1L << (RANDINT_FN - 67)) | (1L << (RANDDOUBLE_FN - 67)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TernaryopContext extends ParserRuleContext {
		public TerminalNode STRSUBSTR() { return getToken(MiniSchemeParser.STRSUBSTR, 0); }
		public TernaryopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ternaryop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterTernaryop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitTernaryop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitTernaryop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TernaryopContext ternaryop() throws RecognitionException {
		TernaryopContext _localctx = new TernaryopContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_ternaryop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(513);
			match(STRSUBSTR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NaryopContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(MiniSchemeParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(MiniSchemeParser.MINUS, 0); }
		public TerminalNode STAR() { return getToken(MiniSchemeParser.STAR, 0); }
		public TerminalNode SLASH() { return getToken(MiniSchemeParser.SLASH, 0); }
		public TerminalNode EXPONENTIATION() { return getToken(MiniSchemeParser.EXPONENTIATION, 0); }
		public TerminalNode STRAPPEND_FN() { return getToken(MiniSchemeParser.STRAPPEND_FN, 0); }
		public TerminalNode EQ_FN() { return getToken(MiniSchemeParser.EQ_FN, 0); }
		public TerminalNode EQUAL_FN() { return getToken(MiniSchemeParser.EQUAL_FN, 0); }
		public TerminalNode LOGICAL_AND() { return getToken(MiniSchemeParser.LOGICAL_AND, 0); }
		public TerminalNode LOGICAL_OR() { return getToken(MiniSchemeParser.LOGICAL_OR, 0); }
		public NaryopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_naryop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterNaryop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitNaryop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitNaryop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NaryopContext naryop() throws RecognitionException {
		NaryopContext _localctx = new NaryopContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_naryop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(515);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << STAR) | (1L << SLASH) | (1L << EXPONENTIATION) | (1L << LOGICAL_AND) | (1L << LOGICAL_OR))) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (STRAPPEND_FN - 70)) | (1L << (EQ_FN - 70)) | (1L << (EQUAL_FN - 70)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetopContext extends ParserRuleContext {
		public TerminalNode SETCAR_FN() { return getToken(MiniSchemeParser.SETCAR_FN, 0); }
		public TerminalNode SETCDR_FN() { return getToken(MiniSchemeParser.SETCDR_FN, 0); }
		public TerminalNode SETVAR_FN() { return getToken(MiniSchemeParser.SETVAR_FN, 0); }
		public TerminalNode SETVEC_FN() { return getToken(MiniSchemeParser.SETVEC_FN, 0); }
		public SetopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterSetop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitSetop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitSetop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetopContext setop() throws RecognitionException {
		SetopContext _localctx = new SetopContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_setop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(517);
			_la = _input.LA(1);
			if ( !(((((_la - 98)) & ~0x3f) == 0 && ((1L << (_la - 98)) & ((1L << (SETVAR_FN - 98)) | (1L << (SETCAR_FN - 98)) | (1L << (SETCDR_FN - 98)) | (1L << (SETVEC_FN - 98)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReadopContext extends ParserRuleContext {
		public TerminalNode READLINE_FN() { return getToken(MiniSchemeParser.READLINE_FN, 0); }
		public TerminalNode READNUMBER_FN() { return getToken(MiniSchemeParser.READNUMBER_FN, 0); }
		public ReadopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_readop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterReadop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitReadop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitReadop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReadopContext readop() throws RecognitionException {
		ReadopContext _localctx = new ReadopContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_readop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(519);
			_la = _input.LA(1);
			if ( !(_la==READLINE_FN || _la==READNUMBER_FN) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_term);
		try {
			setState(523);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(521);
				variable();
				}
				break;
			case NUMBERLIT:
			case STRINGLIT:
			case CHARLIT:
			case BOOLLIT:
				enterOuterAlt(_localctx, 2);
				{
				setState(522);
				constant();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MiniSchemeParser.ID, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(525);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode NUMBERLIT() { return getToken(MiniSchemeParser.NUMBERLIT, 0); }
		public TerminalNode STRINGLIT() { return getToken(MiniSchemeParser.STRINGLIT, 0); }
		public TerminalNode BOOLLIT() { return getToken(MiniSchemeParser.BOOLLIT, 0); }
		public TerminalNode CHARLIT() { return getToken(MiniSchemeParser.CHARLIT, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniSchemeListener ) ((MiniSchemeListener)listener).exitConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniSchemeVisitor ) return ((MiniSchemeVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(527);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3h\u0214\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\3\2\3\2"+
		"\7\2i\n\2\f\2\16\2l\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5"+
		"\3y\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\u0087\n\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u0099"+
		"\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\5\b\u00b4\n\b\3\b\7\b\u00b7\n\b\f\b"+
		"\16\b\u00ba\13\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00c2\n\b\3\b\7\b\u00c5\n"+
		"\b\f\b\16\b\u00c8\13\b\5\b\u00ca\n\b\3\t\3\t\3\t\3\t\7\t\u00d0\n\t\f\t"+
		"\16\t\u00d3\13\t\3\t\3\t\3\t\3\n\3\n\3\n\7\n\u00db\n\n\f\n\16\n\u00de"+
		"\13\n\3\n\3\n\3\13\3\13\3\13\5\13\u00e5\n\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\5\13\u00ed\n\13\3\13\3\13\5\13\u00f1\n\13\3\13\3\13\5\13\u00f5\n"+
		"\13\3\f\3\f\3\f\3\f\5\f\u00fb\n\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r"+
		"\5\r\u0106\n\r\3\r\3\r\3\r\3\r\5\r\u010c\n\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\6\17\u011e\n\17"+
		"\r\17\16\17\u011f\3\17\3\17\3\17\3\17\3\17\5\17\u0127\n\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\6\17\u0132\n\17\r\17\16\17\u0133\3\17"+
		"\3\17\3\17\3\17\3\17\5\17\u013b\n\17\3\17\3\17\5\17\u013f\n\17\3\20\3"+
		"\20\3\20\3\20\5\20\u0145\n\20\3\20\3\20\3\20\5\20\u014a\n\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\5\21"+
		"\u015b\n\21\3\21\3\21\5\21\u015f\n\21\3\21\3\21\3\21\3\21\3\22\3\22\3"+
		"\22\3\23\3\23\3\23\3\24\3\24\7\24\u016d\n\24\f\24\16\24\u0170\13\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\5\24\u0178\n\24\3\25\3\25\3\26\3\26\3\26"+
		"\6\26\u017f\n\26\r\26\16\26\u0180\3\26\3\26\3\27\6\27\u0186\n\27\r\27"+
		"\16\27\u0187\3\30\3\30\3\31\6\31\u018d\n\31\r\31\16\31\u018e\3\32\6\32"+
		"\u0192\n\32\r\32\16\32\u0193\3\33\3\33\3\34\6\34\u0199\n\34\r\34\16\34"+
		"\u019a\3\35\3\35\3\35\3\35\3\35\7\35\u01a2\n\35\f\35\16\35\u01a5\13\35"+
		"\3\35\3\35\3\35\3\35\3\35\7\35\u01ac\n\35\f\35\16\35\u01af\13\35\5\35"+
		"\u01b1\n\35\3\36\3\36\3\37\3\37\3\37\3\37\3\37\7\37\u01ba\n\37\f\37\16"+
		"\37\u01bd\13\37\3\37\3\37\3\37\3\37\3\37\7\37\u01c4\n\37\f\37\16\37\u01c7"+
		"\13\37\5\37\u01c9\n\37\3 \3 \3 \3 \3 \7 \u01d0\n \f \16 \u01d3\13 \3 "+
		"\3 \3 \3 \3 \7 \u01da\n \f \16 \u01dd\13 \5 \u01df\n \3!\3!\3\"\7\"\u01e4"+
		"\n\"\f\"\16\"\u01e7\13\"\3#\3#\3$\6$\u01ec\n$\r$\16$\u01ed\3%\3%\3&\3"+
		"&\3\'\3\'\3(\3(\3)\3)\3*\3*\3*\3*\5*\u01fe\n*\3+\3+\3,\3,\3-\3-\3.\3."+
		"\3/\3/\3\60\3\60\3\61\3\61\5\61\u020e\n\61\3\62\3\62\3\63\3\63\3\63\2"+
		"\2\64\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>"+
		"@BDFHJLNPRTVXZ\\^`bd\2\t\4\2\22\22--\13\2\24\24.;>?ABFGIIPX]]`c\7\2\27"+
		"\33<=EEJN[\\\7\2\t\f\21\21\25\26HHYZ\3\2dg\3\2^_\3\2\37\"\2\u022c\2j\3"+
		"\2\2\2\4\u0086\3\2\2\2\6\u0098\3\2\2\2\b\u009a\3\2\2\2\n\u00a0\3\2\2\2"+
		"\f\u00a6\3\2\2\2\16\u00c9\3\2\2\2\20\u00cb\3\2\2\2\22\u00d7\3\2\2\2\24"+
		"\u00f4\3\2\2\2\26\u00f6\3\2\2\2\30\u0100\3\2\2\2\32\u010f\3\2\2\2\34\u013e"+
		"\3\2\2\2\36\u0140\3\2\2\2 \u0155\3\2\2\2\"\u0164\3\2\2\2$\u0167\3\2\2"+
		"\2&\u0177\3\2\2\2(\u0179\3\2\2\2*\u017b\3\2\2\2,\u0185\3\2\2\2.\u0189"+
		"\3\2\2\2\60\u018c\3\2\2\2\62\u0191\3\2\2\2\64\u0195\3\2\2\2\66\u0198\3"+
		"\2\2\28\u01b0\3\2\2\2:\u01b2\3\2\2\2<\u01c8\3\2\2\2>\u01de\3\2\2\2@\u01e0"+
		"\3\2\2\2B\u01e5\3\2\2\2D\u01e8\3\2\2\2F\u01eb\3\2\2\2H\u01ef\3\2\2\2J"+
		"\u01f1\3\2\2\2L\u01f3\3\2\2\2N\u01f5\3\2\2\2P\u01f7\3\2\2\2R\u01fd\3\2"+
		"\2\2T\u01ff\3\2\2\2V\u0201\3\2\2\2X\u0203\3\2\2\2Z\u0205\3\2\2\2\\\u0207"+
		"\3\2\2\2^\u0209\3\2\2\2`\u020d\3\2\2\2b\u020f\3\2\2\2d\u0211\3\2\2\2f"+
		"i\5\4\3\2gi\5\6\4\2hf\3\2\2\2hg\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2"+
		"k\3\3\2\2\2lj\3\2\2\2mn\7\5\2\2no\7#\2\2op\5b\62\2pq\5\6\4\2qr\7\6\2\2"+
		"r\u0087\3\2\2\2st\7\5\2\2tu\7#\2\2uv\7\5\2\2vx\5`\61\2wy\5,\27\2xw\3\2"+
		"\2\2xy\3\2\2\2yz\3\2\2\2z{\7\6\2\2{|\5.\30\2|}\7\6\2\2}\u0087\3\2\2\2"+
		"~\177\7\5\2\2\177\u0080\7#\2\2\u0080\u0081\5`\61\2\u0081\u0082\7\5\2\2"+
		"\u0082\u0083\5^\60\2\u0083\u0084\7\6\2\2\u0084\u0085\7\6\2\2\u0085\u0087"+
		"\3\2\2\2\u0086m\3\2\2\2\u0086s\3\2\2\2\u0086~\3\2\2\2\u0087\5\3\2\2\2"+
		"\u0088\u0099\5*\26\2\u0089\u0099\5\b\5\2\u008a\u0099\5\n\6\2\u008b\u0099"+
		"\5\f\7\2\u008c\u0099\5\16\b\2\u008d\u0099\5\20\t\2\u008e\u0099\5\22\n"+
		"\2\u008f\u0099\5\24\13\2\u0090\u0099\5\26\f\2\u0091\u0099\5\30\r\2\u0092"+
		"\u0099\5\32\16\2\u0093\u0099\5\34\17\2\u0094\u0099\5\36\20\2\u0095\u0099"+
		"\5 \21\2\u0096\u0099\5$\23\2\u0097\u0099\5(\25\2\u0098\u0088\3\2\2\2\u0098"+
		"\u0089\3\2\2\2\u0098\u008a\3\2\2\2\u0098\u008b\3\2\2\2\u0098\u008c\3\2"+
		"\2\2\u0098\u008d\3\2\2\2\u0098\u008e\3\2\2\2\u0098\u008f\3\2\2\2\u0098"+
		"\u0090\3\2\2\2\u0098\u0091\3\2\2\2\u0098\u0092\3\2\2\2\u0098\u0093\3\2"+
		"\2\2\u0098\u0094\3\2\2\2\u0098\u0095\3\2\2\2\u0098\u0096\3\2\2\2\u0098"+
		"\u0097\3\2\2\2\u0099\7\3\2\2\2\u009a\u009b\7\5\2\2\u009b\u009c\7C\2\2"+
		"\u009c\u009d\5\6\4\2\u009d\u009e\5\6\4\2\u009e\u009f\7\6\2\2\u009f\t\3"+
		"\2\2\2\u00a0\u00a1\7\5\2\2\u00a1\u00a2\5\\/\2\u00a2\u00a3\5\6\4\2\u00a3"+
		"\u00a4\5F$\2\u00a4\u00a5\7\6\2\2\u00a5\13\3\2\2\2\u00a6\u00a7\7\5\2\2"+
		"\u00a7\u00a8\5\\/\2\u00a8\u00a9\5\6\4\2\u00a9\u00aa\7\5\2\2\u00aa\u00ab"+
		"\5^\60\2\u00ab\u00ac\7\6\2\2\u00ac\u00ad\7\6\2\2\u00ad\r\3\2\2\2\u00ae"+
		"\u00b3\7\5\2\2\u00af\u00b4\5T+\2\u00b0\u00b4\5V,\2\u00b1\u00b4\5X-\2\u00b2"+
		"\u00b4\5Z.\2\u00b3\u00af\3\2\2\2\u00b3\u00b0\3\2\2\2\u00b3\u00b1\3\2\2"+
		"\2\u00b3\u00b2\3\2\2\2\u00b4\u00b8\3\2\2\2\u00b5\u00b7\5\6\4\2\u00b6\u00b5"+
		"\3\2\2\2\u00b7\u00ba\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9"+
		"\u00bb\3\2\2\2\u00ba\u00b8\3\2\2\2\u00bb\u00bc\7\6\2\2\u00bc\u00ca\3\2"+
		"\2\2\u00bd\u00c2\5T+\2\u00be\u00c2\5V,\2\u00bf\u00c2\5X-\2\u00c0\u00c2"+
		"\5Z.\2\u00c1\u00bd\3\2\2\2\u00c1\u00be\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1"+
		"\u00c0\3\2\2\2\u00c2\u00c6\3\2\2\2\u00c3\u00c5\5\6\4\2\u00c4\u00c3\3\2"+
		"\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7"+
		"\u00ca\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00ae\3\2\2\2\u00c9\u00c1\3\2"+
		"\2\2\u00ca\17\3\2\2\2\u00cb\u00cc\7\5\2\2\u00cc\u00cd\7D\2\2\u00cd\u00d1"+
		"\7\5\2\2\u00ce\u00d0\5\6\4\2\u00cf\u00ce\3\2\2\2\u00d0\u00d3\3\2\2\2\u00d1"+
		"\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d4\3\2\2\2\u00d3\u00d1\3\2"+
		"\2\2\u00d4\u00d5\7\6\2\2\u00d5\u00d6\7\6\2\2\u00d6\21\3\2\2\2\u00d7\u00d8"+
		"\7\5\2\2\u00d8\u00dc\7@\2\2\u00d9\u00db\5\6\4\2\u00da\u00d9\3\2\2\2\u00db"+
		"\u00de\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00df\3\2"+
		"\2\2\u00de\u00dc\3\2\2\2\u00df\u00e0\7\6\2\2\u00e0\23\3\2\2\2\u00e1\u00e2"+
		"\7\5\2\2\u00e2\u00e4\5`\61\2\u00e3\u00e5\5\60\31\2\u00e4\u00e3\3\2\2\2"+
		"\u00e4\u00e5\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e7\7\6\2\2\u00e7\u00f5"+
		"\3\2\2\2\u00e8\u00e9\7\5\2\2\u00e9\u00ea\7\5\2\2\u00ea\u00ec\5`\61\2\u00eb"+
		"\u00ed\5\60\31\2\u00ec\u00eb\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ee\3"+
		"\2\2\2\u00ee\u00f0\7\6\2\2\u00ef\u00f1\5\66\34\2\u00f0\u00ef\3\2\2\2\u00f0"+
		"\u00f1\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f3\7\6\2\2\u00f3\u00f5\3\2"+
		"\2\2\u00f4\u00e1\3\2\2\2\u00f4\u00e8\3\2\2\2\u00f5\25\3\2\2\2\u00f6\u00f7"+
		"\7\5\2\2\u00f7\u00f8\7+\2\2\u00f8\u00fa\7\5\2\2\u00f9\u00fb\5\62\32\2"+
		"\u00fa\u00f9\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fd"+
		"\7\6\2\2\u00fd\u00fe\5\64\33\2\u00fe\u00ff\7\6\2\2\u00ff\27\3\2\2\2\u0100"+
		"\u0101\7\5\2\2\u0101\u0102\7\5\2\2\u0102\u0103\7+\2\2\u0103\u0105\7\5"+
		"\2\2\u0104\u0106\5\62\32\2\u0105\u0104\3\2\2\2\u0105\u0106\3\2\2\2\u0106"+
		"\u0107\3\2\2\2\u0107\u0108\7\6\2\2\u0108\u0109\5\64\33\2\u0109\u010b\7"+
		"\6\2\2\u010a\u010c\5\66\34\2\u010b\u010a\3\2\2\2\u010b\u010c\3\2\2\2\u010c"+
		"\u010d\3\2\2\2\u010d\u010e\7\6\2\2\u010e\31\3\2\2\2\u010f\u0110\7\5\2"+
		"\2\u0110\u0111\7$\2\2\u0111\u0112\5L\'\2\u0112\u0113\5N(\2\u0113\u0114"+
		"\5P)\2\u0114\u0115\7\6\2\2\u0115\33\3\2\2\2\u0116\u0117\7\5\2\2\u0117"+
		"\u011d\7%\2\2\u0118\u0119\7\7\2\2\u0119\u011a\5H%\2\u011a\u011b\5J&\2"+
		"\u011b\u011c\7\b\2\2\u011c\u011e\3\2\2\2\u011d\u0118\3\2\2\2\u011e\u011f"+
		"\3\2\2\2\u011f\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0126\3\2\2\2\u0121"+
		"\u0122\7\7\2\2\u0122\u0123\7\'\2\2\u0123\u0124\5J&\2\u0124\u0125\7\b\2"+
		"\2\u0125\u0127\3\2\2\2\u0126\u0121\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u0128"+
		"\3\2\2\2\u0128\u0129\7\6\2\2\u0129\u013f\3\2\2\2\u012a\u012b\7\5\2\2\u012b"+
		"\u0131\7%\2\2\u012c\u012d\7\5\2\2\u012d\u012e\5H%\2\u012e\u012f\5J&\2"+
		"\u012f\u0130\7\6\2\2\u0130\u0132\3\2\2\2\u0131\u012c\3\2\2\2\u0132\u0133"+
		"\3\2\2\2\u0133\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u013a\3\2\2\2\u0135"+
		"\u0136\7\5\2\2\u0136\u0137\7\'\2\2\u0137\u0138\5J&\2\u0138\u0139\7\6\2"+
		"\2\u0139\u013b\3\2\2\2\u013a\u0135\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013c"+
		"\3\2\2\2\u013c\u013d\7\6\2\2\u013d\u013f\3\2\2\2\u013e\u0116\3\2\2\2\u013e"+
		"\u012a\3\2\2\2\u013f\35\3\2\2\2\u0140\u0141\7\5\2\2\u0141\u0142\7&\2\2"+
		"\u0142\u0144\7\5\2\2\u0143\u0145\5<\37\2\u0144\u0143\3\2\2\2\u0144\u0145"+
		"\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0147\7\6\2\2\u0147\u0149\7\5\2\2\u0148"+
		"\u014a\5> \2\u0149\u0148\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u014b\3\2\2"+
		"\2\u014b\u014c\7\6\2\2\u014c\u014d\7\5\2\2\u014d\u014e\7\5\2\2\u014e\u014f"+
		"\5@!\2\u014f\u0150\7\6\2\2\u0150\u0151\5B\"\2\u0151\u0152\7\6\2\2\u0152"+
		"\u0153\5D#\2\u0153\u0154\7\6\2\2\u0154\37\3\2\2\2\u0155\u015a\7\5\2\2"+
		"\u0156\u015b\5\"\22\2\u0157\u015b\7(\2\2\u0158\u015b\7)\2\2\u0159\u015b"+
		"\7*\2\2\u015a\u0156\3\2\2\2\u015a\u0157\3\2\2\2\u015a\u0158\3\2\2\2\u015a"+
		"\u0159\3\2\2\2\u015b\u015c\3\2\2\2\u015c\u015e\7\5\2\2\u015d\u015f\58"+
		"\35\2\u015e\u015d\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u0160\3\2\2\2\u0160"+
		"\u0161\7\6\2\2\u0161\u0162\5:\36\2\u0162\u0163\7\6\2\2\u0163!\3\2\2\2"+
		"\u0164\u0165\7(\2\2\u0165\u0166\7h\2\2\u0166#\3\2\2\2\u0167\u0168\t\2"+
		"\2\2\u0168\u0169\5&\24\2\u0169%\3\2\2\2\u016a\u016e\7\5\2\2\u016b\u016d"+
		"\5&\24\2\u016c\u016b\3\2\2\2\u016d\u0170\3\2\2\2\u016e\u016c\3\2\2\2\u016e"+
		"\u016f\3\2\2\2\u016f\u0171\3\2\2\2\u0170\u016e\3\2\2\2\u0171\u0178\7\6"+
		"\2\2\u0172\u0178\5b\62\2\u0173\u0178\5d\63\2\u0174\u0178\5R*\2\u0175\u0178"+
		"\5\24\13\2\u0176\u0178\5$\23\2\u0177\u016a\3\2\2\2\u0177\u0172\3\2\2\2"+
		"\u0177\u0173\3\2\2\2\u0177\u0174\3\2\2\2\u0177\u0175\3\2\2\2\u0177\u0176"+
		"\3\2\2\2\u0178\'\3\2\2\2\u0179\u017a\5`\61\2\u017a)\3\2\2\2\u017b\u017c"+
		"\7\5\2\2\u017c\u017e\7,\2\2\u017d\u017f\5\6\4\2\u017e\u017d\3\2\2\2\u017f"+
		"\u0180\3\2\2\2\u0180\u017e\3\2\2\2\u0180\u0181\3\2\2\2\u0181\u0182\3\2"+
		"\2\2\u0182\u0183\7\6\2\2\u0183+\3\2\2\2\u0184\u0186\5\6\4\2\u0185\u0184"+
		"\3\2\2\2\u0186\u0187\3\2\2\2\u0187\u0185\3\2\2\2\u0187\u0188\3\2\2\2\u0188"+
		"-\3\2\2\2\u0189\u018a\5F$\2\u018a/\3\2\2\2\u018b\u018d\5\6\4\2\u018c\u018b"+
		"\3\2\2\2\u018d\u018e\3\2\2\2\u018e\u018c\3\2\2\2\u018e\u018f\3\2\2\2\u018f"+
		"\61\3\2\2\2\u0190\u0192\5\6\4\2\u0191\u0190\3\2\2\2\u0192\u0193\3\2\2"+
		"\2\u0193\u0191\3\2\2\2\u0193\u0194\3\2\2\2\u0194\63\3\2\2\2\u0195\u0196"+
		"\5F$\2\u0196\65\3\2\2\2\u0197\u0199\5\6\4\2\u0198\u0197\3\2\2\2\u0199"+
		"\u019a\3\2\2\2\u019a\u0198\3\2\2\2\u019a\u019b\3\2\2\2\u019b\67\3\2\2"+
		"\2\u019c\u019d\7\7\2\2\u019d\u019e\5`\61\2\u019e\u019f\5\6\4\2\u019f\u01a0"+
		"\7\b\2\2\u01a0\u01a2\3\2\2\2\u01a1\u019c\3\2\2\2\u01a2\u01a5\3\2\2\2\u01a3"+
		"\u01a1\3\2\2\2\u01a3\u01a4\3\2\2\2\u01a4\u01b1\3\2\2\2\u01a5\u01a3\3\2"+
		"\2\2\u01a6\u01a7\7\5\2\2\u01a7\u01a8\5`\61\2\u01a8\u01a9\5\6\4\2\u01a9"+
		"\u01aa\7\6\2\2\u01aa\u01ac\3\2\2\2\u01ab\u01a6\3\2\2\2\u01ac\u01af\3\2"+
		"\2\2\u01ad\u01ab\3\2\2\2\u01ad\u01ae\3\2\2\2\u01ae\u01b1\3\2\2\2\u01af"+
		"\u01ad\3\2\2\2\u01b0\u01a3\3\2\2\2\u01b0\u01ad\3\2\2\2\u01b19\3\2\2\2"+
		"\u01b2\u01b3\5F$\2\u01b3;\3\2\2\2\u01b4\u01b5\7\7\2\2\u01b5\u01b6\5`\61"+
		"\2\u01b6\u01b7\5\6\4\2\u01b7\u01b8\7\b\2\2\u01b8\u01ba\3\2\2\2\u01b9\u01b4"+
		"\3\2\2\2\u01ba\u01bd\3\2\2\2\u01bb\u01b9\3\2\2\2\u01bb\u01bc\3\2\2\2\u01bc"+
		"\u01c9\3\2\2\2\u01bd\u01bb\3\2\2\2\u01be\u01bf\7\5\2\2\u01bf\u01c0\5`"+
		"\61\2\u01c0\u01c1\5\6\4\2\u01c1\u01c2\7\6\2\2\u01c2\u01c4\3\2\2\2\u01c3"+
		"\u01be\3\2\2\2\u01c4\u01c7\3\2\2\2\u01c5\u01c3\3\2\2\2\u01c5\u01c6\3\2"+
		"\2\2\u01c6\u01c9\3\2\2\2\u01c7\u01c5\3\2\2\2\u01c8\u01bb\3\2\2\2\u01c8"+
		"\u01c5\3\2\2\2\u01c9=\3\2\2\2\u01ca\u01cb\7\7\2\2\u01cb\u01cc\5`\61\2"+
		"\u01cc\u01cd\5\6\4\2\u01cd\u01ce\7\b\2\2\u01ce\u01d0\3\2\2\2\u01cf\u01ca"+
		"\3\2\2\2\u01d0\u01d3\3\2\2\2\u01d1\u01cf\3\2\2\2\u01d1\u01d2\3\2\2\2\u01d2"+
		"\u01df\3\2\2\2\u01d3\u01d1\3\2\2\2\u01d4\u01d5\7\5\2\2\u01d5\u01d6\5`"+
		"\61\2\u01d6\u01d7\5\6\4\2\u01d7\u01d8\7\6\2\2\u01d8\u01da\3\2\2\2\u01d9"+
		"\u01d4\3\2\2\2\u01da\u01dd\3\2\2\2\u01db\u01d9\3\2\2\2\u01db\u01dc\3\2"+
		"\2\2\u01dc\u01df\3\2\2\2\u01dd\u01db\3\2\2\2\u01de\u01d1\3\2\2\2\u01de"+
		"\u01db\3\2\2\2\u01df?\3\2\2\2\u01e0\u01e1\5\6\4\2\u01e1A\3\2\2\2\u01e2"+
		"\u01e4\5\6\4\2\u01e3\u01e2\3\2\2\2\u01e4\u01e7\3\2\2\2\u01e5\u01e3\3\2"+
		"\2\2\u01e5\u01e6\3\2\2\2\u01e6C\3\2\2\2\u01e7\u01e5\3\2\2\2\u01e8\u01e9"+
		"\5F$\2\u01e9E\3\2\2\2\u01ea\u01ec\5\6\4\2\u01eb\u01ea\3\2\2\2\u01ec\u01ed"+
		"\3\2\2\2\u01ed\u01eb\3\2\2\2\u01ed\u01ee\3\2\2\2\u01eeG\3\2\2\2\u01ef"+
		"\u01f0\5\6\4\2\u01f0I\3\2\2\2\u01f1\u01f2\5\6\4\2\u01f2K\3\2\2\2\u01f3"+
		"\u01f4\5\6\4\2\u01f4M\3\2\2\2\u01f5\u01f6\5\6\4\2\u01f6O\3\2\2\2\u01f7"+
		"\u01f8\5\6\4\2\u01f8Q\3\2\2\2\u01f9\u01fe\5T+\2\u01fa\u01fe\5V,\2\u01fb"+
		"\u01fe\5X-\2\u01fc\u01fe\5Z.\2\u01fd\u01f9\3\2\2\2\u01fd\u01fa\3\2\2\2"+
		"\u01fd\u01fb\3\2\2\2\u01fd\u01fc\3\2\2\2\u01feS\3\2\2\2\u01ff\u0200\t"+
		"\3\2\2\u0200U\3\2\2\2\u0201\u0202\t\4\2\2\u0202W\3\2\2\2\u0203\u0204\7"+
		"O\2\2\u0204Y\3\2\2\2\u0205\u0206\t\5\2\2\u0206[\3\2\2\2\u0207\u0208\t"+
		"\6\2\2\u0208]\3\2\2\2\u0209\u020a\t\7\2\2\u020a_\3\2\2\2\u020b\u020e\5"+
		"b\62\2\u020c\u020e\5d\63\2\u020d\u020b\3\2\2\2\u020d\u020c\3\2\2\2\u020e"+
		"a\3\2\2\2\u020f\u0210\7h\2\2\u0210c\3\2\2\2\u0211\u0212\t\b\2\2\u0212"+
		"e\3\2\2\2\62hjx\u0086\u0098\u00b3\u00b8\u00c1\u00c6\u00c9\u00d1\u00dc"+
		"\u00e4\u00ec\u00f0\u00f4\u00fa\u0105\u010b\u011f\u0126\u0133\u013a\u013e"+
		"\u0144\u0149\u015a\u015e\u016e\u0177\u0180\u0187\u018e\u0193\u019a\u01a3"+
		"\u01ad\u01b0\u01bb\u01c5\u01c8\u01d1\u01db\u01de\u01e5\u01ed\u01fd\u020d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}