package com.gildedrose;

public class NormalItemQualityUpdater implements QualityUpdater{

  @Override
  public void updateQuality(Item item) {
    if (item.quality > 0) {
      item.quality = item.quality - 1;
    }

    if (item.sellIn < 0) {
      item.quality = item.quality - 1;
    }
    if (item.quality < 0) {
      item.quality = 0;
    }
  }

  @Override
  public ItemType forType() {
    return ItemType.NORMAL;
  }
}
