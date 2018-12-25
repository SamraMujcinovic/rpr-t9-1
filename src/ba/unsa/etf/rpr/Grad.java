package ba.unsa.etf.rpr;

public class Grad {
    private String nazivGrada;
    private Drzava drzava;
    private int brojStanovnika;

    public Grad() {}

    public Grad(String nazivGrada, Drzava drzava, int brojStanovnika){
        setNazivGrada(nazivGrada);
        setDrzava(drzava);
        setBrojStanovnika(brojStanovnika);
    }

    public String getNazivGrada() {
        return nazivGrada;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setNazivGrada(String nazivGrada) {
        this.nazivGrada = nazivGrada;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }
}
