package advanced;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

public final class Book {
    private final String isbn;
    private final String title;
    private final String subTitle;
    private final Author author;
    private final BigDecimal price;

    public Book(String isbn, String title, String subTitle, Author author, BigDecimal price) {
        this.isbn = isbn;
        this.title = title;
        this.subTitle = subTitle;
        this.author = author;
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Optional<String> getSubTitle() {
        return Optional.ofNullable(subTitle);
    }

    public Author getAuthor() {
        return author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isAuthor(Author author) {
        return getAuthor().equals(author);
    }

    public Book bookWithPrice(BigDecimal newPrice) {
        return new Book(this.isbn, this.title, this.subTitle, this.author, newPrice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn)
               && Objects.equals(title, book.title)
               && Objects.equals(subTitle, book.subTitle)
               && Objects.equals(author, book.author)
               && Objects.equals(price, book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, subTitle, author, price);
    }

    @Override
    public String toString() {
        return "Book{" +
               "isbn='" + isbn + '\'' +
               ", title='" + title + '\'' +
               ", subTitle='" + subTitle + '\'' +
               ", author=" + author +
               ", price=" + price +
               '}';
    }
}
