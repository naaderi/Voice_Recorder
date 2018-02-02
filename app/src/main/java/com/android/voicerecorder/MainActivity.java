package com.android.voicerecorder;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import voicelist.CollectRecordedVoice;
import voicelist.VoiceListAdapter;
import voicelist.VoiceRecordedModel;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    //   private static TextView mPlayingVoiceTextview;
    private Button mRecordButton;
    private Button mStopRecordButton;

    private final Context mContext = MainActivity.this;
    private MediaRecorder mediaRecorder;
    private RecyclerView mVoiceListRecyclerView;
    private VoiceListAdapter mVoiceAdapter;
    List<VoiceRecordedModel> mVoiceRecordedModels = new ArrayList<>();
    private static MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        getData();

        mRecordButton.setOnClickListener(this);
        mStopRecordButton.setOnClickListener(this);
        setButtonStatus(mStopRecordButton, false);
    }

    private void getData() {
        if (getRecordedVoiceList() != null) {
            initAdapter(mVoiceRecordedModels);
            initRecyclerView(mVoiceAdapter);
        }
    }

    private List<VoiceRecordedModel> getRecordedVoiceList() {
        CollectRecordedVoice voice = new CollectRecordedVoice();
        mVoiceRecordedModels = voice.CollectAllRecordedVoice(mContext);
        return mVoiceRecordedModels;
    }

    private void initAdapter(List<VoiceRecordedModel> mVoiceRecordedModels) {
        // Toast.makeText(mContext, "init:" + mVoiceAdapter, Toast.LENGTH_SHORT).show();
        if (mVoiceAdapter != null) {
            mVoiceAdapter.addNewItem(getRecordedVoiceList());
        } else {
            mVoiceAdapter = new VoiceListAdapter(mContext, mVoiceRecordedModels);
        }
    }

    private void initRecyclerView(VoiceListAdapter mVoiceAdapter) {
        mVoiceListRecyclerView.setAdapter(mVoiceAdapter);
        setLayoutManager();
    }

    private void setLayoutManager() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mVoiceListRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void setButtonStatus(Button mButton, boolean status) {
        mButton.setEnabled(status);
    }

    private void findViews() {
        mRecordButton = findViewById(R.id.record_button);
        mStopRecordButton = findViewById(R.id.stopRecord_button);
        mVoiceListRecyclerView = findViewById(R.id.voiceList_recyclerview);
    }

    @Override
    public void onClick(View clickedButton) {
        switch (clickedButton.getId()) {
            case R.id.record_button:
                startRecording();
                break;
            case R.id.stopRecord_button: {
                getData();
                stopRecording();
                break;
            }
        }
    }

    private void startRecording() {
        if (checkPermission(mContext)) {
            makeDirectoryForVoiceRecord();
            MediaRecorderReady();
            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
            } catch (IllegalStateException e) {
                e.printStackTrace();
                Log.e(LOG_TAG, LOG_MESSAGE_RECORD_READY_ERROR + e);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(LOG_TAG, LOG_MESSAGE_RECORD_READY_ERROR + e);
            }
            setButtonStatus(mRecordButton, false);
            //  mRecordButton.setEnabled(false);
            setButtonStatus(mStopRecordButton, true);
            //    mStopRecordButton.setEnabled(true);
        } else {
            requestPermission((Activity) mContext);
        }
    }

    private void MediaRecorderReady() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(createRecordFileName());
    }

    private void stopRecording() {
        mediaRecorder.stop();
        setButtonStatus(mRecordButton, true);
        setButtonStatus(mStopRecordButton, false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                if (grantResults.length > 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;
                    if (StoragePermission && RecordPermission) {
                        showToast(mContext, PERMISSION_GRANTED);
                    } else {
                        showToast(mContext, PERMISSION_DENIED);
                    }
                }
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
