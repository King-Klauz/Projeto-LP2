package src.controller;

import java.util.Hashtable;
import java.util.Scanner;

import src.model.Login;
import src.model.Perfil;

public class MetasSrv {

    public static int nivelDeAtividade(Scanner scan){
        int opcao = 0;

        System.out.println("\n\n---Nivel de Atividade física.---\n");
        System.out.println("1 - Sedentário (Exercício mínimo)");
        System.out.println("2 - Exercício Leve (1-3 dias por semana)");
        System.out.println("3 - Exercício Moderado (3-5 dias por semana)");
        System.out.println("4 - Exercício Intenso (6-7 dias por semana)");
        System.out.println("5 - Exercício Muito Intenso (Atleta, 2x por dia)");
        System.out.printf("\nopcao-> ");

        opcao = scan.nextInt();
        scan.nextLine();

        return opcao;
    }
    
    public static double calcularTDEE(double taxaBasal, int nivelDeAtividade){
        if (nivelDeAtividade==1){
            taxaBasal = taxaBasal*1.2;
            
        }else if (nivelDeAtividade==2){
            taxaBasal = taxaBasal*1.375;
        }else if (nivelDeAtividade==3){
            taxaBasal = taxaBasal*1.55;
        }else if (nivelDeAtividade==4){
            taxaBasal = taxaBasal*1.725;
        }else if (nivelDeAtividade==5){
            taxaBasal = taxaBasal*1.9;
        }
        return taxaBasal;
    }

    public static void atualizarMetas(Scanner scan, Hashtable<String, Perfil> allPerfil, int opcao, Login login){
        if(opcao==1){
            System.out.println("Peso: ");
            double pesoMeta = scan.nextDouble();
            scan.nextLine();
            allPerfil.get(login.getSenha()).getMetas().setPesoMeta(pesoMeta);
            System.out.println("Meta de peso Atualizada.");
        }else if(opcao==2){
            System.out.println("Tempo de atividade: ");
            int tempoAtividade = scan.nextInt();
            scan.nextLine();
            allPerfil.get(login.getSenha()).getMetas().setTempoAtividade(tempoAtividade);
            System.out.println("Meta de tempo de ativiadade atualizada.");
        }else if(opcao==3){
            System.out.println("Calorias: ");
            double metaCalorias = scan.nextDouble();
            scan.nextLine();
            allPerfil.get(login.getSenha()).getMetas().setMetaCalorias(metaCalorias);
            System.out.println("Meta de calorias.");
        }
    }

    public static double calcularMetaDeCalorias(double pesoMeta, double pesoAtual, double metaCalorias){
        if (pesoAtual>pesoMeta){
            metaCalorias = metaCalorias - 200;
            System.out.println("Para perder peso de forma saudável o recomendado é ter um défict em média de 200 a 500 calorias por dia, ou seja, "+metaCalorias+" calorias no mínimo no seu caso.");
            System.out.println("Com essa alimentação voce poderá perder cerca de 0,2 a 0,5 quilos por semana.");
        }else if(pesoAtual<pesoMeta){
            metaCalorias = metaCalorias + 200;
            System.out.println("Para ganhar peso de forma saudável o recomendado é ter um superávit em média de 200 a 500 calorias por dia, ou seja, "+metaCalorias+" calorias no mínimo no seu caso.");
            System.out.println("Com essa alimentação voce poderá ganhar cerca de 0,2 a 0,5 quilos por semana.");
        }else{
            System.out.println("Para manter o peso voce irá precisar ingerir cerca de "+metaCalorias+" por dia.");
        }
        return metaCalorias;
    }

}
