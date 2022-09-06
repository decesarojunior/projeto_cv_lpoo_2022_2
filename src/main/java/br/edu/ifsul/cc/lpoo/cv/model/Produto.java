
package br.edu.ifsul.cc.lpoo.cv.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "tb_produto")
public class Produto implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_produto", strategy = GenerationType.SEQUENCE)      
    private Integer id;
    
    @Column(nullable = true, length = 100)
    private String nome;
    
    @Column(nullable = false)
    private Float valor;
        
    @Column(nullable = false)
    private Float quantidade;
        
    @ManyToOne
    @JoinColumn(name = "fornecedor_cpf", nullable = false)
    private Fornecedor fornecedor;
        
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoProduto tipo;
    
    public Produto(){
        
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
     * @return the quantidade
     */
    public Float getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the fornecedor
     */
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor the fornecedor to set
     */
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    /**
     * @return the tipo
     */
    public TipoProduto getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoProduto tipo) {
        this.tipo = tipo;
    }
    
    
    
}
