
package br.edu.ifsul.cc.lpoo.cv.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author telmo jr
 */

@Entity
@Table(name = "tb_procedimento")
public class Procedimento {
    
    @Id
    @SequenceGenerator(name = "seq_procedimento", sequenceName = "seq_procedimento_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_procedimento", strategy = GenerationType.SEQUENCE)  
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String observacao;
    
    
    @Column(nullable = false)
    private Float valor;
    
    
    @ManyToMany
    @JoinTable(name = "tb_procedimento_produto", joinColumns = {@JoinColumn(name = "procedimento_id")}, //agregacao, vai gerar uma tabela associativa.
                                       inverseJoinColumns = {@JoinColumn(name = "produto_id")})       
 
    private List<Produto> produtos;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    
    
    @ManyToOne
    @JoinColumn(name = "agenda_id", nullable = false)
    private Agenda agenda;
    
    
    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;
    
    public Procedimento(){
        
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
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return the valor
     */
    public Float getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Float valor) {
        this.valor = valor;
    }

    /**
     * @return the produtos
     */
    public List<Produto> getProdutos() {
        return produtos;
    }

    /**
     * @param produtos the produtos to set
     */
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the agenda
     */
    public Agenda getAgenda() {
        return agenda;
    }

    /**
     * @param agenda the agenda to set
     */
    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    /**
     * @return the pet
     */
    public Pet getPet() {
        return pet;
    }

    /**
     * @param pet the pet to set
     */
    public void setPet(Pet pet) {
        this.pet = pet;
    }
    
    
    
}
