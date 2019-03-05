package com.gildedrose.quality;

import com.gildedrose.Item;
import com.gildedrose.ItemType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static com.gildedrose.ItemType.CONJURED;
import static com.gildedrose.ItemType.NORMAL;

@Component
public class DegradableItemQualityUpdater implements QualityUpdater{

  @Override
  public void updateQuality(Item item) {
    int degradeBy = CONJURED.getDescription().equals(item.name) ? 2 : 1;

    if (item.quality > 0) {
      item.quality = item.quality - degradeBy;
    }

    if (item.sellIn < 0) {
      item.quality = item.quality - degradeBy;
    }
    if (item.quality < 0) {
      item.quality = 0;
    }
  }

  @Override
  public List<ItemType> forTypes() {
    return Arrays.asList(NORMAL, CONJURED);
  }
}
