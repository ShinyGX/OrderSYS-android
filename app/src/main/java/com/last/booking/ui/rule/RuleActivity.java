package com.last.booking.ui.rule;

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
import com.last.booking.data.model.RuleInfo;
import com.last.booking.ui.rule.adapter.OnRuleItemClick;
import com.last.booking.ui.rule.adapter.RuleExpandableListViewAdapter;
import com.last.booking.ui.ruleDetail.RuleDetailActivity;

public class RuleActivity extends AppCompatActivity {

    private RuleViewModel ruleViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);

        ruleViewModel = ViewModelProviders.of(this,new RuleViewModelFactory()).get(RuleViewModel.class);


        final AnimatedExpandableListView aelv_rule = findViewById(R.id.rule_list);

        ruleViewModel.getEachOffice();

        ruleViewModel.getEachOfficeRuleResult().observe(this, new Observer<EachOfficeRuleResult>() {
            @Override
            public void onChanged(@Nullable EachOfficeRuleResult eachOfficeRuleResult) {
                if(eachOfficeRuleResult == null)
                    return;

                if(eachOfficeRuleResult.getError() != null)
                    Toast.makeText(getApplicationContext(),eachOfficeRuleResult.getError(), Toast.LENGTH_SHORT)
                            .show();


                if(eachOfficeRuleResult.getInfoList() != null)
                {
                     RuleExpandableListViewAdapter adapter =
                            new RuleExpandableListViewAdapter(eachOfficeRuleResult.getInfoList(),RuleActivity.this);

                     adapter.setOnRuleItemClick(new OnRuleItemClick() {
                         @Override
                         public void onClick(RuleInfo info) {
                             String ruleName = info.getCom_rule_name();
                             String ruleText = info.getCom_rule_text();


                             Intent intent = new Intent(RuleActivity.this, RuleDetailActivity.class);
                             intent.putExtra("rule_name",ruleName);
                             intent.putExtra("rule_text",ruleText);
                             startActivity(intent);
                             finish();
                         }
                     });

                    aelv_rule.setAdapter(adapter);
                    aelv_rule.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                        @Override
                        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                            if(aelv_rule.isGroupExpanded(groupPosition))
                                aelv_rule.collapseGroupWithAnimation(groupPosition);
                            else
                                aelv_rule.expandGroupWithAnimation(groupPosition);

                            return true;
                        }
                    });

                }
            }
        });

    }
}
