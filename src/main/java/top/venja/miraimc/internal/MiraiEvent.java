package top.venja.miraimc.internal;

import cn.nukkit.Server;
import cn.nukkit.plugin.Plugin;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.events.*;
import top.venja.miraimc.listener.*;

public class MiraiEvent {
    
    Plugin plugin;

    public MiraiEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    private Listener BotOnlineListener;
    private Listener BotOfflineActiveListener;
    private Listener BotOfflineForceListener;
    private Listener BotOfflineDroppedListener;
    private Listener BotOfflineRequireReconnectListener;
    private Listener BotReloginEventListener;
    private Listener BotAvatarChangedEventListener;
    private Listener BotNickChangedEventListener;

    private Listener GroupMessageListener;
    private Listener FriendMessageListener;
    private Listener GroupTempMessageEventListener;
    private Listener StrangerMessageEventListener;
    private Listener OtherClientMessageEventListener;

    private Listener GroupMessagePreSendEventListener;
    private Listener FriendMessagePreSendEventListener;
    private Listener GroupTempMessagePreSendEventListener;
    private Listener StrangerMessagePreSendEventListener;

    private Listener GroupMessagePostSendEventListener;
    private Listener FriendMessagePostSendEventListener;
    private Listener GroupTempMessagePostSendEventListener;
    private Listener StrangerMessagePostSendEventListener;

    private Listener FriendMessageRecallEventListener;
    private Listener GroupMessageRecallEventListener;

    private Listener BeforeImageUploadEventListener;
    private Listener ImageUploadSucceedEventListener;
    private Listener ImageUploadFailedEventListener;
    private Listener NudgeEventListener;

    private Listener BotLeaveActiveEventListener;
    private Listener BotLeaveKickEventListener;
    private Listener BotGroupPermissionChangeEventListener;
    private Listener BotMuteEventListener;
    private Listener BotUnmuteEventListener;
    private Listener BotJoinGroupEventListener;

    private Listener MemberJoinInviteEventListener;
    private Listener MemberJoinActiveEventListener;
    private Listener MemberLeaveKickEventListener;
    private Listener MemberLeaveQuitEventListener;
    private Listener MemberJoinRequestEventListener;
    private Listener BotInvitedJoinGroupRequestEventListener;

    public void startListenEvent(){
        // Bot
        BotOnlineListener = GlobalEventChannel.INSTANCE.subscribeAlways(BotOnlineEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiBotOnlineEvent(plugin, event)));
        BotOfflineActiveListener = GlobalEventChannel.INSTANCE.subscribeAlways(BotOfflineEvent.Active.class,event -> Server.getInstance().getPluginManager().callEvent(new MiraiBotOfflineEvent(plugin, event, "Active")));
        BotOfflineForceListener = GlobalEventChannel.INSTANCE.subscribeAlways(BotOfflineEvent.Force.class,event -> Server.getInstance().getPluginManager().callEvent(new MiraiBotOfflineEvent(plugin, event, "Force")));
        BotOfflineDroppedListener = GlobalEventChannel.INSTANCE.subscribeAlways(BotOfflineEvent.Dropped.class,event -> Server.getInstance().getPluginManager().callEvent(new MiraiBotOfflineEvent(plugin, event, "Dropped")));
        BotOfflineRequireReconnectListener = GlobalEventChannel.INSTANCE.subscribeAlways(BotOfflineEvent.RequireReconnect.class,event -> Server.getInstance().getPluginManager().callEvent(new MiraiBotOfflineEvent(plugin, event, "RequireReconnect")));
        BotReloginEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(BotReloginEvent.class,event -> Server.getInstance().getPluginManager().callEvent(new MiraiBotReloginEvent(plugin, event)));
        BotAvatarChangedEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(BotAvatarChangedEvent.class,event -> Server.getInstance().getPluginManager().callEvent(new MiraiBotAvatarChangedEvent(plugin, event)));
        BotNickChangedEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(BotNickChangedEvent.class,event -> Server.getInstance().getPluginManager().callEvent(new MiraiBotNickChangedEvent(plugin, event)));

