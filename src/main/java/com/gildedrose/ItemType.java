package com.gildedrose;

import java.util.Arrays;

public enum ItemType {

  BRIE("Aged Brie"),
  SULFURAS("Sulfuras, Hand of Ragnaros"),
  BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert"),
  NORMAL("N/A");

  private String description;

  ItemType(String description) {
    this.description = description;
  }

  public static ItemType findByName(String description) {
    return Arrays.stream(ItemType.values())
        .filter(itemType -> itemType.description.equals(description))
        .findAny().orElse(NORMAL);
  }

  public String getDescription() {
    return description;
  }
}
