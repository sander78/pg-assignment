package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class NormalItemQualityUpdaterTest {

  private NormalItemQualityUpdater updater = new NormalItemQualityUpdater();

  @Test
  public void shouldUpdateQuality() {
    assertQualityForItem("should decrease quality by 1 when sell date in future", 0, 10, 9);
    assertQualityForItem("should decrease quality by 2 when sell date in past", -1, 10, 8);
    assertQualityForItem("should never decrease quality after zero", -1, 1, 0);
  }

  @Test
  public void shouldForType() {
    assertSame(ItemType.NORMAL, updater.forType());
  }

  private void assertQualityForItem(String failureMessage, int sellIn, int quality, int expectedQuality) {
    Item item = new Item(ItemType.NORMAL.getDescription(), sellIn, quality);

    updater.updateQuality(item);

    assertEquals(failureMessage, expectedQuality, item.quality);
  }

}