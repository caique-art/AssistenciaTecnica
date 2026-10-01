package OrdemServico;

public class Produto {
	private String produto;
	private String marca;
	private String modelo;
	
	public Produto(String produto, String marca, String modelo) {
	    this.produto = produto;
	    this.marca = marca;
	    this.modelo = modelo;
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

}
