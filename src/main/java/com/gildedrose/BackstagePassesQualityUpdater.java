package com.gildedrose;

public class BackstagePassesQualityUpdater {

  /**
   * Will update quality for Backstage passes following these rules:
   * When sellIn > 10 increase by 1
   * When sellIn > 5 increase by 2
   * When sellIn > 0 increase by 3
   * When sellIn <= set quality 0
   *
   * Quality can never be more then 50
   *
   */
  public void updateQualityBackstagePasses(Item item) {
    item.quality = item.quality + 1;
    if (item.sellIn < 11) {
      if (item.quality < 50) {
        item.quality = item.quality + 1;
      }
    }

    if (item.sellIn < 6) {
      if (item.quality < 50) {
        item.quality = item.quality + 1;
      }
    }

    if (item.sellIn < 0) {
      item.quality = 0;
    }

    if (item.quality > 50) {
      item.quality = 50;
    }
  }
}
