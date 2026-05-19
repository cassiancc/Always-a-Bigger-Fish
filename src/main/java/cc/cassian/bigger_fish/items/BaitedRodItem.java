package cc.cassian.bigger_fish.items;

import cc.cassian.bigger_fish.access.PlayerWithGrapplingHook;
import cc.cassian.bigger_fish.entity.GrapplingHookEntity;
import cc.cassian.bigger_fish.helpers.ModHelpers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Objects;
import java.util.Optional;

public class BaitedRodItem extends FishingRodItem {
    public BaitedRodItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);

        GrapplingHookEntity grapplingHook = ((PlayerWithGrapplingHook)player).bigger_fish$getHook();

        if (grapplingHook != null) {
            ItemStack heldStack = player.getItemInHand(usedHand);
            var hook = ModHelpers.getHookData(heldStack);
            boolean isAdminRod = false;
            if (!level.isClientSide()) {
                int rodDamage = grapplingHook.retrieve(heldStack);
                int currentDamage = heldStack.getMaxDamage() - heldStack.getDamageValue();
                if (rodDamage >= currentDamage) {
                    rodDamage = currentDamage;
                }
                if (!isAdminRod) {
//                    if (!hook.isEmpty()) {
//                        BundleContents bundleContents = Objects.requireNonNullElse(heldStack.get(DataComponents.BUNDLE_CONTENTS), BundleContents.EMPTY);
//                        BundleContents.Mutable mutable = new BundleContents.Mutable(bundleContents);
//                        ModHelpers.hurtOrRemoveHook(mutable, player,level);
//                    } else {
                        heldStack.hurtAndBreak(rodDamage, player, Player.getSlotForHand(usedHand));
//                    }
                }
            }

            player.swing(usedHand);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.NEUTRAL, 1.0F, 0.4F / (level.random.nextFloat() * 0.4F + 0.8F));
            player.gameEvent(GameEvent.ITEM_INTERACT_FINISH);

            return InteractionResultHolder.sidedSuccess(heldStack, level.isClientSide());
        }



        boolean grappling = ModHelpers.getHookData(itemStack).equals("grappling");
        boolean sticky = ModHelpers.getHookData(itemStack).equals("sticky_grappling");
        if (grappling || sticky) {
            Entity bobber;
            bobber = new GrapplingHookEntity(player, player.level(), ModHelpers.getBaitFromRod(itemStack), sticky);
            player.level().addFreshEntity(bobber);
            return InteractionResultHolder.success(itemStack);
        }

        return super.use(level, player, usedHand);
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack rod, Slot slot, ClickAction action, Player player) {
        return FishContainer.overrideStackedOnOther(rod, slot, action, player, ModHelpers::isAllowedInBaitedRod);
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack rod, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
        return FishContainer.overrideOtherStackedOnMe(rod, other, slot, action, player, access, ModHelpers::isAllowedInBaitedRod);
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return FishContainer.getTooltipImage(stack, Component.translatable("item.bigger_fish.baited_rod.empty.description"));
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        if (stack.has(DataComponents.MAX_DAMAGE)) {
            return super.isBarVisible(stack);
        } else {
            return FishContainer.isBarVisible(stack);
        }
    }

    @Override
    public int getBarWidth(final ItemStack stack) {
        if (stack.has(DataComponents.MAX_DAMAGE)) {
            return super.getBarWidth(stack);
        } else {
            return FishContainer.getBarWidth(stack);
        }
    }

    @Override
    public int getBarColor(ItemStack stack) {
        if (stack.has(DataComponents.MAX_DAMAGE)) {
            return super.getBarColor(stack);
        } else {
            return FishContainer.getBarColor(stack);
        }
    }

    public void onDestroyed(final ItemEntity entity) {
        FishContainer.onDestroyed(entity);
    }
}
