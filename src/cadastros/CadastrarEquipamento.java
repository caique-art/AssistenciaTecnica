package cadastros;

import Menu.ConsoleVisual;
import java.util.ArrayList;
import java.util.Scanner;

import OrdemServico.OrdemServico;
import login.Clientes;

public class CadastrarEquipamento {
	private Scanner sc;
	CadastroClientes cadastroClientes;
	ArrayList<OrdemServico> listaOrdens;
	ArrayList<Clientes> listaClientes;

	public CadastrarEquipamento(ArrayList<OrdemServico> listaOrdens, ArrayList<Clientes> listaClientes) {
		this.listaOrdens = listaOrdens;
		this.listaClientes = listaClientes;
        this.sc = new Scanner(System.in);
	}

	public void cadastrarEquipamento() {
		Clientes clienteEncontrado = null;
		boolean cpfExist = false;

		ConsoleVisual.titulo("CADASTRAR EQUIPAMENTO", "ASSISTÊNCIA TÉCNICA | Atendimento");
		System.out.println("Digite o seu cpf: ");
		String cpf = sc.next();
		for (Clientes x : listaClientes) {
			if (cpf.equals(x.getCpf())) {
				cpfExist = true;
				clienteEncontrado = x;
			}
		}
		if (!cpfExist) {
			ConsoleVisual.aviso("Cadastre um cliente primeiro, pela opção 1 do menu.");
		} else {
			ConsoleVisual.pedir("Quantidade de aparelhos");
			int quantidade = sc.nextInt();
			sc.nextLine();
			String produto;

			for (int i = 0; i < quantidade; i++) {
				ConsoleVisual.secao("EQUIPAMENTO " + (i + 1) + " DE " + quantidade);
				ConsoleVisual.pedir("Produto");
				produto = sc.nextLine();
				ConsoleVisual.pedir("Marca");
				String marca = sc.nextLine();
				ConsoleVisual.pedir("Modelo");
				String modelo = sc.nextLine();
				OrdemServico os2 = new OrdemServico(produto, marca, modelo, clienteEncontrado);

				os2.setProduto(produto);
				os2.setMarca(marca);
				os2.setModelo(modelo);
				os2.setCliente(clienteEncontrado);
				listaOrdens.add(os2);
				ConsoleVisual.sucesso("Equipamento cadastrado com sucesso.");
			}
		}
	}
}
