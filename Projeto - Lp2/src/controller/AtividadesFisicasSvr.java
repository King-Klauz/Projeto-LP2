package src.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import src.model.AtividadesFisicas;
import src.model.Diario;

public class AtividadesFisicasSvr{
    public static void leitorAtividades(ArrayList<AtividadesFisicas> allAtividades){
        AtividadesFisicas atividadesFisicas;
        BufferedReader leitor;
        int codigo = 0;

        try{
            leitor = new BufferedReader(new FileReader("data\\atividadesFisicas.txt"));
            String linha = null;
            //linha = leitor.readLine();
            while ((linha = leitor.readLine())!=null){
                String dividir[];
                
                //System.out.println(linha);
                dividir = linha.split(";");
                String nome = dividir[0];
                int tempoAtividade = Integer.parseInt(dividir[1]);
                atividadesFisicas = new AtividadesFisicas(nome, codigo, tempoAtividade);
                allAtividades.add(atividadesFisicas);
                codigo++;
                }
                leitor.close();
        }catch(FileNotFoundException file){
            System.out.println("Arquivo não encontrado");

        }catch(IOException e){
            System.out.println("Erro ao ler o arquivo");
        }
    }

    public static void listarAtividades(ArrayList<AtividadesFisicas> allAtividades){
        for (int i = 0; i<allAtividades.size(); i++){
            System.out.println(allAtividades.get(i).getCodigo()+"\t"+allAtividades.get(i).getNome());
        }
    }

    public static void gastoCalorico(ArrayList<AtividadesFisicas> allAtividades , int codigo, int tempoAtividade, Diario diario){
        
        int gastoCalorico = allAtividades.get(codigo).getTempoAtividade()*tempoAtividade;//TROCAR "getTempoAtividade" por "getCaloriasPerdidas"
        allAtividades.get(codigo).setTempoDeAtividade(tempoAtividade);//tempo gasto na atividade atual
        allAtividades.get(codigo).setGastoCalorico(gastoCalorico);
        
        diario.setCaloriasDiariasPerdidas(diario.getCaloriasDiariasPerdidas()+gastoCalorico);
        diario.setTempoAtividade(diario.getTempoAtividade()+tempoAtividade);//tempo total gasto em todas as atividades realziadas durante o dia
        diario.getVetorAtividadesFisicas().add(allAtividades.get(codigo));
        
        System.out.println("Seu gasto calorico total nessa atividade foi de "+gastoCalorico+" calorias.");
    }
}
