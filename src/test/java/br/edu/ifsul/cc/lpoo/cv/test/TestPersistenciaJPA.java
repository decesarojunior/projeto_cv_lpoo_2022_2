
package br.edu.ifsul.cc.lpoo.cv.test;

import br.edu.ifsul.cc.lpoo.cv.model.Fornecedor;
import br.edu.ifsul.cc.lpoo.cv.model.Produto;
import br.edu.ifsul.cc.lpoo.cv.model.Receita;
import br.edu.ifsul.cc.lpoo.cv.model.TipoProduto;
import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJPA;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author telmo
 */
public class TestPersistenciaJPA {
    
    @Test
    public void testConexaoJPA(){
        //criar um objeto do tipo PersistenciaJPA.
        PersistenciaJPA jpa = new PersistenciaJPA();
        if(jpa.conexaoAberta()){
            System.out.println("conectou no BD via jpa ...");
            jpa.fecharConexao();
        }else{
            System.out.println("nao conectou no BD ...");
                        
        }
    }
    
    //@Test
    public void testListProduto() throws Exception {
        //criar um objeto do tipo PersistenciaJPA.
        PersistenciaJPA jpa = new PersistenciaJPA();
        if(jpa.conexaoAberta()){
            List<Produto> lista = jpa.listProdutos();
            System.out.println("testListProduto : "+ lista.size());
            for(Produto p : lista){
                
                
                System.out.println("Produto: "+ p);
            }
            
            
            
            jpa.fecharConexao();
        }else{
            System.out.println("nao conectou no BD ...");
                        
        }
    }
    
    //@Test
    public void testPersitenciaProduto() throws Exception {
        //criar um objeto do tipo PersistenciaJPA.
        PersistenciaJPA jpa = new PersistenciaJPA();
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
                    
                    p.setNome(p.getNome()+" - nome alterado");
                    jpa.persist(p);//altera o registro a partir do objeto gerenciado (p).
                    
                    jpa.remover(p);//remove o registro a partir do objeto gerenciado (p).
                }
                
                System.out.println("Removeu todos os "+lista.size()+" produtos");
            }                                   
            
            jpa.fecharConexao();
        }else{
            System.out.println("nao conectou no BD ...");
                        
        }
    }
    
    //@Test
    public void testPersitenciaReceita() throws Exception {
        //criar um objeto do tipo PersistenciaJPA.
        PersistenciaJPA jpa = new PersistenciaJPA();
        if(jpa.conexaoAberta()){
            
            List<Receita> lista = jpa.listReceitas();
            if(lista.isEmpty()){
            
              //criar uma objeto do tipo Receita
              //vincular dois Produtos na receita.
                
                
            }else{
                
                for(Receita r : lista){
                
                    //listar os produtos da receita
                    //remover os produtos da receita
                    //remover a receita.
                    jpa.remover(r);
                }
                
                System.out.println("Removeu todas as "+lista.size()+" receitas");
            }
            
            jpa.fecharConexao();
        }else{
            System.out.println("nao conectou no BD ...");
                        
        }
    }
    
    private Fornecedor getFornecedor( PersistenciaJPA jpa ) throws Exception {
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
}
