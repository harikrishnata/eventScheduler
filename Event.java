
public class Event{
    /**
     * time the event starts
    */ 
    private int start_time; 
    /**
     * time the event ends
    */ 
    private int end_time;   
    /**
     * priority of the event
    */ 
    private int priority;   
    /**
     * duration of the event
    */ 
    private int duration;  
    /**
     * name of the event
    */ 
    private String name;   
    /**
     * setter for start time
    */       
    public void set_start(int input_time){          
        start_time=input_time;
    } 
    /**
     * getter for start time
    */   
    public int get_start(){         
        return start_time;
    }
    /**
     * setter for end time
    */
    public void set_end(int input_time){  
        end_time=input_time;
    } 
    /**
     * getter for end time
    */
    public int get_end(){   
        return end_time;
    }
    /**
     * setter for priority
    */
    public void set_priority(int input_priority){  
        priority=input_priority;
    }
    /**
     * getter for priority
    */ 
    public int get_priority(){    
        return priority;
    }
    /**
     * setter for duration
    */
    public void set_duration(int input_duration){  
        duration=input_duration;
    }    
    /**
     * getter for duration
    */
    public int get_duration(){   
        return duration;
    }
    /**
     * setter for name
    */
    public void set_name(String input_name){   
        name=input_name;
    }    
    /**
     * getter for name
    */
    public String get_name(){   
        return name;
    }
}