
import com.fawry.photosystem.Location;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {

    @Test
    public void testLocationConstructorAndGetters() {
        Location location = new Location("Giza", 48.8566, 2.3522);

        assertEquals("Giza", location.getName());
        assertEquals(48.8566, location.getLat());
        assertEquals(2.3522, location.getLng());
    }

    @Test
    public void testEqualsAndHashCode() {
        Location location1 = new Location("Giza", 48.8566, 2.3522);
        Location location2 = new Location("Giza", 48.8566, 2.3522);
        Location location3 = new Location("Cairo", 51.5074, -0.1278);

        assertEquals(location1, location2);
        assertEquals(location1.hashCode(), location2.hashCode());
        assertNotEquals(location1, location3);
    }

    @Test
    public void testToString() {
        Location location = new Location("Giza", 48.8566, 2.3522);
        String expected = "Location{name='Giza', \nlat=48.8566, \nlng=2.3522}";

        assertEquals(expected, location.toString());
    }
}