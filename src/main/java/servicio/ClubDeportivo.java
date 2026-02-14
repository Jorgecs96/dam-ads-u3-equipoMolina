package servicio;

import entidades.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

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
        EntityManager em = emf.createEntityManager();
        List<Socio> socios = em.createQuery("SELECT s FROM Socio s", Socio.class).getResultList();
        return new ArrayList<>(socios);
    }

    public ArrayList<Pista> getPistas() {
        EntityManager em = emf.createEntityManager();
        List<Pista> pistas = em.createQuery("SELECT p FROM Pista p", Pista.class).getResultList();
        return new ArrayList<>(pistas);
    }

    public ArrayList<Reserva> getReservas() {
        // A implementar
        ArrayList<Reserva> listaReservas = new ArrayList<Reserva>();
        return listaReservas;
    }

    public Boolean altaPista(Pista pista){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(pista);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (PersistenceException e) {
            em.close();
            return false;
        }


    }

    public void darDeBajaSocio(Socio socioSeleccionado) throws SQLException {
        // A implementar
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.remove(socioSeleccionado);
            em.getTransaction().commit();
            em.close();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
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
