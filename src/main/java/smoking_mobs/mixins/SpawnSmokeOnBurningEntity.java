package smoking_mobs.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import smoking_mobs.tags.ModEntityTags;
import smoking_mobs.tags.ModItemTags;

import java.util.Random;

@Mixin(Entity.class)
public abstract class SpawnSmokeOnBurningEntity {

	@Shadow @Final protected Random random;

	@Shadow public World world;

	@Shadow private EntityDimensions dimensions;

	@Shadow public abstract Vec3d getPos();

	@Shadow public abstract boolean doesRenderOnFire();

	@Shadow public abstract boolean isInLava();

	@Shadow public abstract EntityType<?> getType();

	//@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setFireTicks(I)V"), method = "baseTick()V")
	@Inject(at = @At(value = "TAIL"), method = "baseTick()V")
	private void spawnSmokeOnBurningEntity(CallbackInfo info) {
		if (world instanceof ServerWorld serverWorld) {

			DefaultParticleType particleToSpawn = ParticleTypes.LARGE_SMOKE;

			boolean shouldRenderSmoke = doesRenderOnFire() || (getType().isIn(ModEntityTags.producesSmokeInLava) && isInLava()); // am I rendering fire? or am I in lava & in the producesSmokeInLava tag?

			if (isInLava() && ((Entity)(Object)this) instanceof ItemEntity) { // am I an item? also shut intellij
				ItemEntity itemEntity = ((ItemEntity)(Object)this);

				if (!itemEntity.getStack().isIn(ModItemTags.producesBigSmoke)) particleToSpawn = ParticleTypes.SMOKE; // smaller smoke as it's an item, unless it's in the producesBigSmoke tag

				if (!shouldRenderSmoke) { // if I'm not producing smoke
					shouldRenderSmoke = (itemEntity.getStack().isIn(ModItemTags.producesSmokeInLava) && isInLava()); // am I in lava & in the producesSmokeInLava tag?
				}

			}

			if (shouldRenderSmoke && random.nextInt(5) == 0) {
				float height = dimensions.height;
				float width = dimensions.width;
				Vec3d pos = this.getPos();
				double x = pos.x - width / 2 + random.nextDouble() * width;
				double y = pos.y + random.nextDouble() * height;
				double z = pos.z - width / 2 + random.nextDouble() * width;
				// default pos is:
				// height: bottom
				// with: middle
				// use .SMOKE for release. Using .REVERSE_PORTAl as it's good for debugging purposes, doesn't move much.
				// Can't figure out how to get delta to play nicely so I'm doing my own thing
				for (int i = 0; i < 2; i++) {
					serverWorld.spawnParticles(particleToSpawn, x, y, z, 1, 0, 0, 0, 0);
				}
			}

			// Send XYZ
			//serverWorld.getServer().getPlayerManager().broadcast(Text.of("\nx: " + x + "\ny: " + y + "\nz: " + z), MessageType.CHAT, Util.NIL_UUID);
			// Send height & width
			//serverWorld.getServer().getPlayerManager().broadcast(Text.of("\nHeight: " + height + "\nWidth: " + width), MessageType.CHAT, Util.NIL_UUID);
        }
	}
}
