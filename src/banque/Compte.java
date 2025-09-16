package banque;
import com.sun.corba.se.spi.orb.Operation;
import java.util.List;


abstract class Compte {
    protected String code;
    protected double solde;
    protected List<Operation> listOperations;

    public abstract void retirer(double montant);
    public abstract double calculerInteret();
    public abstract void afficherDetails();

}
