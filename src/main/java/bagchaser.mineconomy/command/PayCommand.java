package bagchaser.mineconomy.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;

import cn.nukkit.Player;

import bagchaser.mineconomy.api.DataBase;

public class PayCommand extends Command {

    public PayCommand(String name){
        super(name, "Pay someone money");
        this.setAliases(new String[]{"pay"});
        this.setPermission("mineconomy.cmd");
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
            sender.sendMessage("Usage: /pay <player> <money>");
            return false;
        }
        if(DataBase.getInstance().isNew(args[0])){
            sender.sendMessage("Player not found!");
            return false;
        }
        Player player = (Player) sender;
        int money = Integer.parseInt(args[1]);
        if(money < 0){
          sender.sendMessage("You can't pay negative money!");
          return false;
        }
        if(DataBase.getInstance().getBalance(sender.getName()) < money){
          sender.sendMessage("You don't have enough money!");
          return false;
        }
        DataBase.getInstance().removeMoney(player.getName(), money);
        DataBase.getInstance().addMoney(args[0], money);
        String format = DataBase.getInstance().format(money);
        player.sendMessage("You have paid " + format + " to " + args[0] + "!");
        return true;
    }
}