package bagchaser.mineconomy.api;

import java.util.Locale;
import java.text.NumberFormat;

import cn.nukkit.utils.Config;

import cn.nukkit.Player;

import bagchaser.mineconomy.Core;

public class DataBase {

    protected static DataBase instance;

    private Config config;

    public DataBase(){
        instance = this;
    }

    public void init(){
        this.config = new Config(Core.getInstance().getDataFolder() + "/data.yml", Config.YAML);
    }

    public static DataBase getInstance(){
        return instance;
    }

    public boolean isNew(String player){
        return !this.config.exists(player);
    }

    public void create(String player){
        this.config.set(player, 1000);
        this.config.save();
    }

    public int getBalance(String player){
        return this.config.getInt(player);
    }

    public void addMoney(String player, int money){
        this.config.set(player, this.config.getInt(player) + money);
        this.config.save();
    }

    public void removeMoney(String player, int money){
        this.config.set(player, this.config.getInt(player) - money);
        this.config.save();
    }

    public void setMoney(String player, int money){
        this.config.set(player, money);
        this.config.save();
    }

    public String format(int money){
        String format = NumberFormat.getNumberInstance(Locale.US).format(money);
        return "$" + format;
    }
}