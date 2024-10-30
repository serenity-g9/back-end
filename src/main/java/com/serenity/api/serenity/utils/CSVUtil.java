package com.serenity.api.serenity.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.util.List;

public class CSVUtil {

    public static String exportar(List<?> objetos) {
        try (FileWriter writer = new FileWriter( System.currentTimeMillis() + ".csv")) {
            String csv = paraCsv(objetos);
            writer.append(csv);
            return csv;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String paraCsv(List<?> objetos) {

        StringBuilder sb = new StringBuilder();
        Object primeiroObjeto = objetos.get(0);

        RecordComponent[] atributos = primeiroObjeto.getClass().getRecordComponents();

        for (int i = 0; i < atributos.length; i++) {
            sb.append(toSnakeCase(atributos[i].getName()));
            if (i < atributos.length - 1) sb.append(";");
        }
        sb.append("\n");

        for (Object objeto : objetos) {
            for (int i = 0; i < atributos.length; i++) {
                Object value;

                try {
                    Method getter = primeiroObjeto.getClass().getMethod(atributos[i].getName());
                    value = getter.invoke(objeto);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }

                if (value instanceof String) {
                    sb.append(String.format("%s", value));
                } else if (value instanceof Integer) {
                    sb.append(String.format("%d", value));
                } else if (value instanceof Double) {
                    sb.append(String.format("%.2f", value));
                } else {
                    sb.append(String.format("%s", value));
                }

                if (i < atributos.length - 1) sb.append(";");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static String toSnakeCase(String input) {
        input = Character.toLowerCase(input.charAt(0)) + input.substring(1);

        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append('_').append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
