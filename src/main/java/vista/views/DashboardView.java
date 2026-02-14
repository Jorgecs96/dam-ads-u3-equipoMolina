package vista.views;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import entidades.*;
import servicio.ClubDeportivo;

public class DashboardView extends BorderPane {
    public DashboardView(ClubDeportivo club) {
        setPadding(new Insets(10));
        Label title = new Label("Resumen General del Club");
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        setTop(title);

        TableView<Socio> tablaSocios = new TableView<>();
        TableColumn<Socio, String> c1 = new TableColumn<>("ID");
        c1.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getIdSocio()));

        TableColumn<Socio, String> c2 = new TableColumn<>("Nombre");
        c2.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getNombre() + " " + p.getValue().getApellidos()));

        tablaSocios.getColumns().addAll(c1, c2);
        tablaSocios.getItems().addAll(club.getSocios());

        TableView<Pista> tablaPistas = new TableView<>();
        TableColumn<Pista, String> p1 = new TableColumn<>("ID");
        p1.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getIdPista()));

        TableColumn<Pista, String> p2 = new TableColumn<>("Deporte");
        p2.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDeporte()));

        TableColumn<Pista, String> p3 = new TableColumn<>("Disponible");
        p3.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().isDisponible() ? "Sí" : "No"));

        tablaPistas.getColumns().addAll(p1, p2, p3);
        tablaPistas.getItems().addAll(club.getPistas());

        TableView<Reserva> tablaReservas = new TableView<>();

        TableColumn<Reserva, String> r1 = new TableColumn<>("ID");
        r1.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getIdReserva()));

        TableColumn<Reserva, String> r2 = new TableColumn<>("Socio");
        r2.setCellValueFactory(p -> {
            Socio s = p.getValue().getIdSocio();
            return new SimpleStringProperty(s != null ? s.getNombre() : "Sin socio");
        });

        TableColumn<Reserva, String> r3 = new TableColumn<>("Pista");
        r3.setCellValueFactory(p -> {
            Pista pista = p.getValue().getIdPista();
            return new SimpleStringProperty(pista != null ? pista.getIdPista() : "Sin pista");
        });

        TableColumn<Reserva, String> r4 = new TableColumn<>("Fecha");
        r4.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getFecha().toString()));

        TableColumn<Reserva, String> r5 = new TableColumn<>("Inicio");
        r5.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getHoraInicio().toString()));

        TableColumn<Reserva, String> r6 = new TableColumn<>("Min");
        r6.setCellValueFactory(p -> new SimpleStringProperty(String.valueOf(p.getValue().getDuracionMin())));

        tablaReservas.getColumns().addAll(r1, r2, r3, r4, r5, r6);
        tablaReservas.getItems().addAll(club.getReservas());

        BorderPane centerLayout = new BorderPane();
        centerLayout.setTop(new Label("Socios"));
        centerLayout.setCenter(tablaSocios);

        BorderPane rightLayout = new BorderPane();
        rightLayout.setTop(new Label("Pistas"));
        rightLayout.setCenter(tablaPistas);

        setCenter(centerLayout);
        setRight(rightLayout);

        BorderPane bottomLayout = new BorderPane();
        bottomLayout.setTop(new Label("Reservas"));
        bottomLayout.setCenter(tablaReservas);
        setBottom(bottomLayout);
    }
}