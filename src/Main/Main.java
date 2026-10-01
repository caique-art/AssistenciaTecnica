package Main;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import Clientes.Clientes;
import Menu.Menu;
import OrdemServico.OrdemServico;
import Menu.CadastrarEquipamento;
import Menu.CadastroClientes;
import Menu.ConsoleVisual;

public class Main {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		ArrayList<OrdemServico> listaOrdens = new ArrayList<>();
		ArrayList<Clientes> listaClientes = new ArrayList<>();
		CadastrarEquipamento cadastrarEquipamento= new CadastrarEquipamento(listaOrdens, listaClientes);
		CadastroClientes cadastroClientes= new CadastroClientes(listaClientes, sc);
		Menu menu = new Menu(listaOrdens, listaClientes, sc);
		int escolha;

		do {
			ConsoleVisual.menuPrincipal();
			escolha = sc.nextInt();
			sc.nextLine();

			switch (escolha) {
			case 1:
				cadastroClientes.CadastrarClientes();
				break;

			case 2:
				cadastrarEquipamento.CadastroEquipamento();
				break;

			case 3:
				menu.AbrirOS();
				break;

			case 4:
				menu.ConsultarOS();
				break;

			case 5:
				menu.AlterarStatus();
				break;
				
			case 6:
				menu.Delete();
				break;

			case 0:
				ConsoleVisual.sucesso("Atendimento encerrado. Até a próxima!");
				break;

			default:
				ConsoleVisual.aviso("Opção inválida. Escolha um número de 0 a 6.");
				break;
			}
		} while (escolha != 0);
		sc.close();
	}
}