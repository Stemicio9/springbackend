package w1n.backend.springbackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import w1n.backend.springbackend.model.tipiutente.Lavoratore;

public interface LavoratoreRepository extends MongoRepository<Lavoratore, String> {
}
