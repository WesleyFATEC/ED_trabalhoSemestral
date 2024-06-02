package model;

public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private double valor;
    private int qtd;
    private TipoProduto tipo;
    
    public Produto() {
        super();
    }
    
    public Produto(int id, String nome, String descricao, double valor, int qtd, TipoProduto tipo) {
        super();
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.qtd = qtd;
        this.tipo = tipo;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public double getValor() {
        return valor;
    }
    
    public int getQtd() {
        return qtd;
    }
    
    public TipoProduto getTipo() {
        return tipo;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
    
    public void setTipo(TipoProduto tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString() {
        return id + "," + nome + "," + descricao + "," + valor + "," + qtd + "," + tipo;
    }
}
