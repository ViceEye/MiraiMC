package me.dreamvoid.miraimc.api;

import me.dreamvoid.miraimc.internal.Config;
import me.dreamvoid.miraimc.internal.Utils;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.utils.BotConfiguration;
import net.mamoe.mirai.utils.LoggerAdapters;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class MiraiBot {

    private final Logger Logger;
    private static MiraiBot instance;

    public MiraiBot() {
        this.Logger = Utils.getLogger();
        instance = this;
    }

    /**
     * 获取MiraiBot实例
     * @return MiraiBot 实例
     */
    public static MiraiBot getInstance(){
        return instance;
    }

    /**
     * 登录一个机器人账号
     * [!] 尚未完善多机器人管理。目前，不建议插件开发者在发行版插件中调用此接口
     * @param Account 机器人账号
     * @param Password 机器人密码
     * @param Protocol 协议类型
     */
    public void doBotLogin(long Account, String Password, BotConfiguration.MiraiProtocol Protocol) {
        privateBotLogin(Account, Password, Protocol);
    }

    /**
     * 登出一个机器人账号
     * [!] 尚未完善多机器人管理。目前，不建议插件开发者在发行版插件中调用此接口
     * @param Account 机器人账号
     */
    public void doBotLogout(long Account) {
        Bot bot = Bot.getInstanceOrNull(Account);
        if(isBotExist(bot)){
            assert bot != null;
            bot.close();
        }
    }
    /**
     * 登出一个机器人账号
     * [!] 尚未完善多机器人管理。目前，不建议插件开发者在发行版插件中调用此接口
     * @param Bot 机器人
     */
    public void doBotLogout(Bot Bot) {
        if(isBotExist(Bot)){
            assert Bot != null;
            Bot.close();
        }
    }

    /**
     * 向指定好友发送消息
     * @param BotAccount 机器人账号
     * @param FriendID 好友QQ号
     * @param Message 消息内容
     * @return 成功返回true，失败返回false (此方法若返回false，则指定的机器人账号不存在)
     */
    public boolean sendFriendMessage(long BotAccount, long FriendID, String Message){
        Bot bot = Bot.getInstanceOrNull(BotAccount);
        if(isBotExist(bot)) {
            assert bot != null;
            bot.getLogger().verbose("[MessageSend/"+BotAccount+"] "+ "Friend("+FriendID + ") <- "+Message);
            bot.getFriendOrFail(FriendID).sendMessage(Message);
            return true;
        } else {
            Logger.warning("Bot account \""+BotAccount+"\" doesn't exist!");
            return false;
        }
    }
    /**
     * 向指定好友发送消息
     * @param BotAccount 机器人账号
     * @param FriendID 好友QQ号
     * @param MessageChain 消息链内容
     * @return 成功返回true，失败返回false (此方法若返回false，则指定的机器人账号不存在)
     */
    public boolean sendFriendMessage(long BotAccount, long FriendID, MessageChain MessageChain){
        Bot bot = Bot.getInstanceOrNull(BotAccount);
        if(isBotExist(bot)) {
            assert bot != null;
            bot.getLogger().verbose("[MessageSend/"+BotAccount+"] "+ "Friend("+FriendID +") <- "+MessageChain.serializeToMiraiCode());
            bot.getFriendOrFail(FriendID).sendMessage(MessageChain);
            return true;
        } else {
            Logger.warning("Bot account \""+BotAccount+"\" doesn't exist!");
            return false;
        }
    }

    /**
     * 向指定群发送消息
     * @param BotAccount 机器人账号
     * @param GroupID 群号
     * @param Message 消息内容
     * @return 成功返回true，失败返回false (此方法若返回false，则指定的机器人账号不存在)
     */
    public boolean sendGroupMessage(long BotAccount, long GroupID, String Message){
        Bot bot = Bot.getInstanceOrNull(BotAccount);
        if(isBotExist(bot)){
            assert bot != null;
            bot.getLogger().verbose("[MessageSend/"+BotAccount+"] "+ "Group("+GroupID +") <- "+Message);
            bot.getGroupOrFail(GroupID).sendMessage(Message);
            return true;
        } else {
            Logger.warning("Bot account \""+BotAccount+"\" doesn't exist!");
            return false;
        }
    }

    /**
     * 向指定群发送消息
     * @param BotAccount 机器人账号
     * @param GroupID 群号
     * @param MessageChain 消息链内容
     * @return 成功返回true，失败返回false (此方法若返回false，则指定的机器人账号不存在)
     */
    public boolean sendGroupMessage(long BotAccount, long GroupID, MessageChain MessageChain){
        Bot bot = Bot.getInstanceOrNull(BotAccount);
        if(isBotExist(bot)){
            assert bot != null;
            bot.getLogger().verbose("[MessageSend/"+BotAccount+"] "+ "Group("+GroupID +") <- "+MessageChain.contentToString());
            bot.getGroupOrFail(GroupID).sendMessage(MessageChain);
            return true;
        } else {
            Logger.warning("Bot account \""+BotAccount+"\" doesn't exist!");
            return false;
        }
    }

    /**
     * 向指定好友发送戳一戳
     * @param BotAccount 机器人账号
     * @param FriendID 好友QQ号
     * @return 成功返回true，失败返回false
     */
    public boolean sendFriendNudge(long BotAccount, long FriendID){
        Bot bot = Bot.getInstanceOrNull(BotAccount);
        if(isBotExist(bot)){
            assert bot != null;
            bot.getLogger().verbose("[NudgeSend/"+BotAccount+"] "+ "Friend("+FriendID + ") <- ");
            return(bot.nudge().sendTo(bot.getFriendOrFail(FriendID)));
        } else {
            Logger.warning("Bot account \""+BotAccount+"\" doesn't exist!");
            return false;
        }
    }

    /**
     * 向指定群发送戳一戳
     * (!) 未经测试
     * @param BotAccount 机器人账号
     * @param GroupID 群号
     * @return 成功返回true，失败返回false
     */
    public boolean sendGroupNudge(long BotAccount, long GroupID){
        if(isBotExist(BotAccount)) {
            Bot bot = Bot.getInstanceOrNull(BotAccount);
            assert bot != null;
            bot.getLogger().verbose("[NudgeSend/" + BotAccount + "] " + "Group("+GroupID + ") <- ");
            return (bot.nudge().sendTo(bot.getGroupOrFail(GroupID)));
        } else {
            Logger.warning("Bot account \""+BotAccount+"\" doesn't exist!");
            return false;
        }
    }

    /**
     * 在指定群禁言指定成员(要求机器人为管理员或群主)
     * @param BotAccount 机器人账号
     * @param GroupID 群号
     * @param TargetID 被操作群员QQ号
     * @param Time 时间(秒)
     * @return 成功返回true，失败返回false (此方法若返回false，则指定的机器人账号不存在)
     */
    public boolean setGroupMemberMute(long BotAccount, long GroupID, long TargetID, int Time){
        Bot bot = Bot.getInstanceOrNull(BotAccount);
        if(isBotExist(bot)){
            assert bot != null;
            Group group = bot.getGroupOrFail(GroupID);
            bot.getLogger().verbose("[GroupMute/"+BotAccount+"] " +"Group("+GroupID+") Target(" + TargetID + ")"+" <- Mute ("+Time+"s)");
            group.getOrFail(TargetID).mute(Time);
            return true;
        } else {
            Logger.warning("Bot account \""+BotAccount+"\" doesn't exist!");
            return false;
        }
    }

    /**
     * 在指定群解除禁言指定成员(要求机器人为管理员或群主)
     * @param BotAccount 机器人账号
     * @param GroupID 群号
     * @param TargetID 被操作群员QQ号
     * @return 成功返回true，失败返回false (此方法若返回false，则指定的机器人账号不存在)
     */
    public boolean setGroupMemberUnmute(long BotAccount, long GroupID, long TargetID){
        Bot bot = Bot.getInstanceOrNull(BotAccount);
        if(isBotExist(bot)){
            assert bot != null;
            bot.getLogger().verbose("[GroupMute/"+BotAccount+"] " +"Group("+GroupID+") Target(" + TargetID + ")"+" <- Unmute");
            bot.getGroupOrFail(GroupID).getOrFail(TargetID).unmute();
            return true;
        } else {
            Logger.warning("Bot account \""+BotAccount+"\" doesn't exist!");
            return false;
        }
    }

    /**
     * 踢出指定群的指定成员(要求机器人为管理员或群主)
     * @param BotAccount 机器人账号
     * @param GroupID 群号
     * @param TargetID 被操作群员QQ号
     * @param Reason 理由
     * @return 成功返回true，失败返回false (此方法若返回false，则指定的机器人账号不存在)
     */
    public boolean setGroupMemberKick(long BotAccount, long GroupID, long TargetID, String Reason){
        Bot bot = Bot.getInstanceOrNull(BotAccount);
        if(isBotExist(bot)){
            assert bot != null;
            bot.getLogger().verbose("[GroupKick/"+BotAccount+"] " +"Group("+GroupID+") Target(" + TargetID + ")"+" <- "+Reason);
            bot.getGroupOrFail(GroupID).getOrFail(TargetID).kick(Reason);
            return true;
        } else {
            Logger.warning("Bot account \""+BotAccount+"\" doesn't exist!");
            return false;
        }
    }

    /**
     * 向指定群的指定成员发送消息
     * @param BotAccount 机器人账号
     * @param GroupID 群号
     * @param TargetID 群成员QQ号
     * @param Message 消息内容
     * @return 成功返回true，失败返回false (此方法若返回false，则指定的机器人不在线)
     */
    public boolean sendGroupMemberMessage(long BotAccount, long GroupID, long TargetID, String Message){
        if(isBotOnline(BotAccount)){
            Bot bot = Bot.getInstanceOrNull(BotAccount);
            assert bot != null;
            bot.getLogger().verbose("[MessageSend/"+BotAccount+"] " +"Group("+GroupID+") Target(" + TargetID + ")"+" <- " + Message);
            bot.getGroupOrFail(GroupID).getOrFail(TargetID).sendMessage(Message);
            return true;
        } else return false;
    }

    /**
     * 获取指定好友的昵称
     * 如果好友不存在，则返回空文本
     * @param BotAccount 机器人账号
     * @param Friend 好友QQ
     * @return 好友昵称
     */
    public String getFriendNick(long BotAccount, long Friend){
        if(isBotOnline(BotAccount)){
            Bot bot = Bot.getInstanceOrNull(BotAccount);
            assert bot != null;
            return bot.getFriendOrFail(Friend).getNick();
        } else return "";
    }

    /**
     * 获取指定好友的备注
     * 如果好友不存在或没有好友备注，则返回空文本
     * @param BotAccount 机器人账号
     * @param Friend 好友QQ
     * @return 好友备注
     */
    public String getFriendRemark(long BotAccount, long Friend){
        if(isBotOnline(BotAccount)){
            Bot bot = Bot.getInstanceOrNull(BotAccount);
            assert bot != null;
            return bot.getFriendOrFail(Friend).getRemark();
        } else return "";
    }

    /**
     * 判断指定群的指定成员是否被禁言
     * @param BotAccount 机器人账号
     * @param GroupID 群号
     * @param TargetID 被操作群员QQ号
     * @return 被禁言返回true，未被禁言false
     */
    public boolean isGroupMemberMuted(long BotAccount, long GroupID, long TargetID){
        Bot bot = Bot.getInstanceOrNull(BotAccount);
        if(isBotExist(bot)){
            assert bot != null;
            return bot.getGroupOrFail(GroupID).getOrFail(TargetID).isMuted();
        } else {
            Logger.warning("Bot account \""+BotAccount+"\" doesn't exist!");
            return false;
        }
    }

    /**
     * 判断机器人是否在线
     * @param Account 机器人账号
     * @return 在线返回true，离线返回false
     */
    public boolean isBotOnline(long Account){
        Bot bot=Bot.getInstanceOrNull(Account);
        if(isBotExist(bot)){
            assert bot != null;
            return bot.isOnline();
        } else {
            Logger.warning("Bot account \""+Account+"\" doesn't exist!");
            return false;
        }
    }

    /**
     * 获取所有在线的机器人
     * @return 机器人账号列表
     */
    public List<Long> getOnlineBots(){
        List<Long> BotList = new ArrayList<>();
        for (Bot bot : Bot.getInstances()) {
            BotList.add(bot.getId());
        }
        return BotList;
    }

    /**
     * 判断机器人是否存在
     * @param Account 机器人账号
     * @return 存在返回true，不存在返回false
     */
    public boolean isBotExist(long Account){ return !(Objects.equals(Bot.getInstanceOrNull(Account), null)); }
    /**
     * 判断机器人是否存在
     * @param bot 机器人
     * @return 存在返回true，不存在返回false
     */
    public boolean isBotExist(Bot bot) { return !(Objects.equals(bot, null)); }

    private void privateBotLogin(long Account, String Password, BotConfiguration.MiraiProtocol Protocol){

        Logger.info("登录新的机器人账号: "+ Account+", 协议: "+ Protocol.name());

        // 建立mirai数据文件夹
        File MiraiDir;
        if(!(Config.config.getString("general.mirai-working-dir", "default").equals("default"))){
            MiraiDir = new File(Config.config.getString("general.mirai-working-dir", "default"));
        } else {
            MiraiDir = new File(String.valueOf(Config.PluginDir),"MiraiBot");
        }
        if(!(MiraiDir.exists())){ if(!(MiraiDir.mkdir())) { Logger.warning("Unable to create folder: \"" + MiraiDir.getPath()+"\", make sure you have enough permission."); } }

        // 建立机器人账号文件夹
        File BotDir = new File(String.valueOf(MiraiDir),"bots");
        if(!(BotDir.exists())){ if(!(BotDir.mkdir())) { Logger.warning("Unable to create folder: \"" + MiraiDir.getPath()+"\", make sure you have enough permission."); } }

        // 建立当前机器人账号配置文件夹和相应的配置
        File BotConfig = new File(String.valueOf(BotDir), String.valueOf(Account));
        if(!(BotConfig.exists())){ if(!(BotConfig.mkdir())) { Logger.warning("Unable to create folder: \"" + MiraiDir.getPath()+"\", make sure you have enough permission."); } }

        // 登录前的准备工作
        Bot bot = BotFactory.INSTANCE.newBot(Account, Password, new BotConfiguration(){{
            // 设置登录信息
            setProtocol(Protocol); // 目前不打算让用户使用其他两个协议
            setWorkingDir(BotConfig);
            fileBasedDeviceInfo();

            // 是否关闭日志输出（不建议开发者关闭）
            if(Config.config.getBoolean("bot.disable-network-logs",false)) { noNetworkLog(); }
            if(Config.config.getBoolean("bot.disable-bot-logs",false)) { noBotLog(); }

            // 是否使用Bukkit的Logger接管Mirai的Logger
            if(Config.config.getBoolean("bot.use-bukkit-logger.bot-logs",true)) { setBotLoggerSupplier(bot -> LoggerAdapters.asMiraiLogger(Logger)); }
            if(Config.config.getBoolean("bot.use-bukkit-logger.network-logs",true)) { setNetworkLoggerSupplier(bot -> LoggerAdapters.asMiraiLogger(Logger)); }

            // 是否使用缓存——对于开发者，请启用；对于用户，请禁用。详见 https://github.com/mamoe/mirai/blob/dev/docs/Bots.md#%E5%90%AF%E7%94%A8%E5%88%97%E8%A1%A8%E7%BC%93%E5%AD%98
            getContactListCache().setFriendListCacheEnabled(Config.config.getBoolean("bot.contact-cache.enable-friend-list-cache",false));
            getContactListCache().setGroupMemberListCacheEnabled(Config.config.getBoolean("bot.contact-cache.enable-group-member-list-cache",false));
            getContactListCache().setSaveIntervalMillis(Config.config.getLong("bot.contact-cache.save-interval-millis",60000));

        }});

        // 开始登录
        bot.login();
        Logger.info(bot.getNick()+"("+bot.getId()+") 登录成功");


    }

}
