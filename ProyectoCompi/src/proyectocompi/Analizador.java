
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20150226 (SVN rev 63)
//----------------------------------------------------

package proyectocompi;

import java_cup.runtime.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20150226 (SVN rev 63) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class Analizador extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  public Analizador() {super();}

  /** Constructor which sets the default scanner. */
  public Analizador(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public Analizador(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\022\000\002\002\004\000\002\003\004\000\002\005" +
    "\004\000\002\005\006\000\002\005\005\000\002\006\003" +
    "\000\002\006\003\000\002\007\004\000\002\010\005\000" +
    "\002\010\005\000\002\010\003\000\002\011\005\000\002" +
    "\011\005\000\002\011\005\000\002\011\003\000\002\012" +
    "\003\000\002\012\003\000\002\012\005" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\040\000\006\012\004\013\007\001\002\000\004\011" +
    "\ufffc\001\002\000\004\002\042\001\002\000\004\011\010" +
    "\001\002\000\004\011\ufffb\001\002\000\006\014\013\015" +
    "\012\001\002\000\004\002\000\001\002\000\004\002\uffff" +
    "\001\002\000\012\005\023\006\022\011\014\023\017\001" +
    "\002\000\004\015\041\001\002\000\004\002\ufffd\001\002" +
    "\000\020\015\ufff7\016\ufff7\017\ufff7\020\031\021\032\022" +
    "\030\024\ufff7\001\002\000\010\005\023\006\022\023\017" +
    "\001\002\000\020\015\ufff3\016\ufff3\017\ufff3\020\ufff3\021" +
    "\ufff3\022\ufff3\024\ufff3\001\002\000\010\015\026\016\024" +
    "\017\025\001\002\000\020\015\ufff1\016\ufff1\017\ufff1\020" +
    "\ufff1\021\ufff1\022\ufff1\024\ufff1\001\002\000\020\015\ufff2" +
    "\016\ufff2\017\ufff2\020\ufff2\021\ufff2\022\ufff2\024\ufff2\001" +
    "\002\000\010\005\023\006\022\023\017\001\002\000\010" +
    "\005\023\006\022\023\017\001\002\000\004\002\ufffa\001" +
    "\002\000\020\015\ufff8\016\ufff8\017\ufff8\020\031\021\032" +
    "\022\030\024\ufff8\001\002\000\010\005\023\006\022\023" +
    "\017\001\002\000\010\005\023\006\022\023\017\001\002" +
    "\000\010\005\023\006\022\023\017\001\002\000\020\015" +
    "\ufff5\016\ufff5\017\ufff5\020\ufff5\021\ufff5\022\ufff5\024\ufff5" +
    "\001\002\000\020\015\ufff6\016\ufff6\017\ufff6\020\ufff6\021" +
    "\ufff6\022\ufff6\024\ufff6\001\002\000\020\015\ufff4\016\ufff4" +
    "\017\ufff4\020\ufff4\021\ufff4\022\ufff4\024\ufff4\001\002\000" +
    "\020\015\ufff9\016\ufff9\017\ufff9\020\031\021\032\022\030" +
    "\024\ufff9\001\002\000\010\016\024\017\025\024\040\001" +
    "\002\000\020\015\ufff0\016\ufff0\017\ufff0\020\ufff0\021\ufff0" +
    "\022\ufff0\024\ufff0\001\002\000\004\002\ufffe\001\002\000" +
    "\004\002\001\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\040\000\006\003\004\006\005\001\001\000\002\001" +
    "\001\000\002\001\001\000\004\005\010\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\012\007\014\010\020\011\015\012\017\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\010\010\036\011\015\012\017\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\006\011\035\012\017\001\001\000\006\011\026\012\017" +
    "\001\001\000\002\001\001\000\002\001\001\000\004\012" +
    "\034\001\001\000\004\012\033\001\001\000\004\012\032" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$Analizador$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$Analizador$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$Analizador$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$Analizador$actions {
  private final Analizador parser;

  /** Constructor */
  CUP$Analizador$actions(Analizador parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$Analizador$do_action_part00000000(
    int                        CUP$Analizador$act_num,
    java_cup.runtime.lr_parser CUP$Analizador$parser,
    java.util.Stack            CUP$Analizador$stack,
    int                        CUP$Analizador$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$Analizador$result;

      /* select the action based on the action number */
      switch (CUP$Analizador$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= Stat EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$Analizador$stack.elementAt(CUP$Analizador$top-1)).value;
		RESULT = start_val;
              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-1)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$Analizador$parser.done_parsing();
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // Stat ::= ArithType Stat2 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("Stat",1, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-1)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // Stat2 ::= ID END 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("Stat2",3, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-1)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // Stat2 ::= ID ASSIGN ID END 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("Stat2",3, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-3)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // Stat2 ::= ID ASSIGN Arit 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("Stat2",3, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-2)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // ArithType ::= NUM 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("ArithType",4, ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // ArithType ::= DEC 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("ArithType",4, ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // Arit ::= E END 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("Arit",5, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-1)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // E ::= E PLUS T 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("E",6, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-2)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // E ::= E MINUS T 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("E",6, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-2)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // E ::= T 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("E",6, ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // T ::= T MULT F 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("T",7, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-2)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // T ::= T DIV F 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("T",7, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-2)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // T ::= T MOD F 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("T",7, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-2)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // T ::= F 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("T",7, ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // F ::= DIGIT 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("F",8, ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // F ::= FLOAT 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("F",8, ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // F ::= PARIZQ E PARDER 
            {
              Object RESULT =null;

              CUP$Analizador$result = parser.getSymbolFactory().newSymbol("F",8, ((java_cup.runtime.Symbol)CUP$Analizador$stack.elementAt(CUP$Analizador$top-2)), ((java_cup.runtime.Symbol)CUP$Analizador$stack.peek()), RESULT);
            }
          return CUP$Analizador$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$Analizador$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$Analizador$do_action(
    int                        CUP$Analizador$act_num,
    java_cup.runtime.lr_parser CUP$Analizador$parser,
    java.util.Stack            CUP$Analizador$stack,
    int                        CUP$Analizador$top)
    throws java.lang.Exception
    {
              return CUP$Analizador$do_action_part00000000(
                               CUP$Analizador$act_num,
                               CUP$Analizador$parser,
                               CUP$Analizador$stack,
                               CUP$Analizador$top);
    }
}

}
