package bagchaser.mineconomy;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;

import cn.nukkit.Player;

import bagchaser.mineconomy.api.DataBase;

public class EventListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(DataBase.getInstance().isNew(player.getName())){
            DataBase.getInstance().create(player.getName());
        }
    }
}