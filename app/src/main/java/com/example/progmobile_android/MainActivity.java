package com.example.progmobile_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.progmobile_android.model.ModelFacade;
import com.example.progmobile_android.model.entities.Event;
import com.example.progmobile_android.model.repository.ServerCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ModelFacade modelFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modelFacade = new ModelFacade(this);

        textView = findViewById(R.id.test);

        //Exemplo de como usar o CallBack
        modelFacade.getListEvents(new ServerCallback() {
            @SuppressWarnings({"unchecked", "RedundantCast"})
            @Override
            public void onSuccess(Object object) {
                textView.setText(((List<Event>) object).toString());
            }
        });
    }
}
