package com.example.progmobile_android.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.ManagerFacade;
import com.example.progmobile_android.model.repository.ServerCallback;

public class Home extends AppCompatActivity {

    private Menu menu;
    private ManagerFacade managerFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);
        setSupportActionBar(findViewById(R.id.toolbar));

        managerFacade = ManagerFacade.getInstance(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_login) {
            startActivityForResult(new Intent(this, Login.class), 0);
            return true;
        } else if (item.getItemId() == R.id.action_logout) {
            managerFacade.logout(new ServerCallback() {
                @Override
                public void onSuccess(Object object) {
                    updateMenu(true);
                }

                @Override
                public void onError(Object object) {
                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buyTickets(View view) {
        startActivity(new Intent(this, EventList.class));
    }

    public void showMyTickets(View view) {
        // startActivity(new Intent(this, MyTickets.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            this.updateMenu(false);
        }
    }

    private void updateMenu(boolean bLogin) {
        MenuItem login = menu.findItem(R.id.action_login);
        login.setVisible(bLogin);

        MenuItem logout = menu.findItem(R.id.action_logout);
        logout.setVisible(!bLogin);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        managerFacade.getUser(new ServerCallback() {
            @Override
            public void onSuccess(Object object) {
                updateMenu(false);
            }

            @Override
            public void onError(Object object) {
                updateMenu(true);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        managerFacade.logout(new ServerCallback() {
            @Override
            public void onSuccess(Object object) {
                updateMenu(true);
            }

            @Override
            public void onError(Object object) {
            }
        });
    }
}
