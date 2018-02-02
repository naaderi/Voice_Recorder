package com.android.voicerecorder;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by Nazila on 1/30/2018.
 */

public class BaseActivity extends AppCompatActivity {
    public static final int REQUEST_PERMISSION_CODE = 1;
    public static final String LOG_TAG = "MEDIA RECORDER";
    public static final String LOG_MESSAGE_PLAY_ERROR = "Can not play recorded file.";

    public static final String SLASH = "/";
    public static final String DIRECTORY_FOR_VOICE = "VOICE_RECORDER";
    public static String FILE_NAME_PREFIX = "Voice_";
    public static String FILE_NAME_POSTFIX = ".3gp";
    public static String PREFERENCES_KEY = "fileIndex";
    public static final String AUDIO_FILE_PATH = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + SLASH;


    protected static void requestPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, REQUEST_PERMISSION_CODE);
    }

    protected static boolean checkPermission(Context mContext) {
        int result = ContextCompat.checkSelfPermission(mContext,
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(mContext,
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

    public static void makeDirectoryForVoiceRecord() {
        File voiceDirectory = new File(AUDIO_FILE_PATH, DIRECTORY_FOR_VOICE);
        if (!voiceDirectory.exists()) {
            voiceDirectory.mkdirs();
        }
    }

    public String createRecordFileName() {
        int lastIndex = readLastFileNameIndex();
        String fileName = AUDIO_FILE_PATH + DIRECTORY_FOR_VOICE + SLASH + FILE_NAME_PREFIX + lastIndex + FILE_NAME_POSTFIX;
        return fileName;
    }

    public void saveFileNameIndex(int index) {
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putInt(PREFERENCES_KEY, index).apply();
    }

    public int readLastFileNameIndex() {
        int index = PreferenceManager.getDefaultSharedPreferences(this)
                .getInt(PREFERENCES_KEY, 0);
        index = index + 1;
        saveFileNameIndex(index);
        return index;
    }

}
