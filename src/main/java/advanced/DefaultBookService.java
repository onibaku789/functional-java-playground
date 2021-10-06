package advanced;


import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static advanced.Type.KID;
import static advanced.Type.NEW_RELEASE;
import static advanced.Type.OLD;

public class DefaultBookService implements BookService {
    private final DataProvider dataProvider;
    private final Comparator<Book> bookTitleAscending = Comparator.comparing(Book::getTitle);
    private final Comparator<Book> bookTitleDescending = bookTitleAscending.reversed();
    private final Function<String, Predicate<String>> startsWithLetter = letter -> title -> title.startsWith(letter);

    public DefaultBookService(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public static void main(String[] args) {
        DefaultBookService defaultBookService = new DefaultBookService(new DataProvider());
        defaultBookService.test();
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
    public Long getCountOfBooksWithFirstLetter(final Predicate<String> startWithLetter) {
        return dataProvider.getAllBooks().stream()
                .map(Book::getTitle)
                .filter(startWithLetter)
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

    public void test() {
        List<Book> allBooksForAuthor = getAllBooksForAuthor(new Author("Philip", "Dickens"));
        System.out.println("allBooksForAuthor = " + getTitleOfBooks(allBooksForAuthor));
        printNL();
        List<Book> allBooksSortedByTitleAscending = getAllBooksSortedByTitleAscending();
        System.out.println("allBooksSortedByTitleAscending = " + getTitleOfBooks(allBooksSortedByTitleAscending));
        printNL();
        List<Book> allBookSortedByPriceDescending = getAllBookSortedByPriceDescending();
        System.out.println("allBookSortedByPriceDescending = " + getTitleOfBooks(allBookSortedByPriceDescending));
        printNL();
        List<Book> allBooks = getAllBooks();
        String booksWithDefaultPrices = getFormattedMessage(allBooks, "Books with default prices: ");
        System.out.println(booksWithDefaultPrices);
        printNL();
        List<Book> discountedBookPrices = getDiscountedBookPrices();
        String booksWithDiscountPrices = getFormattedMessage(discountedBookPrices, "Books with discount prices: ");
        System.out.println(booksWithDiscountPrices);
        printNL();
        Long bookBeginWithFCount = getCountOfBooksWithFirstLetter(startsWithLetter.apply("F"));
        System.out.println("bookBeginWithFCount = " + bookBeginWithFCount);
        printNL();
        String longestSubtitle = getLongestSubtitle();
        System.out.println("longestSubtitle = " + longestSubtitle);
    }

    private List<String> getTitleOfBooks(List<Book> allBookSortedByPriceDescending) {
        return allBookSortedByPriceDescending.stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    private String getFormattedMessage(List<Book> books, String title) {
        return books.stream()
                .map(book -> book.getTitle() + ": " + book.getPrice())
                .collect(Collectors.joining(", ", title, ""));
    }

    private void printNL() {
        System.out.print("\n");
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
            return price.multiply(BigDecimal.valueOf(.85));
        }
        return price.multiply(BigDecimal.valueOf(.75));
    }

    private BigDecimal getNewlyReleasedBookDiscountedPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.valueOf(2000)) > 0) {
            return price.multiply(BigDecimal.valueOf(.60)).add(BigDecimal.valueOf(500));
        }
        return price.multiply(BigDecimal.valueOf(.75));
    }

    private BigDecimal getKidsBookDiscountedPrice(BigDecimal price) {
        return price.multiply(BigDecimal.valueOf(0.5)).subtract(BigDecimal.ONE);
    }

}
