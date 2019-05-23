package application;


import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class PrincipalController {

	@FXML
	private void catalogar(MouseEvent e){
	Main janela = new Main();
	janela.loadScene("Catalogar.fxml", "Mais um pra cole��o!!");
	}
	
	@FXML
	private void listar(MouseEvent e){
	Main janela = new Main();
	janela.loadScene("Listando.fxml", "Minha cole��o!!");
	}
	
	@FXML
	private void alterar(MouseEvent e) throws Exception {
		String nome = JOptionPane.showInputDialog("Digite o nome do livro:");
		String ed = JOptionPane.showInputDialog("Digite a edi��o do livro: ");
		Arquivo file = new Arquivo(Livros.class.getConstructor(),"Meus livros.db");
		if(file.alterar(nome, ed))
			JOptionPane.showMessageDialog(null,"Alterado com sucesso");
		else JOptionPane.showMessageDialog(null, "N�o foi poss�vel encontrar este livro.");
		file.fechaArq();
	}
	
}
