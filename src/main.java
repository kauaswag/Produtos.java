import java.util.ArrayList;
import java.util.Scanner;

public class main {

    static class Cliente {
        private String nome;
        private String cpf;
        private int idade;

        public Cliente(String nome, String cpf, int idade) {
            setNome(nome);
            setCpf(cpf);
            setIdade(idade);
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            if (nome == null || nome.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome não pode ser vazio.");
            }
            this.nome = nome;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            if (cpf == null || !cpf.matches("\\d{11}")) {
                throw new IllegalArgumentException("CPF deve conter 11 dígitos numéricos.");
            }
            this.cpf = cpf;
        }

        public int getIdade() {
            return idade;
        }

        public void setIdade(int idade) {
            if (idade <= 0) {
                throw new IllegalArgumentException("Idade deve ser maior que zero.");
            }
            this.idade = idade;
        }

        @Override
        public String toString() {
            return "Nome: " + nome + ", CPF: " + cpf + ", Idade: " + idade;
        }
    }

    private static final ArrayList<Cliente> clientes = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Buscar Cliente por Nome");
            System.out.println("4. Editar Cliente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            String entrada = scanner.nextLine();
            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> listarClientes();
                case 3 -> buscarCliente();
                case 4 -> editarCliente();
                case 0 -> System.out.println("Encerrando o programa...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void cadastrarCliente() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("CPF (11 dígitos, somente números): ");
            String cpf = scanner.nextLine();

            System.out.print("Idade: ");
            int idade = Integer.parseInt(scanner.nextLine());

            Cliente novo = new Cliente(nome, cpf, idade);
            clientes.add(novo);
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    private static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        System.out.println("\n--- Lista de Clientes ---");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, clientes.get(i));
        }
    }

    private static void buscarCliente() {
        System.out.print("Digite o nome para buscar: ");
        String busca = scanner.nextLine().toLowerCase();

        boolean encontrado = false;
        for (Cliente c : clientes) {
            if (c.getNome().toLowerCase().contains(busca)) {
                System.out.println(c);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum cliente encontrado com esse nome.");
        }
    }

    private static void editarCliente() {
        listarClientes();
        if (clientes.isEmpty())
            return;

        System.out.print("Digite o número do cliente que deseja editar: ");
        int indice;
        try {
            indice = Integer.parseInt(scanner.nextLine()) - 1;
        } catch (Exception e) {
            System.out.println("Número inválido.");
            return;

        }

        if (indice < 0 || indice >= clientes.size()) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        Cliente cliente = clientes.get(indice);

        try {
            System.out.print("Novo nome (" + cliente.getNome() + "): ");
            String novoNome = scanner.nextLine();
            if (!novoNome.trim().isEmpty())
                cliente.setNome(novoNome);

            System.out.print("Novo CPF (" + cliente.getCpf() + "): ");
            String novoCpf = scanner.nextLine();
            if (!novoCpf.trim().isEmpty())
                cliente.setCpf(novoCpf);

            System.out.print("Nova idade (" + cliente.getIdade() + "): ");
            String novaIdade = scanner.nextLine();
            if (!novaIdade.trim().isEmpty()) {
                cliente.setIdade(Integer.parseInt(novaIdade));
            }

            System.out.println("Cliente atualizado com sucesso!");
        } catch (Exception e) {

            System.out.println("Erro ao editar cliente: " + e.getMessage());
        }
    }
}
