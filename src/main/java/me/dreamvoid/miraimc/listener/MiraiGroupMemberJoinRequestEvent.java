package me.dreamvoid.miraimc.listener;

import net.mamoe.mirai.event.events.MemberJoinRequestEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class MiraiGroupMemberJoinRequestEvent extends Event{

    public MiraiGroupMemberJoinRequestEvent(MemberJoinRequestEvent event) {
        super(true);
        this.event = event;
    }

    private static final HandlerList handlers = new HandlerList();
    private final MemberJoinRequestEvent event;

    public @NotNull HandlerList getHandlers() { return handlers; }
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
     * 返回目标群的群名称
     * @return 群名称
     */
    public String getGroupName() { return event.getGroupName(); }

    /**
     * 返回请求加群的成员的QQ号
     * @return 成员QQ号
     */
    public long getRequestMemberID() { return event.getFromId(); }

    /**
     * 返回邀请者的QQ号
     * 如果没有邀请者，则返回 0
     * @return 邀请者QQ号
     */
    public long getInviterID(){
        if(event.getInvitorId() != null) {
            return event.getInvitorId();
        } else return 0;
    }

    /**
     * 获取事件ID用于处理加群事件
     * @return 事件ID
     */
    public long getEventID(){ return event.getEventId(); }

    /**
     * 获取加群时填写的附言
     * @return 附言
     */
    public String getMessage(){ return event.getMessage(); }

    /**
     * 同意请求
     */
    public void setAccept(){ event.accept(); Bukkit.getLogger().info("[EventInvite/"+getBotID()+"] "+ getGroupID()+"("+ getRequestMemberID() +"|"+getInviterID()+") <- Accept"); return;}

    /**
     * 忽略请求
     * @param setBlacklist 是否拒绝目标再次申请加群
     */
    public void setIgnore(boolean setBlacklist){ event.ignore(setBlacklist);Bukkit.getLogger().info("[EventInvite/"+getBotID()+"] "+ getGroupID()+"("+getRequestMemberID() +"|"+getInviterID()+") <- Deny"); return;}


}
