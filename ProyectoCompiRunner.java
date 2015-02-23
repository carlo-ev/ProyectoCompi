import java.io.*;

public class ProyectoCompiRunner{
	public static void main(String args[]) throws FileNotFoundException {
		try{
			LenguajeCompi lex = new LenguajeCompi(new FileReader(args[0]));
			lex.yylex();
		}catch(Exception ex){}
	}
}