package analizadorsintactico;

import java.io.*;
import java.util.*;

public class AnalizadorSintactico {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java analizadorlexico.AnalizadorSintactico <ruta-archivo.json>");
            return;
        }
        String filePath = args[0];
        try {
            Lexer lexer = new Lexer(filePath);
            List<Token> tokens = lexer.tokenizeFile();

            //fase 1: analisis lexico - muestra los tokens reconocidos
            System.out.println("=== Análisis Léxico ===");
            lexer.printTokens(tokens, 0);
            System.out.println();

            //fase 2: analisis sintactico - valida la estructura
            System.out.println("=== Análisis Sintáctico ===");
            Parser parser = new Parser(tokens);
            parser.parse();

            if (parser.isValid()) {
                System.out.println("El archivo es sintácticamente válido.");
            } else {
                System.out.println("Se encontraron errores sintácticos:");
                for (String e : parser.getErrors()) {
                    System.out.println("- " + e);
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
        }
    }
}
