package com.gildedrose;

import java.util.List;

class InventorySystem {

  public static final String AGED_BRIE = "Aged Brie";
  public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
  public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

  private final List<QualityUpdater> qualityUpdaters;

  public InventorySystem(List<QualityUpdater> qualityUpdaters) {
    this.qualityUpdaters = qualityUpdaters;
  }

  private QualityUpdater getUpdaterForType(Item item) {
    ItemType itemType = ItemType.findByName(item.name);
    return qualityUpdaters.stream()
        .filter(qualityUpdater -> qualityUpdater.forType() == itemType)
        .findFirst().orElseThrow(() -> new IllegalStateException("No updater found for itemType " + itemType));
  }

  public void updateQuality(List<Item> items) {
    for (Item item : items) {
      if (item.name.equals(SULFURAS)) {
        continue;
      }

      QualityUpdater qualityUpdater = getUpdaterForType(item);
      qualityUpdater.updateQuality(item);

      decreaseSellInValue(item);

      if (item.sellIn < 0) {
        handleItemPastSellDate(item);
      }
    }
  }

  private void handleItemPastSellDate(Item item) {
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