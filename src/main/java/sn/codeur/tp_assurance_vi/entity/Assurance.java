package sn.codeur.tp_assurance_vi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "assurances")
@NoArgsConstructor
@Data
public class Assurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String numero;
    protected String nomClient;
    protected double montant;

    private static int compteur = 0;

    public Assurance(String nomClient, Double montant) {
        this.nomClient = nomClient;
        this.montant = montant;
        this.numero = genererNumeroContrat();
    }



    private String genererNumeroContrat(){
        compteur++;
        return String.format("ASS%04d", compteur);
    }


    //Abstract
    //public abstract double calculerPrime() ;
    //public abstract String getTypeAssurance();

   /* public Double calculerCoutTotal(int nbAnnees){
        return calculerPrime() * nbAnnees;
    }*/

    public static int getNombreContrats(){
        return compteur;
    }

    @ManyToOne
    private TypeAssurance typeAssurance;

}
