package w1n.backend.springbackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import w1n.backend.springbackend.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);
}