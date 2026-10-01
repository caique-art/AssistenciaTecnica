package OrdemServico;

import Clientes.Clientes;

public class OrdemServico extends Produto {

	private String servico;
	private String defeito;
	private Status status;
	private Clientes Cliente;

	public OrdemServico(String produto, String marca, String modelo, Clientes cliente) {
	    super(produto, marca, modelo);
	    this.Cliente = cliente;
	    status = Status.NAO_ABERTA;
	}

	public String getDefeito() {
		return defeito;
	}

	public void setDefeito(String defeito) {
		this.defeito = defeito;
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
		return Cliente;
	}

	public void setCliente(Clientes cliente) {
		Cliente = cliente;
	}
}
