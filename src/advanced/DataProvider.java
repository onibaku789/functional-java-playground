package advanced;

import java.math.BigDecimal;
import java.util.List;

import static advanced.Type.KID;
import static advanced.Type.NEW_RELEASE;
import static advanced.Type.OLD;

public class DataProvider {
    private static final List<Book> books =
            List.of(new Book("isbn1", "Functional programming in Java", null,
                            new Author("Venkat", "Subramaniam"), BigDecimal.valueOf(2000L), NEW_RELEASE),
                    new Book("isbn2", "Clean Code", "A Handbook of Agile Software Craftsmanship",
                            new Author("Robert", "Martin"), BigDecimal.valueOf(3000L), OLD),
                    new Book("isbn3", "Refactoring", "Improving the Design of Existing Code",
                            new Author("Martin", "Fowler"), BigDecimal.valueOf(5000L), NEW_RELEASE),
                    new Book("isbn4", "Effective Java", null,
                            new Author("Joshua", "Bloch"), BigDecimal.valueOf(1303L), OLD),
                    new Book("isbn5", "Do Androids Dream of Electric Sheep?", null,
                            new Author("Philip", "Dickens"), BigDecimal.valueOf(1500L), OLD),
                    new Book("isbn6", "Coding for Kids: Python", "Learn to Code with 50 Awesome Games and Activities",
                            new Author("Adrienne", "Tacke"), BigDecimal.valueOf(5000L), KID)
            );

    public List<Book> getAllBooks() {
        return books;
    }
}
