//------------------------------------------------------------------------------------------------
//
//   SG Craft - Map feature generation
//
//------------------------------------------------------------------------------------------------

package gcewing.sg;

import java.util.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.structure.*;

import net.minecraftforge.event.terraingen.*;

public class FeatureGeneration {

	public static void onInitMapGen(InitMapGenEvent e) {
		switch (e.type) {
			case SCATTERED_FEATURE:
				if (e.newGen instanceof MapGenStructure)
					e.newGen = modifyScatteredFeatureGen((MapGenStructure)e.newGen);
				else
					System.out.printf("SGCraft: FeatureGeneration: SCATTERED_FEATURE generator is not a MapGenStructure, cannot customise\n");
				break;
		}
	}

	static MapGenStructure modifyScatteredFeatureGen(MapGenStructure gen) {
		//System.out.printf("SGCraft: FeatureGeneration.modifyScatteredFeatureGen: %s\n", gen);
		//return new SGMapGenScatteredFeature(gen);
		MapGenStructureAccess.setStructureMap(gen, new SGStructureMap());
		return gen;
	}

}

class SGStructureMap extends HashMap {

	@Override
	public Object put(Object key, Object value) {
		//System.out.printf("SGCraft: FeatureGeneration: SGStructureMap.put: %s\n", value);
		if (value instanceof StructureStart)
			augmentStructureStart((StructureStart)value);
		return super.put(key, value);
	}
	
	void augmentStructureStart(StructureStart start) {
		LinkedList oldComponents = start.getComponents();
		LinkedList newComponents = new LinkedList();
		//int i = 0;
		for (Object comp : oldComponents) {
			StructureBoundingBox box = ((StructureComponent)comp).getBoundingBox();
			System.out.printf("SGCraft: FeatureGeneration: Found component %s at (%s, %s)\n",
				comp, box.getCenterX(), box.getCenterZ());
			if (comp instanceof ComponentScatteredFeatureDesertPyramid)
				newComponents.add(new FeatureUnderDesertPyramid((ComponentScatteredFeatureDesertPyramid)comp));
			//++i;
		}
		oldComponents.addAll(newComponents);
	}

}
