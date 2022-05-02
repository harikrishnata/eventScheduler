import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner;

public class driver{
    public static void main(String[] args) 
    {
        int main_start;
        int main_end;
        int main_priority=2;
        boolean next=true;
        boolean first=true;
        Event[] events = new Event[10];
        int event_number=0;
        try {
            File input_file = new File("events.txt");
            Scanner reader = new Scanner(input_file);
            while (reader.hasNextLine()) {
              String data = reader.nextLine();
              System.out.println(data);
              String[] single_event = data.split("  ");
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


        /*
        Event Taylor_Swifft= new Event();
        Taylor_Swifft.set_priority(1);
        Taylor_Swifft.set_start(60*18);
        Taylor_Swifft.set_end(60*20);
        main_start=Taylor_Swifft.get_start();
        main_end=Taylor_Swifft.get_end();
        Event[] events = new Event[10];
        events[0]= new Event();
        events[0].set_priority(2);
        events[0].set_duration(60);
        events[1]= new Event();
        events[1].set_priority(4);
        events[1].set_duration(55);
        events[2]= new Event();
        events[2].set_priority(3);
        events[2].set_duration(45);
        events[3]= new Event();
        events[3].set_priority(2);
        events[3].set_duration(120);
        events[4]= new Event();
        events[4].set_priority(3);
        events[4].set_duration(60);
        */

    
        for(;main_priority<5;main_priority++){
            for(int i=0;i<event_number;i++){
                if(events[i].get_priority()==main_priority){
                    if(next){
                        events[i].set_start(main_end);
                        events[i].set_end(events[i].get_start()+events[i].get_duration());
                        main_end+= events[i].get_duration();
                        next=false;
                    }
                    else{
                        events[i].set_end(main_start);
                        events[i].set_start(events[i].get_end()-events[i].get_duration());
                        main_start-= events[i].get_duration();
                        next=true;                        
                    }
                }
            }

        }
        System.out.println();
        System.out.println();

        int current_time=main_start;

        for(int x=0;x<event_number;x++){
            for(int i=0;i<event_number;i++){
                if(events[i].get_start()==current_time){
                    int s_hour=Math.floorDiv(events[i].get_start(), 60);
                    int s_min=events[i].get_start()%60;
                    int e_hour=Math.floorDiv(events[i].get_end(), 60);
                    int e_min=events[i].get_end()%60;
                    System.out.println(events[i].get_name()+" "+s_hour+":"+s_min+"   "+e_hour+":"+e_min);
                    current_time=events[i].get_end();
                }
            }
        }
    }
}