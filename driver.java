import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner;
import java.io.FileWriter;  
import java.io.IOException;
import java.io.BufferedWriter;


public class driver
{
    /**
     * file containing events
    */ 
    private static File event_file;
    /**
     * setter for file containing events
    */ 
    public static void set_event_file(File file){
        event_file=file;
    }
    /**
     * getter for file containing events
    */    
    public static File get_event_file(){
        return event_file;
    }
    /**
     * duration of breaks
    */
    private static int breakDuration;
    /**
     * setter for duration of breaks
    */
    public static void set_breakDuration(int time){
        breakDuration=time;
    }
    /**
     * getter for duration of breaks
    */
    public static int get_breakDuration(){
        return breakDuration;
    }
    /**
     * invokes title screen
    */
    public static void main(String[] args) 
    {
        TitleScreen title= new TitleScreen();
    }
    /**
     * makes timetable after a file has been selected
    */
    public static void makeTimetable()
    {
        int num_of_events=0;    //variable to find total number of events
        int event_number=0;     //variable to act as index to add events into an array
        boolean first=true;     //boolean to cheack it event is 1st priority
        int max_priority=0;     //variable to check which event has the maximum value of priority
        //code to find number of events in input file
        try {
            Scanner reader = new Scanner(event_file);
            while (reader.hasNextLine()) {
                num_of_events++;            //number of events is incremented for every line in file
                reader.nextLine();
            }
            reader.close();
          } catch (FileNotFoundException e) {        
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        Event[] events = new Event[num_of_events];  //array of events
        //code to read the input file and extract values
        try {
            Scanner reader = new Scanner(event_file);
            while (reader.hasNextLine()) {
              String data = reader.nextLine();
              System.out.println(data);
              String[] single_event = data.split(",");      //spereates values by commas
              events[event_number]= new Event();                    //retrives info about event and assigns it to variables
              events[event_number].set_name(single_event[0]);
              events[event_number].set_duration(Integer.parseInt(single_event[1]));
              events[event_number].set_priority(Integer.parseInt(single_event[2]));
              if(events[event_number].get_priority()>max_priority){max_priority=events[event_number].get_priority();}
              if(first){
                int hour = Integer.parseInt(single_event[3].substring(0,2))*60; //checks if it is main event and retrives its starting time
                int min = Integer.parseInt(single_event[3].substring(2));
                events[event_number].set_start(hour+min);
                first=false;
              }
              event_number++;
            }
            reader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }



        boolean next=true;  //boolean to allow events to be alternatively placed after and before main event
        int main_priority=2; //main priority variable allows events to be ordered based on increasing value of priority starting from 2
        int main_start=events[0].get_start();   //main_start refers to start time of first event
        int main_end=events[0].get_start()+events[0].get_duration();    //main_end refers to end time of last event
        events[0].set_end(events[0].get_start()+events[0].get_duration());

        //code to organise the events into a timetable based on priority 
        for(;main_priority<=max_priority;main_priority++){
            for(int i=0;i<event_number;i++){
                if(events[i].get_priority()==main_priority){
                    if(next){
                        events[i].set_start(main_end+get_breakDuration());
                        events[i].set_end(events[i].get_start()+events[i].get_duration());
                        main_end+= (events[i].get_duration()+get_breakDuration());  //main end changes as new events are added to the timetable
                        next=false;
                    }
                    else{
                        events[i].set_end(main_start-get_breakDuration());
                        events[i].set_start(events[i].get_end()-events[i].get_duration());
                        main_start-= (events[i].get_duration()+get_breakDuration());    //main start changes as new events are added to the timetable
                        next=true;                        
                    }
                } 
            }
        }
        System.out.println();
        System.out.println();

        int current_time=main_start;    
        String[] to_be_added = new String[num_of_events];   //string array to store events of timetable in string form
        event_number=0;     //variable to act as index to add events into an array

        //code to convert the timetable into a readable format
        for(int x=0;x<num_of_events;x++){
            for(int i=0;i<num_of_events;i++){
                if(events[i].get_start()==current_time){
                    //converts time to 24 hour readable format
                    int s_hour=Math.floorDiv(events[i].get_start(), 60);    
                    if(s_hour>=24){s_hour-=24;}
                    int s_min=events[i].get_start()%60;
                    int e_hour=Math.floorDiv(events[i].get_end(), 60);
                    if(e_hour>=24){e_hour-=24;}
                    int e_min=events[i].get_end()%60;
                    //converts events to string
                    to_be_added[event_number]=(events[i].get_name()+","+String.format("%02d", s_hour)+":"+String.format("%02d", s_min)+","+String.format("%02d", e_hour)+":"+String.format("%02d", e_min));
                    event_number++;
                    System.out.println(events[i].get_name()+" "+String.format("%02d", s_hour)+":"+String.format("%02d", s_min)+"   "+String.format("%02d", e_hour)+":"+String.format("%02d", e_min));
                    current_time=events[i].get_end()+get_breakDuration(); 
                }
            }
        }


        //code to write the timetable into a csv file
        try
        {
            FileWriter fstream = new FileWriter("timetable.csv");
            BufferedWriter info = new BufferedWriter(fstream);
            info.write("Name of Event,Start Time,End Time\n");
            for (int i = 0; i < event_number; i++) {
                info.write(String.format(to_be_added[i]+"%n"));
            }
            info.close();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }

    }
}