
package br.edu.ifsul.cc.lpoo.cv.test;

import br.edu.ifsul.cc.lpoo.cv.model.Consulta;
import br.edu.ifsul.cc.lpoo.cv.model.Fornecedor;
import br.edu.ifsul.cc.lpoo.cv.model.Medico;
import br.edu.ifsul.cc.lpoo.cv.model.Produto;
import br.edu.ifsul.cc.lpoo.cv.model.Receita;
import br.edu.ifsul.cc.lpoo.cv.model.TipoProduto;
import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJDBC;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author telmo
 */
public class TestPersistenciaJDBC {
    
    //@Test
    public void testConexaoJPA() throws Exception {
        //criar um objeto do tipo PersistenciaJDBC.
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if(jdbc.conexaoAberta()){
            System.out.println("conectou no BD via jpa ...");
            jdbc.fecharConexao();
        }else{
            System.out.println("nao conectou no BD ...");
                        
        }
    }
    
    //@Test
    public void testListProduto() throws Exception {
        //criar um objeto do tipo PersistenciaJPA.
        PersistenciaJDBC jdbc = new PersistenciaJDBC();
        if(jdbc.conexaoAberta()){
            List<Produto> lista = jdbc.listProdutos();
            for(Produto p : lista){
                
                
                System.out.println("Produto: "+ p);
            }            
            
            jdbc.fecharConexao();
        }else{
            System.out.println("nao conectou no BD ...");
                        
        }
    }
    
    //@Test
    public void testPersitenciaProduto() throws Exception {
        //criar um objeto do tipo PersistenciaJPA.
        PersistenciaJDBC jpa = new PersistenciaJDBC();
        if(jpa.conexaoAberta()){
            
            
            List<Produto> lista = jpa.listProdutos();
            if(lista.isEmpty()){
            
                Produto p = new Produto();
                p.setNome("teste");
                p.setFornecedor(getFornecedor(jpa));
                p.setQuantidade(0f);
                p.setTipo(TipoProduto.ATENDIMENTO_AMBULATORIAL);
                p.setValor(100f);
                jpa.persist(p);
                System.out.println("Incluiu o produto: "+ p.getId());
                
                
            }else{
                
                for(Produto p : lista){
                    jpa.remover(p);
                }
                
                System.out.println("Removeu todos os "+lista.size()+" produtos");
            }
            
            jpa.fecharConexao();
        }else{
            System.out.println("nao conectou no BD ...");
                        
        }
    }
    
    @Test
    public void testPersistenciaReceita() throws Exception {
        
        
        //criar um objeto do tipo PersistenciaJPA.
        PersistenciaJDBC jpa = new PersistenciaJDBC();
        if(jpa.conexaoAberta()){
            
            
            List<Receita> lista = jpa.listReceitas();
            if(lista.isEmpty()){
            
                Receita r = new Receita();
                r.setOrientacao("teste de receita. ");
                r.setConsulta(getConsulta(jpa));                
                r.setProdutos(jpa.listProdutos());
                
                jpa.persist(r);
                System.out.println("Incluiu a receita: "+ r.getId());
                
                
            }else{
                
                for(Receita r : lista){
                    jpa.remover(r);
                }
                
                System.out.println("Removeu as "+lista.size()+" receitas existentes.");
            }
            
            jpa.fecharConexao();
        }else{
            System.out.println("nao conectou no BD ...");
                        
        }
        
        
    }
    
    private Consulta getConsulta( PersistenciaJDBC jpa ) throws Exception {
        
        //criar um objeto do tipo PersistenciaJPA.     
        if(jpa.conexaoAberta()){
            
            List<Consulta> lista = jpa.listConsultas();
            if(lista.isEmpty()){
            
                Consulta c = new Consulta();
                c.setObservacao("teste de consulta");
                c.setData(Calendar.getInstance());
                c.setValor(0f);
                c.setMedico(null);
                c.setPet(null);
                jpa.persist(c);
                                
                return c;
                
            }else{
                                
               return lista.get(0);
            }            
            
        }else{
            System.out.println("nao conectou no BD ...");
                        
        }
        
        return null;
    }
    
    
    
    private Fornecedor getFornecedor( PersistenciaJDBC jpa ) throws Exception {
        //criar um objeto do tipo PersistenciaJPA.     
        if(jpa.conexaoAberta()){
            
            List<Fornecedor> lista = jpa.listFornecedores();
            if(lista.isEmpty()){
            
                Fornecedor f = new Fornecedor();
                f.setNome("teste");
                f.setCnpj("08316535000");
                f.setIe("");
                f.setData_cadastro(Calendar.getInstance());
                f.setNome("Laboratório Santa Inês");
                f.setRg("123");
                f.setSenha("123");
                f.setCpf("00001337788");
                jpa.persist(f);
                                
                return f;
                
            }else{
                                
               return lista.get(0);
            }            
            
        }else{
            System.out.println("nao conectou no BD ...");
                        
        }
        
        return null;
    }
    
    
    private Medico getMedico( PersistenciaJDBC jpa ) throws Exception {
        
        //criar um objeto do tipo PersistenciaJPA.     
        if(jpa.conexaoAberta()){
            
            List<Medico> lista = jpa.listMedicos();
            if(lista.isEmpty()){
            
                Medico m = new Medico();
                m.setNome("teste de teste");                
                m.setData_cadastro(Calendar.getInstance());
                m.setRg("123");
                m.setSenha("123");
                m.setCpf("00001337780");
                jpa.persist(m);
                                
                return m;
                
            }else{
                                
               return lista.get(0);
            }            
            
        }else{
            System.out.println("nao conectou no BD ...");
                        
        }
        
        return null;
    }
    
}
