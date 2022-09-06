
package br.edu.ifsul.cc.lpoo.cv.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author telmo
 */

@Entity
@Table(name = "tb_medico")
@DiscriminatorValue("M")
public class Medico extends Pessoa{
    
    @Column(nullable = false, length = 10)
    private String numero_crmv;
    
    public Medico(){
        
    }

    /**
     * @return the numero_crmv
     */
    public String getNumero_crmv() {
        return numero_crmv;
    }

    /**
     * @param numero_crmv the numero_crmv to set
     */
    public void setNumero_crmv(String numero_crmv) {
        this.numero_crmv = numero_crmv;
    }
    
    
    
}
