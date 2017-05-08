package autobazar;

import java.io.Serializable;


/**
 * @author Cachovan Jakub
 * @version 1.0
 * @created 31-3-2017 13:05:52
 */
public class Predajca implements Serializable{

	private String Email;
	private String Lokalita;
	private String Meno;
	private String Priezvisko;
	private String Telefon;

    public Predajca(String Email, String Lokalita, String Meno, String Priezvisko, String Telefon) {
        this.Email = Email;
        this.Lokalita = Lokalita;
        this.Meno = Meno;
        this.Priezvisko = Priezvisko;
        this.Telefon = Telefon;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getLokalita() {
        return Lokalita;
    }

    public void setLokalita(String Lokalita) {
        this.Lokalita = Lokalita;
    }

    public String getMeno() {
        return Meno;
    }

    public void setMeno(String Meno) {
        this.Meno = Meno;
    }

    public String getPriezvisko() {
        return Priezvisko;
    }

    public void setPriezvisko(String Priezvisko) {
        this.Priezvisko = Priezvisko;
    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String Telefon) {
        this.Telefon = Telefon;
    }


    
}