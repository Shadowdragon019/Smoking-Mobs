package smoking_mobs.util.data_gen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import smoking_mobs.tags.ModItemTags;

public class ItemTagGen extends FabricTagProvider.ItemTagProvider {
    public ItemTagGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(ModItemTags.producesSmokeInLava);
        getOrCreateTagBuilder(ModItemTags.producesBigSmoke);
    }
}
