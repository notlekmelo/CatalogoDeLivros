package application;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Arquivo<T extends Entidade> {

	RandomAccessFile arquivo;
	String nomeArquivo;
	Constructor<T> construtor;

	public Arquivo(Constructor<T> c, String n) throws Exception {
		nomeArquivo = n;
		construtor = c;
		arquivo = new RandomAccessFile(nomeArquivo, "rw");
		if(arquivo.length()<4)
			arquivo.writeInt(0);
	}

	public int incluir(Livros obj) throws Exception {
		arquivo.seek(0);
		int id = arquivo.readInt();
		id++;
		obj.setId(id);
		arquivo.seek(0);
		arquivo.writeInt(id);

		arquivo.seek(arquivo.length());
		arquivo.writeByte(' ');
		byte[] b = obj.getByteArray();
		arquivo.writeInt(b.length);
		arquivo.write(b);

		return id;
	}

	public Object[] listar() throws Exception {

		// Em um sistema real, o número de registros será muito superior ao que
		// um ArrayList poderia comportar em memória. Esta operação está aqui
		// apenas para facilitar a depuração do código
		ArrayList<T> lista = new ArrayList<>();
		arquivo.seek(4);
		byte lapide;
		byte[] b;
		int s;
		T obj;
		while(arquivo.getFilePointer()<arquivo.length()) {
			obj = construtor.newInstance();
			lapide = arquivo.readByte();
			s = arquivo.readInt();
			b = new byte[s];
			arquivo.read(b);
			obj.setByteArray(b);
			if(lapide==' ')
				lista.add(obj);
		}
		Object[] ls = lista.stream().sorted().toArray();
		return ls;
	}

	public Object[] buscarNome(String nome) throws Exception {

		byte lapide;
		byte[] b;
		int s;
		ArrayList<Entidade> result = new ArrayList<Entidade>();
		arquivo.seek(4);
		while(arquivo.getFilePointer() < arquivo.length()) {
			Livros obj = new Livros();
			lapide = arquivo.readByte();
			s = arquivo.readInt();
			b = new byte[s];
			arquivo.read(b);
			obj.setByteArray(b);
			if(lapide == ' ' && obj.getNome().equalsIgnoreCase(nome)) {
				result.add(obj);
			}
		}
		return result.stream().sorted().toArray();
	}

	public Object[] buscarAutor(String nome) throws Exception {

		byte lapide;
		byte[] b;
		int s;
		ArrayList<Entidade> result = new ArrayList<Entidade>();
		arquivo.seek(4);
		while(arquivo.getFilePointer() < arquivo.length()) {
			Livros obj = new Livros();
			lapide = arquivo.readByte();
			s = arquivo.readInt();
			b = new byte[s];
			arquivo.read(b);
			obj.setByteArray(b);
			if(lapide == ' ' && obj.getAutor().equalsIgnoreCase(nome))
				result.add(obj);
		}
		return result.toArray();
	}
	
	public Object[] buscarEditora(String nome) throws Exception {

		byte lapide;
		byte[] b;
		int s;
		ArrayList<Entidade> result = new ArrayList<Entidade>();
		arquivo.seek(4);
		while(arquivo.getFilePointer() < arquivo.length()) {
			Livros obj = new Livros();
			lapide = arquivo.readByte();
			s = arquivo.readInt();
			b = new byte[s];
			arquivo.read(b);
			obj.setByteArray(b);
			if(lapide == ' ' && obj.getEditora().equalsIgnoreCase(nome))
				result.add(obj);
		}
		return result.stream().sorted().toArray();
	}
	
	public boolean alterar(String nome, String edicao) throws Exception {

		Livros obj = new Livros();
		byte lapide;
		byte[] b;
		int s;
		long endereco;

		arquivo.seek(4);
		while(arquivo.getFilePointer() < arquivo.length()) {
			endereco = arquivo.getFilePointer();
			lapide = arquivo.readByte();
			s = arquivo.readInt();
			b = new byte[s];
			arquivo.read(b);
			obj.setByteArray(b);
			if(lapide == ' ' && obj.getNome().equals(nome) && obj.getEdicao().equals(edicao)) {
				int op = Integer.parseInt(JOptionPane.showInputDialog("Digite o campo a ser alterado: \nDigite 1 para Nome; \nDigite 2 para Autor; \nDigite 3 para Editora;"
						+	"\nDigite 4 para Edição; \nDigite 5 para Gênero; \nDigite 6 para Status; \nDigite 7 para Classificação."));
				switch(op) {
				case 1:
					String novo = JOptionPane.showInputDialog("Digite o novo nome: ");
					if(novo.length()>obj.getNome().length()) {
						arquivo.seek(endereco);
						arquivo.write('*');
						obj.setNome(novo);
						incluir(obj);
						return true;
					} else if(novo.length()<obj.getNome().length()) {
						novo = reformulaTam(obj.getNome(),novo);
					}
					obj.setNome(novo);
					break;
				case 2:
					novo  = JOptionPane.showInputDialog("Digite o novo autor: ");
					if(novo.length()>obj.getAutor().length()) {
						arquivo.seek(endereco);
						arquivo.write('*');
						obj.setAutor(novo);
						incluir(obj);
						return true;
					} else if(novo.length()<obj.getAutor().length()) {
						novo = reformulaTam(obj.getAutor(),novo);

					}
					obj.setAutor(novo);
					break;
				case 3:
					novo  = JOptionPane.showInputDialog("Digite a nova editora: ");
					if(novo.length()>obj.getEditora().length()) {
						arquivo.seek(endereco);
						arquivo.write('*');
						obj.setEditora(novo);
						incluir(obj);
						return true;
					} else if(novo.length()<obj.getEditora().length()) {
						novo = reformulaTam(obj.getEditora(),novo);

					}
					obj.setEditora(novo);
					break;
				case 4:
					novo  = JOptionPane.showInputDialog("Digite a nova edição: ");
					if(novo.length()>obj.getEdicao().length()) {
						arquivo.seek(endereco);
						arquivo.write('*');
						obj.setEdicao(novo);
						incluir(obj);
						return true;
					} else if(novo.length()<obj.getEdicao().length()) {
						novo = reformulaTam(obj.getEdicao(),novo);

					}
					obj.setEdicao(novo);
					break;
				case 5:
					novo  = JOptionPane.showInputDialog("Digite o novo gênero: ");
					if(novo.length()>obj.getGenero().length()) {
						arquivo.seek(endereco);
						arquivo.write('*');
						obj.setGenero(novo);
						incluir(obj);
						return true;
					} else if(novo.length()<obj.getGenero().length()) {
						novo = reformulaTam(obj.getGenero(),novo);
					}
					obj.setGenero(novo);
					break;
				case 6:
					novo  = JOptionPane.showInputDialog("Digite o novo status: ");
					if(novo.length()>obj.getStatus().length()) {
						arquivo.seek(endereco);
						arquivo.write('*');
						obj.setStatus(novo);
						incluir(obj);
						return true;
					} else if(novo.length()<obj.getStatus().length()) {
						novo = reformulaTam(obj.getStatus(),novo);
					}
					obj.setStatus(novo);
					break;
				case 7:
					novo  = JOptionPane.showInputDialog("Digite a nova: classificação");
					novo = reformulaTam("              .",novo);
					obj.setClassificacao(novo);
					break;
				}
				
				arquivo.seek(endereco);
				arquivo.write(' ');
				byte[] e = obj.getByteArray();
				arquivo.writeInt(e.length);
				arquivo.write(e);
				return true;
			}
		}
		return false;
	}  
	
	private String reformulaTam(String atual, String nova) throws IOException {
		if(atual.length()>nova.length()) {
			for(;nova.length()<atual.length();)
				nova = nova + " ";
		}
		return nova;
	}

	public void fechaArq() {
		try {
			arquivo.close();
		} catch (IOException e) {
			System.out.println("Erro ao fechar arquivo");
		}
	}
}
