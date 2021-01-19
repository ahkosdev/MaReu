package fr.kosdev.mareunion.meeting_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.kosdev.mareunion.DI.DI;
import fr.kosdev.mareunion.R;
import fr.kosdev.mareunion.model.Meeting;
import fr.kosdev.mareunion.service.MeetingApiService;

import static fr.kosdev.mareunion.R.drawable.*;

public class AddMeetingActivity extends AppCompatActivity {

    @BindView(R.id.meeting_date)
    TextInputEditText meetingDate;
   @BindView(R.id.date_picker_button)
    MaterialButton datePicker;
    @BindView(R.id.meeting_time)
    TextInputEditText meetingTime;
    @BindView(R.id.time_picker_button)
    MaterialButton timePicker;
    @BindView(R.id.text_input_object_txt)
    TextInputLayout meetingObject;
    @BindView(R.id.text_input_meeting_room)
    TextInputLayout meetingRoom;
    @BindView(R.id.text_input_entrant_mail)
    TextInputLayout entrantMail;
    @BindView(R.id.meeting_add_btn)
    MaterialButton addMeeting;
    @BindView(R.id.text_input_date)
    TextInputLayout meetingDatePicker;
    @BindView(R.id.text_input_time)
    TextInputLayout meetingTimePicker;
    @BindView(R.id.meeting_image_img)
    ImageView meetingImage;

    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;
    private int lastSelectedHour = -1;
    private int lastSelectedMinute = -1;
    private MeetingApiService mApiService;
    private String meetingDrawable ;
    private String mMeetingImage;
    private  String meetingRoomsColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        ButterKnife.bind(this);
        mApiService = DI.getMeetingApiService();
        init();


        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDay();
            }
        });

        final Calendar calendar = Calendar.getInstance();
        lastSelectedYear = calendar.get(Calendar.YEAR);
        lastSelectedMonth = calendar.get(Calendar.MONTH);
        lastSelectedDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        lastSelectedHour = calendar.get(Calendar.HOUR_OF_DAY);
        lastSelectedMinute = calendar.get(Calendar.MINUTE);


        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTime();
            }
        });

        addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Meeting meeting = new Meeting(

                        mMeetingImage,
                        meetingObject.getEditText().getText().toString(),
                        meetingDatePicker.getEditText().getText().toString(),
                        meetingTimePicker.getEditText().getText().toString(),
                        meetingRoom.getEditText().getText().toString(),
                        entrantMail.getEditText().getText().toString()
                );
                mApiService.createMeeting(meeting);
                finish();
            }
        });
    }

    private void selectDay(){

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                meetingDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                lastSelectedYear = year;
                lastSelectedMonth = month;
                lastSelectedDayOfMonth = dayOfMonth;
            }
        };

        DatePickerDialog picker = new DatePickerDialog(this,dateSetListener,lastSelectedYear,lastSelectedMonth,lastSelectedDayOfMonth);
        picker.show();

    }

    private void selectTime(){

        final boolean is24HourView = true;
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                meetingTime.setText(hourOfDay +":" + minute);
                lastSelectedHour = hourOfDay;
                lastSelectedMinute = minute;
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,timeSetListener,lastSelectedHour,lastSelectedMinute,is24HourView);
        timePickerDialog.show();
    }

    private void init(){
        mMeetingImage = getRoomsColor();
        Glide.with(this).load(mMeetingImage).placeholder(ic_baseline_brightness_red_1_40).into(meetingImage);
        meetingObject.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addMeeting.setEnabled(s.length() > 0);

            }
        });
    }

     String getRoomsColor(){
       meetingRoomsColor = meetingRoom.getEditText().getText().toString();
                switch (meetingRoomsColor) {
                   case "Salle A":
                       meetingImage.setImageResource(ic_baseline_brightness_1_40);
                   case "Salle B":
                       meetingImage.setImageResource(ic_baseline_brightness_blue_1_40);

                   case "Salle C":
                       meetingImage.setImageResource(ic_baseline_brightness_pink_1_40);
               }
               return "meetingImage.setImageResource()"+ System.currentTimeMillis();

    }




}