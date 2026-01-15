package sn.codeur.tp_assurance_vi.repository.interfaceRepo;


import java.util.List;

public interface IInterface<T> {

    void insert(T t);
    void update(T t);
    void delete(int id);
    T findById(int id);
    List<T> findAll();
}
