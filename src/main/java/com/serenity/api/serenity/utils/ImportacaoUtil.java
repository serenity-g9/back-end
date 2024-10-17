package com.serenity.api.serenity.utils;

import java.io.FileWriter;
import java.io.IOException;

public class ImportacaoUtil {

    public static  <T> void exportarDados(T objeto) {
        try (FileWriter writer = new FileWriter(objeto+".csv")) {
            writer.append(objeto.toString());
            System.out.println("Dados exportados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao exportar dados: " + e.getMessage());
        }
    }
    //    public static <T> void importarDados() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Digite o nome do arquivo a ser importado:");
//        String nomeArquivo = scanner.nextLine();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
//            String linha;
//            reader.readLine(); // Ignora o cabeçalho
//
//            while ((linha = reader.readLine()) != null) {
//                String[] dados = linha.split(",");
//                Class<T> objeto = new Class<> (
//                        Integer.parseInt(dados[0]),
//                        dados[1],
//                        Double.parseDouble(dados[2]),
//                        dados[3],
//                        dados[4]
//                );
//                return objeto;
//            }
//
//            System.out.println("Dados importados com sucesso!");
//
//        } catch (IOException e) {
//            System.out.println("Arquivo não encontrado, verifique o nome informado.");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
