package smoking_mobs.tags;

import net.minecraft.entity.EntityType;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import smoking_mobs.Main;

public class ModEntityTags {
    public static final TagKey<EntityType<?>> producesSmokeInLava = TagKey.of(Registry.ENTITY_TYPE_KEY, new Identifier(Main.id, "produces_smoke_in_lava"));
}
