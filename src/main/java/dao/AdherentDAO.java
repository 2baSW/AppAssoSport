package dao;

import entity.Adherent;
import entity.Groupe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class AdherentDAO {

    private EntityManager em;

    public AdherentDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Adherent adherent) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(adherent);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Adherent findById(Long id) {
        return em.find(Adherent.class, id);
    }

    public List<Adherent> findAll() {
        return em.createQuery("SELECT a FROM Adherent a", Adherent.class).getResultList();
    }

    public void update(Adherent adherent) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(adherent);
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
            Adherent adherent = em.find(Adherent.class, id);
            if (adherent != null) {
                em.remove(adherent);
                System.out.println("Adhérent supprimé avec succès : " + adherent.getNom());
            } else {
                System.out.println("Aucun adhérent trouvé avec l'ID : " + id);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la suppression : " + e.getMessage(), e);
        }
    }
    
    public void assignToGroup(Long adherentId, Long groupId) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Adherent adherent = em.find(Adherent.class, adherentId);
            Groupe groupe = em.find(Groupe.class, groupId);

            if (adherent != null) {
                adherent.setGroupe(groupe);
                em.merge(adherent);
                System.out.println("Adhérent assigné au groupe : " + groupe.getNom());
            } else {
                System.out.println("Aucun adhérent trouvé avec l'ID : " + adherentId);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de l'assignation au groupe : " + e.getMessage(), e);
        }
    }

    public void removeFromGroup(Long adherentId) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Adherent adherent = em.find(Adherent.class, adherentId);

            if (adherent != null) {
                adherent.setGroupe(null);
                em.merge(adherent);
                System.out.println("Adhérent retiré du groupe.");
            } else {
                System.out.println("Aucun adhérent trouvé avec l'ID : " + adherentId);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors du retrait du groupe : " + e.getMessage(), e);
        }
    }

}
