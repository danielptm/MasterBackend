package model;

import com.globati.utildb.HostelSyncS3.Location;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class LocationTest {

    @Test
    public void testEquals() {
        Set<Location> locations = new HashSet<>();
        Location location = new Location("Seattle", "USA");
        Location location2 = new Location("Seattle", "USA");

        locations.add(location);
        locations.add(location2);

        Assert.assertTrue(location.equals(location2));
        Assert.assertEquals(1, locations.size());
    }
}
