import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner;
import java.io.FileWriter;  
import java.io.IOException; 
import java.io.BufferedWriter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class driver{

    TitleScreen title;
    public static File event_file;

    private static int breakDuration=10;

    public static void set_breakDuration(int time){
        breakDuration=time;
    }

    public static int get_breakDuration(){
        return breakDuration;
    }

    public static void main(String[] args) 
    {
        TitleScreen title= new TitleScreen();
    }

    public static void makeTimetable()
    {
        int main_start;
        int main_end;
        int main_priority=2;
        boolean next=true;
        boolean first=true;
        Event[] events = new Event[10];
        int event_number=0;


        try {
            //File input_file = new File("events.txt");
            Scanner reader = new Scanner(event_file);
            while (reader.hasNextLine()) {
              String data = reader.nextLine();
              System.out.println(data);
              String[] single_event = data.split(",");
              events[event_number]= new Event();
              events[event_number].set_name(single_event[0]);
              events[event_number].set_duration(Integer.parseInt(single_event[1]));
              events[event_number].set_priority(Integer.parseInt(single_event[2]));
              if(first){
                int hour = Integer.parseInt(single_event[3].substring(0,2))*60;
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




        main_start=events[0].get_start();
        main_end=events[0].get_start()+events[0].get_duration();
        events[0].set_end(events[0].get_start()+events[0].get_duration());

    
        for(;main_priority<5;main_priority++){
            for(int i=0;i<event_number;i++){
                if(events[i].get_priority()==main_priority){
                    if(next){
                        if(main_priority!=1){
                            events[i].set_start(main_end+get_breakDuration());
                            events[i].set_end(events[i].get_start()+events[i].get_duration());
                            main_end+= (events[i].get_duration()+get_breakDuration());
                            next=false;
                        }
                        else{
                            events[i].set_start(main_end);
                            events[i].set_end(events[i].get_start()+events[i].get_duration());
                            main_end+= (events[i].get_duration());
                            next=false;                            
                        }
                    }
                    else{
                        events[i].set_end(main_start-get_breakDuration());
                        events[i].set_start(events[i].get_end()-events[i].get_duration());
                        main_start-= (events[i].get_duration()+get_breakDuration());
                        next=true;                        
                    }
                }
                

            }

        }

        System.out.println();
        System.out.println();

        int current_time=main_start;

        String[] to_be_added = new String[30];
        int loop_counter=0;


        for(int x=0;x<event_number;x++){
            for(int i=0;i<event_number;i++){
                if(events[i].get_start()==current_time){
                    int s_hour=Math.floorDiv(events[i].get_start(), 60);
                    int s_min=events[i].get_start()%60;
                    int e_hour=Math.floorDiv(events[i].get_end(), 60);
                    int e_min=events[i].get_end()%60;

                    to_be_added[loop_counter]=(events[i].get_name()+","+String.format("%02d", s_hour)+":"+String.format("%02d", s_min)+","+String.format("%02d", e_hour)+":"+String.format("%02d", e_min));
                    loop_counter++;
                    System.out.println(events[i].get_name()+" "+String.format("%02d", s_hour)+":"+String.format("%02d", s_min)+"   "+String.format("%02d", e_hour)+":"+String.format("%02d", e_min));
                    current_time=events[i].get_end()+get_breakDuration(); 
                }
            }
        }



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