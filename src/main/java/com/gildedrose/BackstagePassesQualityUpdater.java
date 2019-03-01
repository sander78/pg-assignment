package com.gildedrose;

public class BackstagePassesQualityUpdater implements QualityUpdater {

  /**
   * Will update quality for Backstage passes following these rules:
   * When sellIn >= 10 increase by 1
   * When sellIn >= 5 increase by 2
   * When sellIn >= 0 increase by 3
   * When sellIn < 0 set quality 0
   *
   * Quality can never be more then 50
   *
   */
  @Override
  public void updateQuality(Item item) {
    item.quality = item.quality + 1;
    if (item.sellIn < 0) {
      item.quality = 0;
    } else if (item.sellIn < 5) {
      item.quality = item.quality + 2;
    } else if (item.sellIn < 10) {
      item.quality = item.quality + 1;
    }

    if (item.quality > 50) {
      item.quality = 50;
    }
  }

  @Override
  public ItemType forType() {
    return ItemType.BACKSTAGE_PASSES;
  }
}
