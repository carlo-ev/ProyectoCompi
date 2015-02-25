%%
%class LenguajeCompi
%unicode
%line
%column
%int

//Values
digit = [0-9]+
boolean = (true|false)
character = '(.|\s)'
string = "(.|\s|\t|\n)*"
float = {digit}(\.[0.-9]{1,2})?
whitespace = [\s|\t\|\r|\n]+

//Types and Assignment
id = [a-zA-Z|_]+({digit}([a-zA-Z|_]+)?)*
assign = (=)
number = num
binary = bin
decimal = dec
symbol = sym
terminal = \~

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
then = \->
any = any
//Option any is the "default"

til = til
tilEnd = \/til
//for the while cycle?

//Functions Main
deusexmachina = deusExMachina
deusexmachinaEnd = \/deusExMachina
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


%state COMMENTS

%%

<YYINITIAL>
{
	{number} 			{System.out.print("INTEGER ");}
	{binary} 			{System.out.print("BOOLEAN ");}
	{decimal} 			{System.out.print("FLOAT ");}
	{symbol} 			{System.out.print("CHARACTER ");}
	{assign}			{System.out.print("ASSIGN ");}
	{terminal} 			{System.out.println("END OF STATEMENT");}

	{repetition} 		{System.out.print("FOR ");}
	{repetitionEnd} 	{System.out.println("END OF FOR");}
	{out} 				{System.out.print("BREAK ");}

	{condition} 		{System.out.print("IF ");}
	{conditionEnd} 		{System.out.println("END OF IF");}
	{yet} 				{System.out.print("ELSE ");}
	{yetEnd} 			{System.out.print("END OF ELSE ");}
	
	{set} 				{System.out.print("SWITCH ");}
	{setEnd} 			{System.out.println("END OF SWITCH");}
	{option} 			{System.out.print("CASE ");}
	{optionEnd} 		{System.out.println("END OF CASE");}
	{then}				{System.out.print("THEN ");}
	{any}				{System.out.print("DEFAULT ");}

	{til}				{System.out.print("WHILE ");}
	{tilEnd}			{System.out.print("WHILE END ");}
	
	{deusexmachina} 	{System.out.println("MAIN FUNCTION ");}
	{deusexmachinaEnd} 	{System.out.println("END OF MAIN FUNCTION");}
	{act} 				{System.out.print("FUNCTION ");}
	{actEnd} 			{System.out.println("END OF FUNCTION");}
	{return} 			{System.out.print("RETURN ");}
	{void} 				{System.out.print("VOID ");}

	{plus}				{System.out.print("+ ");}
	{minus}				{System.out.print("- ");}
	{mult}				{System.out.print("* ");}
	{div}				{System.out.print("/ ");}
	{mod}				{System.out.print("% ");}

	{and}				{System.out.print("AND ");}
	{or}				{System.out.print("OR ");}
	{lessThan}			{System.out.print("< ");}
	{greaterThan}		{System.out.print("> ");}
	{lessEqualThan}		{System.out.print("<= ");}
	{greaterEqualThan}	{System.out.print(">= ");}
	{equal}				{System.out.print("== ");}
	{unEqual}			{System.out.print("!= ");}
	{not}				{System.out.print("NOT ");}
	

	{dot}				{System.out.print(" . ");}
	{comma}				{System.out.print(" , ");}
	{parIzq}			{System.out.print(" ( ");}
	{parDer}			{System.out.print(" ) ");}
	{braketIzq}			{System.out.print(" [ ");}
	{braketDer}			{System.out.print(" ] ");}
	{commentStart}		{System.out.println(" COMMENT BLOCK"); yybegin(COMMENTS);}

	{digit}				{System.out.print("Number("+ yytext() +") ");}
	{boolean}			{System.out.print("Boolean("+ yytext() +") ");}
	{string}			{System.out.print("String("+ yytext() +") ");}
	{character}			{System.out.print("Character("+ yytext() +") ");}
	{float}				{System.out.print("Float(" +yytext() +") ");}
	
	{id}				{System.out.print("IDENTIFIER("+ yytext() +") ");}

	{whitespace}		{}
	. 					{System.err.println("Unknown Token: "+yytext()+" Line "+Integer.toString(yyline)+" Column "+Integer.toString(yycolumn));}
} 
<COMMENTS>{
	{commentEnd}	{System.out.println("COMMENT BLOCK END"); yybegin(YYINITIAL);}
	.|{whitespace}		{System.out.print(yytext());}
}