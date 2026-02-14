package vista.views;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import entidades.*;
import servicio.ClubDeportivo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CancelarReservaView extends GridPane {


    public CancelarReservaView(ClubDeportivo club) {
        setPadding(new Insets(12));
        setHgap(8); setVgap(8);

        ComboBox<Reserva> id = new ComboBox();
        Button cancelar = new Button("Cancelar reserva");
        ArrayList<Reserva> reservas = club.getReservas();

        for (Reserva r : reservas) {
            id.getItems().add(r);
        }

        addRow(0, new Label("Reserva"), id);
        add(cancelar, 1, 1);

        cancelar.setOnAction(e -> {
            try {
                boolean borrarReservaOK = club.cancelarReserva(id.getValue());

                if (borrarReservaOK){
                    showInfo("Se ha cancelado la reserva: " + id + "exitosamente");
                }
                else{
                    showError("No se ha podido cancelar la reserva");
                }
            } catch (Exception ex) {
                showError("Error al cancelar la reserva" + ex.getMessage());
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