package com.loibv.t1p;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.loibv.t1p.model.Account;
import com.loibv.t1p.model.Trip;
import com.loibv.t1p.utils.Const;
import com.loibv.t1p.utils.DateUtil;
import com.loibv.t1p.utils.InternalStorage;
import com.loibv.t1p.utils.ServiceUtil;
import com.loibv.t1p.iinterface.OnSendObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

@SuppressLint("SimpleDateFormat")
public class NewTripActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int NEW_TRIP_REQUEST = 1;
    public static final int DONE = 2;

    @Bind(R.id.et_tripname)
    EditText _tripNameET;
    @Bind(R.id.rv_starttime)
    RippleView _startTimeRippleView;
    @Bind(R.id.rv_endtime)
    RippleView _endTimeRippleView;
    @Bind(R.id.tv_starttime)
    TextView _startTimeTV;
    @Bind(R.id.tv_endtime)
    TextView _endTimeTV;
    @Bind(R.id.bt_done)
    Button _doneBT;
    @Bind(R.id.et_gatheringplace)
    EditText _gatheringPlaceET;
    @Bind(R.id.et_note)
    EditText _noteET;

    private SimpleDateFormat mFormatter = new SimpleDateFormat("HH:mm dd/MM/yyyy");

    private SlideDateTimeListener startTimeListener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(Date date) {
            _startTimeTV.setText(mFormatter.format(date).toString());
            _startTimeTV.setError(null);

        }

        // Optional cancel listener
        @Override
        public void onDateTimeCancel() {
            //
        }
    };

    private SlideDateTimeListener endTimeListener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(Date date) {
            _endTimeTV.setText(mFormatter.format(date).toString());
        }

        // Optional cancel listener
        @Override
        public void onDateTimeCancel() {
            //
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_new_trip);
        toolbar.setTitle(getResources().getString(R.string.title_add_new_trip));
        //setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_arrow);
        //toolbar.setNavigationIcon(R.mipmap.ic_dehaze_white_24dp);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                }
        );
        _doneBT.setOnClickListener(this);
        _startTimeRippleView.setOnClickListener(this);
        _endTimeRippleView.setOnClickListener(this);
    }

    public boolean validate(String tripName, String startTime, String endTime, String gatheringPlace) {
        boolean valid = true;

        if (tripName.equals("")) {
            _tripNameET.setError("Required.");
            valid = false;
        } else {
            _tripNameET.setError(null);
        }
        Date startDate = null;
        Date endDate = null;
        if (!startTime.equals("")) {
            try {
                startDate = mFormatter.parse(startTime);
                _startTimeTV.setError(null);
            } catch (ParseException e) {
                //_startTimeTV.setError("date invalid");
                Toast.makeText(getBaseContext(), "Invalid date", Toast.LENGTH_LONG).show();
                valid = false;
            }
        } else {
            _startTimeTV.setError("can not be empty");
            valid = false;
        }
        if (!endTime.equals("")) {
            try {
                endDate = mFormatter.parse(endTime);
                _endTimeTV.setError(null);
            } catch (ParseException e) {
//                _endTimeTV.setError("date invalid");
                Toast.makeText(getBaseContext(), "Invalid date", Toast.LENGTH_LONG).show();
                valid = false;
            }
        }

        if (endDate != null && startDate != null) {
            if (endDate.compareTo(startDate) < 0) {
                Toast.makeText(getBaseContext(), "End date must after start date.", Toast.LENGTH_LONG).show();
                valid = false;
            }
        }

        if (gatheringPlace.equals("")) {
            _gatheringPlaceET.setError("Required.");
            valid = false;
        } else {
            _gatheringPlaceET.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_done:
                String tripName = _tripNameET.getText().toString();
                String startTime = _startTimeTV.getText().toString();
                String endTime = _endTimeTV.getText().toString().trim();
                String gatheringPlace = _gatheringPlaceET.getText().toString().trim();
                String note = _noteET.getText().toString().trim();

                if (validate(tripName, startTime, endTime, gatheringPlace)) {
                    Account acc = null;
                    try {
                        acc = (Account) InternalStorage.readObject(this, Const.IS_ACCOUNT);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    } catch (ClassNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    if (acc!=null) {
                        Trip trip = new Trip();
                        trip.setLeaderId(acc.getId());
                        trip.setName(tripName);
                        trip.setCreateTime(DateUtil.getDateFormat().format(new Date()));
                        trip.setStartTime(startTime);
                        trip.setEndTime(endTime);
                        trip.setGatheringPlace(gatheringPlace);
                        trip.setDescription(note);

                        ObjectMapper mapper = new ObjectMapper();

                        try {
                            JSONObject tripJSON = new JSONObject(mapper.writeValueAsString(trip));
                            ServiceUtil serviceUtil = new ServiceUtil(this);
                            serviceUtil.sendObjectData(Const.URL_ADD_NEW_TRIP, tripJSON, new OnSendObject() {
                                @Override
                                public void onTaskCompleted(boolean error, String message) {
                                    if (!error) {
                                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent();
                                        //intent.putExtra(Const.BD_TRIP, (Parcelable) trip);
                                        setResult(DONE, intent);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } catch (JSONException e) {
                            System.out.println(e.getMessage());
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        } catch (JsonProcessingException e) {
                            System.out.println(e.getMessage());
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            case R.id.rv_starttime:
                new SlideDateTimePicker.Builder(getSupportFragmentManager())
                        .setListener(startTimeListener)
                        .setInitialDate(new Date())
                        //                        .setMinDate(minDate)
                        //                        .setMaxDate(maxDate)
                        .setIs24HourTime(true)
                        .setTheme(SlideDateTimePicker.HOLO_LIGHT)
                        .setIndicatorColor(ContextCompat.getColor(getBaseContext(), R.color.primary))
                        .build()
                        .show();
                break;
            case R.id.rv_endtime:
                new SlideDateTimePicker.Builder(getSupportFragmentManager())
                        .setListener(endTimeListener)
                        .setInitialDate(new Date())
                        //                        .setMinDate(minDate)
                        //                        .setMaxDate(maxDate)
                        .setIs24HourTime(true)
                        .setTheme(SlideDateTimePicker.HOLO_LIGHT)
                        .setIndicatorColor(ContextCompat.getColor(getBaseContext(), R.color.primary))
                        .build()
                        .show();
                break;
        }
    }
}
