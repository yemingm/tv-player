package com.upyun.tvplayer.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.upyun.tvplayer.R;
import com.upyun.tvplayer.adapter.ProgramListAdapter;
import com.upyun.tvplayer.model.Category;
import com.upyun.tvplayer.model.Program;

import org.greenrobot.eventbus.EventBus;

public class ProgramFragment extends Fragment implements AdapterView.OnItemClickListener {

    public void setProgram(Program mProgram) {
        this.mProgram = mProgram;
    }

    private Program mProgram;
    private ProgramListAdapter mProgramListAdapter;

    public static ProgramFragment newInstance(Program program) {
        ProgramFragment programFragment = new ProgramFragment();
        programFragment.setProgram(program);
        return programFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_program, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ListView lvProgram = (ListView) view.findViewById(R.id.lv_program);
        mProgramListAdapter = new ProgramListAdapter(mProgram, getContext());
        lvProgram.setAdapter(mProgramListAdapter);
        lvProgram.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        EventBus.getDefault().post(mProgramListAdapter.getItem(position));
    }
}
