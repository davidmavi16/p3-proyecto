package es.unizar.eina.vv6f.practica3;

import java.io.*;
import java.text.Normalizer;
import java.util.Scanner;

/**
 * Clase para el análisis de la frecuencia de aparición de letras del alfabeto
 * español en un fichero de texto. Los objetos de esta clase se construyen
 * utilizando como argumento un objeto de la clase File que representa el
 * fichero de texto que se quiere analizar. La primera invocación al método
 * analizar() analiza el contenido del fichero de texto y si se ha podido
 * procesar el fichero, siempre devuelve un vector de 27 componentes de tipo
 * entero. Las primeras 26 componentes almacenan el número de apariciones de
 * las 26 letras del alfabeto inglés. La última componente, almacena el número
 * de apariciones de la letra Ñ.
 *
 * No se distingue entre mayúsculas y minúsculas. La letra Ñ es una letra en
 * español. El resto de apariciones de caracteres con diacríticos (acentos
 * agudos, graves, diéresis, cedillas y voladas), se consideran como
 * ocurrencias de la letra correspondiente sin diacríticos.
 * 
 * @author Latre
 * 
 */
public class AnalizadorLiteral {
	private File file;
	private int[] frecuencias = null;

	/**
	 * Construye un AnalizadorLiteral para analizar la frecuencia en las que
	 * aparecen las letras del fichero f.
	 * @param f
	 *            fichero de texto cuyo contenido será analizado.
     */
	public AnalizadorLiteral(File f) {
		this.file = f;
	}


	/**
	 * Si no ha sido analizado ya, analiza el contenido del fichero de texto
	 * asociado a este analizador literal. Devuelve un vector de 27 componentes
	 * con las frecuencias absolutas de aparición de cada letra del alfabeto
	 * español en el fichero.
	 * 
	 * @return vector de 27 componentes de tipo entero. Las primeras 26
	 *         componentes almacenan el número de apariciones de las 26
	 *         letras del alfabeto inglés: la componente indexada por 0
	 *         almacena el número de apariciones de la letra A, la
	 *         componente indexada por 1, el de la letra B y así
	 *         sucesivamente. La última componente, almacena el número de
	 *         apariciones de la letra Ñ.
	 * @throws FileNotFoundException
	 *             si el fichero f no existe o no puede abrirse.
	 */
	public int[] analizar() throws FileNotFoundException {
        if (frecuencias == null) {
            frecuencias = new int[27];
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                for (int i = 0; i < linea.length(); i++) {
                    char ch = linea.charAt(i);
                    if (ch == 'Ñ' || ch == 'ñ') {
                        frecuencias[26]++;
                    } else if (ch == 'ª') {
                        frecuencias[0]++;
                    } else if (ch == 'º') {
                        frecuencias[14]++;
                    }
                }
                linea = linea.replaceAll("[ñÑºª]", "+");
                linea = Normalizer.normalize(linea, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
                for (int i = 0; i < linea.length(); i++) {
                    char ch = linea.charAt(i);
                    if (ch >= 'A' && ch <= 'Z') {
                        frecuencias[ch - 65]++;
                    } else if (ch >= 'a' && ch <= 'z') {
                        frecuencias[ch - 97]++;
                    }
                }
            }
        }
		return frecuencias;
	}
}
