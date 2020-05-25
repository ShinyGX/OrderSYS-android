package com.last.booking.ui.bookDetail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.last.booking.AnimatedExpandableListView;
import com.last.booking.R;
import com.last.booking.data.model.MissionDetail;
import com.last.booking.ui.bookDetail.adapter.MissionDetailExpandableAdapter;
import com.last.booking.ui.bookDetail.adapter.OnExpandItemClick;
import com.last.booking.ui.orderFinalCheck.OrderFinalCheckActivity;

public class BookDetailActivity extends AppCompatActivity {

    private BookDetailViewModel bookDetailViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail2);

        final int userId = getIntent().getIntExtra("userId",-1);
        final int officeId = getIntent().getIntExtra("officeId",-1);


        bookDetailViewModel = ViewModelProviders
                .of(this,new BookDetailViewModelFactory()).get(BookDetailViewModel.class);

        bookDetailViewModel.setUserId(userId);
        bookDetailViewModel.setOfficeId(officeId);

        final AnimatedExpandableListView elv_detail = findViewById(R.id.bookdetail_list);
        bookDetailViewModel.getUsefulInfo();

        bookDetailViewModel.getBookInfo().observe(this, new Observer<BookDetailResult>() {
            @Override
            public void onChanged(@Nullable final BookDetailResult bookDetailResult) {
                if(bookDetailResult == null)
                    return;

                if(bookDetailResult.getBookDetailView() != null)
                {
                    MissionDetailExpandableAdapter adapter =
                            new MissionDetailExpandableAdapter(bookDetailResult.getBookDetailView().getMissionDetailList(),
                                    BookDetailActivity.this);

                    adapter.setOnItemClick(new OnExpandItemClick() {
                        @Override
                        public void itemClick(int parentPos, int childPos, MissionDetail info,boolean isRelease) {
//                            final int userId = getIntent().getIntExtra("userId",-1);
//                            final int officeId = getIntent().getIntExtra("officeId",-1);
//                            final int businessId = getIntent().getIntExtra("businessId",-1);
//                            final long time = getIntent().getLongExtra("time",-1);

                            if(isRelease)
                                return;

                            Intent intent = new Intent(BookDetailActivity.this,OrderFinalCheckActivity.class);
                            intent.putExtra("userId", bookDetailViewModel.getUserId());
                            intent.putExtra("officeId",bookDetailViewModel.getOfficeId());
                            intent.putExtra("businessId",info.getBusinessId());
                            intent.putExtra("time",info.getOrderList().get(childPos).getTime());
                            startActivity(intent);
                            finish();
                        }
                    });

                    elv_detail.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                        @Override
                        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                            if(elv_detail.isGroupExpanded(groupPosition))
                                elv_detail.collapseGroupWithAnimation(groupPosition);
                            else
                                elv_detail.expandGroupWithAnimation(groupPosition);

                            return true;
                        }
                    });
                    elv_detail.setAdapter(adapter);
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
