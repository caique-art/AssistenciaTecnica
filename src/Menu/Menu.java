package Menu;

import java.util.ArrayList;
import java.util.Scanner;

import Clientes.Clientes;
import OrdemServico.OrdemServico;

public class Menu {
	Scanner sc = new Scanner(System.in);
	Clientes cliente = new Clientes();
	OrdemServico os = new OrdemServico();
	// OrdemServico selecionada;
	ArrayList<OrdemServico> lista = new ArrayList<>();

	public void CadastrarClientes() {
		System.out.print("Digite seu nome: ");
		String nome = sc.nextLine();
		System.out.print("Digite seu CPF: ");
		String cpf = sc.nextLine();
		System.out.print("Digite seu telefone: ");
		String telefone = sc.nextLine();

		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setTelefone(telefone);
	}

	public void CadastrarEquipamento() {
		if (cliente.getCpf() == null || cliente.getNome() == null) {
			System.out.println("Complete o login primeiro");
		} else {
			System.out.println("Digite a quantidade de aparelhos: ");
			int quantidade = sc.nextInt();
			sc.nextLine();
			String produto;

			for (int i = 0; i < quantidade; i++) {
				OrdemServico os2 = new OrdemServico();
				System.out.print("Digite o seu produto: ");
				produto = sc.nextLine();
				System.out.print("Digite a marca de seu produto: ");
				String marca = sc.nextLine();
				System.out.print("Digite o modelo de seu produto: ");
				String modelo = sc.nextLine();

				os2.setProduto(produto);
				os2.setMarca(marca);
				os2.setModelo(modelo);
				lista.add(os2);
			}
		}
	}

	public void AbrirOS() {
		if (lista.isEmpty()) {
			System.out.println("Cadastre um equipamento primeiro.");
			return;
		}

		for (int i = 0; i < lista.size(); i++) {
			System.out.println((i + 1) + " - " + lista.get(i).getProduto());
		}
		System.out.print("Qual equipamento deseja selecionar? ");
		int opcao = sc.nextInt();
		sc.nextLine();
		if (opcao < 1 || opcao > lista.size()) {
			System.out.println("Opção inválida.");
			return;
		}
		OrdemServico selecionada = lista.get(opcao - 1);
		System.out.print("Descreva o defeito do equipamento: ");
		String defeito = sc.nextLine();
		selecionada.setDefeito(defeito);
		selecionada.setStatus("Aberta");
		System.out.println("Ordem de serviço aberta com sucesso.");
	}

	public void ConsultarOS() {
		if (lista.isEmpty()) {
			System.out.println("Cadastre um equipamento primeiro.");
			return;
		}

		System.out.println("Nome: " + cliente.getNome());
		System.out.println("CPF: " + cliente.getCpf());
		System.out.println("Telefone: " + cliente.getTelefone());

		for (int i = 0; i < lista.size(); i++) {
			OrdemServico selecionada = lista.get(i);
			System.out.println("Produto: " + lista.get(i).getProduto());
			System.out.println("Marca: " + lista.get(i).getMarca());
			System.out.println("Modelo: " + lista.get(i).getModelo());
			System.out.println("Defeito: " + lista.get(i).getDefeito());
			
			if (selecionada.getStatus() == null) {
			    System.out.println("OS ainda não aberta.");
			} else {
			    System.out.println("Status: " + selecionada.getStatus());
			}

		}
	}

	public void AlterarStatus() {
		if (lista.isEmpty()) {
		    System.out.println("Cadastre um equipamento primeiro.");
		    return;
		}
		System.out.println("Digite a senha: ");
		os.setSenha(sc.nextInt());
		sc.nextLine();
		if (os.getSenha() != 123) {
			System.out.println("Senha errada. Somente pessoas autorizadas podem fazer isso.");

		} else {
			for (int i = 0; i < lista.size(); i++) {
				System.out.println((i + 1) + " - " + lista.get(i).getProduto());
			}

			System.out.print("Qual equipamento deseja selecionar? ");
			int opcao = sc.nextInt();
			sc.nextLine();
			if (opcao < 1 || opcao > lista.size()) {
				System.out.println("Opção inválida.");
				return;
			}

			OrdemServico selecionada = lista.get(opcao - 1);

			if (selecionada.getStatus() == null) {
			    System.out.println("Essa ordem de serviço ainda não foi aberta.");
			    return;
			}
			System.out.print("Digite o novo status da OS: ");
			String status = sc.nextLine();
			selecionada.setStatus(status);
			System.out.println("Status atualizado com sucesso.");
		}
	}

}
