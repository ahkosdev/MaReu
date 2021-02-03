package fr.kosdev.mareunion;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;

import fr.kosdev.mareunion.DI.DI;
import fr.kosdev.mareunion.model.Meeting;
import fr.kosdev.mareunion.service.MeetingApiService;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MeetingServiceTest {

    private MeetingApiService meetingService;
    int item;

    @Before
    public void setUp(){
        meetingService = DI.getMeetingApiService();
    }
    @Test
    public void getMeetingsWithSuccess() {

    }

    @Test
    public void createMeetingWithSuccess() {
        Meeting meetingToCreate = new Meeting();
        meetingService.createMeeting(meetingToCreate);
        assertTrue(meetingService.getMeetings().contains(meetingToCreate));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToCreate = new Meeting();
        meetingService.deleteMeeting(meetingToCreate);
        assertFalse(meetingService.getMeetings().contains(meetingToCreate));
    }

    @Test
    public void getMeetingsWithDateSelectedWithSuccess() {
        List<Meeting> meetingsPerDate = new ArrayList<>();

        Calendar dateSelected = Calendar.getInstance() ;
       for (int item = 0; item<meetingService.getMeetings().size(); item++){
          if (meetingService.getMeetings().get(item).getCalendar().getTimeInMillis()==dateSelected.getTimeInMillis());
           // meetingsPerDate.add(meetingService.getMeetings().get(item));
           meetingService.getMeetingsWithDateSelected(dateSelected);
           assertFalse(meetingsPerDate.contains(meetingService.getMeetings().get(item)));

       }



    }
}