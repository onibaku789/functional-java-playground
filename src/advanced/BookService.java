package advanced;

import java.util.List;
import java.util.function.Predicate;

public interface BookService {
    List<Book> getAllBooksForAuthor(final Author author);

    List<Book> getAllBooksSortedByTitleAscending();

    List<Book> getAllBookSortedByPriceDescending();

    List<Book> getDiscountedBookPrices();

    Long getCountOfBooksWithFirstLetter(final Predicate<String> startWithLetter);

    List<Book> getAllBooks();

    String getLongestSubtitle();

}