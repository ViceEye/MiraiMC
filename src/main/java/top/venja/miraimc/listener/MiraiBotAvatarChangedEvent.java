package top.venja.miraimc.listener;

import cn.nukkit.plugin.Plugin;
import net.mamoe.mirai.event.events.BotAvatarChangedEvent;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.plugin.PluginEvent;

public class MiraiBotAvatarChangedEvent extends PluginEvent {

    public MiraiBotAvatarChangedEvent(Plugin plugin, BotAvatarChangedEvent event) {
        super(plugin);
        this.event = event;
    }

    private static final HandlerList handlers = new HandlerList();
    private final BotAvatarChangedEvent event;

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

    /**
     * 获取机器人头像Url
     * @return 机器人头像Url
     */
    public String getAvatarUrl(){
        return event.getBot().getAvatarUrl();
    }

}
