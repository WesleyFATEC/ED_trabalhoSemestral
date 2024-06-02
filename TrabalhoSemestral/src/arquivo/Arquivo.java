package arquivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import lib.Lista;
import lib.hashtable;
import model.Produto;
import model.TipoProduto;

public class Arquivo {

    public static void escreverCSV(String arquivo, Produto produto) {
        Lista<Produto> produtosCSV = new Lista <>();

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String cabecalho = reader.readLine();
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                int id = Integer.parseInt(partes[0]);
                produtosCSV.addLast(new Produto(id, partes[1], partes[2], Double.parseDouble(partes[3]), Integer.parseInt(partes[4]), TipoProduto.valueOf(partes[5])));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo CSV: " + e.getMessage());
        }

        boolean produtoExiste = false;
        for (Produto p : produtosCSV) {
            if (p.getId(null, 0) == produto.getId(null, 0)) {
                p.setNome(produto.nome);
                p.setDescricao(produto.descricao);
                p.setValor(produto.valor);
                p.setQtd(produto.qtd);
                p.setTipo(produto.tipo);
                produtoExiste = true;
                break;
            }
        }

        // Se o produto não existir, adicioná-lo ao CSV
        if (!produtoExiste) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true))) {
                // Se o arquivo não existir, vamos escrever o cabeçalho
                if (writer.size() == 0) {
                    writer.write("ID,Nome,Descrição,Valor,Quantidade,Tipo\n");
                }

                writer.write(String.format("%d,%s,%s,%.2f,%d,%s\n",
                        produto.getId(),
                        produto.getNome(),
                        produto.getDescricao(),
                        produto.getValor(),
                        produto.getQtd(),
                        produto.getTipo().toString()));

                System.out.println("Produto salvo com sucesso no arquivo " + arquivo);
            } catch (IOException e) {
                System.err.println("Erro ao escrever no arquivo CSV: " + e.getMessage());
            }
        } else {
            // Se o produto existir e for atualizado, reescrever o arquivo com os dados atualizados
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
                writer.write("ID,Nome,Descrição,Valor,Quantidade,Tipo\n");
                for (Produto p : produtosCSV) {
                    writer.write(String.format("%d,%s,%s,%.2f,%d,%s\n",
                            p.getId(),
                            p.getNome(),
                            p.getDescricao(),
                            p.getValor(),
                            p.getQtd(),
                            p.getTipo().toString()));
                }
                System.out.println("Produto atualizado com sucesso no arquivo " + arquivo);
            } catch (IOException e) {
                System.err.println("Erro ao atualizar o arquivo CSV: " + e.getMessage());
            }
        }
    }
}
