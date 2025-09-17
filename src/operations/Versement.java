package operations;

public class Versement extends Operation {
    private String source;

    public Versement(String date,double montant, String source) {
        super(date, montant);
        this.source = source;
    }

    public String getSource() {
        return source;
    }

}
