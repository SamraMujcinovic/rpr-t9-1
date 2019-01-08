package ba.unsa.etf.rpr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GeografijaDAOTest {

    @Test
    void regenerateFile() {
        GeografijaDAO.removeInstance();
        File dbfile = new File("baza.db");
        dbfile.delete();
        GeografijaDAO instance = GeografijaDAO.getInstance();
        ArrayList<Grad> gradovi = instance.gradovi();
        assertEquals("London", gradovi.get(0).getNazivGrada());
        assertEquals("Francuska", gradovi.get(1).getDrzava().getNaziv());
    }

    @Test
    void glavniGrad() {
        GeografijaDAO.removeInstance();
        File dbfile = new File("baza.db");
        dbfile.delete();
        GeografijaDAO instance = GeografijaDAO.getInstance();
        Grad nepoznat = instance.glavniGrad("Bosna i Hercegovina");
        assertNull(nepoznat);
        Grad bech = instance.glavniGrad("Austrija");
        assertEquals("Beč", bech.getNazivGrada());
    }

    @Test
    void obrisiDrzavu() {
        GeografijaDAO.removeInstance();
        File dbfile = new File("baza.db");
        dbfile.delete();
        GeografijaDAO instance = GeografijaDAO.getInstance();
        // Nepostojeća država, neće se desiti ništa
        instance.obrisiDrzavu("Kina");
        ArrayList<Grad> gradovi = instance.gradovi();
        assertEquals("Pariz", gradovi.get(1).getNazivGrada());
        assertEquals("Austrija", gradovi.get(2).getDrzava().getNaziv());
    }

    @Test
    void obrisiDrzavu2() {
        GeografijaDAO.removeInstance();
        File dbfile = new File("baza.db");
        dbfile.delete();
        GeografijaDAO instance = GeografijaDAO.getInstance();

        // Nema gradova Beč i Graz koji su iz austrije
        instance.obrisiDrzavu("Austrija");

        ArrayList<Grad> gradovi = instance.gradovi();
        assertEquals(3, gradovi.size());
        assertEquals("London", gradovi.get(0).getNazivGrada());
        assertEquals("Pariz", gradovi.get(1).getNazivGrada());
        assertEquals("Manchester", gradovi.get(2).getNazivGrada());
    }

    @Test
    void dodajGrad() {
        GeografijaDAO.removeInstance();
        File dbfile = new File("baza.db");
        dbfile.delete();
        GeografijaDAO instance = GeografijaDAO.getInstance();
        Drzava francuska = instance.nadjiDrzavu("Francuska");
        Grad grad = new Grad();
        grad.setNazivGrada("Marseille");
        grad.setBrojStanovnika(869815);
        grad.setDrzava(francuska);
        instance.dodajGrad(grad);

        // Marsej je veći od Manchestera i Graza, ali manji od Pariza, Londona i Beča
        ArrayList<Grad> gradovi = instance.gradovi();
        assertEquals("Marseille", gradovi.get(3).getNazivGrada());
    }

    @Test
    void dodajDrzavu() {
        GeografijaDAO.removeInstance();
        File dbfile = new File("baza.db");
        dbfile.delete();
        Grad sarajevo = new Grad();
        sarajevo.setNazivGrada("Sarajevo");
        sarajevo.setBrojStanovnika(500000);
        Drzava bih = new Drzava();
        bih.setNaziv("Bosna i Hercegovina");
        bih.setGlavniGrad(sarajevo);
        sarajevo.setDrzava(bih);

        GeografijaDAO instance = GeografijaDAO.getInstance();
        instance.dodajDrzavu(bih);
        instance.dodajGrad(sarajevo);

        // Provjera
        Grad proba = instance.glavniGrad("Bosna i Hercegovina");
        assertEquals("Sarajevo", proba.getNazivGrada());
        assertEquals(500000, proba.getBrojStanovnika());
        assertEquals("Bosna i Hercegovina", proba.getDrzava().getNaziv());
    }

    @Test
    void izmijeniGrad() {
        GeografijaDAO.removeInstance();
        File dbfile = new File("baza.db");
        dbfile.delete();
        GeografijaDAO dao = GeografijaDAO.getInstance();
        Grad bech = dao.glavniGrad("Austrija");
        bech.setNazivGrada("Vienna");
        dao.izmijeniGrad(bech);

        ArrayList<Grad> gradovi = dao.gradovi();
        assertEquals("Vienna", gradovi.get(2).getNazivGrada());
    }
}
