package br.com.veiculos.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.veiculos.model.xml.DateAdapter;
import br.com.veiculos.model.xml.PlacaAdapter;

public class Veiculo extends BaseModel implements Serializable {

	private static final long serialVersionUID = -3064306490724801147L;
	
	@NotNull
	@Size(min = 7, max = 7)
	@XmlJavaTypeAdapter(PlacaAdapter.class)
	private String placa;
	
	@NotNull
	@Size(min = 3, max = 100)
	private String proprietario;
	
	@NotNull
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date dataEmplacamento;
	
	@NotNull
	private double valorEmplacamento;
	
	public Veiculo() {
		super();
	}

	public Veiculo(Long id) {
		this();
		setId(id);
	}
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getProprietario() {
		return proprietario;
	}
	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}
	public Date getDataEmplacamento() {
		return dataEmplacamento;
	}
	public void setDataEmplacamento(Date dataEmplacamento) {
		this.dataEmplacamento = dataEmplacamento;
	}
	public double getValorEmplacamento() {
		return valorEmplacamento;
	}
	public void setValorEmplacamento(double valorEmplacamento) {
		this.valorEmplacamento = valorEmplacamento;
	}
}
