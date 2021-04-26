package src.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
/*public static void inserirCurriculos(Hashtable<String, Curriculos> dadosCurriculos, Curriculos curriculos) throws IOException{
        BufferedWriter escritor = new BufferedWriter(new FileWriter("novos_arquivos\\curriculo(Atualizado).csv"));
        for (Curriculos i: dadosCurriculos.values()){
            escritor.write(i.getId()+";"+i.getNome_completo()+"\n");
        }
        escritor.write(curriculos.getId()+";"+curriculos.getNome_completo()+"\n");
        dadosCurriculos.put(curriculos.getId(), curriculos);

        escritor.close();
    }
    public static void removerCurriculos(Hashtable<String, Curriculos> dadosCurriculos, String id) throws IOException{
        BufferedWriter escritor = new BufferedWriter(new FileWriter("novos_arquivos\\curriculo(Atualizado).csv"));
        dadosCurriculos.remove(id);

        for (Curriculos i: dadosCurriculos.values()){
            escritor.write(i.getId()+";"+i.getNome_completo()+"\n");
        }

        escritor.close();
    }*/

import src.model.Metas;
import src.model.Perfil;

public class LoginSrv {

    public static void guardarLogin(Hashtable<String, Perfil> allPerfil) throws IOException{
        BufferedWriter escritor = new BufferedWriter(new FileWriter("data\\usuarios.txt"));
        Enumeration enu = allPerfil.keys();

        for (Perfil i: allPerfil.values()){
            escritor.write(i.getNome()+";"+i.getSexo()+";"+i.getIdade()+";"+i.getAltura()+";"+i.getPeso()+";"+i.getTaxaBasal()+";"+i.getPesoMeta()+";"+enu.nextElement()+";"+i.getMetas().getTempoAtividade()+";"+i.getMetas().getMetaCalorias()+"\n");
        }
        escritor.close();
    }

    public static void leitorLogin(Hashtable<String, Perfil> allPerfil){
        Perfil perfil;
        BufferedReader leitor;

        try{
            leitor = new BufferedReader(new FileReader("data\\usuarios.txt"));
            String linha = null;
            //linha = leitor.readLine();
            while ((linha = leitor.readLine())!=null){
                String dividir[];

                dividir = linha.split(";");
                String nome = dividir[0];
                String sexo = dividir[1];
                int idade = Integer.parseInt(dividir[2]);
                int altura = Integer.parseInt(dividir[3]);
                double peso = Double.parseDouble(dividir[4]);
                double taxaBasal = Double.parseDouble(dividir[5]);
                double pesoMeta = Double.parseDouble(dividir[6]);
                String senha = dividir[7];
                int tempoAtividade = Integer.parseInt(dividir[8]);
                double metaCalorias = Double.parseDouble(dividir[9]);

                perfil = new Perfil(nome, sexo, idade, altura, peso, taxaBasal, pesoMeta);
                perfil.getMetas().setPesoMeta(pesoMeta);
                perfil.getMetas().setTempoAtividade(tempoAtividade);
                perfil.getMetas().setMetaCalorias(metaCalorias);
                allPerfil.put(senha, perfil);
                }
                leitor.close();
        }catch(FileNotFoundException file){
            System.out.println("Arquivo não encontrado");

        }catch(IOException e){
            System.out.println("Erro ao ler o arquivo");
        }
    }
}
