package se.ju.csi.oom.lab4;

import java.util.Arrays;
import java.util.HashSet;

public class TimeZoneTranslator {

	public static DateTime shiftTimeZone(DateTime inputDate, int fromOffset, int toOffset) {
		
				int inputHour = inputDate.getHour(); 
		 		int gmtHour = inputHour - fromOffset; 
				int targetHour = gmtHour + toOffset; 
				int targetDay = inputDate.getDay();  
				int targetMonth = inputDate.getMonth(); 
				int targetYear = inputDate.getYear(); 
				
			 
				if(targetHour > 24) { 
			 			targetHour = targetHour % 24; 
			 			targetDay++; 
			 			 
				} else if(targetHour < 1){ 
			 			targetHour = targetHour % 24; 
			 			targetDay--; 
			 			 
			 	}else {} 
			 			 
			 	if(targetDay > 31) { 
			 			targetDay = targetDay % 31; 
			 			targetMonth++; 
			 			 
		 		} else if(targetDay<1) { 
						targetDay = targetDay % 31; 
			 			targetMonth--; 
			 			 
				}else {} 
			 			 
				 
			 	if(targetMonth > 12) { 
						targetMonth = targetMonth % 12; 
			 			targetYear++; 
					 
				}else if(targetMonth < 1) { 
			 			targetMonth = targetMonth % 12; 
			 			targetYear--; 
			 			 
		 		}else {} 
			 	
			 	
			 	DateTime targetDateTime= new DateTime(targetYear,targetMonth,targetDay,targetHour,inputDate.getMinute(), inputDate.getSecond());
		 			 
			   
			   return targetDateTime;
	}
	

	public static Event shiftEventTimeZone(Event inputEvent, TimeZone fromZone, TimeZone toZone) {
		
		DateTime shiftedStart = shiftTimeZone(inputEvent.getStartDate(), fromZone.getOffset(), toZone.getOffset());
		DateTime shiftedEnd = shiftTimeZone(inputEvent.getEndDate(), fromZone.getOffset(), toZone.getOffset());
		Event targetEvent = new Event(inputEvent.getLabel(), shiftedStart, shiftedEnd, inputEvent.getAttendees(), inputEvent.getLocation());
		return targetEvent;
	}
	
	public static void main(String [ ] args) {
		DateTime LectureStart = new DateTime(2018, 8, 27, 8, 0, 0);
		DateTime LectureEnd = new DateTime(2018, 8, 27, 9, 45, 0);
		Person johannes = new Person("Johannes Schmidt");
		Person ragnar = new Person("Ragnar Nohre");
		Place HC218 = new Place("Hc218",57.7785672,14.1614833,20.0);
		
		Event firstOomLecture = new Event("OOM 2018 Lecture 1",
				LectureStart,
				LectureEnd,
				new HashSet<>(Arrays.asList(johannes, ragnar)),
				HC218);
		
		System.out.println(String.format("============\nOriginal event\n============\n%s", firstOomLecture.toString()));
		System.out.println();
		System.out.println(String.format("========================\nEvent shifted to Boston time\n========================\n%s", shiftEventTimeZone(firstOomLecture, TimeZone.CENTRAL_EUROPEAN_TIME, TimeZone.US_EASTERN).toString()));
	}
}
