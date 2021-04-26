package src.view;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;


import src.controller.*;
import src.model.*;

public class Render {
    public static void menu(){
        System.out.println("---------------------------------------------------------------------");
        System.out.println("1 - Informar atividade física realizada.");
        System.out.println("2 - Informar alimentação durante o dia.");
        System.out.println("3 - Atualizar metas(Peso, Tempo de atividade diaria, calorias).");
        System.out.println("4 - Vizualizar diario(Histórico de atv. e refeições durante o dia.).");
        System.out.println("5 - Imprimir relotário diario.");
        System.out.println("6 - Exibir informações do perfil.");
        System.out.println("7 - Atualizar perfil.");
        System.out.println("8 - Sair.");
        System.out.println("---------------------------------------------------------------------");
    }


    public static void login(Hashtable<String, Perfil> allPerfil, Login login, Scanner scan) throws IOException{
        
        System.out.printf("Usuario: ");
        String usuario = scan.nextLine();
        System.out.printf("Senha: ");
        String senha = scan.nextLine();
        login.setUsuario(usuario);
        login.setSenha(senha);

        if (allPerfil.get(senha)!=null){
            System.out.println("\n\nPerfil encontrado.\nUsuario: "+allPerfil.get(senha).getNome());
        }else{
            System.out.println("\n\nCredenciais invalidas. Vamos cadastrar um perfil.\n");
            Render.cadastrarPerfil(allPerfil, login, scan);
        }

    }

    public static void cadastrarPerfil(Hashtable<String, Perfil> allPerfil, Login login, Scanner scan) throws IOException{
        Perfil perfil;

        System.out.printf("\n----CADASTRAR PERFIL----\n");

        System.out.printf("Usuario: ");
        String usuario = scan.nextLine();

        System.out.printf("Senha: ");
        String senha = scan.nextLine();
        
        System.out.printf("Sexo(M ou F): ");
        String sexo = scan.nextLine();

        System.out.printf("idade: ");
        int idade = scan.nextInt();

        System.out.printf("altura(cm): ");
        int altura = scan.nextInt();

        System.out.printf("peso(Kg): ");
        double peso = scan.nextDouble();
        scan.nextLine();

        double taxaBasal = PerfilSvr.calculoBasal(sexo, idade, peso, altura);
        int nivelDeAtividade = MetasSrv.nivelDeAtividade(scan);
        
        double metaCalorias = MetasSrv.calcularTDEE(taxaBasal, nivelDeAtividade);

        System.out.println("\n\n---Metas---\n");
        System.out.printf("Peso atual: %.2f\n", peso);
        System.out.printf("Peso desejado(Kg): ");
        double pesoMeta = scan.nextDouble();



        scan.nextLine();
        login.setUsuario(usuario);
        login.setSenha(senha);

        System.out.printf("\nMeta de tempo de atividade diário(min): ");
        int tempoAtividade = scan.nextInt();
        System.out.printf("\nMeta de calorias: ");
        metaCalorias = MetasSrv.calcularMetaDeCalorias(pesoMeta, peso, metaCalorias);
        

        perfil = new Perfil(usuario, sexo, idade, altura, peso, taxaBasal, pesoMeta);
        perfil.getMetas().setMetaCalorias(metaCalorias);
        perfil.getMetas().setPesoMeta(pesoMeta);
        perfil.getMetas().setTempoAtividade(tempoAtividade);
        

        allPerfil.put(senha, perfil);
        LoginSrv.guardarLogin(allPerfil);

        System.out.println("\nPerfil cadastrado!\n");
    }

    public static void informarAtividade(ArrayList<AtividadesFisicas> allAtividades, Scanner scan, Diario diario){
        
        System.out.println("Tipo de atividade realizada: ");
        AtividadesFisicasSvr.listarAtividades(allAtividades);
        System.out.printf("Digite codigo da atividade: ");
        int codigo = scan.nextInt();
        scan.nextLine();
        System.out.println("Tempo de atividade (min): ");
        int tempoAtividade = scan.nextInt();
        AtividadesFisicasSvr.gastoCalorico(allAtividades, codigo, tempoAtividade, diario);
    }

