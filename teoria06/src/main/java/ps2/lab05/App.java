package ps2.lab05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import static java.lang.System.out;
import java.util.Scanner;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private TitularRepo titularRepo;

    private static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) {
        boolean sair = false;
        while (!sair) {
            out.println("\n## GERENCIADOR DE TITULARES ##");
            out.println("(1) Criar titular");
            out.println("(2) Ler todos os titulares");
            out.println("(3) Ler um titular específico");
            out.println("(4) Alterar titular");
            out.println("(5) Apagar titular");
            out.println("(0) Sair");
            out.print("Escolha uma opção: ");
            int opcao = Integer.parseInt(entrada.nextLine());
            switch(opcao) {
                case 1: criar(); break; 
                case 2: lerTodos(); break; 
                case 3: ler(); break;
                case 4: alterar(); break;
                case 5: apagar(); break;
                case 0: sair = true; break;
                default:
                    out.println("## Opção inválida! ##");
            }
        }
        System.exit(0);
    }    

    public void criar() {
        out.print("# Id do novo titular: ");
        long id = Long.parseLong(entrada.nextLine());
        out.print("# Nome do novo titular: ");
        String nome = entrada.nextLine();
        out.print("# CPF do novo titular: ");
        String cpf = entrada.nextLine();
        Titular t = new Titular(id, nome, cpf);
        titularRepo.save(t);
        out.println("## Titular criado com sucesso! ##");
    }

    public void lerTodos() {
        Iterable<Titular> titulares = titularRepo.findAll();
        out.println("\n## TITULARES CADASTRADOS ##");
        for(Titular t : titulares) {
            out.println(t.getId() + " - " + t.getNome() + " - " + t.getCpf());
        }
    }

    public void ler() {
        out.print("# Id do titular a ser lido: ");
        long id = Long.parseLong(entrada.nextLine());
        var titular = titularRepo.findById(id);
        if(titular.isPresent()) {
            out.println("\n## TITULAR ENCONTRADO ##");
            out.println(titular.get());
        } else {
            out.println("## Titular não encontrado! ##");
        }
    }

    public void alterar() {
        out.print("# Id do titular a alterar: ");
        long id = Long.parseLong(entrada.nextLine());
        var titular = titularRepo.findById(id);
        if(titular.isPresent()) {
            out.print("# Novo nome: ");
            String nome = entrada.nextLine();
            out.print("# Novo CPF: ");
            String cpf = entrada.nextLine();
            
            Titular t = titular.get();
            t.setNome(nome);
            t.setCpf(cpf);
            
            titularRepo.save(t);
            out.println("## Titular alterado com sucesso! ##");
        } else {
            out.println("## Titular não encontrado! ##");
        }
    }

    public void apagar() {
        out.print("# Id do titular a apagar: ");
        long id = Long.parseLong(entrada.nextLine());
        if(titularRepo.existsById(id)) {
            titularRepo.deleteById(id);
            out.println("## Titular apagado com sucesso! ##");
        } else {
            out.println("## Titular não encontrado! ##");
        }
    }
}