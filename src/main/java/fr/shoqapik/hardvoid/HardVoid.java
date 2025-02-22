package fr.shoqapik.hardvoid;

import com.mojang.logging.LogUtils;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import spiderman.adaptive_difficulty.Adaptive;
import spiderman.adaptive_difficulty.network.AdaptiveDifficultyModVariables;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HardVoid.MODID)
public class HardVoid
{
    public static final String MODID = "hardvoid";
    private static final Logger LOGGER = LogUtils.getLogger();


    public HardVoid()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event)
    {
        if(event.getTo().toString().contains("the_deep_void:deep_void")){
            event.getEntity().getCapability(AdaptiveDifficultyModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction) null).ifPresent((capability) -> {
                capability.Difficulty = "hardcore";
                capability.syncPlayerVariables(event.getEntity());
            });

            event.getEntity().displayClientMessage(Component.literal("Your difficuly has been changed to hardcore"), true);
        }

        if(event.getFrom().toString().contains("the_deep_void:deep_void")){
            event.getEntity().displayClientMessage(Component.literal("Congratulations! You survived The Deep Void. Your difficuly has been reset to hard"), true);
            event.getEntity().getCapability(AdaptiveDifficultyModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction) null).ifPresent((capability) -> {
                capability.Difficulty = "hard";
                capability.syncPlayerVariables(event.getEntity());
            });        }

    }

}
