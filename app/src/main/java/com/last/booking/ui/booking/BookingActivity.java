package com.last.booking.ui.booking;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.last.booking.R;
import com.last.booking.ui.bookDetail.BookDetailActivity;
import com.last.booking.ui.booking.adapter.OfficeInfoExpandableListViewAdapter;

public class BookingActivity extends AppCompatActivity {

    private BookingViewModel bookingViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        int userId = getIntent().getIntExtra("userId",-1);

        bookingViewModel = ViewModelProviders.of(this,new BookViewModelFactory())
                .get(BookingViewModel.class);

        bookingViewModel.setUserId(userId);

        final Spinner spinner = findViewById(R.id.booking_city_spinner);
        bookingViewModel.getCity();

        bookingViewModel.getCityInfo().observe(this, new Observer<BookingResult>() {
            @Override
            public void onChanged(@Nullable final BookingResult bookingResult) {
                if(bookingResult == null)
                    return;
                if(bookingResult.getCityView() != null)
                {
                    getArea(bookingResult.getCityView().getCityId(0));

                    ArrayAdapter<String> spinnerAdapter =
                            new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_item_select
                            ,bookingResult.getCityView().getCityList());

                    spinnerAdapter.setDropDownViewResource(R.layout.spinner_item_drop);
                    spinner.setAdapter(spinnerAdapter);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(getApplicationContext(),
                                    "Position is" + position,Toast.LENGTH_SHORT).show();
                            getArea(bookingResult.getCityView().getCityId(position));
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                }
                if(bookingResult.getMsg() != null)
                {
                    Toast.makeText(getApplicationContext(),
                            bookingResult.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        final ExpandableListView expandableListView = findViewById(R.id.booking_office_list);
        bookingViewModel.getOfficeInfo().observe(this,
                new Observer<BookingResult>() {
                    @Override
                    public void onChanged(@Nullable BookingResult bookingResult) {
                        if(bookingResult == null)
                            return;

                        if(bookingResult.getMsg() != null)
                            Toast.makeText(getApplicationContext(),
                                    bookingResult.getMsg(), Toast.LENGTH_SHORT).show();

                        if(bookingResult.getAreaInfoView() != null)
                        {
                            final AreaInfoView areaInfoView = bookingResult.getAreaInfoView();
                            OfficeInfoExpandableListViewAdapter officeInfoExpandableListViewAdapter
                                    = new OfficeInfoExpandableListViewAdapter(
                                            areaInfoView.getAreas(),
                                            areaInfoView.getOffices(),
                                            BookingActivity.this);

                            expandableListView.setAdapter(officeInfoExpandableListViewAdapter);
                            expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                                @Override
                                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                                    Toast.makeText(getApplicationContext(),
                                            areaInfoView.getOffices().get(groupPosition).get(childPosition).getDesc(),
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(BookingActivity.this, BookDetailActivity.class);
                                    intent.putExtra("userId",bookingViewModel.getUserId());
                                    intent.putExtra("officeId",areaInfoView.getOfficeId(childPosition));
                                    startActivity(intent);
                                    BookingActivity.this.finish();
                                    return true;
                                }
                            });
                        }
                    }
                });

    }

    private void getArea(int cityId)
    {
        bookingViewModel.getArea(cityId);
    }



}
