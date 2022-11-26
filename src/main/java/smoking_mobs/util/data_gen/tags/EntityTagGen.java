package smoking_mobs.util.data_gen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import smoking_mobs.tags.ModEntityTags;

public class EntityTagGen extends FabricTagProvider.EntityTypeTagProvider {
    public EntityTagGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(ModEntityTags.producesSmokeInLava);
    }
}
