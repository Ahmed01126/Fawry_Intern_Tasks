package photo_system_test;

import com.fawry.photo_system.Location;
import com.fawry.photo_system.Image;
import com.github.f4b6a3.ulid.Ulid;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;

public class PhotoTest {

    @Test
    public void testPhotoBuilder() {
        Ulid id = Ulid.fast();
        HashSet<String> tags = new HashSet<>();
        tags.add("vacation");
        tags.add("family");

        Location location = new Location("Giza", 48.8566, 2.3522);
        Image photo = new Image.Builder()
                .id(id)
                .title("Pyramids")
                .tags(tags)
                .date(LocalDate.of(2023, 5, 15))
                .time(LocalTime.of(14, 30))
                .location(location)
                .create();

        assertEquals(id, photo.getId());
        assertEquals("Pyramids", photo.getTitle());
        assertEquals(tags, photo.getTags());
        assertEquals(LocalDate.of(2023, 5, 15), photo.getDate());
        assertEquals(LocalTime.of(14, 30), photo.getTime());
        assertEquals(location, photo.getLocation());
    }

    @Test
    public void testEqualsAndHashCode() {
        Ulid id = Ulid.fast();
        Location location = new Location("Paris", 48.8566, 2.3522);
        HashSet<String> tags = new HashSet<>();
        tags.add("vacation");

        Image photo1 = new Image.Builder()
                .id(id)
                .title("Eiffel Tower")
                .tags(tags)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .location(location)
                .create();

        Image photo2 = new Image.Builder()
                .id(id)
                .title("Eiffel Tower")
                .tags(tags)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .location(location)
                .create();

        Image photo3 = new Image.Builder()
                .id(Ulid.fast())
                .title("Notre Dame")
                .tags(new HashSet<>())
                .date(LocalDate.now().minusDays(1))
                .time(LocalTime.now().minusHours(1))
                .location(new Location("Paris", 48.8530, 2.3499))
                .create();

        assertEquals(photo1, photo2);
        assertEquals(photo1.hashCode(), photo2.hashCode());
        assertNotEquals(photo1, photo3);
    }

    @Test
    public void testSetTags() {
        Image photo = new Image.Builder()
                .id(Ulid.fast())
                .title("Test")
                .tags(new HashSet<>())
                .date(LocalDate.now())
                .time(LocalTime.now())
                .location(new Location("Test", 0, 0))
                .create();

        HashSet<String> newTags = new HashSet<>();
        newTags.add("new");
        newTags.add("tags");

        photo.setTags(newTags);
        assertEquals(newTags, photo.getTags());
    }
}