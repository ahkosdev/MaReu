package fr.kosdev.mareunion.meeting_list;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.kosdev.mareunion.DI.DI;
import fr.kosdev.mareunion.R;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static fr.kosdev.mareunion.needs.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

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

    @Before
    public void initApi(){
        DI.clearNewMeetingApiService();
    }


    @Test
    public void addMeetingFab_StartAddMeetingActivity() {
       onView(Matchers.allOf(ViewMatchers.withId(R.id.add_meeting_fab),isDisplayed())).perform(click());
       onView(Matchers.allOf(withId(R.id.meeting_object_txt),isDisplayed())).check(matches(withHint("Meeting Object")));
    }

    @Test
    public void meetingList_Contains_CreatedMeeting() {
        onView(Matchers.allOf(withId(R.id.add_meeting_fab),isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.meeting_object_txt),isDisplayed())).perform(replaceText("Conseil Général"),closeSoftKeyboard());
        onView(Matchers.allOf(withId(R.id.meeting_add_btn), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.meeting_list_rcv),isDisplayed())).check(withItemCount(ITEMS_COUNT + 1));
    }

    @Test
    public void meetingPerDate_Contains_Only_MeetingDateSelected() {
        onView(Matchers.allOf(withId(R.id.add_meeting_fab),isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.meeting_object_txt),isDisplayed())).perform(replaceText("Conseil Général"),closeSoftKeyboard());
        onView(Matchers.allOf(withId(R.id.date_picker_button),isDisplayed())).perform(click());
        onView(Matchers.allOf(withClassName(Matchers.equalTo(DatePicker.class.getName())),isDisplayed())).perform(PickerActions.setDate(2021,1,5));
        onView(Matchers.allOf(withId(android.R.id.button1),isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.meeting_add_btn), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.meeting_list_rcv),isDisplayed())).check(withItemCount(ITEMS_COUNT + 1));
        onView(Matchers.allOf(withId(R.id.add_meeting_fab),isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.meeting_object_txt),isDisplayed())).perform(replaceText("Conseil Général"),closeSoftKeyboard());
        onView(Matchers.allOf(withId(R.id.date_picker_button),isDisplayed())).perform(click());
        onView(Matchers.allOf(withClassName(Matchers.equalTo(DatePicker.class.getName())),isDisplayed())).perform(PickerActions.setDate(2021,1,6));
        onView(Matchers.allOf(withId(android.R.id.button1),isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.meeting_add_btn), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.meeting_list_rcv),isDisplayed())).check(withItemCount(ITEMS_COUNT + 2));
        onView(Matchers.allOf(withId(R.id.toolbar_filter_list_btn),isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.title),withText("Meeting Dates"),isDisplayed())).perform(click());
        onView(Matchers.allOf(withClassName(Matchers.equalTo(DatePicker.class.getName())),isDisplayed())).perform(PickerActions.setDate(2021,1,6));
        onView(Matchers.allOf(withId(android.R.id.button1),isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.meeting_description_txt),isDisplayed())).check(matches(withText("Conseil Général-6/1/2021--Salle A")));
        onView(Matchers.allOf(withId(R.id.meeting_list_rcv),isDisplayed())).check(withItemCount(ITEMS_COUNT + 1 ));



    }

    @Test
    public void meetingPerRoom_Contains_Only_MeetingRoomSelected() {
        onView(Matchers.allOf(withId(R.id.add_meeting_fab),isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.meeting_object_txt),isDisplayed())).perform(replaceText("Conseil Général"),closeSoftKeyboard());
        onView(Matchers.allOf(withId(R.id.meeting_rooms_spin),isDisplayed())).perform(click());
        onView(Matchers.allOf(withText("Salle C"),isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.meeting_add_btn), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.meeting_list_rcv),isDisplayed())).check(withItemCount(ITEMS_COUNT + 1));
        onView(Matchers.allOf(withId(R.id.add_meeting_fab),isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.meeting_object_txt),isDisplayed())).perform(replaceText("Conseil Général"),closeSoftKeyboard());
        onView(Matchers.allOf(withId(R.id.meeting_rooms_spin),isDisplayed())).perform(click());
        onView(Matchers.allOf(withText("Salle H"),isDisplayed())).perform(scrollTo(),click());
        onView(Matchers.allOf(withId(R.id.meeting_add_btn), isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.meeting_list_rcv),isDisplayed())).check(withItemCount(ITEMS_COUNT + 2));
        onView(Matchers.allOf(withId(R.id.toolbar_filter_list_btn),isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.title),withText("Meeting Rooms"),isDisplayed())).perform(click());
        //DataInteraction appCompatCheckedTextView =
        //onData(anything()).inAdapterView(allOf(withId(R.id.select_dialog_listview), childAtPosition(withId(R.id.contentPanel), 0))).atPosition(2);
        //appCompatCheckedTextView.perform(click());
        onData(anything()).inAdapterView(allOf(withId(R.id.select_dialog_listview),childAtPosition(withId(R.id.contentPanel),0))).atPosition(2).perform(click());
        onView(Matchers.allOf(withId(android.R.id.button1),isDisplayed())).perform(click());
        onView(Matchers.allOf(withId(R.id.meeting_description_txt),isDisplayed())).check(matches(withText("Conseil Général---Salle C")));
        onView(Matchers.allOf(withId(R.id.meeting_list_rcv),isDisplayed())).check(withItemCount(ITEMS_COUNT + 1 ));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
