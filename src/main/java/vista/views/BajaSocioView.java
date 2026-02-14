package vista.views;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import entidades.*;
import servicio.ClubDeportivo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class BajaSocioView extends GridPane {


    public BajaSocioView(ClubDeportivo club) {
        setPadding(new Insets(12));
        setHgap(8); setVgap(8);

        ComboBox<Socio> id = new ComboBox<>();
        addRow(0, new Label("Socio"), id);
        ArrayList<Socio> socios = club.getSocios();
        for (Socio socio : socios) {
            id.getItems().add(socio);
        }
        Button baja = new Button("Dar de baja");
        addRow(1, baja);

        try {
            baja.setOnAction(e -> {
                Socio socioSeleccionado = id.getValue();
                if (socioSeleccionado == null) {
                    showError("Asegurese de haber seleccionado un socio");
                }
                boolean borradoOK = club.darDeBajaSocio(socioSeleccionado);
                if (borradoOK) {
                    showInfo("Socio " + socioSeleccionado.getNombre() + " eliminado correctamente");
                    id.getItems().remove(socioSeleccionado);
                } else {
                    showError("No se pudo eliminar al socio. Asegurate que no tiene reservas pendientes");
                }
            });
        } catch (Exception e) {
            showError("Error al dar de bajo al socio");
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