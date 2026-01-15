package sn.codeur.tp_assurance_vi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "type_assurances")
public class TypeAssurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String label;
}
