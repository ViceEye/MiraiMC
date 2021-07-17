package top.venja.miraimc.listener;

import net.mamoe.mirai.event.events.MemberJoinEvent;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.plugin.PluginEvent;
import cn.nukkit.plugin.Plugin;

public class MiraiGroupMemberJoinEvent extends PluginEvent{

    public MiraiGroupMemberJoinEvent(Plugin plugin, MemberJoinEvent event, MemberJoinEvent.Active eventActive) {
        super(plugin);
        this.event = event;
        this.eventActive = eventActive;
        this.eventInvite = null;
    }
    public MiraiGroupMemberJoinEvent(Plugin plugin, MemberJoinEvent event, MemberJoinEvent.Invite eventInvite) {
        super(plugin);
        this.event = event;
        this.eventInvite = eventInvite;
        this.eventActive = null;
    }

    private static final HandlerList handlers = new HandlerList();
    private final MemberJoinEvent event;
    private final MemberJoinEvent.Active eventActive;
    private final MemberJoinEvent.Invite eventInvite;

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
     * 返回新成员的QQ号
     * @return 成员QQ号
     */
    public long getNewMemberID() { return event.getMember().getId(); }

    /**
     * 返回邀请者的QQ号
     * 如果成员为主动加群，则返回 0
     * @return 邀请者QQ号
     */
    public long getInviterID(){
        if(eventInvite != null){
            return eventInvite.getInvitor().getId();
        } else return 0;
    }

}
