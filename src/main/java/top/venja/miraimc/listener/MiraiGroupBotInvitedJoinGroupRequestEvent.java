package top.venja.miraimc.listener;

import net.mamoe.mirai.event.events.BotInvitedJoinGroupRequestEvent;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.plugin.PluginEvent;
import cn.nukkit.plugin.Plugin;
import top.venja.miraimc.nukkit.PluginMain;

public class MiraiGroupBotInvitedJoinGroupRequestEvent extends PluginEvent{

    public MiraiGroupBotInvitedJoinGroupRequestEvent(Plugin plugin, BotInvitedJoinGroupRequestEvent event) {
        super(plugin);
        this.event = event;
    }

    private static final HandlerList handlers = new HandlerList();
    private final BotInvitedJoinGroupRequestEvent event;

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
     * 返回目标群的群名称
     * @return 群名称
     */
    public String getGroupName() { return event.getGroupName(); }

    /**
     * 返回邀请者的昵称
     * @return 邀请者昵称
     */
    public String getInviterNick() { return event.getInvitorNick(); }

    /**
     * 返回邀请者的QQ号
     * @return 邀请者QQ号
     */
    public long getInviterID(){ return event.getInvitorId(); }

    /**
     * 获取事件ID用于处理加群事件
     * @return 事件ID
     */
    public long getEventID(){ return event.getEventId(); }

    /**
     * 同意请求
     */
    public void setAccept(){ event.accept(); PluginMain.Logger.info("[EventInvite/"+getBotID()+"] "+ getGroupID()+"("+getBotID() +"|"+getInviterID()+") <- Accept"); return;}

    /**
     * 忽略请求
     */
    public void setIgnore(){ event.ignore(); PluginMain.Logger.info("[EventInvite/"+getBotID()+"] "+ getGroupID()+"("+getBotID() +"|"+getInviterID()+") <- Ignore"); return;}


}
