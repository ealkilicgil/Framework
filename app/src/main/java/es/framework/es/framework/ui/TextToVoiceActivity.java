package es.framework.es.framework.ui;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import es.framework.R;


public class TextToVoiceActivity extends Activity {

    TextToSpeech tts;
    int result;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_voice);

        editText=(EditText)findViewById(R.id.textSpeak);
        tts=new TextToSpeech(TextToVoiceActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status== TextToSpeech.SUCCESS){
                    result= tts.setLanguage(Locale.ENGLISH);
                }else
                {
                    Toast.makeText(getApplicationContext(), "Feature Not Supported", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void doSomething(View view) {
        switch(view.getId())
        {
            case R.id.speakButton:
                if(result==TextToSpeech.LANG_NOT_SUPPORTED||result==TextToSpeech.LANG_MISSING_DATA) {
                    Toast.makeText(getApplicationContext(), "Feature Not Supported", Toast.LENGTH_SHORT).show();
                }else{
                    tts.speak(editText.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,"");

                }
                break;
            case R.id.stopButton:
                if(tts!=null)
                {
                    tts.stop();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(tts!=null)
        {
            tts.stop();
            tts.shutdown();
        }
    }
}
