package br.com.projetoFinal.bibliotecaGama.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validadores {
    public static boolean data(String data) {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            sdf.setLenient(false);
            // tento converter a String em um objeto do tipo date, se funcionar
            // sua data é valida
            sdf.parse(data);
            // se nada deu errado retorna true (verdadeiro)
            return true;
        } catch (ParseException ex) {
            // se algum passo dentro do "try" der errado quer dizer que sua data é falsa,
            // então,
            // retorna falso
            return false;
        }
    }
}
