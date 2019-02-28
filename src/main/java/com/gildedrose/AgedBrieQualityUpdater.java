package com.gildedrose;

public class AgedBrieQualityUpdater implements QualityUpdater{

  @Override
  public void updateQuality(Item item) {
    if (item.quality < 50) {
      item.quality = item.quality + 1;
    }
  }

  @Override
  public ItemType forType() {
    return ItemType.BRIE;
  }
}
