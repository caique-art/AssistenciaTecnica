package login;

public class Funcionario extends Pessoa {

    private int numeroFuncionario;
    private String senha = "123";

    public Funcionario(String nome, String cpf, String telefone, String email, int numeroFuncionario) {
        super(nome, cpf, telefone, email);
        this.numeroFuncionario = numeroFuncionario;
    }

    public void setNumeroFuncionario(int numeroFuncionario) {
        this.numeroFuncionario = numeroFuncionario;
    }

    public int getNumeroFuncionario() {
        return numeroFuncionario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }
}
