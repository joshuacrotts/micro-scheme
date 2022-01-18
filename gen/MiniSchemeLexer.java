// Generated from C:/Users/Joshua/Desktop/Files/Java/MiniScheme/src/main/antlr4/com/joshuacrotts/minischeme\MiniScheme.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniSchemeLexer extends Lexer {
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
			"OPEN_PAREN", "CLOSE_PAREN", "OPEN_BRACKET", "CLOSE_BRACKET", "PLUS", 
			"MINUS", "STAR", "SLASH", "AMPERSAND", "PIPE", "CARAT", "PERCENT", "EXPONENTIATION", 
			"SINGLE_QUOTE", "HASH", "LOGICAL_NOT", "LOGICAL_AND", "LOGICAL_OR", "LOGICAL_EQ", 
			"LOGICAL_LE", "LOGICAL_GE", "LOGICAL_LT", "LOGICAL_GT", "BITWISE_SHL", 
			"BITWISE_SHR", "BITWISE_NEG", "NUMBERLIT", "STRINGLIT", "CHARLIT", "BOOLLIT", 
			"DEFINE", "IF", "COND", "DO", "ELSE", "LET", "LETSTAR", "LETREC", "LAMBDA", 
			"BEGIN", "QUOTE", "SIN", "COS", "TAN", "ASIN", "ACOS", "ATAN", "SINH", 
			"COSH", "TANH", "SQRT", "ROUND", "FLOOR", "CEILING", "TRUNCATE", "REMAINDER", 
			"MODULO", "TODEG_FN", "TORAD_FN", "CREATE_LIST_FN", "CAR", "CDR", "CONS", 
			"CREATE_VECTOR_FN", "VECTOR_REF_FN", "VECTORLEN_FN", "DISPLAY", "STRAPPEND_FN", 
			"STRLEN_FN", "STREQ_FN", "STRLE_FN", "STRGE_FN", "STRLT_FN", "STRGT_FN", 
			"STRSUBSTR", "NUMBER_FN", "BOOL_FN", "CHAR_FN", "STRING_FN", "LIST_FN", 
			"VECTOR_FN", "NULL_FN", "SYMBOL_FN", "PAIR_FN", "EQ_FN", "EQUAL_FN", 
			"RANDINT_FN", "RANDDOUBLE_FN", "RAND_FN", "READLINE_FN", "READNUMBER_FN", 
			"STRTONUM_FN", "NUMTOSTR_FN", "STRTOLIST_FN", "LISTTOSTR_FN", "SETVAR_FN", 
			"SETCAR_FN", "SETCDR_FN", "SETVEC_FN", "ID"
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


	public MiniSchemeLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MiniScheme.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2h\u03bc\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\4k\t"+
		"k\4l\tl\4m\tm\4n\tn\4o\to\4p\tp\4q\tq\4r\tr\4s\ts\4t\tt\4u\tu\3\2\6\2"+
		"\u00ed\n\2\r\2\16\2\u00ee\3\2\3\2\3\3\3\3\7\3\u00f5\n\3\f\3\16\3\u00f8"+
		"\13\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t"+
		"\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3"+
		"\20\3\20\3\21\6\21\u011c\n\21\r\21\16\21\u011d\3\22\3\22\3\23\3\23\3\24"+
		"\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33"+
		"\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3!\3!\3"+
		"\"\3\"\3\"\3\"\3#\3#\3#\3$\3$\3%\3%\3%\3&\3&\3&\3\'\3\'\3(\3(\3)\3)\3"+
		")\3*\3*\3*\3+\3+\3,\5,\u015f\n,\3,\6,\u0162\n,\r,\16,\u0163\3,\3,\7,\u0168"+
		"\n,\f,\16,\u016b\13,\5,\u016d\n,\3-\3-\3-\7-\u0172\n-\f-\16-\u0175\13"+
		"-\3-\3-\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\5/\u0188\n/\3\60"+
		"\3\60\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62"+
		"\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\66\3\66"+
		"\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67\3\67\38\38\38\38\38\38\3"+
		"8\38\58\u01b9\n8\39\39\39\39\39\39\3:\3:\3:\3:\3:\3:\3;\3;\3;\3;\3<\3"+
		"<\3<\3<\3=\3=\3=\3=\3>\3>\3>\3>\3>\3?\3?\3?\3?\3?\3@\3@\3@\3@\3@\3A\3"+
		"A\3A\3A\3A\3B\3B\3B\3B\3B\3C\3C\3C\3C\3C\3D\3D\3D\3D\3D\3E\3E\3E\3E\3"+
		"E\3E\3F\3F\3F\3F\3F\3F\3G\3G\3G\3G\3G\3G\3G\3G\3H\3H\3H\3H\3H\3H\3H\3"+
		"H\3H\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3J\3J\3J\3J\3J\3J\3J\3K\3K\3K\3K\3"+
		"K\3K\3K\3K\3K\3K\3K\3K\3K\3K\3K\3K\3K\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3"+
		"L\3L\3L\3L\3L\3L\3L\3M\3M\3M\3M\3M\3N\3N\3N\3N\3O\3O\3O\3O\3P\3P\3P\3"+
		"P\3P\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3R\3R\3R\3R\3R\3R\3R\3R\3R\3R\3R\3S\3S\3S\3"+
		"S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3S\3T\3T\3T\3T\3T\3T\3T\3T\3U\3U\3U\3U\3"+
		"U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3"+
		"V\3W\3W\3W\3W\3W\3W\3W\3W\3W\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3Y\3Y\3Y\3"+
		"Y\3Y\3Y\3Y\3Y\3Y\3Y\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3[\3[\3[\3[\3[\3[\3[\3"+
		"[\3[\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3]\3]\3]\3]\3]\3]\3]\3]\3"+
		"^\3^\3^\3^\3^\3^\3_\3_\3_\3_\3_\3_\3`\3`\3`\3`\3`\3`\3`\3`\3a\3a\3a\3"+
		"a\3a\3a\3b\3b\3b\3b\3b\3b\3b\3b\3c\3c\3c\3c\3c\3c\3d\3d\3d\3d\3d\3d\3"+
		"d\3d\3e\3e\3e\3e\3e\3e\3f\3f\3f\3f\3g\3g\3g\3g\3g\3g\3g\3h\3h\3h\3h\3"+
		"h\3h\3h\3h\3h\3h\3h\3h\3h\3h\3h\3i\3i\3i\3i\3i\3i\3i\3i\3i\3i\3i\3i\3"+
		"i\3i\3j\3j\3j\3j\3j\3j\3j\3k\3k\3k\3k\3k\3k\3k\3k\3k\3k\3l\3l\3l\3l\3"+
		"l\3l\3l\3l\3l\3l\3l\3l\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3"+
		"n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3o\3o\3o\3o\3o\3o\3o\3o\3"+
		"o\3o\3o\3o\3o\3p\3p\3p\3p\3p\3p\3p\3p\3p\3p\3p\3p\3p\3q\3q\3q\3q\3q\3"+
		"r\3r\3r\3r\3r\3r\3r\3r\3r\3s\3s\3s\3s\3s\3s\3s\3s\3s\3t\3t\3t\3t\3t\3"+
		"t\3t\3t\3t\3t\3t\3t\3u\3u\7u\u03b5\nu\fu\16u\u03b8\13u\3u\5u\u03bb\nu"+
		"\4\u00f6\u011d\2v\3\3\5\4\7\2\t\2\13\2\r\2\17\2\21\2\23\2\25\2\27\2\31"+
		"\2\33\2\35\2\37\2!\2#\5%\6\'\7)\b+\t-\n/\13\61\f\63\r\65\16\67\179\20"+
		";\21=\22?\23A\24C\25E\26G\27I\30K\31M\32O\33Q\34S\35U\36W\37Y [!]\"_#"+
		"a$c%e&g\'i(k)m*o+q,s-u.w/y\60{\61}\62\177\63\u0081\64\u0083\65\u0085\66"+
		"\u0087\67\u00898\u008b9\u008d:\u008f;\u0091<\u0093=\u0095>\u0097?\u0099"+
		"@\u009bA\u009dB\u009fC\u00a1D\u00a3E\u00a5F\u00a7G\u00a9H\u00abI\u00ad"+
		"J\u00afK\u00b1L\u00b3M\u00b5N\u00b7O\u00b9P\u00bbQ\u00bdR\u00bfS\u00c1"+
		"T\u00c3U\u00c5V\u00c7W\u00c9X\u00cbY\u00cdZ\u00cf[\u00d1\\\u00d3]\u00d5"+
		"^\u00d7_\u00d9`\u00dba\u00ddb\u00dfc\u00e1d\u00e3e\u00e5f\u00e7g\u00e9"+
		"h\3\2\17\5\2\13\f\17\17\"\"\3\2\62;\3\2c|\3\2C\\\4\2C\\c|\4\2--//\4\2"+
		"$$^^\4\2hhvv\4\2VVvv\4\2HHhh\6\2??C\\aac|\b\2//\62;>@C\\aac|\5\2##??A"+
		"A\2\u03bb\2\3\3\2\2\2\2\5\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2"+
		")\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2"+
		"\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2"+
		"A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3"+
		"\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2"+
		"\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2"+
		"g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3"+
		"\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3"+
		"\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2"+
		"\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091"+
		"\3\2\2\2\2\u0093\3\2\2\2\2\u0095\3\2\2\2\2\u0097\3\2\2\2\2\u0099\3\2\2"+
		"\2\2\u009b\3\2\2\2\2\u009d\3\2\2\2\2\u009f\3\2\2\2\2\u00a1\3\2\2\2\2\u00a3"+
		"\3\2\2\2\2\u00a5\3\2\2\2\2\u00a7\3\2\2\2\2\u00a9\3\2\2\2\2\u00ab\3\2\2"+
		"\2\2\u00ad\3\2\2\2\2\u00af\3\2\2\2\2\u00b1\3\2\2\2\2\u00b3\3\2\2\2\2\u00b5"+
		"\3\2\2\2\2\u00b7\3\2\2\2\2\u00b9\3\2\2\2\2\u00bb\3\2\2\2\2\u00bd\3\2\2"+
		"\2\2\u00bf\3\2\2\2\2\u00c1\3\2\2\2\2\u00c3\3\2\2\2\2\u00c5\3\2\2\2\2\u00c7"+
		"\3\2\2\2\2\u00c9\3\2\2\2\2\u00cb\3\2\2\2\2\u00cd\3\2\2\2\2\u00cf\3\2\2"+
		"\2\2\u00d1\3\2\2\2\2\u00d3\3\2\2\2\2\u00d5\3\2\2\2\2\u00d7\3\2\2\2\2\u00d9"+
		"\3\2\2\2\2\u00db\3\2\2\2\2\u00dd\3\2\2\2\2\u00df\3\2\2\2\2\u00e1\3\2\2"+
		"\2\2\u00e3\3\2\2\2\2\u00e5\3\2\2\2\2\u00e7\3\2\2\2\2\u00e9\3\2\2\2\3\u00ec"+
		"\3\2\2\2\5\u00f2\3\2\2\2\7\u00fd\3\2\2\2\t\u00ff\3\2\2\2\13\u0101\3\2"+
		"\2\2\r\u0103\3\2\2\2\17\u0105\3\2\2\2\21\u0107\3\2\2\2\23\u010a\3\2\2"+
		"\2\25\u010c\3\2\2\2\27\u010e\3\2\2\2\31\u0110\3\2\2\2\33\u0112\3\2\2\2"+
		"\35\u0114\3\2\2\2\37\u0117\3\2\2\2!\u011b\3\2\2\2#\u011f\3\2\2\2%\u0121"+
		"\3\2\2\2\'\u0123\3\2\2\2)\u0125\3\2\2\2+\u0127\3\2\2\2-\u0129\3\2\2\2"+
		"/\u012b\3\2\2\2\61\u012d\3\2\2\2\63\u012f\3\2\2\2\65\u0131\3\2\2\2\67"+
		"\u0133\3\2\2\29\u0135\3\2\2\2;\u0137\3\2\2\2=\u013a\3\2\2\2?\u013c\3\2"+
		"\2\2A\u013e\3\2\2\2C\u0142\3\2\2\2E\u0146\3\2\2\2G\u0149\3\2\2\2I\u014b"+
		"\3\2\2\2K\u014e\3\2\2\2M\u0151\3\2\2\2O\u0153\3\2\2\2Q\u0155\3\2\2\2S"+
		"\u0158\3\2\2\2U\u015b\3\2\2\2W\u015e\3\2\2\2Y\u016e\3\2\2\2[\u0178\3\2"+
		"\2\2]\u017c\3\2\2\2_\u0189\3\2\2\2a\u0190\3\2\2\2c\u0193\3\2\2\2e\u0198"+
		"\3\2\2\2g\u019b\3\2\2\2i\u01a0\3\2\2\2k\u01a4\3\2\2\2m\u01a9\3\2\2\2o"+
		"\u01b8\3\2\2\2q\u01ba\3\2\2\2s\u01c0\3\2\2\2u\u01c6\3\2\2\2w\u01ca\3\2"+
		"\2\2y\u01ce\3\2\2\2{\u01d2\3\2\2\2}\u01d7\3\2\2\2\177\u01dc\3\2\2\2\u0081"+
		"\u01e1\3\2\2\2\u0083\u01e6\3\2\2\2\u0085\u01eb\3\2\2\2\u0087\u01f0\3\2"+
		"\2\2\u0089\u01f5\3\2\2\2\u008b\u01fb\3\2\2\2\u008d\u0201\3\2\2\2\u008f"+
		"\u0209\3\2\2\2\u0091\u0212\3\2\2\2\u0093\u021c\3\2\2\2\u0095\u0223\3\2"+
		"\2\2\u0097\u0234\3\2\2\2\u0099\u0245\3\2\2\2\u009b\u024a\3\2\2\2\u009d"+
		"\u024e\3\2\2\2\u009f\u0252\3\2\2\2\u00a1\u0257\3\2\2\2\u00a3\u025e\3\2"+
		"\2\2\u00a5\u0269\3\2\2\2\u00a7\u0277\3\2\2\2\u00a9\u027f\3\2\2\2\u00ab"+
		"\u028d\3\2\2\2\u00ad\u029b\3\2\2\2\u00af\u02a4\3\2\2\2\u00b1\u02ae\3\2"+
		"\2\2\u00b3\u02b8\3\2\2\2\u00b5\u02c1\3\2\2\2\u00b7\u02ca\3\2\2\2\u00b9"+
		"\u02d4\3\2\2\2\u00bb\u02dc\3\2\2\2\u00bd\u02e2\3\2\2\2\u00bf\u02e8\3\2"+
		"\2\2\u00c1\u02f0\3\2\2\2\u00c3\u02f6\3\2\2\2\u00c5\u02fe\3\2\2\2\u00c7"+
		"\u0304\3\2\2\2\u00c9\u030c\3\2\2\2\u00cb\u0312\3\2\2\2\u00cd\u0316\3\2"+
		"\2\2\u00cf\u031d\3\2\2\2\u00d1\u032c\3\2\2\2\u00d3\u033a\3\2\2\2\u00d5"+
		"\u0341\3\2\2\2\u00d7\u034b\3\2\2\2\u00d9\u0357\3\2\2\2\u00db\u0366\3\2"+
		"\2\2\u00dd\u0375\3\2\2\2\u00df\u0382\3\2\2\2\u00e1\u038f\3\2\2\2\u00e3"+
		"\u0394\3\2\2\2\u00e5\u039d\3\2\2\2\u00e7\u03a6\3\2\2\2\u00e9\u03b2\3\2"+
		"\2\2\u00eb\u00ed\t\2\2\2\u00ec\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee"+
		"\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f1\b\2"+
		"\2\2\u00f1\4\3\2\2\2\u00f2\u00f6\7=\2\2\u00f3\u00f5\13\2\2\2\u00f4\u00f3"+
		"\3\2\2\2\u00f5\u00f8\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f7"+
		"\u00f9\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f9\u00fa\5\27\f\2\u00fa\u00fb\3"+
		"\2\2\2\u00fb\u00fc\b\3\2\2\u00fc\6\3\2\2\2\u00fd\u00fe\t\3\2\2\u00fe\b"+
		"\3\2\2\2\u00ff\u0100\t\4\2\2\u0100\n\3\2\2\2\u0101\u0102\t\5\2\2\u0102"+
		"\f\3\2\2\2\u0103\u0104\t\6\2\2\u0104\16\3\2\2\2\u0105\u0106\7a\2\2\u0106"+
		"\20\3\2\2\2\u0107\u0108\7^\2\2\u0108\u0109\13\2\2\2\u0109\22\3\2\2\2\u010a"+
		"\u010b\7$\2\2\u010b\24\3\2\2\2\u010c\u010d\13\2\2\2\u010d\26\3\2\2\2\u010e"+
		"\u010f\7\f\2\2\u010f\30\3\2\2\2\u0110\u0111\7\17\2\2\u0111\32\3\2\2\2"+
		"\u0112\u0113\7\13\2\2\u0113\34\3\2\2\2\u0114\u0115\7^\2\2\u0115\u0116"+
		"\7\62\2\2\u0116\36\3\2\2\2\u0117\u0118\7^\2\2\u0118\u0119\13\2\2\2\u0119"+
		" \3\2\2\2\u011a\u011c\13\2\2\2\u011b\u011a\3\2\2\2\u011c\u011d\3\2\2\2"+
		"\u011d\u011e\3\2\2\2\u011d\u011b\3\2\2\2\u011e\"\3\2\2\2\u011f\u0120\7"+
		"*\2\2\u0120$\3\2\2\2\u0121\u0122\7+\2\2\u0122&\3\2\2\2\u0123\u0124\7]"+
		"\2\2\u0124(\3\2\2\2\u0125\u0126\7_\2\2\u0126*\3\2\2\2\u0127\u0128\7-\2"+
		"\2\u0128,\3\2\2\2\u0129\u012a\7/\2\2\u012a.\3\2\2\2\u012b\u012c\7,\2\2"+
		"\u012c\60\3\2\2\2\u012d\u012e\7\61\2\2\u012e\62\3\2\2\2\u012f\u0130\7"+
		"(\2\2\u0130\64\3\2\2\2\u0131\u0132\7~\2\2\u0132\66\3\2\2\2\u0133\u0134"+
		"\7`\2\2\u01348\3\2\2\2\u0135\u0136\7\'\2\2\u0136:\3\2\2\2\u0137\u0138"+
		"\7,\2\2\u0138\u0139\7,\2\2\u0139<\3\2\2\2\u013a\u013b\7)\2\2\u013b>\3"+
		"\2\2\2\u013c\u013d\7%\2\2\u013d@\3\2\2\2\u013e\u013f\7p\2\2\u013f\u0140"+
		"\7q\2\2\u0140\u0141\7v\2\2\u0141B\3\2\2\2\u0142\u0143\7c\2\2\u0143\u0144"+
		"\7p\2\2\u0144\u0145\7f\2\2\u0145D\3\2\2\2\u0146\u0147\7q\2\2\u0147\u0148"+
		"\7t\2\2\u0148F\3\2\2\2\u0149\u014a\7?\2\2\u014aH\3\2\2\2\u014b\u014c\7"+
		">\2\2\u014c\u014d\7?\2\2\u014dJ\3\2\2\2\u014e\u014f\7@\2\2\u014f\u0150"+
		"\7?\2\2\u0150L\3\2\2\2\u0151\u0152\7>\2\2\u0152N\3\2\2\2\u0153\u0154\7"+
		"@\2\2\u0154P\3\2\2\2\u0155\u0156\7>\2\2\u0156\u0157\7>\2\2\u0157R\3\2"+
		"\2\2\u0158\u0159\7@\2\2\u0159\u015a\7@\2\2\u015aT\3\2\2\2\u015b\u015c"+
		"\7\u0080\2\2\u015cV\3\2\2\2\u015d\u015f\t\7\2\2\u015e\u015d\3\2\2\2\u015e"+
		"\u015f\3\2\2\2\u015f\u0161\3\2\2\2\u0160\u0162\t\3\2\2\u0161\u0160\3\2"+
		"\2\2\u0162\u0163\3\2\2\2\u0163\u0161\3\2\2\2\u0163\u0164\3\2\2\2\u0164"+
		"\u016c\3\2\2\2\u0165\u0169\7\60\2\2\u0166\u0168\t\3\2\2\u0167\u0166\3"+
		"\2\2\2\u0168\u016b\3\2\2\2\u0169\u0167\3\2\2\2\u0169\u016a\3\2\2\2\u016a"+
		"\u016d\3\2\2\2\u016b\u0169\3\2\2\2\u016c\u0165\3\2\2\2\u016c\u016d\3\2"+
		"\2\2\u016dX\3\2\2\2\u016e\u0173\7$\2\2\u016f\u0172\5\21\t\2\u0170\u0172"+
		"\n\b\2\2\u0171\u016f\3\2\2\2\u0171\u0170\3\2\2\2\u0172\u0175\3\2\2\2\u0173"+
		"\u0171\3\2\2\2\u0173\u0174\3\2\2\2\u0174\u0176\3\2\2\2\u0175\u0173\3\2"+
		"\2\2\u0176\u0177\7$\2\2\u0177Z\3\2\2\2\u0178\u0179\5? \2\u0179\u017a\7"+
		"^\2\2\u017a\u017b\5!\21\2\u017b\\\3\2\2\2\u017c\u0187\5? \2\u017d\u0188"+
		"\t\t\2\2\u017e\u017f\t\n\2\2\u017f\u0180\7t\2\2\u0180\u0181\7w\2\2\u0181"+
		"\u0188\7g\2\2\u0182\u0183\t\13\2\2\u0183\u0184\7c\2\2\u0184\u0185\7n\2"+
		"\2\u0185\u0186\7u\2\2\u0186\u0188\7g\2\2\u0187\u017d\3\2\2\2\u0187\u017e"+
		"\3\2\2\2\u0187\u0182\3\2\2\2\u0188^\3\2\2\2\u0189\u018a\7f\2\2\u018a\u018b"+
		"\7g\2\2\u018b\u018c\7h\2\2\u018c\u018d\7k\2\2\u018d\u018e\7p\2\2\u018e"+
		"\u018f\7g\2\2\u018f`\3\2\2\2\u0190\u0191\7k\2\2\u0191\u0192\7h\2\2\u0192"+
		"b\3\2\2\2\u0193\u0194\7e\2\2\u0194\u0195\7q\2\2\u0195\u0196\7p\2\2\u0196"+
		"\u0197\7f\2\2\u0197d\3\2\2\2\u0198\u0199\7f\2\2\u0199\u019a\7q\2\2\u019a"+
		"f\3\2\2\2\u019b\u019c\7g\2\2\u019c\u019d\7n\2\2\u019d\u019e\7u\2\2\u019e"+
		"\u019f\7g\2\2\u019fh\3\2\2\2\u01a0\u01a1\7n\2\2\u01a1\u01a2\7g\2\2\u01a2"+
		"\u01a3\7v\2\2\u01a3j\3\2\2\2\u01a4\u01a5\7n\2\2\u01a5\u01a6\7g\2\2\u01a6"+
		"\u01a7\7v\2\2\u01a7\u01a8\7,\2\2\u01a8l\3\2\2\2\u01a9\u01aa\7n\2\2\u01aa"+
		"\u01ab\7g\2\2\u01ab\u01ac\7v\2\2\u01ac\u01ad\7t\2\2\u01ad\u01ae\7g\2\2"+
		"\u01ae\u01af\7e\2\2\u01afn\3\2\2\2\u01b0\u01b1\7n\2\2\u01b1\u01b2\7c\2"+
		"\2\u01b2\u01b3\7o\2\2\u01b3\u01b4\7d\2\2\u01b4\u01b5\7f\2\2\u01b5\u01b9"+
		"\7c\2\2\u01b6\u01b7\7\u00d0\2\2\u01b7\u01b9\7\u00bd\2\2\u01b8\u01b0\3"+
		"\2\2\2\u01b8\u01b6\3\2\2\2\u01b9p\3\2\2\2\u01ba\u01bb\7d\2\2\u01bb\u01bc"+
		"\7g\2\2\u01bc\u01bd\7i\2\2\u01bd\u01be\7k\2\2\u01be\u01bf\7p\2\2\u01bf"+
		"r\3\2\2\2\u01c0\u01c1\7s\2\2\u01c1\u01c2\7w\2\2\u01c2\u01c3\7q\2\2\u01c3"+
		"\u01c4\7v\2\2\u01c4\u01c5\7g\2\2\u01c5t\3\2\2\2\u01c6\u01c7\7u\2\2\u01c7"+
		"\u01c8\7k\2\2\u01c8\u01c9\7p\2\2\u01c9v\3\2\2\2\u01ca\u01cb\7e\2\2\u01cb"+
		"\u01cc\7q\2\2\u01cc\u01cd\7u\2\2\u01cdx\3\2\2\2\u01ce\u01cf\7v\2\2\u01cf"+
		"\u01d0\7c\2\2\u01d0\u01d1\7p\2\2\u01d1z\3\2\2\2\u01d2\u01d3\7c\2\2\u01d3"+
		"\u01d4\7u\2\2\u01d4\u01d5\7k\2\2\u01d5\u01d6\7p\2\2\u01d6|\3\2\2\2\u01d7"+
		"\u01d8\7c\2\2\u01d8\u01d9\7e\2\2\u01d9\u01da\7q\2\2\u01da\u01db\7u\2\2"+
		"\u01db~\3\2\2\2\u01dc\u01dd\7c\2\2\u01dd\u01de\7v\2\2\u01de\u01df\7c\2"+
		"\2\u01df\u01e0\7p\2\2\u01e0\u0080\3\2\2\2\u01e1\u01e2\7u\2\2\u01e2\u01e3"+
		"\7k\2\2\u01e3\u01e4\7p\2\2\u01e4\u01e5\7j\2\2\u01e5\u0082\3\2\2\2\u01e6"+
		"\u01e7\7e\2\2\u01e7\u01e8\7q\2\2\u01e8\u01e9\7u\2\2\u01e9\u01ea\7j\2\2"+
		"\u01ea\u0084\3\2\2\2\u01eb\u01ec\7v\2\2\u01ec\u01ed\7c\2\2\u01ed\u01ee"+
		"\7p\2\2\u01ee\u01ef\7j\2\2\u01ef\u0086\3\2\2\2\u01f0\u01f1\7u\2\2\u01f1"+
		"\u01f2\7s\2\2\u01f2\u01f3\7t\2\2\u01f3\u01f4\7v\2\2\u01f4\u0088\3\2\2"+
		"\2\u01f5\u01f6\7t\2\2\u01f6\u01f7\7q\2\2\u01f7\u01f8\7w\2\2\u01f8\u01f9"+
		"\7p\2\2\u01f9\u01fa\7f\2\2\u01fa\u008a\3\2\2\2\u01fb\u01fc\7h\2\2\u01fc"+
		"\u01fd\7n\2\2\u01fd\u01fe\7q\2\2\u01fe\u01ff\7q\2\2\u01ff\u0200\7t\2\2"+
		"\u0200\u008c\3\2\2\2\u0201\u0202\7e\2\2\u0202\u0203\7g\2\2\u0203\u0204"+
		"\7k\2\2\u0204\u0205\7n\2\2\u0205\u0206\7k\2\2\u0206\u0207\7p\2\2\u0207"+
		"\u0208\7i\2\2\u0208\u008e\3\2\2\2\u0209\u020a\7v\2\2\u020a\u020b\7t\2"+
		"\2\u020b\u020c\7w\2\2\u020c\u020d\7p\2\2\u020d\u020e\7e\2\2\u020e\u020f"+
		"\7c\2\2\u020f\u0210\7v\2\2\u0210\u0211\7g\2\2\u0211\u0090\3\2\2\2\u0212"+
		"\u0213\7t\2\2\u0213\u0214\7g\2\2\u0214\u0215\7o\2\2\u0215\u0216\7c\2\2"+
		"\u0216\u0217\7k\2\2\u0217\u0218\7p\2\2\u0218\u0219\7f\2\2\u0219\u021a"+
		"\7g\2\2\u021a\u021b\7t\2\2\u021b\u0092\3\2\2\2\u021c\u021d\7o\2\2\u021d"+
		"\u021e\7q\2\2\u021e\u021f\7f\2\2\u021f\u0220\7w\2\2\u0220\u0221\7n\2\2"+
		"\u0221\u0222\7q\2\2\u0222\u0094\3\2\2\2\u0223\u0224\7t\2\2\u0224\u0225"+
		"\7c\2\2\u0225\u0226\7f\2\2\u0226\u0227\7k\2\2\u0227\u0228\7c\2\2\u0228"+
		"\u0229\7p\2\2\u0229\u022a\7u\2\2\u022a\u022b\7/\2\2\u022b\u022c\7@\2\2"+
		"\u022c\u022d\7f\2\2\u022d\u022e\7g\2\2\u022e\u022f\7i\2\2\u022f\u0230"+
		"\7t\2\2\u0230\u0231\7g\2\2\u0231\u0232\7g\2\2\u0232\u0233\7u\2\2\u0233"+
		"\u0096\3\2\2\2\u0234\u0235\7f\2\2\u0235\u0236\7g\2\2\u0236\u0237\7i\2"+
		"\2\u0237\u0238\7t\2\2\u0238\u0239\7g\2\2\u0239\u023a\7g\2\2\u023a\u023b"+
		"\7u\2\2\u023b\u023c\7/\2\2\u023c\u023d\7@\2\2\u023d\u023e\7t\2\2\u023e"+
		"\u023f\7c\2\2\u023f\u0240\7f\2\2\u0240\u0241\7k\2\2\u0241\u0242\7c\2\2"+
		"\u0242\u0243\7p\2\2\u0243\u0244\7u\2\2\u0244\u0098\3\2\2\2\u0245\u0246"+
		"\7n\2\2\u0246\u0247\7k\2\2\u0247\u0248\7u\2\2\u0248\u0249\7v\2\2\u0249"+
		"\u009a\3\2\2\2\u024a\u024b\7e\2\2\u024b\u024c\7c\2\2\u024c\u024d\7t\2"+
		"\2\u024d\u009c\3\2\2\2\u024e\u024f\7e\2\2\u024f\u0250\7f\2\2\u0250\u0251"+
		"\7t\2\2\u0251\u009e\3\2\2\2\u0252\u0253\7e\2\2\u0253\u0254\7q\2\2\u0254"+
		"\u0255\7p\2\2\u0255\u0256\7u\2\2\u0256\u00a0\3\2\2\2\u0257\u0258\7x\2"+
		"\2\u0258\u0259\7g\2\2\u0259\u025a\7e\2\2\u025a\u025b\7v\2\2\u025b\u025c"+
		"\7q\2\2\u025c\u025d\7t\2\2\u025d\u00a2\3\2\2\2\u025e\u025f\7x\2\2\u025f"+
		"\u0260\7g\2\2\u0260\u0261\7e\2\2\u0261\u0262\7v\2\2\u0262\u0263\7q\2\2"+
		"\u0263\u0264\7t\2\2\u0264\u0265\7/\2\2\u0265\u0266\7t\2\2\u0266\u0267"+
		"\7g\2\2\u0267\u0268\7h\2\2\u0268\u00a4\3\2\2\2\u0269\u026a\7x\2\2\u026a"+
		"\u026b\7g\2\2\u026b\u026c\7e\2\2\u026c\u026d\7v\2\2\u026d\u026e\7q\2\2"+
		"\u026e\u026f\7t\2\2\u026f\u0270\7/\2\2\u0270\u0271\7n\2\2\u0271\u0272"+
		"\7g\2\2\u0272\u0273\7p\2\2\u0273\u0274\7i\2\2\u0274\u0275\7v\2\2\u0275"+
		"\u0276\7j\2\2\u0276\u00a6\3\2\2\2\u0277\u0278\7f\2\2\u0278\u0279\7k\2"+
		"\2\u0279\u027a\7u\2\2\u027a\u027b\7r\2\2\u027b\u027c\7n\2\2\u027c\u027d"+
		"\7c\2\2\u027d\u027e\7{\2\2\u027e\u00a8\3\2\2\2\u027f\u0280\7u\2\2\u0280"+
		"\u0281\7v\2\2\u0281\u0282\7t\2\2\u0282\u0283\7k\2\2\u0283\u0284\7p\2\2"+
		"\u0284\u0285\7i\2\2\u0285\u0286\7/\2\2\u0286\u0287\7c\2\2\u0287\u0288"+
		"\7r\2\2\u0288\u0289\7r\2\2\u0289\u028a\7g\2\2\u028a\u028b\7p\2\2\u028b"+
		"\u028c\7f\2\2\u028c\u00aa\3\2\2\2\u028d\u028e\7u\2\2\u028e\u028f\7v\2"+
		"\2\u028f\u0290\7t\2\2\u0290\u0291\7k\2\2\u0291\u0292\7p\2\2\u0292\u0293"+
		"\7i\2\2\u0293\u0294\7/\2\2\u0294\u0295\7n\2\2\u0295\u0296\7g\2\2\u0296"+
		"\u0297\7p\2\2\u0297\u0298\7i\2\2\u0298\u0299\7v\2\2\u0299\u029a\7j\2\2"+
		"\u029a\u00ac\3\2\2\2\u029b\u029c\7u\2\2\u029c\u029d\7v\2\2\u029d\u029e"+
		"\7t\2\2\u029e\u029f\7k\2\2\u029f\u02a0\7p\2\2\u02a0\u02a1\7i\2\2\u02a1"+
		"\u02a2\7?\2\2\u02a2\u02a3\7A\2\2\u02a3\u00ae\3\2\2\2\u02a4\u02a5\7u\2"+
		"\2\u02a5\u02a6\7v\2\2\u02a6\u02a7\7t\2\2\u02a7\u02a8\7k\2\2\u02a8\u02a9"+
		"\7p\2\2\u02a9\u02aa\7i\2\2\u02aa\u02ab\7>\2\2\u02ab\u02ac\7?\2\2\u02ac"+
		"\u02ad\7A\2\2\u02ad\u00b0\3\2\2\2\u02ae\u02af\7u\2\2\u02af\u02b0\7v\2"+
		"\2\u02b0\u02b1\7t\2\2\u02b1\u02b2\7k\2\2\u02b2\u02b3\7p\2\2\u02b3\u02b4"+
		"\7i\2\2\u02b4\u02b5\7@\2\2\u02b5\u02b6\7?\2\2\u02b6\u02b7\7A\2\2\u02b7"+
		"\u00b2\3\2\2\2\u02b8\u02b9\7u\2\2\u02b9\u02ba\7v\2\2\u02ba\u02bb\7t\2"+
		"\2\u02bb\u02bc\7k\2\2\u02bc\u02bd\7p\2\2\u02bd\u02be\7i\2\2\u02be\u02bf"+
		"\7>\2\2\u02bf\u02c0\7A\2\2\u02c0\u00b4\3\2\2\2\u02c1\u02c2\7u\2\2\u02c2"+
		"\u02c3\7v\2\2\u02c3\u02c4\7t\2\2\u02c4\u02c5\7k\2\2\u02c5\u02c6\7p\2\2"+
		"\u02c6\u02c7\7i\2\2\u02c7\u02c8\7@\2\2\u02c8\u02c9\7A\2\2\u02c9\u00b6"+
		"\3\2\2\2\u02ca\u02cb\7u\2\2\u02cb\u02cc\7w\2\2\u02cc\u02cd\7d\2\2\u02cd"+
		"\u02ce\7u\2\2\u02ce\u02cf\7v\2\2\u02cf\u02d0\7t\2\2\u02d0\u02d1\7k\2\2"+
		"\u02d1\u02d2\7p\2\2\u02d2\u02d3\7i\2\2\u02d3\u00b8\3\2\2\2\u02d4\u02d5"+
		"\7p\2\2\u02d5\u02d6\7w\2\2\u02d6\u02d7\7o\2\2\u02d7\u02d8\7d\2\2\u02d8"+
		"\u02d9\7g\2\2\u02d9\u02da\7t\2\2\u02da\u02db\7A\2\2\u02db\u00ba\3\2\2"+
		"\2\u02dc\u02dd\7d\2\2\u02dd\u02de\7q\2\2\u02de\u02df\7q\2\2\u02df\u02e0"+
		"\7n\2\2\u02e0\u02e1\7A\2\2\u02e1\u00bc\3\2\2\2\u02e2\u02e3\7e\2\2\u02e3"+
		"\u02e4\7j\2\2\u02e4\u02e5\7c\2\2\u02e5\u02e6\7t\2\2\u02e6\u02e7\7A\2\2"+
		"\u02e7\u00be\3\2\2\2\u02e8\u02e9\7u\2\2\u02e9\u02ea\7v\2\2\u02ea\u02eb"+
		"\7t\2\2\u02eb\u02ec\7k\2\2\u02ec\u02ed\7p\2\2\u02ed\u02ee\7i\2\2\u02ee"+
		"\u02ef\7A\2\2\u02ef\u00c0\3\2\2\2\u02f0\u02f1\7n\2\2\u02f1\u02f2\7k\2"+
		"\2\u02f2\u02f3\7u\2\2\u02f3\u02f4\7v\2\2\u02f4\u02f5\7A\2\2\u02f5\u00c2"+
		"\3\2\2\2\u02f6\u02f7\7x\2\2\u02f7\u02f8\7g\2\2\u02f8\u02f9\7e\2\2\u02f9"+
		"\u02fa\7v\2\2\u02fa\u02fb\7q\2\2\u02fb\u02fc\7t\2\2\u02fc\u02fd\7A\2\2"+
		"\u02fd\u00c4\3\2\2\2\u02fe\u02ff\7p\2\2\u02ff\u0300\7w\2\2\u0300\u0301"+
		"\7n\2\2\u0301\u0302\7n\2\2\u0302\u0303\7A\2\2\u0303\u00c6\3\2\2\2\u0304"+
		"\u0305\7u\2\2\u0305\u0306\7{\2\2\u0306\u0307\7o\2\2\u0307\u0308\7d\2\2"+
		"\u0308\u0309\7q\2\2\u0309\u030a\7n\2\2\u030a\u030b\7A\2\2\u030b\u00c8"+
		"\3\2\2\2\u030c\u030d\7r\2\2\u030d\u030e\7c\2\2\u030e\u030f\7k\2\2\u030f"+
		"\u0310\7t\2\2\u0310\u0311\7A\2\2\u0311\u00ca\3\2\2\2\u0312\u0313\7g\2"+
		"\2\u0313\u0314\7s\2\2\u0314\u0315\7A\2\2\u0315\u00cc\3\2\2\2\u0316\u0317"+
		"\7g\2\2\u0317\u0318\7s\2\2\u0318\u0319\7w\2\2\u0319\u031a\7c\2\2\u031a"+
		"\u031b\7n\2\2\u031b\u031c\7A\2\2\u031c\u00ce\3\2\2\2\u031d\u031e\7t\2"+
		"\2\u031e\u031f\7c\2\2\u031f\u0320\7p\2\2\u0320\u0321\7f\2\2\u0321\u0322"+
		"\7q\2\2\u0322\u0323\7o\2\2\u0323\u0324\7/\2\2\u0324\u0325\7k\2\2\u0325"+
		"\u0326\7p\2\2\u0326\u0327\7v\2\2\u0327\u0328\7g\2\2\u0328\u0329\7i\2\2"+
		"\u0329\u032a\7g\2\2\u032a\u032b\7t\2\2\u032b\u00d0\3\2\2\2\u032c\u032d"+
		"\7t\2\2\u032d\u032e\7c\2\2\u032e\u032f\7p\2\2\u032f\u0330\7f\2\2\u0330"+
		"\u0331\7q\2\2\u0331\u0332\7o\2\2\u0332\u0333\7/\2\2\u0333\u0334\7f\2\2"+
		"\u0334\u0335\7q\2\2\u0335\u0336\7w\2\2\u0336\u0337\7d\2\2\u0337\u0338"+
		"\7n\2\2\u0338\u0339\7g\2\2\u0339\u00d2\3\2\2\2\u033a\u033b\7t\2\2\u033b"+
		"\u033c\7c\2\2\u033c\u033d\7p\2\2\u033d\u033e\7f\2\2\u033e\u033f\7q\2\2"+
		"\u033f\u0340\7o\2\2\u0340\u00d4\3\2\2\2\u0341\u0342\7t\2\2\u0342\u0343"+
		"\7g\2\2\u0343\u0344\7c\2\2\u0344\u0345\7f\2\2\u0345\u0346\7/\2\2\u0346"+
		"\u0347\7n\2\2\u0347\u0348\7k\2\2\u0348\u0349\7p\2\2\u0349\u034a\7g\2\2"+
		"\u034a\u00d6\3\2\2\2\u034b\u034c\7t\2\2\u034c\u034d\7g\2\2\u034d\u034e"+
		"\7c\2\2\u034e\u034f\7f\2\2\u034f\u0350\7/\2\2\u0350\u0351\7p\2\2\u0351"+
		"\u0352\7w\2\2\u0352\u0353\7o\2\2\u0353\u0354\7d\2\2\u0354\u0355\7g\2\2"+
		"\u0355\u0356\7t\2\2\u0356\u00d8\3\2\2\2\u0357\u0358\7u\2\2\u0358\u0359"+
		"\7v\2\2\u0359\u035a\7t\2\2\u035a\u035b\7k\2\2\u035b\u035c\7p\2\2\u035c"+
		"\u035d\7i\2\2\u035d\u035e\7/\2\2\u035e\u035f\7@\2\2\u035f\u0360\7p\2\2"+
		"\u0360\u0361\7w\2\2\u0361\u0362\7o\2\2\u0362\u0363\7d\2\2\u0363\u0364"+
		"\7g\2\2\u0364\u0365\7t\2\2\u0365\u00da\3\2\2\2\u0366\u0367\7p\2\2\u0367"+
		"\u0368\7w\2\2\u0368\u0369\7o\2\2\u0369\u036a\7d\2\2\u036a\u036b\7g\2\2"+
		"\u036b\u036c\7t\2\2\u036c\u036d\7/\2\2\u036d\u036e\7@\2\2\u036e\u036f"+
		"\7u\2\2\u036f\u0370\7v\2\2\u0370\u0371\7t\2\2\u0371\u0372\7k\2\2\u0372"+
		"\u0373\7p\2\2\u0373\u0374\7i\2\2\u0374\u00dc\3\2\2\2\u0375\u0376\7u\2"+
		"\2\u0376\u0377\7v\2\2\u0377\u0378\7t\2\2\u0378\u0379\7k\2\2\u0379\u037a"+
		"\7p\2\2\u037a\u037b\7i\2\2\u037b\u037c\7/\2\2\u037c\u037d\7@\2\2\u037d"+
		"\u037e\7n\2\2\u037e\u037f\7k\2\2\u037f\u0380\7u\2\2\u0380\u0381\7v\2\2"+
		"\u0381\u00de\3\2\2\2\u0382\u0383\7n\2\2\u0383\u0384\7k\2\2\u0384\u0385"+
		"\7u\2\2\u0385\u0386\7v\2\2\u0386\u0387\7/\2\2\u0387\u0388\7@\2\2\u0388"+
		"\u0389\7u\2\2\u0389\u038a\7v\2\2\u038a\u038b\7t\2\2\u038b\u038c\7k\2\2"+
		"\u038c\u038d\7p\2\2\u038d\u038e\7i\2\2\u038e\u00e0\3\2\2\2\u038f\u0390"+
		"\7u\2\2\u0390\u0391\7g\2\2\u0391\u0392\7v\2\2\u0392\u0393\7#\2\2\u0393"+
		"\u00e2\3\2\2\2\u0394\u0395\7u\2\2\u0395\u0396\7g\2\2\u0396\u0397\7v\2"+
		"\2\u0397\u0398\7/\2\2\u0398\u0399\7e\2\2\u0399\u039a\7c\2\2\u039a\u039b"+
		"\7t\2\2\u039b\u039c\7#\2\2\u039c\u00e4\3\2\2\2\u039d\u039e\7u\2\2\u039e"+
		"\u039f\7g\2\2\u039f\u03a0\7v\2\2\u03a0\u03a1\7/\2\2\u03a1\u03a2\7e\2\2"+
		"\u03a2\u03a3\7f\2\2\u03a3\u03a4\7t\2\2\u03a4\u03a5\7#\2\2\u03a5\u00e6"+
		"\3\2\2\2\u03a6\u03a7\7x\2\2\u03a7\u03a8\7g\2\2\u03a8\u03a9\7e\2\2\u03a9"+
		"\u03aa\7v\2\2\u03aa\u03ab\7q\2\2\u03ab\u03ac\7t\2\2\u03ac\u03ad\7/\2\2"+
		"\u03ad\u03ae\7u\2\2\u03ae\u03af\7g\2\2\u03af\u03b0\7v\2\2\u03b0\u03b1"+
		"\7#\2\2\u03b1\u00e8\3\2\2\2\u03b2\u03b6\t\f\2\2\u03b3\u03b5\t\r\2\2\u03b4"+
		"\u03b3\3\2\2\2\u03b5\u03b8\3\2\2\2\u03b6\u03b4\3\2\2\2\u03b6\u03b7\3\2"+
		"\2\2\u03b7\u03ba\3\2\2\2\u03b8\u03b6\3\2\2\2\u03b9\u03bb\t\16\2\2\u03ba"+
		"\u03b9\3\2\2\2\u03ba\u03bb\3\2\2\2\u03bb\u00ea\3\2\2\2\20\2\u00ee\u00f6"+
		"\u011d\u015e\u0163\u0169\u016c\u0171\u0173\u0187\u01b8\u03b6\u03ba\3\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}