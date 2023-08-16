package com.ahmedmhassaan.orangetask.presentation.base.activity;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.ahmedmhassaan.orangetask.presentation.base.LocatableActivity;


public abstract class BaseActivityBinding<BINDING extends ViewDataBinding> extends LocatableActivity {


    BINDING binding;


    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, getLayoutId());
    }


    @LayoutRes
    public abstract int getLayoutId();


    public BINDING getBinding() {
        return binding;
    }

    @Override
    protected void onDestroy() {
        if (binding != null) {
            binding.unbind();
            binding = null;
        }

        super.onDestroy();
    }
}
