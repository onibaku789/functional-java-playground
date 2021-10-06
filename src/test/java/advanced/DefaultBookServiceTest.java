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
import java.util.List;
import java.util.stream.Stream;

import static advanced.Type.KID;
import static advanced.Type.NEW_RELEASE;
import static advanced.Type.OLD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


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
        underTest = null;
    }

    @Test
    void getAllBooks() {
        //GIVEN
        List<Book> expected = books;
        //WHEN
        List<Book> actual = underTest.getAllBooks();
        //THEN
        assertEquals(expected, actual);
        when(dataProvider.getAllBooks()).thenReturn(books);
    }

    @Test
    void getAllBooksForAuthor() {
        //GIVEN
        List<Book> expected = List.of(new Book("isbn5", "Do Androids Dream of Electric Sheep?", null,
                new Author("Philip", "Dickens"), BigDecimal.valueOf(1500L), OLD));
        Author author = new Author("Philip", "Dickens");
        //WHEN
        List<Book> actual = underTest.getAllBooksForAuthor(author);
        //THEN
        assertEquals(expected, actual);
        when(dataProvider.getAllBooks()).thenReturn(books);

    }

    @Test
    void getAllBooksSortedByTitleAscending() {
        //GIVEN
        List<Book> expected = List.of(
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
        List<Book> actual = underTest.getAllBooksSortedByTitleAscending();
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void getAllBookSortedByPriceDescending() {
        //GIVEN
        List<Book> expected = List.of(
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
        List<Book> actual = underTest.getAllBookSortedByPriceDescending();
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void getDiscountedBookPrices() {
        //GIVEN
        List<Book> expected = List.of(new Book("isbn1", "Functional programming in Java", null,
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
        List<Book> actual = underTest.getDiscountedBookPrices();
        //THEN
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provider")
    void getCountOfBooksWithFirstLetter(String letter, Long expected) {
        //GIVEN

        //WHEN
        Long actual = underTest.getCountOfBooksWithFirstLetter(letter);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void getLongestSubtitle() {
        //GIVEN

        //WHEN
        String actual = underTest.getLongestSubtitle();
        //THEN
        assertEquals("Learn to Code with 50 Awesome Games and Activities", actual);
    }
}
