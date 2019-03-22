package w1n.backend.springbackend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import w1n.backend.springbackend.model.Role;
import w1n.backend.springbackend.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

    User findAllByRolesContains(Role role);

}