package vista.views;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import entidades.*;
import servicio.ClubDeportivo;

import java.sql.Connection;
import java.sql.SQLException;

public class SocioFormView extends GridPane {

    public SocioFormView(ClubDeportivo club) {
        setPadding(new Insets(12));
        setHgap(8);
        setVgap(8);

        TextField id = new TextField();
        TextField dni = new TextField();
        TextField nombre = new TextField();
        TextField apellidos = new TextField();
        TextField tel = new TextField();
        TextField email = new TextField();
        Button crear = new Button("Crear");

        addRow(0, new Label("idSocio*"), id);
        addRow(1, new Label("DNI"), dni);
        addRow(2, new Label("Nombre"), nombre);
        addRow(3, new Label("Apellidos"), apellidos);
        addRow(4, new Label("Teléfono"), tel);
        addRow(5, new Label("Email"), email);
        add(crear, 1, 6);

        try {
            crear.setOnAction(e -> {
                Socio socio = new Socio();
                String idSocio = id.getText();
                String dniSocio = dni.getText();
                String nombreSocio = nombre.getText();
                String apellidosSocio = apellidos.getText();

                String tlfno = tel.getText();
                String correo = email.getText();
                socio.setIdSocio(idSocio);
                socio.setDni(dniSocio);
                socio.setNombre(nombreSocio);
                socio.setApellidos(apellidosSocio);

                if (club.darDeAltaSocio(socio)){
                    showInfo("Socio registrado correctamente");
                }
            });
        }
        catch (Exception ex){
            showError("No se ha podido dar de alta al socio.");
            ex.printStackTrace();
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