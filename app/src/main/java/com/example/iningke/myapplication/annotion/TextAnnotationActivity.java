package com.example.iningke.myapplication.annotion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.iningke.myapplication.R;

@InjectContentView(R.layout.activity_text_annotation)
public class TextAnnotationActivity extends AppCompatActivity {
    @InjectView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InjectUtils.injectAll(this);

        btn.setText("运行时注解InjectView（RUNTIME FIELD）");

    }

    @HxyOnClick(R.id.btn)
    public void click(View view) {
        Toast.makeText(TextAnnotationActivity.this, "事件注解ok", Toast.LENGTH_SHORT).show();
    }

    @HxyOnLongClick(R.id.btn)
    public boolean longClick(View view) {
        Toast.makeText(TextAnnotationActivity.this, "长按事件注解ok", Toast.LENGTH_SHORT).show();
        return true;
    }
}
