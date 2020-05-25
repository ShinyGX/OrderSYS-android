package com.last.booking.ui.rule;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.last.booking.data.RepositoryCallback;
import com.last.booking.data.RuleRepository;
import com.last.booking.data.model.RuleEachOfficeInfo;

import java.util.List;

public class RuleViewModel extends ViewModel {

    private MutableLiveData<EachOfficeRuleResult> eachOfficeRuleResult = new MutableLiveData<>();

    private RuleRepository repository;

    public RuleViewModel(RuleRepository repository) {
        this.repository = repository;
    }


    public void getEachOffice()
    {
        repository.getAll(new RepositoryCallback<List<RuleEachOfficeInfo>>() {
            @Override
            public void success(List<RuleEachOfficeInfo> data) {
                eachOfficeRuleResult.postValue(new EachOfficeRuleResult(data));
            }

            @Override
            public void failed(String msg) {
                eachOfficeRuleResult.postValue(new EachOfficeRuleResult(msg));
            }
        });
    }

    public MutableLiveData<EachOfficeRuleResult> getEachOfficeRuleResult() {
        return eachOfficeRuleResult;
    }
}
