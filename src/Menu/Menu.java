package Menu;

import java.util.ArrayList;
import java.util.Scanner;

import Clientes.Clientes;
import OrdemServico.OrdemServico;
import OrdemServico.Status;

public class Menu {
	private Scanner sc;
	OrdemServico os;
	Clientes clienteEncontradado = null;
	ArrayList<OrdemServico> listaOrdens;
	ArrayList<Clientes> listaClientes;
	CadastroClientes cadastroClientes;

	public Menu(ArrayList<OrdemServico> listaOrdens, ArrayList<Clientes> listaClientes, Scanner sc) {
		this.listaOrdens = listaOrdens;
		this.listaClientes = listaClientes;
		this.sc = sc;
	}

	public void AbrirOS() {
		ConsoleVisual.titulo("ABRIR ORDEM DE SERVIÇO", "ASSISTÊNCIA TÉCNICA | Atendimento");
		ConsoleVisual.texto("Digite o seu cpf: ");
		String cpf = sc.nextLine();

		Clientes clienteEncontrado = buscarClientePorCpf(cpf);
		if (clienteEncontrado == null) {
			ConsoleVisual.aviso("CPF inválido!!!!");
		} else {
			if (listaOrdens.isEmpty()) {
				ConsoleVisual.aviso("Cadastre um equipamento primeiro.");
				return;
			}

			ArrayList<OrdemServico> ordensCliente = new ArrayList<>(); // lista local

			for (OrdemServico os : listaOrdens) { // compara o cpf digitado com o cpf cadastrado, caso seja igual irá
													// adicionar a lista temporaria
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
				String servico = "Limpeza";
				selecionada.setServico(servico);
				break;
			case 2:
				ConsoleVisual.menuDefeito();
				int escolha1 = sc.nextInt();
				sc.nextLine();

				switch (escolha1) {
				case 2:
					servico = "Manutenção Preventiva";
					selecionada.setServico(servico);
					selecionada.setStatus(Status.ABERTA);
					ConsoleVisual.sucesso("Ordem de serviço aberta com sucesso.");
					break;
				case 1:
					servico = "defeito";
					selecionada.setServico(servico);
					ConsoleVisual.pedir("Qual o defeito ?");
					String defeito = sc.nextLine();
					selecionada.setDefeito(defeito);
					selecionada.setStatus(Status.ABERTA);
					ConsoleVisual.sucesso("Ordem de serviço aberta com sucesso.");
					break;
				default:
					ConsoleVisual.aviso("Digite uma opção válida!!!");
					break;
				}
				break;
			case 3:
				ConsoleVisual.pedir("Defeito relatado:");
				servico = "defeito";
				selecionada.setServico(servico);
				String defeito = sc.nextLine();
				selecionada.setDefeito(defeito);
				selecionada.setStatus(Status.ABERTA);
				ConsoleVisual.sucesso("Ordem de serviço aberta com sucesso.");
				break;
			}

		}
	}

	public void ConsultarOS() {
		ConsoleVisual.titulo("CONSULTAR ORDENS DE SERVIÇO", "ASSISTÊNCIA TÉCNICA | Atendimento");
		ConsoleVisual.pedir("Digite o seu cpf: ");
		String cpf = sc.nextLine();
		Clientes clienteEncontrado=buscarClientePorCpf(cpf);
		

		if (listaOrdens.isEmpty()) {
			ConsoleVisual.aviso("Cadastre um equipamento primeiro.");
			return;
		}
		for (int i = 0; i < listaOrdens.size(); i++) {
			OrdemServico selecionada = listaOrdens.get(i);

			if (cpf.equals(selecionada.getCliente().getCpf())) {
				exibirEquipamento(i, selecionada);
			}
		}
		if (clienteEncontrado == null) {
			ConsoleVisual.aviso("CPF inválido!!!!");
		} else {
			ConsoleVisual.secao("CLIENTE");
			ConsoleVisual.campo("Nome", clienteEncontrado.getNome());
			ConsoleVisual.campo("CPF", clienteEncontrado.getCpf());
			ConsoleVisual.campo("Telefone", clienteEncontrado.getTelefone());
			ConsoleVisual.linha();

			for (int i = 0; i < listaOrdens.size(); i++) {
				OrdemServico selecionada = listaOrdens.get(i);
				if (cpf.equals(selecionada.getCliente().getCpf())) {
					exibirEquipamento(i, selecionada);
				}

			}
		}
	}

