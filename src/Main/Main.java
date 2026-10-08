package Main;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import OrdemServico.OrdemServico;
import OrdemServico.Produto;
import login.Clientes;
import login.Funcionario;
import login.Login;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        Scanner sc = new Scanner(System.in);
        String nome=null, cpf=null, telefone=null, email=null;
        int numeroFuncionario=1;

        ArrayList<OrdemServico> listaOrdens = new ArrayList<>();
        ArrayList<Clientes> listaClientes = new ArrayList<>();
        ArrayList<Funcionario> listaFuncionario = new ArrayList<>();
        Boolean exit;
        
        
        do {

        Funcionario funcionario = new Funcionario(
        		nome, 
        		cpf, 
        		telefone, 
        		email, 
        		numeroFuncionario
        );

        listaFuncionario.add(funcionario);

        Login login = new Login(
                listaClientes,
                listaFuncionario,
                listaOrdens,
                sc
                
        );
        
        exit = login.getExit();

        login.Entrar();
        }while(exit==false);

        sc.close();
    }
}