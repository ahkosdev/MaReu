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


    @Before
    public void setUp(){
        meetingService = DI.getMeetingApiService();
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
        Calendar dateSelected = Calendar.getInstance() ;
        Calendar dateA = Calendar.getInstance();
        dateA.set(2021,2,4);
        Calendar dateB = Calendar.getInstance();
        dateB.set(2021,2,6);
        dateSelected = dateA;
        Meeting meetingRoomA = new Meeting("conseil", "Salle A",dateA);
        Meeting meetingRoomB = new Meeting("conseil","Salle B",dateB);
        meetingService.createMeeting(meetingRoomA);
        meetingService.createMeeting(meetingRoomB);
        List<Meeting> meetingsPerDate = meetingService.getMeetingsWithDateSelected(dateSelected);

           assertTrue(meetingsPerDate.contains(meetingRoomA));
           assertFalse(meetingsPerDate.contains(meetingRoomB));


    }

    @Test
    public void getMeetingWithRoomSelectedWithSuccess() {
        Calendar dateA = Calendar.getInstance();
        dateA.set(2021,2,4);
        Calendar dateB = Calendar.getInstance();
        dateB.set(2021,2,6);
        Meeting meetingRoomA = new Meeting("conseil", "Salle A",dateA);
        Meeting meetingRoomB = new Meeting("conseil","Salle B",dateB);
        meetingService.createMeeting(meetingRoomA);
        meetingService.createMeeting(meetingRoomB);
        String roomSelected = "Salle A";
        List<Meeting> meetingPerRoom = meetingService.getMeetingsWithRoomSelected(roomSelected);
        assertTrue(meetingPerRoom.contains(meetingRoomA));
        assertFalse(meetingPerRoom.contains(meetingRoomB));
    }
}