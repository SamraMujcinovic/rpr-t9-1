package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class guiController {
    public ListView lista;
    public Button ispisiBtn;
    private GeografijaDAO baza;
    public ObservableList<Grad> gradovi;

    public guiController(GeografijaDAO baza) {
        this.baza = baza;
    }

    public void ispisi(ActionEvent actionEvent) {
        gradovi = FXCollections.observableArrayList(baza.gradovi());
        lista.itemsProperty().setValue(gradovi);
    }
}
