package org.Sandship;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {
    private String userName;
    private String password;
    private List<Warehouse> warehouses;
    private StringBuilder news;

    public Player(String userName) {
        this.userName = userName;
        this.warehouses = new ArrayList<>();
        this.news = new StringBuilder();
    }

    public Player(String userName, String password) {
        this.userName = userName;
        this.password = PasswordUtil.hashingPassword(password);
        this.warehouses = new ArrayList<>();
        this.news = new StringBuilder();
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNews() {
        return news.toString();
    }

    public void eraseNews() {
        news.delete(0, news.length());
    }

    //method that updates user's information about new material type
    public void update(String materialName) {
        news.append("New material type was added ").append(materialName).append(".\n");
    }

    //method for verification of user who wants to log in
    public boolean verifyUser(String userName, String givenPassword) {
        return this.userName.equals(userName) && PasswordUtil.verification(givenPassword, this.password);
    }

    public void addWarehouse(Warehouse warehouse) {
        this.warehouses.add(warehouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, warehouses, news);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (! (object instanceof Player player)) {
            return false;
        }
        return this.userName.equals(player.getUserName()) && this.password.equals(player.password);
    }
}
