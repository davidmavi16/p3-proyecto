package es.unizar.eina.vv6f.practica3;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.junit.Assert.*;

/**
 * Created by davidmavi on 21/4/17.
 */
public class MainTest {
    static InputStream in;
    static OutputStream out;

    @BeforeClass
    public static void guardarRef() {
        in = System.in;
        out = System.out;
    }

    @BeforeClass
    public static void localiza() throws IOException{
        new File("Temp").mkdir();
        new File("Temp/auxHamlet.txt").createNewFile();
        new File("Temp/auxQuijote.txt").createNewFile();
        new File("Temp/auxRegenta.txt").createNewFile();
        new File("Temp/auxVacio.txt").createNewFile();
        new File("Temp/auxInexistente.txt").createNewFile();

        Files.copy(Paths.get("src/test/res/hamlet.txt"),
                Paths.get("Temp/auxHamlet.txt"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get("src/test/res/quijote.txt"),
                Paths.get("Temp/auxQuijote.txt"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get("src/test/res/regenta.txt"),
                Paths.get("Temp/auxRegenta.txt"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get("src/test/res/vacio.txt"),
                Paths.get("Temp/auxVacio.txt"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get("src/test/res/inexistente.txt"),
                Paths.get("Temp/auxInexistente.txt"), StandardCopyOption.REPLACE_EXISTING);
    }

    @AfterClass
    public static void recuperarRef(){
        System.setIn(in);
        System.setOut((PrintStream)out);
    }

    @AfterClass
    public static void borrarTemp(){
        File directorio = new File("Temp");
        for(File file:directorio.listFiles()){
            file.delete();
        }
        directorio.delete();
    }
}