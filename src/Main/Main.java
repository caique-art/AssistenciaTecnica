package Main;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import OrdemServico.OrdemServico;
import login.Clientes;
import login.Funcionario;
import login.Login;

public class Main {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);

		Scanner sc = new Scanner(System.in);

		ArrayList<OrdemServico> listaOrdens = new ArrayList<>();
		ArrayList<Clientes> listaClientes = new ArrayList<>();
		ArrayList<Funcionario> listaFuncionario = new ArrayList<>();

		Funcionario funcionario = new Funcionario(
				"Administrador",
				"12345678900",
				"21999999999",
				"admin@email.com",
				1);

		listaFuncionario.add(funcionario);

		Login login = new Login(
				listaClientes,
				listaFuncionario,
				listaOrdens,
				sc);

		login.entrar();

		sc.close();
	}
}