package top.venja.miraimc.listener;

import net.mamoe.mirai.event.events.BotGroupPermissionChangeEvent;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.plugin.PluginEvent;
import cn.nukkit.plugin.Plugin;

public class MiraiGroupBotPermissionChangeEvent extends PluginEvent{

    // 主动退群
    public MiraiGroupBotPermissionChangeEvent(Plugin plugin, BotGroupPermissionChangeEvent event) {
        super(plugin);
        this.event = event;
    }


    private static final HandlerList handlers = new HandlerList();
    private final BotGroupPermissionChangeEvent event;

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
     * 返回机器人的原有权限。
     * @return 0 - 普通成员 | 1 - 管理员 | 2 - 群主
     */
    public int getOriginPermission() {
        return event.getOrigin().getLevel();
    }

    /**
     * 返回机器人的新权限。
     * @return 0 - 普通成员 | 1 - 管理员 | 2 - 群主
     */
    public int getNewPermssion() {
        return event.getNew().getLevel();
    }
}
