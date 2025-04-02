package ps2.lab06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import static java.lang.System.out;
import java.util.Scanner;
import java.util.List;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private ProfessorRepo professorRepo;

    @Autowired
    private FaculdadeRepo faculdadeRepo;

    private static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) {
        while (true) {
            out.println("# Gerenciador de Professores e Faculdades!");
            out.println("(1) Cadastrar Faculdade");
            out.println("(2) Listar Faculdades");
            out.println("(3) Cadastrar Professor");
            out.println("(4) Listar Professores");
            out.println("(5) Sair");
            out.print("Escolha uma opção: ");
            
            int opcao = Integer.parseInt(entrada.nextLine());
            
            switch (opcao) {
                case 1:
                    cadastrarFaculdade();
                    break;
                case 2:
                    listarFaculdades();
                    break;
                case 3:
                    cadastrarProfessor();
                    break;
                case 4:
                    listarProfessores();
                    break;
                case 5:
                    out.println("Saindo do programa...");
                    return;
                default:
                    out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private void cadastrarFaculdade() {
        out.print("Nome da faculdade: ");
        String nome = entrada.nextLine();
        out.print("Ano de fundação: ");
        int ano = Integer.parseInt(entrada.nextLine());
        Faculdade faculdade = new Faculdade(nome, ano);
        faculdadeRepo.save(faculdade);
        out.println("Faculdade cadastrada com sucesso!");
    }

    private void listarFaculdades() {
        List<Faculdade> faculdades = (List<Faculdade>) faculdadeRepo.findAll();
        if (faculdades.isEmpty()) {
            out.println("Nenhuma faculdade cadastrada.");
        } else {
            out.println("ID    NOME                     ANO FUNDAÇÃO");
            for (Faculdade f : faculdades) {
                out.printf("%-5d %-25s %d\n", f.getId(), f.getNome(), f.getAnoFundacao());
            }
        }
    }

    private void cadastrarProfessor() {
        out.print("Nome do novo professor: ");
        String nome = entrada.nextLine();
        out.print("CPF do novo professor: ");
        String cpf = entrada.nextLine();
        out.print("Matrícula do novo professor: ");
        int matricula = Integer.parseInt(entrada.nextLine());
        
        List<Faculdade> faculdades = (List<Faculdade>) faculdadeRepo.findAll();
        if (faculdades.isEmpty()) {
            out.println("Nenhuma faculdade disponível. Cadastre uma antes de prosseguir.");
            return;
        }
        
        out.println("Faculdade do novo professor (selecione um dos números abaixo):");
        for (int i = 0; i < faculdades.size(); i++) {
            out.printf("- (%d) %s\n", i + 1, faculdades.get(i).getNome());
        }
        out.print("Entre o número da faculdade: ");
        int escolha = Integer.parseInt(entrada.nextLine()) - 1;
        
        if (escolha < 0 || escolha >= faculdades.size()) {
            out.println("Opção inválida!");
            return;
        }
        
        Faculdade faculdadeSelecionada = faculdades.get(escolha);
        Professor professor = new Professor(nome, cpf, matricula, faculdadeSelecionada);
        professorRepo.save(professor);
        out.println("Professor cadastrado com sucesso!");
    }

    private void listarProfessores() {
        List<Professor> professores = (List<Professor>) professorRepo.findAll();
        if (professores.isEmpty()) {
            out.println("Nenhum professor cadastrado.");
        } else {
            out.println("ID    NOME                     CPF          MATRÍCULA     FACULDADE");
            for (Professor p : professores) {
                String faculdadeNome = (p.getFaculdade() != null) ? p.getFaculdade().getNome() : "Não atribuído";
                out.printf("%-5d %-25s %-12s %-12d %s\n", 
                    p.getId(), p.getNome(), p.getCpf(), p.getMatricula(), faculdadeNome);
            }
        }
    }
}
