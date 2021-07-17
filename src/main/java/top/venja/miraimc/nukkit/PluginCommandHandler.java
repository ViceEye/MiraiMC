package top.venja.miraimc.nukkit;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.Plugin;
import top.venja.miraimc.internal.ConfigSystem;

public class PluginCommandHandler extends Command {

    private final Plugin plugin;

    public PluginCommandHandler(Plugin plugin) {
        super("miraimc", "Mirai MC Command", "/miraimc help");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (!(args.length == 0)) {
            switch (args[0].toLowerCase()) {
                case "reload": {
                    if (sender.hasPermission("miraimc.command.miraimc.reload")) {
                        ConfigSystem.reloadConfig();
                        sender.sendMessage("§a配置文件已经重新加载，部分配置可能需要重新启动服务器才能生效！");
                    } else sender.sendMessage("§c你没有足够的权限执行此命令！");
                    break;
                }
                case "help": {
                    sender.sendMessage("§6§lMiraiMC§r §b插件帮助菜单");
                    sender.sendMessage("§6/miraimc reload:§r 重新加载插件");
                    break;
                }
                default: {
                    sender.sendMessage("§c未知或不完整的命令，请输入 /miraimc help 查看帮助！");
                    break;
                }
            }
            return true;
        } else {
            sender.sendMessage("This server is running " + plugin.getDescription().getName() + " version " + plugin.getDescription().getVersion() + " by " + plugin.getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
            return false;
        }
    }
}
