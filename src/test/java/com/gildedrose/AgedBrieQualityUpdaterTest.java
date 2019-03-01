package com.gildedrose;

import org.junit.Test;

import static com.gildedrose.ItemType.BRIE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AgedBrieQualityUpdaterTest {

  private AgedBrieQualityUpdater updater = new AgedBrieQualityUpdater();

  @Test
  public void shouldUpdateQuality() {
    assertQualityForItem("should increase quality by 1 before sell date", 0, 1, 2);
    assertQualityForItem("should increase quality by 2 after sell date", -1, 1, 3);
    assertQualityForItem("should never increase quality after 50", -1, 50, 50);
  }

  @Test
  public void shouldHandleBrie() {
    assertTrue(updater.forTypes().contains(BRIE));
    assertEquals(1, updater.forTypes().size());
  }

  private void assertQualityForItem(String failureMessage, int sellIn, int quality, int expectedQuality) {
    Item item = new Item(BRIE.getDescription(), sellIn, quality);

    updater.updateQuality(item);

    assertEquals(failureMessage, expectedQuality, item.quality);
  }
}