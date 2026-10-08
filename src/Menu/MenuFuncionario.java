package Menu;

import java.util.ArrayList;
import java.util.Scanner;

import OrdemServico.OrdemServico;
import OrdemServico.Status;
import cadastros.CadastroClientes;
import cadastros.CadastroFuncionario;
import login.Clientes;
import login.Funcionario;

public class MenuFuncionario {

	private Scanner sc;
	private Funcionario funcionario;

	private ArrayList<OrdemServico> listaOrdens;
	private ArrayList<Clientes> listaClientes;
	private ArrayList<Funcionario> listaFuncionario;

	private CadastroClientes cadastroClientes;
	private CadastroFuncionario cadastroFuncionario;

	public MenuFuncionario(ArrayList<OrdemServico> listaOrdens,
			ArrayList<Clientes> listaClientes,
			ArrayList<Funcionario> listaFuncionario,
			Funcionario funcionario,
			Scanner sc) {

		this.listaOrdens = listaOrdens;
		this.listaClientes = listaClientes;
		this.listaFuncionario = listaFuncionario;
		this.funcionario = funcionario;
		this.sc = sc;

		this.cadastroClientes = new CadastroClientes(listaClientes, sc);
		this.cadastroFuncionario = new CadastroFuncionario(listaFuncionario, sc);
	}

	public void menu() {

		int escolha;

		do {

			ConsoleVisual.titulo("MENU FUNCIONÁRIO",
					"ASSISTÊNCIA TÉCNICA | Funcionário: " + funcionario.getNome());

			System.out.println("1 - Cadastrar cliente");
			System.out.println("2 - Cadastrar funcionário");
			System.out.println("3 - Abrir ordem de serviço");
			System.out.println("4 - Consultar ordem de serviço");
			System.out.println("5 - Alterar status da OS");
			System.out.println("6 - Excluir cadastro/equipamento");
			System.out.println("0 - Sair");

			ConsoleVisual.pedir("Escolha uma opção");
			escolha = sc.nextInt();
			sc.nextLine();

			switch (escolha) {

			case 1:
				cadastroClientes.cadastrarClientes();
				break;

			case 2:
				cadastroFuncionario.cadastrarFuncionario();
				break;

			case 3:
				abrirOS();
				break;

			case 4:
				consultarOS();
				break;

			case 5:
				alterarStatus();
				break;

			case 6:
				deletar();
				break;

			case 0:
				ConsoleVisual.texto("Saindo...");
				break;

			default:
				ConsoleVisual.aviso("Digite uma opção válida!");
			}

		} while (escolha != 0);
	}

