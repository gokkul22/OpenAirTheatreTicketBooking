import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

class Second_User extends JFrame{
    JFrame f;
    JPanel p1,p2,p3,p4,p5,p6,p7;
    JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b14,slo[] = new JButton[10],b11,b12,colur[] = new JButton[8],b13,cac3,dow[] = new JButton[40],ma[] = new JButton[40],ca[] = new JButton[40];
    int pno;
    String slono;
    int slno[][] = new int[4][10],avail[]= new int[4],fname=(int) (Math.random() * 9000 + 1000);
    JTextField cac[][] = new JTextField[100][7];
    JComboBox co1,co2,vi[]= new JComboBox[4],cac2;
    ImageIcon im[] = new ImageIcon[4];
    JLabel l6,l7,l,l9;
    Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("movie.png"));
    ImageIcon icon1 = new ImageIcon(getClass().getClassLoader().getResource("icon1.png"));
    ImageIcon icon2 = new ImageIcon(getClass().getClassLoader().getResource("icon2.png"));
    ImageIcon icon3 = new ImageIcon(getClass().getClassLoader().getResource("icon3.png"));
    ImageIcon bg1 = new ImageIcon(getClass().getClassLoader().getResource("b_1.jpg"));
    ImageIcon bg2 = new ImageIcon(getClass().getClassLoader().getResource("b1_1.jpg"));
    ImageIcon bg3 = new ImageIcon(getClass().getClassLoader().getResource("b2_1.jpg"));
    ImageIcon bg4 = new ImageIcon(getClass().getClassLoader().getResource("b3_1.jpg"));
    ImageIcon bg5 = new ImageIcon(getClass().getClassLoader().getResource("b4_1.jpg"));
    ImageIcon bg6 = new ImageIcon(getClass().getClassLoader().getResource("b5_1.jpg"));

    Second_User(final String em)
    {
        f = new JFrame();
        f.setResizable(false);
        f.setSize(1360,768);
        f.setTitle("User "+em);
        f.addWindowListener(new window());
        f.setLayout(null);
        f.setIconImage(icon);
        f.setVisible(true);
        final Database a = new Database();
        final String S = "mongodb+srv://Bharath:Bharath123456@opentheatre.sgp39.mongodb.net/OpenTheatre?retryWrites=true&w=majority";
        p1 = new JPanel();
        p1.setBounds(0,0,1360,100);
        p1.setVisible(true);
        p1.setLayout(null);
        p2 = new JPanel();
        p2.setBounds(0,100,1360,668);
        p2.setVisible(true);
        p2.setLayout(null);
        f.add(p2);
        f.add(p1);
        b1 = new JButton("Information");
        b1.setBounds(0,80,100,20);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Booking Tickets :\n1 . Ticket Booking process is based on selection and lock process.\n2 . First you must select the date which you want.\n3 . Then click the green button , Next field will appears the list of theatres and movies with time(Filters also Available).\n4 . if you want tho change the date , You can click red button.\n5 . Vice versa for the remaining fields also .\n6 . After Selecting Type of vehicle,Ticket will be displayed as a page which has 2D representaion of Slots (Red - Ticket not Booking and green - Ticket Booked ).\n7 . After Proceeding Payment , You have two options to get the Bill - Download it in your own system or Mail it to your Email.\n\nView :\n1 . As same as the Booking tickets options upto Time Selection , In time selection field - click green button to view the available No of Slots.\n2 . Click red button to View a information .\n\nBooking History :\n1 . First you must select the date and click the green button(New Frame will be displayed).\n2 . In A Frame , Slots booked by you arranged as a Table which has Cancel,Download and Mail.\n3 . Cancel - This Option only available if you book a ticket in Day tomorrow(24-10-2021 you can cancel the ticket for 25-10-2021).\n4 . Download and Mail are same option in Booking Tickets and when you forgot to download or mail your bill , You has another chance to get it.","Information",JOptionPane.INFORMATION_MESSAGE,icon1);
            }
        });
        p1.add(b1);
        b2 = new JButton("BOOK");
        b2.setBounds(400, 25, 100, 50);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                try (MongoClient mongoClient = MongoClients.create(S)) {
                    co1.setEnabled(true);
                    b3.setEnabled(true);
                    b4.setEnabled(true);
                    co1.removeAllItems();
                    b5.setEnabled(false);
                    b6.setEnabled(false);
                    b7.setEnabled(false);
                    b8.setEnabled(false);
                    b9.setEnabled(false);
                    b10.setEnabled(false);
                    b11.setEnabled(false);
                    co2.setEnabled(false);
                    l6.setText("Type");
                    l7.setText("Slot : ");
                    MongoIterable<String> Db = a.getDate(mongoClient);
                    for (String name : Db) {
                        try
                        {
                            Integer.parseInt(name);
                            name = name.substring(0,2)+"/"+name.substring(2,4)+"/"+name.substring(4,name.length());
                            co1.addItem(name);
                        }
                        catch (NumberFormatException sd){
                        }
                    }
                }
                p3.setVisible(true);
                p5.setVisible(false);
                p6.setVisible(false);
            }
        });
        p1.add(b2);
        b12 = new JButton("VIEW");
        b12.setBounds(550, 25, 100, 50);
        b12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p4.setVisible(false);
                for (int i=0;i<4;i++) {
                    vi[i].removeAllItems();
                    vi[i].setEnabled(true);
                }
                for (int i=0;i<8;i++)
                    colur[i].setEnabled(true);
                vi[0].removeAllItems();
                Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                try (MongoClient mongoClient = MongoClients.create(S)) {
                    MongoIterable<String> Db = a.getDate(mongoClient);
                    for (String name : Db) {
                        try
                        {
                            Integer.parseInt(name);
                            name = name.substring(0,2)+"/"+name.substring(2,4)+"/"+name.substring(4,name.length());
                            vi[0].addItem(name);
                        }
                        catch (NumberFormatException sd){
                        }
                    }
                }
                p3.setVisible(false);
                p5.setVisible(true);
                p6.setVisible(false);
            }
        });
        p1.add(b12);
        b13 = new JButton("BOOKING HISTORY");
        b13.setBounds(700, 25, 150, 50);
        b13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cac2.setEnabled(true);
                cac3.setEnabled(true);
                p4.setVisible(false);
                p7.removeAll();
                p7.setVisible(false);
                p6.setVisible(true);
                cac2.removeAllItems();
                Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                try (MongoClient mongoClient = MongoClients.create(S)) {
                    MongoIterable<String> Db = a.getDate(mongoClient);
                    for (String name : Db) {
                        try
                        {
                            Integer.parseInt(name);
                            name = name.substring(0,2)+"/"+name.substring(2,4)+"/"+name.substring(4,name.length());
                            cac2.addItem(name);
                        }
                        catch (NumberFormatException sd){
                        }
                    }
                }
                p5.setVisible(false);
                p3.setVisible(false);
            }
        });
        p1.add(b13);
        b14 = new JButton("CLOSE");
        b14.setBounds(900, 25, 100, 50);
        b14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        p1.add(b14);
        p3 = new JPanel();
        p3.setBounds(0,0,400,668);
        p3.setVisible(false);
        p3.setLayout(null);
        JLabel l1 = new JLabel("Date");
        l1.setBounds(25, 25, 100, 20);
        p3.add(l1);
        co1 = new JComboBox<>();
        co1.setBounds(125, 25, 100, 20);
        p3.add(co1);
        p2.add(p3);
        b3 = new JButton();
        b3.setBounds(250, 25, 20, 20);
        b3.setBackground(Color.green);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) co1.getSelectedItem();
                co1.setEnabled(false);
                co2.setEnabled(true);
                b5.setEnabled(true);
                b3.setEnabled(false);
                co2.removeAllItems();
                b7.setEnabled(true);
                b6.setEnabled(true);
                Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                try (MongoClient mongoClient = MongoClients.create(S)) {
                   s = s.replaceAll("/", "");
                   MongoCollection<Document> rec = mongoClient.getDatabase(s).getCollection("MovieAdmin");
                   String[] the = a.findAll(rec).split("\n");
                      for (String m :the)
                          co2.addItem(m);
                  }
            }
        });
        p3.add(b3);
        b4 = new JButton();
        b4.setBounds(300, 25, 20, 20);
        b4.setBackground(Color.RED);
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                co1.setEnabled(true);
                co2.removeAllItems();
                b3.setEnabled(true);
                b6.setEnabled(false);
                co2.setEnabled(false);
                b7.setEnabled(false);
                b5.setEnabled(false);
            }
        });
        p3.add(b4);
        JLabel l4 = new JLabel("Movies and Theatres");
        l4.setBounds(25, 75, 200, 20);
        p3.add(l4);
        co2 = new JComboBox<>();
        co2.setBounds(25, 125, 300, 20);
        p3.add(co2);
        b5 = new JButton();
        b5.setBounds(25, 175, 20, 20);
        b5.setBackground(Color.green);
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s2 = (String) co2.getSelectedItem();
                if (!s2.equals("No movies Available")) {
                    b5.setEnabled(false);
                    b4.setEnabled(false);
                    co2.setEnabled(false);
                    b7.setEnabled(false);
                    b8.setEnabled(true);
                    b10.setEnabled(true);
                    b9.setEnabled(true);
                }
                else
                    JOptionPane.showMessageDialog(null, "Select Correct Movie","Warning",JOptionPane.WARNING_MESSAGE,icon2);
            }
        });
        p3.add(b5);
        b6 = new JButton();
        b6.setBounds(70, 175, 20, 20);
        b6.setBackground(Color.RED);
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                co2.setEnabled(true);
                b7.setEnabled(true);
                b5.setEnabled(true);
                b8.setEnabled(false);
                b10.setEnabled(false);
                b9.setEnabled(false);
            }
        });
        p3.add(b6);
        b7 = new JButton("Filters");
        b7.setBounds(125, 175, 100, 20);
        b7.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e) {
                String[] filters = {"By Time", "By Movie", "By Theatre","No Filters"};
                String s = (String) co1.getSelectedItem();
                int x = JOptionPane.showOptionDialog(null, "Which one you prefer ?","Filters",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icon1, filters, filters[0]);
                if (x==0) {
                    String[] options = {"19.00", "22.30"};
                    int xi = JOptionPane.showOptionDialog(null, "Time", "Filters", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icon1, options, options[0]);
                    co2.removeAllItems();
                    boolean k = true;
                    Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                    try (MongoClient mongoClient = MongoClients.create(S)) {
                        s = s.replaceAll("/", "");
                        MongoCollection<Document> rec = mongoClient.getDatabase(s).getCollection("MovieAdmin");
                        String[] the = a.findAll(rec).split("\n");
                        for (String m : the)
                            if (xi != -1 && m.contains(options[xi])) {
                                co2.addItem(m);
                                k = false;
                            }
                    }
                    if (k)
                        co2.addItem("No movies Available");
                }
                else if (x==2) {
                    String[] the;
                    Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                    try (MongoClient mongoClient = MongoClients.create(S)) {
                        s = s.replaceAll("/", "");
                        MongoCollection<Document> rec = mongoClient.getDatabase(s).getCollection("MovieAdmin");
                        the = a.findTheatre(rec).split("-");
                        LinkedHashSet<String> temp = new LinkedHashSet<>(Arrays.asList(the));
                        the = temp.toArray(new String[ temp.size() ]);
                    }
                    int xi = JOptionPane.showOptionDialog(null, "Theatre", "Filters", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icon1, the, the[0]);
                    co2.removeAllItems();
                    boolean k = true;
                    Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                    try (MongoClient mongoClient = MongoClients.create(S)) {
                        s = s.replaceAll("/", "");
                        MongoCollection<Document> rec = mongoClient.getDatabase(s).getCollection("MovieAdmin");
                        String[] te = a.findAll(rec).split("\n");
                        for (String m : te)
                            if (xi != -1 && m.contains(the[xi])) {
                                co2.addItem(m);
                                k = false;
                            }
                    }
                    if (k)
                        co2.addItem("No movies Available");
                }
                else if (x==1) {
                    String[] mov ;
                    Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                    try (MongoClient mongoClient = MongoClients.create(S)) {
                        s = s.replaceAll("/", "");
                        MongoCollection<Document> rec = mongoClient.getDatabase(s).getCollection("MovieAdmin");
                        mov = a.findMovie1(rec).split("-");
                        LinkedHashSet<String> temp = new LinkedHashSet<>(Arrays.asList(mov));
                        mov = temp.toArray(new String[ temp.size() ]);
                    }
                    int xi = JOptionPane.showOptionDialog(null, "Movie", "Filters", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icon1, mov, mov[0]);
                    co2.removeAllItems();
                    boolean k = true;
                    Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                    try (MongoClient mongoClient = MongoClients.create(S)) {
                        s = s.replaceAll("/", "");
                        MongoCollection<Document> rec = mongoClient.getDatabase(s).getCollection("MovieAdmin");
                        String[] the = a.findAll(rec).split("\n");
                        for (String m : the)
                            if (xi != -1 && m.contains(mov[xi])) {
                                co2.addItem(m);
                                k = false;
                            }
                    }
                    if (k)
                        co2.addItem("No movies Available");
                }
                else if (x==-1)
                {
                    co2.removeAllItems();
                    co2.addItem("No movies Available");
                }
                else if (x==3)
                {
                    co2.removeAllItems();
                    Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                    try (MongoClient mongoClient = MongoClients.create(S)) {
                        s = s.replaceAll("/", "");
                        MongoCollection<Document> rec = mongoClient.getDatabase(s).getCollection("MovieAdmin");
                        String[] the = a.findAll(rec).split("\n");
                        for (String m : the)
                            co2.addItem(m);
                    }
                }
            }
        });
        p3.add(b7);
        b8 = new JButton("Select Type");
        b8.setBounds(25, 225, 100, 20);
        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String op[] = {"Bike Model","Indica Model","Venue Model","XYLO Model"};
                im[0] = new ImageIcon(getClass().getClassLoader().getResource("pexels-nishant-aneja-2393831.jpg"));
                im[1] = new ImageIcon(getClass().getClassLoader().getResource("pexels-sergey-filippov-8766145.jpg"));
                im[2] = new ImageIcon(getClass().getClassLoader().getResource("pexels-mike-100656.jpg"));
                im[3] = new ImageIcon(getClass().getClassLoader().getResource("pexels-aaron-curtis-119435.jpg"));
                pno = JOptionPane.showOptionDialog(null, "Which one you prefer ?","Type",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icon1, im, im[0]);
                if(!(pno==-1))
                    l6.setText(op[pno]);
            }
        });
        p3.add(b8);
        l6 = new JLabel("Type");
        l6.setBounds(25, 275, 100, 20);
        l6.setForeground(new Color(0, 0, 102));
        p3.add(l6);
        b9 = new JButton();
        b9.setBounds(150, 275, 20, 20);
        b9.setBackground(Color.green);
        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b8.setEnabled(false);
                b9.setEnabled(false);
                String s = (String) co1.getSelectedItem();
                String  an = (String) co2.getSelectedItem();
                String id;
                String[] all = an.split("-");
                Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                try (MongoClient mongoClient = MongoClients.create(S)) {
                    s = s.replaceAll("/", "");
                    MongoCollection<Document> rec = mongoClient.getDatabase(s).getCollection("MovieAdmin");
                    id = a.findId(rec, all[0], all[1], all[2]);

                    MongoCollection<Document> rec1 = mongoClient.getDatabase(s).getCollection("Tickets");
                    for(int i=0;i<10;i++) {
                        slno[pno][i] = a.findticket(rec1, (char) (65 + pno) + "" + (i + 1), id);

                    }
                }
                p4.removeAll();
                p4.setVisible(true);
                for (int i=0;i<10;i++)
                {
                    slo[i] = new JButton((char)(65+pno)+""+(i+1));
                    if(slno[pno][i]==0)
                        slo[i].setBackground(Color.red);
                    else
                        slo[i].setBackground(Color.green);
                    slo[i].addActionListener(new Slot());
                    p4.add(slo[i]);
                }
            }
        });
        p3.add(b9);
        b10 = new JButton();
        b10.setBounds(200, 275, 20, 20);
        b10.setBackground(Color.RED);
        b10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b8.setEnabled(true);
                p4.removeAll();
                p4.setVisible(false);
                b9.setEnabled(true);
            }
        });
        p3.add(b10);
        l7 = new JLabel("Slot : ");
        l7.setForeground(new Color(255, 255, 77));
        l7.setBounds(25, 325, 100, 20);
        p3.add(l7);
        b11 = new JButton("MAKE PAYMENT");
        b11.setBounds(25,375,150,20);
        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b10.setEnabled(false);
                String s = (String) co1.getSelectedItem();
                String  an = (String) co2.getSelectedItem();
                String[] all = an.split("-");
                Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                try (MongoClient mongoClient = MongoClients.create(S)) {
                    s = s.replaceAll("/", "");
                    MongoCollection<Document> rec = mongoClient.getDatabase(s).getCollection("MovieAdmin");
                    String id = a.findId(rec, all[0], all[1], all[2]);
                    MongoCollection<Document> rec1 = mongoClient.getDatabase(s).getCollection("Tickets");
                    a.upTic(rec1, slono, id);
                    a.upSe(rec, 1, id);
                }
                Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                try (MongoClient mongoClient = MongoClients.create(S)) {
                    MongoCollection<Document> rec = null;
                        s = s.replaceAll("/", "");
                        rec = mongoClient.getDatabase(s).getCollection("UserBill");
                    float tax = 2300 + 2300* 0.18f;
                    int tax1 = Math.round(tax);
                    JOptionPane.showMessageDialog(null, "Bill Amount : " + tax1,"Payment", JOptionPane.INFORMATION_MESSAGE, icon1);
                    String[] options = new String[2];
                    options[0] = new String("Download");
                    options[1] = new String("Mail");
                    int co = JOptionPane.showOptionDialog(null,"Are you going to download or Sent the mail to email ","Options", 0,JOptionPane.INFORMATION_MESSAGE,icon1,options,null);
                    if (co == 0) {
                        DownloadPdf(em, s, all[0],all[1],all[2],slono, tax1, true);
                    }
                    else if (co == 1)
                        EmailPdf(em,s,all[0], all[1], all[2],slono,tax1,false);
                    a.insertbill(rec,em,all[0],all[1],all[2],slono,tax1);
                    b11.setEnabled(false);
                } catch (FileNotFoundException fileNotFoundException) {
                    JOptionPane.showMessageDialog(null,"File Not Found Error ", "Error",JOptionPane.ERROR_MESSAGE, icon3);
                }
            }
        });
        p3.add(b11);

        p4 = new JPanel();
        p4.setBounds(425,64,900,100);
        p4.setVisible(false);
        p4.setLayout(new GridLayout(1,10));
        p2.add(p4);
        p6 = new JPanel();
        p6.setBounds(0,0,1360,50);
        p6.setVisible(false);
        p6.setLayout(null);
        p5 = new JPanel();
        p5.setBounds(450,0,400,668);
        p5.setVisible(false);
        p5.setLayout(null);
        p2.add(p5);
        JLabel vl1 = new JLabel("Date");
        vl1.setBounds(25, 25, 100, 20);
        p5.add(vl1);
        vi[0] = new JComboBox<>();
        vi[0].setBounds(125, 25, 100, 20);
        p5.add(vi[0]);
        colur[0] = new JButton();
        colur[0].setBounds(250, 25, 20, 20);
        colur[0].setBackground(Color.green);
        colur[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) vi[0].getSelectedItem();
                vi[0].setEnabled(false);
                vi[1].removeAllItems();
                Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                try (MongoClient mongoClient = MongoClients.create(S)) {
                    s = s.replaceAll("/", "");
                    MongoCollection<Document> rec = mongoClient.getDatabase(s).getCollection("MovieAdmin");
                    String[] the = a.findTheatre(rec).split("-");
                    LinkedHashSet<String> temp = new LinkedHashSet<>(Arrays.asList(the));
                    the = temp.toArray(new String[ temp.size() ]);
                    for (String m :the)
                        vi[1].addItem(m);
                    }
            }
        });
        p5.add(colur[0]);
        colur[1] = new JButton();
        colur[1].setBounds(300, 25, 20, 20);
        colur[1].setBackground(Color.RED);
        colur[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vi[0].setEnabled(true);
                vi[1].removeAllItems();
            }
        });
        p5.add(colur[1]);
        JLabel vl2 = new JLabel("Theatre");
        vl2.setBounds(25, 75, 100, 20);
        p5.add(vl2);
        vi[1] = new JComboBox<>();
        vi[1].setBounds(125, 75, 100, 20);
        p5.add(vi[1]);
        colur[2] = new JButton();
        colur[2].setBounds(250, 75, 20, 20);
        colur[2].setBackground(Color.green);
        colur[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) vi[0].getSelectedItem();
                String s2 = (String) vi[1].getSelectedItem();
                    vi[1].setEnabled(false);
                    colur[0].setEnabled(false);
                    colur[1].setEnabled(false);
                    vi[2].removeAllItems();
                    Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                    try (MongoClient mongoClient = MongoClients.create(S)) {
                        s = s.replaceAll("/", "");
                        MongoCollection<Document> rec = mongoClient.getDatabase(s).getCollection("MovieAdmin");
                        String[] mov = a.findMovie(rec,(String)vi[1].getSelectedItem()).split("-");
                        LinkedHashSet<String> temp = new LinkedHashSet<>(Arrays.asList(mov));
                        mov = temp.toArray(new String[ temp.size() ]);
                        for (String m :mov)
                            vi[2].addItem(m);
                    }
            }
        });
        p5.add(colur[2]);
        colur[3] = new JButton();
        colur[3].setBounds(300, 75, 20, 20);
        colur[3].setBackground(Color.RED);
        colur[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vi[1].setEnabled(true);
                vi[2].removeAllItems();
                colur[1].setEnabled(true);
                colur[0].setEnabled(true);
            }
        });
        p5.add(colur[3]);
        JLabel vl3 = new JLabel("Movie");
        vl3.setBounds(25, 125, 100, 20);
        p5.add(vl3);
        vi[2] = new JComboBox<>();
        vi[2].setBounds(125, 125, 100, 20);
        p5.add(vi[2]);
        colur[4] = new JButton();
        colur[4].setBounds(250, 125, 20, 20);
        colur[4].setBackground(Color.green);
        colur[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) vi[0].getSelectedItem();
                String s2 = (String) vi[2].getSelectedItem();
                    vi[2].setEnabled(false);
                    colur[2].setEnabled(false);
                    colur[3].setEnabled(false);
                    vi[3].removeAllItems();
                    Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                    try (MongoClient mongoClient = MongoClients.create(S)) {
                        s = s.replaceAll("/", "");
                        MongoCollection<Document> rec = mongoClient.getDatabase(s).getCollection("MovieAdmin");
                        String[] ti = a.findDate(rec,(String)vi[1].getSelectedItem(),(String)vi[2].getSelectedItem()).split(" ");
                        for (String m :ti)
                            vi[3].addItem(m);
                    }
            }
        });
        p5.add(colur[4]);
        colur[5] = new JButton();
        colur[5].setBounds(300, 125, 20, 20);
        colur[5].setBackground(Color.RED);
        colur[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vi[2].setEnabled(true);
                vi[3].removeAllItems();
                colur[2].setEnabled(true);
                colur[3].setEnabled(true);
            }
        });
        p5.add(colur[5]);
        JLabel vl4 = new JLabel("Time");
        vl4.setBounds(25, 175, 100, 20);
        p5.add(vl4);
        vi[3] = new JComboBox<>();
        vi[3].setBounds(125, 175, 100, 20);
        p5.add(vi[3]);
        colur[6] = new JButton();
        colur[6].setBounds(250, 175, 20, 20);
        colur[6].setBackground(Color.green);
        colur[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vi[3].setEnabled(false);
                colur[4].setEnabled(false);
                colur[5].setEnabled(false);
                String s = (String) vi[0].getSelectedItem();
                Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                try (MongoClient mongoClient = MongoClients.create(S)) {
                    s = s.replaceAll("/", "");
                    MongoCollection<Document> rec = mongoClient.getDatabase(s).getCollection("MovieAdmin");
                    String id = a.findId(rec, (String) vi[1].getSelectedItem(), (String) vi[2].getSelectedItem(), (String) vi[3].getSelectedItem());
                    MongoCollection<Document> rec1 = mongoClient.getDatabase(s).getCollection("Tickets");
                    for(int i=0;i<4;i++)
                        for(int j=0;j<10;j++)
                        {
                            if(a.findticket(rec1,(char) (65 + i) + "" + (j + 1),id)==0)
                                avail[i]++;
                        }
                    JOptionPane.showMessageDialog(null,"Available No of Slots : " +
                            "\nBike Model   : " + avail[0]+
                            "\nIndica Model : "+avail[1]+
                            "\nVenue Model  : "+avail[2]+
                            "\nXYLO Model   : "+avail[3],"Success",JOptionPane.INFORMATION_MESSAGE,icon1);
                    for(int i=0;i<4;i++)
                        avail[i]=0;
                }
            }
        });
        p5.add(colur[6]);
        colur[7] = new JButton();
        colur[7].setBounds(300, 175, 20, 20);
        colur[7].setBackground(Color.RED);
        colur[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"for Refresh click VIEW button","Information",JOptionPane.INFORMATION_MESSAGE,icon1);
            }
        });
        p5.add(colur[7]);
        p7 = new JPanel();
        p7.setVisible(false);
        p7.setLayout(null);
        p7.setBounds(0,50,1360,618);
        JLabel cac1 = new JLabel("Date");
        cac1.setBounds(25, 25, 100, 20);
        p6.add(cac1);
        cac2 = new JComboBox<>();
        cac2.setBounds(125, 25, 100, 20);
        p6.add(cac2);

        cac3 = new JButton();
        cac3.setBounds(250, 25, 20, 20);
        cac3.setBackground(Color.green);
        cac3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (final MongoClient mongoClient = MongoClients.create(S)) {
                    String s = (String) cac2.getSelectedItem();
                    s = s.replaceAll("/", "");
                    Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                    MongoCollection<Document> rec = mongoClient.getDatabase(s).getCollection("UserBill");
                    Document doc1 = rec.find(Filters.eq("email", em)).first();
                    if(doc1!=null) {
                        cac3.setEnabled(false);
                        cac2.setEnabled(false);
                        p7.setVisible(true);
                        cac[0][0] = new JTextField("Sno");
                        cac[0][0].setEditable(false);
                        cac[0][0].setHorizontalAlignment(0);
                        cac[0][0].setBounds(25, 50, 150, 40);
                        p7.add(cac[0][0]);
                        cac[0][1] = new JTextField("Theatre");
                        cac[0][1].setEditable(false);
                        cac[0][1].setHorizontalAlignment(0);
                        cac[0][1].setBounds(175, 50, 150, 40);
                        p7.add(cac[0][1]);
                        cac[0][2] = new JTextField("Movie");
                        cac[0][2].setEditable(false);
                        cac[0][2].setHorizontalAlignment(0);
                        cac[0][2].setBounds(325, 50, 150, 40);
                        p7.add(cac[0][2]);
                        cac[0][3] = new JTextField("Time");
                        cac[0][3].setEditable(false);
                        cac[0][3].setHorizontalAlignment(0);
                        cac[0][3].setBounds(475, 50, 150, 40);
                        p7.add(cac[0][3]);
                        cac[0][4] = new JTextField("Slot");
                        cac[0][4].setEditable(false);
                        cac[0][4].setHorizontalAlignment(0);
                        cac[0][4].setBounds(625, 50, 150, 40);
                        p7.add(cac[0][4]);
                        cac[0][5] = new JTextField("Total Amount");
                        cac[0][5].setEditable(false);
                        cac[0][5].setHorizontalAlignment(0);
                        cac[0][5].setBounds(775, 50, 150, 40);
                        p7.add(cac[0][5]);
                        int i = 1;
                        FindIterable<Document> docs = rec.find(Filters.eq("email", em));
                        for (final Document doc : docs) {
                            cac[i][0] = new JTextField(i + "");
                            cac[i][0].setEditable(false);
                            cac[i][0].setBounds(25, 50 + (40 * i), 150, 40);
                            p7.add(cac[i][0]);
                            cac[i][1] = new JTextField(doc.getString("Theatre"));
                            cac[i][1].setEditable(false);
                            cac[i][1].setBounds(175, 50 + (40 * i), 150, 40);
                            p7.add(cac[i][1]);
                            cac[i][2] = new JTextField(doc.getString("Movie"));
                            cac[i][2].setEditable(false);
                            cac[i][2].setBounds(325, 50 + (40 * i), 150, 40);
                            p7.add(cac[i][2]);
                            cac[i][3] = new JTextField(doc.getString("Time"));
                            cac[i][3].setEditable(false);
                            cac[i][3].setBounds(475, 50 + (40 * i), 150, 40);
                            p7.add(cac[i][3]);
                            cac[i][4] = new JTextField(doc.getString("Slots") + "");
                            cac[i][4].setEditable(false);
                            cac[i][4].setBounds(625, 50 + (40 * i), 150, 40);
                            p7.add(cac[i][4]);
                            cac[i][5] = new JTextField(doc.getInteger("Total Amount") + "");
                            cac[i][5].setEditable(false);
                            cac[i][5].setBounds(775, 50 + (40 * i), 150, 40);
                            p7.add(cac[i][5]);
                            ca[i - 1] = new JButton("Cancel");
                            ca[i - 1].setBackground(Color.red);
                            ca[i - 1].setBounds(950, 50 + (40 * i), 100, 40);
                            ca[i - 1].setEnabled(false);
                            int date = Integer.parseInt(s.substring(0, 2));
                            GregorianCalendar cal = new GregorianCalendar();
                            int realDay = cal.get(GregorianCalendar.DAY_OF_MONTH);
                            if ((realDay + 1) == date)
                                ca[i - 1].setEnabled(true);
                            final int finalI1 = i;
                            final String finalS = s;
                            ca[i - 1].addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String th = cac[finalI1][1].getText();
                                    String mv = cac[finalI1][2].getText();
                                    String ti = cac[finalI1][3].getText();
                                    String tic = cac[finalI1][4].getText();
                                    String ta = cac[finalI1][5].getText();
                                    Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                                    try (final MongoClient mongoClient = MongoClients.create(S)) {
                                        final String username = "rcap707000@gmail.com";
                                        final String password = "babu@2107";
                                        Properties prop = new Properties();
                                        prop.put("mail.smtp.starttls.enable", "true");
                                        prop.put("mail.smtp.host", "smtp.gmail.com");
                                        prop.put("mail.smtp.port", "587");
                                        prop.put("mail.smtp.auth", "true");
                                        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
                                            protected PasswordAuthentication getPasswordAuthentication() {
                                                return new PasswordAuthentication(username, password);
                                            }
                                        });

                                        try {
                                            Message message = new MimeMessage(session);
                                            message.setFrom(new InternetAddress(username));
                                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(em));
                                            message.setSubject("Ticket Cancellation");
                                            message.setText("Request For Cancelation is Approved.Thank You Sir!!!");
                                            Transport.send(message);
                                            a.cancel(mongoClient, finalS, em, th, mv, ti, tic, ta);
                                            JOptionPane.showMessageDialog(null, "Ticket Cancelled", "Success", JOptionPane.INFORMATION_MESSAGE, icon1);
                                            ca[finalI1 - 1].setEnabled(false);
                                            dow[finalI1 - 1].setEnabled(false);
                                            ma[finalI1 - 1].setEnabled(false);
                                        } catch (AddressException addressException) {
                                            JOptionPane.showMessageDialog(null, e, "Gmail Error ", JOptionPane.ERROR_MESSAGE, icon3);
                                        } catch (MessagingException messagingException) {
                                            JOptionPane.showMessageDialog(null, e, "Gmail Error ", JOptionPane.ERROR_MESSAGE, icon3);
                                        }
                                    }
                                }
                            });
                            dow[i - 1] = new JButton("Download");
                            dow[i - 1].setBackground(Color.green);
                            dow[i - 1].setBounds(1050, 50 + (40 * i), 100, 40);
                            final String finalS1 = s;
                            dow[i - 1].addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String th = cac[finalI1][1].getText();
                                    String mv = cac[finalI1][2].getText();
                                    String ti = cac[finalI1][3].getText();
                                    String tic = cac[finalI1][4].getText();
                                    String ta = cac[finalI1][5].getText();
                                    try {
                                        DownloadPdf(em, finalS1, th, mv, ti, tic, Integer.parseInt(ta), true);
                                    } catch (FileNotFoundException ex) {
                                        JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE, icon3);
                                    }
                                }
                            });
                            p7.add(dow[i - 1]);
                            ma[i - 1] = new JButton("Mail");
                            ma[i - 1].setBackground(Color.blue);
                            ma[i - 1].setBounds(1150, 50 + (40 * i), 100, 40);
                            ma[i - 1].setForeground(Color.white);
                            ma[i - 1].addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String th = cac[finalI1][1].getText();
                                    String mv = cac[finalI1][2].getText();
                                    String ti = cac[finalI1][3].getText();
                                    String tic = cac[finalI1][4].getText();
                                    String ta = cac[finalI1][5].getText();
                                    try {
                                        EmailPdf(em, finalS1, th, mv, ti, tic, Integer.parseInt(ta), false);
                                    } catch (FileNotFoundException ex) {
                                        JOptionPane.showMessageDialog(null, ex, "Error ", JOptionPane.ERROR_MESSAGE, icon3);
                                    }
                                }
                            });
                            p7.add(ma[i - 1]);
                            p7.add(ca[i - 1]);
                            i++;
                        }
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Booking History not Exist", "Error ", JOptionPane.ERROR_MESSAGE, icon3);
                }
                p7.add(l9);
            }
        });
        p6.add(cac3);
        JButton cac4 = new JButton();
        cac4.setBounds(300, 25, 20, 20);
        cac4.setBackground(Color.red);
        cac4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cac2.setEnabled(true);
                cac3.setEnabled(true);
                p7.removeAll();
                p7.setVisible(false);
            }
        });
        p6.add(cac4);
        p2.add(p7);
        p2.add(p6);
        l = new JLabel(bg1);
        l.setBounds(0,0,1360,100);
        p1.add(l);
        l = new JLabel(bg2);
        l.setBounds(0,0,1360,668);
        p2.add(l);
        l = new JLabel(bg3);
        l.setBounds(0,0,400,668);
        p3.add(l);
        l = new JLabel(bg4);
        l.setBounds(0,0,400,668);
        p5.add(l);
        l = new JLabel(bg5);
        l.setBounds(0,0,1360,50);
        p6.add(l);
        l9 = new JLabel(bg6);
        l9.setBounds(0,0,1360,618);

    }
    private void EmailPdf(String em,String Date,String Theatre, String Movie, String Time, String tick, int tax,boolean f) throws FileNotFoundException {
        DownloadPdf(em, Date, Theatre,Movie, Time, tick, tax, f);
        final String username = "rcap707000@gmail.com";
        final String password = "babu@2107";
        Properties prop = new Properties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(prop,new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(em));
            message.setSubject("Movie Booking");
            message.setText("BIll");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Thanks For Supporting");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            String filename = fname+"Bill.pdf";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            JOptionPane.showMessageDialog(null, "Check in Spam if Mail is not in Inbox","Success", JOptionPane.INFORMATION_MESSAGE, icon1);
            File f1 = new File(fname+"Bill.pdf");
            f1.delete();
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null,e,"Gmail Error ", JOptionPane.ERROR_MESSAGE, icon3);
        }
    }
    private void DownloadPdf(final String em, final String Date, final String Theatre, final String Movie, String Time, final String tick, final int tax, boolean f) throws FileNotFoundException {
        if(f) {
            String file = JOptionPane.showInputDialog("Enter the Path ","Bill.pdf");
            if (!file.equals("")) {
                String dest = file;
                PdfWriter writer = null;
                try {
                    writer = new PdfWriter(dest);
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, ex,"Error ", JOptionPane.ERROR_MESSAGE, icon3);
                }
                PdfDocument pdf = new PdfDocument(writer);
                com.itextpdf.layout.Document doc = new com.itextpdf.layout.Document(pdf);
                pdf.setDefaultPageSize(PageSize.A4);
                float[] colwid = {350, 210};
                Table table = new Table(colwid);
                table.setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(com.itextpdf.kernel.color.Color.WHITE);
                table.addCell(new Cell().add("MOVIE BOOKING").setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setFontSize(30f).setMarginTop(30f).setMarginBottom(30f).setBorder(Border.NO_BORDER));
                table.addCell(new Cell().add("CREATED BY \nGOKKUL \nBHARATH \nSAKTHEESWARAN").setTextAlignment(TextAlignment.RIGHT).setMarginTop(30f).setMarginBottom(30f).setMarginRight(10f).setBorder(Border.NO_BORDER));
                float colwid1[] = {80, 480};
                Table t1 = new Table(colwid1).setFontSize(20f);
                t1.addCell(new Cell().add("Email").setBorder(Border.NO_BORDER));
                t1.addCell(new Cell().add(em).setBorder(Border.NO_BORDER));
                t1.addCell(new Cell().add("Date").setBorder(Border.NO_BORDER));
                t1.addCell(new Cell().add(Date.substring(0, 2) + "/" + Date.substring(2, 4) + "/" + Date.substring(4, Date.length())).setBorder(Border.NO_BORDER));
                float colwid2[] = {200, 360};
                Table t2 = new Table(colwid2).setFontSize(20f);
                t2.addCell(new Cell(0, 4).add("Bill Details").setBold().setBorder(Border.NO_BORDER));
                t2.addCell(new Cell().add("Theatre").setBorder(Border.NO_BORDER));
                t2.addCell(new Cell().add(Theatre).setBorder(Border.NO_BORDER));
                t2.addCell(new Cell().add("Movie").setBorder(Border.NO_BORDER));
                t2.addCell(new Cell().add(Movie).setBorder(Border.NO_BORDER));
                Table t4 = new Table(colwid2).setFontSize(20f);
                t2.addCell(new Cell().add("Slot ").setBorder(Border.NO_BORDER));
                t2.addCell(new Cell().add(tick).setBorder(Border.NO_BORDER));
                int rate = 2300;
                float ta = 2300 * 0.18f;
                t4.addCell(new Cell().add("Rate").setBorder(Border.NO_BORDER));
                t4.addCell(new Cell().add(rate + "").setBorder(Border.NO_BORDER));
                t4.addCell(new Cell().add("GST (18%)").setBorder(Border.NO_BORDER));
                t4.addCell(new Cell().add(ta + "").setBorder(Border.NO_BORDER));
                t4.addCell(new Cell().add("Total Amount").setBorder(Border.NO_BORDER));
                t4.addCell(new Cell().add(tax + "").setBorder(Border.NO_BORDER));
                float colwid3[] = {560};
                Table t3 = new Table(colwid3);
                t3.setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(com.itextpdf.kernel.color.Color.WHITE);
                t3.addCell(new Cell().add("GOOD DAY").setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setFontSize(30f).setItalic().setMarginTop(30f).setMarginBottom(30f).setBorder(Border.NO_BORDER));
                doc.add(table);
                doc.add(new Paragraph("\n"));
                doc.add(t1);
                doc.add(new Paragraph("\n"));
                doc.add(new Paragraph("\n"));
                doc.add(t2);
                doc.add(new Paragraph("\n"));
                doc.add(t4);
                doc.add(new Paragraph("\n"));
                doc.add(new Paragraph("\n"));
                doc.add(new Paragraph("\n"));
                doc.add(new Paragraph("\n"));
                doc.add(t3);
                doc.close();
                JOptionPane.showMessageDialog(null, "Downloaded Successfully","Success", JOptionPane.INFORMATION_MESSAGE, icon1);
            }
            else
                JOptionPane.showMessageDialog(null, "Enter the Correct Path","Warning",JOptionPane.WARNING_MESSAGE,icon2);
        }
        else
        {
            String dest = fname+"Bill.pdf";
            PdfWriter writer = null;
            try {
                writer = new PdfWriter(dest);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex,"Error ", JOptionPane.ERROR_MESSAGE, icon3);
            }
            PdfDocument pdf = new PdfDocument(writer);
            com.itextpdf.layout.Document doc = new com.itextpdf.layout.Document(pdf);
            pdf.setDefaultPageSize(PageSize.A4);
            float[] colwid = {350, 210};
            Table table = new Table(colwid);
            table.setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(com.itextpdf.kernel.color.Color.WHITE);
            table.addCell(new Cell().add("MOVIE BOOKING").setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setFontSize(30f).setMarginTop(30f).setMarginBottom(30f).setBorder(Border.NO_BORDER));
            table.addCell(new Cell().add("CREATED BY \nGOKKUL \nBHARATH \nSAKTHEESWARAN").setTextAlignment(TextAlignment.RIGHT).setMarginTop(30f).setMarginBottom(30f).setMarginRight(10f).setBorder(Border.NO_BORDER));
            float colwid1[] = {80, 480};
            Table t1 = new Table(colwid1).setFontSize(20f);
            t1.addCell(new Cell().add("Email").setBorder(Border.NO_BORDER));
            t1.addCell(new Cell().add(em).setBorder(Border.NO_BORDER));
            t1.addCell(new Cell().add("Date").setBorder(Border.NO_BORDER));
            t1.addCell(new Cell().add(Date.substring(0,2)+"/"+Date.substring(2,4)+"/"+Date.substring(4,Date.length())).setBorder(Border.NO_BORDER));
            float colwid2[] = {200, 360};
            Table t2 = new Table(colwid2).setFontSize(20f);
            t2.addCell(new Cell(0, 4).add("Bill Details").setBold().setBorder(Border.NO_BORDER));
            t2.addCell(new Cell().add("Theatre").setBorder(Border.NO_BORDER));
            t2.addCell(new Cell().add(Theatre).setBorder(Border.NO_BORDER));
            t2.addCell(new Cell().add("Movie").setBorder(Border.NO_BORDER));
            t2.addCell(new Cell().add(Movie).setBorder(Border.NO_BORDER));
            Table t4 = new Table(colwid2).setFontSize(20f);
            t2.addCell(new Cell().add("Slot").setBorder(Border.NO_BORDER));
            t2.addCell(new Cell().add(tick).setBorder(Border.NO_BORDER));
            int rate = 2300;
            float ta = 2300 * 0.18f;
            t4.addCell(new Cell().add("Rate").setBorder(Border.NO_BORDER));
            t4.addCell(new Cell().add(rate + "").setBorder(Border.NO_BORDER));
            t4.addCell(new Cell().add("GST (18%)").setBorder(Border.NO_BORDER));
            t4.addCell(new Cell().add(ta + "").setBorder(Border.NO_BORDER));
            t4.addCell(new Cell().add("Total Amount").setBorder(Border.NO_BORDER));
            t4.addCell(new Cell().add(tax + "").setBorder(Border.NO_BORDER));
            float colwid3[] = {560};
            Table t3 = new Table(colwid3);
            t3.setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(com.itextpdf.kernel.color.Color.WHITE);
            t3.addCell(new Cell().add("GOOD DAY").setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE).setFontSize(30f).setItalic().setMarginTop(30f).setMarginBottom(30f).setBorder(Border.NO_BORDER));
            doc.add(table);
            doc.add(new Paragraph("\n"));
            doc.add(t1);
            doc.add(new Paragraph("\n"));
            doc.add(new Paragraph("\n"));
            doc.add(t2);
            doc.add(new Paragraph("\n"));
            doc.add(t4);
            doc.add(new Paragraph("\n"));
            doc.add(new Paragraph("\n"));
            doc.add(new Paragraph("\n"));
            doc.add(new Paragraph("\n"));
            doc.add(t3);
            doc.close();
        }
    }

//    public static void main(String[] ar) {
//     new  Second_User("bmkvs541@gmail.com");
//    }

    private class Slot implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            l7.setText("Slot : "+e.getActionCommand());
            slono = e.getActionCommand();
            p4.setVisible(false);
            b10.setEnabled(false);
            b11.setEnabled(true);
        }
    }
}
