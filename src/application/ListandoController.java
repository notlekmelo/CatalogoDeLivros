package application;

import javafx.scene.control.ToggleGroup;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
public class ListandoController {

	@FXML
	private TextField search;

	@FXML
	private TextArea write;

	@FXML
	private RadioButton title,autor,edit;

	@FXML
	private ToggleGroup busca;

	@FXML
	private void search(MouseEvent e) throws Exception {
		write.setText("");
		Arquivo file = new Arquivo(Livros.class.getConstructor(),"Meus livros.db");
		Object[] result;
		if(busca.getSelectedToggle().equals(autor)) 
			result = file.buscarAutor(search.getText());
		else if(busca.getSelectedToggle().equals(edit))
			result = file.buscarEditora(search.getText());
		else 
			result = file.buscarNome(search.getText());
		
		int tam = result.length;
		for(int i=0; i<tam;i++)
			write.setText(write.getText()+"\n"  + result[i]);
		file.fechaArq();
	}

	@FXML
	private void lista(MouseEvent e) throws Exception {
		write.setText("");
		Arquivo file = new Arquivo(Livros.class.getConstructor(),"Meus livros.db");
		Object[] lista = file.listar();
		for(int i = 0; i< lista.length; i++) {
			write.setText(write.getText() + "\n" + lista[i].toString());
		}
		file.fechaArq();
	}

	@FXML
	private void back(MouseEvent e) {
		Main janela = new Main();
		janela.loadScene("Principal.fxml", "Bem Vindo!");
	}
}
