package com.serenity.api.serenity.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

public class CSVUtil {

    public static void exportar(List<?> objetos) {
        try (FileWriter writer = new FileWriter( System.currentTimeMillis() + ".csv")) {

            for (Object objeto : objetos) {
                writer.append(getCabecalho(objeto));
                writer.append(objeto.toString());
            }

            System.out.println("Dados exportados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao exportar dados: " + e.getMessage());
        }
    }

    public static String getCabecalho(Object object) {
        Class<?> classe = object.getClass();
        Method[] metodos = classe.getDeclaredMethods();
        StringBuilder sb = new StringBuilder();

        StringBuilder cabecalho = new StringBuilder();
        for (int i = 0; i < metodos.length; i++) {
            Method method = metodos[i];

            if (method.getName().startsWith("get") && method.getParameterCount() == 0) {
                String atributo = method.getName().substring(3);
                atributo = Character.toLowerCase(atributo.charAt(0)) + atributo.substring(1);

                for (char c : atributo.toCharArray()) {
                    if (Character.isUpperCase(c)) sb.append('_').append(Character.toLowerCase(c));
                    else sb.append(c);
                }

                String delimitador = (i < metodos.length-1 ? "," : "\n");
                cabecalho.append(atributo).append(delimitador);
            }
        }

        return cabecalho.toString();
    }
}
