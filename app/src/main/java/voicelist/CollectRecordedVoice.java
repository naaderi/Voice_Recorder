package voicelist;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.android.voicerecorder.BaseActivity.DIRECTORY_FOR_VOICE;
import static com.android.voicerecorder.BaseActivity.FILE_NAME_POSTFIX;
import static com.android.voicerecorder.BaseActivity.SLASH;

/**
 * Created by Nazila on 1/31/2018.
 */

public class CollectRecordedVoice {
    List<VoiceRecordedModel> voiceRecordedModels = new ArrayList<VoiceRecordedModel>();
    File dir = new File(Environment.getExternalStorageDirectory()
            .getAbsolutePath() + SLASH + DIRECTORY_FOR_VOICE + SLASH);
    public List<VoiceRecordedModel> CollectAllRecordedVoice(Context mContext) {

        File[] listFile = dir.listFiles();
        if (listFile != null) {
            for (int i = 0; i <listFile.length; i++) {
                if (listFile[i].getName().endsWith(FILE_NAME_POSTFIX)) {
                    VoiceRecordedModel voiceModel = new VoiceRecordedModel();
                    voiceModel.setVoiceName(listFile[i].getName());
                    voiceModel.setVoicePath(listFile[i].getPath());
                    voiceRecordedModels.add(voiceModel);
                }
            }
            return voiceRecordedModels;
        } else
            return null;
    }
}
