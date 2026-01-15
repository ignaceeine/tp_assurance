package sn.codeur.tp_assurance_vi.repository;



import jakarta.persistence.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sn.codeur.tp_assurance_vi.entity.Assurance;
import sn.codeur.tp_assurance_vi.repository.interfaceRepo.IInterface;
import sn.codeur.tp_assurance_vi.utils.JpaUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AssuranceRepository implements IInterface<Assurance> {

    private EntityManager entityManager;

    public AssuranceRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }


    @Override
    public void insert(Assurance assurance) {
        entityManager.getTransaction().begin();
        entityManager.persist(assurance);
        entityManager.getTransaction().commit();

    }

    @Override
    public void update(Assurance assurance) {
        entityManager.getTransaction().begin();
        entityManager.merge(assurance);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(int id) {
        Assurance assurance = this.findById(id);
        entityManager.getTransaction().begin();
        entityManager.remove(assurance);
        entityManager.getTransaction().commit();
    }

    @Override
    public Assurance findById(int id) {
        return entityManager.find(Assurance.class, id);
    }

    @Override
    public ObservableList<Assurance> findAll() {
        return FXCollections.observableArrayList(
                entityManager.createQuery("SELECT a FROM Assurance a", Assurance.class).getResultList()
        );
    }

}
