package com.gildedrose;

public class AgedBrieQualityUpdater {

  //TODO add unit test
  public void updateQuality(Item item) {
    if (item.quality < 50) {
      item.quality = item.quality + 1;
    }
  }
}
