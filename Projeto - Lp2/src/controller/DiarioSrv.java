package src.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;

import src.model.Diario;
import src.model.Login;
import src.model.Perfil;


public class DiarioSrv {
    public static void escreverDiario(Diario diario, Login login, Hashtable<String, Perfil> allPerfil) throws IOException{
        Calendar c = Calendar.getInstance();
        DateFormat d = DateFormat.getDateInstance(DateFormat.MEDIUM);
        
        String usuario = login.getUsuario();
        String senha = login.getSenha();
        double peso = allPerfil.get(senha).getPeso();
        
        BufferedWriter escritor = new BufferedWriter(new FileWriter("Relatórios\\"+usuario+"("+peso+" kg)"+"("+d.format(c.getTime())+").html"));

        String aux1 = tabela1(usuario, allPerfil.get(senha).getMetas().getPesoMeta(), allPerfil.get(senha).getMetas().getTempoAtividade(), allPerfil.get(senha).getMetas().getMetaCalorias());
        String aux2 = tabela2(allPerfil.get(senha).getPeso(), diario.getTempoAtividade(), diario.getCaloriasDiarias());

        escritor.write(aux1);
        escritor.write(aux2);

        for(int i=0; i<diario.getVetorAtividadesFisicas().size();i++){
            escritor.write(tabela3(i+1, diario.getVetorAtividadesFisicas().get(i).getNome(), diario.getVetorAtividadesFisicas().get(i).getTempoDeAtividade(), diario.getVetorAtividadesFisicas().get(i).getGastoCalorico()));
        }
           escritor.write("\nTotal de calorias perdidas em atividades fisicas: "+diario.getCaloriasDiariasPerdidas()+"<br>");

        escritor.write("\n\n");
        
        for(int i=0; i<diario.getVetorRefeicoes().size();i++){
            escritor.write("<br><caption>------"+(i+1)+"º refeição ------</caption><br>");
            for(int j = 0; j<diario.getVetorRefeicoes().get(i).size(); j++){
                
                escritor.write(tabela4(diario.getVetorRefeicoes().get(i).get(j).getNome(), diario.getVetorRefeicoes().get(i).get(j).getPeso(), diario.getVetorRefeicoes().get(i).get(j).getCalorias()));
            }
        }
        escritor.write("\nTotal de calorias ingeridas: "+diario.getCaloriasDiarias());
        escritor.close();
        System.out.println("Diario escrito com sucesso! Voce pode encontra-lo na pasta de Relatórios.\n");
    }

    public static void exibirDiario(Diario diario, Login login, Hashtable<String, Perfil> allPerfil) throws IOException{
        
        String usuario = login.getUsuario();
        String senha = login.getSenha();
            

        System.out.println("------METAS------\n\t-Meta de peso(kg): "+allPerfil.get(senha).getMetas().getPesoMeta()+".\n\t-Meta de tempo em atividades(min): "+allPerfil.get(senha).getMetas().getTempoAtividade()+".\n\t-Meta de calorias: "+allPerfil.get(senha).getMetas().getMetaCalorias()+".\n----------------\n");
        System.out.println("Peso atual---> "+allPerfil.get(senha).getPeso()+".\nTempo total em atividades: "+diario.getTempoAtividade()+"\nTotal de calorais: "+diario.getCaloriasDiarias());


        for(int i=0; i<diario.getVetorAtividadesFisicas().size();i++){
            System.out.println("\n------ "+(i+1)+"º atividade ------\n\nAtividade: "+diario.getVetorAtividadesFisicas().get(i).getNome()+"\nTempo: "+diario.getVetorAtividadesFisicas().get(i).getTempoDeAtividade()+" minutos.\nGastoCalorico: "+diario.getVetorAtividadesFisicas().get(i).getGastoCalorico()+"\n");
        }

        System.out.println("\nTotal de calorias perdidas em atividades fisicas: "+diario.getCaloriasDiariasPerdidas());

        System.out.println("\n\n");

        for(int i=0; i<diario.getVetorRefeicoes().size();i++){
            System.out.println("------ "+(i+1)+"º refeição ------\n");
             for(int j = 0; j<diario.getVetorRefeicoes().get(i).size(); j++){
                 System.out.println(diario.getVetorRefeicoes().get(i).get(j).toString());
            }
        }
        System.out.println("\nTotal de calorias ingeridas: "+diario.getCaloriasDiarias());
    }

