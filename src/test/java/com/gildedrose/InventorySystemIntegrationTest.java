package com.gildedrose;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static com.gildedrose.ItemType.BACKSTAGE_PASSES;
import static com.gildedrose.ItemType.BRIE;
import static com.gildedrose.ItemType.NORMAL;
import static com.gildedrose.ItemType.SULFURAS;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class InventorySystemIntegrationTest {

  @Autowired
  private InventorySystem inventorySystem;

  @Test
  public void shouldDecrementSellInByOne() {
    List<Item> itemList = Collections.singletonList(new Item("foo", 0, 10));

    inventorySystem.updateQuality(itemList);

    assertEquals(-1, itemList.get(0).sellIn);
  }

  @Test
  public void shouldUpdateQualityOfNormalItems() {
    assertQualityForItem("Should decrement by 1 when sell date in future", NORMAL.getDescription(), 1, 40, 39);
    assertQualityForItem("Should decrement by 2 when sell date in past", NORMAL.getDescription(), 0, 40, 38);
    assertQualityForItem("Quality can not be negative", NORMAL.getDescription(), 0, 0, 0);
  }

  @Test
  public void shouldUpdateQualityOfAgedBrie() {
    assertQualityForItem("Should increment by 1 when sell date in future", BRIE.getDescription(), 1, 40, 41);
    assertQualityForItem("Should increment by 2 when sell date in past", BRIE.getDescription(), 0, 40, 42);
    assertQualityForItem("Should never be > 50", BRIE.getDescription(), 0, 50, 50);
  }

  @Test
  public void shouldUpdateQualityOfBackstagePasses() {
    assertQualityForItem("Should increment by 1 when sellIn > 10", BACKSTAGE_PASSES.getDescription(), 11, 40, 41);
    assertQualityForItem("Should increment by 2 when sellIn <= 10 and >5", BACKSTAGE_PASSES.getDescription(), 10, 40, 42);
    assertQualityForItem("Should increment by 2 when sellIn <= 10 and >5", BACKSTAGE_PASSES.getDescription(), 6, 40, 42);

    assertQualityForItem("Should increment by 3 when sellIn <= 5 and >0", BACKSTAGE_PASSES.getDescription(), 5, 40, 43);
    assertQualityForItem("Should increment by 3 when sellIn <= 5 and >0", BACKSTAGE_PASSES.getDescription(), 1, 40, 43);

    assertQualityForItem("Should never be > 50", BACKSTAGE_PASSES.getDescription(), 1, 50, 50);
    assertQualityForItem("Should set to 0 when sellIn <= 0", BACKSTAGE_PASSES.getDescription(), 0, 40, 0);
  }

  @Test
  public void shouldUpdateQualityForSulfuras() {
    assertQualityForItem("Quality should never change", SULFURAS.getDescription(), 1, 80, 80);
    assertQualityForItem("Quality should never change", SULFURAS.getDescription(), 0, 80, 80);
  }

  private void assertQualityForItem(String failureMessage, String itemName, int sellIn, int quality, int expectedQuality) {
    List<Item> itemList = Collections.singletonList(new Item(itemName, sellIn, quality));

    inventorySystem.updateQuality(itemList);

    assertEquals(failureMessage, expectedQuality, itemList.get(0).quality);
  }
}
