package me.ashif.smartrelay.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import me.ashif.smartrelay.R;
import me.ashif.smartrelay.databinding.ActivityMainBinding;
import me.ashif.smartrelay.util.CommonUtils;
import me.ashif.smartrelay.util.StorageUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setListeners();
        if (StorageUtils.fetchBoolean("LOGIN_STATUS", this)) {
            launchDashboard();
        }
    }

    private void setListeners() {
        mBinding.buttonSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_submit:
                if (mBinding.inputUsername.getText().toString().isEmpty() ||
                        mBinding.inputPassword.getText().toString().isEmpty()) {
                    CommonUtils.displayToast(getString(R.string.parameter_missing), this);
                } else if (mBinding.inputUsername.getText().toString().equals("ashif") &&
                        mBinding.inputPassword.getText().toString().equals("9947753535")) {
                    StorageUtils.storeBoolean("LOGIN_STATUS", true, this);
                    launchDashboard();
                } else {
                    CommonUtils.displayToast("bad username/password", this);
                }
        }
    }

    private void launchDashboard() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}
