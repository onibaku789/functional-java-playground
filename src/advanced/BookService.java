package advanced;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface BookService {
    List<Book> getAllBooksForAuthor(Author author);

    List<Book> getAllBooksSortedByTitleAscending();

    List<Book> getAllBookSortedByPriceDescending();

    List<Book> getDiscountedBookPrices(Function<BigDecimal, BigDecimal> discountCalculator);

    List<Book> getAllBooksWithStartingLetter(Predicate<String> startWithLetter);

}
