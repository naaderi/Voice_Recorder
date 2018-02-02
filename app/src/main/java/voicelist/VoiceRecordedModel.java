package voicelist;

/**
 * Created by Nazila on 2/1/2018.
 */

public class VoiceRecordedModel {
    private String voiceName;
    private String voicePath;

    public String getVoiceName() {
        return voiceName;
    }

    public void setVoiceName(String voiceName) {
        this.voiceName = voiceName;
    }

    public String getVoicePath() {
        return voicePath;
    }

    public void setVoicePath(String voicePath) {
        this.voicePath = voicePath;
    }

    @Override
    public String toString() {
        return "VoiceRecordedModel{" +
                "voiceName='" + voiceName + '\'' +
                ", voicePath='" + voicePath + '\'' +
                '}';
    }
}
