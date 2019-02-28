package com.gildedrose;

import org.junit.Test;

import static com.gildedrose.InventorySystem.BACKSTAGE_PASSES;
import static org.junit.Assert.assertEquals;

public class BackstagePassesQualityUpdaterTest {

  private QualityUpdater backstagePassesQualityUpdater = new BackstagePassesQualityUpdater();

  @Test
  public void shouldUpdateQualityOfBackstagePasses() {
    assertQualityForItem("Should increment by 1 when sellIn > 10",  11, 40, 41);
    assertQualityForItem("Should increment by 2 when sellIn <= 10 and >5",  10, 40, 42);
    assertQualityForItem("Should increment by 2 when sellIn <= 10 and >5",  6, 40, 42);

    assertQualityForItem("Should increment by 3 when sellIn <= 5 and >0",  5, 40, 43);
    assertQualityForItem("Should increment by 3 when sellIn <= 5 and >0",  1, 40, 43);

    assertQualityForItem("Should never be > 50",  1, 50, 50);
    assertQualityForItem("Should set to 0 when sellIn <= 0",  -1, 40, 0);
  }

  private void assertQualityForItem(String failureMessage, int sellIn, int quality, int expectedQuality) {
    Item item = new Item(BACKSTAGE_PASSES, sellIn, quality);

    backstagePassesQualityUpdater.updateQuality(item);

    assertEquals(failureMessage, expectedQuality, item.quality);
  }
}