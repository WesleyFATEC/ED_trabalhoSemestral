package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import lib.HashtableProduct;
import model.Produto;
import model.TipoProduto;
import utils.CsvReader;

public class ProductController {
	 private static HashtableProduct tabelaProdutos; // Armazena a Hashtable

	    public static void setTabelaProdutos(HashtableProduct tabela) {
	        tabelaProdutos = tabela;
	    }
	private static final String dir = System.getProperty("user.dir");
	
    private static final String FILE_PATH = dir + "/TrabalhoSemestral/src/arquivo/bd.csv";
    private static final String HEADER = "cod,nome,descricao,valor,quantidade,tipo";

    public static void cadastrarProduto(int id, String nome, String descricao, double valor, int qtd, TipoProduto tipo) throws Exception {
    	System.out.println(dir);
    	if (tabelaProdutos.containsKey(id)) {
            throw new IllegalArgumentException("Já existe um produto com o mesmo ID.");
        }
        Produto novoProduto = new Produto(id, nome, descricao, valor, qtd, tipo);
        tabelaProdutos.inserir(novoProduto);
        salvarTabelaProdutos(tabelaProdutos);
    }

    public static void atualizarProduto(int id, String nome, String descricao, double valor, int qtd, TipoProduto tipo) throws Exception {
        HashtableProduct tabelaProdutos = carregarTabelaProdutos();
        if (!tabelaProdutos.containsKey(id)) {
            throw new IllegalArgumentException("Produto com ID " + id + " não encontrado.");
        }
        Produto produtoAtualizado = new Produto(id, nome, descricao, valor, qtd, tipo);
        tabelaProdutos.inserir(produtoAtualizado);
        salvarTabelaProdutos(tabelaProdutos);
    }

    public static void excluirProduto(int id) throws Exception {
        HashtableProduct tabelaProdutos = carregarTabelaProdutos();
        if (!tabelaProdutos.containsKey(id)) {
            throw new IllegalArgumentException("Produto com ID " + id + " não encontrado.");
        }
        tabelaProdutos.remover(id);
        salvarTabelaProdutos(tabelaProdutos);
    }

    public static void listarProdutos() throws Exception {
        HashtableProduct tabelaProdutos = carregarTabelaProdutos();
        tabelaProdutos.iterar();
    }

    private static HashtableProduct carregarTabelaProdutos() throws Exception {
        File arquivo = new File(FILE_PATH);

        if (!arquivo.exists()) {
            criarArquivo(FILE_PATH, HEADER);
        }

        int tamanhoTabela = CsvReader.contarLinhas(FILE_PATH);
        HashtableProduct tabelaProdutos = new HashtableProduct(tamanhoTabela);

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            boolean primeiraLinha = true; // Variável para ignorar a primeira linha
            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue; // Pular a primeira linha
                }
                String[] dados = linha.split(",");
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                double preco = Double.parseDouble(dados[3]);
                Produto produto = new Produto(id, nome, dados[2], preco, Integer.parseInt(dados[4]), null);
                tabelaProdutos.inserir(produto);
            }
        }

        return tabelaProdutos;
    }


    private static void salvarTabelaProdutos(HashtableProduct tabelaProdutos) throws IOException {
    	try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bw.write(HEADER); // Escreve o cabeçalho
            bw.newLine(); // Nova linha após o cabeçalho
            for (Produto produto : tabelaProdutos) {
                bw.write(produto.getId() + "," + produto.getNome() + "," + produto.getDescricao() + "," + produto.getValor() + "," + produto.getQtd());
                bw.newLine(); // Nova linha após cada produto
            }
            File arquivo = new File(FILE_PATH);
            if (arquivo.exists()) {
                System.out.println("Arquivo criado com sucesso: " + FILE_PATH);
            } else {
                System.out.println("Falha ao criar o arquivo: " + FILE_PATH);
            }
            
    	}catch (IOException e) {
            System.out.println("Erro ao salvar dados no arquivo: " + e.getMessage());
        }
        
    }

    private static boolean arquivoExiste(String caminho) {
        File arquivo = new File(caminho);
        return arquivo.exists();
    }

    private static void criarArquivo(String caminho, String cabecalho) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
            bw.write(cabecalho);
            bw.newLine();
        }
    }
}
