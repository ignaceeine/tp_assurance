package sn.codeur.tp_assurance_vi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "type_assurances")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TypeAssurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String label;

    public TypeAssurance(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    @OneToMany
    private List<Assurance> assurances;
}
