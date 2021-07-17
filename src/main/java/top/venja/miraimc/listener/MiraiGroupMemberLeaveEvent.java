package top.venja.miraimc.listener;

import net.mamoe.mirai.event.events.MemberLeaveEvent;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.plugin.PluginEvent;
import cn.nukkit.plugin.Plugin;

public class MiraiGroupMemberLeaveEvent extends PluginEvent{

    // 主动退群
    public MiraiGroupMemberLeaveEvent(Plugin plugin, MemberLeaveEvent event, MemberLeaveEvent.Quit eventQuit) {
        super(plugin);
        this.event = event;
        this.eventQuit = eventQuit;
        this.eventKick = null;
    }

    // 被踢出群
    public MiraiGroupMemberLeaveEvent(Plugin plugin, MemberLeaveEvent.Kick event, MemberLeaveEvent.Kick eventKick) {
        super(plugin);
        this.event = event;
        this.eventKick = eventKick;
        this.eventQuit = null;
    }

    private static final HandlerList handlers = new HandlerList();
    private final MemberLeaveEvent event;
    private final MemberLeaveEvent.Quit eventQuit;
    private final MemberLeaveEvent.Kick eventKick;

    public static HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }

    /**
     * 获取机器人账号
     * @return 机器人账号
     */
    public long getBotID() { return event.getBot().getId(); }

    /**
     * 返回退出群的群号
     * @return 群号
     */
    public long getGroupID() { return event.getGroupId(); }

    /**
     * 返回退群类型
     * @return Active - 主动退群 | Kick - 被踢出群
     */
    public String getType() {
        if(eventKick != null){
            return "Kick";
        } else return "Quit";
    }

    /**
     * 获取退出群的成员QQ
     * @return 成员QQ
     */
    public long getTargetID(){
        return event.getUser().getId();
    }
    /**
     * 返回操作管理员的QQ。
     * 如果成员为主动退群，则返回 0
     * @return 操作者ID
     */
    public long getOperator() {
        if(eventKick != null){
            return eventKick.getOperator().getId();
        } else return 0;
    }
}
