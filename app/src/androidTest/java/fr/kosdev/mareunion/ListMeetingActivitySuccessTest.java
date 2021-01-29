package fr.kosdev.mareunion;

import android.content.Context;
import android.view.View;

import androidx.test.espresso.ViewAssertion;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.kosdev.mareunion.meeting_list.ListMeetingActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withInputType;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ListMeetingActivitySuccessTest {

    private static int ITEMS_COUNT = 0;

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityTestRule = new ActivityTestRule<>(ListMeetingActivity.class);


    @Test
    public void addMeetingFab_StartAddMeetingActivity() {
       onView(Matchers.allOf(withId(R.id.add_meeting_fab),isDisplayed())).perform(click());
       onView(Matchers.allOf(withId(R.id.meeting_object_txt),isDisplayed())).check(matches(withHint("Meeting Object")));
    }

    @Test
    public void meetingList_Contains_CreatedMeeting() {
        onView(Matchers.allOf(withId(R.id.add_meeting_fab),isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.meeting_object_txt),isDisplayed())).perform(replaceText("Conseil Général"),closeSoftKeyboard());
        onView(Matchers.allOf(withId(R.id.meeting_rooms_spin), isDisplayed()));
        onView(Matchers.allOf(withId(R.id.meeting_add_btn), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.meeting_interface) , isDisplayed()));
        onView(Matchers.allOf(withId(R.id.meeting_list_rcv),isDisplayed()));
       onView(Matchers.allOf(withId(R.id.meeting_description_txt),isDisplayed())).check(matches(withText("Conseil Général")));
    }

}
