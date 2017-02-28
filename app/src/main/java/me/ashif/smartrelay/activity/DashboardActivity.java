package me.ashif.smartrelay.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import me.ashif.smartrelay.R;
import me.ashif.smartrelay.adapter.EquipAdapter;
import me.ashif.smartrelay.api.ApiManager;
import me.ashif.smartrelay.api.ApiService;
import me.ashif.smartrelay.databinding.ActivityDashboardBinding;
import me.ashif.smartrelay.listener.ButtonToggled;
import me.ashif.smartrelay.util.CommonUtils;
import me.ashif.smartrelay.util.StorageUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DashboardActivity extends AppCompatActivity implements ButtonToggled {

    private ActivityDashboardBinding mBinding;
    private ApiService mApiService;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        mApiService = ApiManager.getClient().create(ApiService.class);
        populateObjects();
    }

    private void populateObjects() {
        mProgressDialog = new ProgressDialog(this);
        ArrayList<String> equipList = new ArrayList<>();
        for (int i = 1; i < 17; i++) {
            equipList.add("Equipment " + i);
        }
        EquipAdapter equipAdapter = new EquipAdapter(equipList, getApplicationContext(), this);
        mBinding.listEquipcontrol.setAdapter(equipAdapter);
        mBinding.listEquipcontrol.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onToggleButtonClicked(final int relay_id, int value) {
        //TODO make on/off network calls

        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.show();

        Call<ResponseBody> call = mApiService.toggleRelay(relay_id, 123456, value);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //relay state changed
                mProgressDialog.dismiss();
                CommonUtils.displayToast("Relay " + relay_id + " switched", getApplicationContext());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //relay state change failed
                mProgressDialog.dismiss();
                CommonUtils.displayToast("Relay " + relay_id + " failed to switch", getApplicationContext());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(R.string.logout);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                StorageUtils.clearUserPreference(this);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
