The program takes a list of all the acts, their lengths, and how important they are (their priority), as well as a time the headline act starts, and it will then generate a schedule for all the acts based on that data.

The headline act (the act with priority 1) will start at the target time that was specified, the next act (priority 2) will start at the closest possible time to the target time that will not overlap with anyone else (act 1). This could be before or after the headline act.

This continues, putting each act on the schedule until all acts are scheduled according to their priority.

Times should be specified as a normal 24-hour based time, i.e., 19:30 and durations should be specified in whole minutes.







The user first enters break time time in between events

The user selects a text file containing events in the format:

Main Event,Duration,Priority,Starting Time            Taylor Swift,120,1,1800
Another Event,Duration,Priority                       Harry Styles,90,2      
Another Event,Duration,Priority                       Years & Years,90,3
. . . .     

The timetable will be stored in a CSV file titled timetable.CSV
