/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DBConnect.Connector;
import DBTables.CurrentSession;
import DBTables.Tables;
import DBTables.UserType;
import DBViews.usersnames_Rv;
import Utils.Notification;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import sun.java2d.SunGraphicsEnvironment;

 
public class SplitPaneEXP extends JFrame   {
     
    
   JFrame frame = new JFrame();
   JPanel board = new JPanel();
   JPanel head;
   JPanel menu;
   JSplitPane splitPaneMain ,splitPaneForPanels ;
   JPanel SplitRightSide, SplitLeftSide , splitPaneForPanelsTop,splitPaneForPanelsButtom;
   SlideMenu LeftMenu;
   Head  panexthead;
   OrderTRX panorderTRX;
   HapyCafe happycafe = new HapyCafe();
   Notification not;
   usersnames_Rv emp;
   String empname;
   String Type;
   JButton bt = new JButton("Refresh");
   State currentState;
   JPanel jPanel1,jPanel4,homepanel,orderPanel,safePAnel,warehuse,reportsPanel,settingPanel;
    JLabel jLabel1,jLabel2,jLabel3,jLabel5,jLabel6,jLabel7,jLabel8,jLabel9,jLabel10,jLabel11,jLabel12,jLabel13,jLabel4;
JPanel panOrderTRXhead, panTRXButtons , panTRX;
    final String UserType ;  
    JButton [] TableButtons;
     JButton btn;
     Tables tb;
     OpenOrder od;
// HomeBoard HomeBoard;
//   private panelclass panel;
    private int i;
  
     
    public SplitPaneEXP() throws Exception {
         
        setTitle("MR POS V 1.0");
        JLabel headlb = new JLabel("Head");
        JLabel j1 = new JLabel("Area 1");
        JLabel j2 = new JLabel("Area 2");
        JLabel oplb = new JLabel("Left Panel");
        tb = new Tables();
        
        bt.addActionListener( new RefreshListener());
//         ImageIcon img = new ImageIcon(getClass().getResource("/gui/Res/referesh.png"));
       //  bt.setIcon(img);
         panexthead = new Head();
menu = new JPanel();

         head = new JPanel();
        head.setLayout(new BorderLayout());
        head.add(happycafe,BorderLayout.CENTER);
        head.add(bt,BorderLayout.LINE_START);
        head.setBackground(new Color(204,204,255));

         SplitRightSide = new JPanel();
         SplitRightSide.setLayout(new BorderLayout());
         
           SplitLeftSide = new JPanel();
          SplitLeftSide.setLayout(new BorderLayout());
           splitPaneMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true, SplitLeftSide,SplitRightSide );
           splitPaneMain.setOneTouchExpandable(true);
           
           splitPaneForPanelsTop = new JPanel();
           splitPaneForPanelsTop.setLayout(new BorderLayout());
           panorderTRX = new OrderTRX(State.Warehouse_STATE);
           ////////////////////////////////////////////////////////////////////
           paintSplitPaneForPanelsTop();
           //splitPaneForPanelsTop.add(panorderTRX,BorderLayout.CENTER);
           //System.out.println(SplashLeftSide.getHeight()+" /4 "+SplashLeftSide.getHeight()/4);
           splitPaneForPanelsTop.setPreferredSize(new Dimension(this.getWidth(),200));
           
           splitPaneForPanelsButtom = new JPanel();
           splitPaneForPanelsButtom.setLayout(new BorderLayout());
           
           splitPaneForPanels =new JSplitPane(JSplitPane.VERTICAL_SPLIT,true, splitPaneForPanelsTop, splitPaneForPanelsButtom);
            splitPaneForPanels.setOneTouchExpandable(true);
           
            SplitLeftSide.add(splitPaneForPanels,BorderLayout.CENTER);
        
            board.setLayout(new BorderLayout());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  
        this.setLayout(new BorderLayout()); 
         head.setPreferredSize(new Dimension(this.getWidth(), 60));
       SplitLeftSide.setPreferredSize(new Dimension(1050, this.getHeight()));
        
        this.setPreferredSize(new Dimension(screenSize.width,screenSize.height-50));
        
        //define outside panels;
       // LeftMenu =new SlideMenu();
        
       
     //   HomeBoard = new HomeBoard();
        ////////////////////////////////////////////////////////////////
          UserType = new CurrentSession().getCurrentUserType();
          paintMenu();
          checkPermission(UserType);
        
