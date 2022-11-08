/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package br.edu.ifsul.cc.lpoo.cv;

import javax.swing.JOptionPane;

/**
 *
 * @author telmo
 */
public class ProjetoCVLPOO_2022_2 {
    
    private Controle controle;
    
    public ProjetoCVLPOO_2022_2(){
        
            try {
                controle = new Controle();//cria a instancia e atribui para o atributo controle.

                ////primeiramente - tenta estabelecer a conexao com o banco de dados.
                if(controle.conectarBD()){

                    //inicializa a interface gráfica.
                    controle.initComponents();
                }else{

                    JOptionPane.showMessageDialog(null, "Não conectou no Banco de Dados!", "Banco de Dados", JOptionPane.ERROR_MESSAGE);
                }

        } catch (Exception ex) {

                JOptionPane.showMessageDialog(null, "Erro ao tentar conectar no Banco de Dados: "+ex.getLocalizedMessage(), "Banco de Dados", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
        }
        
    }

    public static void main(String[] args) {
       new ProjetoCVLPOO_2022_2();
    }
}
