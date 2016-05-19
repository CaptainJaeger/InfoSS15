package praktikum.versuch4;
/* Gruppe 14
 * Autor: Bilal Aydin
 * Aufgabe 4.2 Taschenrechner-GUI
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.text.DecimalFormat;

@SuppressWarnings("serial")
class TaschenrechnerFrame extends JFrame implements ActionListener {
  private JTextField eingabeFeld1, eingabeFeld2, ausgabeFeld;
  private JLabel label;
  private JRadioButton[] radioArray;
  private ButtonGroup radioGroup;
  String plusChoice = "plusTrue", minusChoice = "minusFalse";   //Kontrolle, welche Aktion auszufuehren werden soll bei 'Berechnen'
                                    //Standard auf 'plus' setzen
  public TaschenrechnerFrame(String s) {
    super(s);
    setTitle("Einfacherer Taschenrechner");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = getContentPane();
    c.setLayout(new GridLayout(6,1));
    c.setForeground(Color.black);
    
    /*---- Panel f�r Textfelder + Auswahllist ----*/
    JPanel p1 = new JPanel();
    p1.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));
    
    
    /*---- Frame und Label f�r 1. Eingabe ----*/
    label = new JLabel("1. Eingabe:");
    label.setFont(new Font("Arial",Font.BOLD,24));
    label.setForeground(Color.black);
    p1.add(label);
    
    eingabeFeld1 = new JTextField(10);
    eingabeFeld1.setHorizontalAlignment(JTextField.RIGHT);
    eingabeFeld1.setFont(new Font("Arial",Font.BOLD,24));
    eingabeFeld1.setForeground(Color.blue);
    p1.add(eingabeFeld1);
    
    /*---- Frame und Label f�r 2. Eingabe ----*/
    label = new JLabel("2. Eingabe:");
    label.setFont(new Font("Arial",Font.BOLD,24));
    label.setForeground(Color.black);
    p1.add(label);
    
    eingabeFeld2 = new JTextField(10);
    eingabeFeld2.setHorizontalAlignment(JTextField.RIGHT);
    eingabeFeld2.setFont(new Font("Arial",Font.BOLD,24));
    eingabeFeld2.setForeground(Color.blue);
    p1.add(eingabeFeld2);
    
    
    /*---- Frame und Label f�r Ausgabe ----*/
    label = new JLabel("Ausgabe:");
    label.setFont(new Font("Arial",Font.BOLD,24));
    label.setForeground(Color.black);
    p1.add(label);
    
    ausgabeFeld = new JTextField(10);
    ausgabeFeld.setFont(new Font("Arial",Font.BOLD,24));
    ausgabeFeld.setForeground(Color.blue);
    p1.add(ausgabeFeld);

    /*---- RadioButtons erzeugen und anpassen ----*/
    radioArray = new JRadioButton[2];
    radioArray[0] = new JRadioButton("plus", true);
    radioArray[1] = new JRadioButton("minus");
    radioGroup = new ButtonGroup();
    
    for (int i=0;i<2;i++) {
        radioArray[i].setFont(new Font("Arial",Font.BOLD,20));
        radioArray[i].setForeground(Color.blue);
        // Einfuegen der Radiobuttons in die Radiogroup:
        // Stellt sicher, da� jeweils nur einer von ihnen ausgew�hlt sein kann
        radioGroup.add(radioArray[i]);
        p1.add(radioArray[i]);
        c.add(p1, BorderLayout.NORTH);
       }
    RadioListener lisRadio = new RadioListener();
    for (int i=0;i<2;i++)
        radioArray[i].addItemListener(lisRadio);
    
    /*---- Buttons an addButton �bergeben ----*/
    JPanel p2 = new JPanel();
    addButton(p2, "Beenden");
    addButton(p2, "C");
    addButton(p2, "Berechnen");
    c.add(p2,  BorderLayout.SOUTH);
    

    pack();   //Komponenten an Fenster anpassen
    setLocation(100,100);
    setVisible(true);
  }
  /*---- Buttons erzeugen, anpassen und ActionListener �bergeben ----*/
  private void addButton(JPanel panel, String s) {
    JButton b = new JButton(s);
    b.setFont(new Font("Arial", Font.BOLD,20));
    b.setForeground(Color.black);
    b.addActionListener(this);
    panel.add(b);
  }
  
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();
    System.out.println(cmd);    //Kontrolle ob Taste gedr�ckt wurde
    int zahl1, zahl2;
    
    if(cmd.equals("Berechnen")) {
      if(plusChoice.equals("plusTrue")) {
      try {
         zahl1 = Integer.parseInt(eingabeFeld1.getText());
         zahl2 = Integer.parseInt(eingabeFeld2.getText());
        berechnen(zahl1, zahl2, plusChoice);
      } catch(NumberFormatException ex) {
        zahl1= 0;
        zahl2=0;
        eingabeFeld1.setText("");
        eingabeFeld2.setText("");
      }
      
     }
      else if(minusChoice.equals("minusTrue")) {
       try {
          zahl1 = Integer.parseInt(eingabeFeld1.getText());
           zahl2 = Integer.parseInt(eingabeFeld2.getText());
          berechnen(zahl1, zahl2, minusChoice);
        } catch(NumberFormatException ex) {
          zahl1= 0;
          zahl2=0;
          eingabeFeld1.setText("");
          eingabeFeld2.setText("");
        }
     }
    }
    else if(cmd.equals("C")) {    //Alles leer setzen
      zahl1= 0;
      zahl2=0;
      eingabeFeld1.setText("");
      eingabeFeld2.setText("");
      ausgabeFeld.setText("");
      radioArray[0].setSelected(true);  //Und Auswahl zuruecksetzen
      radioArray[1].setSelected(false);
      
    } else if(cmd.equals("Beenden")) {
      System.exit(1);
    }
  }
  /*---- Rechnung durchfuehren ----*/
  private void berechnen(int wert1, int wert2, String Choice) {
    int erg;
    if(radioArray[0].isSelected()) {
      erg = wert1 + wert2;
      ausgabeFeld.setText(formatieren(erg));
    }
    else if(radioArray[1].isSelected()) {
      erg = wert1 - wert2;
      ausgabeFeld.setText(formatieren(erg));
    }
  }
  /*---- Integer zu String umwandeln ----*/  
  private static String formatieren(int x) {
      DecimalFormat f = new DecimalFormat("0");
      return f.format(x);
    }
  
  
  class RadioListener implements ItemListener {
  public void itemStateChanged(ItemEvent e) {

      if (radioArray[0].isSelected()) {
        plusChoice = "plusTrue";
      }     

      if (radioArray[1].isSelected()){
        minusChoice = "minusTrue";
      }     
    }
  }
} 