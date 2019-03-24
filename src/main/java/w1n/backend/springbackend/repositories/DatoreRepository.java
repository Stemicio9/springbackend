package w1n.backend.springbackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import w1n.backend.springbackend.model.tipiutente.Datore;

public interface DatoreRepository extends MongoRepository<Datore,String> {
}
