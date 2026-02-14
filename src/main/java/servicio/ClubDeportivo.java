package servicio;

import entidades.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ClubDeportivo {
    private EntityManagerFactory emf;

    public ClubDeportivo() {
        emf = Persistence.createEntityManagerFactory("clubdeportivo");
    }

    public ArrayList<Socio> getSocios() {
        // A implementar
        ArrayList<Socio> listaSocios = new ArrayList<Socio>();
        return listaSocios;
    }

    public ArrayList<Pista> getPistas() {
        // A implementar
        ArrayList<Pista> listaPistas = new ArrayList<Pista>();
        return listaPistas;
    }

    public ArrayList<Reserva> getReservas() {
        // A implementar
        ArrayList<Reserva> listaReservas = new ArrayList<Reserva>();
        return listaReservas;
    }

    public void altaPista(Pista pista) throws SQLException {
        // A implementar
    }

    public void darDeBajaSocio(Socio socioSeleccionado) throws SQLException {
        // A implementar
    }

    public void darDeAltaSocio(Socio s) throws SQLException{
        // A implementar
    }

    public void crearReserva(Reserva r) throws SQLException {
        // A implementar (LLAMAR A PROCEDIMIENTO SP_CREAR_RESERVA)
    }

    public void CambiarDisponibilidad(String idPista, Boolean disponible) throws SQLException {
        // A implementar (HACER UPDATE A LA PISTA)
    }
  
    public void cancelarReserva(Reserva r) throws SQLException {
        // A implementar
    }
}
