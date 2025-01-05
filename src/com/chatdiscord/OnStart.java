package com.chatdiscord;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class OnStart {

    private final JavaPlugin plugin;

    // Constructeur prenant l'instance du plugin
    public OnStart(JavaPlugin plugin) {
        this.plugin = plugin;
        initializeConfig(); // Appelle l'initialisation de la configuration
    }

    // Initialise la configuration
    private void initializeConfig() {
        // Vérifie si le dossier du plugin existe, sinon le crée
        File dataFolder = plugin.getDataFolder();
        if (!dataFolder.exists() && dataFolder.mkdir()) {
            plugin.getLogger().info("Dossier du plugin créé !");
        }

        // Vérifie si le fichier config.yml existe, sinon le crée dynamiquement
        File configFile = new File(dataFolder, "config.yml");
        if (!configFile.exists()) {
            try (PrintWriter writer = new PrintWriter(configFile)) {
                writer.println("webhook: \"\"");
                plugin.getLogger().info("Le fichier config.yml a été créé !");
            } catch (IOException e) {
                plugin.getLogger().severe("Impossible de créer le fichier config.yml !");
                e.printStackTrace();
            }
        }

        // Charge la configuration
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();

        // Vérifie si le webhook est défini dans la configuration
        if (!config.contains("webhook")) {
            config.set("webhook", ""); // Ajoute un champ webhook vide
            plugin.saveConfig();
            plugin.getLogger().info("Webhook vide ajouté dans la config.");
        }

        // Affiche le webhook dans la console
        String webhook = config.getString("webhook");
        plugin.getLogger().info("Webhook actuel : " + webhook);
    }

    // Récupère le webhook depuis la configuration
    public String getWebhook() {
        return plugin.getConfig().getString("webhook", ""); // Retourne le webhook ou une chaîne vide
    }

    // Met à jour le webhook dans la configuration
    public void setWebhook(String webhook) {
        FileConfiguration config = plugin.getConfig();
        config.set("webhook", webhook); // Met à jour la valeur
        plugin.saveConfig();
        plugin.getLogger().info("Webhook mis à jour : " + webhook);
    }
}
