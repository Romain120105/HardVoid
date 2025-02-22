package fr.shoqapik.hardvoid.mixins;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import spiderman.adaptive_difficulty.Adaptive;
import spiderman.adaptive_difficulty.network.AdaptiveDifficultyModVariables;

@Mixin(Adaptive.class)
public abstract class AdaptiveMixin {


    /**
     * @author
     * @reason
     */
    @Overwrite(remap = false)
    public static void setDifficulty(Player player, String mode) {
        if(player.level().dimension().toString().contains("the_deep_void:deep_void")){
            player.displayClientMessage(Component.literal("You are not allowed to change your difficulty in The Deep Void, it has been reset to hard."), true);
        }else {
            player.getCapability(AdaptiveDifficultyModVariables.PLAYER_VARIABLES_CAPABILITY, (Direction) null).ifPresent((capability) -> {
                capability.Difficulty = mode;
                capability.syncPlayerVariables(player);
            });
        }
    }

}