	public void AlterarStatus() {

		ConsoleVisual.titulo("ALTERAR STATUS DA OS", "ASSISTÊNCIA TÉCNICA | Atendimento");
		if (listaOrdens.isEmpty()) {
			ConsoleVisual.aviso("Cadastre um equipamento primeiro.");
			return;
		}
		ConsoleVisual.pedir("Senha de acesso");
		int senha = sc.nextInt();
		sc.nextLine();
		if (senha != 123) {
			ConsoleVisual.aviso("Senha errada. Somente pessoas autorizadas podem fazer isso.");

		} else {

			System.out.println(listaClientes);
			ConsoleVisual.pedir("Digite o cpf: ");
			String cpf = sc.nextLine();
			Clientes clienteEncontrado = buscarClientePorCpf(cpf);

			if (clienteEncontrado == null) {
				ConsoleVisual.aviso("CPF inválido!!!!");
			} else {

				for (int i = 0; i < listaOrdens.size(); i++) {
					exibirEquipamento(i, listaOrdens.get(i));
				}

				ConsoleVisual.pedir("Número do equipamento");
				int opcao = sc.nextInt();
				sc.nextLine();
				if (opcao < 1 || opcao > listaOrdens.size()) {
					ConsoleVisual.aviso("Opção inválida.");
					return;
				}

				OrdemServico selecionada = listaOrdens.get(opcao - 1);

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
					System.out.println("Saindo...");
					break;

				default:
					System.out.println("Digite uma opção válida!!!");
				}
				ConsoleVisual.sucesso("Status atualizado com sucesso.");
			}
		}
	}

	public void Delete() {
		ConsoleVisual.titulo("EXCLUIR CADASTRO OU EQUIPAMENTO", "ASSISTÊNCIA TÉCNICA | Atendimento");
		ConsoleVisual.pedir("Digite o seu cpf: ");
		String cpf = sc.nextLine();
		Clientes clienteEncontrado=buscarClientePorCpf(cpf);

		if (clienteEncontrado == null) {
			ConsoleVisual.aviso("CPF inválido!!!!");

		} else {
			ConsoleVisual.menuDelete();
			int escolha = sc.nextInt();
			sc.nextLine();

			ArrayList<OrdemServico> ordensCliente = new ArrayList<>(); // lista local

			for (OrdemServico os : listaOrdens) { // compara o cpf digitado com o cpf cadastrado, caso seja igual irá
													// adicionar a lista temporaria
				if (cpf.equals(os.getCliente().getCpf())) {
					ordensCliente.add(os);
				}
			}

			switch (escolha) {
			case 1:

				listaOrdens.removeAll(ordensCliente);
				listaClientes.remove(clienteEncontrado);
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

				ConsoleVisual.sucesso("Cadastro excluído");
				break;
			}

		}
	}

	private void exibirEquipamento(int indice, OrdemServico equipamento) {
		ConsoleVisual.secao("EQUIPAMENTO [" + (indice + 1) + "]");
		ConsoleVisual.campo("Produto", equipamento.getProduto());
		ConsoleVisual.campo("Marca", equipamento.getMarca());
		ConsoleVisual.campo("Modelo", equipamento.getModelo());
		ConsoleVisual.campo("Serviço pedido:", equipamento.getServico());
		ConsoleVisual.campo("Defeito:",
				equipamento.getDefeito() == null ? "Nenhum defeito informado" : equipamento.getDefeito());
		ConsoleVisual.campo("Status", equipamento.getStatus().toString());
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
