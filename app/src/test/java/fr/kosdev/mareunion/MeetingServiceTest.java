package fr.kosdev.mareunion;

import org.junit.Before;
import org.junit.Test;

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
}