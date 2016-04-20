package com.loibv.t1p.dummy;

import com.loibv.t1p.R;
import com.loibv.t1p.adapter.Trip;
import com.loibv.t1p.model.Place;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    public static  final List<Trip> TRIPS = new ArrayList<Trip>();

    public static  final List<Place> PLACES = new ArrayList<Place>();
    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }

        Trip tripItem = new Trip();
        tripItem.setName("Vinh Ha Long");
        tripItem.setThumbnail(R.drawable.vinh_ha_long);
        tripItem.setTime(new Date());
        tripItem.setPlace("Place 1");
        TRIPS.add(tripItem);

        tripItem = new Trip();
        tripItem.setName("Pho co Hoi An");
        tripItem.setThumbnail(R.drawable.pho_co_hoi_an);
        tripItem.setTime(new Date());
        tripItem.setPlace("Place 2");
        TRIPS.add(tripItem);

        tripItem = new Trip();
        tripItem.setName("Chua Thien Mu");
        tripItem.setThumbnail(R.drawable.chua_thien_mu);
        tripItem.setTime(new Date());
        tripItem.setPlace("Place 3");
        TRIPS.add(tripItem);

        tripItem = new Trip();
        tripItem.setName("Sa Pa");
        tripItem.setThumbnail(R.drawable.sa_pa);
        tripItem.setTime(new Date());
        tripItem.setPlace("Place 4");
        TRIPS.add(tripItem);

        tripItem = new Trip();
        tripItem.setName("Phu Quoc");
        tripItem.setThumbnail(R.drawable.phu_quoc);
        tripItem.setTime(new Date());
        tripItem.setPlace("Place 5");
        TRIPS.add(tripItem);

        Place place = new Place();
        place.setName("aaa");
        PLACES.add(place);

        place = new Place();
        place.setName("bbb");
        PLACES.add(place);


    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
