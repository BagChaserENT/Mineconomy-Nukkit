package bagchaser.mineconomy.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

import cn.nukkit.Player;

import bagchaser.mineconomy.api.DataBase;

public class BalanceCommand extends Command {

    public BalanceCommand(String name){
        super(name, "Check your balance");
        this.setAliases(new String[]{"bal", "money"});
        this.setPermission("mineconomy.cmd");
    }

    public boolean execute(CommandSender sender, String label, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("You must be a player to use this command!");
            return false;
        }
        Player player = (Player) sender;
        int balance = DataBase.getInstance().getBalance(player.getName());
        String format = DataBase.getInstance().format(balance);
        player.sendMessage("Your balance is: " + format);
        return true;
    }
}