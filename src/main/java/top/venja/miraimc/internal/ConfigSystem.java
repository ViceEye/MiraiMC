package top.venja.miraimc.internal;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginLogger;
import cn.nukkit.utils.Config;
import top.venja.miraimc.nukkit.PluginMain;
import top.venja.miraimc.nukkit.PluginEventHandler;
import top.venja.miraimc.nukkit.utils.Metrics;

import java.io.File;

public class ConfigSystem {

    public static Config config;
    public static File pluginDir;
    private static PluginMain plugin;
    private static PluginLogger logger;

    public ConfigSystem(PluginMain plugin){
        ConfigSystem.plugin = plugin;
        logger = PluginMain.Logger;
    }

    public void loadConfig() {
        pluginDir = plugin.getDataFolder();
        File configure = new File(pluginDir, "/config.yml");
        if(!(configure.exists())){ plugin.saveDefaultConfig(); }

        // 加载配置文件
        config = new Config(new File(pluginDir, "/config.yml"), Config.YAML);

        // bStats统计
        if(config.getBoolean("general.allow-bStats", true)) {
            int pluginId = 11534;
            new Metrics(plugin, pluginId);
        }

        // 安全警告
        if(!(config.getBoolean("general.disable-safe-warning-message",false))){
            logger.warning("确保您正在使用开源的MiraiMC插件，未知来源的插件可能会盗取您的账号！");
            logger.warning("请始终从Github或作者指定的其他途径下载插件: https://github.com/DreamVoid/MiraiMC");
        }

        if(config.getBoolean("bot.log-events",true)){
            Server.getInstance().getPluginManager().registerEvents(new PluginEventHandler(), plugin);
        }
    }

    public static void reloadConfig() {
        plugin.reloadConfig();
    }
}
