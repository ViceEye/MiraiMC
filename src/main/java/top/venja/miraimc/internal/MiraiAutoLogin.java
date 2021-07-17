package top.venja.miraimc.internal;

import cn.nukkit.plugin.PluginLogger;
import cn.nukkit.scheduler.NukkitRunnable;
import cn.nukkit.utils.Config;
import top.venja.miraimc.api.MiraiBot;
import top.venja.miraimc.nukkit.PluginMain;
import net.mamoe.mirai.utils.BotConfiguration;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiraiAutoLogin {

    public MiraiAutoLogin(PluginMain plugin) {
        this.plugin = plugin;
        this.Logger = PluginMain.Logger;
    }

    private final PluginMain plugin;
    private final PluginLogger Logger;
    private static File AutoLoginFile;

    public void loadFile() {
        File MiraiDir;
        if(!(ConfigSystem.config.getString("general.mirai-working-dir", "default").equals("default"))){
            MiraiDir = new File(ConfigSystem.config.getString("general.mirai-working-dir", "default"));
        } else {
            MiraiDir = new File(String.valueOf(ConfigSystem.pluginDir),"MiraiBot");
        }
        if(!(MiraiDir.exists())){ if(!(MiraiDir.mkdir())) { Logger.warning("Unable to create folder: \"" + MiraiDir.getPath()+"\", make sure you have enough permission."); } }

        // 建立配置文件夹
        File ConfigDir = new File(String.valueOf(MiraiDir),"config");
        if(!(ConfigDir.exists())){ if(!(ConfigDir.mkdir())) { Logger.warning("Unable to create folder: \"" + MiraiDir.getPath()+"\", make sure you have enough permission."); } }

        // 建立控制台文件夹
        File ConsoleDir = new File(String.valueOf(ConfigDir), "Console");
        if(!(ConsoleDir.exists())){ if(!(ConsoleDir.mkdir())) { Logger.warning("Unable to create folder: \"" + MiraiDir.getPath()+"\", make sure you have enough permission."); } }

        // 建立自动登录文件
        AutoLoginFile = new File(ConsoleDir, "AutoLogin.yml");
        if(!AutoLoginFile.exists()) {
            try {
                AutoLoginFile.createNewFile();
                String defaulttext = "accounts: "+System.getProperty("line.separator");
                File writeName = AutoLoginFile;
                try (FileWriter writer = new FileWriter(writeName);
                     BufferedWriter out = new BufferedWriter(writer)
                ) {
                    out.write(defaulttext);
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Map<?, ?>> loadAutoLoginList() {
        Config data = new Config(AutoLoginFile, Config.YAML);
        return (List<Map<?, ?>>) data.get("accounts");
    }

    public void doStartUpAutoLogin() {
        new NukkitRunnable(){
            @Override
            public void run() {
                Logger.info("[AutoLogin] Starting auto-bot task.");
                for(Map<?,?> map : loadAutoLoginList()){
                    Map<?,?> password = (Map<?, ?>) map.get("password");
                    Map<?,?> configuration = (Map<?, ?>) map.get("configuration");
                    Integer Account = (Integer) map.get("account");
                    String Password = password.get("value").toString();
                    BotConfiguration.MiraiProtocol Protocol = BotConfiguration.MiraiProtocol.valueOf(configuration.get("protocol").toString());

                    Logger.info("[AutoLogin] Auto login bot account: " + Account + " Protocol: " + Protocol.name());
                    MiraiBot.getInstance().doBotLogin(Account, Password, Protocol);
                }
            }
        }.runTaskAsynchronously(plugin);
    }

    public boolean addAutoLoginBot(long Account, String Password, String Protocol){
        // 获取现有的机器人列表
        Config data = new Config(AutoLoginFile, Config.YAML);
        List<Map<?, ?>> list = (List<Map<?, ?>>) data.get("accounts");

        // 新建用于添加进去的Map
        Map<Object, Object> account = new HashMap<>();

        // account 节点
        account.put("account", Account);

        // password 节点
        Map<Object, Object> password = new HashMap<>();
        password.put("kind","PLAIN");
        password.put("value",Password);
        account.put("password",password);

        // configuration 节点
        Map<Object, Object> configuration = new HashMap<>();
        configuration.put("protocol", Protocol);
        configuration.put("device", "device.json");
        account.put("configuration",configuration);

        // 添加
        list.add(account);
        data.set("accounts", list);
        Logger.info("save");
        data.save(AutoLoginFile);
        return true;
    }

    public boolean delAutoLoginBot(long Account){
        Config data = new Config(AutoLoginFile, Config.YAML);
        List<Map<?, ?>> list = (List<Map<?, ?>>) data.get("accounts");

        for(Map<?,?> bots : list){
            if((Integer) bots.get("account") == Account){
                list.remove(bots);
                break;
            }
        }

        data.set("accounts", list);

        data.save(AutoLoginFile);
        return true;
    }
}
