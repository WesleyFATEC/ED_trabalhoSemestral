package lib;
import lib.Lista;
import utils.Iterar;

public class hashtable<T> {
    private Lista<T>[] tabela;
    private int tamtabela;
    
    public hashtable(int tamanho) 
    {
        this.tamtabela = tamanho;
        this.tabela = new Lista[tamtabela];
        for (int i=0; i < tamtabela; i++)
        {
            tabela[i] = new Lista<>();
        }
    }
    
    private int hash(int key) 
    {
        return key % tamtabela;
    }
    
    public void inserir(int key, T object) throws Exception
    {
        int index = hash(key);
        tabela[index].addLast(object);
    }

    public void iterar() throws Exception {
        for (int i = 0; i < tabela.length; i++) {
            Lista<T> lista = tabela[i];
            for (int j = 0; j < lista.size(); j++) {
                T objeto = lista.get(j);
                System.out.println(objeto);
            }
        }
    }
    
    public T buscar(int key) 
    {
        int index = hash(key);
        Lista<T> lista = tabela[index];
        for (int i = 0; i < lista.size(); i++) {
            T object = lista.get(i);
            if (object.getId() == key) {
                return object;
            }
    }
        return null;
    }
}
