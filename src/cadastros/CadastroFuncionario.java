package cadastros;

import Menu.ConsoleVisual;
import java.util.ArrayList;
import java.util.Scanner;
import login.Funcionario;

public class CadastroFuncionario {
	ArrayList<Funcionario> listaFuncionario;
	private Scanner sc;

	public CadastroFuncionario(ArrayList<Funcionario> listaFuncionario, Scanner sc) {
		this.listaFuncionario = listaFuncionario;
		this.sc = sc;
	}

	public void CadastrarFuncionario() {

		ConsoleVisual.titulo("CADASTRAR FUNCIONÁRIO", "ASSISTÊNCIA TÉCNICA | Atendimento");
		ConsoleVisual.pedir("Senha de acesso");
		String senha = sc.nextLine();
		if (Senha(senha) == true) {
			ConsoleVisual.pedir("Nome completo");
			String nome = sc.nextLine();
			ConsoleVisual.pedir("CPF");
			String cpf = sc.nextLine();
			if (cpfExistent(cpf) == false) {
				ConsoleVisual.pedir("Telefone");
				String telefone = sc.nextLine();
				ConsoleVisual.pedir("E-mail");
				String email = sc.nextLine();
				ConsoleVisual.pedir("N° de Funcionario");
				int numeroFuncionario = sc.nextInt();
				sc.nextLine();

				Funcionario funcionario = new Funcionario(nome, cpf, telefone, email, numeroFuncionario);
				listaFuncionario.add(funcionario);
				ConsoleVisual.sucesso("Funcionário cadastrado com sucesso.");
				ConsoleVisual.sucesso("Seja bem vindo :D");
			} else {
				ConsoleVisual.aviso("Cpf já existe!");
			}

		} else {
			ConsoleVisual.aviso("Senha incorreta!");
		}
	}

	private boolean cpfExistent(String cpf) {
		for (Funcionario x : listaFuncionario) {
			if (cpf.equals(x.getCpf())) {
				return true;
			}

		}
		return false;
	}

	private boolean Senha(String senha) {
		for (Funcionario x : listaFuncionario) {
			if (senha.equals(x.getSenha())) {
				return true;
			}

		}
		return false;
	}
}
