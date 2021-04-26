package src.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import src.model.Alimentos;
import src.model.Diario;


public class AlimentosSrv {
    public static void leitorAlimentos(ArrayList<Alimentos> allAlimentos){
        Alimentos alimentos;
        BufferedReader leitor;
        int codigo = 0;

        try{
            leitor = new BufferedReader(new FileReader("data\\Alimentos.csv"));
            String linha = null;
            while ((linha = leitor.readLine())!=null){
                String dividir[];
                //System.out.println(linha);
                dividir = linha.split(";");
                String nome = dividir[0];
                String unidade = dividir[1];
                String peso = dividir[2];
                double calorias = Double.parseDouble(dividir[3]);
                alimentos = new Alimentos(nome, unidade, peso, calorias, codigo);
                allAlimentos.add(alimentos);
                codigo++;
                }
                leitor.close();
        }catch(FileNotFoundException file){
            System.out.println("Arquivo não encontrado");

        }catch(IOException e){
            System.out.println("Erro ao ler o arquivo");
        }
    }

    public static void listarAlimentos(ArrayList<Alimentos> allAlimentos){
        System.out.println("Codigo | Alimento | Unidade | Peso(g/ml) | Calorias.");
        for (int i=0; i<allAlimentos.size();i++){
            System.out.println(allAlimentos.get(i).getCodigo()+"\t|"+allAlimentos.get(i).getNome()+"\t|"+allAlimentos.get(i).getUnidade()+"\t|"+allAlimentos.get(i).getPeso()+"\t|"+allAlimentos.get(i).getCalorias());
        }
    }

    public static int buscarAlimento(ArrayList<Alimentos> allAlimentos, String nomeAlimento){
        System.out.println("Codigo | Alimento | Unidade | Peso(g/ml) | Calorias.");
        boolean flag = false;
        for (int i=0; i<allAlimentos.size();i++){
            if (allAlimentos.get(i).getNome().contains(nomeAlimento)){
                System.out.println(allAlimentos.get(i).getCodigo()+"\t|"+allAlimentos.get(i).getNome()+"\t|"+allAlimentos.get(i).getUnidade()+"\t|"+allAlimentos.get(i).getPeso()+"\t|"+allAlimentos.get(i).getCalorias());
                flag = true;
            }
        }
        if(!flag){
            System.out.print("Esse alimento não foi encontrado. Tente novamente.");
            return buscarAlimento(allAlimentos, nomeAlimento);
        }
        return 0;
    }

    public static void selecionarAlimento(ArrayList<Alimentos> allAlimentos, int codigo, ArrayList<Alimentos> vetorAlimentos, Diario diario){
        vetorAlimentos.add(allAlimentos.get(codigo));
        diario.setCaloriasDiarias(diario.getCaloriasDiarias()+allAlimentos.get(codigo).getCalorias());//total de calorias em um dia
        System.out.println("alimento inserido com sucesso!\nAté o momento voce ingeriu "+diario.getCaloriasDiarias()+" calorias.");
    }
}
