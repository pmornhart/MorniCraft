package de.mornihosting.mornicraft;

import io.papermc.paper.event.player.AsyncChatEvent;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * MorniCraft in the MoinMorni network: every second the network's ranks and the clan tags (for the network chat),
 * and while something happens to a player (join, click, move, sneak …) MorniCraft speaks the player's language.
 * Only registered when the network plugin runs here.
 */
final class NetworkMode implements Listener {
  /** Read by the network plugin: the clan tag in front of the name in the network chat ("MC"), and its colour. */
  static final String TAG = "moinmorni:tag";
  static final String TAG_COLOUR = "moinmorni:tag-colour";
  private static final long EVERY_TICKS = 20;

  private final MorniCraft plugin;

  NetworkMode(MorniCraft plugin) {
    this.plugin = plugin;
    Bukkit.getScheduler().runTaskTimer(plugin, this::tick, EVERY_TICKS, EVERY_TICKS);
  }

  private void tick() {
    plugin.ranks().watchNetwork();
    for (Player p : Bukkit.getOnlinePlayers()) tag(p);
  }

  /** The clan tag as metadata on the player (the network plugin puts it into the chat line). */
  private void tag(Player p) {
    String tag = plugin.clans().tagText(p.getUniqueId());
    String shown = p.hasMetadata(TAG) && !p.getMetadata(TAG).isEmpty() ? p.getMetadata(TAG).get(0).asString() : "";
    if (tag.equals(shown)) return;
    if (tag.isEmpty()) {
      p.removeMetadata(TAG, plugin);
      p.removeMetadata(TAG_COLOUR, plugin);
      return;
    }
    p.setMetadata(TAG, new FixedMetadataValue(plugin, tag));
    p.setMetadata(TAG_COLOUR, new FixedMetadataValue(plugin, plugin.clans().tagColour(p.getUniqueId())));
  }

  /** On shutdown: no tags left behind. */
  void release() {
    for (Player p : Bukkit.getOnlinePlayers()) {
      p.removeMetadata(TAG, plugin);
      p.removeMetadata(TAG_COLOUR, plugin);
    }
  }

  // ---------------------------------------------------------------- the player's language while MorniCraft answers

  /** From now on the texts in this player's language (until the event is through). */
  void speak(UUID player) {
    Network net = plugin.network();
    if (net != null) Texts.use(net.language(player));
  }

  private void speak(Event e) {
    Player p = playerOf(e);
    if (p != null) speak(p.getUniqueId());
  }

  private static Player playerOf(Event e) {
    if (e instanceof org.bukkit.event.player.PlayerEvent pe) return pe.getPlayer();
    if (e instanceof InventoryClickEvent ic && ic.getWhoClicked() instanceof Player p) return p;
    if (e instanceof PlayerDeathEvent pd) return pd.getEntity();
    if (e instanceof EntityDamageEvent ed && ed.getEntity() instanceof Player p) return p;
    if (e instanceof AsyncChatEvent ac) return ac.getPlayer();
    return null;
  }

  private static void done() {
    Texts.use(null);
  }

  // Lowest: before every other MorniCraft listener; monitor: after all of them.
  @EventHandler(priority = EventPriority.LOWEST)
  public void a(PlayerJoinEvent e) { speak(e); }
  @EventHandler(priority = EventPriority.MONITOR)
  public void z(PlayerJoinEvent e) { done(); }
  @EventHandler(priority = EventPriority.LOWEST)
  public void a(PlayerQuitEvent e) { speak(e); }
  @EventHandler(priority = EventPriority.MONITOR)
  public void z(PlayerQuitEvent e) { done(); }
  @EventHandler(priority = EventPriority.LOWEST)
  public void a(InventoryClickEvent e) { speak(e); }
  @EventHandler(priority = EventPriority.MONITOR)
  public void z(InventoryClickEvent e) { done(); }
  @EventHandler(priority = EventPriority.LOWEST)
  public void a(PlayerInteractEvent e) { speak(e); }
  @EventHandler(priority = EventPriority.MONITOR)
  public void z(PlayerInteractEvent e) { done(); }
  @EventHandler(priority = EventPriority.LOWEST)
  public void a(PlayerInteractEntityEvent e) { speak(e); }
  @EventHandler(priority = EventPriority.MONITOR)
  public void z(PlayerInteractEntityEvent e) { done(); }
  @EventHandler(priority = EventPriority.LOWEST)
  public void a(PlayerMoveEvent e) { speak(e); }
  @EventHandler(priority = EventPriority.MONITOR)
  public void z(PlayerMoveEvent e) { done(); }
  @EventHandler(priority = EventPriority.LOWEST)
  public void a(PlayerToggleSneakEvent e) { speak(e); }
  @EventHandler(priority = EventPriority.MONITOR)
  public void z(PlayerToggleSneakEvent e) { done(); }
  @EventHandler(priority = EventPriority.LOWEST)
  public void a(PlayerToggleFlightEvent e) { speak(e); }
  @EventHandler(priority = EventPriority.MONITOR)
  public void z(PlayerToggleFlightEvent e) { done(); }
  @EventHandler(priority = EventPriority.LOWEST)
  public void a(PlayerRespawnEvent e) { speak(e); }
  @EventHandler(priority = EventPriority.MONITOR)
  public void z(PlayerRespawnEvent e) { done(); }
  @EventHandler(priority = EventPriority.LOWEST)
  public void a(PlayerDeathEvent e) { speak(e); }
  @EventHandler(priority = EventPriority.MONITOR)
  public void z(PlayerDeathEvent e) { done(); }
  @EventHandler(priority = EventPriority.LOWEST)
  public void a(EntityDamageEvent e) { speak(e); }
  @EventHandler(priority = EventPriority.MONITOR)
  public void z(EntityDamageEvent e) { done(); }
  @EventHandler(priority = EventPriority.LOWEST)
  public void a(AsyncChatEvent e) { speak(e); }
  @EventHandler(priority = EventPriority.MONITOR)
  public void z(AsyncChatEvent e) { done(); }

  /** The commands MorniCraft leaves to the network (they reach the server only through an alias). */
  static final List<String> NETWORK_COMMANDS = List.of("msg", "r", "gefaengnis", "frei", "stumm", "entstummen",
      "rang", "abzeichen");
}
