import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class is the title screen of the program
 */
public class TitleScreen extends JFrame implements ActionListener 
{
    //elements in title screen
    private JButton chooser = new JButton("CHOOSE FILE");   //button to choose file
    private JSpinner breakInput;    //Jspinner to choose length of break
    private JLabel chooser_info = new JLabel("Length of gap(in minutes)");
    /**
     * Constructor for the title screen
    */
    public TitleScreen() 
    {
        //setting elements of title screen
        //main title
        JTextField heading = new JTextField("FESTIVAL SCHEDULER");        
        Font font1 = new Font("Big Carlson", Font.BOLD, 40);
        heading.setFont(font1);
        heading.setEditable(false);
        heading.setHorizontalAlignment(JTextField.CENTER);
        //Instructions for uploading file
        JTextArea info = new JTextArea("\n\n    1) Enter the break time time in between events\n\n\n    2) Select a text file containing your events in the format\n\n                                                                                    eg:\n          Main Event,Duration,Priority,Starting Time            Taylor Swift,120,1,1800\n          Another Event,Duration,Priority                            Harry Styles,90,2      \n          Another Event,Duration,Priority                            Years & Years,90,3\n          . . . .     \n\n\n    3) Your timetable will be stored in a CSV file titled timetable.CSV");  
        Font font2 = new Font("Big Carlson", Font.BOLD, 20);
        info.setFont(font2);
        info.setEditable(false);
        Font font3 = new Font("Big Carlson", Font.PLAIN, 20);
        chooser.setFont(font3);
        //Jspinner for setting break
        SpinnerModel values= new SpinnerNumberModel(0,0,60,1);
        JPanel outer = new JPanel();
        JPanel inner = new JPanel();
        BorderLayout outLayout = new BorderLayout();
        breakInput = new JSpinner(values);
        //setting layouts for title screen
        outer.setLayout(outLayout);
        FlowLayout inLayout = new FlowLayout();

        inner.setLayout(inLayout);
        inner.add(chooser_info);
        inner.add(breakInput);
        inner.add(chooser);
        chooser.addActionListener(this);

        outer.add("South", inner);
        outer.add("Center", info);
        outer.add("North", heading);

        this.setContentPane(outer);
        this.setSize(900, 550);
        this.setTitle("FESTIVAL SCHEDULER");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * Determines what to do when the file is selected
    */
    public void actionPerformed(ActionEvent e)
    {
        this.dispose();                 //title screen gets closed when a button is clicked

        if (e.getSource() == chooser)        
        {
            JFileChooser fileChooser = new JFileChooser();      //intitating file chooser
            int option = fileChooser.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
                driver.set_event_file(fileChooser.getSelectedFile());   //sets event_file 
            }
            driver.set_breakDuration((int) breakInput.getValue());  //sets break value
            driver.makeTimetable();                     //makes timetable with input file
        }
    }
}