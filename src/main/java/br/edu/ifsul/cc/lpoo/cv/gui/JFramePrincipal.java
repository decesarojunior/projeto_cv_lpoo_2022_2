
package br.edu.ifsul.cc.lpoo.cv.gui;

import br.edu.ifsul.cc.lpoo.cv.Controle;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author telmo
 */
public class JFramePrincipal extends JFrame {
    
    private Controle controle;
    
    public JFramePrincipal(Controle controle){
        
        this.controle = controle;
        
        initComponents();            
    }
    
    private void initComponents(){
        //customização do JFrame
        //defini o titulo do Jframe
        this.setTitle("Sisteminha para CRUD - Clinica Veterinária"); //seta o título do jframe
        //defini o tamanho mínimo
        this.setMinimumSize(new Dimension(600,600)); //tamanho minimo quando for reduzido.
        //define a abertura em modo maximizado
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // por padrão abre maximizado.
        //defini o comportamento de fechar o processo no fechamento do Jframe
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );// finaliza o processo quando o frame é fechado.  
       
    }
}
