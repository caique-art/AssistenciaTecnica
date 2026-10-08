package cadastros;

import Menu.ConsoleVisual;
import java.util.ArrayList;
import java.util.Scanner;
import login.Clientes;

public class CadastroClientes {
	ArrayList<Clientes> listaClientes;
	private Scanner sc;

	public CadastroClientes(ArrayList<Clientes> listaClientes, Scanner sc) {
		this.listaClientes = listaClientes;
		this.sc = sc;
	}

	public void CadastrarClientes() {

		ConsoleVisual.titulo("CADASTRAR CLIENTE", "ASSISTÊNCIA TÉCNICA | Atendimento");
		ConsoleVisual.pedir("Nome completo");
		String nome = sc.nextLine();
		ConsoleVisual.pedir("CPF");
		String cpf = sc.nextLine();
		if (cpfExistent(cpf) == false) {
			ConsoleVisual.pedir("Telefone");
			String telefone = sc.nextLine();
			ConsoleVisual.pedir("E-mail");
			String email=sc.nextLine();

			Clientes cliente = new Clientes(nome, cpf, telefone, email);
			listaClientes.add(cliente);
			ConsoleVisual.sucesso("Cliente cadastrado com sucesso.");
		} else {
			ConsoleVisual.aviso("Cpf já existe!");
		}

	}

	private boolean cpfExistent(String cpf) {
		for (Clientes x : listaClientes) {
			if (cpf.equals(x.getCpf())) {
				return true;
			}

		}
		return false;
	}

}
