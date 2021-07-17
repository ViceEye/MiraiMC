package top.venja.miraimc.listener;

import net.mamoe.mirai.event.events.BotUnmuteEvent;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.plugin.PluginEvent;
import cn.nukkit.plugin.Plugin;

public class MiraiGroupBotUnmuteEvent extends PluginEvent{

    // 主动退群
    public MiraiGroupBotUnmuteEvent(Plugin plugin, BotUnmuteEvent event) {
        super(plugin);
        this.event = event;
    }


    private static final HandlerList handlers = new HandlerList();
    private final BotUnmuteEvent event;

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
     * 返回执行解除禁言操作的管理员。
     * @return 管理员QQ
     */
    public long getNewPermssion() {
        return event.getOperator().getId();
    }
}
