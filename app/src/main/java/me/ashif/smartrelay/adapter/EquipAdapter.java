package me.ashif.smartrelay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.ashif.smartrelay.R;
import me.ashif.smartrelay.databinding.EquipSwitchesLayoutBinding;
import me.ashif.smartrelay.listener.ButtonToggled;
import me.ashif.smartrelay.util.CommonUtils;

/**
 * Created by asif on 27/2/17.
 */

public class EquipAdapter extends RecyclerView.Adapter{

    private ArrayList<String> mEquipList;
    private Context mContext;
    private ButtonToggled mCallback;

    public EquipAdapter(ArrayList<String> mEquipList, Context mContext, ButtonToggled callback) {
        this.mEquipList = mEquipList;
        this.mContext = mContext;
        this.mCallback = callback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        EquipSwitchesLayoutBinding equipSwitchesLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.equip_switches_layout,parent,false);
        return new EquipViewHolder(equipSwitchesLayoutBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((EquipViewHolder) holder).mBinding.setEquip(mEquipList.get(position));
    }

    @Override
    public int getItemCount() {
        return mEquipList.size();
    }

    private class EquipViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        EquipSwitchesLayoutBinding mBinding;
        public EquipViewHolder(EquipSwitchesLayoutBinding equipSwitchesLayoutBinding) {
            super(equipSwitchesLayoutBinding.getRoot());
            this.mBinding = equipSwitchesLayoutBinding;

            mBinding.switchEquip1.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.switch_equip1:
//                    CommonUtils.displayToast("switching " + mBinding.switchEquip1.getText().toString()
//                            + " " +mEquipList.get(getLayoutPosition()),mContext);
                    switch (mBinding.switchEquip1.getText().toString()){
                        case "ON":
                            //relay is on
                            //switching off the relay
                            mCallback.onToggleButtonClicked(getAdapterPosition()+1,0);
                            break;
                        case "OFF":
                            //relay is off
                            //switching on the relay
                            mCallback.onToggleButtonClicked(getAdapterPosition()+1,1);
                            break;
                    }
            }
        }
    }
}
