package advanced;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface BookService {
    List<Book> getAllBooksForAuthor(final Author author);

    List<Book> getAllBooksSortedByTitleAscending();

    List<Book> getAllBookSortedByPriceDescending();

    List<Book> getDiscountedBookPrices(final Function<BigDecimal, BigDecimal> discountCalculator);

    List<String> getAllBooksWithStartingLetter(final Predicate<String> startWithLetter);

    List<Book> getAllBooks();

}
