package view;

import lib.*;
import model.*;
import controller.*;
import javax.swing.JOptionPane;
import controller.ProductController;
import model.TipoProduto;

public class Principal
{
	public static void main(String[] args)
	{	HashtableProduct tabelaProdutos = new HashtableProduct(10); // Tamanho inicial da Hashtable
    	ProductController.setTabelaProdutos(tabelaProdutos); 
		TipoController tc = new TipoController();
		controller.Carrinho cart = new controller.Carrinho();
		Lista<ListaTipos> tipos = new Lista<>();
		
		while (true) {
			String[] opcs = {"Adicionar Tipo", "Listar Tipos", "Excluir Tipos",
					"Consultar por C�digo", "adicionar ao carrinho", "Sair"};
			int opt = menu(opcs);
			if (opt == 0) tc.adicionarTipo(tipos);
			else if (opt == 1) tc.listarTipos(tipos);
			else if (opt == 2) {
				int cod = Integer.parseInt(
						JOptionPane.showInputDialog(
								"Informe o c�digo do tipo a ser exclu�do:"));
				tc.excluirTipoPorCod(tipos, cod);
			} else if (opt == 3) {
				int cod = Integer.parseInt(
						JOptionPane.showInputDialog(
								"Informe o c�digo do tipo a ser consultado:"));
				tc.consultarTipoPorCod(tipos, cod);
			} else if (opt ==4) {
				String[] opc = {"Cadastra Produto", "Excluir Produto", "Atualizar Produto",
						"Lista Produtos","Sair"};
				int optProd= menu(opc);
				switch (optProd)
				{
				case 0:
					int cod = Integer.parseInt(JOptionPane.showInputDialog(
								"Informe o cod do produto a ser cadastrado"));
				try {
					TipoProduto tipagem = null;
					ProductController.cadastrarProduto(cod, "nome", "descricao", 1.0, 1, tipagem);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				case 1:
					int codExclud = Integer.parseInt(JOptionPane.showInputDialog(
								"Informe o cod do produto a ser excluido"));
				try {
					ProductController.excluirProduto(codExclud);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				case 2:
					int codAt = Integer.parseInt(JOptionPane.showInputDialog(
								"Informe o cod do produto a ser atualizado"));
				try {
					TipoProduto tipagem = null;
					ProductController.atualizarProduto(codAt, "nomeatualizado", "descricao", 1.0, 1, tipagem);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				case 4:
					break;
				}
				
			} 
			
			else
				break;
		}
		
		
	}
	
	private static int menu(String [] opcs)
	{
		
		return JOptionPane.showOptionDialog(
			null, "Selecione a op��o desejada", "Impressora",
			JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
			opcs, opcs[3]);
	}
	
	
}
