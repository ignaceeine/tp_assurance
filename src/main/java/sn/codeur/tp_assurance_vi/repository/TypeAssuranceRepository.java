package sn.codeur.tp_assurance_vi.repository;



import sn.codeur.tp_assurance_vi.entity.TypeAssurance;
import sn.codeur.tp_assurance_vi.repository.interfaceRepo.IInterface;

import java.util.List;

public class TypeAssuranceRepository  implements IInterface<TypeAssurance> {
    @Override
    public void insert(TypeAssurance typeAssurance) {

    }

    @Override
    public void update(TypeAssurance typeAssurance) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public TypeAssurance findById(int id) {
        return null;
    }

    @Override
    public List<TypeAssurance> findAll() {
        return List.of();
    }
}
