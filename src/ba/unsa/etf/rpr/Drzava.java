package ba.unsa.etf.rpr;

public class Drzava {
    private int id;
    private String naziv;
    private Grad glavniGrad;

    public Drzava(){}

    public Drzava(int id, String naziv, Grad glavniGrad){
        setGlavniGrad(glavniGrad);
        setId(id);
        setNaziv(naziv);
    }

    public int getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public Grad getGlavniGrad() {
        return glavniGrad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setGlavniGrad(Grad glavniGrad) {
        this.glavniGrad = glavniGrad;
    }
}
