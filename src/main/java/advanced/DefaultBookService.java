package advanced;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static advanced.Type.KID;
import static advanced.Type.NEW_RELEASE;
import static advanced.Type.OLD;
import static java.util.stream.Collectors.toSet;

public class DefaultBookService implements BookService {
    private final DataProvider dataProvider;
    private final Comparator<Book> bookTitleAscending = Comparator.comparing(Book::getTitle);
    private final Comparator<Book> bookTitleDescending = bookTitleAscending.reversed();

    public DefaultBookService(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public static BigDecimal getOldBookDiscountedPrice(BigDecimal price) {
        BigDecimal newPrice;
        if (price.compareTo(BigDecimal.valueOf(1000)) > 0) {
            newPrice = price.multiply(BigDecimal.valueOf(.85));
        } else {
            newPrice = price.multiply(BigDecimal.valueOf(.75));
        }
        return newPrice.setScale(1, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal getNewlyReleasedBookDiscountedPrice(BigDecimal price) {
        BigDecimal newPrice;
        if (price.compareTo(BigDecimal.valueOf(2000)) > 0) {
            newPrice = price.multiply(BigDecimal.valueOf(.60)).add(BigDecimal.valueOf(500));
        } else {
            newPrice = price.multiply(BigDecimal.valueOf(.75));
        }
        return newPrice.setScale(1, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal getKidsBookDiscountedPrice(BigDecimal price) {
        return price.multiply(BigDecimal.valueOf(0.5)).subtract(BigDecimal.ONE);
    }

    @Override
    public List<Book> getAllBooks() {
        return dataProvider.getAllBooks();
    }

    @Override
    public List<Book> getAllBooksForAuthor(final Author author) {
        return dataProvider.getAllBooks().stream()
                .filter(author::isAuthorOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getAllBooksSortedByTitleAscending() {
        return dataProvider.getAllBooks().stream()
                .sorted(bookTitleAscending)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getAllBookSortedByPriceDescending() {
        return dataProvider.getAllBooks().stream()
                .sorted(bookTitleDescending)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getDiscountedBookPrices() {
        return dataProvider.getAllBooks().stream()
                .map(book -> book.bookWithPrice(calculateDiscount(book)))
                .collect(Collectors.toList());
    }

    @Override
    public Long getCountOfBooksWithFirstLetter(String letter) {
        return dataProvider.getAllBooks().stream()
                .map(Book::getTitle)
                .filter(title -> title.startsWith(letter))
                .count();
    }

    @Override
    public String getLongestSubtitle() {
        return dataProvider.getAllBooks().stream()
                .map(Book::getSubTitle)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .max(Comparator.comparingInt(String::length))
                .orElseThrow();
    }

    @Override
    public Map<String, Set<Book>> getBooksByTitleFirstLetter() {
        return dataProvider.getAllBooks().stream()
                .collect(Collectors.groupingBy(book -> String.valueOf(book.getTitle().charAt(0)), toSet()));


    }

    private BigDecimal calculateDiscount(Book book) {
        BigDecimal price = book.getPrice();
        return switch (book.getType()) {
            case OLD -> OLD.calculatePrice(price);
            case NEW_RELEASE -> NEW_RELEASE.calculatePrice(price);
            case KID -> KID.calculatePrice(price);
        };
    }

}
