package com.example.progmobile_android.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.progmobile_android.R;
import com.example.progmobile_android.model.ManagerFacade;
import com.example.progmobile_android.model.repository.ServerCallback;

public class BaseActivity extends AppCompatActivity {

    private CustomMenu customMenu = CustomMenu.getCustomMenu();
    private Menu menu;
    private ManagerFacade managerFacade = ManagerFacade.getInstance(this);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        customMenu.updateMenu(menu, customMenu.isNotLogged());
        getCustomMenu().adaptMenu(getMenu(), this.getClass().getSimpleName());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_home:
                Intent intent = new Intent(this, Home.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                return true;
            case R.id.action_login:
                startActivityForResult(new Intent(this, Login.class), 0);
                return true;
            case R.id.action_logout:
                managerFacade.logout(new ServerCallback() {
                    @Override
                    public void onSuccess(Object object) {
                        customMenu.updateMenu(menu, true);
                    }

                    @Override
                    public void onError(Object object) {
                    }
                });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            customMenu.updateMenu(menu, false);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        customMenu.updateMenu(menu, customMenu.isNotLogged());
    }

    public Menu getMenu() {
        return menu;
    }

    public CustomMenu getCustomMenu() {
        return customMenu;
    }

    public ManagerFacade getManagerFacade() {
        return managerFacade;
    }

}
