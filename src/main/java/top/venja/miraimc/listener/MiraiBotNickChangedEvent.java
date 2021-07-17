package top.venja.miraimc.listener;

import net.mamoe.mirai.event.events.BotNickChangedEvent;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.plugin.PluginEvent;
import cn.nukkit.plugin.Plugin;

public class MiraiBotNickChangedEvent extends PluginEvent {

    public MiraiBotNickChangedEvent(Plugin plugin, BotNickChangedEvent event) {
        super(plugin);
        this.event = event;
    }

    private static final HandlerList handlers = new HandlerList();
    private final BotNickChangedEvent event;

    public static HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }

    /**
     * 获取机器人账号
     * @return 机器人账号
     */
    public long getID() { return event.getBot().getId(); }

    /**
     * 获取机器人更换前的昵称
     * @return 机器人更换前的昵称
     */
    public String getOldNick() { return event.getFrom(); }

    /**
     * 获取机器人更换后的昵称
     * @return 机器人更换后的昵称
     */
    public String getNewNick() { return event.getTo(); }
}
