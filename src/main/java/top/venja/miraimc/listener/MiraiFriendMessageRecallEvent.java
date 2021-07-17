package top.venja.miraimc.listener;

import net.mamoe.mirai.event.events.MessageRecallEvent;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.plugin.PluginEvent;
import cn.nukkit.plugin.Plugin;

public class MiraiFriendMessageRecallEvent extends PluginEvent {

    public MiraiFriendMessageRecallEvent(Plugin plugin, MessageRecallEvent.FriendRecall event) {
        super(plugin);
        this.event = event;
    }

    private static final HandlerList handlers = new HandlerList();
    private final MessageRecallEvent.FriendRecall event;

    public static HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }

    /**
     * 获取机器人账号
     * @return 机器人账号
     */
    public long getID() { return event.getBot().getId(); }

    /**
     * 获取被撤回信息的发送者昵称
     * @return 发送者昵称
     */
    public String getSenderNick() { return event.getAuthor().getNick(); }

    /**
     * 获取被撤回信息的发送者ID
     * @return 发送者ID
     */
    public long getSenderID() { return event.getAuthor().getId(); }

    /**
     * 获取撤回信息的操作者昵称
     * @return 操作者昵称
     */
    public String getOperatorNick() { return event.getOperator().getNick(); }

    /**
     * 获取撤回信息的操作者ID
     * @return 操作者ID
     */
    public long getOperatorID() { return event.getOperatorId(); }

    /**
     * 获取信息发送时间
     * @return 发送时间
     */
    public long getMessageTime() { return event.getMessageTime(); }

    /**
     * (?)获取信息编号
     * @return 信息编号
     */
    public int[] getMessageIds() { return event.getMessageIds(); }

}
