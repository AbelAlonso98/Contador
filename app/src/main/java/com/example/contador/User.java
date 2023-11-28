package com.example.contador;

import java.io.Serializable;

public class User implements Serializable {
    String user, password, money, clickValue, autoClickValue,  upgradePrecioClick, upgradePrecioAutoClick, upgradePrecioSpeed;

    int autoClickTime, upgradeNivelClick, upgradeNivelAutoClick, upgradeNivelSpeed;
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

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getClickValue() {
        return clickValue;
    }

    public void setClickValue(String clickValue) {
        this.clickValue = clickValue;
    }

    public String getAutoClickValue() {
        return autoClickValue;
    }

    public void setAutoClickValue(String autoClickValue) {
        this.autoClickValue = autoClickValue;
    }

    public int getAutoClickTime() {
        return autoClickTime;
    }

    public void setAutoClickTime(int autoClickTime) {
        this.autoClickTime = autoClickTime;
    }

    public String getUpgradePrecioClick() {
        return upgradePrecioClick;
    }

    public void setUpgradePrecioClick(String upgradePrecioClick) {
        this.upgradePrecioClick = upgradePrecioClick;
    }

    public String getUpgradePrecioAutoClick() {
        return upgradePrecioAutoClick;
    }

    public void setUpgradePrecioAutoClick(String upgradePrecioAutoClick) {
        this.upgradePrecioAutoClick = upgradePrecioAutoClick;
    }

    public String getUpgradePrecioSpeed() {
        return upgradePrecioSpeed;
    }

    public void setUpgradePrecioSpeed(String upgradePrecioSpeed) {
        this.upgradePrecioSpeed = upgradePrecioSpeed;
    }

    public int getUpgradeNivelClick() {
        return upgradeNivelClick;
    }

    public void setUpgradeNivelClick(int upgradeNivelClick) {
        this.upgradeNivelClick = upgradeNivelClick;
    }

    public int getUpgradeNivelAutoClick() {
        return upgradeNivelAutoClick;
    }

    public void setUpgradeNivelAutoClick(int upgradeNivelAutoClick) {
        this.upgradeNivelAutoClick = upgradeNivelAutoClick;
    }

    public int getUpgradeNivelSpeed() {
        return upgradeNivelSpeed;
    }

    public void setUpgradeNivelSpeed(int upgradeNivelSpeed) {
        this.upgradeNivelSpeed = upgradeNivelSpeed;
    }
}
