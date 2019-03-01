package com.gildedrose;

import org.junit.Test;

import static com.gildedrose.ItemType.CONJURED;
import static com.gildedrose.ItemType.NORMAL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DegradableItemQualityUpdaterTest {

  private DegradableItemQualityUpdater updater = new DegradableItemQualityUpdater();

  @Test
  public void shouldUpdateQualityNormalItem() {
    assertQualityForItem("should decrease quality by 1 when sell date in future", NORMAL, 0, 10, 9);
    assertQualityForItem("should decrease quality by 2 when sell date in past", NORMAL,-1, 10, 8);
    assertQualityForItem("should never decrease quality after zero", NORMAL, -1, 1, 0);
  }

  @Test
  public void shouldUpdateQualityConjuredItem() {
    assertQualityForItem("should decrease quality by 2 when sell date in future", CONJURED, 0, 10, 8);
    assertQualityForItem("should decrease quality by 4 when sell date in past", CONJURED,-1, 10, 6);
    assertQualityForItem("should never decrease quality after zero", CONJURED, -1, 1, 0);
  }

  @Test
  public void shouldHandleNormalAndConjured() {
    assertTrue(updater.forTypes().contains(NORMAL));
    assertTrue(updater.forTypes().contains(CONJURED));
    assertEquals(2, updater.forTypes().size());
  }

  private void assertQualityForItem(String failureMessage, ItemType itemType, int sellIn, int quality, int expectedQuality) {
    Item item = new Item(itemType.getDescription(), sellIn, quality);

    updater.updateQuality(item);

    assertEquals(failureMessage, expectedQuality, item.quality);
  }

}