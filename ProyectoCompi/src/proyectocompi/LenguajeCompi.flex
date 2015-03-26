
package proyectocompi;

import java.util.ArrayList;
import javax.swing.JTextArea;
import java_cup.runtime.*;
%%
%class LenguajeCompi
%unicode
%line
%column
%int
%cup

//Values
digit = [0-9]+
boolean = (true|false)
character = '(.|\s)'
string = "(.|\s|\t|\n)*"
float = {digit}(\.[0.-9]{1,2})?
whitespace = [\s|\t\|\r|\n]+

//Types and Assignment
id = [a-zA-Z|_|@|#]+({digit}([a-zA-Z|_|@|#]+)?)*
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
out = out

condition = con
conditionEnd = \/con
yet = yet
yetend = \/yet

set = set
setEnd = \/set
option = opt
any = any

then = \>>


til = til
tilEnd = \/til
//for the while cycle?

//Functions Main
main = run
mainEnd = \/run
act = act
actEnd = \/act
return = ret
void = nil

deusexmachina = deusExMachina
deusexmachinaEnd = \/deusExMachina

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
greaterEqualThan = >=
equal = (==)
unEqual = (\!=)
not = [not|!]

//General Operators
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
	public JTextArea outputArea;
	public ArrayList<String> lexErrors = new ArrayList<String>();

	public void setOutputArea(JTextArea output){
		this.outputArea = output;
	}

	public void printOutput(String outputLine){
		if(this.outputArea != null){
			this.outputArea.append(outputLine + "\n");
		} 
		this.lexErrors.add(outputLine);
	}

	public ArrayList getLexErrors(){
		return this.lexErrors;
	}
%}

%eofval{
    return new Symbol(sym.EOF);
%eofval}

%%

<YYINITIAL>
{

	
/*
	{number} 			{return new symbol(Sym.NUM);}
	{binary} 			{return new Symbol(Sym.BIN);}
	{decimal} 			{return new Symbol(Sym.DEC);}
	{symbol} 			{return new Symbol(Sym.SYM);}
	{cadena}			{return new Symbol(Sym.STR);}
	{assign}			{return new Symbol(Sym.ASSIGN);}
*/
        {terminal} 			{return new Symbol(sym.END);}

	{repetition}                    {return new Symbol(sym.REP);}
	{repetitionEnd}                 {return new Symbol(sym.REPEND);}
	{out} 				{return new Symbol(sym.OUT);}

	{condition}                     {return new Symbol(sym.COND);}
	{conditionEnd}                  {return new Symbol(sym.CONDEND);}
	{yet} 				{return new Symbol(sym.YET);}
        {yetend}                        {return new Symbol(sym.YETEND);}
	
	{set} 				{return new Symbol(sym.SET);}
	{setEnd} 			{return new Symbol(sym.SETEND);}
	{option} 			{return new Symbol(sym.OPT);}
	{then}				{return new Symbol(sym.THEN);}
	{any}				{return new Symbol(sym.ANY);}

	{til}				{return new Symbol(sym.TIL);}
	{tilEnd}			{return new Symbol(sym.TILEND);}
	
	{main} 				{return new Symbol(sym.MAIN);}
	{mainEnd}		 	{return new Symbol(sym.MAINEND);}
/*	
        {act} 				{return new Symbol(Sym.ACT);}
	{actEnd} 			{return new Symbol(Sym.ACTEND);}
	{return} 			{return new Symbol(Sym.RET);}
	{void} 				{return new Symbol(Sym.NIL);}

	{plus}				{return new Symbol(Sym.PLUS);}
	{minus}				{return new Symbol(Sym.MINUS);}
	{mult}				{return new Symbol(Sym.MULT);}
	{div}				{return new Symbol(Sym.DIV);}
	{mod}				{return new Symbol(Sym.MOD);}
*/
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
	{dot}				{return new Symbol(Sym.DOT);}
	{comma}				{return new Symbol(Sym.COMMA);}
*/
        {parIzq}			{return new Symbol(sym.PARIZQ);}
	{parDer}			{return new Symbol(sym.PARDER);}
/*
        {braketIzq}			{return new Symbol(Sym.BRAIZQ);}
	{braketDer}			{return new Symbol(Sym.BRADER);}
	{commentStart}                  {yybegin(COMMENTS);}
	{include}			{return new Symbol(Sym.INCLUDE);}
*/
//	{digit}				{return new Symbol( sym.DIGIT, Integer.parseInt(yytext()) );}
	{boolean}			{return new Symbol( sym.BOOL, new Boolean(yytext()) );}
//	{string}			{return new Symbol( sym.STRING, new String(yytext()) );}
//	{character}			{return new Symbol( sym.CHAR, yytext().charAt(0) );}
//	{float}				{return new Symbol( sym.FLOAT, new Float(yytext()) );}

	{id}				{return new Symbol(sym.ID, yytext());}
	{whitespace}		{}
	. 					{this.printOutput("Unknown Token: "+yytext()+" in Line "+Integer.toString(yyline)+" Column "+Integer.toString(yycolumn) );}

} 
<COMMENTS>{
	{commentEnd}|.|{whitespace}		{}
}