    public static String tabela1(String nome, double pesoMeta,int tempoAtividadeMeta, double metaCaloriasMeta){
        String tabela=
             "<style>"+
            " .azul{"+    
            "background-color: powderblue;"+
            "}"+
            "td{"+
            "color:black;"+
            "}"+ 
            ".branco{"+
            "background-color: white;"+
            "}"+ 
            "</style>"+
            "<table border=1>"+    
            "<colgroup>"+ 
            "<col class=azul>"+
            "<col class=branco>"+
            "</colgroup>"+
             "<caption>USUARIO: "+nome+"<br>------METAS------</caption>"+
                "<thead>"+
                    " <tr>"+
                    " <td>-Meta de peso(kg): </td>"+
                    "<td>"+String.valueOf(pesoMeta)+"</td>"+
                    " </tr>"+
                    " <td>-Meta de tempo em atividades(min): </td>"+
                    "<td>"+String.valueOf(tempoAtividadeMeta)+"</td>"+
                    " </tr>"+      
                    " <td>-Meta de calorias: </td>"+
                    "<td>"+String.valueOf(metaCaloriasMeta)+"</td>"+
                    " </tr>"+ 
                   "</thead>"+
        "</table><br>";

        return tabela;
    }

    public static String tabela2(double pesoMeta, int tempoAtividadeMeta, double metaCaloriasMeta){
        String tabela =
        "\n\n<table border=1>"+    
            "<colgroup>"+ 
            "<col class=azul>"+
            "<col class=branco>"+
            "</colgroup>"+
                "<thead>"+
                    " <tr>"+
                    " <td>Peso atual</td>"+
                    "<td>"+String.valueOf(pesoMeta)+"</td>"+
                    " </tr>"+
                    " <td>Tempo total em atividades</td>"+
                    "<td>"+String.valueOf(tempoAtividadeMeta)+"</td>"+
                    " </tr>"+      
                    " <td>Calorias</td>"+
                    "<td>"+String.valueOf(metaCaloriasMeta)+"</td>"+
                    " </tr>"+ 
                   "</thead>"+
        "</table><br>";

        return tabela;
    }

    public static String tabela3(int numeroAtividade, String nomeAtividade,int tempoDeAtividade, double calorias){
        String tabela =
            "\n\n<table border=1>"+    
            "<colgroup>"+ 
            "<col class=azul>"+
            "<col class=branco>"+
            "</colgroup>"+
             "<caption>------"+numeroAtividade+"º atividade ------</caption>"+
                "<thead>"+
                    " <tr>"+
                    " <td>Atividade</td>"+
                    "<td>"+nomeAtividade+"</td>"+
                    " </tr>"+
                    " <td>Tempo</td>"+
                    "<td>"+String.valueOf(tempoDeAtividade)+" minutos.</td>"+
                    " </tr>"+      
                    " <td>GastoCalorico</td>"+
                    "<td>"+String.valueOf(calorias)+" calorias</td>"+
                    " </tr>"+ 
                   "</thead>"+
        "</table><br>";

        return tabela;
    }

    public static String tabela4(String nomeAlimento, String peso, Double calorias){
        String tabela = 
        "\n\n<table border=1>"+    
            "<colgroup>"+ 
            "<col class=azul>"+
            "<col class=branco>"+
            "</colgroup>"+
                "<thead>"+
                    " <tr>"+
                    " <td>Alimento</td>"+
                    "<td>"+nomeAlimento+"</td>"+
                    " </tr>"+
                    " <td>Peso</td>"+
                    "<td>"+peso+" g</td>"+
                    " </tr>"+      
                    " <td>Calorias</td>"+
                    "<td>"+String.valueOf(calorias)+" calorias</td>"+
                   "</thead>"+
        "</table><br>";

        return tabela;
    }
}
