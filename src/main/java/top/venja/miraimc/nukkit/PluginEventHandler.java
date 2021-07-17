package top.venja.miraimc.nukkit;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import top.venja.miraimc.listener.MiraiFriendMessageEvent;
import top.venja.miraimc.listener.MiraiGroupMessageEvent;

public class PluginEventHandler implements Listener {

    public PluginEventHandler(){}

    @EventHandler
    public void onMiraiGroupMessageReceived(MiraiGroupMessageEvent e){
        PluginMain.Logger.info("[GroupMessage/"+e.getBotID()+"] ["+e.getGroupName()+"("+e.getGroupID()+")] "+e.getSenderNameCard()+"("+e.getSenderID()+") -> "+e.getMessage());
    }

    @EventHandler
    public void onMiraiFriendMessageReceived(MiraiFriendMessageEvent e){
        PluginMain.Logger.info("[FriendMessage/"+e.getBotID()+"] "+e.getSenderNick()+"("+e.getSenderID()+") -> "+e.getMessage());
    }

}
