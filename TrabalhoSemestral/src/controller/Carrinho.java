package controller;
import lib.Pilha;
import model.Produto;

public class Carrinho {
	private Pilha<Produto> itens;
	public Carrinho () {
		this.itens = new Pilha<>();
	}
	
	public void addItem (Produto item) {
		itens.push(item);
		System.out.println(item + "adicionado ao carrinho");
	}
	
	public void removeItem () throws Exception {
		if (!itens.isEmpty()) {
			Produto itemremovido = itens.pop();
			System.out.println(itemremovido + "removido do Carrinho");
		}
		else {
			System.out.println("Carrinho vazio, não há nenhum item para ser removido");
		}
	}
	
	public void exibirCart() throws Exception {
		System.out.println("Itens no carrinho");
		for (int i= 0 ; i<= itens.size(); i ++) {
			System.out.println(itens.top());
		}
	}
	
	public void removeItem (Produto item) {
		if(itens.contains(item))
		{
			Pilha tempCart = new Pilha();
			while (!itens.top().equals(item)) 
			{
				tempCart.push(itens.top());
			}
			itens.pop();
			while (!tempCart.isEmpty()) 
			{
				itens.push(itens.pop());
			}
			System.out.println(item+ "removido do carrinho");
			
		}
		else
		{
			System.out.println("O item não está no carrinho");
		}
	}
	
}
