package login;

import java.util.ArrayList;
import java.util.Scanner;

import OrdemServico.OrdemServico;

import Menu.ConsoleVisual;
import Menu.MenuCliente;
import Menu.MenuFuncionario;

import cadastros.CadastroClientes;
import cadastros.CadastroFuncionario;

public class Login {

	private ArrayList<Clientes> listaClientes;

	private ArrayList<Funcionario> listaFuncionario;

	private ArrayList<OrdemServico> listaOrdens;

	private Scanner sc;
	
	private Boolean exit = false;

	public Login(ArrayList<Clientes> listaClientes, ArrayList<Funcionario> listaFuncionario,
			ArrayList<OrdemServico> listaOrdens, Scanner sc) {

		this.listaClientes = listaClientes;

		this.listaFuncionario = listaFuncionario;

		this.listaOrdens = listaOrdens;

		this.sc = sc;
	}

	public void setExit(Boolean exit) {this.exit=exit;}
	
	public void Entrar() {
		Boolean aviso = false;
		
		do {

			ConsoleVisual.menuAcesso();

			int escolha = sc.nextInt();

			sc.nextLine();

			switch (escolha) {

			case 1:

				ConsoleVisual.titulo("LOGIN", "ASSISTÊNCIA TÉCNICA | Atendimento");

				ConsoleVisual.pedir("CPF");

				String cpf = sc.nextLine();

				Clientes clienteEncontrado = null;

				Funcionario funcionarioEncontrado = null;

				for (Clientes x : listaClientes) {

					if (cpf.equals(x.getCpf())) {

						clienteEncontrado = x;

						break;
					}
				}

				if (clienteEncontrado != null) {

					MenuCliente menu = new MenuCliente(listaOrdens, clienteEncontrado, sc);

					menu.menu();

					return;
				}

				for (Funcionario x : listaFuncionario) {

					if (cpf.equals(x.getCpf())) {

						funcionarioEncontrado = x;

						break;
					}
				}

				if (funcionarioEncontrado != null) {

					ConsoleVisual.pedir("Senha");

					String senha = sc.nextLine();

					if (senha.equals(funcionarioEncontrado.getSenha())) {

						MenuFuncionario menu = new MenuFuncionario(listaOrdens, listaClientes, listaFuncionario,
								funcionarioEncontrado, sc);

						menu.menu();

					} else {

						ConsoleVisual.aviso("Senha incorreta!");
					}

					return;
				}

				ConsoleVisual.aviso("CPF não encontrado!");
				aviso = true;

				break;

			case 2:

				ConsoleVisual.menuCadastro();

				int opcao = sc.nextInt();

				sc.nextLine();

				switch (opcao) {

				case 1:

					CadastroClientes cadastroClientes = new CadastroClientes(listaClientes, sc);

					cadastroClientes.CadastrarClientes();

					break;

				case 2:

					CadastroFuncionario cadastroFuncionario = new CadastroFuncionario(listaFuncionario, sc);

					cadastroFuncionario.CadastrarFuncionario();

					break;

				case 0:

					break;

				default:

					ConsoleVisual.aviso("Opção inválida.");

					break;
				}

				break;

			case 0:

				ConsoleVisual.sucesso("Atendimento encerrado. Até a próxima!");
				
				exit=true;
				setExit(exit);

				break;

			default:

				ConsoleVisual.aviso("Opção inválida.");

				break;
			}
		} while (aviso == true);
	}
	
	
	public Boolean getExit() {return exit;}
}
