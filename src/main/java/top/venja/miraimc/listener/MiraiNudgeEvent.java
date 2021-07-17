package top.venja.miraimc.listener;

import net.mamoe.mirai.event.events.BotReloginEvent;
import net.mamoe.mirai.event.events.NudgeEvent;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.plugin.PluginEvent;
import cn.nukkit.plugin.Plugin;

public class MiraiNudgeEvent extends PluginEvent {

    public MiraiNudgeEvent(Plugin plugin, NudgeEvent event) {
        super(plugin);
        this.event = event;
    }

    private static final HandlerList handlers = new HandlerList();
    private final NudgeEvent event;

    public static HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }

    /**
     * 获取机器人账号
     * @return 机器人账号
     */
    public long getID() { return event.getBot().getId(); }

    /**
     * 获取发送者ID
     * @return 发送者ID
     */
    public long getFromID() { return event.getFrom().getId(); }

    /**
     * 获取发送者昵称
     * @return 发送者昵称
     */
    public String getFromNick() { return event.getFrom().getNick(); }

    /**
     * 获取接收者ID
     * @return 接收者ID
     */
    public long getTargetID() { return event.getTarget().getId(); }

    /**
     * 获取接收者昵称
     * @return 接收者昵称
     */
    public String getTargetNick() { return event.getTarget().getNick(); }

    /**
     * (?)获取操作
     * @return 操作内容
     */
    public String getAction(){return event.getAction();}

    /**
     * (?)获取后缀
     * @return 后缀内容
     */
    public String getSuffix(){return event.getSuffix();}

}