        SplitRightSide.add(menu,BorderLayout.CENTER);
        
       
        head.add(panexthead,BorderLayout.LINE_END);
        //SplashRightSide.add(HomeBoard,BorderLayout.CENTER);
        this.add(head,BorderLayout.NORTH);
        this.add(splitPaneMain,BorderLayout.CENTER);
        //leftpanel.setBorder(BorderFactory.createLineBorder(Color.yellow));
        //leftpanel.setPreferredSize(new Dimension(100, this.getHeight()));
        //adding items to splash 
        //SplashRightSide.add();
        //splitPane.setPreferredSize(new Dimension(this.getWidth()-leftpanel.getWidth(),this.getHeight()));
         //openo.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));    
       // 
      //  frame.add(board,BorderLayout.CENTER);
        //SplashLeftSide.removeAll();
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //  setImageIcon();
       pack();
       emp =new usersnames_Rv();
        System.out.println(""+emp.getCurrentUserName());
      empname = emp.getCurrentUserName();
       not=new Notification("Happiness Cafe", "Welcome "+empname);
      /* try {
            URL resource = getClass().getResource("/gui/Res/app.png");
            System.out.println(""+resource);
            BufferedImage image = ImageIO.read(resource);
            setIconImage(image);
        } catch (IOException e) {
            e.printStackTrace();
            
        }*/
       
    }
    
    public SplitPaneEXP(int i)  {
   
   panitSplitButtom(i);
   this.UserType ="";
    }
    
    public void setImageIcon (){
        JFrame frame = new JFrame();

    Image icon = Toolkit.getDefaultToolkit().getImage("intro.ico");
    frame.setIconImage(icon);

       // setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("intro.ico")));
    }
 
    public void repaintsplitPaneForPanelsTop() throws Exception{
        splitPaneForPanelsTop.removeAll();
         splitPaneForPanelsTop = new JPanel();
           splitPaneForPanelsTop.setLayout(new BorderLayout());
           panorderTRX = new OrderTRX(State.Home_STATE);
           splitPaneForPanelsTop.add(panorderTRX,BorderLayout.CENTER);
    }
   
    
    
 public void repaintHead(State state){
     currentState =state;
     
     

        try {
            panorderTRX = new OrderTRX(state);
            panexthead = new Head();
        } catch (Exception ex) {
            Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
        }
        head.removeAll();
        head.add(happycafe,BorderLayout.CENTER);
        head.add(bt,BorderLayout.LINE_START);
         head.add(panexthead,BorderLayout.LINE_END);
        head.setBackground(new Color(163,126,180));
        //head.add(panexthead);
        head.invalidate();
        head.validate();
        head.repaint();
        splitPaneForPanelsTop.removeAll();
       splitPaneForPanelsTop.add(panorderTRX);
       
      splitPaneForPanels.revalidate();
      splitPaneForPanels.repaint();
     
 }
 class RefreshListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
             if (ae.getActionCommand().equalsIgnoreCase("Refresh")){
         try {
           refreshTablebuttons();
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }
        }
     
 }
 
