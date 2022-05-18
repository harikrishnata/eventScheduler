import java.io.File;  
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class is the title screen of the game
 */
public class TitleScreen extends JFrame implements ActionListener 
{
    public static File input_file;

    private JButton b1 = new JButton("CHOOSE FILE");   //buttons in title screen
    private JSpinner breakInput;
    private JLabel b2 = new JLabel("Length of gap(in minutes)");
    /**
     * Constructor for the title screen
    */
    public TitleScreen() 
    {
        JTextField t = new JTextField("FESTIVAL SCHEDULER");         //setting elements of title screen
        Font font1 = new Font("Big Carlson", Font.BOLD, 40);
        t.setFont(font1);
        t.setEditable(false);
        t.setHorizontalAlignment(JTextField.CENTER);
        JTextArea info = new JTextArea("\n\n    1) Enter the break time time in between events\n\n\n    2) Select a text file containing your events in the format\n\n                                                                                    eg:\n          Main Event,Duration,Priority,Starting Time            Taylor Swift,120,1,1800\n          Another Event,Duration,Priority                            Harry Styles,90,2      \n          Another Event,Duration,Priority                            Years & Years,90,3\n          . . . .     \n\n\n    3) Your timetable will be stored in a CSV file titled timetable.CSV");  
                                                                                                                  // Another Event,Duration,Priority,Starting Time                                                                                       Years & Years,90,3                                              
        Font font3 = new Font("Big Carlson", Font.BOLD, 20);
        info.setFont(font3);
        info.setEditable(false);

        Font font2 = new Font("Big Carlson", Font.PLAIN, 20);
        b1.setFont(font2);
        SpinnerModel values= new SpinnerNumberModel(0,0,60,1);
        JPanel outer = new JPanel();
        JPanel inner = new JPanel();
        BorderLayout outLayout = new BorderLayout();
        breakInput = new JSpinner(values);
        outer.setLayout(outLayout);
        FlowLayout inLayout = new FlowLayout();
        inner.setLayout(inLayout);
        inner.add(b2);
        inner.add(breakInput);
        inner.add(b1);
        b1.addActionListener(this);
        outer.add("South", inner);
        outer.add("Center", info);
        outer.add("North", t);
        this.setContentPane(outer);
        this.setSize(900, 550);
        this.setTitle("FESTIVAL SCHEDULER");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * Determines what the buttons do when they are pressed
    */
    public void actionPerformed(ActionEvent e)
    {
        this.dispose();                 //title screen gets closed when a button is clicked

        if (e.getSource() == b1)        
        {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
                driver.event_file = fileChooser.getSelectedFile();
            }
            driver.set_breakDuration((int) breakInput.getValue());
            driver.makeTimetable();
        }
    }
}