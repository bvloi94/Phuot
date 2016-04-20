package com.loibv.t1p;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.loibv.t1p.adapter.PlaceItemAdapter;
import com.loibv.t1p.iinterface.OnRequestArray;
import com.loibv.t1p.iinterface.OnSendObject;
import com.loibv.t1p.model.Place;
import com.loibv.t1p.model.Trip;
import com.loibv.t1p.model.TripPlace;
import com.loibv.t1p.utils.Const;
import com.loibv.t1p.utils.DateUtil;
import com.loibv.t1p.utils.ServiceUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

@SuppressLint("SimpleDateFormat")
public class TripInfoActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.et_tripname)
    EditText _tripNameET;
    @Bind(R.id.rv_starttime)
    RippleView _startTimeRV;
    @Bind(R.id.rv_endtime)
    RippleView _endTimeRV;
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
    @Bind(R.id.tv_addplace)
    TextView _addPlaceTV;

    private RecyclerView recyclerView;
    private PlaceItemAdapter placeItemAdapter;
    private Trip trip;

    public static final int TRIPINFO_REQUEST = 10;

    private SlideDateTimeListener startTimeListener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(Date date) {
            _startTimeTV.setText(DateUtil.getDateFormat().format(date).toString());
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
            _endTimeTV.setText(DateUtil.getDateFormat().format(date).toString());
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
        setContentView(R.layout.activity_trip_info);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_trip_info);
        toolbar.setTitle(getResources().getString(R.string.title_trip_info));
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

        Intent intent = getIntent();
        trip = intent.getParcelableExtra(Const.BD_TRIP);

        _tripNameET.setText(trip.getName());
        _startTimeTV.setText(trip.getStartTime());
        _endTimeTV.setText(trip.getEndTime());
        _gatheringPlaceET.setText(trip.getGatheringPlace());
        _noteET.setText(trip.getDescription());

        _startTimeRV.setOnClickListener(this);
        _endTimeRV.setOnClickListener(this);
        _doneBT.setOnClickListener(this);
        _addPlaceTV.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.rv_tripplace);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        placeItemAdapter = new PlaceItemAdapter(new ArrayList<Place>(), new PlaceItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Place item) {
                // Show map or delete
            }
        });
        recyclerView.setAdapter(placeItemAdapter);

        ServiceUtil<Place> serviceUtil = new ServiceUtil(getBaseContext());
        serviceUtil.getHashMap().put("tripId", String.valueOf(trip.getId()));
        serviceUtil.retrieveArrayData(Const.URL_GET_ALL_TRIP_PLACE, new OnRequestArray<Place>() {
            @Override
            public void onTaskCompleted(List<Place> list, boolean error, String message) {
                if (!error) {
                    placeItemAdapter.updateList(list);
                }
            }
        }, Place.class);

    }

    public boolean validate(String tripName, String startTime, String endTime, String gatheringPlace) {
        boolean valid = true;

        if (tripName.equals("")) {
            _gatheringPlaceET.setError("Required.");
            valid = false;
        } else {
            _tripNameET.setError(null);
        }

        Date startDate = null;
        Date endDate = null;
        if (!startTime.equals("")) {
            try {
                startDate = DateUtil.getDateFormat().parse(startTime);
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
                endDate = DateUtil.getDateFormat().parse(endTime);
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
            case R.id.bt_done: {
                String tripName = _tripNameET.getText().toString();
                String startTime = _startTimeTV.getText().toString();
                String endTime = _endTimeTV.getText().toString().trim();
                String gatheringPlace = _gatheringPlaceET.getText().toString().trim();
                String note = _noteET.getText().toString().trim();
                if (validate(tripName, startTime, endTime, gatheringPlace)) {
                    trip.setName(tripName);
                    trip.setStartTime(startTime);
                    trip.setEndTime(endTime);
                    trip.setGatheringPlace(gatheringPlace);
                    trip.setDescription(note);

                    // Get places from recycler view to update in database
                    List<Place> places = placeItemAdapter.getPlaces();

                    ServiceUtil<TripPlace> serviceUtil = new ServiceUtil<>(this);
                    for (int i = 0; i < places.size(); i++) {
                        serviceUtil.getHashMap().put("tripId", String.valueOf(trip.getId()));
                        serviceUtil.getHashMap().put("placeId", String.valueOf(places.get(i).getId()));
                        //"nextTripPlaceId"
                        serviceUtil.sendObjectData(Const.URL_ADD_NEW_TRIP_PLACE, new OnSendObject() {
                            @Override
                            public void onTaskCompleted(boolean error, String message) {
                                //
                            }
                        });
                    }
                    setResult(RESULT_OK, new Intent());
                    finish();
                }

                break;
            }
            case R.id.rv_starttime: {
                Date date = null;
                try {
                    date = DateUtil.getDateFormat().parse(_startTimeTV.getText().toString());
                } catch (ParseException e) {
                    date = new Date();
                }
                new SlideDateTimePicker.Builder(getSupportFragmentManager())
                        .setListener(startTimeListener)
                        .setInitialDate(date)
//                        .setMinDate(minDate)
//                        .setMaxDate(maxDate)
                        .setIs24HourTime(true)
                        .setTheme(SlideDateTimePicker.HOLO_LIGHT)
                        .setIndicatorColor(ContextCompat.getColor(getBaseContext(), R.color.primary))
                        .build()
                        .show();
                break;
            }
            case R.id.rv_endtime: {
                Date date = null;
                try {
                    date = DateUtil.getDateFormat().parse(_endTimeTV.getText().toString());
                } catch (ParseException e) {
                    date = new Date();
                }
                new SlideDateTimePicker.Builder(getSupportFragmentManager())
                        .setListener(endTimeListener)
                        .setInitialDate(date)
//                        .setMinDate(minDate)
//                        .setMaxDate(maxDate)
                        .setIs24HourTime(true)
                        .setTheme(SlideDateTimePicker.HOLO_LIGHT)
                        .setIndicatorColor(ContextCompat.getColor(getBaseContext(), R.color.primary))
                        .build()
                        .show();
                break;
            }
            case R.id.tv_addplace: {
                Intent intent = new Intent(TripInfoActivity.this, AddPlaceActivity.class);
                startActivityForResult(intent, AddPlaceActivity.PICKPLACE_REQUEST);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AddPlaceActivity.PICKPLACE_REQUEST) {
            if (resultCode == RESULT_OK) {
                List<Place> addedPlaces = data.getParcelableExtra(AddPlaceActivity.BD_PLACES);
                placeItemAdapter.mergeList(addedPlaces);
            }
        }
    }
}
