package advanced;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

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
    public List<Book> getAllBookSortedByPriceDescending() {
        return null;
    }

    @Override
    public List<Book> getDiscountedBookPrices() {
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

    private BigDecimal calculateDiscount(Book book) {
        BigDecimal price = book.getPrice();
        return switch (book.getType()) {
            case OLD -> OLD.calculatePrice(this::getOldBookDiscountedPrice, price);
            case NEW_RELEASE -> NEW_RELEASE.calculatePrice(this::getNewlyReleasedBookDiscountedPrice, price);
            case KID -> KID.calculatePrice(this::getKidsBookDiscountedPrice, price);
        };
    }

    private BigDecimal getOldBookDiscountedPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.valueOf(1000)) > 0) {
            return price.multiply(BigDecimal.valueOf(.85)).setScale(1, RoundingMode.HALF_EVEN);
        }
        return price.multiply(BigDecimal.valueOf(.75)).setScale(1, RoundingMode.HALF_EVEN);
    }

    private BigDecimal getNewlyReleasedBookDiscountedPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.valueOf(2000)) > 0) {
            return price.multiply(BigDecimal.valueOf(.60)).add(BigDecimal.valueOf(500)).setScale(1, RoundingMode.HALF_EVEN);
        }
        return price.multiply(BigDecimal.valueOf(.75)).setScale(1, RoundingMode.HALF_EVEN);
    }

    private BigDecimal getKidsBookDiscountedPrice(BigDecimal price) {
        return price.multiply(BigDecimal.valueOf(0.5)).subtract(BigDecimal.ONE).setScale(1, RoundingMode.HALF_EVEN);
    }
}
