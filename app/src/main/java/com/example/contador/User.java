package com.example.contador;

import java.io.Serializable;

public class User implements Serializable {
    private String user, password, money, clickValue, autoClickValue, upgradePrecioClick, upgradePrecioAutoClick, upgradePrecioSpeed;
    private int autoClickTime, upgradeNivelClick, upgradeNivelAutoClick, upgradeNivelSpeed;

    public User(String user, String password, String money, String clickValue, String autoClickValue, int autoClickTime, String upgradePrecioClick, String upgradePrecioAutoClick, String upgradePrecioSpeed, int upgradeNivelClick, int upgradeNivelAutoClick, int upgradeNivelSpeed) {
        this.user = user;
        this.password = password;
        this.money = money;
        this.clickValue = clickValue;
        this.autoClickValue = autoClickValue;
        this.autoClickTime = autoClickTime;
        this.upgradePrecioClick = upgradePrecioClick;
        this.upgradePrecioAutoClick = upgradePrecioAutoClick;
        this.upgradePrecioSpeed = upgradePrecioSpeed;
        this.upgradeNivelClick = upgradeNivelClick;
        this.upgradeNivelAutoClick = upgradeNivelAutoClick;
        this.upgradeNivelSpeed = upgradeNivelSpeed;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getMoney() {
        return money;
    }

    public String getClickValue() {
        return clickValue;
    }

    public String getAutoClickValue() {
        return autoClickValue;
    }

    public int getAutoClickTime() {
        return autoClickTime;
    }

    public String getUpgradePrecioClick() {
        return upgradePrecioClick;
    }

    public String getUpgradePrecioAutoClick() {
        return upgradePrecioAutoClick;
    }

    public String getUpgradePrecioSpeed() {
        return upgradePrecioSpeed;
    }

    public int getUpgradeNivelClick() {
        return upgradeNivelClick;
    }

    public int getUpgradeNivelAutoClick() {
        return upgradeNivelAutoClick;
    }

    public int getUpgradeNivelSpeed() {
        return upgradeNivelSpeed;
    }


}
