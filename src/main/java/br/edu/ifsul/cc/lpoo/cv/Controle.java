
package br.edu.ifsul.cc.lpoo.cv;

import br.edu.ifsul.cc.lpoo.cv.gui.JFramePrincipal;
import br.edu.ifsul.cc.lpoo.cv.gui.JMenuBarHome;
import br.edu.ifsul.cc.lpoo.cv.gui.JPanelHome;
import br.edu.ifsul.cc.lpoo.cv.gui.autenticacao.JPanelAutenticacao;
import br.edu.ifsul.cc.lpoo.cv.model.Funcionario;
import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJDBC;
import javax.swing.JOptionPane;

/**
 *
 * @author telmo
 */
public class Controle {
    
    private PersistenciaJDBC conexaoJDBC;
    private JFramePrincipal frame; // frame principal da minha aplicação gráfica

    private JPanelAutenticacao telaAutenticacao; //tela de autentiacao.
    
    private JMenuBarHome menuBar;
    
    private JPanelHome  telaHome;
    
    
    public Controle(){                  
    }
    
    public boolean conectarBD() throws Exception {
            conexaoJDBC = new PersistenciaJDBC();
            if(getConexaoJDBC()!= null){
                return getConexaoJDBC().conexaoAberta();
            }
            return false;
    }
    public PersistenciaJDBC getConexaoJDBC() {
        return conexaoJDBC;
    }
    public void initComponents(){
        
        frame = new JFramePrincipal(this);
        
        telaAutenticacao = new JPanelAutenticacao(this);// inicializa
        
        menuBar = new JMenuBarHome(this);
        
        telaHome = new JPanelHome(this);
        
        frame.addTela(telaAutenticacao, "tela_autenticacao"); //adiciona
        
        frame.addTela(telaHome, "tela_home"); //adiciona
         
        
        frame.showTela("tela_autenticacao");   //mostra
         
        
        frame.setVisible(true); // torna visível o jframe
    }
    
    public void autenticar(String cpf, String senha) {
        //  implementar o metodo doLogin da classe PersistenciaJDBC
        //  chamar o doLogin e verificar o retorno.
        // se o retorno for nulo, informar ao usuário
        //se nao for nulo, apresentar a tela de boas vindas e o menu.
        try{

            Funcionario f =  conexaoJDBC.doLogin(cpf, senha);
            
            if(f != null){

                JOptionPane.showMessageDialog(telaAutenticacao, "Funcionario "+f.getCpf()+" autenticado com sucesso!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);

                 frame.setJMenuBar(menuBar);//adiciona o menu de barra no frame
                 frame.showTela("tela_home");//muda a tela para o painel de boas vindas (home)

            }else{

                JOptionPane.showMessageDialog(telaAutenticacao, "Dados inválidos!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);
            }

        }catch(Exception e){

            JOptionPane.showMessageDialog(telaAutenticacao, "Erro ao executar a autenticação no Banco de Dados!", "Autenticação", JOptionPane.ERROR_MESSAGE);
        }
    }
}
