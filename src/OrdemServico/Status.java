package OrdemServico;

public enum Status {
	NAO_ABERTA("Não aberta"),
	ABERTA("Aberta"),
	EM_MANUTENCAO("Produto em manutenção"),
	FINALIZANDO("Produto sendo finalizado"),
	PRONTO_PARA_RETIRADA("O seu produto esta pronto para retirada");
	
	private String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
