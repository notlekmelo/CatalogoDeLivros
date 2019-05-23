package application;


import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class CatalogarController {

	private String star="";

	@FXML
	private Label msg;


	@FXML
	private TextField nome,autor,editora,edicao,genero,status;

	@FXML
	private RadioButton nClas;

	@FXML
	private void cadastro(MouseEvent e) throws Exception {
			if(star.equals(""))
				star = "Nao Classificado";
			Livros cad = new Livros(nome.getText(),editora.getText(),autor.getText(),status.getText(),genero.getText(),star,edicao.getText());
			Arquivo file = new Arquivo(Livros.class.getConstructor(),"Meus livros.db");
			file.incluir(cad);
			JOptionPane.showMessageDialog(null,"Livro adicionado a sua estante virtual.");
			nome.setText(" ");
			editora.setText("");
			autor.setText("");
			status.setText("");
			genero.setText("");
			edicao.setText("");
			star = " ";
			msg.setText(" ");
			file.fechaArq();
	}

	@FXML
	private void star1(MouseEvent e) {
		star = "1 estrela      .";
		msg.setText("Tá bom não");
	}

	@FXML
	private void star2(MouseEvent e) {
		star = "2 estrelas      .";
		msg.setText("Podia ser melhor");
	}

	@FXML
	private void star3(MouseEvent e) {
		star = "3 estrelas      .";
		msg.setText("Ta razoável");
	}

	@FXML
	private void star4(MouseEvent e) {
		star = "4 estrelas      .";
		msg.setText("Ta bom");
	}

	@FXML
	private void star5(MouseEvent e) {
		star = "5 estrelas      .";
		msg.setText("PER-FEI-TO!!");
	}

	@FXML
	private void back(MouseEvent e) {
		Main janela = new Main();
		janela.loadScene("Principal.fxml", "Bem Vindo!");
	}
}
