
package br.edu.ifsul.cc.lpoo.cv;

import br.edu.ifsul.cc.lpoo.cv.gui.JFramePrincipal;
import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJDBC;

/**
 *
 * @author telmo
 */
public class Controle {
    
    private PersistenciaJDBC conexaoJDBC;
    private JFramePrincipal frame; // frame principal da minha aplicação gráfica

    
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
         
        
        frame.setVisible(true); // torna visível o jframe
    }
}
