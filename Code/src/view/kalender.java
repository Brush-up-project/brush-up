package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class kalender extends JPanel implements ItemListener{

	JPanel p1, p2;
    JComboBox month;
    JComboBox year;
    int days[]={31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    String weekdays[] = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public kalender()
    {
        super();
        p1 = new JPanel();
        month = new JComboBox();
        for(int i=0;i< months.length;i++)
        {
            month.addItem(months[i]);
        }
        month.addItemListener(this);
        year = new JComboBox();
        for(int i=1980;i<=2099;i++)
        {
            year.addItem(i);
        }
        year.addItemListener(this);
        p1.add(month);
        p1.add(year);
        p1.setPreferredSize(new Dimension(1200, 30));
        p2 = new JPanel();
        p2.setLayout(new GridLayout(0,7));
        p2.setPreferredSize(new Dimension(1100, 600));
        Date date = new Date();
        drawCalendar(months[date.getMonth()], (1900+date.getYear()));
        year.setSelectedItem((1900+date.getYear()));
        month.setSelectedItem(months[date.getMonth()]);
        add(p1);
        add(p2);   
        
        
    }
    
    public void itemStateChanged(ItemEvent e)
    {
        if(e.getStateChange() == ItemEvent.SELECTED)
        {
            drawCalendar((String)month.getSelectedItem(), (Integer)year.getSelectedItem());
        }
    }
    
    
    
    public void drawCalendar(String inputMonth, int inputYear)
    {
        p2.removeAll();
        for(int i=0;i< weekdays.length;i++)
        {
            JLabel label = new JLabel(weekdays[i]);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            p2.add(label);
        }
        Date date = new Date("01-"+inputMonth+"-"+inputYear);
        int noOfDaysInMonth = days[date.getMonth()];
        if(date.getYear()%4==0 && date.getMonth()==1)
        {
            noOfDaysInMonth = 29;
        }
        
        

        for(int i=1, day=1;day<=noOfDaysInMonth;i++)
        {
            for(int j=0;j<7;j++)
            {
                if(day==1)
                {
                    if(j==date.getDay())
                    {
                        JLabel label = new JLabel(String.valueOf(day));
                        label.setHorizontalAlignment(SwingConstants.RIGHT);
                        label.setVerticalAlignment(SwingConstants.TOP);
                        label.setBorder(BorderFactory.createLineBorder(Color.gray));                         
                        p2.add(label);
                        day++;
                    }
                    else
                    {
                        JLabel label = new JLabel("");
                        p2.add(label);
                    }
                }
                else
                {
                    JLabel label = new JLabel(String.valueOf(day));
                    label.setHorizontalAlignment(SwingConstants.RIGHT);
                    label.setVerticalAlignment(SwingConstants.TOP);
                    label.setBorder(BorderFactory.createLineBorder(Color.gray));  
                    final String dateNumber = label.getText();
                    final String monthText = (String) month.getSelectedItem();
                    final int monthNumber = (int) month.getSelectedIndex();
                    final int yearNumber = (int) year.getSelectedItem();
                    final String uniqueID = dateNumber+monthNumber+yearNumber;
                    label.addMouseListener(new MouseAdapter()
                    {
                        public void mouseClicked(MouseEvent evt)
                        {
                            {
                                new calendarEvent();
                            }
                        }
                    });
                    p2.add(label);
                    day++;
                }
                if(day>noOfDaysInMonth)
                {
                    break;
                }
                
            }
            
        }
        p2.validate();
    }
    
}
