package com.example.iningke.myapplication.iflytek;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iningke.myapplication.R;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.sunflower.FlowerCollector;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IFlyTekSpeechActivity extends AppCompatActivity {

    @Bind(R.id.iFly_edit)
    EditText iFlyEdit;
    @Bind(R.id.iFly_btnSpeech)
    Button iFlyBtnSpeech;
    @Bind(R.id.iFly_btnCancel)
    Button iFlyBtnCancel;
    @Bind(R.id.iFly_btnPause)
    Button iFlyBtnPause;
    @Bind(R.id.iFly_btnResume)
    Button iFlyBtnResume;
    private SpeechSynthesizer mTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ifly_tek_speech);
        ButterKnife.bind(this);

        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        //1.创建SpeechSynthesizer对象, 第二个参数：本地合成时传InitListener
        mTts = SpeechSynthesizer.createSynthesizer(this, null);
        //2.合成参数设置，详见《科大讯飞MSC API手册(Android)》SpeechSynthesizer 类
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        // 设置在线合成发音人
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
        //设置合成语速
        mTts.setParameter(SpeechConstant.SPEED, "50");
        //设置合成音调
        mTts.setParameter(SpeechConstant.PITCH, "50");
        //设置合成音量
        mTts.setParameter(SpeechConstant.VOLUME, "50");
        //设置播放器音频流类型
        mTts.setParameter(SpeechConstant.STREAM_TYPE, "3");
        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");
        //设置合成音频保存位置（可自定义保存位置），保存在“./sdcard/iflytek.pcm”
        //保存在SD卡需要在AndroidManifest.xml添加写SD卡权限
        //如果不需要保存合成音频，注释该行代码
//        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
        //3.开始合成
//        mTts.startSpeaking("科大讯飞，让世界聆听我们的声音", mSynListener);
    }

    // 缓冲进度
    private int mPercentForBuffering = 0;
    // 播放进度
    private int mPercentForPlaying = 0;
    //合成监听器
    private SynthesizerListener mSynListener = new SynthesizerListener() {
        //会话结束回调接口，没有错误时，error为null
        public void onCompleted(SpeechError error) {
            if (error == null) {
                showTip("播放完成");
            } else if (error != null) {
                showTip(error.getPlainDescription(true));
            }
        }

        //缓冲进度回调
        //percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在文本中结束位置，info为附加信息。
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
            // 合成进度
            mPercentForBuffering = percent;
            showTip(String.format(getString(R.string.tts_toast_format),
                    mPercentForBuffering, mPercentForPlaying));
        }

        //开始播放
        public void onSpeakBegin() {
            showTip("开始播放");
        }

        //暂停播放
        public void onSpeakPaused() {
            showTip("暂停播放");
        }

        //播放进度回调
        //percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置.
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
            // 播放进度
            mPercentForPlaying = percent;
            showTip(String.format(getString(R.string.tts_toast_format),
                    mPercentForBuffering, mPercentForPlaying));
        }

        //恢复播放回调接口
        public void onSpeakResumed() {
            showTip("继续播放");
        }

        //会话事件回调接口
        public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //		Log.d(TAG, "session id =" + sid);
            //	}
        }
    };
    private Toast mToast;

    private void showTip(final String str) {
        mToast.setText(str);
        mToast.show();
    }

    @OnClick({R.id.iFly_btnSpeech, R.id.iFly_btnCancel, R.id.iFly_btnPause, R.id.iFly_btnResume})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iFly_btnSpeech:
                // 移动数据分析，收集开始合成事件
                FlowerCollector.onEvent(IFlyTekSpeechActivity.this, "tts_play");

                String text = iFlyEdit.getText().toString();
                if (!TextUtils.isEmpty(text)) {
                    int code = mTts.startSpeaking(text, mSynListener);
                    if (code != ErrorCode.SUCCESS) {
                        if (code == ErrorCode.ERROR_COMPONENT_NOT_INSTALLED) {
                            //未安装则跳转到提示安装页面
//                            mInstaller.install();
                        } else {
                            showTip("语音合成失败,错误码: " + code);
                        }
                    }
                }
                break;
            case R.id.iFly_btnCancel:
                mTts.stopSpeaking();
                break;
            case R.id.iFly_btnPause:
                mTts.pauseSpeaking();
                break;
            case R.id.iFly_btnResume:
                mTts.resumeSpeaking();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTts.stopSpeaking();
        // 退出时释放连接
        mTts.destroy();
    }

    @Override
    protected void onResume() {
        //移动数据统计分析
        FlowerCollector.onResume(IFlyTekSpeechActivity.this);
        FlowerCollector.onPageStart(IFlyTekSpeechActivity.class.getName());
        super.onResume();
    }

    @Override
    protected void onPause() {
        //移动数据统计分析
        FlowerCollector.onPageEnd(IFlyTekSpeechActivity.class.getName());
        FlowerCollector.onPause(IFlyTekSpeechActivity.this);
        super.onPause();
    }
}
