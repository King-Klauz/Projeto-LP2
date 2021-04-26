import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Scanner;

import src.controller.AlimentosSrv;
import src.controller.AtividadesFisicasSvr;
import src.controller.DiarioSrv;
import src.controller.LoginSrv;
import src.model.Alimentos;
import src.model.AtividadesFisicas;
import src.model.Diario;
import src.model.Login;
import src.model.Metas;
import src.model.Perfil;
import src.view.Render;

public class App{
    public static void main(String [] agr ) throws IOException{

        Scanner scan = new Scanner(System.in);
        
        //Metas metas = new Metas(0, 0, 0);
        Diario diario = new Diario("0", 0, 0, 0, 0);
        Login login = new Login("0", "0");

        Hashtable<String, Perfil> allPerfil = new Hashtable<>();
        ArrayList<AtividadesFisicas> allAtividades = new ArrayList<>();
        ArrayList<Alimentos> allAlimentos = new ArrayList<>();
        
        AtividadesFisicasSvr.leitorAtividades(allAtividades);
        AlimentosSrv.leitorAlimentos(allAlimentos);
        LoginSrv.leitorLogin(allPerfil);
        
        int opcao = 0;
        Render.menuLogin(scan, allPerfil, login);
        
        while(opcao!=8){
            Render.menu();
            System.out.printf("opcao-> ");
            opcao = scan.nextInt();
            scan.nextLine();
            System.out.println("\n");
            System.out.println("---------------------------------------------------------------------");
            switch(opcao){
                case 1:
                    Render.informarAtividade(allAtividades, scan, diario);
                break;
                case 2:
                    Render.alimentosCadastro(allAlimentos, scan, diario);
                break;
                case 3:
                    Render.atualizarMetas(scan, allPerfil, login);
                break;
                case 4:
                    DiarioSrv.exibirDiario(diario, login, allPerfil);
                break;
                case 5:
                    DiarioSrv.escreverDiario(diario, login, allPerfil);
                break;
                case 6:
                    Render.exibirPerfil(scan, allPerfil, login);
                break;
                case 7:
                    Render.atualizarPerfil(scan, allPerfil, login);
                break;
                case 8:
                    System.out.println("voce saiu do sistema.");
                break;
                default:
                    System.out.println("opcao invalida.");
                break;
            }
        }

    }
}