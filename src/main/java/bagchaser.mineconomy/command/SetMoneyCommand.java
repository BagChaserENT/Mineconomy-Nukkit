package bagchaser.mineconomy.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;

import cn.nukkit.Player;

import bagchaser.mineconomy.api.DataBase;

public class SetMoneyCommand extends Command {

    public SetMoneyCommand(String name){
        super(name, "Set someone's money");
        this.setAliases(new String[]{"setmoney", "set"});
        this.setPermission("mineconomy.op");
        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{
            new CommandParameter("player", CommandParamType.TARGET, false),
            new CommandParameter("money", CommandParamType.INT, false)
        });
    }

    public boolean execute(CommandSender sender, String label, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage("You must be a player to use this command!");
            return false;
        }
        if(args.length < 2){
            sender.sendMessage("Usage: /setmoney <player> <money>");
            return false;
        }
        if(DataBase.getInstance().isNew(args[0])){
            sender.sendMessage("Player not found!");
            return false;
        }
        Player player = (Player) sender;
        int money = Integer.parseInt(args[1]);
        if(money < 0){
            sender.sendMessage("You can't set negative money!");
            return false;
        }
        DataBase.getInstance().setMoney(args[0], money);
        String format = DataBase.getInstance().format(money);
        player.sendMessage("You have set " + args[0] + "'s money to " + format + "!");
        return true;
    }
}