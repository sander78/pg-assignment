package com.gildedrose;

import java.util.List;

public class InventorySystem {

  private final List<QualityUpdater> qualityUpdaters;

  public InventorySystem(List<QualityUpdater> qualityUpdaters) {
    this.qualityUpdaters = qualityUpdaters;
  }

  private QualityUpdater getUpdaterForType(ItemType itemType) {
    return qualityUpdaters.stream()
        .filter(qualityUpdater -> qualityUpdater.forType() == itemType)
        .findFirst().orElseThrow(() -> new IllegalStateException("No updater found for itemType " + itemType));
  }

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