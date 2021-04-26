package src.model;

public class AtividadesFisicas {
    private String nome;
    private int codigo;
    private int tempoAtividade;
    private double gastoCalorico;
    private int tempoDeAtividade;

    public AtividadesFisicas(String nome, int codigo, int tempoAtividade){
        this.nome = nome;
        this.codigo = codigo;
        this.tempoAtividade = tempoAtividade;
    }

    public String getNome() {
        return nome;
    }
    public int getCodigo() {
        return codigo;
    }
    public int getTempoAtividade() {
        return tempoAtividade;
    }
    public double getGastoCalorico() {
        return gastoCalorico;
    }
    public int getTempoDeAtividade() {
        return tempoDeAtividade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public void setTempoAtividade(int tempoAtividade) {
        this.tempoAtividade = tempoAtividade;
    }
    public void setGastoCalorico(double gastoCalorico) {
        this.gastoCalorico = gastoCalorico;
    }
    public void setTempoDeAtividade(int tempoDeAtividade) {
        this.tempoDeAtividade = tempoDeAtividade;
    }
}
