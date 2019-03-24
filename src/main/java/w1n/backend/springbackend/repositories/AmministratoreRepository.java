package w1n.backend.springbackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import w1n.backend.springbackend.model.tipiutente.Amministratore;

public interface AmministratoreRepository extends MongoRepository<Amministratore,String> {
}
