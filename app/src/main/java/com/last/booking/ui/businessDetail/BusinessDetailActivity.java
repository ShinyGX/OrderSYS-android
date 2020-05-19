package com.last.booking.ui.businessDetail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.last.booking.R;
import com.last.booking.ui.businessDetail.adapter.BusinessInfoListAdapter;

public class BusinessDetailActivity extends AppCompatActivity {

    private BusinessDetailViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_detail);

        viewModel = ViewModelProviders.of(this,new BusinessDetailViewModelFactory())
                .get(BusinessDetailViewModel.class);


        viewModel.getAllBusiness();
        final RecyclerView recyclerView = findViewById(R.id.businessdetail_list);

        viewModel.getBusinessInfoResult().observe(this, new Observer<BusinessInfoResult>() {
            @Override
            public void onChanged(@Nullable BusinessInfoResult businessInfoResult) {
                if(businessInfoResult == null)
                    return;

                if(businessInfoResult.getError() != null)
                    Toast.makeText(
                            getApplicationContext(),businessInfoResult.getError(),Toast.LENGTH_SHORT).show();

                if(businessInfoResult.getInfoList() != null)
                {
                    BusinessInfoListAdapter adapter = new BusinessInfoListAdapter(businessInfoResult.getInfoList());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BusinessDetailActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                }
            }
        });



    }
}