    public static void alimentosCadastro(ArrayList<Alimentos> allAlimentos, Scanner scan, Diario diario){
        ArrayList<Alimentos> vetorAlimentos = new ArrayList<>();
        System.out.println("REFEIÇÕES");
        System.out.println("1 - Ver todos os alimentos do cardápio");
        System.out.println("2 - Buscar por um alimento especifico.");
        System.out.println("opcao-> ");
        String continuar = "s";
        int opcao = scan.nextInt();
        scan.nextLine();
        
        switch(opcao){
            case 1:
                while(continuar.equals("s")){
                    AlimentosSrv.listarAlimentos(allAlimentos);
                    System.out.println("digite o codigo do alimento.");
                    int codigo = scan.nextInt();
                    scan.nextLine();
                    AlimentosSrv.selecionarAlimento(allAlimentos, codigo, vetorAlimentos, diario);
                    System.out.println("\nDeseja escolher mais alimentos? [s/n]");
                    continuar = scan.nextLine();
                }
                diario.getVetorRefeicoes().add(vetorAlimentos);
                //diario.getVetorAlimentos().clear();
            break;
                
            case 2:
                while(continuar.equals("s")){
                    System.out.printf("Nome do alimento-> ");
                    String nomeAlimento = scan.nextLine();
                    AlimentosSrv.buscarAlimento(allAlimentos, nomeAlimento);
                    System.out.println("\nDigite o codigo do alimento.");
                    int codigo1 = scan.nextInt();
                    scan.nextLine();
                    AlimentosSrv.selecionarAlimento(allAlimentos, codigo1, vetorAlimentos, diario);
                    
                    System.out.println("\nDeseja escolher mais alimentos? [s/n]");
                    continuar = scan.nextLine();
                }
                diario.getVetorRefeicoes().add(vetorAlimentos);
                //diario.getVetorAlimentos().clear();
            break;

            default:
                System.out.println("opcao invalida.");
            break;
        }
        
    }


    public static void menuLogin(Scanner scan, Hashtable<String, Perfil> allPerfil, Login login) throws IOException{
        
        System.out.println("1 - Entrar.");
        System.out.println("2 - Cadastrar perfil.");
        System.out.printf("opcao-> ");
        int opcao = scan.nextInt();
        scan.nextLine();

        switch(opcao){
            case 1:
                Render.login(allPerfil, login, scan);
            break;
            case 2:
                Render.cadastrarPerfil(allPerfil, login, scan);
            break;
            default:
                System.out.println("opcao invalida");
            break;
        }
    }
    public static void exibirPerfil(Scanner scan, Hashtable<String, Perfil> allPerfil, Login login){
        System.out.println("Usuário: "+allPerfil.get(login.getSenha()).getNome()+".\nSexo: "+allPerfil.get(login.getSenha()).getSexo()+".\nIdade: "+allPerfil.get(login.getSenha()).getIdade()+".\nPeso atual: "+allPerfil.get(login.getSenha()).getPeso()+".\nMeta de peso: "+allPerfil.get(login.getSenha()).getMetas().getPesoMeta()+".");
    }

    public static void atualizarMetas(Scanner scan, Hashtable<String, Perfil> allPerfil, Login login){
        System.out.println("1 - Atualizar meta de peso.");
        System.out.println("2 - Atualizar meta de tempo(min) de atividade fisica por dia.");
        System.out.println("3 - Atualizar meta de calorias por dia.");
        System.out.printf("opção-> ");
        int opcao = scan.nextInt();
        scan.nextLine();

        MetasSrv.atualizarMetas(scan, allPerfil, opcao, login);
    }

    public static void atualizarPerfil(Scanner scan, Hashtable<String, Perfil> allPerfil, Login login){
        System.out.println("1 - Atualizar idade.");
        System.out.println("2 - Atualizar altura.");
        System.out.println("3 - Atualizar peso.");
        System.out.printf("opção-> ");
        int opcao = scan.nextInt();
        scan.nextLine();
        PerfilSvr.atualizarPerfil(scan, opcao, allPerfil, login);
    }
}