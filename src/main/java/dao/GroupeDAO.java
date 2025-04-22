package dao;

import entity.Groupe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class GroupeDAO {

    private EntityManager em;

    public GroupeDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Groupe groupe) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(groupe);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Groupe findById(Long id) {
        return em.find(Groupe.class, id);
    }

    public List<Groupe> findAll() {
        TypedQuery<Groupe> query = em.createQuery("SELECT g FROM Groupe g", Groupe.class);
        return query.getResultList();
    }

    public void update(Groupe groupe) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(groupe);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void delete(Long id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Groupe groupe = em.find(Groupe.class, id);
            if (groupe != null) {
                em.remove(groupe);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public List<Groupe> findGroupsWithAdherents() {
        TypedQuery<Groupe> query = em.createQuery(
            "SELECT g FROM Groupe g LEFT JOIN FETCH g.adherents", Groupe.class
        );
        return query.getResultList();
    }
}
