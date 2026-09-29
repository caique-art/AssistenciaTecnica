package OrdemServico;

import Clientes.Clientes;

public class OrdemServico {

	private String produto;
	private String marca;
	private String modelo;
	private String servico;
	private String defeito;
	private Status status;
	private int senha;
	private Clientes Cliente;

	public OrdemServico() {
		status=Status.NAO_ABERTA;
	}

	public String getDefeito() {
		return defeito;
	}

	public void setDefeito(String defeito) {
		this.defeito = defeito;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getSenha() {
		return senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}
	
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico=servico;
	}

	public Clientes getCliente() {
		return Cliente;
	}

	public void setCliente(Clientes cliente) {
		Cliente = cliente;
	}
}
