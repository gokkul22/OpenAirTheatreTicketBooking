import com.mongodb.client.*;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

class First extends JFrame
{
    JFrame f;
    JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12;
    JPanel p,p1,p3,p4,p5,p6,p2,p7;
    JTextField t1,t3,t5;
    JPasswordField t2,t4,t6,t7,t8,t9,t10;
    JCheckBox c1,c2,c3,c4,c5,c6,c7;
    Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("movie.png"));
    ImageIcon icon1 = new ImageIcon(getClass().getClassLoader().getResource("icon1.png"));
    ImageIcon icon2 = new ImageIcon(getClass().getClassLoader().getResource("icon2.png"));
    ImageIcon icon3 = new ImageIcon(getClass().getClassLoader().getResource("icon3.png"));
    ImageIcon bg1 = new ImageIcon(getClass().getClassLoader().getResource("b.jpg"));
    ImageIcon bg2 = new ImageIcon(getClass().getClassLoader().getResource("b1.jpg"));
    ImageIcon bg3 = new ImageIcon(getClass().getClassLoader().getResource("b2.jpg"));
    ImageIcon bg4 = new ImageIcon(getClass().getClassLoader().getResource("b3.jpg"));
    ImageIcon bg5 = new ImageIcon(getClass().getClassLoader().getResource("b5.jpg"));
    ImageIcon bg6 = new ImageIcon(getClass().getClassLoader().getResource("b6.jpg"));
    ImageIcon bg7 = new ImageIcon(getClass().getClassLoader().getResource("b7.jpg"));
    Database a = new Database();
    String s = "mongodb+srv://Bharath:Bharath123456@opentheatre.sgp39.mongodb.net/OpenTheatre?retryWrites=true&w=majority";
    JLabel l,l12,l11;
    First()
    {
        Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
        try (MongoClient mongoClient = MongoClients.create(s)) {
            MongoIterable<String> Db = a.getDate(mongoClient);
            Date date = new Date();
            for (String name : Db) {
                try
                {
                    Integer.parseInt(name);
                    String n1;
                    n1 = name.substring(0,2)+"/"+name.substring(2,4)+"/"+name.substring(4,name.length());
                    SimpleDateFormat fw = new SimpleDateFormat("dd/MM/yyyy");
                    Date dt = fw.parse(n1);
                    long dtw =dt.getTime();
                    long dtw1 =date.getTime();
                    long dte = dtw<dtw1 ? dtw1-dtw:0;
                    int ld =(int)(dte/(1000*60*60*24))%365;
                    if(ld>0)
                    {
                        MongoDatabase db = mongoClient.getDatabase(name);
                        db.drop();
                    }
                }
                catch (NumberFormatException sd){
                } catch (ParseException e) {
                }
            }
        }
        f = new JFrame();
        f.setResizable(false);
        f.setSize(1360,768);
        f.setTitle("TICKET BOOKING APP");
        f.addWindowListener(new window());
        f.setLayout(null);
        f.setVisible(true);
        f.setIconImage(icon);
        JOptionPane.showMessageDialog(null,"If you are new to this application ,read the information carefully.\nTo read the information Click Information button.","Welcome",JOptionPane.INFORMATION_MESSAGE,icon1);
        p = new JPanel();
        p.setVisible(true);
        p.setLayout(null);
        p.setBounds(0,0,1360,100);
        b1 = new JButton("USER");
        b1.setBounds(550,25,100,50);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p2.setVisible(false);
                p3.setVisible(true);
            }
        });
        p.add(b1);
        b2 = new JButton("ADMIN");
        b2.setBounds(700,25,100,50);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p3.setVisible(false);
                p6.setVisible(false);
                p5.setVisible(false);
                p4.setVisible(false);
                p2.setVisible(true);
            }
        });
        p.add(b2);
        b12 = new JButton("Information");
        b12.setBounds(0,80,100,20);
        b12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Register :\n1 . Enter the Email Id and password where password is a collection of any characters.\n2 . If Registration Success , Your Email Id is registered and You can login using the Email Id and Password.\n3 . If you already Registered , then you get a warning message.\n\nLogin:\n1 . Enter the Registered Email and password.\n2 . If Login Sucess , then you are taken to next page.\n3 . If Email is not Registered , then you get a warning message.\n\nNOTE : if you forgot the password , then we have the option to reset the password by sending a 4 digit code to your mail.","Information",JOptionPane.INFORMATION_MESSAGE,icon1);
            }
        });
        p.add(b12);
        f.add(p);
        p1  = new JPanel();
        p1.setBounds(0,100,1360,668);
        p1.setVisible(true);
        p1.setLayout(null);
        p6  = new JPanel();
        p6.setBounds(680,0,680,334);
        p6.setVisible(false);
        p6.setLayout(null);
        p7  = new JPanel();
        p7.setBounds(0,0,680,334);
        p7.setVisible(false);
        p7.setLayout(null);
        p3 = new JPanel();
        p3.setBounds(0,0,680,100);
        p3.setVisible(false);
        p3.setLayout(null);
        b3 = new JButton("LOGIN");
        b3.setBounds(25,25,100,50);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p5.setVisible(false);
                p4.setVisible(true);
            }
        });
        p3.add(b3);
        b4 = new JButton("REGISTER");
        b4.setBounds(225,25,100,50);
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p4.setVisible(false);
                p5.setVisible(true);
            }
        });
        p3.add(b4);
        p1.add(p3);
        p4 = new JPanel();
        p4.setVisible(false);
        p4.setBounds(0,100,680,568);
        p4.setLayout(null);
        JLabel l1 = new JLabel("Email");
        l1.setBounds(25,100,100,20);
        l1.setForeground(Color.red);
        p4.add(l1);
        t1 = new JTextField();
        t1.setBounds(125,100,300,20);
        p4.add(t1);
        JLabel l2 = new JLabel("Password");
        l2.setBounds(25,150,100,20);
        l2.setForeground(Color.red);
        p4.add(l2);
        t2 = new JPasswordField();
        t2.setBounds(125,150,300,20);
        p4.add(t2);
        t2.setEchoChar('*');
        c1 = new JCheckBox("Show Password");
        c1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (c1.isSelected())
                    t2.setEchoChar((char)0);
                else
                    t2.setEchoChar('*');
            }
        });
        c1.setBounds(450,150,125,20);
        p4.add(c1);
        b5 = new JButton("LOGIN");
        b5.setBounds(25,200,100,20);
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String em = t1.getText();
                String pwd = new String(t2.getPassword());
                if(!em.equals("")){
                     if(!pwd.equals("")){
                             if(isemail(em)) {
                                 Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                                 try (MongoClient mongoClient = MongoClients.create(s)) {
                                     MongoCollection<Document> record = mongoClient.getDatabase("OpenTheatre").getCollection("userlogin");
                                     if (a.findlogin(record, em, pwd)) {
                                         JOptionPane.showMessageDialog(null, "Login Success " +em,"Success",JOptionPane.INFORMATION_MESSAGE,icon1);
                                         f.setVisible(false);
                                         new Second_User(em);
                                     } else {
                                         JOptionPane.showMessageDialog(null, "Invalid Password/Email Id","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                                         b8 = new JButton("FORGOT PASSWORD");
                                         b8.setBounds(225,200,200,20);
                                         b8.addActionListener(new ActionListener() {
                                             @Override
                                             public void actionPerformed(ActionEvent e) {
                                                 int code = 0;
                                                 int n = (int) (Math.random() * 9000 + 1000);
                                                 Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                                                 try (MongoClient mongoClient = MongoClients.create(s)) {
                                                     MongoCollection<Document> record = mongoClient.getDatabase("OpenTheatre").getCollection("userlogin");
                                                     if (a.findemail(record, em)) {
                                                         Integer[] codes = {n, (int) (Math.random() * 9000 + 1000), (int) (Math.random() * 9000 + 1000)};
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
                                                             message.setSubject("CODE FOR CHANGING PASSWORD");
                                                             message.setText("4 digit code for reset password : " + n);
                                                             Transport.send(message);
                                                             int x = JOptionPane.showOptionDialog(null, "Click the code which is sent to your mail.if not available check in Spam : ", "Reset Password", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icon1, codes, codes[0]);
                                                             code = codes[x];
                                                         } catch (MessagingException ae) {
                                                             JOptionPane.showMessageDialog(null, ae, "Gmail Error ", JOptionPane.ERROR_MESSAGE, icon3);
                                                         }
                                                         if (code == n) {
                                                             JLabel l7 = new JLabel("New Password");
                                                             l7.setBounds(25, 25, 200, 20);
                                                             p6.add(l7);
                                                             t7 = new JPasswordField();
                                                             t7.setBounds(225, 25, 300, 20);
                                                             p6.add(t7);
                                                             JLabel l8 = new JLabel("Confirm New Password");
                                                             l8.setBounds(25, 75, 200, 20);
                                                             p6.add(l8);
                                                             t8 = new JPasswordField();
                                                             t8.setBounds(225, 75, 300, 20);
                                                             p6.add(t8);
                                                             t8.setEchoChar('*');
                                                             t7.setEchoChar('*');
                                                             c6 = new JCheckBox("Show Password");
                                                             c6.addActionListener(new ActionListener() {
                                                                 @Override
                                                                 public void actionPerformed(ActionEvent e) {
                                                                     if (c6.isSelected())
                                                                         t7.setEchoChar((char)0);
                                                                     else
                                                                         t7.setEchoChar('*');
                                                                 }
                                                             });
                                                             c6.setBounds(225,50,125,20);
                                                             p6.add(c6);
                                                             c7 = new JCheckBox("Show Password");
                                                             c7.addActionListener(new ActionListener() {
                                                                 @Override
                                                                 public void actionPerformed(ActionEvent e) {
                                                                     if (c7.isSelected())
                                                                         t8.setEchoChar((char)0);
                                                                     else
                                                                         t8.setEchoChar('*');
                                                                 }
                                                             });
                                                             c7.setBounds(225,100,125,20);
                                                             p6.add(c7);
                                                             b10 = new JButton("Set Password");
                                                             b10.setBounds(25, 150, 200, 20);
                                                             b10.addActionListener(new ActionListener() {
                                                                 @Override
                                                                 public void actionPerformed(ActionEvent e) {
                                                                     String newp = new String(t7.getPassword());
                                                                     String cnewp = new String(t8.getPassword());
                                                                     Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                                                                     try (MongoClient mongoClient = MongoClients.create(s)) {
                                                                         MongoCollection<Document> record = mongoClient.getDatabase("OpenTheatre").getCollection("userlogin");
                                                                         if (!newp.equals("") && !cnewp.equals("") && newp.equals(cnewp)) {
                                                                             String op = a.findpass(record, em);
                                                                             if (!newp.equals(op)) {
                                                                                 record.updateOne(new Document("email", em), Updates.set("password", newp));
                                                                                 JOptionPane.showMessageDialog(null, "Password Updated ", "Password Reseted", JOptionPane.INFORMATION_MESSAGE, icon1);
                                                                                 p6.setVisible(false);
                                                                             } else
                                                                                 JOptionPane.showMessageDialog(null, "Enter A New Password ", "Warning", JOptionPane.WARNING_MESSAGE, icon2);
                                                                             t7.setText("");
                                                                             t8.setText("");
                                                                         } else
                                                                             JOptionPane.showMessageDialog(null, "Enter the Password Correctly", "Warning", JOptionPane.WARNING_MESSAGE, icon2);
                                                                         t7.setText("");
                                                                         t8.setText("");
                                                                     }
                                                                 }
                                                             });
                                                             t7.setText("");
                                                             t8.setText("");
                                                             p6.add(b10);
                                                             p6.add(l11);
                                                             p6.setVisible(true);
                                                         } else
                                                             JOptionPane.showMessageDialog(null, "Wrong Code Try Again", "Warning", JOptionPane.WARNING_MESSAGE, icon2);
                                                     } else
                                                         JOptionPane.showMessageDialog(null, "Try Existing Email", "Warning", JOptionPane.WARNING_MESSAGE, icon2);
                                                 }
                                             }
                                         });
                                         p4.add(b8);
                                     }
                                 }
                             }
                             else
                                 JOptionPane.showMessageDialog(null, "Enter Valid Email Id","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                     }
                     else
                         JOptionPane.showMessageDialog(null, "Enter the password","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                }
                else
                    JOptionPane.showMessageDialog(null, "Enter Email Id","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                t1.setText("");
                t2.setText("");
            }
        });
        p4.add(b5);
        p1.add(p6);
        p1.add(p4);
        p5 = new JPanel();
        p5.setVisible(false);
        p5.setBounds(0,100,680,568);
        p5.setLayout(null);
        JLabel l3 = new JLabel("Email");
        l3.setBounds(25,100,100,20);
        p5.add(l3);
        l3.setForeground(Color.red);
        t3 = new JTextField();
        t3.setBounds(125,100,300,20);
        p5.add(t3);
        JLabel l4 = new JLabel("Password");
        l4.setBounds(25,150,100,20);
        l4.setForeground(Color.red);
        p5.add(l4);
        t4 = new JPasswordField();
        t4.setBounds(125,150,300,20);
        p5.add(t4);
        t4.setEchoChar('*');
        c2 = new JCheckBox("Show Password");
        c2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (c2.isSelected())
                    t4.setEchoChar((char)0);
                else
                    t4.setEchoChar('*');
            }
        });
        c2.setBounds(450,150,125,20);
        p5.add(c2);
        b6 = new JButton("REGISTER");
        b6.setBounds(25,200,100,20);
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String em = t3.getText();
                String pwd = new String(t4.getPassword());
                Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                if(!em.equals("")) {
                    if (!pwd.equals("")) {
                        if (isemail(em)) {
                            try (MongoClient mongoClient = MongoClients.create(s)) {
                                MongoCollection<Document> record = mongoClient.getDatabase("OpenTheatre").getCollection("userlogin");
                                if (a.findregister(record, em, pwd)) {
                                    a.insertuser(record, em, pwd);
                                    JOptionPane.showMessageDialog(null, "Reigistration Success "+em, "Success", JOptionPane.INFORMATION_MESSAGE, icon1);
                                }
                                else
                                    JOptionPane.showMessageDialog(null, "Email Id already exists","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                            }
                        }
                        else
                            JOptionPane.showMessageDialog(null, "Enter Valid Email Id","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Enter the Password","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                }
                else
                    JOptionPane.showMessageDialog(null, "Enter the Email Id","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                t3.setText("");
                t4.setText("");
            }
        });
        p5.add(b6);
        p1.add(p5);
        f.add(p1);
        p2  = new JPanel();
        p2.setBounds(680,0,680,668);
        p2.setVisible(false);
        p2.setLayout(null);
        JLabel l5 = new JLabel("Email");
        l5.setBounds(25,100,100,20);
        p2.add(l5);
        t5 = new JTextField();
        t5.setBounds(125,100,300,20);
        p2.add(t5);
        JLabel l6 = new JLabel("Password");
        l6.setBounds(25,150,100,20);
        p2.add(l6);
        t6 = new JPasswordField();
        t6.setBounds(125,150,300,20);
        p2.add(t6);
        t6.setEchoChar('*');
        c3 = new JCheckBox("Show Password");
        c3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (c3.isSelected())
                    t6.setEchoChar((char)0);
                else
                    t6.setEchoChar('*');
            }
        });
        c3.setBounds(450,150,125,20);
        p2.add(c3);
        b7 = new JButton("LOGIN");
        b7.setBounds(25,200,100,20);
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String em = t5.getText();
                String pwd = new String(t6.getPassword());
                Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                if(!em.equals("") ){
                    if(!pwd.equals("")){
                            try (MongoClient mongoClient = MongoClients.create(s)) {
                            MongoCollection<Document> record = mongoClient.getDatabase("OpenTheatre").getCollection("adminlogin");
                            if (a.findlogin(record, em, pwd)) {
                                JOptionPane.showMessageDialog(null, "Login Success " + em, "Success", JOptionPane.INFORMATION_MESSAGE, icon1);
                                f.setVisible(false);
                                new Second_Admin(em);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Invalid Password / Email Id", "Password Reseted", JOptionPane.INFORMATION_MESSAGE, icon2);
                                b9 = new JButton("FORGOT PASSWORD");
                                b9.setBounds(225,200,200,20);
                                b9.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        int code = 0;
                                        int n = (int) (Math.random() * 9000 + 1000);
                                        Integer[] codes = {n, (int) (Math.random() * 9000 + 1000), (int) (Math.random() * 9000 + 1000)};
                                        Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                                        try (MongoClient mongoClient = MongoClients.create(s)) {
                                            MongoCollection<Document> record = mongoClient.getDatabase("OpenTheatre").getCollection("adminlogin");
                                            if (a.findemail(record, em))
                                            {
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
                                                    message.setSubject("CODE FOR CHANGING PASSWORD");
                                                    message.setText("4 digit code for reset password : "+n);
                                                    Transport.send(message);
                                                    int x = JOptionPane.showOptionDialog(null, "Click the code which is sent to your mail.if not available check in Spam : ", "Reset Password", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icon1, codes, codes[0]);
                                                    code = codes[x];
                                                } catch (MessagingException ae) {
                                                    JOptionPane.showMessageDialog(null,ae, "Gmail Error ", JOptionPane.ERROR_MESSAGE, icon3);
                                                }
                                                if (code==n) {
                                                    JLabel l9 = new JLabel("New Password");
                                                    l9.setBounds(25,25,200,20);
                                                    p7.add(l9);
                                                    t9 = new JPasswordField();
                                                    t9.setBounds(225,25,300,20);
                                                    p7.add(t9);
                                                    JLabel l10 = new JLabel("Confirm New Password");
                                                    l10.setBounds(25,75,200,20);
                                                    p7.add(l10);
                                                    t10 = new JPasswordField();
                                                    t10.setBounds(225,75,300,20);
                                                    p7.add(t10);
                                                    t9.setEchoChar('*');
                                                    t10.setEchoChar('*');
                                                    c4 = new JCheckBox("Show Password");
                                                    c4.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            if (c4.isSelected())
                                                                t9.setEchoChar((char)0);
                                                            else
                                                                t9.setEchoChar('*');
                                                        }
                                                    });
                                                    c4.setBounds(225,50,125,20);
                                                    p7.add(c4);
                                                    c5 = new JCheckBox("Show Password");
                                                    c5.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            if (c5.isSelected())
                                                                t10.setEchoChar((char)0);
                                                            else
                                                                t10.setEchoChar('*');
                                                        }
                                                    });
                                                    c5.setBounds(225,100,125,20);
                                                    p7.add(c5);
                                                    b11 = new JButton("Set Password");
                                                    b11.setBounds(25,150,200,20);
                                                    b11.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            String newp = new String(t9.getPassword());
                                                            String cnewp = new String(t10.getPassword());
                                                            Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                                                            try (MongoClient mongoClient = MongoClients.create(s)) {
                                                                MongoCollection<Document> record = mongoClient.getDatabase("OpenTheatre").getCollection("adminlogin");
                                                                if (!newp.equals("") && ! cnewp.equals("") && newp.equals(cnewp)) {
                                                                    String op = a.findpass(record, em);
                                                                    if (!newp.equals(op))
                                                                    {
                                                                        record.updateOne(new Document("email",em), Updates.set("password",newp));
                                                                        JOptionPane.showMessageDialog(null,"Password Updated .", "Password Reseted", JOptionPane.INFORMATION_MESSAGE, icon1);
                                                                        p7.setVisible(false);
                                                                    }
                                                                    else
                                                                        JOptionPane.showMessageDialog(null,"Enter A New Password ","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                                                                    t9.setText("");
                                                                    t10.setText("");
                                                                }
                                                                else
                                                                    JOptionPane.showMessageDialog(null,"Enter the Password Correctly","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                                                                t9.setText("");
                                                                t10.setText("");
                                                            }
                                                        }
                                                    });
                                                    t9.setText("");
                                                    t10.setText("");
                                                    p7.add(b11);
                                                    p7.add(l12);
                                                    p7.setVisible(true);
                                                }
                                                else
                                                    JOptionPane.showMessageDialog(null,"Wrong Code Try Again","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                                            }
                                            else
                                                JOptionPane.showMessageDialog(null,"Try Existing Email","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                                            }
                                        }
                                });
                                p2.add(b9);
                            }
                            }
                        }
                    else
                        JOptionPane.showMessageDialog(null, "Enter the password","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                }
                else
                    JOptionPane.showMessageDialog(null, "Enter Email Id","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                t5.setText("");
                t6.setText("");
            }
        });
        p2.add(b7);
        p1.add(p7);
        p1.add(p2);
        l = new JLabel(bg1);
        l.setBounds(0,0,1360,100);
        p.add(l);
        l = new JLabel(bg2);
        l.setBounds(0,0,1360,668);
        p1.add(l);
        l = new JLabel(bg3);
        l.setBounds(0,0,680,668);
        p2.add(l);
        l = new JLabel(bg4);
        l.setBounds(0,0,680,100);
        p3.add(l);
        l = new JLabel(bg5);
        l.setBounds(0,0,680,568);
        p4.add(l);
        l = new JLabel(bg5);
        l.setBounds(0,0,680,568);
        p5.add(l);
        l11 = new JLabel(bg6);
        l11.setBounds(0,0,680,334);
        l12 = new JLabel(bg7);
        l12.setBounds(0,0,680,334);
    }
    private boolean isemail(String em) {
        boolean isValid = false;
        try {
            InternetAddress internetAddress = new InternetAddress(em);
            internetAddress.validate();
            isValid = true;
        } catch (AddressException e) {
            isValid = false;
        }
        return isValid;
    }
    public static void main(String x[])
    {
       new First();
    }
}