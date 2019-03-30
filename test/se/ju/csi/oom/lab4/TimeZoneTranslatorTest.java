package se.ju.csi.oom.lab4;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class TimeZoneTranslatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testShiftTimeZone() {
		DateTime dateTime = new DateTime(2017, 8 , 21, 11, 0, 0 );
		DateTime shiftedTime = TimeZoneTranslator.shiftTimeZone(dateTime, 2, 10); //10-2
		assertEquals("Fail, not equals", "2017-08-21 19:00", shiftedTime.toString());
	}

	@Test
	public void testShiftEventTimeZone() {
		
		DateTime startTime = new DateTime(2017, 8 , 21, 02, 0, 0 );
		DateTime endTime = new DateTime(2017, 8, 21, 23, 0, 0);
		
		Person maria = new Person("Maria");
		Person dikran = new Person("Dikran");
		Place jonkoping = new Place("Jönköping", 57.7559194,14.047828,11.00);
	
		
		Event karinsBirth = new Event("Karin's birth",
				startTime,
				endTime,
				new HashSet<>(Arrays.asList(maria, dikran)),
				jonkoping);
		
		System.out.println(String.format("============\nOriginal event\n============\n%s", karinsBirth.toString()));
		
		
		Event shiftedEvent = TimeZoneTranslator.shiftEventTimeZone(karinsBirth,TimeZone.CENTRAL_EUROPEAN_TIME, TimeZone.UNITED_ARAB_EMIRATES);
		
		Event thisIsExpected = new Event("Karin's birth",  
							new DateTime(2017,8, 21, 05,0,0), 
							new DateTime(2017, 8, 22, 02,0, 0), 
							new HashSet<>(Arrays.asList(maria, dikran)), 
							jonkoping); 
		
		System.out.println(); 
		System.out.println(String.format("============\nthis is expected\n============\n%s", thisIsExpected.toString())); 
		System.out.println(); 
		System.out.println(String.format("============\nwhat I got\n============\n%s", shiftedEvent.toString())); 
		 		 
				 
	    assertEquals("Fail, not equals", thisIsExpected.toString() , shiftedEvent.toString()); 
			 

	}

}
