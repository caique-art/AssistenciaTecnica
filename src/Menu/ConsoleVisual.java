package Menu;

public final class ConsoleVisual {

    private static final int LARGURA = 66;

    private ConsoleVisual() {

    }

    public static void linha() {
        System.out.println("  +" + "-".repeat(LARGURA + 2) + "+");
    }

    public static void texto(String valor) {

        String conteudo = valor == null || valor.isBlank()
                ? "Não informado"
                : valor;

        for (String trecho : conteudo.split("\\R", -1)) {

            do {

                int fim = Math.min(LARGURA, trecho.length());

                System.out.printf(
                        "  | %-" + LARGURA + "s |%n",
                        trecho.substring(0, fim)
                );

                trecho = trecho.substring(fim);

            } while (!trecho.isEmpty());
        }
    }

    public static void titulo(String titulo, String descricao) {

        System.out.println();

        linha();

        texto(titulo);
        texto(descricao);

        linha();
    }

    public static void secao(String titulo) {

        System.out.println();

        linha();

        texto(titulo);

        linha();
    }

    public static void campo(String rotulo, String valor) {

        texto(
            String.format(
                "%-12s : %s",
                rotulo,
                valor == null || valor.isBlank()
                        ? "Não informado"
                        : valor
            )
        );
    }

    public static void pedir(String rotulo) {

        System.out.printf("  > %-22s: ", rotulo);
    }

    public static void sucesso(String mensagem) {

        System.out.println("\n  [OK] " + mensagem);
    }

    public static void aviso(String mensagem) {

        System.out.println("\n  [ATENÇÃO] " + mensagem);
    }


    public static void menuCliente() {

        titulo(
            "ASSISTÊNCIA TÉCNICA",
            "Central de atendimento | Área do cliente"
        );

        texto("MENU DO CLIENTE");

        texto("  [1] Cadastrar equipamento");
        texto("  [2] Consultar equipamento");
        texto("  [3] Consultar ordem de serviço");
        texto("  [0] Sair");

        linha();

        pedir("Escolha uma opção");
    }

    public static void menuFuncionario() {

        titulo(
            "ASSISTÊNCIA TÉCNICA",
            "Central de atendimento | Área do funcionário"
        );

        texto("MENU DO FUNCIONÁRIO");

        texto("CADASTROS");

        texto("  [1] Cadastrar cliente");
        texto("  [2] Cadastrar funcionário");

        linha();

        texto("ORDENS DE SERVIÇO");

        texto("  [3] Abrir ordem de serviço");
        texto("  [4] Consultar ordem de serviço");
        texto("  [5] Alterar status da OS");

        linha();

        texto("EXCLUSÃO");

        texto("  [6] Deletar cadastro/equipamento");

        linha();

        texto("  [0] Sair");

        linha();

        pedir("Escolha uma opção");
    }

    public static void menuServico() {

        titulo(
            "ASSISTÊNCIA TÉCNICA",
            "Central de atendimento | Tipo de serviços"
        );

        texto("SERVIÇOS");

        texto("  [1] Limpeza");
        texto("  [2] Manutenção preventiva");
        texto("  [3] Conserto");
    }

    public static void menuDefeito() {

        titulo(
            "ASSISTÊNCIA TÉCNICA",
            "Central de atendimento | Tipo de serviços"
        );

        texto("SERVIÇOS");

        texto("O aparelho apresenta defeitos?");

        texto("  [1] Sim");
        texto("  [2] Não");
    }

    public static void menuDelete() {

        titulo(
            "ASSISTÊNCIA TÉCNICA",
            "Central de atendimento | Exclusão"
        );

        texto("EXCLUIR");

        texto("  [1] Cadastro");
        texto("  [2] Equipamento");
        texto("  [3] Sair");
    }

    public static void menuStatus() {

        titulo(
            "ASSISTÊNCIA TÉCNICA",
            "Central de atendimento | Alterar Status da OS"
        );

        texto("STATUS");

        texto("  [1] ABERTA");
        texto("  [2] EM_MANUTENCAO");
        texto("  [3] FINALIZANDO");
        texto("  [4] PRONTO_PARA_RETIRADA");
        texto("  [5] Sair");
    }
    public static void menuAcesso() {

        titulo(
            "ASSISTÊNCIA TÉCNICA",
            "Central de atendimento | Acesso ao sistema"
        );

        texto("ACESSO");

        texto("  [1] Entrar");
        texto("  [2] Criar cadastro");
        texto("  [0] Sair");

        linha();

        pedir("Escolha uma opção");
    }
    public static void menuCadastro() {

        titulo(
            "ASSISTÊNCIA TÉCNICA",
            "Central de atendimento | Cadastro"
        );

        texto("NOVO CADASTRO");

        texto("  [1] Cadastro de cliente");
        texto("  [2] Cadastro de funcionário");
        texto("  [0] Voltar");

        linha();

        pedir("Escolha uma opção");
    }
    public static void loginCliente() {

        titulo(
            "ASSISTÊNCIA TÉCNICA",
            "Central de atendimento | Login do cliente"
        );

        texto("LOGIN DO CLIENTE");

        pedir("CPF");
    }
    public static void loginFuncionario() {

        titulo(
            "ASSISTÊNCIA TÉCNICA",
            "Central de atendimento | Login do funcionário"
        );

        texto("LOGIN DO FUNCIONÁRIO");

        pedir("Nº funcionário");

        pedir("Senha");
    }
}
