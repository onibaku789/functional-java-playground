package advanced;

import java.util.List;
import java.util.function.Predicate;

public interface BookService {
    List<Book> getAllBooksForAuthor(final Author author);

    List<Book> getAllBooksSortedByTitleAscending();

    List<Book> getAllBookSortedByPriceDescending();

    List<Book> getDiscountedBooks();

    Long getCountOfBooksWithFirstLetter(String letter);

    List<Book> getAllBooks();

    String getLongestSubtitle();

}
