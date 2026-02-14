package servicio;

import entidades.*;
import jakarta.persistence.*;

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
        EntityManager em = emf.createEntityManager();
        List<Reserva> reservas = em.createQuery("SELECT r FROM Reserva r", Reserva.class).getResultList();
        return new ArrayList<>(reservas);
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

    public Boolean darDeBajaSocio(Socio socioSeleccionado){
        EntityManager em = emf.createEntityManager();
        try{
            Socio socioABorrar = em.find(Socio.class, socioSeleccionado.getIdSocio());
            em.getTransaction().begin();
            em.remove(socioABorrar);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (RuntimeException e) {
            em.close();
            return false;
        }
    }

    public Boolean darDeAltaSocio(Socio s){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (PersistenceException e) {
            em.close();
            return false;
        }
    }

    public boolean crearReserva(Reserva r) {
        // Usamos la misma lógica que en el metodo borrarEmpleado del Ejercicio1 de clase
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select r from Reserva r where r.idPista = :idPista and r.fecha = :fecha and r.horaInicio = :horaInicio", Reserva.class);
        q.setParameter("idPista", r.getIdPista());
        q.setParameter("fecha", r.getFecha());
        q.setParameter("horaInicio", r.getHoraInicio());
        List<Reserva> listaReservas = q.getResultList();
        if (listaReservas.size() > 0) {
            em.close();
            return false;
        } else {
            try {
                em.getTransaction().begin();
                em.persist(r);
                em.getTransaction().commit();
                em.close();
                return true;
            } catch (PersistenceException ex) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                em.close();
                return false;
            }
        }
    }

    public Boolean CambiarDisponibilidad(String idPista, Boolean disponible){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();

            Pista pista = em.find(Pista.class, Long.parseLong(idPista));
            if (pista != null){
                pista.setDisponible(disponible);
                em.getTransaction().commit();
                return true;
            }
            else {
                return false;
            }
        } catch (PersistenceException e) {
            em.close();
            return false;
        }
    }

    public Boolean cancelarReserva(Reserva r) {
        EntityManager em = emf.createEntityManager();
        try{
            Reserva socioABorrar = em.find(Reserva.class, r.getIdReserva());
            em.getTransaction().begin();
            em.remove(socioABorrar);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (RuntimeException e) {
            em.close();
            return false;
        }
    }
}