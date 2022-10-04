
package br.edu.ifsul.cc.lpoo.cv.model.dao;

import br.edu.ifsul.cc.lpoo.cv.model.Consulta;
import br.edu.ifsul.cc.lpoo.cv.model.Fornecedor;
import br.edu.ifsul.cc.lpoo.cv.model.Medico;
import br.edu.ifsul.cc.lpoo.cv.model.Procedimento;
import br.edu.ifsul.cc.lpoo.cv.model.Produto;
import br.edu.ifsul.cc.lpoo.cv.model.Receita;
import br.edu.ifsul.cc.lpoo.cv.model.TipoProduto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 *
 * @author Prof. Telmo Jr
 */
public class PersistenciaJDBC implements InterfacePersistencia {
    
    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "postgres";
    private final String SENHA = "123456";
    public static final String URL = "jdbc:postgresql://localhost:5432/db_cv_2022_2";
    private Connection con = null;

    
    
    public PersistenciaJDBC() throws Exception{
        
        Class.forName(DRIVER); //carregamento do driver postgresql em tempo de execução
        System.out.println("Tentando estabelecer conexao JDBC com : "+URL+" ...");
            
        this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA); 

        
    }

    public Boolean conexaoAberta() {
        
        try {
            if(con != null)
                return !con.isClosed();//verifica se a conexao está aberta
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return false;
        
    }

    @Override
    public void fecharConexao() {        
        
        try{                               
            this.con.close();//fecha a conexao.
            System.out.println("Fechou conexao JDBC");
        }catch(SQLException e){            
            e.printStackTrace();//gera uma pilha de erro na saida.
        } 
        
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void persist(Object o) throws Exception {
        
       
        if(o instanceof Produto){
            
            Produto p = (Produto) o;
            if(p.getId() == null){
                
                PreparedStatement ps = this.con.prepareStatement("insert into tb_produto (id, nome, quantidade, tipo, valor, fornecedor_cpf) "
                                                               + "values (nextval('seq_produto_id'), ?, ?, ?, ?, ?) returning id;");
                ps.setString(1, p.getNome());
                ps.setFloat(2, p.getQuantidade());
                ps.setString(3, p.getTipo().name());
                ps.setFloat(4, p.getValor());
                ps.setString(5, p.getFornecedor().getCpf());
                
                
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    p.setId(rs.getInt("id"));
                }
                
            }else{
                
                PreparedStatement ps = this.con.prepareStatement("update tb_produto set nome = ?, quantidade = ?, tipo = ?, valor = ?, fornecedor_cpf = ? "
                                                               + "where id = ?; ");
                
                ps.setString(1, p.getNome());
                ps.setFloat(2, p.getQuantidade());
                ps.setString(3, p.getTipo().name());
                ps.setFloat(4, p.getValor());
                ps.setString(5, p.getFornecedor().getCpf());
                ps.setInt(6, p.getId());
                
                ps.execute();
            
            }
            
        }else if (o instanceof Receita){
            
            
            Receita r = (Receita) o;
            if(r.getId() == null){
                
                PreparedStatement ps = this.con.prepareStatement("insert into tb_receita (id, orientacao, consulta_id) "
                                                               + "values (nextval('seq_receita_id'), ?, ?) returning id;");
                ps.setString(1, r.getOrientacao());
                ps.setInt(2,  r.getConsulta().getId());
                
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    r.setId(rs.getInt("id"));
                }
              
                if(r.getProdutos() != null && !r.getProdutos().isEmpty()){
                    
                    for(Produto p : r.getProdutos()){
                        
                        PreparedStatement ps2 = this.con.prepareStatement("insert into tb_receita_produto (receita_id, produto_id) "
                                                               + "values (?, ?) ");
               
                        ps2.setInt(1,  r.getId());
                        ps2.setInt(2,  p.getId());
                        
                        ps2.execute();
                        
                        ps2.close();
                        
                    }                                        
                    
                }
                rs.close();
                ps.close();
                
            }else{
               
                PreparedStatement ps = this.con.prepareStatement("update tb_receita set orientacao = ?, consulta_id = ? "
                                                               + "where id = ?");
                ps.setString(1, r.getOrientacao());
                ps.setInt(2,  r.getConsulta().getId());
                ps.setInt(3, r.getId());
                
                ps.execute();
                        
                //remove as linhas na tabela associativa.
                PreparedStatement ps2 = this.con.prepareStatement("delete from tb_receita_produto where receita_id = ?");
                ps2.setInt(1, r.getId());
                
                ps2.execute();
                
                if(r.getProdutos() != null && !r.getProdutos().isEmpty()){
                    
                    for(Produto p : r.getProdutos()){
                        
                        PreparedStatement ps3 = this.con.prepareStatement("insert into tb_receita_produto (receita_id, produto_id) "
                                                               + "values (?, ?) ");
               
                        ps3.setInt(1,  r.getId());
                        ps3.setInt(2,  p.getId());
                        
                        ps3.execute();
                        
                        ps3.close();
                        
                    }                                        
                    
                }
                
                
            }
            
        }else if (o instanceof Fornecedor){
            
            
            
        }else if (o instanceof Medico){
            
            
        }else if (o instanceof Procedimento){
            
        }else if (o instanceof Consulta){
            
            Consulta c = (Consulta) o;
            if(c.getId() == null){
                
                PreparedStatement ps = this.con.prepareStatement("insert into tb_consulta (id, data, data_retorno, observacao, valor, medico_cpf, pet_id) values "
                                                               + " (nextval('seq_consulta_id'), ?,?,?) returning id ");
                
                ps.setDate(1, new java.sql.Date(c.getData().getTimeInMillis()));
                ps.setDate(2, new java.sql.Date(c.getData_retorno().getTimeInMillis()));
                ps.setString(3, c.getObservacao());
                ps.setFloat(4, c.getValor());
                ps.setString(4, c.getMedico().getCpf());
                ps.setInt(5, c.getPet().getId());
                
                ResultSet rs = ps.executeQuery();
                
                if(rs.next()){
                    c.setId(rs.getInt("id"));
                }
                
            }
            
            
        }
    }

    @Override
    public void remover(Object o) throws Exception {
        

       if(o instanceof Produto){
           Produto p = (Produto) o;
           
           PreparedStatement ps = this.con.prepareStatement("delete from tb_produto where id = ?");
           ps.setInt(1, p.getId());
           
           ps.execute();
           
       }else if(o instanceof Fornecedor){
           
           
       }else if(o instanceof Receita){
           
           
            Receita r = (Receita) o;
            
            //remove as linhas na tabela associativa.
            PreparedStatement ps2 = this.con.prepareStatement("delete from tb_receita_produto where receita_id = ?");
            ps2.setInt(1, r.getId());
            ps2.execute();
                    
            //remove as linhas na tabela.
            PreparedStatement ps3 = this.con.prepareStatement("delete from tb_receita where id = ?");
            ps3.setInt(1, r.getId());
            ps3.execute();
           
       }
    }

    @Override
    public List<Produto> listProdutos() throws Exception {
        
        
        List<Produto> lista;
        
         PreparedStatement ps = this.con.prepareStatement(" select p.id, p.nome, p.quantidade, p.tipo, p.valor, p.fornecedor_cpf, f.ie "
                                                        + " from tb_produto p, tb_fornecedor f where p.fornecedor_cpf=f.cpf order by id asc");
         
         ResultSet rs = ps.executeQuery();
         
         lista = new ArrayList();
         
         while(rs.next()){
             
             Produto p = new Produto();
             p.setId(rs.getInt("id"));
             p.setNome(rs.getString("nome"));
             p.setQuantidade(rs.getFloat("quantidade"));
             if(rs.getString("tipo").equals(TipoProduto.CONSULTA.name())){
                p.setTipo(TipoProduto.CONSULTA);
             }else if(rs.getString("tipo").equals(TipoProduto.ATENDIMENTO_AMBULATORIAL.name())){
                p.setTipo(TipoProduto.ATENDIMENTO_AMBULATORIAL);
             }
             p.setValor(rs.getFloat("valor"));
                Fornecedor fc = new Fornecedor();
                fc.setCpf(rs.getString("fornecedor_cpf"));
                fc.setIe(rs.getString("ie"));
             p.setFornecedor(fc);
             
             
             lista.add(p);
             
         }
         
         return lista;
        
    }

    @Override
    public List<Fornecedor> listFornecedores() throws Exception {
        
        List<Fornecedor> lista;
        
         PreparedStatement ps = this.con.prepareStatement(" select p.cpf, p.nome "
                                                        + " from tb_pessoa p, tb_fornecedor f where p.cpf=f.cpf order by p.cpf asc");
         
         ResultSet rs = ps.executeQuery();
         
         lista = new ArrayList();
         
         while(rs.next()){
             
             Fornecedor f = new Fornecedor();
             f.setCpf(rs.getString("cpf"));
             f.setNome(rs.getString("nome"));
             
             
             lista.add(f);
             
         }
         
         return lista;
    }

    @Override
    public List<Receita> listReceitas() throws Exception {
        
        List<Receita> lista = null;
        
        PreparedStatement ps = this.con.prepareStatement(" select r.id, r.orientacao, r.consulta_id "
                                                        + " from tb_receita r, tb_consulta c where r.consulta_id=c.id order by r.id asc");
         
        ResultSet rs = ps.executeQuery();
         
        lista = new ArrayList();
         
        while(rs.next()){
             
            Receita r = new Receita();
            r.setId(rs.getInt("id"));
            r.setOrientacao(rs.getString("orientacao"));
            Consulta c = new Consulta();
            c.setId(rs.getInt("consulta_id"));
            r.setConsulta(c);
            
            PreparedStatement ps2 = this.con.prepareStatement("select p.id, p.nome "
                                                        + " from tb_produto p, tb_receita_produto rp where p.id=rp.produto_id and rc.receita_id = ? order by r.id asc");
            
            ps2.setInt(1, r.getId());
            
            ResultSet rs2 = ps2.executeQuery();
            
            while(rs2.next()){
                
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                
                r.setProduto(p);
            }
            rs2.close();
            ps2.close();
            
            lista.add(r);
             
        }
         
        rs.close();
        ps.close();
        
        return lista;
        
        
    }

    @Override
    public List<Procedimento> listProcedimentos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Consulta> listConsultas() throws Exception {
        
        List<Consulta> lista;
        
        PreparedStatement ps = this.con.prepareStatement(" select c.id, c.data, c.data_retorno, c.observacao, c.valor, c.medico_cpf, c.pet_id "
                                                       + " from tb_consulta c order by c.id asc");
         
        ResultSet rs = ps.executeQuery();
         
        lista = new ArrayList();
         
        while(rs.next()){
             
             Consulta c = new Consulta();
             c.setId(rs.getInt("id"));
             Calendar cData = Calendar.getInstance();
             cData.setTimeInMillis(rs.getDate("data").getTime());
             c.setData(cData);
             
             //... recuperar os demais campos.
             
             //... recuperar as receitas da consulta
             
             
             lista.add(c);
             
        }
         
        return lista;
    }

    @Override
    public List<Medico> listMedicos() throws Exception {
        
        List<Medico> lista;
        
        PreparedStatement ps = this.con.prepareStatement(" select p.cpf, p.nome "
                                                        + " from tb_pessoa p, tb_medico f where p.cpf=m.cpf order by p.cpf asc");
         
        ResultSet rs = ps.executeQuery();
         
        lista = new ArrayList();
         
        while(rs.next()){
             
             Medico m = new Medico();
             m.setCpf(rs.getString("cpf"));
             m.setNome(rs.getString("nome"));
             
             lista.add(m);
             
        }
         
        return lista;
    }
    
}