        // 消息
        // - 被动
        GroupMessageListener = GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiGroupMessageEvent(plugin, event)));
        FriendMessageListener = GlobalEventChannel.INSTANCE.subscribeAlways(FriendMessageEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiFriendMessageEvent(plugin, event)));
        GroupTempMessageEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(GroupTempMessageEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiGroupTempMessageEvent(plugin, event)));
        StrangerMessageEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(StrangerMessageEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiStrangerMessageEvent(plugin, event)));
        OtherClientMessageEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(OtherClientMessageEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiOtherClientMessageEvent(plugin, event)));
        // - 主动前
        GroupMessagePreSendEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessagePreSendEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiGroupMessagePreSendEvent(plugin, event)));
        FriendMessagePreSendEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(FriendMessagePreSendEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiFriendMessagePreSendEvent(plugin, event)));
        GroupTempMessagePreSendEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(GroupTempMessagePreSendEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiGroupTempMessagePreSendEvent(plugin, event)));
        StrangerMessagePreSendEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(StrangerMessagePreSendEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiStrangerMessagePreSendEvent(plugin, event)));
        // - 主动后
        GroupMessagePostSendEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessagePostSendEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiGroupMessagePostSendEvent(plugin, event)));
        FriendMessagePostSendEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(FriendMessagePostSendEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiFriendMessagePostSendEvent(plugin, event)));
        GroupTempMessagePostSendEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(GroupTempMessagePostSendEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiGroupTempMessagePostSendEvent(plugin, event)));
        StrangerMessagePostSendEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(StrangerMessagePostSendEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiStrangerMessagePostSendEvent(plugin, event)));
        // - 撤回
        FriendMessageRecallEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(MessageRecallEvent.FriendRecall.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiFriendMessageRecallEvent(plugin, event)));
        GroupMessageRecallEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(MessageRecallEvent.GroupRecall.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiGroupMessageRecallEvent(plugin, event)));
        // - 图片上传
        // -- 图片上传前
        BeforeImageUploadEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(BeforeImageUploadEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiBeforeImageUploadEvent(plugin, event)));
        // -- 图片上传完成
        ImageUploadSucceedEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(ImageUploadEvent.Succeed.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiImageUploadSucceedEvent(plugin, event)));
        ImageUploadFailedEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(ImageUploadEvent.Failed.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiImageUploadFailedEvent(plugin, event)));
        // - 戳一戳
        NudgeEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(NudgeEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiNudgeEvent(plugin, event)));

        // 群
        BotLeaveActiveEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(BotLeaveEvent.Active.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiGroupBotLeaveEvent(plugin, event, event)));
        BotLeaveKickEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(BotLeaveEvent.Kick.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiGroupBotLeaveEvent(plugin, event, event)));
        BotGroupPermissionChangeEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(BotGroupPermissionChangeEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiGroupBotPermissionChangeEvent(plugin, event)));
        BotMuteEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(BotMuteEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiGroupBotMuteEvent(plugin, event)));
        BotUnmuteEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(BotUnmuteEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiGroupBotUnmuteEvent(plugin, event)));
        BotJoinGroupEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(BotJoinGroupEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiGroupBotJoinGroupEvent(plugin, event)));
        // - 群成员
        // -- 成员列表变更
        MemberJoinInviteEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(MemberJoinEvent.Invite.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiGroupMemberJoinEvent(plugin, event, event)));
        MemberJoinActiveEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(MemberJoinEvent.Active.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiGroupMemberJoinEvent(plugin, event, event)));
        MemberLeaveKickEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(MemberLeaveEvent.Kick.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiGroupMemberLeaveEvent(plugin, event, event)));
        MemberLeaveQuitEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(MemberLeaveEvent.Quit.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiGroupMemberLeaveEvent(plugin, event, event)));
        MemberJoinRequestEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(MemberJoinRequestEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiGroupMemberJoinRequestEvent(plugin, event)));
        BotInvitedJoinGroupRequestEventListener = GlobalEventChannel.INSTANCE.subscribeAlways(BotInvitedJoinGroupRequestEvent.class, event -> Server.getInstance().getPluginManager().callEvent(new MiraiGroupBotInvitedJoinGroupRequestEvent(plugin, event)));


    }

    public void stopListenEvent(){
        BotOnlineListener.complete();
        BotOfflineActiveListener.complete();
        BotOfflineForceListener.complete();
        BotOfflineDroppedListener.complete();
        BotOfflineRequireReconnectListener.complete();
        BotReloginEventListener.complete();
        BotAvatarChangedEventListener.complete();
        BotNickChangedEventListener.complete();

        GroupMessageListener.complete();
        FriendMessageListener.complete();
        GroupTempMessageEventListener.complete();
        StrangerMessageEventListener.complete();
        OtherClientMessageEventListener.complete();

        GroupMessagePreSendEventListener.complete();
        FriendMessagePreSendEventListener.complete();
        GroupTempMessagePreSendEventListener.complete();
        StrangerMessagePreSendEventListener.complete();

        GroupMessagePostSendEventListener.complete();
        FriendMessagePostSendEventListener.complete();
        GroupTempMessagePostSendEventListener.complete();
        StrangerMessagePostSendEventListener.complete();

        FriendMessageRecallEventListener.complete();
        GroupMessageRecallEventListener.complete();

        BeforeImageUploadEventListener.complete();
        ImageUploadSucceedEventListener.complete();
        ImageUploadFailedEventListener.complete();

        NudgeEventListener.complete();

        BotLeaveActiveEventListener.complete();
        BotLeaveKickEventListener.complete();
        BotGroupPermissionChangeEventListener.complete();
        BotMuteEventListener.complete();
        BotUnmuteEventListener.complete();
        BotJoinGroupEventListener.complete();

        MemberJoinInviteEventListener.complete();
        MemberJoinActiveEventListener.complete();
        MemberLeaveKickEventListener.complete();
        MemberLeaveQuitEventListener.complete();
        MemberJoinRequestEventListener.complete();
        BotInvitedJoinGroupRequestEventListener.complete();
    }

}
