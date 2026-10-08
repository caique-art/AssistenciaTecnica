package OrdemServico;

public enum StatusOS {
    ABERTA("Aberta"),
    EM_ANDAMENTO("Em Andamento"),
    AGUARDANDO_PECA("Aguardando Peça"),
    CONCLUIDA("Concluída"),
    CANCELADA("Cancelada");

    private final String descricao;

    StatusOS(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
