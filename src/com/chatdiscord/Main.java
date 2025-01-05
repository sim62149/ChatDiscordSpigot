package com.chatdiscord;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        // Message de confirmation dans la console
        getLogger().info("Le plugin ChatDiscord a été activé !");
        OnStart configManager = new OnStart(this); // Initialise la gestion de la config
        // Enregistre les événements
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);

        // Enregistre la commande "showchatwebhook"
        getCommand("showchatwebhook").setExecutor(new ShowWebhook());
    }

    @Override
    public void onDisable() {
        getLogger().info("Le plugin ChatDiscord a été désactivé.");
    }
}
