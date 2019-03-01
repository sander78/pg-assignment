package com.gildedrose;

import java.util.Collections;
import java.util.List;

import static com.gildedrose.ItemType.BRIE;

public class AgedBrieQualityUpdater implements QualityUpdater{

  @Override
  public void updateQuality(Item item) {
    item.quality = item.quality + 1;

    if (item.sellIn < 0) {
      item.quality = item.quality + 1;
    }

    if (item.quality > 50) {
      item.quality = 50;
    }
  }

  @Override
  public List<ItemType> forTypes() {
    return Collections.singletonList(BRIE);
  }
}
