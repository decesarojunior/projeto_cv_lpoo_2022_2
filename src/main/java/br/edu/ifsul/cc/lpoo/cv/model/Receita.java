
package br.edu.ifsul.cc.lpoo.cv.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author telmo
 */
@Entity
@Table(name = "tb_receita")
@NamedQueries({@NamedQuery(name="Receita.orderbyid", query="select r from Receita r order by r.id asc")})
public class Receita implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_receita", sequenceName = "seq_receita_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_receita", strategy = GenerationType.SEQUENCE)      
    private Integer id;
    
    @Column(nullable = true, length = 200)
    private String orientacao;
    
    @ManyToOne
    @JoinColumn(name = "consulta_id", nullable = false)
    private Consulta consulta;
    
    @ManyToMany
    @JoinTable(name = "tb_receita_produto", joinColumns = {@JoinColumn(name = "receita_id")}, //agregacao, vai gerar uma tabela associativa.
                                       inverseJoinColumns = {@JoinColumn(name = "produto_id")})       
    private List<Produto> produtos;
    
    public Receita(){
        
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
     * @return the orientacao
     */
    public String getOrientacao() {
        return orientacao;
    }

    /**
     * @param orientacao the orientacao to set
     */
    public void setOrientacao(String orientacao) {
        this.orientacao = orientacao;
    }

    /**
     * @return the consulta
     */
    public Consulta getConsulta() {
        return consulta;
    }

    /**
     * @param consulta the consulta to set
     */
    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
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
    
    
    
}
