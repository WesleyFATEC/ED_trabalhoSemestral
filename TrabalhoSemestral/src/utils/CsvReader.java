package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {
    public static int contarLinhas(String arquivo) {
        int linhas = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            while (br.readLine() != null) {
                linhas++;
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return linhas;
    }
}
