package com.android.voicerecorder;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends BaseActivity implements View.OnClickListener {
    private Button mRecodrButton;
 //   private final Context context=FirstActivity.this;
   // String path=AUDIO_FILE_PATH+DIRECTORY_FOR_VOICE;
    private TextView show;
    int username=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
     //   show = findViewById(R.id.show);

//        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                read();
//
//            }
//        });

    }
    public void makes(int username) {
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putInt("username", username).apply();
        Toast.makeText(this, "make:"+username, Toast.LENGTH_SHORT).show();
     //  read();

    }

    public void read(){
        int username =
                PreferenceManager.getDefaultSharedPreferences(this)
                        .getInt("username", 0);
        username=username+1;

           makes(username);
        Toast.makeText(this, username+"", Toast.LENGTH_SHORT).show();
    }

        // show.setText(findFiles()+"");
      //  File mpath=new File(Environment.getExternalStorageDirectory()
         //       .getAbsolutePath()+ SLASH+DIRECTORY_FOR_VOICE+SLASH);
//walkdir(mpath);


  //  }
  //  ArrayList<String> voiceList=new ArrayList<>();
   // File listFile[]={};
  //  ArrayList<String> absolutepath;
//    public void walkdir(File dir) {
//        File[] listFile = dir.listFiles();
//        if (listFile != null) {
//            for (int i = 0; i < listFile.length; i++) {
//                    if (listFile[i].getName().endsWith(FILE_NAME_POSTFIX)){
//                        voiceList.add(listFile[i].getName());
//                }
//            }
//       //     Toast.makeText(context, listFile[0].getName()+"", Toast.LENGTH_SHORT).show();
//        }

//    }
//        findViews();
//        mRecodrButton.setOnClickListener(this);
//    }
    private void findViews(){
        mRecodrButton=findViewById(R.id.record_button);
    }

    @Override
    public void onClick(View clickedButton) {
        switch (clickedButton.getId()) {
          //  case R.id.record_button:
              //  showToast(mContext, getString(R.string.record_button));
        }
    }
}
