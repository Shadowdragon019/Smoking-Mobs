package smoking_mobs.util.data_gen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import smoking_mobs.util.data_gen.tags.EntityTagGen;
import smoking_mobs.util.data_gen.tags.ItemTagGen;

public class DataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        generator.addProvider(ItemTagGen::new);
        generator.addProvider(EntityTagGen::new);
    }
}
