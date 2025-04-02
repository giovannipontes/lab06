package ps2.lab06;

import jakarta.persistence.*;

@Entity
public class Professor {
    @Id @GeneratedValue
    private Long id;
    private String nome;
    private String cpf;
    private int matricula;

    @ManyToOne
    private Faculdade faculdade;

    public Professor() {}

    public Professor(String nome, String cpf, int matricula, Faculdade faculdade) {
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.faculdade = faculdade;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getCpf() {
        return cpf;
    }
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    public int getMatricula() {
        return matricula;
    }
    public void setFaculdade(Faculdade faculdade) {
        this.faculdade = faculdade;
    }
    public Faculdade getFaculdade() {
        return faculdade;
    }
}
