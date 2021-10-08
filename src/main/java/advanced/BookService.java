package advanced;

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

    Map<String, Set<Book>> getBooksByTitleFirstLetter();

}
