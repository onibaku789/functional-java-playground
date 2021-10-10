package advanced;


import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static advanced.Type.KID;
import static advanced.Type.NEW_RELEASE;
import static advanced.Type.OLD;

public class DefaultBookService implements BookService {
    private final DataProvider dataProvider;
    private final Comparator<Book> bookTitleAscending = Comparator.comparing(Book::getTitle);
    private final Comparator<Book> bookTitleDescending = bookTitleAscending.reversed();

    public DefaultBookService(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @Override
    public List<Book> getAllBooksForAuthor(Author author) {
        return null;
    }

    @Override
    public List<Book> getAllBooksSortedByTitleAscending() {
        return null;
    }

    @Override
    public List<Book> getAllBookSortedByTitleDescending() {
        return null;
    }

    @Override
    public List<Book> getDiscountedBooks() {
        return null;
    }

    @Override
    public Long getCountOfBooksWithFirstLetter(String letter) {
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public String getLongestSubtitle() {
        return null;
    }

    @Override
    public Map<Character, Set<String>> getBookTitleByFirstLetter() {
        return null;
    }

    @Override
    public Map<Type, BigDecimal> getAveragePriceByBookType() {
        return null;
    }

    private BigDecimal calculateDiscount(Book book) {
        final BigDecimal price = book.getPrice();
        return switch (book.getType()) {
            case OLD -> OLD.calculatePrice(price);
            case NEW_RELEASE -> NEW_RELEASE.calculatePrice(price);
            case KID -> KID.calculatePrice(price);
        };
    }
}
