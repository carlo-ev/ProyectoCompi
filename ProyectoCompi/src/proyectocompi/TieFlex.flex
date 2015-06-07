
package proyectocompi;

import java.util.ArrayList;
import javax.swing.JTextArea;
import java_cup.runtime.Symbol;
%%
%class TieLexer
%unicode
%line
%column
%int
%cup

%{
    public static JTextArea outputArea;
    public static ArrayList<String> lexErrors = new ArrayList<String>();

    public void printOutput(String outputLine){
            if(outputArea != null){
                    outputArea.append(outputLine + "\n");
            } 
            lexErrors.add(outputLine);
    }
    public Symbol symbol(int type){
        return new Token(type, yyline+1, yycolumn+1, yytext());
    }
    public Symbol symbol(int type, Object value){
        return new Token(type, yyline+1, yycolumn+1, yytext(), value);
    }
%}

%eofval{
    return symbol(sym.EOF);
%eofval}


//Values
digit = [0-9]+
boolean = (true|false)
character = \'(.|\s)\'
string = \"(.|\s|\t|\n)*\"
float = {digit}(\.[0.-9]{1,2})?
whitespace = [\s|\t\|\r|\n]+

//Types and Assignment
id = [a-zA-Z|_]+({digit}([a-zA-Z|_]+)?)*
assign = (=)
number = num
binary = bin
decimal = dec
symbol = sym
cadena = str

terminal = \;

//Flow Operators
repetition = rep
repetitionEnd = \/rep

condition = con
conditionEnd = \/con
yet = yet
yetend = \/yet

set = set
setEnd = \/set
option = opt
optionEnd = \/opt
any = any
anyEnd = \/any

til = til
tilEnd = \/til

//Functions Main
main = run
mainEnd = \/run
act = act
actEnd = \/act
return = ret
void = nil

//Math Operators
plus = \+
minus = \-
mult = \*
div = \/
mod = \%

//Logic Operators
and = and
or = or
lessThan = <
greaterThan = >
lessEqualThan = <=
greaterEqualThan = ">="
equal = (==)
unEqual = (\!=)
not = [not|!]

//General Operators
this = @
dot = \.
comma = \,
commentStart = #\*
commentEnd = \*#
parIzq = \(
parDer = \)
braketIzq = \[
braketDer = \]
include = inc

%state COMMENTS

%%

<YYINITIAL>
{
	{number} 			{return symbol(sym.NUM);}
	{binary} 			{return symbol(sym.BIN);}
	{decimal} 			{return symbol(sym.DEC);}
	{symbol} 			{return symbol(sym.SYM);}
	{cadena}			{return symbol(sym.STR);}
	{assign}			{return symbol(sym.ASSIGN);}

        {terminal} 			{return symbol(sym.END);}

	{repetition}                    {return symbol(sym.REP);}
	{repetitionEnd}                 {return symbol(sym.REPEND);}

	{condition}                     {return symbol(sym.COND);}
	{conditionEnd}                  {return symbol(sym.CONDEND);}
	{yet} 				{return symbol(sym.YET);}
        {yetend}                        {return symbol(sym.YETEND);}
	
	{set} 				{return symbol(sym.SET);}
	{setEnd} 			{return symbol(sym.SETEND);}
	{option} 			{return symbol(sym.OPT);}
        {optionEnd}                     {return symbol(sym.OPTEND);}
	{any}				{return symbol(sym.ANY);}
        {anyEnd}                        {return symbol(sym.ANYEND);}

	{til}				{return symbol(sym.TIL);}
	{tilEnd}			{return symbol(sym.TILEND);}
	
	{main} 				{return symbol(sym.MAIN);}
	{mainEnd}		 	{return symbol(sym.MAINEND);}

        {act} 				{return symbol(sym.ACT);}
	{actEnd} 			{return symbol(sym.ACTEND);}
	{return} 			{return symbol(sym.RET);}

	{void} 				{return symbol(sym.NIL);}

	{plus}				{return symbol(sym.PLUS);}
	{minus}				{return symbol(sym.MINUS);}
	{mult}				{return symbol(sym.MULT);}
	{div}				{return symbol(sym.DIV);}
	{mod}				{return symbol(sym.MOD);}

	{and}				{return symbol(sym.AND);}
	{or}				{return symbol(sym.OR);}
	{lessThan}			{return symbol(sym.LESSTHAN);}
	{greaterThan}                   {return symbol(sym.GREATERTHAN);}
	{lessEqualThan}                 {return symbol(sym.LESSEQUALTHAN);}
	{greaterEqualThan}              {return symbol(sym.GREATEREQUALTHAN);}
	{equal}				{return symbol(sym.EQUAL);}
	{unEqual}			{return symbol(sym.NOTEQUAL);}
	{not}				{return symbol(sym.NOT);}

	{comma}				{return symbol(sym.COMMA);}
        {parIzq}			{return symbol(sym.PARIZQ);}
	{parDer}			{return symbol(sym.PARDER);}

	{commentStart}                  {yybegin(COMMENTS);}

	{digit}				{return symbol( sym.DIGIT, new Integer(yytext()) );}
	{boolean}			{return symbol( sym.BOOL, new Boolean(yytext()) );}
	{string}			{return symbol( sym.STRING, new String(yytext()) );}
	{character}			{return symbol( sym.CHAR, new Character(yytext().charAt(1)) );}
	{float}				{return symbol( sym.FLOAT, new Float(yytext()) );}

	{id}				{return symbol(sym.ID, new String(yytext()));}
	{whitespace}                    {}
	. 				{this.printOutput("> Unknown Token: '"+yytext()+"' in Line "+Integer.toString(yyline)+" Column "+Integer.toString(yycolumn) );}

} 
<COMMENTS>{
	{commentEnd}            {yybegin(YYINITIAL);}
        .|{whitespace}		{}
}