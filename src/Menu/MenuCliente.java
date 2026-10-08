package Menu;

import java.util.ArrayList;
import java.util.Scanner;

import OrdemServico.OrdemServico;
import OrdemServico.Status;
import login.Clientes;

public class MenuCliente {

	private Scanner sc;
	private Clientes cliente;

	private ArrayList<OrdemServico> listaOrdens;

	public MenuCliente(ArrayList<OrdemServico> listaOrdens,
			Clientes cliente,
			Scanner sc) {

		this.listaOrdens = listaOrdens;
		this.cliente = cliente;
		this.sc = sc;
	}

	public void menu() {

		int escolha;

		do {

			ConsoleVisual.titulo("MENU CLIENTE",
					"ASSISTÊNCIA TÉCNICA | " + cliente.getNome());

			System.out.println("1 - Consultar equipamentos");
			System.out.println("2 - Consultar ordens de serviço");
			System.out.println("3 - Abrir ordem de serviço");
			System.out.println("4 - Excluir cadastro");
			System.out.println("0 - Sair");

			ConsoleVisual.pedir("Escolha uma opção");
			escolha = sc.nextInt();
			sc.nextLine();

			switch (escolha) {

			case 1:
				consultarEquipamentos();
				break;

			case 2:
				consultarOS();
				break;

			case 3:
				abrirOS();
				break;

			case 4:
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

	public void consultarEquipamentos() {

		ConsoleVisual.titulo("MEUS EQUIPAMENTOS",
				"ASSISTÊNCIA TÉCNICA | Cliente");

		boolean encontrou = false;

		for (int i = 0; i < listaOrdens.size(); i++) {

			OrdemServico equipamento = listaOrdens.get(i);

			if (cliente.getCpf().equals(equipamento.getCliente().getCpf())) {

				exibirEquipamento(i, equipamento);
				encontrou = true;
			}
		}

		if (!encontrou) {
			ConsoleVisual.aviso("Você não possui equipamentos cadastrados.");
		}
	}

	public void consultarOS() {

		ConsoleVisual.titulo("MINHAS ORDENS DE SERVIÇO",
				"ASSISTÊNCIA TÉCNICA | Cliente");

		boolean encontrou = false;

		for (int i = 0; i < listaOrdens.size(); i++) {

			OrdemServico equipamento = listaOrdens.get(i);

			if (cliente.getCpf().equals(equipamento.getCliente().getCpf())) {

				exibirEquipamento(i, equipamento);
				encontrou = true;
			}
		}

		if (!encontrou) {
			ConsoleVisual.aviso("Você não possui ordens de serviço.");
		}
	}

	public void abrirOS() {

		ConsoleVisual.titulo("ABRIR ORDEM DE SERVIÇO",
				"ASSISTÊNCIA TÉCNICA | Cliente");

		ArrayList<OrdemServico> ordensCliente = new ArrayList<>();

		for (OrdemServico os : listaOrdens) {

			if (cliente.getCpf().equals(os.getCliente().getCpf())) {
				ordensCliente.add(os);
			}
		}

		if (ordensCliente.isEmpty()) {
			ConsoleVisual.aviso("Você não possui equipamentos cadastrados.");
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

	public void deletar() {

		ConsoleVisual.titulo("EXCLUIR MEU CADASTRO",
				"ASSISTÊNCIA TÉCNICA | Cliente");

		ArrayList<OrdemServico> ordensCliente = new ArrayList<>();

		for (OrdemServico os : listaOrdens) {

			if (cliente.getCpf().equals(os.getCliente().getCpf())) {
				ordensCliente.add(os);
			}
		}

		listaOrdens.removeAll(ordensCliente);

		ConsoleVisual.aviso("Seu cadastro será excluído junto com seus equipamentos.");
		
		// Aqui ainda precisamos receber a listaClientes
		// para remover o cliente da lista principal.
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
}
