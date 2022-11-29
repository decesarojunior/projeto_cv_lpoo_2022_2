package br.edu.ifsul.cc.lpoo.cv.gui.funcionario;


import br.edu.ifsul.cc.lpoo.cv.Controle;
import br.edu.ifsul.cc.lpoo.cv.model.Cargo;
import br.edu.ifsul.cc.lpoo.cv.model.Funcionario;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author telmo
 */
public class JPanelAFuncionarioFormulario extends JPanel implements ActionListener{
    
    
    private JPanelAFuncionario pnlAFuncionario;
    private Controle controle;
    
    private BorderLayout borderLayout;
    private JTabbedPane tbpAbas;
    
    private JPanel pnlDadosCadastrais;    
    private JPanel pnlCentroDadosCadastrais;
    
    private GridBagLayout gridBagLayoutDadosCadastrais;
    private JLabel lblCPF;
    private JTextField txfCPF;
    
    private JLabel lblNome;
    private JTextField txfNome;

    private JLabel lblSenha;
    private JPasswordField txfSenha;
    
    private JLabel lblCargo;
    private JComboBox cbxCargo;
            
    private JLabel lblDataCadastro;
    private JTextField txfDataCadastro;
    
    private JLabel lblRG;
    private JTextField txfRG;
    
    private JLabel lblCTPS;
    private JTextField txfCTPS;
    
    private JLabel lblPIS;
    private JTextField txfPIS;

    
    
    private Funcionario funcionario;
    private SimpleDateFormat format;
    
    private JPanel pnlSul;
    private JButton btnGravar;
    private JButton btnCancelar;
    
    
    
    public JPanelAFuncionarioFormulario(JPanelAFuncionario pnlAFuncionario, Controle controle){
        
        this.pnlAFuncionario = pnlAFuncionario;
        this.controle = controle;
        
        initComponents();
        
    }    
    public void populaComboCargo(){        
        cbxCargo.removeAllItems();//zera o combo
        DefaultComboBoxModel model =  (DefaultComboBoxModel) cbxCargo.getModel();
        model.addElement("Selecione"); //primeiro item                
        model.addElement(Cargo.ATENDENTE);
        model.addElement(Cargo.ADESTRADOR);
        model.addElement(Cargo.AUXILIAR_VETERINARIO);                
    }
    
    
    public Funcionario getFuncionariobyFormulario(){        
        //validacao do formulario
       if(txfCPF.getText().trim().length() == 11 && 
            txfNome.getText().trim().length() > 0 &&
            txfPIS.getText().trim().length() > 0 &&
            txfCTPS.getText().trim().length() > 0 &&
            new String(txfSenha.getPassword()).trim().length() > 3 && 
            cbxCargo.getSelectedIndex() > 0 && 
            txfRG.getText().trim().length() > 0){

            Funcionario f = new Funcionario();
                f.setCpf(txfCPF.getText().trim());    
                f.setRg(txfRG.getText().trim());
                f.setSenha(new String(txfSenha.getPassword()).trim());
                f.setCargo((Cargo) cbxCargo.getSelectedItem());
                f.setNumero_pis(txfPIS.getText().trim());
                f.setNumero_ctps(txfCTPS.getText().trim());
                f.setNome(txfNome.getText().trim());
                
            if(funcionario != null)
                f.setData_cadastro(funcionario.getData_cadastro());
                        
            return f;
         }

         return null;
    }
    
    public void setFuncionarioFormulario(Funcionario f){
        if(f == null){//se o parametro estiver nullo, limpa o formulario
            txfCPF.setText("");
            txfCPF.setEditable(true);
            txfRG.setText("");
            txfSenha.setText("");
            cbxCargo.setSelectedIndex(0);
            txfNome.setText("");
            txfDataCadastro.setText("");
            txfPIS.setText("");
            txfCTPS.setText("");            
            funcionario = null;
        }else{
            funcionario = f;
            txfCPF.setEditable(false);
            txfCPF.setText(funcionario.getCpf());
            txfSenha.setText(funcionario.getSenha());
            cbxCargo.getModel().setSelectedItem(funcionario.getCargo());//aqui chama o método equals do classe Endereco
            txfRG.setText(funcionario.getRg());
            txfNome.setText(funcionario.getNome()); 
            txfPIS.setText(funcionario.getNumero_pis());
            txfCTPS.setText(funcionario.getNumero_ctps());
            txfDataCadastro.setText(format.format(f.getData_cadastro().getTime()));                                 
        }

    }
    
