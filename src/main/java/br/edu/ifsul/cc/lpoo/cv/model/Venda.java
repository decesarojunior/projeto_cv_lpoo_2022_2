
package br.edu.ifsul.cc.lpoo.cv.model;

import java.io.Serializable;
import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author telmo
 */

@Entity
@Table(name = "tb_venda")
public class Venda implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_venda", sequenceName = "seq_venda_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_venda", strategy = GenerationType.SEQUENCE)      
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String observacao;
    
    @Column(nullable = false)
    private Float valor_total;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Pagamento formaPagamento;
    
    @ManyToOne
    @JoinColumn(name = "cliente_cpf", nullable = false)
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "funcionario_cpf", nullable = false)
    private Funcionario funcionario;
    
    @ManyToMany
    @JoinTable(name = "tb_venda_consulta", joinColumns = {@JoinColumn(name = "venda_id")}, //agregacao, vai gerar uma tabela associativa.
                                       inverseJoinColumns = {@JoinColumn(name = "consulta_id")})   
    private List<Consulta> consultas;
    
    
    @ManyToMany
    @JoinTable(name = "tb_venda_produto", joinColumns = {@JoinColumn(name = "venda_id")}, //agregacao, vai gerar uma tabela associativa.
                                       inverseJoinColumns = {@JoinColumn(name = "produto_id")})       
    private List<Produto> produtos;

    
    public Venda(){
        
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
     * @return the valor_total
     */
    public Float getValor_total() {
        return valor_total;
    }

    /**
     * @param valor_total the valor_total to set
     */
    public void setValor_total(Float valor_total) {
        this.valor_total = valor_total;
    }

    /**
     * @return the data
     */
    public Calendar getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Calendar data) {
        this.data = data;
    }

    /**
     * @return the formaPagamento
     */
    public Pagamento getFormaPagamento() {
        return formaPagamento;
    }

    /**
     * @param formaPagamento the formaPagamento to set
     */
    public void setFormaPagamento(Pagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the funcionario
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * @param funcionario the funcionario to set
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * @return the consultas
     */
    public List<Consulta> getConsultas() {
        return consultas;
    }

    /**
     * @param consultas the consultas to set
     */
    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
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
