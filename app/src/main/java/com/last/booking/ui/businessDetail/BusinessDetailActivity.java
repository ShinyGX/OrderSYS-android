package com.last.booking.ui.businessDetail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.last.booking.OnRecyclerItemClickListener;
import com.last.booking.R;
import com.last.booking.data.model.BusinessInfo;
import com.last.booking.ui.businessDetail.adapter.BusinessInfoListAdapter;
import com.last.booking.ui.singleBusinessDetail.SingleBusinessDetailActivity;

import java.util.List;

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
                    final List<BusinessInfo> info = businessInfoResult.getInfoList();
                    BusinessInfoListAdapter adapter = new BusinessInfoListAdapter(businessInfoResult.getInfoList());
                    adapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
                        @Override
                        public void onItemClick(View view) {
                            int pos = recyclerView.getChildAdapterPosition(view);

                            String businessName = info.get(pos).getBusiness_desc();
                            String businessDetail = info.get(pos).getBusiness_detail();

                            Intent intent = new Intent(BusinessDetailActivity.this, SingleBusinessDetailActivity.class);
                            intent.putExtra("name",businessName);
                            intent.putExtra("detail",businessDetail);
                            startActivity(intent);
                        }
                    });
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BusinessDetailActivity.this);
                    recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(BusinessDetailActivity.this,
                            R.anim.layout_anim_fall_down));
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);

                }
            }
        });



    }
}
