package telas;

import dao.DaoMysql;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.KeyStroke;
import javax.swing.text.MaskFormatter;


/**
 *
 * @author rli
 */
public class Teclado extends javax.swing.JFrame implements KeyListener{
    
    

    /**
     * Creates new form Teclado
     */
    
    private int controleCpf=0;
    private DaoMysql dao;
    private String retornoRegistro = null;
    private String matricula = "";
    private EntrouDialog telaEntrou;
    private NaoEntrouDialog telaNaoEntrou;
    private Calendario calendario;
    private Arquivo arquivoConf;
    private String numeroFormat="";
    
    public Teclado(){
        initComponents();
        setLocationRelativeTo( null );
        this.setExtendedState(MAXIMIZED_BOTH);
        int pontoLargura = ((this.getWidth() / 2) - painelPrincipal.getWidth() / 2) - 2;  
        int pontoAltura = ((this.getHeight() / 2) - painelPrincipal.getHeight() / 2) - 16;   
        painelPrincipal.setLocation(pontoLargura, pontoAltura);
        addKeyListener(this);
        calendario = new Calendario();
        arquivoConf = new Arquivo(calendario);
        dao = new DaoMysql(arquivoConf.getConfig());
        //dao = new DaoMysql("localhost");
        telaEntrou = new EntrouDialog(this, true);
        telaNaoEntrou = new NaoEntrouDialog(this, true);
        srvAddr.setText(arquivoConf.getConfig());
        
        try {
            MaskFormatter cpfmask = new MaskFormatter("###.###.###-##");   
            cpfmask.install(jFormattedTextFieldCPF);
        } catch (ParseException ex) {
            System.out.println("Erro na mascara");
            Logger.getLogger(Teclado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        jFormattedTextFieldCPF.setVisible(false);
        labelCpf.setText("Informe seu CPF");
        //jTextFieldCPF.setColumns(10);
    }
    
    /*public void centralizar() {  
        int pontoLargura = ((this.getWidth() / 2) - painelPrincipal.getWidth() / 2) - 2;  
        int pontoAltura = ((this.getHeight() / 2) - painelPrincipal.getHeight() / 2) - 16;   
        painelPrincipal.setLocation(pontoLargura, pontoAltura);  
    }*/
    public void mostrarHora() {  
        AtualizadorHorario ah = new AtualizadorHorario(labelHora);  
        ah.mostrarData(true);  
        Thread thHora = ah;  
        thHora.start();  
    } 
    
    public void limpaCpfField(){
        controleCpf = 0;
        labelCpf.setText("Informe seu CPF");
        jFormattedTextFieldCPF.setText(null);
        //jTextFieldCPF.setText("");
        matricula="";
        numeroFormat="";
    }
    
    
    public void setLabelCpf(String numero){
        int tamanho = 0;
        if(controleCpf==0){
            labelCpf.setText("");
            //jTextFieldCPF.setText("");
        }
         
        
        numeroFormat = numeroFormat+numero;
        jFormattedTextFieldCPF.setText(numeroFormat);
        
        String temp = labelCpf.getText();
        String tempMat = matricula;
        tamanho = tempMat.length();
        if (tamanho<=2){
            //labelCpf.setText(temp+numero);
            matricula = tempMat+numero;
            labelCpf.setText(temp+"*");
            controleCpf=1;
        }else if (tamanho==3){
            //labelCpf.setText(temp+"."+numero);
            labelCpf.setText(temp+"."+"*");
            //jTextFieldCPF.setText(temp+"."+"*");
            matricula = tempMat+"."+numero;
            controleCpf=1;
        }else if ((tamanho>3)&&(tamanho<=6)){
            //labelCpf.setText(temp+numero);
            labelCpf.setText(temp+"*");
            //jTextFieldCPF.setText(temp+"*");
            matricula = tempMat+numero;
            controleCpf=1;
        }else if (tamanho==7){
            //labelCpf.setText(temp+"."+numero);
            labelCpf.setText(temp+"."+"*");
            //jTextFieldCPF.setText(temp+"."+"*");
            matricula = tempMat+"."+numero;
            controleCpf=1;
        }else if ((tamanho>7)&&(tamanho<=10)){
            //labelCpf.setText(temp+numero);
            labelCpf.setText(temp+"*");
            //jTextFieldCPF.setText(temp+"*");
            matricula = tempMat+numero;
            controleCpf=1;
        }else if (tamanho==11){
            //labelCpf.setText(temp+"-"+numero);
            labelCpf.setText(temp+"-"+"*");
            //jTextFieldCPF.setText(temp+"-"+"*");
            matricula = tempMat+"-"+numero;
            controleCpf=1;
        }else if ((tamanho>11)&&(tamanho<=13)){
            //labelCpf.setText(temp+numero);
            labelCpf.setText(temp+"*");
            //jTextFieldCPF.setText(temp+"*");
            matricula = tempMat+numero;
            controleCpf=1;
        }
        
    }
    
    public void controlaBotoes(String controle){
        if(controle.equals("desabilitar")){
            bt1.setEnabled(false);
            bt2.setEnabled(false);
            bt3.setEnabled(false);
            bt4.setEnabled(false);
            bt5.setEnabled(false);
            bt6.setEnabled(false);
            bt7.setEnabled(false);
            bt8.setEnabled(false);
            bt9.setEnabled(false);
            bt0.setEnabled(false);
            btEntrar.setEnabled(false);
            btLimpar.setEnabled(false);
        }
        else if(controle.equals("habilitar")){
            //labelCpf.setText("Informe seu CPF");
           // jTextFieldCPF.setText("Informe seu CPF");
            bt1.setEnabled(true);
            bt2.setEnabled(true);
            bt3.setEnabled(true);
            bt4.setEnabled(true);
            bt5.setEnabled(true);
            bt6.setEnabled(true);
            bt7.setEnabled(true);
            bt8.setEnabled(true);
            bt9.setEnabled(true);
            bt0.setEnabled(true);
            btEntrar.setEnabled(true);
            btLimpar.setEnabled(true);
        }
    }
    
    public void pausa(){
        try {
            Thread.sleep(5000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void entrou(){
        telaEntrou.setLocationRelativeTo(this);
        telaEntrou.setVisible(true);
    }
    public void naoEntrou(){
        telaNaoEntrou.setLocationRelativeTo(this);
        telaNaoEntrou.setVisible(true);
        //telaNaoEntrou.pausa();
    }
    
    public void pressEnter(java.awt.event.KeyEvent evt){
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            controlaBotoes("desabilitar");
            //matricula = jFormattedTextFieldCPF.getText();
            System.out.println("Srv: "+dao.getHostName());
            retornoRegistro = dao.registraEntrada(jFormattedTextFieldCPF.getText());
            if(retornoRegistro.equals("0")){
                naoEntrou();
            }else{
                entrou();
            }
            controlaBotoes("habilitar");
            limpaCpfField();
        }else if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            limpaCpfField();
        }else{
            char input = evt.getKeyChar();
            if(input=='1'){
                setLabelCpf("1");
            }else if(input=='2'){
                setLabelCpf("2");
            }else if(input=='3'){
                setLabelCpf("3");
            }else if(input=='4'){
                setLabelCpf("4");
            }else if(input=='5'){
                setLabelCpf("5");
            }else if(input=='6'){
                setLabelCpf("6");
            }else if(input=='7'){
                setLabelCpf("7");
            }else if(input=='8'){
                setLabelCpf("8");
            }else if(input=='9'){
                setLabelCpf("9");
            }else if(input=='0'){
                setLabelCpf("0");
            }
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelPrincipal = new javax.swing.JPanel();
        jPanelTeclado = new javax.swing.JPanel();
        bt4 = new javax.swing.JButton();
        btEntrar = new javax.swing.JButton();
        bt1 = new javax.swing.JButton();
        btLimpar = new javax.swing.JButton();
        bt7 = new javax.swing.JButton();
        bt9 = new javax.swing.JButton();
        bt6 = new javax.swing.JButton();
        bt8 = new javax.swing.JButton();
        bt5 = new javax.swing.JButton();
        bt2 = new javax.swing.JButton();
        bt3 = new javax.swing.JButton();
        bt0 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        labelHora = new javax.swing.JLabel();
        labelCpf = new javax.swing.JLabel();
        jFormattedTextFieldCPF = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        srvAddr = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setPreferredSize(new java.awt.Dimension(1280, 800));
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        painelPrincipal.setLayout(null);

        jPanelTeclado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelTeclado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanelTecladoKeyPressed(evt);
            }
        });

        bt4.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        bt4.setText("4");
        bt4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt4MouseClicked(evt);
            }
        });
        bt4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bt4KeyPressed(evt);
            }
        });

        btEntrar.setBackground(new java.awt.Color(0, 153, 102));
        btEntrar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btEntrar.setForeground(new java.awt.Color(255, 255, 255));
        btEntrar.setText("ENTRAR");
        btEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEntrarActionPerformed(evt);
            }
        });

        bt1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        bt1.setText("1");
        bt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt1MouseClicked(evt);
            }
        });
        bt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bt1KeyPressed(evt);
            }
        });

        btLimpar.setBackground(new java.awt.Color(204, 153, 0));
        btLimpar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btLimpar.setForeground(new java.awt.Color(255, 255, 255));
        btLimpar.setText("LIMPAR");
        btLimpar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btLimparMouseClicked(evt);
            }
        });
        btLimpar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btLimparKeyPressed(evt);
            }
        });

        bt7.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        bt7.setText("7");
        bt7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt7MouseClicked(evt);
            }
        });
        bt7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bt7KeyPressed(evt);
            }
        });

        bt9.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        bt9.setText("9");
        bt9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt9MouseClicked(evt);
            }
        });
        bt9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bt9KeyPressed(evt);
            }
        });

        bt6.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        bt6.setText("6");
        bt6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt6MouseClicked(evt);
            }
        });
        bt6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bt6KeyPressed(evt);
            }
        });

        bt8.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        bt8.setText("8");
        bt8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt8MouseClicked(evt);
            }
        });
        bt8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bt8KeyPressed(evt);
            }
        });

        bt5.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        bt5.setText("5");
        bt5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt5MouseClicked(evt);
            }
        });
        bt5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt5ActionPerformed(evt);
            }
        });
        bt5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bt5KeyPressed(evt);
            }
        });

        bt2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        bt2.setText("2");
        bt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt2MouseClicked(evt);
            }
        });
        bt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bt2KeyPressed(evt);
            }
        });

        bt3.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        bt3.setText("3");
        bt3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt3MouseClicked(evt);
            }
        });
        bt3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bt3KeyPressed(evt);
            }
        });

        bt0.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        bt0.setText("0");
        bt0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt0MouseClicked(evt);
            }
        });
        bt0.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bt0KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanelTecladoLayout = new javax.swing.GroupLayout(jPanelTeclado);
        jPanelTeclado.setLayout(jPanelTecladoLayout);
        jPanelTecladoLayout.setHorizontalGroup(
            jPanelTecladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTecladoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelTecladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTecladoLayout.createSequentialGroup()
                        .addComponent(bt1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(bt2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(bt3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelTecladoLayout.createSequentialGroup()
                        .addComponent(bt4, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(bt5, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(bt6, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelTecladoLayout.createSequentialGroup()
                        .addComponent(bt7, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(bt8, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(bt9, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelTecladoLayout.createSequentialGroup()
                        .addComponent(btLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(bt0, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(btEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanelTecladoLayout.setVerticalGroup(
            jPanelTecladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTecladoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelTecladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanelTecladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanelTecladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt9, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanelTecladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt0, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        painelPrincipal.add(jPanelTeclado);
        jPanelTeclado.setBounds(670, 40, 493, 490);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelHora.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelHora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHora.setText("Relógio");

        labelCpf.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        labelCpf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCpf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelHora, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                    .addComponent(labelCpf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelHora)
                .addContainerGap())
        );

        painelPrincipal.add(jPanel2);
        jPanel2.setBounds(70, 110, 540, 140);

        jFormattedTextFieldCPF.setBackground(new java.awt.Color(240, 240, 240));
        jFormattedTextFieldCPF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextFieldCPF.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jFormattedTextFieldCPF.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jFormattedTextFieldCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldCPFKeyPressed(evt);
            }
        });
        painelPrincipal.add(jFormattedTextFieldCPF);
        jFormattedTextFieldCPF.setBounds(80, 300, 512, 70);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icomp-new-mini.png"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ufam-mini.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ACADEMIA UFAM - ENTRADA");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("© ICOMP - Instituto de Computação  Desenvolvido no contexto da disciplina ICC410 - 2014/02");

        srvAddr.setText("endereço");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(srvAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 236, Short.MAX_VALUE)
                        .addComponent(jLabel4)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(srvAddr)
                        .addContainerGap())
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bt1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt1MouseClicked
        setLabelCpf("1");
    }//GEN-LAST:event_bt1MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        mostrarHora();

    }//GEN-LAST:event_formWindowOpened

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        
    }//GEN-LAST:event_formWindowActivated

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        jFormattedTextFieldCPF.requestFocus();
    }//GEN-LAST:event_formWindowGainedFocus

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
    }//GEN-LAST:event_formFocusGained

    private void btLimparMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLimparMouseClicked
        limpaCpfField();
    }//GEN-LAST:event_btLimparMouseClicked

    private void bt2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt2MouseClicked
        setLabelCpf("2");
    }//GEN-LAST:event_bt2MouseClicked

    private void bt3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt3MouseClicked
        setLabelCpf("3");
    }//GEN-LAST:event_bt3MouseClicked

    private void bt4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt4MouseClicked
        setLabelCpf("4");
    }//GEN-LAST:event_bt4MouseClicked

    private void bt5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt5MouseClicked
        setLabelCpf("5");
    }//GEN-LAST:event_bt5MouseClicked

    private void bt6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt6MouseClicked
        setLabelCpf("6");
    }//GEN-LAST:event_bt6MouseClicked

    private void bt7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt7MouseClicked
        setLabelCpf("7");
    }//GEN-LAST:event_bt7MouseClicked

    private void bt8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt8MouseClicked
        setLabelCpf("8");
    }//GEN-LAST:event_bt8MouseClicked

    private void bt9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt9MouseClicked
        setLabelCpf("9");
    }//GEN-LAST:event_bt9MouseClicked

    private void bt0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt0MouseClicked
        setLabelCpf("0");
    }//GEN-LAST:event_bt0MouseClicked

    private void bt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bt1KeyPressed
        pressEnter(evt);
    }//GEN-LAST:event_bt1KeyPressed

    private void bt2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bt2KeyPressed
        pressEnter(evt);
    }//GEN-LAST:event_bt2KeyPressed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped

        
    }//GEN-LAST:event_formKeyTyped

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        

    }//GEN-LAST:event_formKeyPressed

    private void btEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEntrarActionPerformed
        //String matricula = labelCpf.getText();
        controlaBotoes("desabilitar");
        System.out.println("Srv: "+dao.getHostName());
        retornoRegistro = dao.registraEntrada(jFormattedTextFieldCPF.getText());
        if(retornoRegistro.equals("0")){
            //labelCpf.setText("Entrada não autorizada");
            //System.out.println("Entrada não autorizada");
            naoEntrou();
        }else{
            //labelCpf.setText("Entrada liberada");
            //System.out.println("Entrada liberada");
            entrou();
        }
        controlaBotoes("habilitar");
        limpaCpfField();
        
    }//GEN-LAST:event_btEntrarActionPerformed

    private void bt5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt5ActionPerformed

    private void jFormattedTextFieldCPFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCPFKeyPressed
        pressEnter(evt);
    }//GEN-LAST:event_jFormattedTextFieldCPFKeyPressed

    private void jPanelTecladoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanelTecladoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            controlaBotoes("desabilitar");
            //matricula = jFormattedTextFieldCPF.getText();
            System.out.println("Srv: "+dao.getHostName());
            retornoRegistro = dao.registraEntrada(jFormattedTextFieldCPF.getText());
            if(retornoRegistro.equals("0")){
                naoEntrou();
            }else{
                entrou();
            }
            controlaBotoes("habilitar");
            limpaCpfField();
        }
    }//GEN-LAST:event_jPanelTecladoKeyPressed

    private void bt3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bt3KeyPressed
       pressEnter(evt);
    }//GEN-LAST:event_bt3KeyPressed

    private void bt4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bt4KeyPressed
        pressEnter(evt);
    }//GEN-LAST:event_bt4KeyPressed

    private void bt5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bt5KeyPressed
        pressEnter(evt);
    }//GEN-LAST:event_bt5KeyPressed

    private void bt6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bt6KeyPressed
        pressEnter(evt);
    }//GEN-LAST:event_bt6KeyPressed

    private void bt7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bt7KeyPressed
        pressEnter(evt);
    }//GEN-LAST:event_bt7KeyPressed

    private void bt8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bt8KeyPressed
        pressEnter(evt);
    }//GEN-LAST:event_bt8KeyPressed

    private void bt9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bt9KeyPressed
        pressEnter(evt);
    }//GEN-LAST:event_bt9KeyPressed

    private void bt0KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bt0KeyPressed
        pressEnter(evt);
    }//GEN-LAST:event_bt0KeyPressed

    private void btLimparKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btLimparKeyPressed
        pressEnter(evt);
    }//GEN-LAST:event_btLimparKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Teclado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Teclado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Teclado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Teclado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Teclado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt0;
    private javax.swing.JButton bt1;
    private javax.swing.JButton bt2;
    private javax.swing.JButton bt3;
    private javax.swing.JButton bt4;
    private javax.swing.JButton bt5;
    private javax.swing.JButton bt6;
    private javax.swing.JButton bt7;
    private javax.swing.JButton bt8;
    private javax.swing.JButton bt9;
    private javax.swing.JButton btEntrar;
    private javax.swing.JButton btLimpar;
    private javax.swing.JFormattedTextField jFormattedTextFieldCPF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelTeclado;
    private javax.swing.JLabel labelCpf;
    private javax.swing.JLabel labelHora;
    private javax.swing.JPanel painelPrincipal;
    private javax.swing.JLabel srvAddr;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
