package com.example.progmobile_android.view;

import android.view.Menu;
import android.view.MenuItem;

import com.example.progmobile_android.R;

public class CustomMenu {

    private static CustomMenu customMenu;
    private boolean bLogin;

    private CustomMenu() {
        bLogin = true;
    }

    public static CustomMenu getCustomMenu() {
        if (customMenu == null) {
            customMenu = new CustomMenu();
        }
        return customMenu;
    }

    public void updateMenu(Menu menu, boolean bLogin) {
        this.bLogin = bLogin;

        MenuItem login = menu.findItem(R.id.action_login);
        login.setVisible(bLogin);

        MenuItem logout = menu.findItem(R.id.action_logout);
        logout.setVisible(!bLogin);
    }

    public boolean isbLogin() {
        return bLogin;
    }
}
