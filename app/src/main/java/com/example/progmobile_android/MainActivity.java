package com.example.progmobile_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.progmobile_android.model.ManagerFacade;
import com.example.progmobile_android.model.entities.Event;
import com.example.progmobile_android.model.entities.User;
import com.example.progmobile_android.model.entities.UserToken;
import com.example.progmobile_android.model.repository.ServerCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView, txtViewUser, txtViewToken;
    private ManagerFacade managerFacade;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        managerFacade = ManagerFacade.getInstance(this);

        textView = findViewById(R.id.test);
        imageView = findViewById(R.id.imageView);

        txtViewUser = findViewById(R.id.txtViewUser);
        txtViewToken = findViewById(R.id.txtViewToken);

//        //Exemplo de como usar o CallBack
//        managerFacade.getListEvents(new ServerCallback() {
//            @SuppressWarnings({"unchecked", "RedundantCast"})
//            @Override
//            public void onSuccess(Object object) {
//                textView.setText(((List<Event>) object).toString());
//            }
//        });
//
//        //Exemplo carregar imagem evento
//        String url = "https://thegraphicsfairy.com/wp-content/uploads/blogger/_CarNcodpCMA/S-xGzI1quqI/AAAAAAAAHcw/6OV1SRWgWFI/s1600/ticket-graphicsfairy002d.jpg";
//        Picasso.get().load(url).into(imageView);


//        managerFacade.login("hueligton", "teste", object -> {
//            UserToken userToken = (UserToken) object;
//            User user = userToken.getUser();
//            String token = userToken.getToken();
//
//            txtViewUser.setText(user.toString());
//            txtViewToken.setText(token);
//
//        });

    }
}
