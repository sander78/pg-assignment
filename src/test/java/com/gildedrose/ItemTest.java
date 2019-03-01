package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {

  @Test
  public void shouldPrintToString() {
    assertEquals("normal item, 1, 1", new Item("normal item", 1, 1).toString());
  }
}