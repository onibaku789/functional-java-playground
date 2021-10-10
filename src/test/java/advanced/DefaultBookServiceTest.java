package advanced;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static advanced.Type.KID;
import static advanced.Type.NEW_RELEASE;
import static advanced.Type.OLD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@SuppressWarnings("ResultOfMethodCallIgnored")
class DefaultBookServiceTest {
    private static final List<Book> books =
            List.of(new Book("isbn1", "Functional programming in Java", null,
                            new Author("Venkat", "Subramaniam"), BigDecimal.valueOf(2000), NEW_RELEASE),
                    new Book("isbn2", "Clean Code", "A Handbook of Agile Software Craftsmanship",
                            new Author("Robert", "Martin"), BigDecimal.valueOf(3000L), OLD),
                    new Book("isbn3", "Refactoring", "Improving the Design of Existing Code",
                            new Author("Martin", "Fowler"), BigDecimal.valueOf(5000L), NEW_RELEASE),
                    new Book("isbn4", "Effective Java", null,
                            new Author("Joshua", "Bloch"), BigDecimal.valueOf(1303L), OLD),
                    new Book("isbn5", "Do Androids Dream of Electric Sheep?", null,
                            new Author("Philip", "Dickens"), BigDecimal.valueOf(1500L), OLD),
                    new Book("isbn6", "Coding for Kids: Python", "Learn to Code with 50 Awesome Games and Activities",
                            new Author("Adrienne", "Tacke"), BigDecimal.valueOf(5000L), KID)
            );
    @Mock
    private DataProvider dataProvider;
    @InjectMocks
    private DefaultBookService underTest;

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of("F", 1L),
                Arguments.of("R", 1L),
                Arguments.of("C", 2L),
                Arguments.of("X", 0L)
        );
    }

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.openMocks(this);
        given(dataProvider.getAllBooks()).willReturn(books);
    }

    @AfterEach
    protected void tearUp() {
        then(dataProvider).should().getAllBooks();
        underTest = null;
    }

    @Test
    void getAllBooks() {
        //GIVEN
        final List<Book> expected = books;
        //WHEN
        final List<Book> actual = underTest.getAllBooks();
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void getAllBooksForAuthor() {
        //GIVEN
        final List<Book> expected = List.of(new Book("isbn5", "Do Androids Dream of Electric Sheep?", null,
                new Author("Philip", "Dickens"), BigDecimal.valueOf(1500L), OLD));
        final Author author = new Author("Philip", "Dickens");
        //WHEN
        final List<Book> actual = underTest.getAllBooksForAuthor(author);
        //THEN
        assertEquals(expected, actual);

    }

    @Test
    void getAllBooksSortedByTitleAscending() {
        //GIVEN
        final List<Book> expected = List.of(
                new Book("isbn2", "Clean Code", "A Handbook of Agile Software Craftsmanship",
                        new Author("Robert", "Martin"), BigDecimal.valueOf(3000L), OLD),
                new Book("isbn6", "Coding for Kids: Python", "Learn to Code with 50 Awesome Games and Activities",
                        new Author("Adrienne", "Tacke"), BigDecimal.valueOf(5000L), KID),
                new Book("isbn5", "Do Androids Dream of Electric Sheep?", null,
                        new Author("Philip", "Dickens"), BigDecimal.valueOf(1500L), OLD),
                new Book("isbn4", "Effective Java", null,
                        new Author("Joshua", "Bloch"), BigDecimal.valueOf(1303L), OLD),
                new Book("isbn1", "Functional programming in Java", null,
                        new Author("Venkat", "Subramaniam"), BigDecimal.valueOf(2000L), NEW_RELEASE),
                new Book("isbn3", "Refactoring", "Improving the Design of Existing Code",
                        new Author("Martin", "Fowler"), BigDecimal.valueOf(5000L), NEW_RELEASE)
        );
        //WHEN
        final List<Book> actual = underTest.getAllBooksSortedByTitleAscending();
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void getAllBookSortedByPriceDescending() {
        //GIVEN
        final List<Book> expected = List.of(
                new Book("isbn3", "Refactoring", "Improving the Design of Existing Code",
                        new Author("Martin", "Fowler"), BigDecimal.valueOf(5000L), NEW_RELEASE),
                new Book("isbn1", "Functional programming in Java", null,
                        new Author("Venkat", "Subramaniam"), BigDecimal.valueOf(2000L), NEW_RELEASE),
                new Book("isbn4", "Effective Java", null,
                        new Author("Joshua", "Bloch"), BigDecimal.valueOf(1303L), OLD),
                new Book("isbn5", "Do Androids Dream of Electric Sheep?", null,
                        new Author("Philip", "Dickens"), BigDecimal.valueOf(1500L), OLD),
                new Book("isbn6", "Coding for Kids: Python", "Learn to Code with 50 Awesome Games and Activities",
                        new Author("Adrienne", "Tacke"), BigDecimal.valueOf(5000L), KID),
                new Book("isbn2", "Clean Code", "A Handbook of Agile Software Craftsmanship",
                        new Author("Robert", "Martin"), BigDecimal.valueOf(3000L), OLD)
        );
        //WHEN
        final List<Book> actual = underTest.getAllBookSortedByPriceDescending();
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void getDiscountedBookPrices() {
        //GIVEN
        final List<Book> expected = List.of(new Book("isbn1", "Functional programming in Java", null,
                        new Author("Venkat", "Subramaniam"), BigDecimal.valueOf(1500.0), NEW_RELEASE),
                new Book("isbn2", "Clean Code", "A Handbook of Agile Software Craftsmanship",
                        new Author("Robert", "Martin"), BigDecimal.valueOf(2550.0), OLD),
                new Book("isbn3", "Refactoring", "Improving the Design of Existing Code",
                        new Author("Martin", "Fowler"), BigDecimal.valueOf(3500.0), NEW_RELEASE),
                new Book("isbn4", "Effective Java", null,
                        new Author("Joshua", "Bloch"), BigDecimal.valueOf(1107.6), OLD),
                new Book("isbn5", "Do Androids Dream of Electric Sheep?", null,
                        new Author("Philip", "Dickens"), BigDecimal.valueOf(1275.0), OLD),
                new Book("isbn6", "Coding for Kids: Python", "Learn to Code with 50 Awesome Games and Activities",
                        new Author("Adrienne", "Tacke"), BigDecimal.valueOf(2499.0), KID)
        );
        //WHEN
        final List<Book> actual = underTest.getDiscountedBookPrices();
        //THEN
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provider")
    void getCountOfBooksWithFirstLetter(String letter, Long expected) {
        //GIVEN

        //WHEN
        final Long actual = underTest.getCountOfBooksWithFirstLetter(letter);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void getLongestSubtitle() {
        //GIVEN

        //WHEN
        final String actual = underTest.getLongestSubtitle();
        //THEN
        assertEquals("Learn to Code with 50 Awesome Games and Activities", actual);
    }

    @Test
    void getBooksByTitleFirstLetter() {
        //GIVEN
        final Map<Character, Set<String>> expected = Map.of(
                'R', Set.of("Refactoring"),
                'C', Set.of("Clean Code", "Coding for Kids: Python"),
                'D', Set.of("Do Androids Dream of Electric Sheep?"),
                'E', Set.of("Effective Java"),
                'F', Set.of("Functional programming in Java"));
        //WHEN
        final Map<Character, Set<String>> actual = underTest.getBookTitleByFirstLetter();
        //THEN
        assertEquals(expected, actual);
    }


    @Test
    void getAveragePriceByBookType() {
        //GIVEN
        final Map<Type, BigDecimal> expected = Map.of(
                OLD, BigDecimal.valueOf(1934.33).setScale(2, RoundingMode.UNNECESSARY),
                NEW_RELEASE, BigDecimal.valueOf(3500.00).setScale(2, RoundingMode.UNNECESSARY),
                KID, BigDecimal.valueOf(5000.00).setScale(2, RoundingMode.UNNECESSARY));
        //WHEN
        final Map<Type, BigDecimal> actual = underTest.getAveragePriceByBookType();
        //THEN
        assertEquals(expected, actual);
    }
}