	public void abrirOS() {

		ConsoleVisual.titulo("ABRIR ORDEM DE SERVIÇO",
				"ASSISTÊNCIA TÉCNICA | Atendimento");

		ConsoleVisual.pedir("Digite o CPF do cliente");
		String cpf = sc.nextLine();

		Clientes clienteEncontrado = buscarClientePorCpf(cpf);

		if (clienteEncontrado == null) {
			ConsoleVisual.aviso("CPF inválido!");
			return;
		}

		if (listaOrdens.isEmpty()) {
			ConsoleVisual.aviso("Cadastre um equipamento primeiro.");
			return;
		}

		ArrayList<OrdemServico> ordensCliente = new ArrayList<>();

		for (OrdemServico os : listaOrdens) {

			if (cpf.equals(os.getCliente().getCpf())) {
				ordensCliente.add(os);
			}
		}

		if (ordensCliente.isEmpty()) {
			ConsoleVisual.aviso("Este cliente não possui equipamentos.");
			return;
		}

		for (int i = 0; i < ordensCliente.size(); i++) {
			exibirEquipamento(i, ordensCliente.get(i));
		}

		ConsoleVisual.pedir("Número do equipamento");
		int opcao = sc.nextInt();
		sc.nextLine();

		if (opcao < 1 || opcao > ordensCliente.size()) {
			ConsoleVisual.aviso("Opção inválida.");
			return;
		}

		OrdemServico selecionada = ordensCliente.get(opcao - 1);

		ConsoleVisual.menuServico();
		int escolha = sc.nextInt();
		sc.nextLine();

		switch (escolha) {

		case 1:

			selecionada.setServico("Limpeza");
			selecionada.setStatus(Status.ABERTA);

			ConsoleVisual.sucesso("Ordem de serviço aberta com sucesso.");
			break;

		case 2:

			ConsoleVisual.menuDefeito();
			int escolha1 = sc.nextInt();
			sc.nextLine();

			switch (escolha1) {

			case 1:

				selecionada.setServico("Defeito");

				ConsoleVisual.pedir("Qual o defeito?");
				String defeito = sc.nextLine();

				selecionada.setDefeito(defeito);
				selecionada.setStatus(Status.ABERTA);

				ConsoleVisual.sucesso("Ordem de serviço aberta com sucesso.");
				break;

			case 2:

				selecionada.setServico("Manutenção Preventiva");
				selecionada.setStatus(Status.ABERTA);

				ConsoleVisual.sucesso("Ordem de serviço aberta com sucesso.");
				break;

			default:

				ConsoleVisual.aviso("Digite uma opção válida!");
				break;
			}

			break;

		case 3:

			selecionada.setServico("Defeito");

			ConsoleVisual.pedir("Defeito relatado:");
			String defeito = sc.nextLine();

			selecionada.setDefeito(defeito);
			selecionada.setStatus(Status.ABERTA);

			ConsoleVisual.sucesso("Ordem de serviço aberta com sucesso.");
			break;

		default:

			ConsoleVisual.aviso("Digite uma opção válida!");
			break;
		}
	}

	public void consultarOS() {

		ConsoleVisual.titulo("CONSULTAR ORDENS DE SERVIÇO",
				"ASSISTÊNCIA TÉCNICA | Atendimento");

		ConsoleVisual.pedir("Digite o CPF do cliente");
		String cpf = sc.nextLine();

		Clientes clienteEncontrado = buscarClientePorCpf(cpf);

		if (clienteEncontrado == null) {
			ConsoleVisual.aviso("CPF inválido!");
			return;
		}

		if (listaOrdens.isEmpty()) {
			ConsoleVisual.aviso("Nenhuma ordem de serviço cadastrada.");
			return;
		}

		ConsoleVisual.secao("CLIENTE");
		ConsoleVisual.campo("Nome", clienteEncontrado.getNome());
		ConsoleVisual.campo("CPF", clienteEncontrado.getCpf());
		ConsoleVisual.campo("Telefone", clienteEncontrado.getTelefone());
		ConsoleVisual.linha();

		boolean encontrou = false;

		for (int i = 0; i < listaOrdens.size(); i++) {

			OrdemServico selecionada = listaOrdens.get(i);

			if (cpf.equals(selecionada.getCliente().getCpf())) {

				exibirEquipamento(i, selecionada);
				encontrou = true;
			}
		}

		if (!encontrou) {
			ConsoleVisual.aviso("Este cliente não possui equipamentos.");
		}
	}

