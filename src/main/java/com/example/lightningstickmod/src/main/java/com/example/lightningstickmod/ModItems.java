package com.example.lightningstickmod;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "lightningstickmod");

    public static final RegistryObject<Item> LIGHTNING_STICK = ITEMS.register("lightning_stick",
        () -> new LightningStickItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        eventBus.addListener((CreativeModeTabEvent.Register event) -> {
            event.registerCreativeModeTab(new net.minecraft.resources.ResourceLocation("lightningstickmod", "tab"),
                builder -> builder.icon(() -> new ItemStack(LIGHTNING_STICK.get()))
                                  .title(net.minecraft.network.chat.Component.literal("Lightning Stick Mod"))
                                  .displayItems((params, output) -> output.accept(LIGHTNING_STICK.get())));
        });
    }
}
