package vista.views;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import entidades.*;
import servicio.ClubDeportivo;

public class PistaFormView extends GridPane {
    public PistaFormView(ClubDeportivo club) {
        setPadding(new Insets(12));
        setHgap(8); setVgap(8);

        TextField id = new TextField();
        TextField deporte = new TextField();
        TextField descripcion = new TextField();
        CheckBox disponible = new CheckBox("Disponible");
        Button crear = new Button("Crear");

        addRow(0, new Label("idPista*"), id);
        addRow(1, new Label("Deporte"), deporte);
        addRow(2, new Label("Descripción"), descripcion);
        addRow(3, new Label("Operativa"), disponible);
        add(crear, 1, 4);

        crear.setOnAction(e -> {
            try {
                String dep = deporte.getText().toLowerCase();
                if (!dep.equals("fútbol sala") && !dep.equals("pádel") && !dep.equals("tenis")) {
                    showError("Solo se permiten pistas de: fútbol sala, tenis o pádel");
                    return;
                }

                Pista nueva = new Pista();
                nueva.setIdPista(id.getText());
                nueva.setDeporte(dep);
                nueva.setDescripcion(descripcion.getText());
                nueva.setDisponible(disponible.isSelected());

                Boolean altaPistaOK = club.altaPista(nueva);
                if (altaPistaOK){
                    showInfo("Pista " + id.getText() + " ha sido creada correctamente.");
                }
                else {
                    showError("No se ha podido dar de alta la pista");
                }

            } catch (Exception ex) {
                showError("Error al guardar en la base de datos: " + ex.getMessage());
                ex.printStackTrace();
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