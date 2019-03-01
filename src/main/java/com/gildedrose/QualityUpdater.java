package com.gildedrose;

import java.util.List;

public interface QualityUpdater {
  void updateQuality(Item item);

  List<ItemType> forTypes();
}
