package com.gildedrose;

import java.util.List;

class InventorySystem {

  public static final String AGED_BRIE = "Aged Brie";
  public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
  public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

  private final BackstagePassesQualityUpdater backstagePassesQualityUpdater;
  private final AgedBrieQualityUpdater agedBrieQualityUpdater;

  public InventorySystem(BackstagePassesQualityUpdater backstagePassesQualityUpdater, AgedBrieQualityUpdater agedBrieQualityUpdater) {
    this.backstagePassesQualityUpdater = backstagePassesQualityUpdater;
    this.agedBrieQualityUpdater = agedBrieQualityUpdater;
  }

  public void updateQuality(List<Item> items) {
    for (Item item : items) {
      if (item.name.equals(SULFURAS)) {
        continue;
      }

      if (!item.name.equals(AGED_BRIE)
          && !item.name.equals(BACKSTAGE_PASSES)) {
        degradeQuality(item);
      } else {
        if (item.name.equals(BACKSTAGE_PASSES)) {
          backstagePassesQualityUpdater.updateQuality(item);
        } else {
          // must be AGED_BRIE
          agedBrieQualityUpdater.updateQuality(item);
        }
      }

      decreaseSellInValue(item);

      if (item.sellIn < 0) {
        if (!item.name.equals(AGED_BRIE)) {
          if (!item.name.equals(BACKSTAGE_PASSES)) {
            //degrade quality again after sell date has past, so degrade by 2 in total
            degradeQuality(item);
          } else {
            item.quality = 0;
          }
        } else {
          increaseQuality(item);
        }
      }
    }
  }

  private void increaseQuality(Item item) {
    if (item.quality < 50) {
      item.quality = item.quality + 1;
    }
  }

  private void decreaseSellInValue(Item item) {
    item.sellIn = item.sellIn - 1;
  }

  private void degradeQuality(Item item) {
    if (item.quality > 0) {
      item.quality = item.quality - 1;
    }
  }
}