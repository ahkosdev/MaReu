package fr.kosdev.mareunion.meeting_list;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.kosdev.mareunion.DI.DI;
import fr.kosdev.mareunion.R;
import fr.kosdev.mareunion.event.DeleteMeetingEvent;
import fr.kosdev.mareunion.model.Meeting;
import fr.kosdev.mareunion.service.DummyMeetingApiService;
import fr.kosdev.mareunion.service.MeetingApiService;

public class ListMeetingActivity extends AppCompatActivity {

    @BindView(R.id.add_meeting_fab)
    FloatingActionButton addMeeting;
    @BindView(R.id.meeting_list_rcv)
    RecyclerView mRecyclerView;


    private List<Meeting> mMeetings;
    private MeetingApiService mApiService;
    private MeetingRecyclerViewAdapter meetingAdapter;
    DummyMeetingApiService meetingApiService = new DummyMeetingApiService();
    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meeting_);
        ButterKnife.bind(this);
        this.configureToolbar();
        this.configureRecyclerView();

        mApiService = DI.getMeetingApiService();

        //addMeeting = (FloatingActionButton) findViewById(R.id.add_meeting_fab) ;
        addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMeetingActivity.this, AddMeetingActivity.class);
                startActivity(intent);

            }
        });



        final Calendar calendar = Calendar.getInstance();
        lastSelectedYear = calendar.get(Calendar.YEAR);
        lastSelectedMonth = calendar.get(Calendar.MONTH);
        lastSelectedDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);


    }

    public void configureRecyclerView(){

        mMeetings = new ArrayList<>();
        meetingAdapter = new MeetingRecyclerViewAdapter(mMeetings);
        mRecyclerView.setAdapter(meetingAdapter);
    }

    private void updateMeetingList(){
        mMeetings.clear();
        mMeetings.addAll(mApiService.getMeetings());
        meetingAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_list_meeting_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()){
            //case R.id.toolbar_filter_list_btn:
                //Toast.makeText(this, "no filter", Toast.LENGTH_LONG).show();
                //return true;
            case R.id.sub_date:
                menuSelectedDate();
                return true;
            case R.id.sub_rooms:
                configureAlertDialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }



    }

    private void configureToolbar(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.meeting_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateMeetingList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe

    public void onEvent(DeleteMeetingEvent event) {
        mApiService.deleteMeeting(event.meeting);
        updateMeetingList();
    }

    private void menuSelectedDate(){

        DatePickerDialog.OnDateSetListener mPickerDialog = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                java.util.Calendar dateSelected = java.util.Calendar.getInstance();
                dateSelected.clear();
                dateSelected.set(year,month,dayOfMonth);
                mMeetings.clear();
                mMeetings.addAll(mApiService.getMeetingsWithDateSelected(dateSelected));
                meetingAdapter.notifyDataSetChanged();
                lastSelectedYear = year;
                lastSelectedMonth = month;
                lastSelectedDayOfMonth = dayOfMonth;

            }

        };
        DatePickerDialog menuPicker = new DatePickerDialog(this, mPickerDialog,lastSelectedYear,lastSelectedMonth,lastSelectedDayOfMonth);
        menuPicker.show();
    }

    private void configureAlertDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("choose your room");
        String[] rooms = {"Salle A","Salle B","Salle C","Salle D","Salle E","Salle F","Salle G","Salle H","Salle I","Salle J"};
        int checkedRoom = 0;
        builder.setSingleChoiceItems(rooms, checkedRoom, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String roomSelected = Integer.toString(checkedRoom);
                mMeetings.clear();
                mMeetings.addAll(mApiService.getMeetingsWithRoomSelected(roomSelected));
                meetingAdapter.notifyDataSetChanged();

            }
        });
        builder.setNegativeButton("Cancel",null);
        AlertDialog roomsDialog = builder.create();
        roomsDialog.show();
    }

}