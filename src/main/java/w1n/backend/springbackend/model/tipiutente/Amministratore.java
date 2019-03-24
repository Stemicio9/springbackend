package w1n.backend.springbackend.model.tipiutente;


import org.springframework.data.mongodb.core.mapping.Document;
import w1n.backend.springbackend.model.User;

@Document(collection = "amministratore")
public class Amministratore extends User {
}
