package advanced;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BookService {
    List<Book> getAllBooksForAuthor(final Author author);

    List<Book> getAllBooksSortedByTitleAscending();

    List<Book> getAllBookSortedByPriceDescending();

    List<Book> getDiscountedBookPrices();

    Long getCountOfBooksWithFirstLetter(String letter);

    List<Book> getAllBooks();

    String getLongestSubtitle();

    Map<Character, Set<String>> getBookTitleByFirstLetter();

    Map<Type, BigDecimal> getAveragePriceByBookType();
}
