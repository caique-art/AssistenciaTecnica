package OrdemServico;

import login.Clientes;

public class OrdemServico extends Produto {

	private String servico;
	private String defeito;
	private Status status;
	private Clientes cliente;
	private int senha;

	public OrdemServico(String produto, String marca, String modelo, Clientes cliente) {
	    super(produto, marca, modelo);
	    this.cliente = cliente;
	    status = Status.NAO_ABERTA;
	}
	
	public OrdemServico() {
		super("", "", "");
		status = Status.NAO_ABERTA;
	}

	public String getDefeito() {
		return defeito;
	}

	public void setDefeito(String defeito) {
		this.defeito = defeito;
	}
	
	public void setStatus(String statusStr) {
		// Apenas para compatibilidade com o Menu antigo que usava string
		this.status = Status.ABERTA; 
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico=servico;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}
	
	public int getSenha() {
		return senha;
	}
	
	public void setSenha(int senha) {
		this.senha = senha;
	}
}
