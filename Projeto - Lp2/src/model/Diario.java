package src.model;

import java.util.ArrayList;

public class Diario {
    private ArrayList<ArrayList<Alimentos>> vetorRefeicoes = new ArrayList<>();
    
    private ArrayList<AtividadesFisicas> vetorAtividadesFisicas = new ArrayList<>();
    private String ativadadesDiarias;
    private int tempoAtividade;
    private double caloriasDiariasPerdidas;
    private double pesoDiario;
    private double caloriasDiarias;


    public Diario(String ativadadesDiarias, int tempoAtividade, double caloriasDiariasPerdidas ,double pesoDiario, double caloriasDiarias) {
        this.ativadadesDiarias = ativadadesDiarias;
        this.tempoAtividade = tempoAtividade;
        this.pesoDiario = pesoDiario;
        this.caloriasDiarias = caloriasDiarias;
        this.caloriasDiariasPerdidas = caloriasDiariasPerdidas;
    }

    public String getAtivadadesDiarias(){
        return ativadadesDiarias;
    }
    public double getCaloriasDiarias() {
        return caloriasDiarias;
    }
    public double getPesoDiario() {
        return pesoDiario;
    }
    public int getTempoAtividade() {
        return tempoAtividade;
    }
    public ArrayList<ArrayList<Alimentos>> getVetorRefeicoes() {
        return vetorRefeicoes;
    }
    public ArrayList<AtividadesFisicas> getVetorAtividadesFisicas() {
        return vetorAtividadesFisicas;
    }
    public double getCaloriasDiariasPerdidas() {
        return caloriasDiariasPerdidas;
    }
    
    
    public void setAtivadadesDiarias(String ativadadesDiarias) {
        this.ativadadesDiarias = ativadadesDiarias;
    }

    public void setCaloriasDiarias(double caloriasDiarias) {
        this.caloriasDiarias = caloriasDiarias;
    }
    public void setPesoDiario(double pesoDiario) {
        this.pesoDiario = pesoDiario;
    }
    public void setTempoAtividade(int tempoAtividade) {
        this.tempoAtividade = tempoAtividade;
    }
    public void setVetorRefeicoes(ArrayList<ArrayList<Alimentos>> vetorRefeicoes) {
        this.vetorRefeicoes = vetorRefeicoes;
    }
    public void setVetorAtividadesFisicas(ArrayList<AtividadesFisicas> vetorAtividadesFisicas) {
        this.vetorAtividadesFisicas = vetorAtividadesFisicas;
    }
    public void setCaloriasDiariasPerdidas(double caloriasDiariasPerdidas) {
        this.caloriasDiariasPerdidas = caloriasDiariasPerdidas;
    }
}
