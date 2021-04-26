package src.model;//Alimento;Unidade;Peso;Calorias

public class Alimentos {
    private int codigo;
    private String nome;
    private String unidade;
    private String peso;
    private double calorias;
    
    public Alimentos(String nome, String unidade, String peso, double calorias, int codigo) {
        this.nome = nome;
        this.unidade = unidade;
        this.peso = peso;
        this.calorias = calorias;
        this.codigo = codigo;
    }

    public double getCalorias() {
        return calorias;
    }
    public String getNome() {
        return nome;
    }
    public String getPeso() {
        return peso;
    }
    public String getUnidade() {
        return unidade;
    }
    public int getCodigo() {
        return codigo;
    }


    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setPeso(String peso) {
        this.peso = peso;
    }
    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return String.format("Alimento: "+nome+".%nPeso: "+peso+".%nCalorias: "+calorias+"%n");
    }
}
