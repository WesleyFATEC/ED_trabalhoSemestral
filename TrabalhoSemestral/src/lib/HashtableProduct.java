package lib;

import model.Produto;
import java.util.Iterator;

public class HashtableProduct implements Iterable<Produto> {
    private Lista<Produto>[] tabela;
    private int tamtabela;
    
    public HashtableProduct(int tamanho) {
        this.tamtabela = tamanho;
        this.tabela = new Lista[tamtabela];
        for (int i=0; i < tamtabela; i++) {
            tabela[i] = new Lista<>();
        }
    }
    
    private int hash(int key) {
        return key % tamtabela;
    }
    
    public void inserir(Produto produto) throws Exception {
        int index = hash(produto.getId());
        tabela[index].addLast(produto);
    }

    public void iterar() throws Exception {
        for (int i = 0; i < tabela.length; i++) {
            Lista<Produto> lista = tabela[i];
            for (int j = 0; j < lista.size(); j++) {
                Produto produto = lista.get(j);
                System.out.println(produto);
            }
        }
    }
    
    public Produto buscar(int key) throws Exception {
        int index = hash(key);
        Lista<Produto> lista = tabela[index];
        for (int i = 0; i < lista.size(); i++) {
            Produto produto = lista.get(i);
            if (produto.getId() == key) {
                return produto;
            }
        }
        return null;
    }

    public boolean containsKey(int key) throws Exception {
        return buscar(key) != null;
    }

    @Override
    public Iterator<Produto> iterator() {
        return new HashtableProductIterator();
    }
    
    public void remover(int key) throws Exception {
        int index = hash(key);
        Lista<Produto> lista = tabela[index];
        for (int i = 0; i < lista.size(); i++) {
            Produto produto = lista.get(i);
            if (produto.getId() == key) {
                lista.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("Produto com ID " + key + " não encontrado.");
    }
    
    private class HashtableProductIterator implements Iterator<Produto> {
        private int currentIndex = 0;
        private int currentListIndex = 0;
    
        
        @Override
        public boolean hasNext() {
            while (currentListIndex < tabela.length) {
                Lista<Produto> currentList = tabela[currentListIndex];
                if (currentIndex < currentList.size()) {
                    return true;
                } else {
                    currentIndex = 0;
                    currentListIndex++;
                }
            }
            return false;
        }

        @Override
        public Produto next() {
            Lista<Produto> currentList = tabela[currentListIndex];
            try {
            	Produto produto = currentList.get(currentIndex);
            	currentIndex++;
            	return produto;
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null; 
            
        }
    }
}
