/* The following code was generated by JFlex 1.6.0 */


package proyectocompi;

import java.util.ArrayList;
import javax.swing.JTextArea;
import java_cup.runtime.*;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.0
 * from the specification file <tt>src/proyectocompi/LenguajeCompi.flex</tt>
 */
class LenguajeCompi implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int COMMENTS = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\22\1\13\1\14\1\14\1\14\22\0\1\15\1\47\1\16"+
    "\1\51\1\0\1\45\1\0\1\12\1\17\1\23\1\24\1\43\1\50"+
    "\1\44\1\20\1\25\12\1\1\0\1\37\1\46\1\27\1\42\1\0"+
    "\5\26\1\26\7\26\1\26\15\26\1\0\1\0\1\0\1\0\1\26"+
    "\1\0\1\7\1\32\1\35\1\34\1\5\1\6\1\26\1\26\1\33"+
    "\2\26\1\10\1\31\1\30\1\41\1\40\1\26\1\3\1\11\1\2"+
    "\1\4\2\26\1\26\1\36\1\26\1\0\1\21\10\0\1\14\32\0"+
    "\1\14\u15df\0\1\14\u097f\0\13\14\35\0\1\14\1\14\5\0\1\14"+
    "\57\0\1\14\u0fa0\0\1\14\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\ud00f\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\1\2\1\3\5\4\1\1\2\5\1\6"+
    "\1\3\1\7\1\10\1\11\1\12\1\3\4\4\1\13"+
    "\1\3\1\14\1\15\1\16\1\17\1\20\1\3\1\21"+
    "\2\5\1\0\11\4\2\0\1\4\5\0\1\22\5\4"+
    "\1\23\2\4\1\24\1\25\1\26\1\27\1\4\1\30"+
    "\1\31\1\32\1\4\1\33\1\34\1\35\1\36\1\37"+
    "\1\40\7\0\1\41\1\42\1\43\1\44\1\45\1\46"+
    "\1\47\1\27\1\50\1\0\1\51\1\52\1\53\1\54"+
    "\1\55\1\56\11\0\1\57";

  private static int [] zzUnpackAction() {
    int [] result = new int[110];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\52\0\124\0\176\0\250\0\322\0\374\0\u0126"+
    "\0\u0150\0\u017a\0\u01a4\0\u01ce\0\u01f8\0\124\0\u0222\0\124"+
    "\0\124\0\u024c\0\u0276\0\u02a0\0\u02ca\0\u02f4\0\u031e\0\u0348"+
    "\0\124\0\u0372\0\u039c\0\124\0\124\0\124\0\u03c6\0\u03f0"+
    "\0\124\0\124\0\u041a\0\u0444\0\u046e\0\u0498\0\u04c2\0\u04ec"+
    "\0\u0516\0\u0540\0\u056a\0\u0594\0\u05be\0\u05e8\0\u0612\0\u0222"+
    "\0\u063c\0\u0666\0\u0690\0\u06ba\0\u06e4\0\124\0\u070e\0\u0738"+
    "\0\u0762\0\u078c\0\u07b6\0\374\0\u07e0\0\u080a\0\124\0\124"+
    "\0\124\0\u0834\0\u085e\0\374\0\374\0\374\0\u0888\0\374"+
    "\0\374\0\374\0\374\0\374\0\124\0\u08b2\0\u08dc\0\u0906"+
    "\0\u0930\0\u095a\0\u0984\0\u09ae\0\374\0\374\0\374\0\374"+
    "\0\374\0\374\0\374\0\124\0\374\0\u09d8\0\124\0\124"+
    "\0\124\0\124\0\124\0\124\0\u0a02\0\u0a2c\0\u0a56\0\u0a80"+
    "\0\u0aaa\0\u0ad4\0\u0afe\0\u0b28\0\u0b52\0\124";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[110];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\1\5\1\6\2\7\1\10\1\11\1\7"+
    "\1\12\1\13\2\14\1\15\1\3\1\16\1\3\1\17"+
    "\1\14\1\20\1\21\1\22\1\7\1\23\1\24\1\7"+
    "\1\25\1\7\1\26\1\27\1\30\1\31\1\7\1\32"+
    "\1\33\1\34\1\35\1\36\1\37\1\40\1\41\1\7"+
    "\13\42\3\14\3\42\2\14\1\42\1\43\25\42\53\0"+
    "\1\4\16\0\1\44\32\0\2\7\1\45\6\7\7\0"+
    "\1\7\4\0\1\7\1\0\3\7\1\46\3\7\1\0"+
    "\2\7\7\0\1\7\1\0\3\7\1\47\1\50\4\7"+
    "\7\0\1\7\4\0\1\7\1\0\7\7\1\0\2\7"+
    "\7\0\1\7\1\0\11\7\7\0\1\7\4\0\1\7"+
    "\1\0\7\7\1\0\2\7\7\0\1\7\1\0\6\7"+
    "\1\51\2\7\7\0\1\7\4\0\1\7\1\0\7\7"+
    "\1\0\2\7\7\0\1\7\1\0\11\7\7\0\1\7"+
    "\4\0\1\7\1\0\1\52\6\7\1\0\2\7\7\0"+
    "\1\7\1\0\1\7\1\53\2\7\1\54\4\7\7\0"+
    "\1\7\4\0\1\7\1\0\6\7\1\55\1\0\2\7"+
    "\7\0\1\7\52\56\13\0\3\14\3\0\2\14\42\0"+
    "\3\14\1\57\2\0\2\14\30\0\11\7\1\0\3\14"+
    "\3\0\1\60\1\14\3\0\1\7\1\0\7\7\1\0"+
    "\2\7\7\0\1\7\2\0\1\61\1\62\5\0\1\63"+
    "\23\0\1\64\1\65\42\0\1\66\23\0\3\7\1\67"+
    "\5\7\7\0\1\7\4\0\1\7\1\0\7\7\1\0"+
    "\2\7\7\0\1\7\1\0\11\7\7\0\1\7\4\0"+
    "\1\7\1\0\3\7\1\70\3\7\1\0\2\7\7\0"+
    "\1\7\1\0\4\7\1\71\4\7\7\0\1\7\4\0"+
    "\1\7\1\0\7\7\1\0\2\7\7\0\1\7\1\0"+
    "\11\7\7\0\1\7\4\0\1\7\1\0\7\7\1\0"+
    "\1\7\1\72\7\0\1\7\1\0\4\7\1\73\4\7"+
    "\7\0\1\7\4\0\1\7\1\0\7\7\1\0\2\7"+
    "\7\0\1\7\1\0\2\7\1\74\1\75\5\7\7\0"+
    "\1\7\4\0\1\7\1\0\7\7\1\0\1\76\1\7"+
    "\7\0\1\7\27\0\1\77\51\0\1\100\51\0\1\101"+
    "\73\0\1\42\1\0\1\102\16\0\1\102\4\0\1\102"+
    "\25\0\3\7\1\103\5\7\7\0\1\7\4\0\1\7"+
    "\1\0\7\7\1\0\2\7\7\0\1\7\1\0\7\7"+
    "\1\104\1\7\7\0\1\7\4\0\1\7\1\0\7\7"+
    "\1\0\2\7\7\0\1\7\1\0\11\7\7\0\1\7"+
    "\4\0\1\7\1\0\1\105\6\7\1\0\2\7\7\0"+
    "\1\7\1\0\11\7\7\0\1\7\4\0\1\7\1\0"+
    "\7\7\1\0\1\106\1\7\7\0\1\7\1\0\7\7"+
    "\1\107\1\7\7\0\1\7\4\0\1\7\1\0\7\7"+
    "\1\0\2\7\7\0\1\7\1\0\11\7\7\0\1\7"+
    "\4\0\1\7\1\0\4\7\1\110\1\7\1\111\1\0"+
    "\2\7\7\0\1\7\1\0\2\7\1\112\6\7\7\0"+
    "\1\7\4\0\1\7\1\0\7\7\1\0\2\7\7\0"+
    "\1\7\1\0\1\7\1\113\7\7\7\0\1\7\4\0"+
    "\1\7\1\0\7\7\1\0\2\7\7\0\1\7\1\0"+
    "\11\7\7\0\1\7\4\0\1\7\1\0\1\7\1\114"+
    "\5\7\1\0\2\7\7\0\1\7\12\0\1\115\56\0"+
    "\1\116\65\0\1\117\22\0\1\120\1\121\51\0\1\122"+
    "\105\0\1\123\15\0\1\124\45\0\11\7\7\0\1\7"+
    "\4\0\1\7\1\0\1\7\1\125\5\7\1\0\2\7"+
    "\7\0\1\7\1\0\11\7\7\0\1\7\4\0\1\7"+
    "\1\0\1\126\6\7\1\0\2\7\7\0\1\7\1\0"+
    "\11\7\7\0\1\7\4\0\1\7\1\0\5\7\1\127"+
    "\1\7\1\0\2\7\7\0\1\7\1\0\11\7\7\0"+
    "\1\7\4\0\1\7\1\0\1\130\6\7\1\0\2\7"+
    "\7\0\1\7\1\0\1\7\1\131\7\7\7\0\1\7"+
    "\4\0\1\7\1\0\7\7\1\0\2\7\7\0\1\7"+
    "\1\0\1\7\1\132\7\7\7\0\1\7\4\0\1\7"+
    "\1\0\7\7\1\0\2\7\7\0\1\7\1\0\1\7"+
    "\1\133\7\7\7\0\1\7\4\0\1\7\1\0\7\7"+
    "\1\0\2\7\7\0\1\7\1\0\1\134\16\0\1\134"+
    "\4\0\1\134\25\0\4\7\1\135\4\7\7\0\1\7"+
    "\4\0\1\7\1\0\7\7\1\0\2\7\7\0\1\7"+
    "\1\0\10\7\1\103\7\0\1\7\4\0\1\7\1\0"+
    "\7\7\1\0\2\7\7\0\1\7\20\0\1\136\41\0"+
    "\1\137\71\0\1\140\61\0\1\141\13\0\1\142\77\0"+
    "\1\143\23\0\1\144\70\0\1\145\41\0\1\146\61\0"+
    "\1\147\52\0\1\150\50\0\1\151\43\0\1\152\61\0"+
    "\1\153\52\0\1\154\43\0\1\155\50\0\1\156\34\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2940];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\11\12\1\1\11\1\1\2\11\7\1\1\11"+
    "\2\1\3\11\2\1\2\11\1\1\1\0\11\1\2\0"+
    "\1\1\5\0\1\11\10\1\3\11\13\1\1\11\7\0"+
    "\7\1\1\11\1\1\1\0\6\11\11\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[110];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
	public static JTextArea outputArea;
	public static ArrayList<String> lexErrors = new ArrayList<String>();

	public void printOutput(String outputLine){
		if(outputArea != null){
			outputArea.append(outputLine + "\n");
		} 
		lexErrors.add(outputLine);
	}


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  LenguajeCompi(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 202) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;           
    int totalRead = 0;
    while (totalRead < requested) {
      int numRead = zzReader.read(zzBuffer, zzEndRead + totalRead, requested - totalRead);
      if (numRead == -1) {
        break;
      }
      totalRead += numRead;
    }

    if (totalRead > 0) {
      zzEndRead += totalRead;
      if (totalRead == requested) { /* possibly more input available */
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      return false;
    }

    // totalRead = 0: End of stream
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 1: 
          { this.printOutput("> Unknown Token: '"+yytext()+"' in Line "+Integer.toString(yyline)+" Column "+Integer.toString(yycolumn) );
          }
        case 48: break;
        case 2: 
          { return new Symbol( sym.DIGIT, Integer.parseInt(yytext()) );
          }
        case 49: break;
        case 3: 
          { return new Symbol(sym.NOT);
          }
        case 50: break;
        case 4: 
          { return new Symbol(sym.ID, yytext());
          }
        case 51: break;
        case 5: 
          { 
          }
        case 52: break;
        case 6: 
          { return new Symbol(sym.PARIZQ);
          }
        case 53: break;
        case 7: 
          { return new Symbol(sym.PARDER);
          }
        case 54: break;
        case 8: 
          { return new Symbol(sym.MULT);
          }
        case 55: break;
        case 9: 
          { return new Symbol(sym.DIV);
          }
        case 56: break;
        case 10: 
          { return new Symbol(sym.ASSIGN);
          }
        case 57: break;
        case 11: 
          { return new Symbol(sym.END);
          }
        case 58: break;
        case 12: 
          { return new Symbol(sym.GREATERTHAN);
          }
        case 59: break;
        case 13: 
          { return new Symbol(sym.PLUS);
          }
        case 60: break;
        case 14: 
          { return new Symbol(sym.MINUS);
          }
        case 61: break;
        case 15: 
          { return new Symbol(sym.MOD);
          }
        case 62: break;
        case 16: 
          { return new Symbol(sym.LESSTHAN);
          }
        case 63: break;
        case 17: 
          { return new Symbol(sym.COMMA);
          }
        case 64: break;
        case 18: 
          { return new Symbol(sym.EQUAL);
          }
        case 65: break;
        case 19: 
          { return new Symbol(sym.OR);
          }
        case 66: break;
        case 20: 
          { return new Symbol(sym.GREATEREQUALTHAN);
          }
        case 67: break;
        case 21: 
          { return new Symbol(sym.LESSEQUALTHAN);
          }
        case 68: break;
        case 22: 
          { return new Symbol(sym.NOTEQUAL);
          }
        case 69: break;
        case 23: 
          { return new Symbol( sym.FLOAT, new Float(yytext()) );
          }
        case 70: break;
        case 24: 
          { return new Symbol(sym.TIL);
          }
        case 71: break;
        case 25: 
          { return new Symbol(sym.MAIN);
          }
        case 72: break;
        case 26: 
          { return new Symbol(sym.REP);
          }
        case 73: break;
        case 27: 
          { return new Symbol(sym.AND);
          }
        case 74: break;
        case 28: 
          { return new Symbol(sym.ANY);
          }
        case 75: break;
        case 29: 
          { return new Symbol(sym.STR);
          }
        case 76: break;
        case 30: 
          { return new Symbol(sym.SET);
          }
        case 77: break;
        case 31: 
          { return new Symbol(sym.SYM);
          }
        case 78: break;
        case 32: 
          { return new Symbol( sym.CHAR, yytext().charAt(0) );
          }
        case 79: break;
        case 33: 
          { return new Symbol(sym.NUM);
          }
        case 80: break;
        case 34: 
          { return new Symbol(sym.BIN);
          }
        case 81: break;
        case 35: 
          { return new Symbol(sym.DEC);
          }
        case 82: break;
        case 36: 
          { return new Symbol(sym.COND);
          }
        case 83: break;
        case 37: 
          { return new Symbol(sym.YET);
          }
        case 84: break;
        case 38: 
          { return new Symbol(sym.OUT);
          }
        case 85: break;
        case 39: 
          { return new Symbol(sym.OPT);
          }
        case 86: break;
        case 40: 
          { return new Symbol( sym.BOOL, new Boolean(yytext()) );
          }
        case 87: break;
        case 41: 
          { return new Symbol(sym.TILEND);
          }
        case 88: break;
        case 42: 
          { return new Symbol(sym.MAINEND);
          }
        case 89: break;
        case 43: 
          { return new Symbol(sym.REPEND);
          }
        case 90: break;
        case 44: 
          { return new Symbol(sym.SETEND);
          }
        case 91: break;
        case 45: 
          { return new Symbol(sym.CONDEND);
          }
        case 92: break;
        case 46: 
          { return new Symbol(sym.YETEND);
          }
        case 93: break;
        case 47: 
          { return new Symbol( sym.STRING, new String(yytext()) );
          }
        case 94: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              {     return new Symbol(sym.EOF);
 }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
