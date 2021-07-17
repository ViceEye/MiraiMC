package top.venja.miraimc.listener;

import net.mamoe.mirai.event.events.BotJoinGroupEvent;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.plugin.PluginEvent;
import cn.nukkit.plugin.Plugin;

public class MiraiGroupBotJoinGroupEvent extends PluginEvent{

    public MiraiGroupBotJoinGroupEvent(Plugin plugin, BotJoinGroupEvent event) {
        super(plugin);
        this.event = event;
    }

    private static final HandlerList handlers = new HandlerList();
    private final BotJoinGroupEvent event;

    public static HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }

    /**
     * 获取机器人账号
     * @return 机器人账号
     */
    public long getBotID() { return event.getBot().getId(); }

    /**
     * 返回加入群的群号
     * @return 群号
     */
    public long getGroupID() { return event.getGroupId(); }

}
