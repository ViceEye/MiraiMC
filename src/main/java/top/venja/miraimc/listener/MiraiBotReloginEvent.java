package top.venja.miraimc.listener;

import net.mamoe.mirai.contact.ContactList;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.BotReloginEvent;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.plugin.PluginEvent;
import cn.nukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class MiraiBotReloginEvent extends PluginEvent {

    public MiraiBotReloginEvent(Plugin plugin, BotReloginEvent event) {
        super(plugin);
        this.event = event;
    }

    private static final HandlerList handlers = new HandlerList();
    private final BotReloginEvent event;

    public static HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }

    /**
     * 获取机器人账号
     * @return 机器人账号
     */
    public long getID() { return event.getBot().getId(); }

    /**
     * 获取机器人昵称
     * @return 机器人昵称
     */
    public String getNick() { return event.getBot().getNick(); }

}
