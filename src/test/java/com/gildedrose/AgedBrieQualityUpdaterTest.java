package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class AgedBrieQualityUpdaterTest {

  private AgedBrieQualityUpdater updater = new AgedBrieQualityUpdater();

  @Test
  public void shouldUpdateQuality() {
    assertQualityForItem("should increase quality by 1 before sell date", 0, 1, 2);
    assertQualityForItem("should increase quality by 2 after sell date", -1, 1, 3);
    assertQualityForItem("should never increase quality after 50", -1, 50, 50);
  }

  @Test
  public void shouldForType() {
    assertSame(ItemType.BRIE, updater.forType());
  }

  private void assertQualityForItem(String failureMessage, int sellIn, int quality, int expectedQuality) {
    Item item = new Item(ItemType.BRIE.getDescription(), sellIn, quality);

    updater.updateQuality(item);

    assertEquals(failureMessage, expectedQuality, item.quality);
  }
}