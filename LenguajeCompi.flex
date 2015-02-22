%%
%class lenguajeCompi
%unicode
%line
%column
%int

id = [a-zA-Z]+
digit = [0-9]+
boolean = (true|false|1|0)
whitespace = (\s)*
end = ~
assign = (=)
number = num
binary = bin
decimal = dec
symbol = sym
repetition = rep
repetitionEnd = /rep
condition = con
conditionEnd = /con
yet = yet
yetEnd = /yet
terminal = ~
set = set
setEnd = /end
option = opt
optionEnd = /opt
out = out
deusexmachina = deusExMachina
deusexmachinaEnd = /deusExMachina
act = act
actEnd = /act
return = ret
void = nil

%%

<YYINITIAL>
{
	{number} {System.out.println("AN INTEGER");}
	{binary} {System.out.println("A BOOLEAN");}
	{decimal} {System.out.println("A FLOAT");}
	{symbol} {System.out.println("A CHARACTER");}
	{repetition} {System.out.println("A FOR CYCLE");}
	{repetitionEnd} {System.out.println("END OF FOR CYCLE");}
	{condition} = {System.out.println("AN IF STATEMENT");}
	{conditionEnd} = {System.out.println("END OF IF STATEMENT");}
	{yet} = {System.out.println("AN ELSE STATEMENT");}
	{yetEnd} = {System.out.println("END OF ELSE STATEMENT");}
	{terminal} = {System.out.println("END OF STATEMENT");}
	{set} = {System.out.println("A SWITCH STATEMENT");}
	{setEnd} = {System.out.println("END OF SWITCH STATEMENT");}
	{option} = {System.out.println("A CASE STATEMENT");}
	{optionEnd} = {System.out.println("END OF CASE STATEMENT");}
	{out} = {System.out.println("FORCED END OF STATEMENT");}
	{deusexmachina} = {System.out.println("MAIN FUNCTION");}
	{deusexmachinaEnd} = {System.out.println("END OF MAIN FUNCTION");}
	{act} = {System.out.println("A FUNCTION");}
	{actEnd} = {System.out.println("END OF FUNCTION");}
	{return} = {System.out.println("RETURN STATEMENT");}
	{void} = {System.out.println("A VOID STATEMENT");}
	. {}
} 