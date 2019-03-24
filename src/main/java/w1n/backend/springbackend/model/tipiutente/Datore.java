package w1n.backend.springbackend.model.tipiutente;


import org.springframework.data.mongodb.core.mapping.Document;
import w1n.backend.springbackend.model.User;

@Document(collection = "datore")
public class Datore extends User {


    private String nome;
    private String cognome;
    private String telefono;
    private String ragionesociale;
    private String sitoweb;
    private String partitaiva;
    private String indirizzo;


    public String getTelefono() {
        return telefono;
    }


    public String getCognome() {
        return cognome;
    }


    public String getNome() {
        return nome;
    }


    public String getIndirizzo() {
        return indirizzo;
    }


    public String getRagionesociale() {
        return ragionesociale;
    }


    public String getPartitaiva() {
        return partitaiva;
    }


    public String getSitoweb() {
        return sitoweb;
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setRagionesociale(String ragionesociale) {
        this.ragionesociale = ragionesociale;
    }

    public void setPartitaiva(String partitaiva) {
        this.partitaiva = partitaiva;
    }

    public void setSitoweb(String sitoweb) {
        this.sitoweb = sitoweb;
    }
}
