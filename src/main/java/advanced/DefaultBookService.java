package advanced;


import advanced.util.BigDecimalAverageCollector;

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
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;

public class DefaultBookService implements BookService {
    private final DataProvider dataProvider;
    private final Comparator<Book> bookTitleAscending = Comparator.comparing(Book::getTitle);
    private final Comparator<Book> bookTitleDescending = bookTitleAscending.reversed();

    public DefaultBookService(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
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
    public List<Book> getAllBookSortedByTitleDescending() {
        return dataProvider.getAllBooks().stream()
                .sorted(bookTitleDescending)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getDiscountedBooks() {
        return dataProvider.getAllBooks().stream()
                .map(book -> book.bookWithPrice(calculateDiscount(book)))
                .collect(Collectors.toList());
    }

    @Override
    public Long getCountOfBooksWithFirstLetter(final String letter) {
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
    public Map<Character, Set<String>> getBookTitleByFirstLetter() {
        return dataProvider.getAllBooks().stream()
                .collect(Collectors.groupingBy(book -> book.getTitle().charAt(0), mapping(Book::getTitle, toSet())));
    }

    @Override
    public Map<Type, BigDecimal> getAveragePriceByBookType() {
        return dataProvider.getAllBooks().stream()
                .collect(groupingBy(Book::getType, mapping(Book::getPrice, new BigDecimalAverageCollector())));
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
