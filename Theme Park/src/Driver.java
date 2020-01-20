/************************************************************************************
 *  Program:      Driver.java                                                       *
 *  Date:         May  1,  2019                                                     *
 *  Purpose:      Create the GUI and run threads for the themepark loop and GUI     *
 *  Author:       Tanyaradzwa Matasva,  University  of  Bridgeport                  *
 *  Course:       Introduction  to  Computing  2  with  Java                        *
 *  Assignment:   Final Project : Theme Park Simulation                             *
 ************************************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Driver extends JFrame implements ActionListener {
     static JPanel panel;
    private JButton btn;
    private JLabel headers;
    private String headersText;

    //private boolean startMode;

    private static ThemePark themePark=new ThemePark();
    //static JLabel test=new JLabel();

   private static Stats stats=new Stats();



    public Driver(){
        /*
        set up the JFrame
         */
        this.setTitle("Theme Park Simulator");
        this.setSize(800,800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);

        /*
        set up the JPanel panel that will hold a TextField with theme park statistics
         */
        panel=new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,800,800);
        panel.setBackground(Color.black);

        /*
        set up the JLabels that will indicate the data shown in the text field
         */
        headersText=String.format("%-20s%-15s%-15s%-15s%-20s%-20s%-20s","Ride Number:","Ride Time","Occupied:","Line Size:","Fast Pass","Guest Number:","Popularity %:");
        headers=new JLabel(headersText);
        headers.setForeground(Color.white);
        headers.setBounds(10,70,780,20);

        /*
        set up the text area that contains all the stats from the ThemePark class and the Ride class
         */


        /*
        set up button to start the run() method from the theme park class and start the clock
         */
        btn=new JButton("Start");
        btn.addActionListener(this);
        btn.setBounds(350,10,100,50);
        btn.setBackground(Color.darkGray);
        btn.setForeground(Color.white);

        /*
        stack components on top inside the JFrame
         */
        panel.add(btn);
        panel.add(headers);
        panel.add(stats);
        this.add(panel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Driver();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn){
        Thread t1= new Thread(() -> themePark.run());
            Thread t2= new Thread(() -> {
                while(themePark.isOpen()){
                    stats.repaint();
                }
            });

            t1.start();
            t2.start();
            btn.setEnabled(false);
            btn.setText("");
        }
    }
    public static class Stats extends JPanel{
        public Stats(){
            setBounds(10,100,780,600);
            setBackground(Color.darkGray);
            setForeground(Color.white);
            setLayout(null);
        }

        public void paint(Graphics g){
            super.paint(g);
            g.setColor(Color.white);
            g.drawString(themePark.displayStats(0),0,10);
            g.drawString(themePark.displayStats(1),0,30);
            g.drawString(themePark.displayStats(2),0,50);
            g.drawString(themePark.displayStats(3),0,70);
            g.drawString(themePark.displayStats(4),0,90);
            g.drawString(themePark.displayStats(5),0,110);
            g.drawString(themePark.displayStats(6),0,130);
            g.drawString(themePark.displayStats(7),0,150);
            g.drawString(themePark.displayStats(8),0,170);
            g.drawString(themePark.displayStats(9),0,190);
            g.drawString("Elapsed Time  : "+themePark.getTime(),0,230);
            g.drawString("Total Guests  : "+themePark.getTotalGuests(),0,250);
            g.drawString("Guests Served : "+themePark.getServedGuests(),0,270);
        }
    }

}
