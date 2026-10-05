package fr.javafxcours.vercorium;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.ResourceBundle;

public class VercoriumController implements Initializable {

    @FXML
    public MenuButton liste_capteur;

    // Ajout du typage <String>
    @FXML
    public ListView<String> liste_capteur_ajouter;

    @FXML
    public Button btn_simulation;

    @FXML
    private Label foreusetitle;

    // Ajout du typage <String>
    @FXML
    private ComboBox<String> ajout_capteur;

    // Cette variable va contenir la liste de capteurs issue de la BDD
    private ObservableList<String> optionsDisponibles;
    @FXML
    public void ajout_capteur(InputMethodEvent actionEvent) {

        String selection = ajout_capteur.getValue();
        System.out.println("dans methode ajoutCapteur:" + selection);
        if (selection != null) {
            // On l'ajoute à la ListView
            liste_capteur_ajouter.getItems().add(selection);

            // On le retire de la ComboBox
            Platform.runLater(() -> {
                optionsDisponibles.remove(selection);
                ajout_capteur.getSelectionModel().clearSelection(); // Remet le texte à vide
            });
        }

    }

    @FXML
    public void OnActionLancersimulation(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // 1. Initialisation via la BDD
        // On stocke la liste issue de la BDD dans notre variable pour la manipuler ensuite
        Gestionbdd bdd = new Gestionbdd();
        optionsDisponibles = FXCollections.observableArrayList(bdd.getlistcapteur());
        // On lie cette liste à la ComboBox
        ajout_capteur.setItems(optionsDisponibles);


        // 2. Configuration du design de la ListView (avec le bouton supprimer)
        liste_capteur_ajouter.setCellFactory(param -> new ListCell<String>() {
            private final HBox hbox = new HBox(10);
            private final Label textLabel = new Label();
            private final Region spacer = new Region();
            private final Button btnDelete = new Button("🗑");

            {
                HBox.setHgrow(spacer, Priority.ALWAYS);
                hbox.setAlignment(Pos.CENTER_LEFT);
                hbox.getChildren().addAll(textLabel, spacer, btnDelete);

                // Action de suppression
                btnDelete.setOnAction(e -> {
                    String item = getItem();
                    // On retire le capteur de la liste visuelle
                    getListView().getItems().remove(item);
                    // On remet le capteur dans la liste de la ComboBox
                    optionsDisponibles.add(item);
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    textLabel.setText(item);
                    setGraphic(hbox);
                }
            }
        });


        // 3. Événement : Quand on choisit un capteur dans la ComboBox
       /* ajout_capteur.setOnAction(event -> {
            String selection = ajout_capteur.getValue();

            if (selection != null) {
                // On l'ajoute à la ListView
                liste_capteur_ajouter.getItems().add(selection);

                // On le retire de la ComboBox
                Platform.runLater(() -> {
                    optionsDisponibles.remove(selection);
                    ajout_capteur.getSelectionModel().clearSelection(); // Remet le texte à vide
                });
            }
        });*/
    }
}