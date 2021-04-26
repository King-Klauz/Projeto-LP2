package src.controller;

import java.util.Hashtable;
import java.util.Scanner;

import src.model.Login;
import src.model.Perfil;

public class PerfilSvr{

    public static double calculoBasal(String sexo, int idade, double peso, int altura) {
        double tmb=0;
        
        if (sexo.equals("M")){
            tmb = 66 + (13.8*peso)+(5*altura)-(6.8*idade);
        }else{
            tmb = 655 + (9.6*peso)+(1.8*altura)-(4.7*idade);
        }
        return tmb;
    }

    public static void atualizarPerfil(Scanner scan, int i, Hashtable<String, Perfil> allPerfil, Login login){

        String senha = login.getSenha();
        
        if(i==1){
            System.out.println("Editar idade: ");
            int idade = scan.nextInt();
            allPerfil.get(senha).setIdade(idade);
        }else if(i==2){
            System.out.println("Editar altura: ");
            int altura = scan.nextInt();
            allPerfil.get(senha).setAltura(altura);
        }else if(i==3){
            System.out.println("Editar peso: ");
            double peso = scan.nextDouble();
            allPerfil.get(senha).setPeso(peso);
        }else{
            System.out.println("opção inválida.");
        }
    }
}
