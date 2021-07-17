package top.venja.miraimc.nukkit;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginLogger;
import top.venja.miraimc.api.MiraiBot;
import top.venja.miraimc.internal.ConfigSystem;
import top.venja.miraimc.internal.MiraiAutoLogin;
import top.venja.miraimc.internal.MiraiEvent;
import net.mamoe.mirai.Bot;

public class PluginMain extends PluginBase {

    private MiraiEvent MiraiEvent;
    private MiraiBot MiraiBot;
    private ConfigSystem config;
    public static PluginLogger Logger;
    public MiraiAutoLogin MiraiAutoLogin;

    @Override // 加载插件
    public void onLoad() {
        Logger = this.getLogger();
        this.config = new ConfigSystem(this);
        this.MiraiEvent = new MiraiEvent(this);
        this.MiraiBot = new MiraiBot();
        this.MiraiAutoLogin = new MiraiAutoLogin(this);
    }

    @Override // 启用插件
    public void onEnable() {
        config.loadConfig();

        Logger.info("Mirai working dir: " + ConfigSystem.config.getString("general.mirai-working-dir", "default"));

        Logger.info("Starting bot event listener.");
        MiraiEvent.startListenEvent();

        Logger.info("Registering commands.");
        this.getServer().getCommandMap().register("mirai", new MiraiCommandHandler(this));
        this.getServer().getCommandMap().register("miraimc", new PluginCommandHandler(this));

        Logger.info("Loading auto-login file.");
        MiraiAutoLogin.loadFile();
        MiraiAutoLogin.doStartUpAutoLogin(); // 服务器启动完成后执行自动登录机器人

        Logger.info("All tasks done. Welcome to use MiraiMC!");
    }

    @Override // 禁用插件
    public void onDisable() {
        Logger.info("Stopping bot event listener.");
        MiraiEvent.stopListenEvent();

        Logger.info("Closing all bots");
        for (long bots : MiraiBot.getOnlineBots()){ MiraiBot.doBotLogout(Bot.getInstance(bots)); }

        Logger.info("All tasks done. Thanks for use MiraiMC!");
    }


}
