package me.aom13.treecapitator.listener;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class TreeListener implements Listener {
    public TreeListener() {
    }

    private Material LOG_type;
    private ItemStack predmet_in_right_hand;
    private Block broken_block;

    @EventHandler
    public void onBreak(BlockBreakEvent blockBreakEvent) {
        broken_block =blockBreakEvent.getBlock();
        Player player = blockBreakEvent.getPlayer();
//        float speed = broken_block.getBreakSpeed(player); // Скорость ломания блока игроком
        predmet_in_right_hand = player.getInventory().getItemInMainHand();
        if (predmet_in_right_hand.getType().equals(Material.DIAMOND_AXE) |
                predmet_in_right_hand.getType().equals(Material.GOLDEN_AXE) |
                predmet_in_right_hand.getType().equals(Material.IRON_AXE) |
                predmet_in_right_hand.getType().equals(Material.NETHERITE_AXE) |
                predmet_in_right_hand.getType().equals(Material.STONE_AXE) |
                predmet_in_right_hand.getType().equals(Material.WOODEN_AXE) & !(player.isSneaking())){

            switch (blockBreakEvent.getBlock().getType()){
                case OAK_LOG:
                    LOG_type = Material.OAK_LOG;
                    break;
                case ACACIA_LOG:
                    LOG_type = Material.ACACIA_LOG;
                    break;
                case BIRCH_LOG:
                    LOG_type = Material.BIRCH_LOG;
                    break;
                case DARK_OAK_LOG:
                    LOG_type = Material.DARK_OAK_LOG;
                    break;
                case JUNGLE_LOG:
                    LOG_type = Material.JUNGLE_LOG;
                    break;
                case SPRUCE_LOG:
                    LOG_type = Material.SPRUCE_LOG;
                    break;
                default:
                    return;
            }
            CheckTree(broken_block.getLocation());
        }
    }


    private void CheckTree(Location location){
        for (int x_change = -1; x_change <= 1; x_change++){ // Эта хрень проверяет все рядом стоящие блоки вокруг сломанного блока
            for (int y_change = -1; y_change <= 1; y_change++){ // И срубает их
                for (int z_change = -1; z_change <= 1; z_change++){
                    Location curent_location = new Location(location.getWorld(), location.getX()+x_change, location.getY()+y_change, location.getZ()+z_change);
                    if (curent_location.getBlock().getType().equals(LOG_type)){
                        if (predmet_in_right_hand.getType().getMaxDurability() - predmet_in_right_hand.getDurability() > 1){
                            ItemStack drop = new ItemStack(LOG_type, 1); //установка итема для дропа
                            curent_location.getBlock().setType(Material.AIR); //замена блока (который разрушали) воздухом
                            curent_location.getWorld().dropItem(broken_block.getLocation(), drop); //имитация дропа итема по координатам разрушеного блока
                            predmet_in_right_hand.setDurability((short) (predmet_in_right_hand.getDurability()+1)); // +1 потому что эта хрень возвращает не прочность! а потеряное значение прочности
                            CheckTree(curent_location);
                        }
                        else{
                            break;
                        }
                    }
                }
            }
        }
    }
}
