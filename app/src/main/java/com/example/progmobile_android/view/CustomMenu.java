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

    public void adaptMenu(Menu menu, String name) {
        MenuItem home = menu.findItem(R.id.action_home);
        MenuItem search = menu.findItem(R.id.action_search);

        switch (name) {
            case "Home":
                home.setVisible(false);
                search.setVisible(false);
                break;
            case "EventList":
                home.setVisible(true);
                search.setVisible(true);
                break;
            default:
                home.setVisible(true);
                search.setVisible(false);
        }
    }

    public boolean isNotLogged() {
        return isNotLogged;
    }
}
