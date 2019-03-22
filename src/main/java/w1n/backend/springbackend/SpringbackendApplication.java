package w1n.backend.springbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import w1n.backend.springbackend.model.Annuncio;
import w1n.backend.springbackend.model.Role;
import w1n.backend.springbackend.repositories.RoleRepository;

@SpringBootApplication
public class SpringbackendApplication{

    public static void main(String[] args) {
        SpringApplication.run(SpringbackendApplication.class, args);
    }


    @Bean
    CommandLineRunner init(RoleRepository roleRepository) {

        return args -> {

            Role adminRole = roleRepository.findByRole("ADMIN");
            if (adminRole == null) {
                Role newAdminRole = new Role();
                newAdminRole.setRole("ADMIN");
                roleRepository.save(newAdminRole);
            }

            Role userRole = roleRepository.findByRole("USER");
            if (userRole == null) {
                Role newUserRole = new Role();
                newUserRole.setRole("USER");
                roleRepository.save(newUserRole);
            }

            Role datore = roleRepository.findByRole("DATORE");
            if (datore == null) {
                Role datorerole = new Role();
                datorerole.setRole("DATORE");
                roleRepository.save(datorerole);
            }


        };

    }
}
