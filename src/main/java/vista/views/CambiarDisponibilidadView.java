package vista.views;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import entidades.*;
import servicio.ClubDeportivo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CambiarDisponibilidadView extends GridPane {

    public CambiarDisponibilidadView(ClubDeportivo club) {
        setPadding(new Insets(12));
        setHgap(8); setVgap(8);

        ComboBox<Pista> id = new ComboBox();
        CheckBox disponible = new CheckBox("Disponible");
        Button cambiar = new Button("Aplicar");


        ArrayList<Pista> pistas = club.getPistas();
        for (Pista pista : pistas) {
            id.getItems().add(pista);
        }


        addRow(0, new Label("idPista"), id);
        addRow(1, new Label("Estado"), disponible);
        add(cambiar, 1, 2);

        try {
            cambiar.setOnAction(e -> {

                Pista pistaSeleccionada = id.getValue();
                if (pistaSeleccionada == null) {
                    showError("No has seleccionado una pista");
                }
                String idPista = pistaSeleccionada.getIdPista();
                boolean cambioOK = club.CambiarDisponibilidad(idPista, disponible.isSelected());
                if (cambioOK){
                    showInfo("La disponibilidad de la pista " + idPista + "ha sido cambiada correctamente");
                }
                else {
                    showError("No se ha podido cambiar la disponibilidad de la pista");
                }
            });
        } catch (Exception e) {
            showError("Error al cambiar disponibilidad de la pista");
            e.printStackTrace();
        }
    }

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        a.setHeaderText("Error");
        a.showAndWait();
    }
    private void showInfo(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        a.setHeaderText(null);
        a.showAndWait();
    }
}