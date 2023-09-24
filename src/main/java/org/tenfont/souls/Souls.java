package org.tenfont.souls;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.SkinTrait;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class Souls extends JavaPlugin {
    @Override
    public void onEnable() {
        getCommand("spawn").setExecutor((sender, command, label, args) -> {
            if (!(sender instanceof Player player)) return true;
            Location spawnLocation = player.getLocation();
            spawnLocation.setPitch(0);
            Bukkit.getScheduler().runTaskLater(this, () -> {
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 220, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 220, 1));
                player.showTitle(Title.title(
                        Component.text("Careful...").color(TextColor.fromHexString("#c42b2b")),
                        Component.text("There are souls nearby").color(TextColor.fromHexString("#9c9c9c")).decorate(TextDecoration.ITALIC)
                ));

                NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "&7Lost Soul");
                SkinTrait skinTrait = npc.getOrAddTrait(SkinTrait.class);
                skinTrait.setSkinName("sunalsun", true);
                npc.setProtected(false);
                npc.spawn(spawnLocation);
                Player playerEntity = (Player) npc.getEntity();
                playerEntity.setNoDamageTicks(0);
                playerEntity.setWalkSpeed(0.5f);
                npc.getNavigator().setTarget(player, true);
                Bukkit.getScheduler().runTaskLater(this, () -> {
                    npc.destroy();
                }, 200);
            }, 100);
            return true;
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
