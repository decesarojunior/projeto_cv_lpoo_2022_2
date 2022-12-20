
package br.edu.ifsul.cc.lpoo.cv.gui.produto;

import br.edu.ifsul.cc.lpoo.cv.Controle;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author telmo
 */
public class JPanelProdutoListagem  extends JPanel implements ActionListener {
 
    public Controle controle;
    public BorderLayout borderLayout;
    
    public JPanel pnlNorte;
    public JPanel pnlCentro;
    public JPanel pnlSul;
    
    public JLabel lblFiltro;
    public JTextField txfFiltro;
    public JButton btnFiltrar;
    
    public JScrollPane scpPane;
    public JTable tblListagem;
    public DefaultTableModel modeloTabela;
    
    public JButton btnRemover;
    
    public JPanelProdutoListagem(Controle controle){
        
        this.controle = controle;
        initComponents();
    }
    
    private void initComponents(){
        
        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        
        pnlNorte = new JPanel();
        pnlNorte.setLayout(new FlowLayout());
        
        lblFiltro = new JLabel("Filtrar por Nome:");
        txfFiltro = new JTextField(20);
        btnFiltrar = new JButton("Buscar");
        btnFiltrar.setActionCommand("filtrar_produto");
        btnFiltrar.addActionListener(this);
        
        pnlNorte.add(lblFiltro);
        pnlNorte.add(txfFiltro);
        pnlNorte.add(btnFiltrar);
        
        this.add(pnlNorte, BorderLayout.NORTH);
        
        scpPane = new JScrollPane();
        tblListagem = new JTable();
        modeloTabela = new DefaultTableModel(new String[]{"ID","Nome", "Fornecedor", "Tipo"},0);
        tblListagem.setModel(modeloTabela);
        
        scpPane.setViewportView(tblListagem);
        
        pnlCentro = new JPanel();
        pnlCentro.setLayout(new BorderLayout());
        pnlCentro.add(scpPane, BorderLayout.CENTER);//como terá apenas o JScrollPanel no pnlCentro, a tabela irá ocupar todo os espaço disponível
        
        this.add(pnlCentro, BorderLayout.CENTER);
        
        pnlSul = new JPanel();
        pnlSul.setLayout(new FlowLayout());
        btnRemover = new JButton("Remover");
        btnRemover.setActionCommand("remover_produto");
        btnRemover.addActionListener(this);
        pnlSul.add(btnRemover);
        
        this.add(pnlSul, BorderLayout.SOUTH);
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
