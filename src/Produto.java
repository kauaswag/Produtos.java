
public class Produto {
    public int id;
    public String nome;
    public String desc;

    public Produto(int id, String nome, String desc) {
        this.id = id;
        this.nome = nome;
        this.desc = desc;
    }

    public void imprimir() {
        System.out.println("Produto ID: " + id + " Nome: " + nome + " Descrição: " + desc);
    }

    public void mudarNome(String novoNome) {
        this.nome = novoNome;
    }

    public void mudarDescricao(String novaDescricao) {
        this.desc = novaDescricao;
    }

    public static void main(String[] args) {
        Produto P;
        P = new Produto(1, "Caneta", "Caneta azul esferográfica");
        P.imprimir();
        System.err.println("***Mudança de nome ***");
        P.mudarNome("Caneta vermelha");
        P.imprimir();
        System.err.println("***Mudança de descrição ***");
        P.mudarDescricao("Caneta vermelha de tinta permanente");
        P.imprimir();
        P = new Produto(2, "Caderno", "Caderno universitário 200 folhas");
        P.imprimir();
    }
}
