package com.gildedrose;

public class NormalItemQualityUpdater implements QualityUpdater{

  @Override
  public void updateQuality(Item item) {
    if (item.quality > 0) {
      item.quality = item.quality - 1;
    }
  }

  @Override
  public ItemType forType() {
    return ItemType.NORMAL;
  }
}
