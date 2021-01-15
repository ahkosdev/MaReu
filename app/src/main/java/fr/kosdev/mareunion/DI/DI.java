package fr.kosdev.mareunion.DI;

import fr.kosdev.mareunion.service.DummyMeetingApiService;
import fr.kosdev.mareunion.service.MeetingApiService;

public class DI {

    private static MeetingApiService sMeetingApiService = new DummyMeetingApiService();


    public static MeetingApiService getMeetingApiService() {

        return sMeetingApiService;
    }
}
