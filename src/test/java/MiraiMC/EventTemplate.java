package MiraiMC;

import cn.nukkit.event.HandlerList;
import cn.nukkit.event.plugin.PluginEvent;
import cn.nukkit.plugin.Plugin;
import net.mamoe.mirai.event.events.BotOnlineEvent;

/**
 * 快速创建Mirai事件
 * 基于已经完成的BotOnlineEvent制作
 * 所有Mirai事件均有方法getBot，因此使用子方法getId作为基础方法
 * @author DreamVoid
 */
public class EventTemplate extends PluginEvent {
    public EventTemplate(Plugin plugin, BotOnlineEvent event) {
        super(plugin);
        this.event = event;
    }

    private static final HandlerList handlers = new HandlerList();
    private final BotOnlineEvent event;

    public static HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }

    /**
     * 获取机器人账号
     * @return 机器人账号
     */
    public long getID() { return event.getBot().getId(); }


}
