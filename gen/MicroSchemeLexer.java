// Generated from C:/Users/Joshua/Desktop/Files/Java/MicroScheme/src/main/antlr4/com/joshuacrotts/microscheme\MicroScheme.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MicroSchemeLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"WHITESPACE", "COMMENT", "DIGIT", "UPPER_CASE_LTR", "LOWER_CASE_LTR", 
			"ANY_CASE_LTR", "UNDERSCORE", "QUOTCHAR", "DOUBLE_QUOTE", "ANYCHAR", 
			"NEWLINE", "CARRIAGE_RET", "TAB", "NULL_CHAR", "ESCAPED_CHAR", "ANYCHAR_MOD", 
			"OPEN_PAREN", "CLOSE_PAREN", "OPEN_BRACKET", "CLOSE_BRACKET", "SINGLE_QUOTE", 
			"BACK_TICK", "COMMA", "HASH", "ATSIGN", "PERIOD", "NUMBERLIT", "STRINGLIT", 
			"CHARLIT", "BOOLLIT", "DEFINE", "IF", "COND", "OR", "AND", "ELSE", "LAMBDA", 
			"BEGIN", "QUOTE", "UNQUOTE", "UNQUOTESPLICING", "WHEN", "UNLESS", "APPLY", 
			"EVAL", "DO", "LET", "LETSTAR", "LETREC", "SET", "SETCAR", "SETCDR", 
			"SETVECTOR", "ID"
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


	public MicroSchemeLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MicroScheme.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2*\u0228\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\3\2\6\2q\n\2\r\2\16\2r\3\2\3\2\3\3\3"+
		"\3\7\3y\n\3\f\3\16\3|\13\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3"+
		"\17\3\17\3\17\3\20\3\20\3\20\3\21\6\21\u00a0\n\21\r\21\16\21\u00a1\3\22"+
		"\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31"+
		"\3\31\3\32\3\32\3\33\3\33\3\34\5\34\u00b9\n\34\3\34\6\34\u00bc\n\34\r"+
		"\34\16\34\u00bd\3\34\3\34\7\34\u00c2\n\34\f\34\16\34\u00c5\13\34\5\34"+
		"\u00c7\n\34\3\34\5\34\u00ca\n\34\3\34\6\34\u00cd\n\34\r\34\16\34\u00ce"+
		"\3\34\3\34\7\34\u00d3\n\34\f\34\16\34\u00d6\13\34\5\34\u00d8\n\34\3\34"+
		"\5\34\u00db\n\34\3\35\3\35\3\35\7\35\u00e0\n\35\f\35\16\35\u00e3\13\35"+
		"\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\3\37\5\37\u00f6\n\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \5"+
		" \u0104\n \3!\3!\3!\3!\5!\u010a\n!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\5\""+
		"\u0114\n\"\3#\3#\3#\3#\5#\u011a\n#\3$\3$\3$\3$\3$\3$\5$\u0122\n$\3%\3"+
		"%\3%\3%\3%\3%\3%\3%\5%\u012c\n%\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3"+
		"&\3&\5&\u013c\n&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\5\'\u0148\n\'"+
		"\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\5(\u0154\n(\3)\3)\3)\3)\3)\3)\3)\3)\3)"+
		"\3)\3)\3)\3)\3)\5)\u0164\n)\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*"+
		"\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\5*\u0186\n*\3+"+
		"\3+\3+\3+\3+\3+\3+\3+\5+\u0190\n+\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,"+
		"\5,\u019e\n,\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-\5-\u01aa\n-\3.\3.\3.\3.\3."+
		"\3.\3.\3.\5.\u01b4\n.\3/\3/\3/\3/\5/\u01ba\n/\3\60\3\60\3\60\3\60\3\60"+
		"\3\60\5\60\u01c2\n\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\5\61\u01cc"+
		"\n\61\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\5\62"+
		"\u01da\n\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\5\63\u01e4\n\63\3"+
		"\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3"+
		"\64\3\64\5\64\u01f6\n\64\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65"+
		"\3\65\3\65\3\65\3\65\3\65\3\65\3\65\5\65\u0208\n\65\3\66\3\66\3\66\3\66"+
		"\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66"+
		"\3\66\3\66\3\66\3\66\5\66\u0220\n\66\3\67\3\67\7\67\u0224\n\67\f\67\16"+
		"\67\u0227\13\67\4z\u00a1\28\3\3\5\4\7\2\t\2\13\2\r\2\17\2\21\2\23\2\25"+
		"\2\27\2\31\2\33\2\35\2\37\2!\2#\5%\6\'\7)\b+\t-\n/\13\61\f\63\r\65\16"+
		"\67\179\20;\21=\22?\23A\24C\25E\26G\27I\30K\31M\32O\33Q\34S\35U\36W\37"+
		"Y [!]\"_#a$c%e&g\'i(k)m*\3\2\20\5\2\13\f\17\17\"\"\3\2\62;\3\2c|\3\2C"+
		"\\\4\2C\\c|\3\2//\4\2--//\4\2KKkk\4\2$$^^\4\2hhvv\4\2VVvv\4\2HHhh\n\2"+
		"&&,-//\61\61>@C\\aac|\13\2##&&,-//\61;>AC\\aac|\2\u0242\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-"+
		"\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2"+
		"\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2"+
		"E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3"+
		"\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2"+
		"\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2"+
		"k\3\2\2\2\2m\3\2\2\2\3p\3\2\2\2\5v\3\2\2\2\7\u0081\3\2\2\2\t\u0083\3\2"+
		"\2\2\13\u0085\3\2\2\2\r\u0087\3\2\2\2\17\u0089\3\2\2\2\21\u008b\3\2\2"+
		"\2\23\u008e\3\2\2\2\25\u0090\3\2\2\2\27\u0092\3\2\2\2\31\u0094\3\2\2\2"+
		"\33\u0096\3\2\2\2\35\u0098\3\2\2\2\37\u009b\3\2\2\2!\u009f\3\2\2\2#\u00a3"+
		"\3\2\2\2%\u00a5\3\2\2\2\'\u00a7\3\2\2\2)\u00a9\3\2\2\2+\u00ab\3\2\2\2"+
		"-\u00ad\3\2\2\2/\u00af\3\2\2\2\61\u00b1\3\2\2\2\63\u00b3\3\2\2\2\65\u00b5"+
		"\3\2\2\2\67\u00b8\3\2\2\29\u00dc\3\2\2\2;\u00e6\3\2\2\2=\u00ea\3\2\2\2"+
		"?\u0103\3\2\2\2A\u0109\3\2\2\2C\u0113\3\2\2\2E\u0119\3\2\2\2G\u0121\3"+
		"\2\2\2I\u012b\3\2\2\2K\u013b\3\2\2\2M\u0147\3\2\2\2O\u0153\3\2\2\2Q\u0163"+
		"\3\2\2\2S\u0185\3\2\2\2U\u018f\3\2\2\2W\u019d\3\2\2\2Y\u01a9\3\2\2\2["+
		"\u01b3\3\2\2\2]\u01b9\3\2\2\2_\u01c1\3\2\2\2a\u01cb\3\2\2\2c\u01d9\3\2"+
		"\2\2e\u01e3\3\2\2\2g\u01f5\3\2\2\2i\u0207\3\2\2\2k\u021f\3\2\2\2m\u0221"+
		"\3\2\2\2oq\t\2\2\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2\2st\3\2\2\2t"+
		"u\b\2\2\2u\4\3\2\2\2vz\7=\2\2wy\13\2\2\2xw\3\2\2\2y|\3\2\2\2z{\3\2\2\2"+
		"zx\3\2\2\2{}\3\2\2\2|z\3\2\2\2}~\5\27\f\2~\177\3\2\2\2\177\u0080\b\3\2"+
		"\2\u0080\6\3\2\2\2\u0081\u0082\t\3\2\2\u0082\b\3\2\2\2\u0083\u0084\t\4"+
		"\2\2\u0084\n\3\2\2\2\u0085\u0086\t\5\2\2\u0086\f\3\2\2\2\u0087\u0088\t"+
		"\6\2\2\u0088\16\3\2\2\2\u0089\u008a\7a\2\2\u008a\20\3\2\2\2\u008b\u008c"+
		"\7^\2\2\u008c\u008d\13\2\2\2\u008d\22\3\2\2\2\u008e\u008f\7$\2\2\u008f"+
		"\24\3\2\2\2\u0090\u0091\13\2\2\2\u0091\26\3\2\2\2\u0092\u0093\7\f\2\2"+
		"\u0093\30\3\2\2\2\u0094\u0095\7\17\2\2\u0095\32\3\2\2\2\u0096\u0097\7"+
		"\13\2\2\u0097\34\3\2\2\2\u0098\u0099\7^\2\2\u0099\u009a\7\62\2\2\u009a"+
		"\36\3\2\2\2\u009b\u009c\7^\2\2\u009c\u009d\13\2\2\2\u009d \3\2\2\2\u009e"+
		"\u00a0\13\2\2\2\u009f\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\3"+
		"\2\2\2\u00a1\u009f\3\2\2\2\u00a2\"\3\2\2\2\u00a3\u00a4\7*\2\2\u00a4$\3"+
		"\2\2\2\u00a5\u00a6\7+\2\2\u00a6&\3\2\2\2\u00a7\u00a8\7]\2\2\u00a8(\3\2"+
		"\2\2\u00a9\u00aa\7_\2\2\u00aa*\3\2\2\2\u00ab\u00ac\7)\2\2\u00ac,\3\2\2"+
		"\2\u00ad\u00ae\7b\2\2\u00ae.\3\2\2\2\u00af\u00b0\7.\2\2\u00b0\60\3\2\2"+
		"\2\u00b1\u00b2\7%\2\2\u00b2\62\3\2\2\2\u00b3\u00b4\7B\2\2\u00b4\64\3\2"+
		"\2\2\u00b5\u00b6\7\60\2\2\u00b6\66\3\2\2\2\u00b7\u00b9\t\7\2\2\u00b8\u00b7"+
		"\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bb\3\2\2\2\u00ba\u00bc\t\3\2\2\u00bb"+
		"\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2"+
		"\2\2\u00be\u00c6\3\2\2\2\u00bf\u00c3\7\60\2\2\u00c0\u00c2\t\3\2\2\u00c1"+
		"\u00c0\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2"+
		"\2\2\u00c4\u00c7\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6\u00bf\3\2\2\2\u00c6"+
		"\u00c7\3\2\2\2\u00c7\u00da\3\2\2\2\u00c8\u00ca\t\b\2\2\u00c9\u00c8\3\2"+
		"\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cc\3\2\2\2\u00cb\u00cd\t\3\2\2\u00cc"+
		"\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2"+
		"\2\2\u00cf\u00d7\3\2\2\2\u00d0\u00d4\7\60\2\2\u00d1\u00d3\t\3\2\2\u00d2"+
		"\u00d1\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2"+
		"\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7\u00d0\3\2\2\2\u00d7"+
		"\u00d8\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00db\t\t\2\2\u00da\u00c9\3\2"+
		"\2\2\u00da\u00db\3\2\2\2\u00db8\3\2\2\2\u00dc\u00e1\7$\2\2\u00dd\u00e0"+
		"\5\21\t\2\u00de\u00e0\n\n\2\2\u00df\u00dd\3\2\2\2\u00df\u00de\3\2\2\2"+
		"\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e4"+
		"\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00e5\7$\2\2\u00e5:\3\2\2\2\u00e6\u00e7"+
		"\5\61\31\2\u00e7\u00e8\7^\2\2\u00e8\u00e9\5!\21\2\u00e9<\3\2\2\2\u00ea"+
		"\u00f5\5\61\31\2\u00eb\u00f6\t\13\2\2\u00ec\u00ed\t\f\2\2\u00ed\u00ee"+
		"\7t\2\2\u00ee\u00ef\7w\2\2\u00ef\u00f6\7g\2\2\u00f0\u00f1\t\r\2\2\u00f1"+
		"\u00f2\7c\2\2\u00f2\u00f3\7n\2\2\u00f3\u00f4\7u\2\2\u00f4\u00f6\7g\2\2"+
		"\u00f5\u00eb\3\2\2\2\u00f5\u00ec\3\2\2\2\u00f5\u00f0\3\2\2\2\u00f6>\3"+
		"\2\2\2\u00f7\u00f8\7f\2\2\u00f8\u00f9\7g\2\2\u00f9\u00fa\7h\2\2\u00fa"+
		"\u00fb\7k\2\2\u00fb\u00fc\7p\2\2\u00fc\u0104\7g\2\2\u00fd\u00fe\7F\2\2"+
		"\u00fe\u00ff\7G\2\2\u00ff\u0100\7H\2\2\u0100\u0101\7K\2\2\u0101\u0102"+
		"\7P\2\2\u0102\u0104\7G\2\2\u0103\u00f7\3\2\2\2\u0103\u00fd\3\2\2\2\u0104"+
		"@\3\2\2\2\u0105\u0106\7k\2\2\u0106\u010a\7h\2\2\u0107\u0108\7K\2\2\u0108"+
		"\u010a\7H\2\2\u0109\u0105\3\2\2\2\u0109\u0107\3\2\2\2\u010aB\3\2\2\2\u010b"+
		"\u010c\7e\2\2\u010c\u010d\7q\2\2\u010d\u010e\7p\2\2\u010e\u0114\7f\2\2"+
		"\u010f\u0110\7E\2\2\u0110\u0111\7Q\2\2\u0111\u0112\7P\2\2\u0112\u0114"+
		"\7F\2\2\u0113\u010b\3\2\2\2\u0113\u010f\3\2\2\2\u0114D\3\2\2\2\u0115\u0116"+
		"\7q\2\2\u0116\u011a\7t\2\2\u0117\u0118\7Q\2\2\u0118\u011a\7T\2\2\u0119"+
		"\u0115\3\2\2\2\u0119\u0117\3\2\2\2\u011aF\3\2\2\2\u011b\u011c\7c\2\2\u011c"+
		"\u011d\7p\2\2\u011d\u0122\7f\2\2\u011e\u011f\7C\2\2\u011f\u0120\7P\2\2"+
		"\u0120\u0122\7F\2\2\u0121\u011b\3\2\2\2\u0121\u011e\3\2\2\2\u0122H\3\2"+
		"\2\2\u0123\u0124\7g\2\2\u0124\u0125\7n\2\2\u0125\u0126\7u\2\2\u0126\u012c"+
		"\7g\2\2\u0127\u0128\7G\2\2\u0128\u0129\7N\2\2\u0129\u012a\7U\2\2\u012a"+
		"\u012c\7G\2\2\u012b\u0123\3\2\2\2\u012b\u0127\3\2\2\2\u012cJ\3\2\2\2\u012d"+
		"\u012e\7n\2\2\u012e\u012f\7c\2\2\u012f\u0130\7o\2\2\u0130\u0131\7d\2\2"+
		"\u0131\u0132\7f\2\2\u0132\u013c\7c\2\2\u0133\u0134\7N\2\2\u0134\u0135"+
		"\7C\2\2\u0135\u0136\7O\2\2\u0136\u0137\7D\2\2\u0137\u0138\7F\2\2\u0138"+
		"\u013c\7C\2\2\u0139\u013a\7\u00d0\2\2\u013a\u013c\7\u00bd\2\2\u013b\u012d"+
		"\3\2\2\2\u013b\u0133\3\2\2\2\u013b\u0139\3\2\2\2\u013cL\3\2\2\2\u013d"+
		"\u013e\7d\2\2\u013e\u013f\7g\2\2\u013f\u0140\7i\2\2\u0140\u0141\7k\2\2"+
		"\u0141\u0148\7p\2\2\u0142\u0143\7D\2\2\u0143\u0144\7G\2\2\u0144\u0145"+
		"\7I\2\2\u0145\u0146\7K\2\2\u0146\u0148\7P\2\2\u0147\u013d\3\2\2\2\u0147"+
		"\u0142\3\2\2\2\u0148N\3\2\2\2\u0149\u014a\7s\2\2\u014a\u014b\7w\2\2\u014b"+
		"\u014c\7q\2\2\u014c\u014d\7v\2\2\u014d\u0154\7g\2\2\u014e\u014f\7S\2\2"+
		"\u014f\u0150\7W\2\2\u0150\u0151\7Q\2\2\u0151\u0152\7V\2\2\u0152\u0154"+
		"\7G\2\2\u0153\u0149\3\2\2\2\u0153\u014e\3\2\2\2\u0154P\3\2\2\2\u0155\u0156"+
		"\7w\2\2\u0156\u0157\7p\2\2\u0157\u0158\7s\2\2\u0158\u0159\7w\2\2\u0159"+
		"\u015a\7q\2\2\u015a\u015b\7v\2\2\u015b\u0164\7g\2\2\u015c\u015d\7W\2\2"+
		"\u015d\u015e\7P\2\2\u015e\u015f\7S\2\2\u015f\u0160\7W\2\2\u0160\u0161"+
		"\7Q\2\2\u0161\u0162\7V\2\2\u0162\u0164\7G\2\2\u0163\u0155\3\2\2\2\u0163"+
		"\u015c\3\2\2\2\u0164R\3\2\2\2\u0165\u0166\7w\2\2\u0166\u0167\7p\2\2\u0167"+
		"\u0168\7s\2\2\u0168\u0169\7w\2\2\u0169\u016a\7q\2\2\u016a\u016b\7v\2\2"+
		"\u016b\u016c\7g\2\2\u016c\u016d\7/\2\2\u016d\u016e\7u\2\2\u016e\u016f"+
		"\7r\2\2\u016f\u0170\7n\2\2\u0170\u0171\7k\2\2\u0171\u0172\7e\2\2\u0172"+
		"\u0173\7k\2\2\u0173\u0174\7p\2\2\u0174\u0186\7i\2\2\u0175\u0176\7W\2\2"+
		"\u0176\u0177\7P\2\2\u0177\u0178\7S\2\2\u0178\u0179\7W\2\2\u0179\u017a"+
		"\7Q\2\2\u017a\u017b\7V\2\2\u017b\u017c\7G\2\2\u017c\u017d\7/\2\2\u017d"+
		"\u017e\7U\2\2\u017e\u017f\7R\2\2\u017f\u0180\7N\2\2\u0180\u0181\7K\2\2"+
		"\u0181\u0182\7E\2\2\u0182\u0183\7K\2\2\u0183\u0184\7P\2\2\u0184\u0186"+
		"\7I\2\2\u0185\u0165\3\2\2\2\u0185\u0175\3\2\2\2\u0186T\3\2\2\2\u0187\u0188"+
		"\7y\2\2\u0188\u0189\7j\2\2\u0189\u018a\7g\2\2\u018a\u0190\7p\2\2\u018b"+
		"\u018c\7Y\2\2\u018c\u018d\7J\2\2\u018d\u018e\7G\2\2\u018e\u0190\7P\2\2"+
		"\u018f\u0187\3\2\2\2\u018f\u018b\3\2\2\2\u0190V\3\2\2\2\u0191\u0192\7"+
		"w\2\2\u0192\u0193\7p\2\2\u0193\u0194\7n\2\2\u0194\u0195\7g\2\2\u0195\u0196"+
		"\7u\2\2\u0196\u019e\7u\2\2\u0197\u0198\7W\2\2\u0198\u0199\7P\2\2\u0199"+
		"\u019a\7N\2\2\u019a\u019b\7G\2\2\u019b\u019c\7U\2\2\u019c\u019e\7U\2\2"+
		"\u019d\u0191\3\2\2\2\u019d\u0197\3\2\2\2\u019eX\3\2\2\2\u019f\u01a0\7"+
		"c\2\2\u01a0\u01a1\7r\2\2\u01a1\u01a2\7r\2\2\u01a2\u01a3\7n\2\2\u01a3\u01aa"+
		"\7{\2\2\u01a4\u01a5\7C\2\2\u01a5\u01a6\7R\2\2\u01a6\u01a7\7R\2\2\u01a7"+
		"\u01a8\7N\2\2\u01a8\u01aa\7[\2\2\u01a9\u019f\3\2\2\2\u01a9\u01a4\3\2\2"+
		"\2\u01aaZ\3\2\2\2\u01ab\u01ac\7g\2\2\u01ac\u01ad\7x\2\2\u01ad\u01ae\7"+
		"c\2\2\u01ae\u01b4\7n\2\2\u01af\u01b0\7G\2\2\u01b0\u01b1\7X\2\2\u01b1\u01b2"+
		"\7C\2\2\u01b2\u01b4\7N\2\2\u01b3\u01ab\3\2\2\2\u01b3\u01af\3\2\2\2\u01b4"+
		"\\\3\2\2\2\u01b5\u01b6\7f\2\2\u01b6\u01ba\7q\2\2\u01b7\u01b8\7F\2\2\u01b8"+
		"\u01ba\7Q\2\2\u01b9\u01b5\3\2\2\2\u01b9\u01b7\3\2\2\2\u01ba^\3\2\2\2\u01bb"+
		"\u01bc\7n\2\2\u01bc\u01bd\7g\2\2\u01bd\u01c2\7v\2\2\u01be\u01bf\7N\2\2"+
		"\u01bf\u01c0\7G\2\2\u01c0\u01c2\7V\2\2\u01c1\u01bb\3\2\2\2\u01c1\u01be"+
		"\3\2\2\2\u01c2`\3\2\2\2\u01c3\u01c4\7n\2\2\u01c4\u01c5\7g\2\2\u01c5\u01c6"+
		"\7v\2\2\u01c6\u01cc\7,\2\2\u01c7\u01c8\7N\2\2\u01c8\u01c9\7G\2\2\u01c9"+
		"\u01ca\7V\2\2\u01ca\u01cc\7,\2\2\u01cb\u01c3\3\2\2\2\u01cb\u01c7\3\2\2"+
		"\2\u01ccb\3\2\2\2\u01cd\u01ce\7n\2\2\u01ce\u01cf\7g\2\2\u01cf\u01d0\7"+
		"v\2\2\u01d0\u01d1\7t\2\2\u01d1\u01d2\7g\2\2\u01d2\u01da\7e\2\2\u01d3\u01d4"+
		"\7N\2\2\u01d4\u01d5\7G\2\2\u01d5\u01d6\7V\2\2\u01d6\u01d7\7T\2\2\u01d7"+
		"\u01d8\7G\2\2\u01d8\u01da\7E\2\2\u01d9\u01cd\3\2\2\2\u01d9\u01d3\3\2\2"+
		"\2\u01dad\3\2\2\2\u01db\u01dc\7u\2\2\u01dc\u01dd\7g\2\2\u01dd\u01de\7"+
		"v\2\2\u01de\u01e4\7#\2\2\u01df\u01e0\7U\2\2\u01e0\u01e1\7G\2\2\u01e1\u01e2"+
		"\7V\2\2\u01e2\u01e4\7#\2\2\u01e3\u01db\3\2\2\2\u01e3\u01df\3\2\2\2\u01e4"+
		"f\3\2\2\2\u01e5\u01e6\7u\2\2\u01e6\u01e7\7g\2\2\u01e7\u01e8\7v\2\2\u01e8"+
		"\u01e9\7/\2\2\u01e9\u01ea\7e\2\2\u01ea\u01eb\7c\2\2\u01eb\u01ec\7t\2\2"+
		"\u01ec\u01f6\7#\2\2\u01ed\u01ee\7U\2\2\u01ee\u01ef\7G\2\2\u01ef\u01f0"+
		"\7V\2\2\u01f0\u01f1\7/\2\2\u01f1\u01f2\7E\2\2\u01f2\u01f3\7C\2\2\u01f3"+
		"\u01f4\7T\2\2\u01f4\u01f6\7#\2\2\u01f5\u01e5\3\2\2\2\u01f5\u01ed\3\2\2"+
		"\2\u01f6h\3\2\2\2\u01f7\u01f8\7u\2\2\u01f8\u01f9\7g\2\2\u01f9\u01fa\7"+
		"v\2\2\u01fa\u01fb\7/\2\2\u01fb\u01fc\7e\2\2\u01fc\u01fd\7f\2\2\u01fd\u01fe"+
		"\7t\2\2\u01fe\u0208\7#\2\2\u01ff\u0200\7U\2\2\u0200\u0201\7G\2\2\u0201"+
		"\u0202\7V\2\2\u0202\u0203\7/\2\2\u0203\u0204\7E\2\2\u0204\u0205\7F\2\2"+
		"\u0205\u0206\7T\2\2\u0206\u0208\7#\2\2\u0207\u01f7\3\2\2\2\u0207\u01ff"+
		"\3\2\2\2\u0208j\3\2\2\2\u0209\u020a\7x\2\2\u020a\u020b\7g\2\2\u020b\u020c"+
		"\7e\2\2\u020c\u020d\7v\2\2\u020d\u020e\7q\2\2\u020e\u020f\7t\2\2\u020f"+
		"\u0210\7/\2\2\u0210\u0211\7u\2\2\u0211\u0212\7g\2\2\u0212\u0213\7v\2\2"+
		"\u0213\u0220\7#\2\2\u0214\u0215\7X\2\2\u0215\u0216\7G\2\2\u0216\u0217"+
		"\7E\2\2\u0217\u0218\7V\2\2\u0218\u0219\7Q\2\2\u0219\u021a\7T\2\2\u021a"+
		"\u021b\7/\2\2\u021b\u021c\7U\2\2\u021c\u021d\7G\2\2\u021d\u021e\7V\2\2"+
		"\u021e\u0220\7#\2\2\u021f\u0209\3\2\2\2\u021f\u0214\3\2\2\2\u0220l\3\2"+
		"\2\2\u0221\u0225\t\16\2\2\u0222\u0224\t\17\2\2\u0223\u0222\3\2\2\2\u0224"+
		"\u0227\3\2\2\2\u0225\u0223\3\2\2\2\u0225\u0226\3\2\2\2\u0226n\3\2\2\2"+
		"\u0227\u0225\3\2\2\2*\2rz\u00a1\u00b8\u00bd\u00c3\u00c6\u00c9\u00ce\u00d4"+
		"\u00d7\u00da\u00df\u00e1\u00f5\u0103\u0109\u0113\u0119\u0121\u012b\u013b"+
		"\u0147\u0153\u0163\u0185\u018f\u019d\u01a9\u01b3\u01b9\u01c1\u01cb\u01d9"+
		"\u01e3\u01f5\u0207\u021f\u0225\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}