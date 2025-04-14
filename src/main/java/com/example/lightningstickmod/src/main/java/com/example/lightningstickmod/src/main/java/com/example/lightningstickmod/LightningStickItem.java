package com.example.lightningstickmod;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.LightningBolt;

public class LightningStickItem extends Item {
    public LightningStickItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            Vec3 eyePosition = player.getEyePosition();
            Vec3 look = player.getLookAngle();
            Vec3 reach = eyePosition.add(look.scale(50));

            BlockHitResult hitResult = world.clip(new ClipContext(eyePosition, reach, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));
            BlockPos pos = hitResult.getBlockPos();

            LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(world);
            if (lightning != null) {
                lightning.moveTo(pos.getX(), pos.getY(), pos.getZ());
                world.addFreshEntity(lightning);
            }
        }

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
