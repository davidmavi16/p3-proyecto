package es.unizar.eina.vv6f.practica3;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by davidmavi on 28/3/17.
 */
public class AnalizadorLiteralTest {

    @Test
    public void ficheroExistente() throws IOException {
        String archivo = "src/main/res/regenta.txt";
        File f = new File(archivo);
        Scanner sc = new Scanner(f);
        int [] resultado = new int[27];
        int [] esperado = {195175,26862,53217,68273,178917,8620,15404,13913,87589,6505,61,84711,37053,88950,129829,34482,
                17977,90627,102937,54752,58427,15717,19,1240,13826,5632,3534};
        AnalizadorLiteral AL = new AnalizadorLiteral(f);
        resultado = AL.analizar();
        assertArrayEquals(resultado,esperado);

    }

    @Test
    public void ficheroNoExistente() throws FileNotFoundException {
            String archivo = "NoExiste";
            File f = new File(archivo);
            AnalizadorLiteral AL = new AnalizadorLiteral(f);
            try{
                int frec[] = AL.analizar();
                fail("Se esperaba una FileNotFoundException pero no se ha capturado");
            }
            catch (FileNotFoundException fn){
                //Esperado
            }

    }

    @Test
    public void ficheroVacio() throws FileNotFoundException {
        String archivo = "src/main/res/vacio.txt";
        File f = new File(archivo);
        int [] resultado = new int[27];
        int [] esperado = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        AnalizadorLiteral AL = new AnalizadorLiteral(f);
        resultado = AL.analizar();
        assertArrayEquals(resultado,esperado);
    }

    @Test
    public void ficheroNulo() throws FileNotFoundException {
        File f = null;
        try{
            AnalizadorLiteral AL = new AnalizadorLiteral(f);
            int frec[] = AL.analizar();
            fail("Se esperaba una FileNotFoundException pero no se ha capturado");
        }
        catch (Exception e){
            //Esperado
        }

    }

}