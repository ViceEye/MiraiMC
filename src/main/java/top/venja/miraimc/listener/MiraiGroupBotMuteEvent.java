package top.venja.miraimc.listener;

import net.mamoe.mirai.event.events.BotMuteEvent;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.plugin.PluginEvent;
import cn.nukkit.plugin.Plugin;

public class MiraiGroupBotMuteEvent extends PluginEvent{

    // 主动退群
    public MiraiGroupBotMuteEvent(Plugin plugin, BotMuteEvent event) {
        super(plugin);
        this.event = event;
    }


    private static final HandlerList handlers = new HandlerList();
    private final BotMuteEvent event;

    public static HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }

    /**
     * 获取机器人账号
     * @return 机器人账号
     */
    public long getBotID() { return event.getBot().getId(); }

    /**
     * 返回目标群的群号
     * @return 群号
     */
    public long getGroupID() { return event.getGroupId(); }

    /**
     * 返回机器人被禁言的时间
     * @return 时间(秒)
     */
    public int getTime() {
        return event.getDurationSeconds();
    }

    /**
     * 返回执行禁言操作的管理员。
     * @return 管理员QQ
     */
    public long getNewPermission() {
        return event.getOperator().getId();
    }
}
