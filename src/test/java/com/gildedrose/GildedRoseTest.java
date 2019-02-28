package com.gildedrose;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

  public static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
  public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
  public static final String AGED_BRIE = "Aged Brie";
  public static final String NORMAL_ITEM = "Foo";

  private BackstagePassesQualityUpdater backstagePassesQualityUpdater = new BackstagePassesQualityUpdater();
  private AgedBrieQualityUpdater agedBrieQualityUpdater = new AgedBrieQualityUpdater();
  private NormalItemQualityUpdater normalItemQualityUpdater = new NormalItemQualityUpdater();
  private InventorySystem inventorySystem = new InventorySystem(backstagePassesQualityUpdater, agedBrieQualityUpdater, normalItemQualityUpdater);

  @Test
  public void shouldDecrementSellInByOne() {
    Item[] items = new Item[]{new Item("foo", 0, 10)};
    List<Item> itemList = Collections.singletonList(new Item("foo", 0, 10));

    //TODO remove me
    GildedRose app = new GildedRose(items);

    app.updateQuality();
    inventorySystem.updateQuality(itemList);

    assertEquals(-1, app.items[0].sellIn);
    assertEquals(-1, itemList.get(0).sellIn);
  }

  @Test
  public void shouldUpdateQualityOfNormalItems() {
    assertQualityForItem("Should decrement by 1 when sell date in future", NORMAL_ITEM, 1, 40, 39);
    assertQualityForItem("Should decrement by 2 when sell date in past", NORMAL_ITEM, 0, 40, 38);
    assertQualityForItem("Quality can not be negative", NORMAL_ITEM, 0, 0, 0);
  }

  @Test
  public void shouldUpdateQualityOfAgedBrie() {
    assertQualityForItem("Should increment by 1 when sell date in future", AGED_BRIE, 1, 40, 41);
    assertQualityForItem("Should increment by 2 when sell date in past", AGED_BRIE, 0, 40, 42);
    assertQualityForItem("Should never be > 50", AGED_BRIE, 0, 50, 50);
  }

  @Test
  public void shouldUpdateQualityOfBackstagePasses() {
    assertQualityForItem("Should increment by 1 when sellIn > 10", BACKSTAGE, 11, 40, 41);
    assertQualityForItem("Should increment by 2 when sellIn <= 10 and >5", BACKSTAGE, 10, 40, 42);
    assertQualityForItem("Should increment by 2 when sellIn <= 10 and >5", BACKSTAGE, 6, 40, 42);

    assertQualityForItem("Should increment by 3 when sellIn <= 5 and >0", BACKSTAGE, 5, 40, 43);
    assertQualityForItem("Should increment by 3 when sellIn <= 5 and >0", BACKSTAGE, 1, 40, 43);

    assertQualityForItem("Should never be > 50", BACKSTAGE, 1, 50, 50);
    assertQualityForItem("Should set to 0 when sellIn <= 0", BACKSTAGE, 0, 40, 0);
  }

  @Test
  public void shouldUpdateQualityForSulfuras() {
    assertQualityForItem("Quality should never change", SULFURAS, 1, 80, 80);
    assertQualityForItem("Quality should never change", SULFURAS, 0, 80, 80);
  }

  private void assertQualityForItem(String failureMessage, String itemName, int sellIn, int quality, int expectedQuality) {
    Item[] items = new Item[]{new Item(itemName, sellIn, quality)};
    List<Item> itemList = Collections.singletonList(new Item(itemName, sellIn, quality));
    //TODO remove me
    GildedRose app = new GildedRose(items);

    app.updateQuality();
    inventorySystem.updateQuality(itemList);

    assertEquals(failureMessage, expectedQuality, app.items[0].quality);
    assertEquals(failureMessage, expectedQuality, itemList.get(0).quality);
  }
}
