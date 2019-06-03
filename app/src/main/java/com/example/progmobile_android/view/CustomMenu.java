package com.example.progmobile_android.view;

import android.view.Menu;
import android.view.MenuItem;

import com.example.progmobile_android.R;

public class CustomMenu {

    private static CustomMenu customMenu;
    private boolean isNotLogged;

    private CustomMenu() {
        isNotLogged = true;
    }

    public static CustomMenu getCustomMenu() {
        if (customMenu == null) {
            customMenu = new CustomMenu();
        }
        return customMenu;
    }

    public void updateMenu(Menu menu, boolean isNotLogged) {
        this.isNotLogged = isNotLogged;

        MenuItem login = menu.findItem(R.id.action_login);
        login.setVisible(isNotLogged);

        MenuItem logout = menu.findItem(R.id.action_logout);
        logout.setVisible(!isNotLogged);
    }

    public boolean isNotLogged() {
        return isNotLogged;
    }
}
