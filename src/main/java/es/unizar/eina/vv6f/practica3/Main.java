package es.unizar.eina.vv6f.practica3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Programa Java que, al iniciar su ejecución, solicita al usuario el nombre
 * de un fichero de texto. Una vez que el usuario lo ha introducido, se
 * muestra en la pantalla una lista de las letras del alfabeto español y el
 * número de veces que dicha letra aparece en el fichero introducido.
 * 
 * No se distingue entre mayúsculas y minúsculas. La letra Ñ es una letra en
 * español. El resto de apariciones de caracteres con diacríticos (acentos
 * agudos, graves, diéresis, cedillas y voladas), se consideran como
 * ocurrencias de
 * la letra correspondiente sin diacríticos.
 */
public class Main {

	private static final String FORMATO_SALIDA_FRECUENCIAS = "%c: %7d%n";

	/**
	 * Método que, al iniciar su ejecución, solicita al usuario el nombre de
	 * un fichero de texto. Una vez que el usuario lo ha introducido, se
	 * muestra en la pantalla una lista de las letras del alfabeto español y
	 * el número de veces que dicha letra aparece en el fichero introducido.
	 * 
	 * No se distingue entre mayúsculas y minúsculas. La letra Ñ es una
	 * letra en español. El resto de apariciones de caracteres con
	 * diacríticos (acentos agudos, graves, diéresis y cedillas), se
	 * consideran como ocurrencias de la letra correspondiente sin
	 * diacríticos.
	 * 
	 * @param args
	 *            no utilizado.
	 */
	public static void main(String[] args) throws IOException {
		System.out.print("Nombre de un fichero de texto: ");
        Scanner sc = new Scanner(System.in);
        String archivo = sc.next();
        try{
            File file = new File(archivo);

            AnalizadorLiteral aL = new AnalizadorLiteral(file);
            int[] frec = aL.analizar();
            char c = 65;
            for(int i = 0; i<14;i++){
                System.out.printf("%c: %8d\n", c, frec[i]);
                c++;
            }
            System.out.printf("Ñ: %8d\n", frec[frec.length-1]);
            for(int i = 14; i<frec.length-1;i++){
                System.out.printf("%c: %8d\n", c, frec[i]);
                c++;
            }
        }
        catch(FileNotFoundException f){
        }
	}
}
