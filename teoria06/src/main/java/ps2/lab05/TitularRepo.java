package ps2.lab05;

import org.springframework.data.repository.CrudRepository;

public interface TitularRepo extends CrudRepository<Titular, Long> {
    // Métodos padrão do CrudRepository já inclusos:
    // save(), findById(), findAll(), deleteById(), etc.
}