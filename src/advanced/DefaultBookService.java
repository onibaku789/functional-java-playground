package advanced;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DefaultBookService implements BookService {
    private final DataProvider dataProvider;
    private final Comparator<Book> bookTitleAscending = Comparator.comparing(Book::getTitle);
    private final Comparator<Book> bookTitleDescending = bookTitleAscending.reversed();

    public DefaultBookService(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public static void main(String[] args) {
        DefaultBookService defaultBookService = new DefaultBookService(new DataProvider());
        defaultBookService.test();
    }

    public void test() {
        DefaultBookService defaultBookService = new DefaultBookService(new DataProvider());
        List<Book> allBooksForAuthor = defaultBookService.getAllBooksForAuthor(new Author("Philip", "Dickens"));
        System.out.println("allBooksForAuthor = " + allBooksForAuthor.stream().map(Book::getTitle).collect(Collectors.toList()));

        List<Book> allBooksSortedByTitleAscending = defaultBookService.getAllBooksSortedByTitleAscending();
        System.out.println("allBooksSortedByTitleAscending = " + allBooksSortedByTitleAscending.stream().map(Book::getTitle).collect(Collectors.toList()));

        List<Book> allBookSortedByPriceDescending = defaultBookService.getAllBookSortedByPriceDescending();
        System.out.println("allBookSortedByPriceDescending = " + allBookSortedByPriceDescending.stream().map(Book::getTitle).collect(Collectors.toList()));

        List<Book> allBooks = defaultBookService.getAllBooks();
        String booksWithDefaultPrices = allBooks.stream()
                .map(book -> book.getTitle() + ": " + book.getPrice())
                .collect(Collectors.joining(", ", "Books with default prices: ", ""));
        System.out.println(booksWithDefaultPrices);

        List<Book> discountedBookPrices = defaultBookService.getDiscountedBookPrices(this::getDiscountPrice);
        String booksWithDiscountPrices = discountedBookPrices.stream()
                .map(book -> book.getTitle() + ": " + book.getPrice())
                .collect(Collectors.joining(", ", "Books with discount prices: ", ""));
        System.out.println(booksWithDiscountPrices);
    }

    private BigDecimal getDiscountPrice(BigDecimal bigDecimal) {
        boolean priceMoreThanTwoThousand = bigDecimal.compareTo(BigDecimal.valueOf(2000)) >= 0;
        if (priceMoreThanTwoThousand) {
            return bigDecimal.multiply(BigDecimal.valueOf(0.85));
        }
        return bigDecimal;
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
    public List<Book> getDiscountedBookPrices(final Function<BigDecimal, BigDecimal> discountCalculator) {
        return dataProvider.getAllBooks().stream()
                .map(book -> book.bookWithPrice(discountCalculator.apply(book.getPrice())))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBooksWithStartingLetter(final Predicate<String> startWithLetter) {
        return dataProvider.getAllBooks().stream()
                .map(Book::getTitle)
                .filter(startWithLetter)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getAllBooks() {
        return dataProvider.getAllBooks();
    }
}
