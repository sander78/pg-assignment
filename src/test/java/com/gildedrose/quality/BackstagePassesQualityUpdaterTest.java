package com.gildedrose.quality;

import com.gildedrose.Item;
import org.junit.Test;

import static com.gildedrose.ItemType.BACKSTAGE_PASSES;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BackstagePassesQualityUpdaterTest {

  private BackstagePassesQualityUpdater updater = new BackstagePassesQualityUpdater();

  @Test
  public void shouldUpdateQuality() {
    assertQualityForItem("should increase quality by 1 when sell date >= 10", 10, 1, 2);
    assertQualityForItem("should increase quality by 2 when sell date < 10 and >= 5", 9, 1, 3);
    assertQualityForItem("should increase quality by 2 when sell date < 10 and >= 5", 5, 1, 3);
    assertQualityForItem("should increase quality by 3 when sell date < 5 and >= 0", 4, 1, 4);
    assertQualityForItem("should increase quality by 3 when sell date < 5 and >= 0", 0, 1, 4);
    assertQualityForItem("should set quality to zero when past sell date", -1, 1, 0);
    assertQualityForItem("should never quality to > 50", 1, 50, 50);
  }

  @Test
  public void shouldHandlePasses() {
    assertTrue(updater.forTypes().contains(BACKSTAGE_PASSES));
    assertEquals(1, updater.forTypes().size());
  }

  private void assertQualityForItem(String failureMessage, int sellIn, int quality, int expectedQuality) {
    Item item = new Item(BACKSTAGE_PASSES.getDescription(), sellIn, quality);

    updater.updateQuality(item);

    assertEquals(failureMessage, expectedQuality, item.quality);
  }
}