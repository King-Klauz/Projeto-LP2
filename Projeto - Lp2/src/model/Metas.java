package src.model;

public class Metas{
    private double pesoMeta;
    private double metaCalorias;
    private int tempoAtividade;
    
    public Metas (double pesoMeta, double metaCalorias, int tempoAtividade){
        this.pesoMeta = pesoMeta;
        this.tempoAtividade = tempoAtividade;
        this.metaCalorias = metaCalorias;
    }

    public double getPesoMeta() {
        return pesoMeta;
    }
    public int getTempoAtividade() {
        return tempoAtividade;
    }
    public double getMetaCalorias() {
        return metaCalorias;
    }

    public void setPesoMeta(double pesoMeta) {
        this.pesoMeta = pesoMeta;
    }
    public void setTempoAtividade(int tempoAtividade) {
        this.tempoAtividade = tempoAtividade;
    }
    public void setMetaCalorias(double metaCalorias) {
        this.metaCalorias = metaCalorias;
    }
}
