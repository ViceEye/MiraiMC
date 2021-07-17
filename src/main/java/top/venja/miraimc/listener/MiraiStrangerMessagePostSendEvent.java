package top.venja.miraimc.listener;

import net.mamoe.mirai.event.events.StrangerMessagePostSendEvent;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.plugin.PluginEvent;
import cn.nukkit.plugin.Plugin;

public class MiraiStrangerMessagePostSendEvent extends PluginEvent {

    public MiraiStrangerMessagePostSendEvent(Plugin plugin, StrangerMessagePostSendEvent event) {
        super(plugin);
        this.event = event;
    }

    private static final HandlerList handlers = new HandlerList();
    private final StrangerMessagePostSendEvent event;

    public static HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }

    /**
     * 返回发送这条信息的机器人ID
     * @return 机器人ID
     */
    public long getBotID(){
        return event.getBot().getId();
    }

    /**
     * 返回接收这条信息的目标QQ
     * @return 目标ID
     */
    public long getTargetID(){
        return event.getTarget().getId();
    }

    /**
     * 返回接收这条信息的目标昵称
     * @return 目标昵称
     */
    public String getTargetNick(){
        return event.getTarget().getNick();
    }

    /**
     * 返回接收者的备注名
     * @return 备注名
     */
    public String getFriendRemark(){ return event.getTarget().getRemark(); }

    /**
     * 返回将发送的消息内容
     * @return 消息内容
     */
    public String getMessage(){
        return event.getMessage().contentToString();
    }

}
