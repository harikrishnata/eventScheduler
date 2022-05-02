
public class Event{
    private int start_time;
    private int end_time;
    private int priority;
    private int duration;
    private String name;
    public void set_start(int input_time){
        start_time=input_time;
    }    
    public int get_start(){
        return start_time;
    }
    public void set_end(int input_time){
        end_time=input_time;
    } 
    public int get_end(){
        return end_time;
    }
    public void set_priority(int input_priority){
        priority=input_priority;
    } 
    public int get_priority(){
        return priority;
    }
    public void set_duration(int input_duration){
        duration=input_duration;
    }    
    public int get_duration(){
        return duration;
    }
    public void set_name(String input_name){
        name=input_name;
    }    
    public String get_name(){
        return name;
    }
}