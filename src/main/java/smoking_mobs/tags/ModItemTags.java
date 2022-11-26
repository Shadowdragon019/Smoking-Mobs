package smoking_mobs.tags;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import smoking_mobs.Main;

public class ModItemTags {
    public static final TagKey<Item> producesSmokeInLava = TagKey.of(Registry.ITEM_KEY, new Identifier(Main.id, "produces_smoke_in_lava"));
    public static final TagKey<Item> producesBigSmoke = TagKey.of(Registry.ITEM_KEY, new Identifier(Main.id, "produces_big_smoke"));
}