public void paintMenu() throws Exception{
  /*  menu.setLayout(new GridLayout(11, 1));
    JPanel p1 =new JPanel(new BorderLayout());
    JLabel lb1= new JLabel("Happiness Cafe");
    p1.setBackground(new Color(153,50,204));
    lb1.setFont(new SlideMenu().jLabel3.getFont());
    //lb1.setHorizontalTextPosition(5);
    p1.add(lb1,BorderLayout.CENTER);
    JPanel p2 =new JPanel();
    p2.setBackground(new Color(153,50,204));
    JPanel p3 =new JPanel();
    p3.setBackground(new Color(85,55,118));
    JPanel p4 =new JPanel();
    p4.setBackground(new Color(147,112,219));
    JPanel p5 =new JPanel();
    p5.setBackground(new Color(147,112,219));
    menu.add(p1);
    menu.add(p2);
    menu.add(p3);
    menu.add(p4);
    menu.add(p5);
    */
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        homepanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        orderPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        safePAnel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        warehuse = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        reportsPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        settingPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        menu.setBackground(new java.awt.Color(204,204,255));
        menu.setLayout(new java.awt.GridLayout(11, 1));

        jPanel1.setBackground(new java.awt.Color(204,204,255));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0,51,204));
        jLabel1.setText("Happiness Cafe  v 1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel1)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        menu.add(jPanel1);

        jPanel4.setBackground(new java.awt.Color(204,204,255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 287, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        menu.add(jPanel4);

        homepanel.setBackground(new java.awt.Color(85, 55, 118));
        homepanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homepanelMouseClicked(evt);
            }
        });
        homepanel.setLayout(new java.awt.BorderLayout());

       // jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/images/home-automation-40.png"))); // NOI18N
        homepanel.add(jLabel2, java.awt.BorderLayout.LINE_END);

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("الرئيسية");
        homepanel.add(jLabel3, java.awt.BorderLayout.CENTER);

        menu.add(homepanel);

        orderPanel.setBackground(new java.awt.Color(147, 112, 219));
        orderPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderPanelMouseClicked(evt);
            }
        });
        orderPanel.setLayout(new java.awt.BorderLayout());

        //jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/images/advertisement-page-50.png"))); // NOI18N
        orderPanel.add(jLabel4, java.awt.BorderLayout.LINE_END);

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("الطلبات");
        orderPanel.add(jLabel5, java.awt.BorderLayout.CENTER);

        menu.add(orderPanel);

        safePAnel.setBackground(new java.awt.Color(147, 112, 219));
        safePAnel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                safePAnelMouseClicked(evt);
            }
        });
        safePAnel.setLayout(new java.awt.BorderLayout());

     //   jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/images/safe.png"))); // NOI18N
        safePAnel.add(jLabel6, java.awt.BorderLayout.LINE_END);

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("الخزنه");
        safePAnel.add(jLabel7, java.awt.BorderLayout.CENTER);

        menu.add(safePAnel);

        warehuse.setBackground(new java.awt.Color(147, 112, 219));
        warehuse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    warehuseMouseClicked(evt);
                } catch (Exception ex) {
                    Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        warehuse.setLayout(new java.awt.BorderLayout());

        jLabel12.setBackground(new java.awt.Color(204, 204, 204));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("المخزن");
        
        warehuse.add(jLabel12, java.awt.BorderLayout.CENTER);

       // jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/images/warehouse.png"))); // NOI18N
        warehuse.add(jLabel13, java.awt.BorderLayout.LINE_END);

        menu.add(warehuse);

        reportsPanel.setBackground(new java.awt.Color(147, 112, 219));
        reportsPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportsPanelMouseClicked(evt);
            }
        });
        reportsPanel.setLayout(new java.awt.BorderLayout());

        //jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/images/reports.png"))); // NOI18N
        reportsPanel.add(jLabel8, java.awt.BorderLayout.LINE_END);

        jLabel9.setBackground(new java.awt.Color(204, 204, 204));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("التقارير");
        reportsPanel.add(jLabel9, java.awt.BorderLayout.CENTER);

        menu.add(reportsPanel);

        settingPanel.setBackground(new java.awt.Color(147, 112, 219));
        settingPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingPanelMouseClicked(evt);
            }
        });
        settingPanel.setLayout(new java.awt.BorderLayout());

        //jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/images/administrator-40.png"))); // NOI18N
        settingPanel.add(jLabel10, java.awt.BorderLayout.LINE_END);

        jLabel11.setBackground(new java.awt.Color(204, 204, 204));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("الإعدادات");
        settingPanel.add(jLabel11, java.awt.BorderLayout.CENTER);

        menu.add(settingPanel);
}
 
