package com.example.iningke.myapplication;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.iningke.baseproject.utils.LogUtils;

import java.util.Locale;

/**
 * 根据文字 度语音
 *
 * @author hxy
 * @date 2017/2/14
 */

public class TextToSpeechActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private EditText editText;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);
        initView();
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.edit_speech);
        tts = new TextToSpeech(this, this);

    }

    public void onClickSpeech(View view) {
        // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
        tts.setPitch(1.0f);
        // 设置语速
        tts.setSpeechRate(0.3f);
        if (editText.getText().length() >= 1) {
            tts.speak(editText.getText().toString(),
                    TextToSpeech.QUEUE_FLUSH, null);
//            tts.speak(editText.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, "");
        } else {
            tts.speak("Nothing to say", TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void onInit(int status) {
        if (status == tts.SUCCESS) {
            // Toast.makeText(MainActivity.this,"成功输出语音",
            // Toast.LENGTH_SHORT).show();
            // Locale loc1=new Locale("us");
            // Locale loc2=new Locale("china");

            //TODO Android 系统自带的TTS (Text To Speak)语音库目前只有English ,German , Italian ,French ,Spanish(英文,德语,意大利语,法语,西班牙语)五个音库
            int result1 = tts.setLanguage(Locale.US);
            int result2 = tts.setLanguage(Locale.CHINA);
            if (result1 == TextToSpeech.LANG_MISSING_DATA
                    || result1 == TextToSpeech.LANG_NOT_SUPPORTED
                    || result2 == TextToSpeech.LANG_MISSING_DATA
                    || result2 == TextToSpeech.LANG_NOT_SUPPORTED) {
                LogUtils.e(status + "");
                Toast.makeText(this, "数据丢失或不支持", Toast.LENGTH_SHORT).show();
            }
//            if (result1 == TextToSpeech.LANG_MISSING_DATA
//                    || result1 == TextToSpeech.LANG_NOT_SUPPORTED) {
//                LogUtils.e(status + "");
//                Toast.makeText(this, "数据丢失或不支持", Toast.LENGTH_SHORT).show();
//            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}
