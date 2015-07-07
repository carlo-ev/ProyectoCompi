# TIE
TIE is a simple programming language for general purpose that for now covers the most
primary condition, flow and expresion operations.
TIE es un simple lenguaje de programacion de proposito general que actualmente solamente soporta los operadores de Flujo: IF, FOR, WHILE, SWITCH; al igual que la evaluacion de condiciones y declaraciones simples.

TIE es el Proyecto Final para la Clase de Compiladores I y Compiladores II de la carrera de Ingenieria en Sistemas Computaciones en la Universidad Tecnologica de Honduras (UNITEC) desarrollado por:
	
	- Carlo Espinal
	- Jorge Paz

##Programa Ejemplo

```
run
	num numero = 12;
	sym caracter = 'a';
	dec decimal = 12.00;
	bin booleano  = true;

	num i;
	# REP == FOR
	rep( i=0; i<10; i=i+1;)
		numero = numero + i;
	/rep

	# SET == SWITCH
	set(tucara)
		opt 15
			dec deci1 = 33.2;
		/opt 
		any
			dec deci1 = 0;
		/any
	/set

	# ACT == FUNCTION
	bin act funcion 
		sym algo = 'a';
		con(deci1 == 0)
			bin flag = 1;
		yet
			bin flag = 0;
		/con

		ret flag;
	/act

	# TIL == WHILE
	til(true)
		# Es posible utilizar variables y funciones previo a declararlas
		numero = calc(numero);
		numero = numero - 1;
		num act calc(num id)
			ret id * 2;
		/act
	/til

	# Es posible declarar variables y funciones en cualquier parte del codigo
	# Inlcuso dentro de bloques com lo son til, con, etc...
	# Aunque cada bloque mantiene un ambito individual

#*
	Comentario en Bloques
	CON == IF
	con(booleano algo)
		bin algo = 1;
	/con
*#

/run
```