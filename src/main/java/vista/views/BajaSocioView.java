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

        baja.setOnAction(e -> {
        //LLamar al método del modelo para dar de baja  a un socio.
            Socio socioSeleccionado = id.getValue();
            try {
                if (socioSeleccionado == null) {
                    showError("Asegurese de haber seleccionado un socio");
                }
                club.darDeBajaSocio(socioSeleccionado);
                showInfo("Socio eliminado correctamente");
            } catch (SQLException ex) {
                if (ex.getMessage().contains("fk_reservas_socio")) {
                    showError("No se puede eliminar el socio porque tiene reservas asociadas.\n" +
                            "El cliente no tiene que tener reservas activas antes de darlo de baja.");
                } else {
                    showError("Error al eliminar socio:\n" + ex.getMessage());
                }
            }
        });
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
