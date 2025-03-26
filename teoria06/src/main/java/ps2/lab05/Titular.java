package ps2.lab05;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Titular {
    @Id
    private long id;
    private String nome;
    private String cpf;

    public Titular() {}

    public Titular(long id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " - " + cpf;
    }
}