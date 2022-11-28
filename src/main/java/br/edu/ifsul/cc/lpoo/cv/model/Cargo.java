
package br.edu.ifsul.cc.lpoo.cv.model;

/**
 *
 * @author telmo
 */
public enum Cargo {
    
    ADESTRADOR, ATENDENTE, AUXILIAR_VETERINARIO;
    
    public static Cargo getCargo(String nome){
        
        if(nome.equals(ADESTRADOR.toString())){
            
            return ADESTRADOR;
            
        }else if(nome.equals(ATENDENTE.toString())){
            
            return ATENDENTE;
            
        }else if(nome.equals(AUXILIAR_VETERINARIO.toString())){
            
            return AUXILIAR_VETERINARIO;
        }
        
        return null;
    }
    
}
