package w1n.backend.springbackend.model.tipiutente;

import org.springframework.data.mongodb.core.mapping.Document;
import w1n.backend.springbackend.model.Role;
import w1n.backend.springbackend.model.User;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Document(collection = "lavoratore")
public class Lavoratore extends User {

    private String nome;
    private String cognome;
    private String telefono;
    private Date datanascita;
    private String luogonascita;


    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getTelefono() {
        return telefono;
    }

    public Date getDatanascita() {
        return datanascita;
    }

    public String getLuogonascita() {
        return luogonascita;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDatanascita(Date datanascita) {
        this.datanascita = datanascita;
    }

    public void setLuogonascita(String luogonascita) {
        this.luogonascita = luogonascita;
    }

    @Override
    public String toString(){
        String result = nome + "   " + getEmail();
        return result;
    }


    public User getUser(){
        User user = new User();
        user.setEnabled(true);
        user.setEmail(getEmail());
        user.setId(getId());
        user.setFullname(nome + " " + cognome);
        return user;
    }

}
