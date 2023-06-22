package fila.mensagem.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeCliente;
	private String marcaModeloVeiculo;
	private int anoModelo;
	private double valorVenda;
	private Date dataPublicacao;

	public Veiculo(String nomeCliente, String marcaModeloVeiculo, int anoModelo, double valorVenda,
			Date dataPublicacao) {
		setNomeCliente(nomeCliente);
		setMarcaModeloVeiculo(marcaModeloVeiculo);
		setAnoModelo(anoModelo);
		setValorVenda(valorVenda);
		setDataPublicacao(dataPublicacao);
	}

	public Veiculo() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getMarcaModeloVeiculo() {
		return marcaModeloVeiculo;
	}

	public void setMarcaModeloVeiculo(String marcaModeloVeiculo) {
		this.marcaModeloVeiculo = marcaModeloVeiculo;
	}

	public int getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(int anoModelo) {
		this.anoModelo = anoModelo;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

}