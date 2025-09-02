// Generated from ExprFunc.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class ExprFuncLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, MUL=11, DIV=12, ADD=13, SUB=14, ID=15, INT=16, FLOAT=17, NEWLINE=18, 
		WS=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "MUL", "DIV", "ADD", "SUB", "ID", "INT", "FLOAT", "NEWLINE", 
			"WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'('", "')'", "'!'", "'sin'", "'cos'", "'tan'", "'sqrt'", 
			"'log'", "'ln'", "'*'", "'/'", "'+'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "MUL", 
			"DIV", "ADD", "SUB", "ID", "INT", "FLOAT", "NEWLINE", "WS"
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


	public ExprFuncLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ExprFunc.g4"; }

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
		"\u0004\u0000\u0013p\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t"+
		"\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\r\u0001\r\u0001\u000e\u0004\u000eQ\b\u000e\u000b\u000e\f\u000e"+
		"R\u0001\u000f\u0004\u000fV\b\u000f\u000b\u000f\f\u000fW\u0001\u0010\u0004"+
		"\u0010[\b\u0010\u000b\u0010\f\u0010\\\u0001\u0010\u0001\u0010\u0004\u0010"+
		"a\b\u0010\u000b\u0010\f\u0010b\u0001\u0011\u0003\u0011f\b\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0012\u0004\u0012k\b\u0012\u000b\u0012\f\u0012"+
		"l\u0001\u0012\u0001\u0012\u0000\u0000\u0013\u0001\u0001\u0003\u0002\u0005"+
		"\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n"+
		"\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012%\u0013\u0001\u0000\u0003\u0002\u0000AZaz\u0001\u000009\u0002\u0000"+
		"\t\t  u\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000"+
		"\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000"+
		"\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000"+
		"\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000"+
		"\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000"+
		"\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000"+
		"\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000"+
		"\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000"+
		"!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001"+
		"\u0000\u0000\u0000\u0001\'\u0001\u0000\u0000\u0000\u0003)\u0001\u0000"+
		"\u0000\u0000\u0005+\u0001\u0000\u0000\u0000\u0007-\u0001\u0000\u0000\u0000"+
		"\t/\u0001\u0000\u0000\u0000\u000b3\u0001\u0000\u0000\u0000\r7\u0001\u0000"+
		"\u0000\u0000\u000f;\u0001\u0000\u0000\u0000\u0011@\u0001\u0000\u0000\u0000"+
		"\u0013D\u0001\u0000\u0000\u0000\u0015G\u0001\u0000\u0000\u0000\u0017I"+
		"\u0001\u0000\u0000\u0000\u0019K\u0001\u0000\u0000\u0000\u001bM\u0001\u0000"+
		"\u0000\u0000\u001dP\u0001\u0000\u0000\u0000\u001fU\u0001\u0000\u0000\u0000"+
		"!Z\u0001\u0000\u0000\u0000#e\u0001\u0000\u0000\u0000%j\u0001\u0000\u0000"+
		"\u0000\'(\u0005=\u0000\u0000(\u0002\u0001\u0000\u0000\u0000)*\u0005(\u0000"+
		"\u0000*\u0004\u0001\u0000\u0000\u0000+,\u0005)\u0000\u0000,\u0006\u0001"+
		"\u0000\u0000\u0000-.\u0005!\u0000\u0000.\b\u0001\u0000\u0000\u0000/0\u0005"+
		"s\u0000\u000001\u0005i\u0000\u000012\u0005n\u0000\u00002\n\u0001\u0000"+
		"\u0000\u000034\u0005c\u0000\u000045\u0005o\u0000\u000056\u0005s\u0000"+
		"\u00006\f\u0001\u0000\u0000\u000078\u0005t\u0000\u000089\u0005a\u0000"+
		"\u00009:\u0005n\u0000\u0000:\u000e\u0001\u0000\u0000\u0000;<\u0005s\u0000"+
		"\u0000<=\u0005q\u0000\u0000=>\u0005r\u0000\u0000>?\u0005t\u0000\u0000"+
		"?\u0010\u0001\u0000\u0000\u0000@A\u0005l\u0000\u0000AB\u0005o\u0000\u0000"+
		"BC\u0005g\u0000\u0000C\u0012\u0001\u0000\u0000\u0000DE\u0005l\u0000\u0000"+
		"EF\u0005n\u0000\u0000F\u0014\u0001\u0000\u0000\u0000GH\u0005*\u0000\u0000"+
		"H\u0016\u0001\u0000\u0000\u0000IJ\u0005/\u0000\u0000J\u0018\u0001\u0000"+
		"\u0000\u0000KL\u0005+\u0000\u0000L\u001a\u0001\u0000\u0000\u0000MN\u0005"+
		"-\u0000\u0000N\u001c\u0001\u0000\u0000\u0000OQ\u0007\u0000\u0000\u0000"+
		"PO\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000RP\u0001\u0000\u0000"+
		"\u0000RS\u0001\u0000\u0000\u0000S\u001e\u0001\u0000\u0000\u0000TV\u0007"+
		"\u0001\u0000\u0000UT\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000\u0000"+
		"WU\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000X \u0001\u0000\u0000"+
		"\u0000Y[\u0007\u0001\u0000\u0000ZY\u0001\u0000\u0000\u0000[\\\u0001\u0000"+
		"\u0000\u0000\\Z\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000\u0000]^\u0001"+
		"\u0000\u0000\u0000^`\u0005.\u0000\u0000_a\u0007\u0001\u0000\u0000`_\u0001"+
		"\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000b`\u0001\u0000\u0000\u0000"+
		"bc\u0001\u0000\u0000\u0000c\"\u0001\u0000\u0000\u0000df\u0005\r\u0000"+
		"\u0000ed\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000fg\u0001\u0000"+
		"\u0000\u0000gh\u0005\n\u0000\u0000h$\u0001\u0000\u0000\u0000ik\u0007\u0002"+
		"\u0000\u0000ji\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000lj\u0001"+
		"\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000"+
		"no\u0006\u0012\u0000\u0000o&\u0001\u0000\u0000\u0000\u0007\u0000RW\\b"+
		"el\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}