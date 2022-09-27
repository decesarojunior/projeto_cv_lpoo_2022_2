
package br.edu.ifsul.cc.lpoo.cv.model.dao;

import br.edu.ifsul.cc.lpoo.cv.model.Fornecedor;
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
            
            
        }else if (o instanceof Fornecedor){
            
        }else if (o instanceof Procedimento){
            
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Procedimento> listProcedimentos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
