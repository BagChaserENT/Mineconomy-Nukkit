package bagchaser.mineconomy;

import cn.nukkit.plugin.PluginBase;

import bagchaser.mineconomy.api.DataBase;

import bagchaser.mineconomy.command.BalanceCommand;
import bagchaser.mineconomy.command.SeeBalanceCommand;
import bagchaser.mineconomy.command.AddMoneyCommand;
import bagchaser.mineconomy.command.RemoveMoneyCommand;
import bagchaser.mineconomy.command.SetMoneyCommand;
import bagchaser.mineconomy.command.PayCommand;

public class Core extends PluginBase {

    protected static Core instance;

    private DataBase db;

    public void onLoad(){
        instance = this;
    }

    public void onEnable(){
        this.db = new DataBase();
        this.db.init();
        this.getServer().getPluginManager().registerEvents(new EventListener(), this);
        this.getServer().getCommandMap().registerAll("Mineconomy", java.util.Arrays.asList(
            new BalanceCommand("balance"),
            new SeeBalanceCommand("seebalance"),
            new AddMoneyCommand("addmoney"),
            new RemoveMoneyCommand("removemoney"),
            new SetMoneyCommand("setmoney"),
            new PayCommand("pay")
        ));
    }

    public static Core getInstance(){
        return instance;
    }
}