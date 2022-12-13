import com.mongodb.client.*;
import org.bson.Document;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Second_Admin implements ActionListener {
    JFrame f;
    JPanel p1,p2,p3,p4,p5,p6;
    JButton b1,b2,b3,b4,b5,b6,b7,b11,b9,b10;
    JTextField t1,t2,t3,t4,t5,t6;
    JLabel l10,l11,l;
    Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("movie.png"));
    ImageIcon icon1 = new ImageIcon(getClass().getClassLoader().getResource("icon1.png"));
    ImageIcon icon2 = new ImageIcon(getClass().getClassLoader().getResource("icon2.png"));
    ImageIcon bg1 = new ImageIcon(getClass().getClassLoader().getResource("b_2.jpg"));
    ImageIcon bg2 = new ImageIcon(getClass().getClassLoader().getResource("b1_2.jpg"));
    ImageIcon bg3 = new ImageIcon(getClass().getClassLoader().getResource("b2_2.jpg"));
    ImageIcon bg4 = new ImageIcon(getClass().getClassLoader().getResource("b3_2.jpg"));
    ImageIcon bg5 = new ImageIcon(getClass().getClassLoader().getResource("b4_2.jpg"));
    ImageIcon bg6 = new ImageIcon(getClass().getClassLoader().getResource("b5_2.jpg"));
    JComboBox<String> co1,co2;
    JButton[][] t = new JButton[6][7];
    JTextField[] d = new JTextField[7];
    int realYear, realMonth, realDay, currentYear, currentMonth,mt,yr,cm,cou=0;
    String get(String date)
    {
        String date1[]=date.split("/");
        if(date1[0].length()==1)
            date1[0]="0"+date1[0];
        if(date1[1].length()==1)
            date1[1]="0"+date1[1];
        date=date1[0]+date1[1]+date1[2];
        return date;
    }
    void refreshCalendar(int month, int year)
    {
        String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int nod, som;
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        b9.setEnabled(true);
        b10.setEnabled(true);
        if (month == cm && year == realYear) {
            cou = 0;
            b9.setEnabled(false);
        }
        if (cou==3)
            b10.setEnabled(false);
        l10.setText(months[month]);
        mt=month+1;
        yr=year;
        for (int i=0; i<6; i++)
            for (int j=0; j<=6; j++)
                t[i][j].setText("");
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        som = cal.get(GregorianCalendar.DAY_OF_WEEK);
        for (int i=1; i<=nod; i++)
        {
            int row = (i+som-2)/7;
            int column  =  (i+som-2)%7;
            if (i<10)
                t[row][column].setText("0"+i);
            else
                t[row][column].setText(""+i);
            if(i<realDay && month==cm)
                t[row][column].setEnabled(false);
            if(month!=cm)
                t[row][column].setEnabled(true);
        }
    }
    Second_Admin(final String s) {
        f = new JFrame();
        f.setResizable(false);
        f.setSize(1360, 768);
        f.setTitle("Admin " + s);
        f.addWindowListener(new window());
        f.setLayout(null);
        f.setVisible(true);
        f.setIconImage(icon);
        final Database a = new Database();
        final String S = "mongodb+srv://Bharath:Bharath123456@opentheatre.sgp39.mongodb.net/OpenTheatre?retryWrites=true&w=majority";
        p1 = new JPanel();
        p1.setVisible(true);
        p1.setBounds(0, 0, 1360, 100);
        p1.setLayout(null);
        b1 = new JButton("ADD");
        b1.setBounds(400, 25, 100, 50);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                co1.removeAllItems();
                co2.removeAllItems();
                p3.setVisible(true);
                p4.setVisible(false);
                p5.setVisible(false);
            }
        });
        p1.add(b1);
        co1 = new JComboBox();
        co1.setBounds(125, 75, 100, 20);
        b2 = new JButton("VIEW");
        b2.setBounds(550, 25, 100, 50);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                co1.removeAllItems();
                co2.removeAllItems();
                p4.setVisible(true);
                co1.addItem("");
                Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                try (MongoClient mongoClient = MongoClients.create(S)) {
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
                p3.setVisible(false);
                p5.setVisible(false);
            }
        });
        p1.add(b2);
        co2 = new JComboBox();
        co2.setBounds(125, 75, 100, 20);
        b3 = new JButton("DELETE");
        b3.setBounds(700, 25, 100, 50);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                co1.removeAllItems();
                co2.removeAllItems();
                p5.setVisible(true);
                co2.addItem("");
                Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                try (MongoClient mongoClient = MongoClients.create(S)) {
                    MongoIterable<String> Db = a.getDate(mongoClient);
                    for (String name : Db) {
                        try
                        {
                            Integer.parseInt(name);
                            name = name.substring(0,2)+"/"+name.substring(2,4)+"/"+name.substring(4,name.length());
                            co2.addItem(name);
                        }
                        catch (NumberFormatException sd){
                        }
                    }
                }
                p3.setVisible(false);
                p4.setVisible(false);
            }
        });
        p1.add(b3);
        b4 = new JButton("CLOSE");
        b4.setBounds(850, 25, 100, 50);
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        p1.add(b4);
        p2 = new JPanel();
        p2.setVisible(true);
        p2.setBounds(0, 100, 1360, 668);
        p2.setLayout(null);
        p3 = new JPanel();
        p3.setLayout(null);
        p3.setBounds(0, 0, 400, 668);
        JLabel l1 = new JLabel("Movie");
        l1.setBounds(25, 25, 100, 20);
        l1.setForeground(Color.white);
        p3.add(l1);
        t1 = new JTextField();
        t1.setBounds(125, 25, 100, 20);
        p3.add(t1);
        JLabel l2 = new JLabel("Theatre");
        l2.setBounds(25, 75, 100, 20);
        l2.setForeground(Color.white);
        p3.add(l2);
        t2 = new JTextField();
        t2.setBounds(125, 75, 100, 20);
        p3.add(t2);
        JLabel l3 = new JLabel("Time");
        l3.setBounds(25, 125, 100, 20);
        l3.setForeground(Color.white);
        p3.add(l3);
        t3 = new JTextField();
        t3.setBounds(125, 125, 100, 20);
        p3.add(t3);
        l11 =  new JLabel("Date");
        l11.setBounds(25, 175, 100, 20);
        l11.setForeground(Color.white);
        p3.add(l11);
        t6 = new JTextField();
        t6.setBounds(125, 175, 100, 20);
        p3.add(t6);
        t6.setEditable(false);
        b11 = new JButton();
        b11.setBounds(250, 175, 20, 20);
        b11.setBackground(Color.WHITE);
        p3.add(b11);
        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p6.setVisible(true);
            }
        });
        b5 = new JButton("ADD");
        b5.setBounds(125, 225, 100, 25);
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mo = t1.getText();
                String th = t2.getText();
                String ti = t3.getText(), id;
                int n = (int) (Math.random() * 9000 + 1000);
                switch (ti) {
                    case "19.00":
                        id = n + "NN1";
                        break;
                    case "22.30":
                        id = n + "NN2";
                        break;
                    default:
                        id = "";
                        break;
                }
                if (!mo.equals("")) {
                    if (!th.equals("")) {
                        if (!ti.equals("")) {
                            if (!id.equals("")) {
                                Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                                try (MongoClient mongoClient = MongoClients.create(S)) {
                                    String s = get(t6.getText());
                                    MongoCollection<Document> rec = mongoClient.getDatabase(s).getCollection("MovieAdmin");
                                    if (a.findmovie(rec, mo, th, ti)) {
                                        a.insertMovie(rec, mo, th, ti, id, 40);
                                        MongoCollection<Document> rec1 = mongoClient.getDatabase(s).getCollection("Tickets");
                                        a.insertTickets(rec1,id);
                                        JOptionPane.showMessageDialog(null, "Don't Forget The Id : " + id,"Success", JOptionPane.INFORMATION_MESSAGE, icon1);
                                    } else
                                        JOptionPane.showMessageDialog(null, "Field Already exist","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                                }
                            } else
                                JOptionPane.showMessageDialog(null, "Enter The Correct Time","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                        } else
                            JOptionPane.showMessageDialog(null, "Enter the Time","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                    } else
                        JOptionPane.showMessageDialog(null, "Enter the Theatre","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                } else
                    JOptionPane.showMessageDialog(null, "Enter the Movie","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                t1.setText("");
                t2.setText("");
                t3.setText("");
            }
        });
        p3.add(b5);
        p4 = new JPanel();
        p4.setLayout(null);
        p4.setBounds(450, 0, 400, 668);
        JLabel l4 = new JLabel("NO");
        l4.setBounds(25, 25, 100, 20);
        l4.setForeground(Color.white);
        p4.add(l4);
        t4 = new JTextField();
        t4.setBounds(125, 25, 100, 20);
        p4.add(t4);
        JLabel l12 = new JLabel("Date");
        l12.setBounds(25, 75, 100, 20);
        p4.add(l12);
        l12.setForeground(Color.white);
        p4.add(co1);
        b6 = new JButton("VIEW");
        b6.setBounds(125, 175, 100, 25);

        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String no = t4.getText();
                String s = (String) co1.getSelectedItem();
                if (!s.equals("")) {
                    Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                    try (MongoClient mongoClient = MongoClients.create(S)) {
                        s = s.replaceAll("/", "");
                        MongoCollection<Document> rec = mongoClient.getDatabase(s).getCollection("MovieAdmin");
                        Document doc = a.findNo(rec, no);
                        if (doc == null)
                            JOptionPane.showMessageDialog(null, "Enter Correct Id","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                        else
                            JOptionPane.showMessageDialog(null,"Details are Displayed" +
                                    "\nMovie                   :  "+ doc.getString("Movie")+
                                    "\nTheatre               : " + doc.getString("Theatre")+
                                    "\nTime                     : " + doc.getString("Time")+
                                    "\nSlots Available : " + doc.getInteger("Slots"),"Success", JOptionPane.INFORMATION_MESSAGE, icon1);
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Select Correct Date","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                t4.setText("");
                co1.removeAllItems();
            }
        });
        p4.add(b6);
        p5 = new JPanel();
        p5.setLayout(null);
        p5.setBounds(900, 0, 400, 668);
        JLabel l5 = new JLabel("NO");
        l5.setBounds(25, 25, 100, 20);
        l5.setForeground(Color.white);
        p5.add(l5);
        l5.setForeground(Color.white);
        t5 = new JTextField();
        t5.setBounds(125, 25, 100, 20);
        p5.add(t5);
        JLabel l13 = new JLabel("Date");
        l13.setForeground(Color.white);
        l13.setBounds(25, 75, 100, 20);
        p5.add(l13);
        p5.add(co2);
        b7 = new JButton("DELETE");
        b7.setBounds(125, 175, 100, 25);
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String no = t5.getText();
                String s = (String) co2.getSelectedItem();
                if (!s.equals("")) {
                    s = s.replaceAll("/", "");
                    Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
                    try (MongoClient mongoClient = MongoClients.create(S)) {
                        MongoCollection<Document> rec = mongoClient.getDatabase(s).getCollection("MovieAdmin");
                        MongoCollection<Document> rec1 = mongoClient.getDatabase(s).getCollection("Tickets");
                        Document doc = a.findNo(rec, no);
                        Document doc1 = a.findNo(rec1, no);
                        if (doc == null || doc1 == null)
                            JOptionPane.showMessageDialog(null, "Not Exist","Warning",JOptionPane.WARNING_MESSAGE,icon2);
                        else {
                            rec.deleteOne(doc);
                            if (rec.find().first()==null)
                                rec.drop();
                            rec1.deleteOne(doc1);
                            if (rec1.find().first()==null)
                                rec1.drop();
                            JOptionPane.showMessageDialog(null, "Deleted SuccessFully","Success", JOptionPane.INFORMATION_MESSAGE, icon1);
                        }
                    }
                }
                t5.setText("");
                co2.removeAllItems();
            }
        });
        p3.setVisible(false);
        p4.setVisible(false);
        p5.setVisible(false);
        p5.add(b7);
        p2.add(p3);
        p2.add(p4);
        p2.add(p5);
        f.add(p1);
        f.add(p2);
        p6 = new JPanel();
        p6.setVisible(false);
        p6.setBounds(0,275,400,350);
        p6.setLayout(null);
        l10 =  new JLabel("January");
        l10.setForeground(Color.white);
        b9 = new JButton("<<");
        b9.addActionListener(new B1());
        b10 = new JButton(">>");
        b10.addActionListener(new B2());
        l10.setBounds(175,25,100,20);
        for(int j=0;j<7;j++)
        {
            d[j] = new JTextField();
            d[j].setBounds((50*(j+1))-25,55,50,30);
            d[j].setEditable(false);
            d[j].setHorizontalAlignment(JTextField.CENTER);
            p6.add(d[j]);
            d[j].setForeground(Color.white);
            d[j].setBackground(Color.black);
        }
        for(int i=0;i<6;i++)
            for(int j=0;j<7;j++)
            {
                t[i][j] = new JButton();
                t[i][j].setBounds((50*(j+1))-25,(30*(i+2))+25,50,30);
                if(j==0 || j==6)
                    t[i][j].setBackground(new Color(255, 220, 220));
                else
                    t[i][j].setBackground(new Color(255, 255, 255));
                t[i][j].addActionListener(this);
                p6.add(t[i][j]);
            }
        p6.add(l10);
        b9.setBounds(25, 25, 50, 25);
        b10.setBounds(325, 25, 50, 25);
        p6.add(b9);
        p6.add(b10);
        GregorianCalendar cal = new GregorianCalendar();
        realDay = cal.get(GregorianCalendar.DAY_OF_MONTH);
        realMonth = cal.get(GregorianCalendar.MONTH);
        realYear = cal.get(GregorianCalendar.YEAR);
        currentMonth = realMonth;
        currentYear = realYear;
        cm=realMonth;
        t6.setText(realDay + "/" + (currentMonth+1) + "/" + currentYear);
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i=0; i<7; i++)
            d[i].setText(days[i]);
        refreshCalendar (realMonth, realYear);
        p3.add(p6);
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
        p4.add(l);
        l = new JLabel(bg5);
        l.setBounds(0,0,400,668);
        p5.add(l);
        l = new JLabel(bg6);
        l.setBounds(0,0,400,350);
        p6.add(l);
    }
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(!s.equals("")) {
            t6.setText(s + "/" + mt + "/" + yr);
            p6.setVisible(false);
        }
        else
            JOptionPane.showMessageDialog(null,"Not Allowed","Warning",JOptionPane.WARNING_MESSAGE,icon2);
    }
    class B1 implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            if (currentMonth == 0)
            {
                currentMonth = 11;
                currentYear -= 1;
            }
            else
                currentMonth -= 1;
            cou--;
            refreshCalendar(currentMonth, currentYear);
        }
    }
    class B2 implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            if (currentMonth == 11)
            {
                currentMonth = 0;
                currentYear += 1;
            }
            else
                currentMonth += 1;
            cou++;
            refreshCalendar(currentMonth, currentYear);
        }
    }
    /*public static void main(String[] ar) {
        new Second_Admin("bmkvs541@gmail.com");
    }*/
}