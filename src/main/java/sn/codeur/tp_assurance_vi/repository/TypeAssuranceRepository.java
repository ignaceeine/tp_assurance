package sn.codeur.tp_assurance_vi.repository;


import jakarta.persistence.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sn.codeur.tp_assurance_vi.entity.TypeAssurance;
import sn.codeur.tp_assurance_vi.repository.interfaceRepo.IInterface;
import sn.codeur.tp_assurance_vi.utils.JpaUtil;

import java.util.List;

public class TypeAssuranceRepository  implements IInterface<TypeAssurance> {

    private EntityManager entityManager;

    public TypeAssuranceRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    @Override
    public void insert(TypeAssurance typeAssurance) {
        entityManager.getTransaction().begin();
        entityManager.persist(typeAssurance);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(TypeAssurance typeAssurance) {
        entityManager.getTransaction().begin();
        entityManager.merge(typeAssurance);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(int id) {
        TypeAssurance typeAssurance = this.findById(id);
        entityManager.getTransaction().begin();
        entityManager.remove(typeAssurance);
        entityManager.getTransaction().commit();
    }

    @Override
    public TypeAssurance findById(int id) {
        return entityManager.find(TypeAssurance.class, id);
    }

    @Override
    public ObservableList<TypeAssurance> findAll() {
        return FXCollections.observableArrayList(
                entityManager.createQuery("SELECT t FROM TypeAssurance t", TypeAssurance.class).getResultList()
        );
    }
}
