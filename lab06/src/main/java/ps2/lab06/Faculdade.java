package ps2.lab06;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Faculdade {
    @Id @GeneratedValue
    private Long id;
    private String nome;
    private int anoFundacao;

    @OneToMany(mappedBy = "faculdade")
    private List<Professor> professores;

    public Faculdade() {}

    public Faculdade(String nome, int anoFundacao) {
        this.nome = nome;
        this.anoFundacao = anoFundacao;
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
    public void setAnoFundacao(int anoFundacao) {
        this.anoFundacao = anoFundacao;
    }
    public int getAnoFundacao() {
        return anoFundacao;
    }
    public List<Professor> getProfessores() {
        return professores;
    }
}
