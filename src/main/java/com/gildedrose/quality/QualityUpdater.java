package com.gildedrose.quality;

import com.gildedrose.Item;
import com.gildedrose.ItemType;

import java.util.List;

/**
 * A quality updater for certain {@link ItemType}.
 * Implementations should <b>only</b> update the quality property of the item.
 */
public interface QualityUpdater {

  void updateQuality(Item item);

  /**
   * For which types it can update the quality
   * @return list of types
   */
  List<ItemType> forTypes();
}
