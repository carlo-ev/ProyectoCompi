# TIE
TIE is simple programming language for general purpose that for now covers the most
primary condition, flow and expresion operations.
TIE es un simple lenguaje de programacion de proposito general que actualmente solamente soporta los operadores de Flujo: IF, FOR, WHILE, SWITCH; al igual que la evalcion de condiciones y declaraciones simples.

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

	rep(num i=0;i<10;i=i+1;)
		numero = numero + i;
	/rep

	set(tucara)
		opt 15
			dec deci1 = 33.2;
		/opt 
		any
			dec deci1 = 0;
	/set

	bin act funcion 
		sym algo = 'a';
		con(deci1 == 0)
			bin flag = 1;
		yet
			bin flag = 0;
		/con

		ret flag;
	/act

#*
	Comentario en Bloques
	con(booleano algo)
		bin algo = 1;
	/con
*#

/run
```