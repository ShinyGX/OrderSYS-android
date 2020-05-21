package com.last.booking.ui.bookDetail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.last.booking.R;
import com.last.booking.data.model.MissionDetail;
import com.last.booking.ui.bookDetail.adapter.MissionDetailAdapter;
import com.last.booking.ui.bookDetail.adapter.OnItemClick;
import com.last.booking.ui.orderFinalCheck.OrderFinalCheckActivity;

public class BookDetailActivity extends AppCompatActivity {

    private BookDetailViewModel bookDetailViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        final int userId = getIntent().getIntExtra("userId",-1);
        final int officeId = getIntent().getIntExtra("officeId",-1);


        bookDetailViewModel = ViewModelProviders
                .of(this,new BookDetailViewModelFactory()).get(BookDetailViewModel.class);

        bookDetailViewModel.setUserId(userId);
        bookDetailViewModel.setOfficeId(officeId);

        final RecyclerView detail = findViewById(R.id.detail_booking_detail);
        bookDetailViewModel.getUsefulInfo();

        bookDetailViewModel.getBookInfo().observe(this, new Observer<BookDetailResult>() {
            @Override
            public void onChanged(@Nullable final BookDetailResult bookDetailResult) {
                if(bookDetailResult == null)
                    return;

                if(bookDetailResult.getBookDetailView() != null)
                {
                    final BookDetailView bookDetailView = bookDetailResult.getBookDetailView();
                    MissionDetailAdapter adapter = new MissionDetailAdapter(bookDetailView.getMissionDetailList());
                    adapter.setOnItemClick(new OnItemClick() {
                        @Override
                        public void itemClick(int parent,int pos) {
                            MissionDetail missionDetail = bookDetailView.getMissionDetailList().get(parent);
//                            bookDetailViewModel.add(missionDetail.getBusinessId(),
//                                    missionDetail.getTime().get(pos));

                            Intent nextActivity = new Intent(BookDetailActivity.this, OrderFinalCheckActivity.class);
                            nextActivity.putExtra("userId",userId);
                            nextActivity.putExtra("officeId",officeId);
                            nextActivity.putExtra("businessId",missionDetail.getBusinessId());
                            nextActivity.putExtra("time",missionDetail.getTime().get(pos).getTime());
                            startActivity(nextActivity);
                            BookDetailActivity.this.finish();
                        }
                    });
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BookDetailActivity.this);
                    detail.setLayoutManager(linearLayoutManager);
                    detail.setAdapter(adapter);
                }

                if(bookDetailResult.getError() != null)
                {
                    Toast.makeText(
                            BookDetailActivity.this,bookDetailResult.getError(),Toast.LENGTH_SHORT).show();
                }


            }
        });


        bookDetailViewModel.getBookSuccessInfo().observe(this, new Observer<BookDetailResult>() {
            @Override
            public void onChanged(@Nullable BookDetailResult bookDetailResult) {
                if(bookDetailResult == null)
                    return;

//                if(bookDetailResult.getBookSucessView() != null)
//                {
//                    Toast.makeText(getApplicationContext(),
//                            "预约成功",Toast.LENGTH_SHORT).show();
//                }

                if(bookDetailResult.getError() != null)
                {
                    Toast.makeText(getApplicationContext(),
                            bookDetailResult.getError(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
