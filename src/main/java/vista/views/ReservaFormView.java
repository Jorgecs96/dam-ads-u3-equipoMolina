package vista.views;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import entidades.*;
import servicio.ClubDeportivo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;


public class ReservaFormView extends GridPane {
    public ReservaFormView(ClubDeportivo club) {
        setPadding(new Insets(12));
        setHgap(8); setVgap(8);

        TextField id = new TextField();
        ComboBox<Socio> idSocio = new ComboBox();
        ComboBox<Pista> idPista = new ComboBox();
        DatePicker fecha = new DatePicker(LocalDate.now());
        TextField hora = new TextField("10:00");
        Spinner<Integer> duracion = new Spinner<>(30, 300, 60, 30);
        TextField precio = new TextField("10.0");
        Button crear = new Button("Reservar");

        for (Socio socio : club.getSocios()) {
            idSocio.getItems().add(socio);
        }

        for (Pista pista : club.getPistas()) {
            idPista.getItems().add(pista);
        }
        addRow(0, new Label("idReserva*"), id);
        addRow(1, new Label("Socio*"), idSocio);
        addRow(2, new Label("Pista*"), idPista);
        addRow(3, new Label("Fecha*"), fecha);
        addRow(4, new Label("Hora inicio* (HH:mm)"), hora);
        addRow(5, new Label("Duración (min)"), duracion);
        addRow(6, new Label("Precio (€)"), precio);
        add(crear, 1, 7);

        crear.setOnAction(e -> {
            try {
                Reserva r = new Reserva();
                r.setIdReserva(id.getText());
                r.setFecha(fecha.getValue());
                r.setHoraInicio(LocalTime.parse(hora.getText()));
                r.setDuracionMin(duracion.getValue());
                r.setPrecio(new BigDecimal(precio.getText()));
                r.setIdSocio(idSocio.getValue());
                r.setIdPista(idPista.getValue());

                Boolean crearReservaOK = club.crearReserva(r);
                if (crearReservaOK){
                    showInfo("Reserva " + id.getText() + " creada correctamente");
                }
                else{
                    showError("No se ha podido crear la reserva");
                }

            } catch (Exception ex) {
                showError("Error en la creación de la reserva: " + ex.getMessage());
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