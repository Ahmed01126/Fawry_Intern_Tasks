package photo_system_test;

import com.fawry.photo_system.*;
import com.fawry.photo_system.Image;
import com.fawry.photo_system.ImageService;
import com.github.f4b6a3.ulid.Ulid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class GalleryServiceTest {

    private ImageService galleryService;
    private Image photo1, photo2, photo3;

    @BeforeEach
    public void setUp() {
        galleryService = new ImageService();

        HashSet<String> tags1 = new HashSet<>();
        tags1.add("vacation");
        tags1.add("family");

        HashSet<String> tags2 = new HashSet<>();
        tags2.add("work");
        tags2.add("business");

        HashSet<String> tags3 = new HashSet<>();
        tags3.add("vacation");
        tags3.add("friends");

        photo1 = new Image.Builder()
                .id(Ulid.fast())
                .title("Eiffel Tower")
                .tags(tags1)
                .date(LocalDate.of(2023, 5, 15))
                .time(LocalTime.of(14, 30))
                .location(new Location("Paris", 48.8566, 2.3522))
                .create();

        photo2 = new Image.Builder()
                .id(Ulid.fast())
                .title("Office Meeting")
                .tags(tags2)
                .date(LocalDate.of(2023, 6, 20))
                .time(LocalTime.of(10, 0))
                .location(new Location("New York", 40.7128, -74.0060))
                .create();

        photo3 = new Image.Builder()
                .id(Ulid.fast())
                .title("Beach Day")
                .tags(tags3)
                .date(LocalDate.of(2023, 7, 10))
                .time(LocalTime.of(16, 45))
                .location(new Location("Miami", 25.7617, -80.1918))
                .create();

        galleryService.uploadPhoto(photo1);
        galleryService.uploadPhoto(photo2);
        galleryService.uploadPhoto(photo3);
    }

    @Test
    public void testUploadPhoto() {
        Set<Image> photos = galleryService.getPhotos();
        assertEquals(3, photos.size());
        assertTrue(photos.contains(photo1));
        assertTrue(photos.contains(photo2));
        assertTrue(photos.contains(photo3));
    }

    @Test
    public void testAddTagsToPhoto() {
        HashSet<String> newTags = new HashSet<>();
        newTags.add("Egypt");
        newTags.add("france");

        galleryService.addTagsToPhoto(photo1, newTags);

        Image updatedPhoto = galleryService.getPhotos().stream()
                .filter(p -> p.getId().equals(photo1.getId()))
                .findFirst()
                .orElse(null);

        assertNotNull(updatedPhoto);
        assertTrue(updatedPhoto.getTags().contains("family"));
        assertTrue(updatedPhoto.getTags().contains("vacation")); // existing tag
    }

    @Test
    public void testSearchPhotosByTags() {
        HashSet<String> searchTags = new HashSet<>();
        searchTags.add("vacation");

        List<Image> results = galleryService.searchPhotosByTags(searchTags);
        assertEquals(2, results.size());
        assertTrue(results.contains(photo1));
        assertTrue(results.contains(photo3));
        assertFalse(results.contains(photo2));
    }

    @Test
    public void testSearchPhotosByDateRange() {
        LocalDate from = LocalDate.of(2023, 5, 1);
        LocalDate to = LocalDate.of(2023, 6, 30);

        List<Image> results = galleryService.searchPhotosByDateRange(from, to);
        assertEquals(2, results.size());
        assertTrue(results.contains(photo1));
        assertTrue(results.contains(photo2));
        assertFalse(results.contains(photo3));
    }

    @Test
    public void testSearchPhotosByDateRangeInvalid() {
        LocalDate from = LocalDate.of(2023, 6, 30);
        LocalDate to = LocalDate.of(2023, 5, 1);

        assertThrows(IllegalArgumentException.class, () -> {
            galleryService.searchPhotosByDateRange(from, to);
        });
    }

    @Test
    public void testSearchPhotosByTimeRange() {
        LocalTime from = LocalTime.of(12, 0);
        LocalTime to = LocalTime.of(18, 0);

        List<Image> results = galleryService.searchPhotosByTimeRange(from, to);
        assertEquals(2, results.size());
        assertTrue(results.contains(photo1));
        assertTrue(results.contains(photo3));
        assertFalse(results.contains(photo2));
    }

    @Test
    public void testSearchPhotosByTimeRangeInvalid() {
        LocalTime from = LocalTime.of(18, 0);
        LocalTime to = LocalTime.of(12, 0);

        assertThrows(IllegalArgumentException.class, () -> {
            galleryService.searchPhotosByTimeRange(from, to);
        });
    }

    @Test
    public void testSearchPhotosByNameLocation() {
        List<Image> results = galleryService.searchPhotosByNameLocation("Paris");
        assertEquals(1, results.size());
        assertEquals(photo1, results.get(0));
    }

    @Test
    public void testSearchPhotosByLatLngLocation() {
        // Search around Paris coordinates
        List<Image> results = galleryService.searchPhotosByLatLngLocation(48.0, 2.0, 49.0, 3.0);
        assertEquals(1, results.size());
        assertEquals(photo1, results.get(0));
    }
}