	public void alterarStatus() {

		ConsoleVisual.titulo("ALTERAR STATUS DA OS",
				"ASSISTÊNCIA TÉCNICA | Atendimento");

		if (listaOrdens.isEmpty()) {
			ConsoleVisual.aviso("Nenhuma ordem de serviço cadastrada.");
			return;
		}

		ConsoleVisual.pedir("Digite o CPF do cliente");
		String cpf = sc.nextLine();

		Clientes clienteEncontrado = buscarClientePorCpf(cpf);

		if (clienteEncontrado == null) {
			ConsoleVisual.aviso("CPF inválido!");
			return;
		}

		ArrayList<OrdemServico> ordensCliente = new ArrayList<>();

		for (OrdemServico os : listaOrdens) {

			if (cpf.equals(os.getCliente().getCpf())) {
				ordensCliente.add(os);
			}
		}

		if (ordensCliente.isEmpty()) {
			ConsoleVisual.aviso("Este cliente não possui equipamentos.");
			return;
		}

		for (int i = 0; i < ordensCliente.size(); i++) {
			exibirEquipamento(i, ordensCliente.get(i));
		}

		ConsoleVisual.pedir("Número do equipamento");
		int opcao = sc.nextInt();
		sc.nextLine();

		if (opcao < 1 || opcao > ordensCliente.size()) {
			ConsoleVisual.aviso("Opção inválida.");
			return;
		}

		OrdemServico selecionada = ordensCliente.get(opcao - 1);

		if (selecionada.getStatus() == Status.NAO_ABERTA) {
			ConsoleVisual.aviso("Essa ordem de serviço ainda não foi aberta.");
			return;
		}

		ConsoleVisual.menuStatus();

		int escolha = sc.nextInt();
		sc.nextLine();

		switch (escolha) {

		case 1:
			selecionada.setStatus(Status.ABERTA);
			break;

		case 2:
			selecionada.setStatus(Status.EM_MANUTENCAO);
			break;

		case 3:
			selecionada.setStatus(Status.FINALIZANDO);
			break;

		case 4:
			selecionada.setStatus(Status.PRONTO_PARA_RETIRADA);
			break;

		case 5:
			return;

		default:
			ConsoleVisual.aviso("Digite uma opção válida!");
			return;
		}

		ConsoleVisual.sucesso("Status atualizado com sucesso.");
	}

	public void deletar() {

		ConsoleVisual.titulo("EXCLUIR CADASTRO OU EQUIPAMENTO",
				"ASSISTÊNCIA TÉCNICA | Atendimento");

		ConsoleVisual.pedir("Digite o CPF do cliente");
		String cpf = sc.nextLine();

		Clientes clienteEncontrado = buscarClientePorCpf(cpf);

		if (clienteEncontrado == null) {
			ConsoleVisual.aviso("CPF inválido!");
			return;
		}

		ArrayList<OrdemServico> ordensCliente = new ArrayList<>();

		for (OrdemServico os : listaOrdens) {

			if (cpf.equals(os.getCliente().getCpf())) {
				ordensCliente.add(os);
			}
		}

		ConsoleVisual.menuDelete();

		int escolha = sc.nextInt();
		sc.nextLine();

		switch (escolha) {

		case 1:

			listaOrdens.removeAll(ordensCliente);
			listaClientes.remove(clienteEncontrado);

			ConsoleVisual.sucesso("Cadastro excluído.");
			break;

		case 2:

			if (ordensCliente.isEmpty()) {
				ConsoleVisual.aviso("Este cliente não possui equipamentos.");
				return;
			}

			for (int i = 0; i < ordensCliente.size(); i++) {
				exibirEquipamento(i, ordensCliente.get(i));
			}

			ConsoleVisual.pedir("Número do equipamento");

			int opcao = sc.nextInt();
			sc.nextLine();

			if (opcao < 1 || opcao > ordensCliente.size()) {
				ConsoleVisual.aviso("Opção inválida.");
				return;
			}

			OrdemServico selecionada = ordensCliente.get(opcao - 1);

			listaOrdens.remove(selecionada);

			ConsoleVisual.sucesso("Equipamento excluído.");
			break;

		default:

			ConsoleVisual.aviso("Digite uma opção válida!");
		}
	}

	private void exibirEquipamento(int indice, OrdemServico equipamento) {

		ConsoleVisual.secao("EQUIPAMENTO [" + (indice + 1) + "]");

		ConsoleVisual.campo("Produto", equipamento.getProduto());
		ConsoleVisual.campo("Marca", equipamento.getMarca());
		ConsoleVisual.campo("Modelo", equipamento.getModelo());
		ConsoleVisual.campo("Serviço pedido:", equipamento.getServico());

		ConsoleVisual.campo("Defeito:",
				equipamento.getDefeito() == null
						? "Nenhum defeito informado"
						: equipamento.getDefeito());

		ConsoleVisual.campo("Status",
				equipamento.getStatus().toString());

		ConsoleVisual.linha();
	}

	private Clientes buscarClientePorCpf(String cpf) {

		for (Clientes x : listaClientes) {

			if (cpf.equals(x.getCpf())) {
				return x;
			}
		}

		return null;
	}
}
