package autobazar;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Trieda reprezentujúca Predajcu vozidla.
 * @author Cachovan Jakub
 * @version 1.0
 */
public class Predajca implements Serializable{

	private String Email;
	private String Lokalita;
	private String Meno;
	private String Priezvisko;
	private String Telefon;

    /**
     * Konštruktor pre vytvorenie objektu typu Predajca.
     * @param Email - emailová adresa predajcu
     * @param Lokalita - lokalita v ktorej býva predajca
     * @param Meno - meno predajcu 
     * @param Priezvisko - priezvisko predajcu
     * @param Telefon - telefon predajcu
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
     * @return email predajcu.
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Setter pre email predajcu.
     * @param Email email predajcu.
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * Getter pre lokalitu predajcu.
     * @return lokalita predajcu
     */
    public String getLokalita() {
        return Lokalita;
    }

    /**
     * Setter pre lokalitu predajcu.
     * @param Lokalita lokalita predajcu
     */
    public void setLokalita(String Lokalita) {
        this.Lokalita = Lokalita;
    }

    /**
     * Getter pre meno predajcu.
     * @return meno predajcu.
     */
    public String getMeno() {
        return Meno;
    }

    /**
     * Setter pre meno predajcu.
     * @param Meno meno predajcu.
     */
    public void setMeno(String Meno) {
        this.Meno = Meno;
    }

    /**
     * Getter pre priezvisko predajcu.
     * @return priezvisko predajcu.
     */
    public String getPriezvisko() {
        return Priezvisko;
    }

    /**
     * Setter pre priezvisko predajcu.
     * @param Priezvisko priezvisko predajcu.
     */
    public void setPriezvisko(String Priezvisko) {
        this.Priezvisko = Priezvisko;
    }

    /**
     * Getter pre telefon predajcu.
     * @return telefon predajcu.
     */
    public String getTelefon() {
        return Telefon;
    }

    /**
     * Setter pre telegon predajcu.
     * @param Telefon telefon predajcu.
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
     * @return String
     */
    @Override
    public String toString() {
        return "Predajca{" + "Email=" + Email + ", Lokalita=" + Lokalita + ", Meno=" + Meno + ", Priezvisko=" + Priezvisko + ", Telefon=" + Telefon + '}';
    }
    
    
    
}