public void refreshTablebuttons() throws Exception{
  head.remove(panexthead);
    this.panexthead=null;
     this.panexthead = new Head();
  
    
    head.add(panexthead,BorderLayout.LINE_END);
    head.revalidate();
    head.repaint();
    
    panTRXButtons.removeAll();
      createTablesButtons();
      panTRXButtons.revalidate();
      panTRXButtons.repaint();
}
public void paintSplitPaneForPanelsTop() throws Exception{
    
      panOrderTRXhead = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panTRXButtons = new javax.swing.JPanel();
        panTRX = new javax.swing.JPanel();

        splitPaneForPanelsTop.setLayout(new java.awt.GridLayout(2, 1));

        panOrderTRXhead.setBackground(new java.awt.Color(204,204,255));
        panOrderTRXhead.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 102, 255)));
        panOrderTRXhead.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("         ");
        panOrderTRXhead.add(jLabel1, java.awt.BorderLayout.LINE_START);

        jLabel2.setText("       ");
        panOrderTRXhead.add(jLabel2, java.awt.BorderLayout.LINE_END);

        jLabel3.setText("   ");
        panOrderTRXhead.add(jLabel3, java.awt.BorderLayout.PAGE_END);

        jLabel4.setText("          ");
        panOrderTRXhead.add(jLabel4, java.awt.BorderLayout.PAGE_START);

        panTRXButtons.setLayout(new java.awt.GridLayout(1, 0));
        panOrderTRXhead.add(panTRXButtons, java.awt.BorderLayout.CENTER);

        splitPaneForPanelsTop.add(panOrderTRXhead);

        panTRX.setBackground(new java.awt.Color(204,204,255));//147, 112, 219
        panTRX.setLayout(new java.awt.BorderLayout());
        createTablesButtons();
        splitPaneForPanelsTop.add(panTRX);
}
private void safePAnelMouseClicked(java.awt.event.MouseEvent evt) {                                       
     setMenuColor(safePAnel);
      panTRX.removeAll();
      PanSafe pan = new PanSafe();
      panTRX.add(pan);
      panTRX.revalidate();
      panTRX.repaint();
    }                                      

    private void homepanelMouseClicked(java.awt.event.MouseEvent evt) {                                       
        setMenuColor(homepanel);
       panTRX.removeAll();
      PanOrder pan = new PanOrder();
      panTRX.add(pan);
      panTRX.revalidate();
      panTRX.repaint();
    }                                      

    private void orderPanelMouseClicked(java.awt.event.MouseEvent evt) {                                        
        setMenuColor(orderPanel);
         panTRX.removeAll();
      PanOrder pan = new PanOrder();
      panTRX.add(pan);
      panTRX.revalidate();
      panTRX.repaint();
    }                                       

    private void reportsPanelMouseClicked(java.awt.event.MouseEvent evt) {                                          
      setMenuColor(reportsPanel);
      panTRX.removeAll();
      PanReports pan = new PanReports();
      panTRX.add(pan);
      panTRX.revalidate();
      panTRX.repaint();
    }                                         

    private void settingPanelMouseClicked(java.awt.event.MouseEvent evt) {                                          
      setMenuColor(settingPanel);
      panTRX.removeAll();
      PanSetting pan = new PanSetting();
      panTRX.add(pan);
      panTRX.revalidate();
      panTRX.repaint();
    }

                                     

    private void warehuseMouseClicked(java.awt.event.MouseEvent evt) throws Exception {                                      
      setMenuColor(warehuse);
      panTRX.removeAll();
      Panwharehouse pan = new Panwharehouse();
      panTRX.add(pan);
      panTRX.revalidate();
      panTRX.repaint();
       
    }                               
   
   public void checkPermission(String flag){
    if(flag.equals("A")){
        
    }else if (flag.equals("U")) {
        safePAnel.setVisible(false);
        reportsPanel.setVisible(false);
        settingPanel.setVisible(false);
        warehuse.setVisible(false);
    }
    
}
 public void setMenuColor(JPanel jPanel){
     if (UserType.equals("A")){
        safePAnel.setBackground(new Color(147,112,219));
        reportsPanel.setBackground(new Color(147,112,219));
        settingPanel.setBackground(new Color(147,112,219));
        homepanel.setBackground(new Color(147,112,219));
        orderPanel.setBackground(new Color(147,112,219));
        warehuse.setBackground(new Color(147,112,219));
        jPanel.setBackground(new Color(85,55,118));
     } else if (UserType.equals("U")){
         orderPanel.setBackground(new Color(147,112,219));
          homepanel.setBackground(new Color(147,112,219));
          jPanel.setBackground(new Color(85,55,118));
         
     }
     
 
    }
 
 public void createTablesButtons() throws Exception{
    Tables t = new Tables();
    int tno = t.getTablesNo();
    String sqlTableName = "select T_TableNo_ID  name ,STATUS from T_TableNo  order by T_TableNo_ID";
   ResultSet rs = new Connector().start_Statment().executeQuery(sqlTableName);
   TableButtons= new JButton[tno] ; 
   while(rs.next()){ 
        String name=""+rs.getInt("name");
         btn=new JButton(name);
         btn.setName("Table"+name);
         btn.addActionListener(new ButtonListener());
         //System.out.println(" Name "+btn.getName()+" Text "+btn.getText());
         if(rs.getString("STATUS").equals("N")){
             btn.setBackground(Color.GREEN);
         }else {
             btn.setBackground(Color.RED);
         }
                panTRXButtons.add(btn);
        
    }
    rs.close();
}
 public void panitSplitButtom(int i){
     if (i==1){
         System.out.println("paint split buttom method ");
      
        /* JFrame f = new JFrame();
         ProductGroupDefine pg = new ProductGroupDefine();
        // f.setSize(MAXIMIZED_BOTH);
         f.setLayout(new BorderLayout());
         f.add(pg,BorderLayout.CENTER);
         f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
         f.setVisible(true);
                 */
     }
     
 }
 
    public static void main(String[] args) throws Exception {
        SplitPaneEXP s = new SplitPaneEXP();
       // s.setPanelSizes();
        s.setVisible(true);
        
       
    }
}


