package com.gildedrose;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class InventorySystemTest {

  @Mock
  private AgedBrieQualityUpdater agedBrieQualityUpdater;
  private InventorySystem inventorySystem;

  @Captor
  private ArgumentCaptor<Item> itemArgumentCaptor;

  @Before
  public void setUp() {
    inventorySystem = new InventorySystem(Collections.singletonList(agedBrieQualityUpdater));
  }

  @Test
  public void shouldNotUpdateLegendaryItem() {
    Item item = new Item(ItemType.SULFURAS.getDescription(), 5, 80);

    inventorySystem.updateQuality(Collections.singletonList(item));

    assertEquals(5, item.sellIn);
    assertEquals(80, item.quality);
  }

  @Test
  public void shouldFirstDecreaseSellInAndThenUpdateQualityOfItem() {
    Item item = new Item(ItemType.BRIE.getDescription(), 5, 80);
    Mockito.when(agedBrieQualityUpdater.forType()).thenReturn(ItemType.BRIE);

    inventorySystem.updateQuality(Collections.singletonList(item));

    verify(agedBrieQualityUpdater).updateQuality(itemArgumentCaptor.capture());
    assertEquals(4, itemArgumentCaptor.getValue().sellIn);
  }

  @Test(expected = IllegalStateException.class)
  public void shouldFailWhenUpdaterNotFoundForItemType() {
    Item item = new Item(ItemType.BACKSTAGE_PASSES.getDescription(), 5, 80);

    inventorySystem.updateQuality(Collections.singletonList(item));
  }
}