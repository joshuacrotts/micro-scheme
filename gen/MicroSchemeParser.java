// Generated from C:/Users/Joshua/Desktop/Files/Java/MicroScheme/src/main/antlr4/com/joshuacrotts/microscheme\MicroScheme.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MicroSchemeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WHITESPACE=1, COMMENT=2, OPEN_PAREN=3, CLOSE_PAREN=4, OPEN_BRACKET=5, 
		CLOSE_BRACKET=6, SINGLE_QUOTE=7, BACK_TICK=8, COMMA=9, HASH=10, ATSIGN=11, 
		PERIOD=12, NUMBERLIT=13, STRINGLIT=14, CHARLIT=15, BOOLLIT=16, DEFINE=17, 
		IF=18, COND=19, OR=20, AND=21, ELSE=22, LAMBDA=23, BEGIN=24, QUOTE=25, 
		UNQUOTE=26, UNQUOTESPLICING=27, WHEN=28, UNLESS=29, APPLY=30, EVAL=31, 
		DO=32, LET=33, LETSTAR=34, LETREC=35, SET=36, SETCAR=37, SETCDR=38, SETVECTOR=39, 
		ID=40;
	public static final int
		RULE_microScheme = 0, RULE_variableDeclaration = 1, RULE_variableBody = 2, 
		RULE_procedureDeclaration = 3, RULE_procedureParameters = 4, RULE_procedureBody = 5, 
		RULE_declExpr = 6, RULE_expr = 7, RULE_beginExpr = 8, RULE_booleanExpr = 9, 
		RULE_evalExpr = 10, RULE_applyExpr = 11, RULE_whenExpr = 12, RULE_whenCond = 13, 
		RULE_unlessExpr = 14, RULE_unlessCond = 15, RULE_setExpr = 16, RULE_setListExpr = 17, 
		RULE_letExpr = 18, RULE_letStarExpr = 19, RULE_letRecExpr = 20, RULE_letParameters = 21, 
		RULE_letBody = 22, RULE_lambdaExpr = 23, RULE_lambdaParameters = 24, RULE_lambdaBody = 25, 
		RULE_doExpr = 26, RULE_doDecl = 27, RULE_doTest = 28, RULE_doTrueExpr = 29, 
		RULE_doBody = 30, RULE_condExpr = 31, RULE_condForm = 32, RULE_ifExpr = 33, 
		RULE_applicationExpr = 34, RULE_applicationArgs = 35, RULE_symbolExpr = 36, 
		RULE_symbolDatum = 37, RULE_symbolDatumRep = 38, RULE_quasiSymbolExpr = 39, 
		RULE_quasiSymbolDatum = 40, RULE_quasiSymbolDatumRep = 41, RULE_constant = 42, 
		RULE_variable = 43;
	private static String[] makeRuleNames() {
		return new String[] {
			"microScheme", "variableDeclaration", "variableBody", "procedureDeclaration", 
			"procedureParameters", "procedureBody", "declExpr", "expr", "beginExpr", 
			"booleanExpr", "evalExpr", "applyExpr", "whenExpr", "whenCond", "unlessExpr", 
			"unlessCond", "setExpr", "setListExpr", "letExpr", "letStarExpr", "letRecExpr", 
			"letParameters", "letBody", "lambdaExpr", "lambdaParameters", "lambdaBody", 
			"doExpr", "doDecl", "doTest", "doTrueExpr", "doBody", "condExpr", "condForm", 
			"ifExpr", "applicationExpr", "applicationArgs", "symbolExpr", "symbolDatum", 
			"symbolDatumRep", "quasiSymbolExpr", "quasiSymbolDatum", "quasiSymbolDatumRep", 
			"constant", "variable"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'('", "')'", "'['", "']'", "'''", "'`'", "','", "'#'", 
			"'@'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WHITESPACE", "COMMENT", "OPEN_PAREN", "CLOSE_PAREN", "OPEN_BRACKET", 
			"CLOSE_BRACKET", "SINGLE_QUOTE", "BACK_TICK", "COMMA", "HASH", "ATSIGN", 
			"PERIOD", "NUMBERLIT", "STRINGLIT", "CHARLIT", "BOOLLIT", "DEFINE", "IF", 
			"COND", "OR", "AND", "ELSE", "LAMBDA", "BEGIN", "QUOTE", "UNQUOTE", "UNQUOTESPLICING", 
			"WHEN", "UNLESS", "APPLY", "EVAL", "DO", "LET", "LETSTAR", "LETREC", 
			"SET", "SETCAR", "SETCDR", "SETVECTOR", "ID"
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
	public String getGrammarFileName() { return "MicroScheme.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MicroSchemeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class MicroSchemeContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MicroSchemeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_microScheme; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterMicroScheme(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitMicroScheme(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitMicroScheme(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MicroSchemeContext microScheme() throws RecognitionException {
		MicroSchemeContext _localctx = new MicroSchemeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_microScheme);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << SINGLE_QUOTE) | (1L << BACK_TICK) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << ID))) != 0)) {
				{
				{
				setState(88);
				expr();
				}
				}
				setState(93);
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

	public static class VariableDeclarationContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(MicroSchemeParser.OPEN_PAREN, 0); }
		public TerminalNode DEFINE() { return getToken(MicroSchemeParser.DEFINE, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public VariableBodyContext variableBody() {
			return getRuleContext(VariableBodyContext.class,0);
		}
		public TerminalNode CLOSE_PAREN() { return getToken(MicroSchemeParser.CLOSE_PAREN, 0); }
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitVariableDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_variableDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(OPEN_PAREN);
			setState(95);
			match(DEFINE);
			setState(96);
			variable();
			setState(97);
			variableBody();
			setState(98);
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

	public static class VariableBodyContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public VariableBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterVariableBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitVariableBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitVariableBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableBodyContext variableBody() throws RecognitionException {
		VariableBodyContext _localctx = new VariableBodyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_variableBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(100);
				expr();
				}
				}
				setState(103); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << SINGLE_QUOTE) | (1L << BACK_TICK) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << ID))) != 0) );
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

	public static class ProcedureDeclarationContext extends ParserRuleContext {
		public List<TerminalNode> OPEN_PAREN() { return getTokens(MicroSchemeParser.OPEN_PAREN); }
		public TerminalNode OPEN_PAREN(int i) {
			return getToken(MicroSchemeParser.OPEN_PAREN, i);
		}
		public TerminalNode DEFINE() { return getToken(MicroSchemeParser.DEFINE, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ProcedureParametersContext procedureParameters() {
			return getRuleContext(ProcedureParametersContext.class,0);
		}
		public List<TerminalNode> CLOSE_PAREN() { return getTokens(MicroSchemeParser.CLOSE_PAREN); }
		public TerminalNode CLOSE_PAREN(int i) {
			return getToken(MicroSchemeParser.CLOSE_PAREN, i);
		}
		public ProcedureBodyContext procedureBody() {
			return getRuleContext(ProcedureBodyContext.class,0);
		}
		public TerminalNode PERIOD() { return getToken(MicroSchemeParser.PERIOD, 0); }
		public ProcedureDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedureDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterProcedureDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitProcedureDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitProcedureDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcedureDeclarationContext procedureDeclaration() throws RecognitionException {
		ProcedureDeclarationContext _localctx = new ProcedureDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_procedureDeclaration);
		try {
			setState(124);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(105);
				match(OPEN_PAREN);
				setState(106);
				match(DEFINE);
				setState(107);
				match(OPEN_PAREN);
				setState(108);
				variable();
				setState(109);
				procedureParameters();
				setState(110);
				match(CLOSE_PAREN);
				setState(111);
				procedureBody();
				setState(112);
				match(CLOSE_PAREN);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(114);
				match(OPEN_PAREN);
				setState(115);
				match(DEFINE);
				setState(116);
				match(OPEN_PAREN);
				setState(117);
				variable();
				setState(118);
				match(PERIOD);
				setState(119);
				procedureParameters();
				setState(120);
				match(CLOSE_PAREN);
				setState(121);
				procedureBody();
				setState(122);
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

	public static class ProcedureParametersContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ProcedureParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedureParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterProcedureParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitProcedureParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitProcedureParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcedureParametersContext procedureParameters() throws RecognitionException {
		ProcedureParametersContext _localctx = new ProcedureParametersContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_procedureParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << SINGLE_QUOTE) | (1L << BACK_TICK) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << ID))) != 0)) {
				{
				{
				setState(126);
				expr();
				}
				}
				setState(131);
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

	public static class ProcedureBodyContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ProcedureBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedureBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterProcedureBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitProcedureBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitProcedureBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcedureBodyContext procedureBody() throws RecognitionException {
		ProcedureBodyContext _localctx = new ProcedureBodyContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_procedureBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(132);
				expr();
				}
				}
				setState(135); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << SINGLE_QUOTE) | (1L << BACK_TICK) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << ID))) != 0) );
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

	public static class DeclExprContext extends ParserRuleContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public ProcedureDeclarationContext procedureDeclaration() {
			return getRuleContext(ProcedureDeclarationContext.class,0);
		}
		public DeclExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterDeclExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitDeclExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitDeclExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclExprContext declExpr() throws RecognitionException {
		DeclExprContext _localctx = new DeclExprContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_declExpr);
		try {
			setState(139);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				variableDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(138);
				procedureDeclaration();
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
		public DeclExprContext declExpr() {
			return getRuleContext(DeclExprContext.class,0);
		}
		public BeginExprContext beginExpr() {
			return getRuleContext(BeginExprContext.class,0);
		}
		public EvalExprContext evalExpr() {
			return getRuleContext(EvalExprContext.class,0);
		}
		public ApplyExprContext applyExpr() {
			return getRuleContext(ApplyExprContext.class,0);
		}
		public WhenExprContext whenExpr() {
			return getRuleContext(WhenExprContext.class,0);
		}
		public UnlessExprContext unlessExpr() {
			return getRuleContext(UnlessExprContext.class,0);
		}
		public SetExprContext setExpr() {
			return getRuleContext(SetExprContext.class,0);
		}
		public SetListExprContext setListExpr() {
			return getRuleContext(SetListExprContext.class,0);
		}
		public LetExprContext letExpr() {
			return getRuleContext(LetExprContext.class,0);
		}
		public LetStarExprContext letStarExpr() {
			return getRuleContext(LetStarExprContext.class,0);
		}
		public LetRecExprContext letRecExpr() {
			return getRuleContext(LetRecExprContext.class,0);
		}
		public LambdaExprContext lambdaExpr() {
			return getRuleContext(LambdaExprContext.class,0);
		}
		public BooleanExprContext booleanExpr() {
			return getRuleContext(BooleanExprContext.class,0);
		}
		public CondExprContext condExpr() {
			return getRuleContext(CondExprContext.class,0);
		}
		public IfExprContext ifExpr() {
			return getRuleContext(IfExprContext.class,0);
		}
		public DoExprContext doExpr() {
			return getRuleContext(DoExprContext.class,0);
		}
		public SymbolExprContext symbolExpr() {
			return getRuleContext(SymbolExprContext.class,0);
		}
		public QuasiSymbolExprContext quasiSymbolExpr() {
			return getRuleContext(QuasiSymbolExprContext.class,0);
		}
		public ApplicationExprContext applicationExpr() {
			return getRuleContext(ApplicationExprContext.class,0);
		}
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_expr);
		try {
			setState(162);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				declExpr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				beginExpr();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(143);
				evalExpr();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(144);
				applyExpr();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(145);
				whenExpr();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(146);
				unlessExpr();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(147);
				setExpr();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(148);
				setListExpr();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(149);
				letExpr();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(150);
				letStarExpr();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(151);
				letRecExpr();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(152);
				lambdaExpr();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(153);
				booleanExpr();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(154);
				condExpr();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(155);
				ifExpr();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(156);
				doExpr();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(157);
				symbolExpr();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(158);
				quasiSymbolExpr();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(159);
				applicationExpr();
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(160);
				constant();
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(161);
				variable();
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

	public static class BeginExprContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(MicroSchemeParser.OPEN_PAREN, 0); }
		public TerminalNode BEGIN() { return getToken(MicroSchemeParser.BEGIN, 0); }
		public TerminalNode CLOSE_PAREN() { return getToken(MicroSchemeParser.CLOSE_PAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BeginExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_beginExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterBeginExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitBeginExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitBeginExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BeginExprContext beginExpr() throws RecognitionException {
		BeginExprContext _localctx = new BeginExprContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_beginExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			match(OPEN_PAREN);
			setState(165);
			match(BEGIN);
			setState(167); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(166);
				expr();
				}
				}
				setState(169); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << SINGLE_QUOTE) | (1L << BACK_TICK) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << ID))) != 0) );
			setState(171);
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

	public static class BooleanExprContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(MicroSchemeParser.OPEN_PAREN, 0); }
		public TerminalNode CLOSE_PAREN() { return getToken(MicroSchemeParser.CLOSE_PAREN, 0); }
		public TerminalNode AND() { return getToken(MicroSchemeParser.AND, 0); }
		public TerminalNode OR() { return getToken(MicroSchemeParser.OR, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BooleanExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterBooleanExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitBooleanExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitBooleanExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanExprContext booleanExpr() throws RecognitionException {
		BooleanExprContext _localctx = new BooleanExprContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_booleanExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			match(OPEN_PAREN);
			setState(174);
			_la = _input.LA(1);
			if ( !(_la==OR || _la==AND) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << SINGLE_QUOTE) | (1L << BACK_TICK) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << ID))) != 0)) {
				{
				{
				setState(175);
				expr();
				}
				}
				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(181);
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

	public static class EvalExprContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(MicroSchemeParser.OPEN_PAREN, 0); }
		public TerminalNode EVAL() { return getToken(MicroSchemeParser.EVAL, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode CLOSE_PAREN() { return getToken(MicroSchemeParser.CLOSE_PAREN, 0); }
		public EvalExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_evalExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterEvalExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitEvalExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitEvalExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EvalExprContext evalExpr() throws RecognitionException {
		EvalExprContext _localctx = new EvalExprContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_evalExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(OPEN_PAREN);
			setState(184);
			match(EVAL);
			setState(185);
			expr();
			setState(186);
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

	public static class ApplyExprContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(MicroSchemeParser.OPEN_PAREN, 0); }
		public TerminalNode APPLY() { return getToken(MicroSchemeParser.APPLY, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode CLOSE_PAREN() { return getToken(MicroSchemeParser.CLOSE_PAREN, 0); }
		public ApplyExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_applyExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterApplyExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitApplyExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitApplyExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ApplyExprContext applyExpr() throws RecognitionException {
		ApplyExprContext _localctx = new ApplyExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_applyExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(OPEN_PAREN);
			setState(189);
			match(APPLY);
			setState(190);
			expr();
			setState(191);
			expr();
			setState(192);
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

	public static class WhenExprContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(MicroSchemeParser.OPEN_PAREN, 0); }
		public TerminalNode WHEN() { return getToken(MicroSchemeParser.WHEN, 0); }
		public WhenCondContext whenCond() {
			return getRuleContext(WhenCondContext.class,0);
		}
		public TerminalNode CLOSE_PAREN() { return getToken(MicroSchemeParser.CLOSE_PAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public WhenExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whenExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterWhenExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitWhenExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitWhenExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhenExprContext whenExpr() throws RecognitionException {
		WhenExprContext _localctx = new WhenExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_whenExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			match(OPEN_PAREN);
			setState(195);
			match(WHEN);
			setState(196);
			whenCond();
			setState(198); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(197);
				expr();
				}
				}
				setState(200); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << SINGLE_QUOTE) | (1L << BACK_TICK) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << ID))) != 0) );
			setState(202);
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

	public static class WhenCondContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public WhenCondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whenCond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterWhenCond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitWhenCond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitWhenCond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhenCondContext whenCond() throws RecognitionException {
		WhenCondContext _localctx = new WhenCondContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_whenCond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
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

	public static class UnlessExprContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(MicroSchemeParser.OPEN_PAREN, 0); }
		public TerminalNode UNLESS() { return getToken(MicroSchemeParser.UNLESS, 0); }
		public UnlessCondContext unlessCond() {
			return getRuleContext(UnlessCondContext.class,0);
		}
		public TerminalNode CLOSE_PAREN() { return getToken(MicroSchemeParser.CLOSE_PAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public UnlessExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unlessExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterUnlessExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitUnlessExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitUnlessExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnlessExprContext unlessExpr() throws RecognitionException {
		UnlessExprContext _localctx = new UnlessExprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_unlessExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			match(OPEN_PAREN);
			setState(207);
			match(UNLESS);
			setState(208);
			unlessCond();
			setState(210); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(209);
				expr();
				}
				}
				setState(212); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << SINGLE_QUOTE) | (1L << BACK_TICK) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << ID))) != 0) );
			setState(214);
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

	public static class UnlessCondContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UnlessCondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unlessCond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterUnlessCond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitUnlessCond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitUnlessCond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnlessCondContext unlessCond() throws RecognitionException {
		UnlessCondContext _localctx = new UnlessCondContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_unlessCond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
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

	public static class SetExprContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(MicroSchemeParser.OPEN_PAREN, 0); }
		public TerminalNode SET() { return getToken(MicroSchemeParser.SET, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode CLOSE_PAREN() { return getToken(MicroSchemeParser.CLOSE_PAREN, 0); }
		public SetExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterSetExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitSetExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitSetExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetExprContext setExpr() throws RecognitionException {
		SetExprContext _localctx = new SetExprContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_setExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			match(OPEN_PAREN);
			setState(219);
			match(SET);
			setState(220);
			variable();
			setState(221);
			expr();
			setState(222);
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

	public static class SetListExprContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(MicroSchemeParser.OPEN_PAREN, 0); }
		public TerminalNode CLOSE_PAREN() { return getToken(MicroSchemeParser.CLOSE_PAREN, 0); }
		public TerminalNode SETCAR() { return getToken(MicroSchemeParser.SETCAR, 0); }
		public TerminalNode SETCDR() { return getToken(MicroSchemeParser.SETCDR, 0); }
		public TerminalNode SETVECTOR() { return getToken(MicroSchemeParser.SETVECTOR, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public SetListExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setListExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterSetListExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitSetListExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitSetListExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetListExprContext setListExpr() throws RecognitionException {
		SetListExprContext _localctx = new SetListExprContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_setListExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(OPEN_PAREN);
			setState(225);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SETCAR) | (1L << SETCDR) | (1L << SETVECTOR))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(227); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(226);
				expr();
				}
				}
				setState(229); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << SINGLE_QUOTE) | (1L << BACK_TICK) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << ID))) != 0) );
			setState(231);
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

	public static class LetExprContext extends ParserRuleContext {
		public List<TerminalNode> OPEN_PAREN() { return getTokens(MicroSchemeParser.OPEN_PAREN); }
		public TerminalNode OPEN_PAREN(int i) {
			return getToken(MicroSchemeParser.OPEN_PAREN, i);
		}
		public TerminalNode LET() { return getToken(MicroSchemeParser.LET, 0); }
		public List<TerminalNode> CLOSE_PAREN() { return getTokens(MicroSchemeParser.CLOSE_PAREN); }
		public TerminalNode CLOSE_PAREN(int i) {
			return getToken(MicroSchemeParser.CLOSE_PAREN, i);
		}
		public LetBodyContext letBody() {
			return getRuleContext(LetBodyContext.class,0);
		}
		public List<LetParametersContext> letParameters() {
			return getRuleContexts(LetParametersContext.class);
		}
		public LetParametersContext letParameters(int i) {
			return getRuleContext(LetParametersContext.class,i);
		}
		public LetExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterLetExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitLetExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitLetExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetExprContext letExpr() throws RecognitionException {
		LetExprContext _localctx = new LetExprContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_letExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(OPEN_PAREN);
			setState(234);
			match(LET);
			setState(235);
			match(OPEN_PAREN);
			setState(239);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPEN_PAREN || _la==OPEN_BRACKET) {
				{
				{
				setState(236);
				letParameters();
				}
				}
				setState(241);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(242);
			match(CLOSE_PAREN);
			setState(243);
			letBody();
			setState(244);
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

	public static class LetStarExprContext extends ParserRuleContext {
		public List<TerminalNode> OPEN_PAREN() { return getTokens(MicroSchemeParser.OPEN_PAREN); }
		public TerminalNode OPEN_PAREN(int i) {
			return getToken(MicroSchemeParser.OPEN_PAREN, i);
		}
		public TerminalNode LETSTAR() { return getToken(MicroSchemeParser.LETSTAR, 0); }
		public List<TerminalNode> CLOSE_PAREN() { return getTokens(MicroSchemeParser.CLOSE_PAREN); }
		public TerminalNode CLOSE_PAREN(int i) {
			return getToken(MicroSchemeParser.CLOSE_PAREN, i);
		}
		public LetBodyContext letBody() {
			return getRuleContext(LetBodyContext.class,0);
		}
		public List<LetParametersContext> letParameters() {
			return getRuleContexts(LetParametersContext.class);
		}
		public LetParametersContext letParameters(int i) {
			return getRuleContext(LetParametersContext.class,i);
		}
		public LetStarExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letStarExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterLetStarExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitLetStarExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitLetStarExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetStarExprContext letStarExpr() throws RecognitionException {
		LetStarExprContext _localctx = new LetStarExprContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_letStarExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			match(OPEN_PAREN);
			setState(247);
			match(LETSTAR);
			setState(248);
			match(OPEN_PAREN);
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPEN_PAREN || _la==OPEN_BRACKET) {
				{
				{
				setState(249);
				letParameters();
				}
				}
				setState(254);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(255);
			match(CLOSE_PAREN);
			setState(256);
			letBody();
			setState(257);
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

	public static class LetRecExprContext extends ParserRuleContext {
		public List<TerminalNode> OPEN_PAREN() { return getTokens(MicroSchemeParser.OPEN_PAREN); }
		public TerminalNode OPEN_PAREN(int i) {
			return getToken(MicroSchemeParser.OPEN_PAREN, i);
		}
		public TerminalNode LETREC() { return getToken(MicroSchemeParser.LETREC, 0); }
		public List<TerminalNode> CLOSE_PAREN() { return getTokens(MicroSchemeParser.CLOSE_PAREN); }
		public TerminalNode CLOSE_PAREN(int i) {
			return getToken(MicroSchemeParser.CLOSE_PAREN, i);
		}
		public LetBodyContext letBody() {
			return getRuleContext(LetBodyContext.class,0);
		}
		public List<LetParametersContext> letParameters() {
			return getRuleContexts(LetParametersContext.class);
		}
		public LetParametersContext letParameters(int i) {
			return getRuleContext(LetParametersContext.class,i);
		}
		public LetRecExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letRecExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterLetRecExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitLetRecExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitLetRecExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetRecExprContext letRecExpr() throws RecognitionException {
		LetRecExprContext _localctx = new LetRecExprContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_letRecExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			match(OPEN_PAREN);
			setState(260);
			match(LETREC);
			setState(261);
			match(OPEN_PAREN);
			setState(265);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPEN_PAREN || _la==OPEN_BRACKET) {
				{
				{
				setState(262);
				letParameters();
				}
				}
				setState(267);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(268);
			match(CLOSE_PAREN);
			setState(269);
			letBody();
			setState(270);
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

	public static class LetParametersContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(MicroSchemeParser.OPEN_PAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode CLOSE_PAREN() { return getToken(MicroSchemeParser.CLOSE_PAREN, 0); }
		public TerminalNode OPEN_BRACKET() { return getToken(MicroSchemeParser.OPEN_BRACKET, 0); }
		public TerminalNode CLOSE_BRACKET() { return getToken(MicroSchemeParser.CLOSE_BRACKET, 0); }
		public LetParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterLetParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitLetParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitLetParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetParametersContext letParameters() throws RecognitionException {
		LetParametersContext _localctx = new LetParametersContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_letParameters);
		try {
			setState(282);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_PAREN:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(272);
				match(OPEN_PAREN);
				setState(273);
				expr();
				setState(274);
				expr();
				setState(275);
				match(CLOSE_PAREN);
				}
				}
				break;
			case OPEN_BRACKET:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(277);
				match(OPEN_BRACKET);
				setState(278);
				expr();
				setState(279);
				expr();
				setState(280);
				match(CLOSE_BRACKET);
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

	public static class LetBodyContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public LetBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterLetBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitLetBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitLetBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetBodyContext letBody() throws RecognitionException {
		LetBodyContext _localctx = new LetBodyContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_letBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(284);
				expr();
				}
				}
				setState(287); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << SINGLE_QUOTE) | (1L << BACK_TICK) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << ID))) != 0) );
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

	public static class LambdaExprContext extends ParserRuleContext {
		public List<TerminalNode> OPEN_PAREN() { return getTokens(MicroSchemeParser.OPEN_PAREN); }
		public TerminalNode OPEN_PAREN(int i) {
			return getToken(MicroSchemeParser.OPEN_PAREN, i);
		}
		public TerminalNode LAMBDA() { return getToken(MicroSchemeParser.LAMBDA, 0); }
		public LambdaParametersContext lambdaParameters() {
			return getRuleContext(LambdaParametersContext.class,0);
		}
		public List<TerminalNode> CLOSE_PAREN() { return getTokens(MicroSchemeParser.CLOSE_PAREN); }
		public TerminalNode CLOSE_PAREN(int i) {
			return getToken(MicroSchemeParser.CLOSE_PAREN, i);
		}
		public LambdaBodyContext lambdaBody() {
			return getRuleContext(LambdaBodyContext.class,0);
		}
		public List<TerminalNode> PERIOD() { return getTokens(MicroSchemeParser.PERIOD); }
		public TerminalNode PERIOD(int i) {
			return getToken(MicroSchemeParser.PERIOD, i);
		}
		public LambdaExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterLambdaExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitLambdaExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitLambdaExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaExprContext lambdaExpr() throws RecognitionException {
		LambdaExprContext _localctx = new LambdaExprContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_lambdaExpr);
		try {
			setState(308);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(289);
				match(OPEN_PAREN);
				setState(290);
				match(LAMBDA);
				setState(291);
				match(OPEN_PAREN);
				setState(292);
				lambdaParameters();
				setState(293);
				match(CLOSE_PAREN);
				setState(294);
				lambdaBody();
				setState(295);
				match(CLOSE_PAREN);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(297);
				match(OPEN_PAREN);
				setState(298);
				match(LAMBDA);
				setState(299);
				match(OPEN_PAREN);
				setState(300);
				lambdaParameters();
				setState(301);
				match(PERIOD);
				setState(302);
				match(PERIOD);
				setState(303);
				match(PERIOD);
				setState(304);
				match(CLOSE_PAREN);
				setState(305);
				lambdaBody();
				setState(306);
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

	public static class LambdaParametersContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public LambdaParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterLambdaParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitLambdaParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitLambdaParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaParametersContext lambdaParameters() throws RecognitionException {
		LambdaParametersContext _localctx = new LambdaParametersContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_lambdaParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << SINGLE_QUOTE) | (1L << BACK_TICK) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << ID))) != 0)) {
				{
				{
				setState(310);
				expr();
				}
				}
				setState(315);
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

	public static class LambdaBodyContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public LambdaBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterLambdaBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitLambdaBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitLambdaBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaBodyContext lambdaBody() throws RecognitionException {
		LambdaBodyContext _localctx = new LambdaBodyContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_lambdaBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(316);
				expr();
				}
				}
				setState(319); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << SINGLE_QUOTE) | (1L << BACK_TICK) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << ID))) != 0) );
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

	public static class DoExprContext extends ParserRuleContext {
		public List<TerminalNode> OPEN_PAREN() { return getTokens(MicroSchemeParser.OPEN_PAREN); }
		public TerminalNode OPEN_PAREN(int i) {
			return getToken(MicroSchemeParser.OPEN_PAREN, i);
		}
		public TerminalNode DO() { return getToken(MicroSchemeParser.DO, 0); }
		public List<TerminalNode> CLOSE_PAREN() { return getTokens(MicroSchemeParser.CLOSE_PAREN); }
		public TerminalNode CLOSE_PAREN(int i) {
			return getToken(MicroSchemeParser.CLOSE_PAREN, i);
		}
		public DoTestContext doTest() {
			return getRuleContext(DoTestContext.class,0);
		}
		public DoBodyContext doBody() {
			return getRuleContext(DoBodyContext.class,0);
		}
		public List<DoDeclContext> doDecl() {
			return getRuleContexts(DoDeclContext.class);
		}
		public DoDeclContext doDecl(int i) {
			return getRuleContext(DoDeclContext.class,i);
		}
		public List<DoTrueExprContext> doTrueExpr() {
			return getRuleContexts(DoTrueExprContext.class);
		}
		public DoTrueExprContext doTrueExpr(int i) {
			return getRuleContext(DoTrueExprContext.class,i);
		}
		public DoExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterDoExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitDoExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitDoExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoExprContext doExpr() throws RecognitionException {
		DoExprContext _localctx = new DoExprContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_doExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(321);
			match(OPEN_PAREN);
			setState(322);
			match(DO);
			setState(323);
			match(OPEN_PAREN);
			setState(327);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPEN_PAREN) {
				{
				{
				setState(324);
				doDecl();
				}
				}
				setState(329);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(330);
			match(CLOSE_PAREN);
			setState(331);
			match(OPEN_PAREN);
			setState(332);
			doTest();
			setState(336);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << SINGLE_QUOTE) | (1L << BACK_TICK) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << ID))) != 0)) {
				{
				{
				setState(333);
				doTrueExpr();
				}
				}
				setState(338);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(339);
			match(CLOSE_PAREN);
			setState(340);
			doBody();
			setState(341);
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

	public static class DoDeclContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(MicroSchemeParser.OPEN_PAREN, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode CLOSE_PAREN() { return getToken(MicroSchemeParser.CLOSE_PAREN, 0); }
		public DoDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterDoDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitDoDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitDoDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoDeclContext doDecl() throws RecognitionException {
		DoDeclContext _localctx = new DoDeclContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_doDecl);
		try {
			setState(354);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(343);
				match(OPEN_PAREN);
				setState(344);
				variable();
				setState(345);
				expr();
				setState(346);
				match(CLOSE_PAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(348);
				match(OPEN_PAREN);
				setState(349);
				variable();
				setState(350);
				expr();
				setState(351);
				expr();
				setState(352);
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

	public static class DoTestContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public DoTestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doTest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterDoTest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitDoTest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitDoTest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoTestContext doTest() throws RecognitionException {
		DoTestContext _localctx = new DoTestContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_doTest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public DoTrueExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doTrueExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterDoTrueExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitDoTrueExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitDoTrueExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoTrueExprContext doTrueExpr() throws RecognitionException {
		DoTrueExprContext _localctx = new DoTrueExprContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_doTrueExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(358);
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

	public static class DoBodyContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public DoBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterDoBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitDoBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitDoBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoBodyContext doBody() throws RecognitionException {
		DoBodyContext _localctx = new DoBodyContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_doBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(361); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(360);
				expr();
				}
				}
				setState(363); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << SINGLE_QUOTE) | (1L << BACK_TICK) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << ID))) != 0) );
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

	public static class CondExprContext extends ParserRuleContext {
		public List<TerminalNode> OPEN_PAREN() { return getTokens(MicroSchemeParser.OPEN_PAREN); }
		public TerminalNode OPEN_PAREN(int i) {
			return getToken(MicroSchemeParser.OPEN_PAREN, i);
		}
		public TerminalNode COND() { return getToken(MicroSchemeParser.COND, 0); }
		public List<TerminalNode> CLOSE_PAREN() { return getTokens(MicroSchemeParser.CLOSE_PAREN); }
		public TerminalNode CLOSE_PAREN(int i) {
			return getToken(MicroSchemeParser.CLOSE_PAREN, i);
		}
		public List<CondFormContext> condForm() {
			return getRuleContexts(CondFormContext.class);
		}
		public CondFormContext condForm(int i) {
			return getRuleContext(CondFormContext.class,i);
		}
		public List<TerminalNode> OPEN_BRACKET() { return getTokens(MicroSchemeParser.OPEN_BRACKET); }
		public TerminalNode OPEN_BRACKET(int i) {
			return getToken(MicroSchemeParser.OPEN_BRACKET, i);
		}
		public List<TerminalNode> CLOSE_BRACKET() { return getTokens(MicroSchemeParser.CLOSE_BRACKET); }
		public TerminalNode CLOSE_BRACKET(int i) {
			return getToken(MicroSchemeParser.CLOSE_BRACKET, i);
		}
		public TerminalNode ELSE() { return getToken(MicroSchemeParser.ELSE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CondExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterCondExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitCondExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitCondExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondExprContext condExpr() throws RecognitionException {
		CondExprContext _localctx = new CondExprContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_condExpr);
		int _la;
		try {
			int _alt;
			setState(421);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(365);
				match(OPEN_PAREN);
				setState(366);
				match(COND);
				setState(371); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(367);
					match(OPEN_PAREN);
					setState(368);
					condForm();
					setState(369);
					match(CLOSE_PAREN);
					}
					}
					setState(373); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==OPEN_PAREN );
				setState(375);
				match(CLOSE_PAREN);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(377);
				match(OPEN_PAREN);
				setState(378);
				match(COND);
				setState(383); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(379);
					match(OPEN_BRACKET);
					setState(380);
					condForm();
					setState(381);
					match(CLOSE_BRACKET);
					}
					}
					setState(385); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==OPEN_BRACKET );
				setState(387);
				match(CLOSE_PAREN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(389);
				match(OPEN_PAREN);
				setState(390);
				match(COND);
				setState(395); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(391);
						match(OPEN_PAREN);
						setState(392);
						condForm();
						setState(393);
						match(CLOSE_PAREN);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(397); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(399);
				match(OPEN_PAREN);
				setState(400);
				match(ELSE);
				setState(401);
				expr();
				setState(402);
				match(CLOSE_PAREN);
				setState(403);
				match(CLOSE_PAREN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(405);
				match(OPEN_PAREN);
				setState(406);
				match(COND);
				setState(411); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(407);
						match(OPEN_BRACKET);
						setState(408);
						condForm();
						setState(409);
						match(CLOSE_BRACKET);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(413); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(415);
				match(OPEN_BRACKET);
				setState(416);
				match(ELSE);
				setState(417);
				expr();
				setState(418);
				match(CLOSE_BRACKET);
				setState(419);
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

	public static class CondFormContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CondFormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condForm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterCondForm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitCondForm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitCondForm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondFormContext condForm() throws RecognitionException {
		CondFormContext _localctx = new CondFormContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_condForm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
			expr();
			setState(424);
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

	public static class IfExprContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(MicroSchemeParser.OPEN_PAREN, 0); }
		public TerminalNode IF() { return getToken(MicroSchemeParser.IF, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode CLOSE_PAREN() { return getToken(MicroSchemeParser.CLOSE_PAREN, 0); }
		public IfExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterIfExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitIfExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitIfExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfExprContext ifExpr() throws RecognitionException {
		IfExprContext _localctx = new IfExprContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_ifExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(426);
			match(OPEN_PAREN);
			setState(427);
			match(IF);
			setState(428);
			expr();
			setState(429);
			expr();
			setState(431);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << SINGLE_QUOTE) | (1L << BACK_TICK) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << ID))) != 0)) {
				{
				setState(430);
				expr();
				}
			}

			setState(433);
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

	public static class ApplicationExprContext extends ParserRuleContext {
		public TerminalNode OPEN_PAREN() { return getToken(MicroSchemeParser.OPEN_PAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ApplicationArgsContext applicationArgs() {
			return getRuleContext(ApplicationArgsContext.class,0);
		}
		public TerminalNode CLOSE_PAREN() { return getToken(MicroSchemeParser.CLOSE_PAREN, 0); }
		public ApplicationExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_applicationExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterApplicationExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitApplicationExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitApplicationExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ApplicationExprContext applicationExpr() throws RecognitionException {
		ApplicationExprContext _localctx = new ApplicationExprContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_applicationExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(435);
			match(OPEN_PAREN);
			setState(436);
			expr();
			setState(437);
			applicationArgs();
			setState(438);
			match(CLOSE_PAREN);
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

	public static class ApplicationArgsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ApplicationArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_applicationArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterApplicationArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitApplicationArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitApplicationArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ApplicationArgsContext applicationArgs() throws RecognitionException {
		ApplicationArgsContext _localctx = new ApplicationArgsContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_applicationArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(443);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << SINGLE_QUOTE) | (1L << BACK_TICK) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << ID))) != 0)) {
				{
				{
				setState(440);
				expr();
				}
				}
				setState(445);
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

	public static class SymbolExprContext extends ParserRuleContext {
		public SymbolDatumRepContext symbolDatumRep() {
			return getRuleContext(SymbolDatumRepContext.class,0);
		}
		public TerminalNode QUOTE() { return getToken(MicroSchemeParser.QUOTE, 0); }
		public TerminalNode SINGLE_QUOTE() { return getToken(MicroSchemeParser.SINGLE_QUOTE, 0); }
		public TerminalNode BACK_TICK() { return getToken(MicroSchemeParser.BACK_TICK, 0); }
		public SymbolExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_symbolExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterSymbolExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitSymbolExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitSymbolExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SymbolExprContext symbolExpr() throws RecognitionException {
		SymbolExprContext _localctx = new SymbolExprContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_symbolExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(446);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SINGLE_QUOTE) | (1L << BACK_TICK) | (1L << QUOTE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(447);
			symbolDatumRep();
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

	public static class SymbolDatumContext extends ParserRuleContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode LAMBDA() { return getToken(MicroSchemeParser.LAMBDA, 0); }
		public SymbolDatumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_symbolDatum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterSymbolDatum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitSymbolDatum(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitSymbolDatum(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SymbolDatumContext symbolDatum() throws RecognitionException {
		SymbolDatumContext _localctx = new SymbolDatumContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_symbolDatum);
		try {
			setState(452);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBERLIT:
			case STRINGLIT:
			case CHARLIT:
			case BOOLLIT:
				enterOuterAlt(_localctx, 1);
				{
				setState(449);
				constant();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(450);
				variable();
				}
				break;
			case LAMBDA:
				enterOuterAlt(_localctx, 3);
				{
				setState(451);
				match(LAMBDA);
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

	public static class SymbolDatumRepContext extends ParserRuleContext {
		public SymbolDatumContext symbolDatum() {
			return getRuleContext(SymbolDatumContext.class,0);
		}
		public TerminalNode OPEN_PAREN() { return getToken(MicroSchemeParser.OPEN_PAREN, 0); }
		public List<SymbolDatumRepContext> symbolDatumRep() {
			return getRuleContexts(SymbolDatumRepContext.class);
		}
		public SymbolDatumRepContext symbolDatumRep(int i) {
			return getRuleContext(SymbolDatumRepContext.class,i);
		}
		public TerminalNode PERIOD() { return getToken(MicroSchemeParser.PERIOD, 0); }
		public TerminalNode CLOSE_PAREN() { return getToken(MicroSchemeParser.CLOSE_PAREN, 0); }
		public SymbolDatumRepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_symbolDatumRep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterSymbolDatumRep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitSymbolDatumRep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitSymbolDatumRep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SymbolDatumRepContext symbolDatumRep() throws RecognitionException {
		SymbolDatumRepContext _localctx = new SymbolDatumRepContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_symbolDatumRep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(469);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				setState(454);
				symbolDatum();
				}
				break;
			case 2:
				{
				{
				setState(455);
				match(OPEN_PAREN);
				setState(456);
				symbolDatumRep();
				setState(457);
				match(PERIOD);
				setState(458);
				symbolDatumRep();
				setState(459);
				match(CLOSE_PAREN);
				}
				}
				break;
			case 3:
				{
				{
				setState(461);
				match(OPEN_PAREN);
				setState(465);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << LAMBDA) | (1L << ID))) != 0)) {
					{
					{
					setState(462);
					symbolDatumRep();
					}
					}
					setState(467);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(468);
				match(CLOSE_PAREN);
				}
				}
				break;
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

	public static class QuasiSymbolExprContext extends ParserRuleContext {
		public TerminalNode BACK_TICK() { return getToken(MicroSchemeParser.BACK_TICK, 0); }
		public QuasiSymbolDatumRepContext quasiSymbolDatumRep() {
			return getRuleContext(QuasiSymbolDatumRepContext.class,0);
		}
		public QuasiSymbolExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quasiSymbolExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterQuasiSymbolExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitQuasiSymbolExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitQuasiSymbolExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuasiSymbolExprContext quasiSymbolExpr() throws RecognitionException {
		QuasiSymbolExprContext _localctx = new QuasiSymbolExprContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_quasiSymbolExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(471);
			match(BACK_TICK);
			setState(472);
			quasiSymbolDatumRep();
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

	public static class QuasiSymbolDatumContext extends ParserRuleContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public SymbolExprContext symbolExpr() {
			return getRuleContext(SymbolExprContext.class,0);
		}
		public EvalExprContext evalExpr() {
			return getRuleContext(EvalExprContext.class,0);
		}
		public ApplyExprContext applyExpr() {
			return getRuleContext(ApplyExprContext.class,0);
		}
		public ApplicationExprContext applicationExpr() {
			return getRuleContext(ApplicationExprContext.class,0);
		}
		public QuasiSymbolDatumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quasiSymbolDatum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterQuasiSymbolDatum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitQuasiSymbolDatum(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitQuasiSymbolDatum(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuasiSymbolDatumContext quasiSymbolDatum() throws RecognitionException {
		QuasiSymbolDatumContext _localctx = new QuasiSymbolDatumContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_quasiSymbolDatum);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(480);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				setState(474);
				constant();
				}
				break;
			case 2:
				{
				setState(475);
				variable();
				}
				break;
			case 3:
				{
				setState(476);
				symbolExpr();
				}
				break;
			case 4:
				{
				setState(477);
				evalExpr();
				}
				break;
			case 5:
				{
				setState(478);
				applyExpr();
				}
				break;
			case 6:
				{
				setState(479);
				applicationExpr();
				}
				break;
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

	public static class QuasiSymbolDatumRepContext extends ParserRuleContext {
		public QuasiSymbolDatumContext quasiSymbolDatum() {
			return getRuleContext(QuasiSymbolDatumContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(MicroSchemeParser.COMMA, 0); }
		public TerminalNode OPEN_PAREN() { return getToken(MicroSchemeParser.OPEN_PAREN, 0); }
		public List<QuasiSymbolDatumRepContext> quasiSymbolDatumRep() {
			return getRuleContexts(QuasiSymbolDatumRepContext.class);
		}
		public QuasiSymbolDatumRepContext quasiSymbolDatumRep(int i) {
			return getRuleContext(QuasiSymbolDatumRepContext.class,i);
		}
		public TerminalNode PERIOD() { return getToken(MicroSchemeParser.PERIOD, 0); }
		public TerminalNode CLOSE_PAREN() { return getToken(MicroSchemeParser.CLOSE_PAREN, 0); }
		public TerminalNode ATSIGN() { return getToken(MicroSchemeParser.ATSIGN, 0); }
		public QuasiSymbolDatumRepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quasiSymbolDatumRep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterQuasiSymbolDatumRep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitQuasiSymbolDatumRep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitQuasiSymbolDatumRep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuasiSymbolDatumRepContext quasiSymbolDatumRep() throws RecognitionException {
		QuasiSymbolDatumRepContext _localctx = new QuasiSymbolDatumRepContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_quasiSymbolDatumRep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(485);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(482);
				match(COMMA);
				}
				break;
			case 2:
				{
				{
				setState(483);
				match(COMMA);
				setState(484);
				match(ATSIGN);
				}
				}
				break;
			}
			setState(502);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(487);
				quasiSymbolDatum();
				}
				break;
			case 2:
				{
				{
				setState(488);
				match(OPEN_PAREN);
				setState(489);
				quasiSymbolDatumRep();
				setState(490);
				match(PERIOD);
				setState(491);
				quasiSymbolDatumRep();
				setState(492);
				match(CLOSE_PAREN);
				}
				}
				break;
			case 3:
				{
				{
				setState(494);
				match(OPEN_PAREN);
				setState(498);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_PAREN) | (1L << SINGLE_QUOTE) | (1L << BACK_TICK) | (1L << COMMA) | (1L << NUMBERLIT) | (1L << STRINGLIT) | (1L << CHARLIT) | (1L << BOOLLIT) | (1L << QUOTE) | (1L << ID))) != 0)) {
					{
					{
					setState(495);
					quasiSymbolDatumRep();
					}
					}
					setState(500);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(501);
				match(CLOSE_PAREN);
				}
				}
				break;
			}
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

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode STRINGLIT() { return getToken(MicroSchemeParser.STRINGLIT, 0); }
		public TerminalNode CHARLIT() { return getToken(MicroSchemeParser.CHARLIT, 0); }
		public TerminalNode BOOLLIT() { return getToken(MicroSchemeParser.BOOLLIT, 0); }
		public TerminalNode NUMBERLIT() { return getToken(MicroSchemeParser.NUMBERLIT, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(504);
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

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MicroSchemeParser.ID, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroSchemeListener ) ((MicroSchemeListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MicroSchemeVisitor ) return ((MicroSchemeVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(506);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3*\u01ff\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\3\2\7\2\\\n\2\f\2\16\2_\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\6"+
		"\4h\n\4\r\4\16\4i\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\5\5\177\n\5\3\6\7\6\u0082\n\6\f\6\16\6\u0085"+
		"\13\6\3\7\6\7\u0088\n\7\r\7\16\7\u0089\3\b\3\b\5\b\u008e\n\b\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\5\t\u00a5\n\t\3\n\3\n\3\n\6\n\u00aa\n\n\r\n\16\n\u00ab\3\n\3\n\3"+
		"\13\3\13\3\13\7\13\u00b3\n\13\f\13\16\13\u00b6\13\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\6\16\u00c9\n"+
		"\16\r\16\16\16\u00ca\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\6\20\u00d5"+
		"\n\20\r\20\16\20\u00d6\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\23\3\23\3\23\6\23\u00e6\n\23\r\23\16\23\u00e7\3\23\3\23\3\24\3\24"+
		"\3\24\3\24\7\24\u00f0\n\24\f\24\16\24\u00f3\13\24\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\7\25\u00fd\n\25\f\25\16\25\u0100\13\25\3\25\3\25"+
		"\3\25\3\25\3\26\3\26\3\26\3\26\7\26\u010a\n\26\f\26\16\26\u010d\13\26"+
		"\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\5\27\u011d\n\27\3\30\6\30\u0120\n\30\r\30\16\30\u0121\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\5\31\u0137\n\31\3\32\7\32\u013a\n\32\f\32\16\32\u013d\13\32"+
		"\3\33\6\33\u0140\n\33\r\33\16\33\u0141\3\34\3\34\3\34\3\34\7\34\u0148"+
		"\n\34\f\34\16\34\u014b\13\34\3\34\3\34\3\34\3\34\7\34\u0151\n\34\f\34"+
		"\16\34\u0154\13\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3"+
		"\35\3\35\3\35\3\35\3\35\5\35\u0165\n\35\3\36\3\36\3\37\3\37\3 \6 \u016c"+
		"\n \r \16 \u016d\3!\3!\3!\3!\3!\3!\6!\u0176\n!\r!\16!\u0177\3!\3!\3!\3"+
		"!\3!\3!\3!\3!\6!\u0182\n!\r!\16!\u0183\3!\3!\3!\3!\3!\3!\3!\3!\6!\u018e"+
		"\n!\r!\16!\u018f\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\6!\u019e\n!\r!\16"+
		"!\u019f\3!\3!\3!\3!\3!\3!\5!\u01a8\n!\3\"\3\"\3\"\3#\3#\3#\3#\3#\5#\u01b2"+
		"\n#\3#\3#\3$\3$\3$\3$\3$\3%\7%\u01bc\n%\f%\16%\u01bf\13%\3&\3&\3&\3\'"+
		"\3\'\3\'\5\'\u01c7\n\'\3(\3(\3(\3(\3(\3(\3(\3(\3(\7(\u01d2\n(\f(\16(\u01d5"+
		"\13(\3(\5(\u01d8\n(\3)\3)\3)\3*\3*\3*\3*\3*\3*\5*\u01e3\n*\3+\3+\3+\5"+
		"+\u01e8\n+\3+\3+\3+\3+\3+\3+\3+\3+\3+\7+\u01f3\n+\f+\16+\u01f6\13+\3+"+
		"\5+\u01f9\n+\3,\3,\3-\3-\3-\2\2.\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVX\2\6\3\2\26\27\3\2\')\4\2\t\n\33"+
		"\33\3\2\17\22\2\u0215\2]\3\2\2\2\4`\3\2\2\2\6g\3\2\2\2\b~\3\2\2\2\n\u0083"+
		"\3\2\2\2\f\u0087\3\2\2\2\16\u008d\3\2\2\2\20\u00a4\3\2\2\2\22\u00a6\3"+
		"\2\2\2\24\u00af\3\2\2\2\26\u00b9\3\2\2\2\30\u00be\3\2\2\2\32\u00c4\3\2"+
		"\2\2\34\u00ce\3\2\2\2\36\u00d0\3\2\2\2 \u00da\3\2\2\2\"\u00dc\3\2\2\2"+
		"$\u00e2\3\2\2\2&\u00eb\3\2\2\2(\u00f8\3\2\2\2*\u0105\3\2\2\2,\u011c\3"+
		"\2\2\2.\u011f\3\2\2\2\60\u0136\3\2\2\2\62\u013b\3\2\2\2\64\u013f\3\2\2"+
		"\2\66\u0143\3\2\2\28\u0164\3\2\2\2:\u0166\3\2\2\2<\u0168\3\2\2\2>\u016b"+
		"\3\2\2\2@\u01a7\3\2\2\2B\u01a9\3\2\2\2D\u01ac\3\2\2\2F\u01b5\3\2\2\2H"+
		"\u01bd\3\2\2\2J\u01c0\3\2\2\2L\u01c6\3\2\2\2N\u01d7\3\2\2\2P\u01d9\3\2"+
		"\2\2R\u01e2\3\2\2\2T\u01e7\3\2\2\2V\u01fa\3\2\2\2X\u01fc\3\2\2\2Z\\\5"+
		"\20\t\2[Z\3\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3\2\2\2^\3\3\2\2\2_]\3\2\2\2"+
		"`a\7\5\2\2ab\7\23\2\2bc\5X-\2cd\5\6\4\2de\7\6\2\2e\5\3\2\2\2fh\5\20\t"+
		"\2gf\3\2\2\2hi\3\2\2\2ig\3\2\2\2ij\3\2\2\2j\7\3\2\2\2kl\7\5\2\2lm\7\23"+
		"\2\2mn\7\5\2\2no\5X-\2op\5\n\6\2pq\7\6\2\2qr\5\f\7\2rs\7\6\2\2s\177\3"+
		"\2\2\2tu\7\5\2\2uv\7\23\2\2vw\7\5\2\2wx\5X-\2xy\7\16\2\2yz\5\n\6\2z{\7"+
		"\6\2\2{|\5\f\7\2|}\7\6\2\2}\177\3\2\2\2~k\3\2\2\2~t\3\2\2\2\177\t\3\2"+
		"\2\2\u0080\u0082\5\20\t\2\u0081\u0080\3\2\2\2\u0082\u0085\3\2\2\2\u0083"+
		"\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\13\3\2\2\2\u0085\u0083\3\2\2"+
		"\2\u0086\u0088\5\20\t\2\u0087\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089"+
		"\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\r\3\2\2\2\u008b\u008e\5\4\3\2"+
		"\u008c\u008e\5\b\5\2\u008d\u008b\3\2\2\2\u008d\u008c\3\2\2\2\u008e\17"+
		"\3\2\2\2\u008f\u00a5\5\16\b\2\u0090\u00a5\5\22\n\2\u0091\u00a5\5\26\f"+
		"\2\u0092\u00a5\5\30\r\2\u0093\u00a5\5\32\16\2\u0094\u00a5\5\36\20\2\u0095"+
		"\u00a5\5\"\22\2\u0096\u00a5\5$\23\2\u0097\u00a5\5&\24\2\u0098\u00a5\5"+
		"(\25\2\u0099\u00a5\5*\26\2\u009a\u00a5\5\60\31\2\u009b\u00a5\5\24\13\2"+
		"\u009c\u00a5\5@!\2\u009d\u00a5\5D#\2\u009e\u00a5\5\66\34\2\u009f\u00a5"+
		"\5J&\2\u00a0\u00a5\5P)\2\u00a1\u00a5\5F$\2\u00a2\u00a5\5V,\2\u00a3\u00a5"+
		"\5X-\2\u00a4\u008f\3\2\2\2\u00a4\u0090\3\2\2\2\u00a4\u0091\3\2\2\2\u00a4"+
		"\u0092\3\2\2\2\u00a4\u0093\3\2\2\2\u00a4\u0094\3\2\2\2\u00a4\u0095\3\2"+
		"\2\2\u00a4\u0096\3\2\2\2\u00a4\u0097\3\2\2\2\u00a4\u0098\3\2\2\2\u00a4"+
		"\u0099\3\2\2\2\u00a4\u009a\3\2\2\2\u00a4\u009b\3\2\2\2\u00a4\u009c\3\2"+
		"\2\2\u00a4\u009d\3\2\2\2\u00a4\u009e\3\2\2\2\u00a4\u009f\3\2\2\2\u00a4"+
		"\u00a0\3\2\2\2\u00a4\u00a1\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a3\3\2"+
		"\2\2\u00a5\21\3\2\2\2\u00a6\u00a7\7\5\2\2\u00a7\u00a9\7\32\2\2\u00a8\u00aa"+
		"\5\20\t\2\u00a9\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00a9\3\2\2\2"+
		"\u00ab\u00ac\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\7\6\2\2\u00ae\23"+
		"\3\2\2\2\u00af\u00b0\7\5\2\2\u00b0\u00b4\t\2\2\2\u00b1\u00b3\5\20\t\2"+
		"\u00b2\u00b1\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5"+
		"\3\2\2\2\u00b5\u00b7\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7\u00b8\7\6\2\2\u00b8"+
		"\25\3\2\2\2\u00b9\u00ba\7\5\2\2\u00ba\u00bb\7!\2\2\u00bb\u00bc\5\20\t"+
		"\2\u00bc\u00bd\7\6\2\2\u00bd\27\3\2\2\2\u00be\u00bf\7\5\2\2\u00bf\u00c0"+
		"\7 \2\2\u00c0\u00c1\5\20\t\2\u00c1\u00c2\5\20\t\2\u00c2\u00c3\7\6\2\2"+
		"\u00c3\31\3\2\2\2\u00c4\u00c5\7\5\2\2\u00c5\u00c6\7\36\2\2\u00c6\u00c8"+
		"\5\34\17\2\u00c7\u00c9\5\20\t\2\u00c8\u00c7\3\2\2\2\u00c9\u00ca\3\2\2"+
		"\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd"+
		"\7\6\2\2\u00cd\33\3\2\2\2\u00ce\u00cf\5\20\t\2\u00cf\35\3\2\2\2\u00d0"+
		"\u00d1\7\5\2\2\u00d1\u00d2\7\37\2\2\u00d2\u00d4\5 \21\2\u00d3\u00d5\5"+
		"\20\t\2\u00d4\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6"+
		"\u00d7\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9\7\6\2\2\u00d9\37\3\2\2"+
		"\2\u00da\u00db\5\20\t\2\u00db!\3\2\2\2\u00dc\u00dd\7\5\2\2\u00dd\u00de"+
		"\7&\2\2\u00de\u00df\5X-\2\u00df\u00e0\5\20\t\2\u00e0\u00e1\7\6\2\2\u00e1"+
		"#\3\2\2\2\u00e2\u00e3\7\5\2\2\u00e3\u00e5\t\3\2\2\u00e4\u00e6\5\20\t\2"+
		"\u00e5\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8"+
		"\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ea\7\6\2\2\u00ea%\3\2\2\2\u00eb"+
		"\u00ec\7\5\2\2\u00ec\u00ed\7#\2\2\u00ed\u00f1\7\5\2\2\u00ee\u00f0\5,\27"+
		"\2\u00ef\u00ee\3\2\2\2\u00f0\u00f3\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2"+
		"\3\2\2\2\u00f2\u00f4\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f4\u00f5\7\6\2\2\u00f5"+
		"\u00f6\5.\30\2\u00f6\u00f7\7\6\2\2\u00f7\'\3\2\2\2\u00f8\u00f9\7\5\2\2"+
		"\u00f9\u00fa\7$\2\2\u00fa\u00fe\7\5\2\2\u00fb\u00fd\5,\27\2\u00fc\u00fb"+
		"\3\2\2\2\u00fd\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff"+
		"\u0101\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u0102\7\6\2\2\u0102\u0103\5."+
		"\30\2\u0103\u0104\7\6\2\2\u0104)\3\2\2\2\u0105\u0106\7\5\2\2\u0106\u0107"+
		"\7%\2\2\u0107\u010b\7\5\2\2\u0108\u010a\5,\27\2\u0109\u0108\3\2\2\2\u010a"+
		"\u010d\3\2\2\2\u010b\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010e\3\2"+
		"\2\2\u010d\u010b\3\2\2\2\u010e\u010f\7\6\2\2\u010f\u0110\5.\30\2\u0110"+
		"\u0111\7\6\2\2\u0111+\3\2\2\2\u0112\u0113\7\5\2\2\u0113\u0114\5\20\t\2"+
		"\u0114\u0115\5\20\t\2\u0115\u0116\7\6\2\2\u0116\u011d\3\2\2\2\u0117\u0118"+
		"\7\7\2\2\u0118\u0119\5\20\t\2\u0119\u011a\5\20\t\2\u011a\u011b\7\b\2\2"+
		"\u011b\u011d\3\2\2\2\u011c\u0112\3\2\2\2\u011c\u0117\3\2\2\2\u011d-\3"+
		"\2\2\2\u011e\u0120\5\20\t\2\u011f\u011e\3\2\2\2\u0120\u0121\3\2\2\2\u0121"+
		"\u011f\3\2\2\2\u0121\u0122\3\2\2\2\u0122/\3\2\2\2\u0123\u0124\7\5\2\2"+
		"\u0124\u0125\7\31\2\2\u0125\u0126\7\5\2\2\u0126\u0127\5\62\32\2\u0127"+
		"\u0128\7\6\2\2\u0128\u0129\5\64\33\2\u0129\u012a\7\6\2\2\u012a\u0137\3"+
		"\2\2\2\u012b\u012c\7\5\2\2\u012c\u012d\7\31\2\2\u012d\u012e\7\5\2\2\u012e"+
		"\u012f\5\62\32\2\u012f\u0130\7\16\2\2\u0130\u0131\7\16\2\2\u0131\u0132"+
		"\7\16\2\2\u0132\u0133\7\6\2\2\u0133\u0134\5\64\33\2\u0134\u0135\7\6\2"+
		"\2\u0135\u0137\3\2\2\2\u0136\u0123\3\2\2\2\u0136\u012b\3\2\2\2\u0137\61"+
		"\3\2\2\2\u0138\u013a\5\20\t\2\u0139\u0138\3\2\2\2\u013a\u013d\3\2\2\2"+
		"\u013b\u0139\3\2\2\2\u013b\u013c\3\2\2\2\u013c\63\3\2\2\2\u013d\u013b"+
		"\3\2\2\2\u013e\u0140\5\20\t\2\u013f\u013e\3\2\2\2\u0140\u0141\3\2\2\2"+
		"\u0141\u013f\3\2\2\2\u0141\u0142\3\2\2\2\u0142\65\3\2\2\2\u0143\u0144"+
		"\7\5\2\2\u0144\u0145\7\"\2\2\u0145\u0149\7\5\2\2\u0146\u0148\58\35\2\u0147"+
		"\u0146\3\2\2\2\u0148\u014b\3\2\2\2\u0149\u0147\3\2\2\2\u0149\u014a\3\2"+
		"\2\2\u014a\u014c\3\2\2\2\u014b\u0149\3\2\2\2\u014c\u014d\7\6\2\2\u014d"+
		"\u014e\7\5\2\2\u014e\u0152\5:\36\2\u014f\u0151\5<\37\2\u0150\u014f\3\2"+
		"\2\2\u0151\u0154\3\2\2\2\u0152\u0150\3\2\2\2\u0152\u0153\3\2\2\2\u0153"+
		"\u0155\3\2\2\2\u0154\u0152\3\2\2\2\u0155\u0156\7\6\2\2\u0156\u0157\5>"+
		" \2\u0157\u0158\7\6\2\2\u0158\67\3\2\2\2\u0159\u015a\7\5\2\2\u015a\u015b"+
		"\5X-\2\u015b\u015c\5\20\t\2\u015c\u015d\7\6\2\2\u015d\u0165\3\2\2\2\u015e"+
		"\u015f\7\5\2\2\u015f\u0160\5X-\2\u0160\u0161\5\20\t\2\u0161\u0162\5\20"+
		"\t\2\u0162\u0163\7\6\2\2\u0163\u0165\3\2\2\2\u0164\u0159\3\2\2\2\u0164"+
		"\u015e\3\2\2\2\u01659\3\2\2\2\u0166\u0167\5\20\t\2\u0167;\3\2\2\2\u0168"+
		"\u0169\5\20\t\2\u0169=\3\2\2\2\u016a\u016c\5\20\t\2\u016b\u016a\3\2\2"+
		"\2\u016c\u016d\3\2\2\2\u016d\u016b\3\2\2\2\u016d\u016e\3\2\2\2\u016e?"+
		"\3\2\2\2\u016f\u0170\7\5\2\2\u0170\u0175\7\25\2\2\u0171\u0172\7\5\2\2"+
		"\u0172\u0173\5B\"\2\u0173\u0174\7\6\2\2\u0174\u0176\3\2\2\2\u0175\u0171"+
		"\3\2\2\2\u0176\u0177\3\2\2\2\u0177\u0175\3\2\2\2\u0177\u0178\3\2\2\2\u0178"+
		"\u0179\3\2\2\2\u0179\u017a\7\6\2\2\u017a\u01a8\3\2\2\2\u017b\u017c\7\5"+
		"\2\2\u017c\u0181\7\25\2\2\u017d\u017e\7\7\2\2\u017e\u017f\5B\"\2\u017f"+
		"\u0180\7\b\2\2\u0180\u0182\3\2\2\2\u0181\u017d\3\2\2\2\u0182\u0183\3\2"+
		"\2\2\u0183\u0181\3\2\2\2\u0183\u0184\3\2\2\2\u0184\u0185\3\2\2\2\u0185"+
		"\u0186\7\6\2\2\u0186\u01a8\3\2\2\2\u0187\u0188\7\5\2\2\u0188\u018d\7\25"+
		"\2\2\u0189\u018a\7\5\2\2\u018a\u018b\5B\"\2\u018b\u018c\7\6\2\2\u018c"+
		"\u018e\3\2\2\2\u018d\u0189\3\2\2\2\u018e\u018f\3\2\2\2\u018f\u018d\3\2"+
		"\2\2\u018f\u0190\3\2\2\2\u0190\u0191\3\2\2\2\u0191\u0192\7\5\2\2\u0192"+
		"\u0193\7\30\2\2\u0193\u0194\5\20\t\2\u0194\u0195\7\6\2\2\u0195\u0196\7"+
		"\6\2\2\u0196\u01a8\3\2\2\2\u0197\u0198\7\5\2\2\u0198\u019d\7\25\2\2\u0199"+
		"\u019a\7\7\2\2\u019a\u019b\5B\"\2\u019b\u019c\7\b\2\2\u019c\u019e\3\2"+
		"\2\2\u019d\u0199\3\2\2\2\u019e\u019f\3\2\2\2\u019f\u019d\3\2\2\2\u019f"+
		"\u01a0\3\2\2\2\u01a0\u01a1\3\2\2\2\u01a1\u01a2\7\7\2\2\u01a2\u01a3\7\30"+
		"\2\2\u01a3\u01a4\5\20\t\2\u01a4\u01a5\7\b\2\2\u01a5\u01a6\7\6\2\2\u01a6"+
		"\u01a8\3\2\2\2\u01a7\u016f\3\2\2\2\u01a7\u017b\3\2\2\2\u01a7\u0187\3\2"+
		"\2\2\u01a7\u0197\3\2\2\2\u01a8A\3\2\2\2\u01a9\u01aa\5\20\t\2\u01aa\u01ab"+
		"\5\20\t\2\u01abC\3\2\2\2\u01ac\u01ad\7\5\2\2\u01ad\u01ae\7\24\2\2\u01ae"+
		"\u01af\5\20\t\2\u01af\u01b1\5\20\t\2\u01b0\u01b2\5\20\t\2\u01b1\u01b0"+
		"\3\2\2\2\u01b1\u01b2\3\2\2\2\u01b2\u01b3\3\2\2\2\u01b3\u01b4\7\6\2\2\u01b4"+
		"E\3\2\2\2\u01b5\u01b6\7\5\2\2\u01b6\u01b7\5\20\t\2\u01b7\u01b8\5H%\2\u01b8"+
		"\u01b9\7\6\2\2\u01b9G\3\2\2\2\u01ba\u01bc\5\20\t\2\u01bb\u01ba\3\2\2\2"+
		"\u01bc\u01bf\3\2\2\2\u01bd\u01bb\3\2\2\2\u01bd\u01be\3\2\2\2\u01beI\3"+
		"\2\2\2\u01bf\u01bd\3\2\2\2\u01c0\u01c1\t\4\2\2\u01c1\u01c2\5N(\2\u01c2"+
		"K\3\2\2\2\u01c3\u01c7\5V,\2\u01c4\u01c7\5X-\2\u01c5\u01c7\7\31\2\2\u01c6"+
		"\u01c3\3\2\2\2\u01c6\u01c4\3\2\2\2\u01c6\u01c5\3\2\2\2\u01c7M\3\2\2\2"+
		"\u01c8\u01d8\5L\'\2\u01c9\u01ca\7\5\2\2\u01ca\u01cb\5N(\2\u01cb\u01cc"+
		"\7\16\2\2\u01cc\u01cd\5N(\2\u01cd\u01ce\7\6\2\2\u01ce\u01d8\3\2\2\2\u01cf"+
		"\u01d3\7\5\2\2\u01d0\u01d2\5N(\2\u01d1\u01d0\3\2\2\2\u01d2\u01d5\3\2\2"+
		"\2\u01d3\u01d1\3\2\2\2\u01d3\u01d4\3\2\2\2\u01d4\u01d6\3\2\2\2\u01d5\u01d3"+
		"\3\2\2\2\u01d6\u01d8\7\6\2\2\u01d7\u01c8\3\2\2\2\u01d7\u01c9\3\2\2\2\u01d7"+
		"\u01cf\3\2\2\2\u01d8O\3\2\2\2\u01d9\u01da\7\n\2\2\u01da\u01db\5T+\2\u01db"+
		"Q\3\2\2\2\u01dc\u01e3\5V,\2\u01dd\u01e3\5X-\2\u01de\u01e3\5J&\2\u01df"+
		"\u01e3\5\26\f\2\u01e0\u01e3\5\30\r\2\u01e1\u01e3\5F$\2\u01e2\u01dc\3\2"+
		"\2\2\u01e2\u01dd\3\2\2\2\u01e2\u01de\3\2\2\2\u01e2\u01df\3\2\2\2\u01e2"+
		"\u01e0\3\2\2\2\u01e2\u01e1\3\2\2\2\u01e3S\3\2\2\2\u01e4\u01e8\7\13\2\2"+
		"\u01e5\u01e6\7\13\2\2\u01e6\u01e8\7\r\2\2\u01e7\u01e4\3\2\2\2\u01e7\u01e5"+
		"\3\2\2\2\u01e7\u01e8\3\2\2\2\u01e8\u01f8\3\2\2\2\u01e9\u01f9\5R*\2\u01ea"+
		"\u01eb\7\5\2\2\u01eb\u01ec\5T+\2\u01ec\u01ed\7\16\2\2\u01ed\u01ee\5T+"+
		"\2\u01ee\u01ef\7\6\2\2\u01ef\u01f9\3\2\2\2\u01f0\u01f4\7\5\2\2\u01f1\u01f3"+
		"\5T+\2\u01f2\u01f1\3\2\2\2\u01f3\u01f6\3\2\2\2\u01f4\u01f2\3\2\2\2\u01f4"+
		"\u01f5\3\2\2\2\u01f5\u01f7\3\2\2\2\u01f6\u01f4\3\2\2\2\u01f7\u01f9\7\6"+
		"\2\2\u01f8\u01e9\3\2\2\2\u01f8\u01ea\3\2\2\2\u01f8\u01f0\3\2\2\2\u01f9"+
		"U\3\2\2\2\u01fa\u01fb\t\5\2\2\u01fbW\3\2\2\2\u01fc\u01fd\7*\2\2\u01fd"+
		"Y\3\2\2\2(]i~\u0083\u0089\u008d\u00a4\u00ab\u00b4\u00ca\u00d6\u00e7\u00f1"+
		"\u00fe\u010b\u011c\u0121\u0136\u013b\u0141\u0149\u0152\u0164\u016d\u0177"+
		"\u0183\u018f\u019f\u01a7\u01b1\u01bd\u01c6\u01d3\u01d7\u01e2\u01e7\u01f4"+
		"\u01f8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}