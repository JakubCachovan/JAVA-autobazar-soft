package autobazar;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    /**
     * Konštruktor pre vytvorenie objektu typu Predajca.
     * @param Email
     * @param Lokalita
     * @param Meno
     * @param Priezvisko
     * @param Telefon 
     */
    public Predajca(String Email, String Lokalita, String Meno, String Priezvisko, String Telefon) {
        this.Email = Email;
        this.Lokalita = Lokalita;
        this.Meno = Meno;
        this.Priezvisko = Priezvisko;
        this.Telefon = Telefon;
    }
    
    /**
     * Getter pre email predajcu.
     * @return 
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Setter pre email predajcu.
     * @param Email 
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * Getter pre lokalitu predajcu.
     * @return 
     */
    public String getLokalita() {
        return Lokalita;
    }

    /**
     * Setter pre lokalitu predajcu.
     * @param Lokalita 
     */
    public void setLokalita(String Lokalita) {
        this.Lokalita = Lokalita;
    }

    /**
     * Getter pre meno predajcu.
     * @return 
     */
    public String getMeno() {
        return Meno;
    }

    /**
     * Setter pre meno predajcu.
     * @param Meno 
     */
    public void setMeno(String Meno) {
        this.Meno = Meno;
    }

    /**
     * Getter pre priezvisko predajcu.
     * @return 
     */
    public String getPriezvisko() {
        return Priezvisko;
    }

    /**
     * Setter pre priezvisko predajcu.
     * @param Priezvisko 
     */
    public void setPriezvisko(String Priezvisko) {
        this.Priezvisko = Priezvisko;
    }

    /**
     * Getter pre telefon predajcu.
     * @return 
     */
    public String getTelefon() {
        return Telefon;
    }

    /**
     * Setter pre telegon predajcu.
     * @param Telefon 
     */
    public void setTelefon(String Telefon) {
        this.Telefon = Telefon;
    }

    /**
     * Metóda pre zistenie zhody kľúčového slova v znakovej reprezentácií objektu (toString).
     * @param keyWord - kľúčové slovo
     * @return true/false
     */
    public boolean obsahujeKlucoveSlovo(String keyWord){
        Pattern p = Pattern.compile("("+keyWord+")");
        Matcher m = p.matcher(this.toString());
        return m.find();   
    }

    /**
     * Znaková reprezentácia objektu typu Motocykel.
     * @return 
     */
    @Override
    public String toString() {
        return "Predajca{" + "Email=" + Email + ", Lokalita=" + Lokalita + ", Meno=" + Meno + ", Priezvisko=" + Priezvisko + ", Telefon=" + Telefon + '}';
    }
    
    
    
}