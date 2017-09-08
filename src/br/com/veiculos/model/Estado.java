package br.com.veiculos.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.veiculos.model.xml.DateAdapter;

public class Estado extends BaseModel implements Serializable {

	private static final long serialVersionUID = -3064306490724801147L;

	private String nome;
	private String sigla;
	
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date data;
	private double populacao;
	private double pib;

	public Estado() {
		super();

	}

	public Estado(Long id) {
		this();
		setId(id);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getPopulacao() {
		return populacao;
	}

	public void setPopulacao(double populacao) {
		this.populacao = populacao;
	}

	public double getPib() {
		return pib;
	}

	public void setPib(double pib) {
		this.pib = pib;
	}

}
