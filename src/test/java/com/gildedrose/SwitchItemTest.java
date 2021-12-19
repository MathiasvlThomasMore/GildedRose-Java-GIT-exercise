package com.gildedrose;

import com.gildedrose.core.GildedRose;
import com.gildedrose.core.Item;
import com.gildedrose.items.SwitchItem;
import org.junit.jupiter.api.Test;

public class SwitchItemTest {
    private Item[] items = new Item[]{new SwitchItem("+20 Frozen hart ", 3, 10)};
    private GildedRose app = new GildedRose(items);

    @Test
    void decreaseQualityNormal() {
        app.calculateNextDay();
        assert (app.items[0].sellIn == 2);
        assert (app.items[0].quality == 9);
    }

    @Test
    void decreaseQualityExpired() {
        app.items[0].sellIn = 1;
        app.items[0].quality = 20;
        app.calculateNextDay();
        assert (app.items[0].sellIn == 0);
        assert (app.items[0].quality == 19);
        app.calculateNextDay();
        assert (app.items[0].sellIn == -1);
        assert (app.items[0].quality == 17);
        app.calculateNextDay();
        assert (app.items[0].sellIn == -2);
        assert (app.items[0].quality == 15);
    }

    @Test
    void noQualityBelowZero() {
        app.items[0].sellIn = 1;
        app.items[0].quality = 1;
        app.calculateNextDay();
        assert (app.items[0].sellIn == 0);
        assert (app.items[0].quality == 0);
        app.calculateNextDay();
        assert (app.items[0].sellIn == -1);
        assert (app.items[0].quality == 0);
        app.calculateNextDay();
        assert (app.items[0].sellIn == -2);
        assert (app.items[0].quality == 0);
    }
}
