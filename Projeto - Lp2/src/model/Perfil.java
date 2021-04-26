package src.model;

public class Perfil{
    private String nome;
    private String sexo;
    private int idade;
    private int altura;
    private double peso;
    private double taxaBasal;
    private double pesoMeta;
    private Metas metas = new Metas(0, 0, 0);

    public Perfil(String nome, String sexo, int idade, int altura, double peso, double taxaBasal, double pesoMeta) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.altura = altura;
        this.peso = peso;
        this.taxaBasal = taxaBasal;
        this.pesoMeta = pesoMeta;
    }

    public int getAltura() {
        return altura;
    }
    public int getIdade() {
        return idade;
    }
    public String getNome() {
        return nome;
    }
    public double getPeso() {
        return peso;
    }
    public String getSexo() {
        return sexo;
    }
    public double getTaxaBasal() {
        return taxaBasal;
    }
    public double getPesoMeta() {
        return pesoMeta;
    }
    public Metas getMetas() {
        return metas;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public void setTaxaBasal(double taxaBasal) {
        this.taxaBasal = taxaBasal;
    }
    public void setPesoMeta(double pesoMeta) {
        this.pesoMeta = pesoMeta;
    }
    public void setMetas(Metas metas) {
        this.metas = metas;
    }
}
