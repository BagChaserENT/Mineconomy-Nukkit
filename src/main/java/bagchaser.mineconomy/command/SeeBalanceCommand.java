package bagchaser.mineconomy.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;

import cn.nukkit.Player;

import bagchaser.mineconomy.api.DataBase;

public class SeeBalanceCommand extends Command {

    public SeeBalanceCommand(String name){
        super(name, "Check someone's balance");
        this.setAliases(new String[]{"seemoney", "see", "seebal"});
        this.setPermission("mineconomy.cmd");
        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{
            new CommandParameter("player", CommandParamType.TARGET, false)
        });
    }

    public boolean execute(CommandSender sender, String label, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("You must be a player to use this command!");
            return false;
        }
        if(args.length == 0){
            sender.sendMessage("Usage: /seebalance <player>");
            return false;
        }
        if(DataBase.getInstance().isNew(args[0])){
           sender.sendMessage("Player not found!");
           return false;
        }
        Player player = (Player) sender;
        int balance = DataBase.getInstance().getBalance(args[0]);
        String format = DataBase.getInstance().format(balance);
        player.sendMessage(args[0] + " balance is: " + format);
        return true;
    }
}