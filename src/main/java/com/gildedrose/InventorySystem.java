package com.gildedrose;

import com.gildedrose.quality.QualityUpdater;

import java.util.List;

public class InventorySystem {

  private final List<QualityUpdater> qualityUpdaters;

  public InventorySystem(List<QualityUpdater> qualityUpdaters) {
    this.qualityUpdaters = qualityUpdaters;
  }

  private QualityUpdater getUpdaterForType(ItemType itemType) {
    return qualityUpdaters.stream()
        .filter(qualityUpdater -> qualityUpdater.forTypes().contains(itemType))
        .findFirst().orElseThrow(() -> new IllegalStateException("No updater found for itemType " + itemType));
  }

  /**
   * Daily updates the quality of all items in inventory.
   * Items can have their quality degrade, increase or stay the same (mostly based on the date the item should be sold).
   *
   * @param items all items in inventory
   */
  public void updateQuality(List<Item> items) {
    for (Item item : items) {
      ItemType itemType = ItemType.findByName(item.name);
      if (itemType == ItemType.SULFURAS) {
        continue;
      }

      item.sellIn = item.sellIn - 1;

      QualityUpdater qualityUpdater = getUpdaterForType(itemType);
      qualityUpdater.updateQuality(item);
    }
  }
}