package com.gildedrose;

public interface QualityUpdater {
  void updateQuality(Item item);

  ItemType forType();
}
