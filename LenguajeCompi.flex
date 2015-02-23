%%
%class LenguajeCompi
%unicode
%line
%column
%int

digit = [0-9]+
boolean = (true|false)
whitespace = [\s|\t\|\r|\n]+
assign = (=)
number = num
binary = bin
decimal = dec
symbol = sym
repetition = rep
repetitionEnd = \/rep
condition = con
conditionEnd = \/con
yet = yet
yetEnd = \/yet
terminal = \~
set = set
setEnd = \/set
option = opt
optionEnd = \/opt
out = out
deusexmachina = deusExMachina
deusexmachinaEnd = \/deusExMachina
act = act
actEnd = \/act
return = ret
void = nil
id = [a-zA-Z|_]+({digit}([a-zA-Z|_]+)?)*
commentBlock = #\*
commentBlockEnd = \*#
condOperators = [<|>|==|<=|>=|!=]
mathOperators = [\+|\*|\-|\/|\%]
parIzq = \(
parDer = \)
character = '(.|\s)'
float = {digit}(\.[0.-9]{1,2})?
then = \->

%state COMMENTS

%%

<YYINITIAL>
{
	{number} 			{System.out.print("INTEGER ");}
	{binary} 			{System.out.print("BOOLEAN ");}
	{decimal} 			{System.out.print("FLOAT ");}
	{symbol} 			{System.out.print("CHARACTER ");}
	{repetition} 		{System.out.print("FOR ");}
	{repetitionEnd} 	{System.out.println("END OF FOR");}
	{condition} 		{System.out.print("IF ");}
	{conditionEnd} 		{System.out.println("END OF IF");}
	{yet} 				{System.out.print("ELSE ");}
	{yetEnd} 			{System.out.print("END OF ELSE ");}
	{terminal} 			{System.out.println("END OF STATEMENT");}
	{set} 				{System.out.print("SWITCH ");}
	{setEnd} 			{System.out.println("END OF SWITCH");}
	{option} 			{System.out.print("CASE ");}
	{then}				{System.out.print("THEN ");}
	{optionEnd} 		{System.out.println("END OF CASE");}
	{out} 				{System.out.print("BREAK ");}
	{deusexmachina} 	{System.out.println("MAIN FUNCTION ");}
	{deusexmachinaEnd} 	{System.out.println("END OF MAIN FUNCTION");}
	{act} 				{System.out.print("FUNCTION ");}
	{actEnd} 			{System.out.println("END OF FUNCTION");}
	{return} 			{System.out.print("RETURN ");}
	{void} 				{System.out.print("VOID ");}
	{digit}				{System.out.print("Number("+ yytext() +") ");}
	{boolean}			{System.out.print("Boolean("+ yytext() +") ");}
	{id}				{System.out.print("IDENTIFIER("+ yytext() +") ");}
	{character}			{System.out.print("Character("+ yytext() +") ");}
	{float}				{System.out.print("Float(" +yytext() +") ");}
	{assign}			{System.out.print("ASSIGN ");}
	{parIzq}			{System.out.print(" ( ");}
	{parDer}			{System.out.print(" ) ");}
	{mathOperators}		{System.out.print("MATHOPERATOR("+ yytext() +") ");}
	{condOperators}		{System.out.print("CONDOPERATOR("+ yytext() +") ");}
	{commentBlock}		{System.out.println(" COMMENT BLOCK"); yybegin(COMMENTS);}
	.|{whitespace} 		{}
} 
<COMMENTS>{
	{commentBlockEnd}	{System.out.println("COMMENT BLOCK END"); yybegin(YYINITIAL);}
	.|{whitespace}		{System.out.print(yytext());}
}