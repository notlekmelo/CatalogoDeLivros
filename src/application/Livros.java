package application;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Livros implements Entidade, Comparable<Livros>{
	private String nome, editora, autor, status, genero, classificacao, edicao;
	private int id;

	public Livros(String nome, String editora, String autor, String status, String genero, String classificacao,
			String edicao) {
		this.nome = nome;
		this.editora = editora;
		this.autor = autor;
		this.status = status;
		this.genero = genero;
		this.classificacao = classificacao;
		this.edicao = edicao;
	}

	public String getNome() {
		return nome;
	}

	public String getEditora() {
		return editora;
	}

	public String getAutor() {
		return autor;
	}

	public String getStatus() {
		return status;
	}

	public String getGenero() {
		return genero;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public String getEdicao() {
		return edicao;
	}

	@Override
	public String toString() {
		return nome + " | Autor: " + autor + " | Editora: " + editora + " | Edição: " + edicao + " | Gênero: "
				+ genero + " | Status: " + status  + " | Classificação: " + classificacao;
	}

	public Livros() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setId(int codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] getByteArray() throws IOException {
		//essa função parece ter um problema
		 ByteArrayOutputStream dados = new ByteArrayOutputStream();
	     DataOutputStream saida = new DataOutputStream( dados );
	        saida.writeInt(this.id);
	        saida.writeUTF(this.nome);
	        saida.writeUTF(this.autor);
	        saida.writeUTF(this.editora);
	        saida.writeUTF(this.edicao);
	        saida.writeUTF(this.genero);
	        saida.writeUTF(this.status);
	        saida.writeUTF(this.classificacao);
	        return dados.toByteArray();
	}

	@Override
	public void setByteArray(byte[] b) throws IOException {
		 ByteArrayInputStream dados = new ByteArrayInputStream(b);
	     DataInputStream entrada = new DataInputStream(dados);
	        this.id = entrada.readInt();
	        this.nome = entrada.readUTF();
	        this.autor = entrada.readUTF();
	        this.editora = entrada.readUTF();
	        this.edicao = entrada.readUTF();
	        this.genero = entrada.readUTF();
	        this.status = entrada.readUTF();
	        this.classificacao = entrada.readUTF();

	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	@Override
	public int compareTo(Livros arg0) {
		if(arg0.getAutor().compareTo(this.autor)==0)
		return 0;
		else if(arg0.getAutor().compareToIgnoreCase(this.autor)>0)
			return -1;
		else return 1;
	}
	
	

}
