package top.venja.miraimc.listener;

import net.mamoe.mirai.event.events.StrangerMessageEvent;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.plugin.PluginEvent;
import cn.nukkit.plugin.Plugin;

public class MiraiStrangerMessageEvent extends PluginEvent {

    public MiraiStrangerMessageEvent(Plugin plugin, StrangerMessageEvent event) {
        super(plugin);
        this.event = event;
    }

    private static final HandlerList handlers = new HandlerList();
    private final StrangerMessageEvent event;

    public static HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }

    /**
     * 返回接收到这条信息的机器人ID
     * @return 机器人ID
     */
    public long getBotID(){
        return event.getBot().getId();
    }

    /**
     * 返回发送这条信息的发送者ID
     * @return 发送者ID
     */
    public long getSenderID(){
        return event.getSender().getId();
    }

    /**
     * 返回发送这条信息的发送者昵称
     * @return 发送者昵称
     */
    public String getSenderNick(){
        return event.getSender().getNick();
    }

    /**
     * 返回接收到的消息内容
     * @return 消息内容
     */
    public String getMessage(){
        return event.getMessage().serializeToMiraiCode();
    }

    /**
     * 返回接收到这条信息的时间
     * @return 发送时间
     */
    public int getTime(){
        return event.getTime();
    }

}