    private void initComponents(){
        
        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        
        tbpAbas = new JTabbedPane();
        this.add(tbpAbas, BorderLayout.CENTER);
        
        pnlDadosCadastrais = new JPanel();
        gridBagLayoutDadosCadastrais = new GridBagLayout();
        pnlDadosCadastrais.setLayout(gridBagLayoutDadosCadastrais);
        
        lblCPF = new JLabel("CPF:");
        GridBagConstraints posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblCPF, posicionador);//o add adiciona o rotulo no painel  
        
        txfCPF = new JTextField(20);        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfCPF, posicionador);//o add adiciona o rotulo no painel  
          
        
        lblRG = new JLabel("RG:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblRG, posicionador);//o add adiciona o rotulo no painel  
        
        txfRG = new JTextField(20);        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfRG, posicionador);//o add adiciona o rotulo no painel  
      
        lblNome = new JLabel("Nome:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblNome, posicionador);//o add adiciona o rotulo no painel  
        
        txfNome = new JTextField(30);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfNome, posicionador);//o add adiciona o rotulo no painel  
             
        lblSenha = new JLabel("Senha:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblSenha, posicionador);//o add adiciona o rotulo no painel  
        
        txfSenha = new JPasswordField(10);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfSenha, posicionador);//o add adiciona o rotulo no painel  
                
        lblPIS = new JLabel("PIS:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblPIS, posicionador);//o add adiciona o rotulo no painel  
                
        txfPIS = new JTextField(5);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfPIS, posicionador);//o add adiciona o rotulo no painel  
            
        lblCTPS = new JLabel("CTPS:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 5;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblCTPS, posicionador);//o add adiciona o rotulo no painel  
                
        txfCTPS = new JTextField(5);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 5;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfCTPS, posicionador);//o add adiciona o rotulo no painel  
     
        
        lblCargo = new JLabel("Cargo:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 6;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(lblCargo, posicionador);//o add adiciona o rotulo no painel  
                
        cbxCargo = new JComboBox();
        posicionador = new GridBagConstraints();
        posicionador.gridy = 6;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(cbxCargo, posicionador);//o add adiciona o rotulo no painel 
                
                            
        lblDataCadastro = new JLabel("Data de Cadastro:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 7;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblDataCadastro, posicionador);//o add adiciona o rotulo no painel         
        
        txfDataCadastro = new JTextField(20);
        txfDataCadastro.setEditable(false);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 7;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(txfDataCadastro, posicionador);//o add adiciona o rotulo no painel         
        
        tbpAbas.addTab("Dados Cadastrais", pnlDadosCadastrais);
        
        pnlSul = new JPanel();
        pnlSul.setLayout(new FlowLayout());
        
        btnGravar = new JButton("Gravar");
        btnGravar.addActionListener(this);
        btnGravar.setFocusable(true);    //acessibilidade    
        btnGravar.setToolTipText("btnGravarJogador"); //acessibilidade
        btnGravar.setMnemonic(KeyEvent.VK_G);
        btnGravar.setActionCommand("botao_gravar_formulario_jogador");
        
        pnlSul.add(btnGravar);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        btnCancelar.setFocusable(true);    //acessibilidade    
        btnCancelar.setToolTipText("btnCancelarJogador"); //acessibilidade
        btnCancelar.setActionCommand("botao_cancelar_formulario_jogador");
        
        pnlSul.add(btnCancelar);
        
        this.add(pnlSul, BorderLayout.SOUTH);
        
        format = new SimpleDateFormat("dd/MM/yyyy");
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        
        
        if(arg0.getActionCommand().equals(btnGravar.getActionCommand())){
            
            Funcionario f = getFuncionariobyFormulario();//recupera os dados do formulario
            
            if(f != null){

                try {
                    
                    pnlAFuncionario.getControle().getConexaoJDBC().persist(f);
                    
                    JOptionPane.showMessageDialog(this, "Funcionario armazenado!", "Salvar", JOptionPane.INFORMATION_MESSAGE);
                    
                    pnlAFuncionario.showTela("tela_funcionario_listagem");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar Jogador! : "+ex.getMessage(), "Salvar", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            }else{

                JOptionPane.showMessageDialog(this, "Preencha o formulário!", "Edição", JOptionPane.INFORMATION_MESSAGE);
            }
            
            
        }else if(arg0.getActionCommand().equals(btnCancelar.getActionCommand())){
            
            
                pnlAFuncionario.showTela("tela_funcionario_listagem");
            
        }
    }
}
