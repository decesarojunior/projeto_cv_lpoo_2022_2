
package br.edu.ifsul.cc.lpoo.cv.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author telmo
 */

@Entity
@Table(name = "tb_raca")
public class Raca implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_raca", sequenceName = "seq_raca_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_raca", strategy = GenerationType.SEQUENCE)  
    private Integer id;
    
    @Column(nullable = false, length = 100)
    private String nome;
        
    @ManyToOne
    @JoinColumn(name = "especie_id", nullable = false)
    private Especie especie;
    
    
    public Raca(){
        
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the especie
     */
    public Especie getEspecie() {
        return especie;
    }

    /**
     * @param especie the especie to set
     */
    public void setEspecie(Especie especie) {
        this.especie = especie;
    }
    
    
    
}
