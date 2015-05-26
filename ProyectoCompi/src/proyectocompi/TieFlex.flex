
package proyectocompi;

import java.util.ArrayList;
import javax.swing.JTextArea;
import java_cup.runtime.*;
%%
%class TieLexer
%unicode
%line
%column
%int
%extends sym
%cup

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
    return new Symbol(sym.EOF);
%eofval}

%%

<YYINITIAL>
{
	{number} 			{return new Symbol(sym.NUM);}
	{binary} 			{return new Symbol(sym.BIN);}
	{decimal} 			{return new Symbol(sym.DEC);}
	{symbol} 			{return new Symbol(sym.SYM);}
	{cadena}			{return new Symbol(sym.STR);}
	{assign}			{return new Symbol(sym.ASSIGN);}

        {terminal} 			{return new Symbol(sym.END);}

	{repetition}                    {return new Symbol(sym.REP);}
	{repetitionEnd}                 {return new Symbol(sym.REPEND);}

	{condition}                     {return new Symbol(sym.COND);}
	{conditionEnd}                  {return new Symbol(sym.CONDEND);}
	{yet} 				{return new Symbol(sym.YET);}
        {yetend}                        {return new Symbol(sym.YETEND);}
	
	{set} 				{return new Symbol(sym.SET);}
	{setEnd} 			{return new Symbol(sym.SETEND);}
	{option} 			{return new Symbol(sym.OPT);}
	{any}				{return new Symbol(sym.ANY);}

	{til}				{return new Symbol(sym.TIL);}
	{tilEnd}			{return new Symbol(sym.TILEND);}
	
	{main} 				{return new Symbol(sym.MAIN);}
	{mainEnd}		 	{return new Symbol(sym.MAINEND);}

        {act} 				{return new Symbol(sym.ACT);}
	{actEnd} 			{return new Symbol(sym.ACTEND);}
	{return} 			{return new Symbol(sym.RET);}

	{void} 				{return new Symbol(sym.NIL);}

	{plus}				{return new Symbol(sym.PLUS);}
	{minus}				{return new Symbol(sym.MINUS);}
	{mult}				{return new Symbol(sym.MULT);}
	{div}				{return new Symbol(sym.DIV);}
	{mod}				{return new Symbol(sym.MOD);}

	{and}				{return new Symbol(sym.AND);}
	{or}				{return new Symbol(sym.OR);}
	{lessThan}			{return new Symbol(sym.LESSTHAN);}
	{greaterThan}                   {return new Symbol(sym.GREATERTHAN);}
	{lessEqualThan}                 {return new Symbol(sym.LESSEQUALTHAN);}
	{greaterEqualThan}              {return new Symbol(sym.GREATEREQUALTHAN);}
	{equal}				{return new Symbol(sym.EQUAL);}
	{unEqual}			{return new Symbol(sym.NOTEQUAL);}
	{not}				{return new Symbol(sym.NOT);}
/*
        {this}                          {return new Symbol(sym.THIS);}
*/
	{comma}				{return new Symbol(sym.COMMA);}
        {parIzq}			{return new Symbol(sym.PARIZQ);}
	{parDer}			{return new Symbol(sym.PARDER);}

	{commentStart}                  {yybegin(COMMENTS);}

	{digit}				{return new Symbol( sym.DIGIT, new Integer(yytext()) );}
	{boolean}			{return new Symbol( sym.BOOL, new Boolean(yytext()) );}
	{string}			{return new Symbol( sym.STRING, new String(yytext()) );}
	{character}			{return new Symbol( sym.CHAR, new Character(yytext().charAt(0)) );}
	{float}				{return new Symbol( sym.FLOAT, new Double(yytext()) );}

	{id}				{return new Symbol(sym.ID);}
	{whitespace}                    {}
	. 				{this.printOutput("> Unknown Token: '"+yytext()+"' in Line "+Integer.toString(yyline)+" Column "+Integer.toString(yycolumn) );}

} 
<COMMENTS>{
	{commentEnd}            {yybegin(YYINITIAL);}
        .|{whitespace}		{}
}