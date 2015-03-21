
package ProyectoCompi;

import java.util.ArrayList;
import javax.swing.JTextArea;
import java_cup.runtime.Symbol;
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
yetEnd = \/yet

set = set
setEnd = \/set
option = opt
optionEnd = \/opt
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

%%

<YYINITIAL>
{
/*
	{number} 			{return new Symbol(Sym.NUM);}
	{binary} 			{return new Symbol(Sym.BIN);}
	{decimal} 			{return new Symbol(Sym.DEC);}
	{symbol} 			{return new Symbol(Sym.SYM);}
	{cadena}			{return new Symbol(Sym.STR);}
	{assign}			{return new Symbol(Sym.ASSIGN);}
	{terminal} 			{return new Symbol(Sym.END);}

	{repetition} 		{return new Symbol(Sym.REP);}
	{repetitionEnd} 	{return new Symbol(Sym.REPEND);}
	{out} 				{return new Symbol(Sym.OUT);}

	{condition} 		{return new Symbol(Sym.COND);}
	{conditionEnd} 		{return new Symbol(Sym.CONDEND);}
	{yet} 				{return new Symbol(Sym.YET);}
	{yetEnd} 			{return new Symbol(Sym.YETEND);}
	
	{set} 				{return new Symbol(Sym.SET);}
	{setEnd} 			{return new Symbol(Sym.SETEND);}
	{option} 			{return new Symbol(Sym.OPT);}
	{optionEnd} 		{return new Symbol(Sym.OPTEND);}
	{then}				{return new Symbol(Sym.THEN);}
	{any}				{return new Symbol(Sym.ANY);}

	{til}				{return new Symbol(Sym.TIL);}
	{tilEnd}			{return new Symbol(Sym.TILEND);}
*/	
	{main} 				{return new Symbol(sym.MAIN);}
	{mainEnd}		 	{return new Symbol(sym.MAINEND);}
/*	{act} 				{return new Symbol(Sym.ACT);}
	{actEnd} 			{return new Symbol(Sym.ACTEND);}
	{return} 			{return new Symbol(Sym.RET);}
	{void} 				{return new Symbol(Sym.NIL);}

	{plus}				{return new Symbol(Sym.PLUS);}
	{minus}				{return new Symbol(Sym.MINUS);}
	{mult}				{return new Symbol(Sym.MULT);}
	{div}				{return new Symbol(Sym.DIV);}
	{mod}				{return new Symbol(Sym.MOD);}

	{and}				{return new Symbol(Sym.AND);}
	{or}				{return new Symbol(Sym.OR);}
	{lessThan}			{return new Symbol(Sym.LESSTHAN);}
	{greaterThan}		{return new Symbol(Sym.GREATERTHAN);}
	{lessEqualThan}		{return new Symbol(Sym.LESSEQUALTHAN);}
	{greaterEqualThan}	{return new Symbol(Sym.GREATEREQUALTHAN);}
	{equal}				{return new Symbol(Sym.EQUAL);}
	{unEqual}			{return new Symbol(Sym.NOTEQUAL);}
	{not}				{return new Symbol(Sym.NOT);}

	{dot}				{return new Symbol(Sym.DOT);}
	{comma}				{return new Symbol(Sym.COMMA);}
	{parIzq}			{return new Symbol(Sym.PARIZQ);}
	{parDer}			{return new Symbol(Sym.PARDER);}
	{braketIzq}			{return new Symbol(Sym.BRAIZQ);}
	{braketDer}			{return new Symbol(Sym.BRADER);}
	{commentStart}		{yybegin(COMMENTS);}
	{include}			{return new Symbol(Sym.INCLUDE);}

	{digit}				{return new Symbol(Sym.DIGIT, yytext());}
	{boolean}			{return new Symbol(Sym.BOOL);}
	{string}			{return new Symbol(Sym.STRING, yytext());}
	{character}			{return new Symbol(Sym.CHAR, yytext());}
	{float}				{return new Symbol(Sym.FLOAT, yytext());}
	
	{id}				{return new Symbol(Sym.ID, yytext());}
*/
	{whitespace}		{}
	. 					{printOutput("Unknown Token: "+yytext()+" in Line "+Integer.toString(yyline)+" Column "+Integer.toString(yycolumn) );}
} 
<COMMENTS>{
	{commentEnd}|.|{whitespace}		{}
}