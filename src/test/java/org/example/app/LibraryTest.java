package org.example.app;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LibraryTest {

    private static Library library;

    @BeforeEach
    public void setUp() {
        library = new Library();
    }

    @AfterEach
    public void init() {
        library.getBooks().clear();
    }

    @Test
    @Order(1)
    @DisplayName("Test if book is really added.")
    public void whenAddBook_ThenBookIsAdded() {
        // given
        Book book = new Book("Clean Code", "Robert C. Martin");
        int expectedBookCount = 1;

        // then
        library.addBook(book);

        // assertions & verification
        assertEquals(expectedBookCount, library.getBookCount(),
                "Shop should have at least 1 book!");
    }

    @Test
    @Order(2)
    @DisplayName("Test if all books are really added")
    public void whenAddBooks_ThenBooksAreAdded() {
        //given
        Book book = new Book("Clean Code", "Robert C. Martin");
        Book book1 = new Book("Code Complete", "Steve McConnel");
        Book book2 = new Book("Mark Twain", "The Adventures of Huckleberry Finn");
        int expectedBooksCount = 3;

        //then
        library.addBook(book);
        library.addBook(book1);
        library.addBook(book2);

        // assertions & verification
        assertEquals(expectedBooksCount, library.getBookCount(),
                "All books are in the library");
    }

    @Test
    @Order(3)
    @DisplayName("When null book is adding then exception can be thrown.")
    public void whenAddNullBook_ThenThrowException() {
        // given
        Book invalidBook = null;
        String expectedExceptionMessage =
                "Parameter [book] must not be null!";

        // then
        NullPointerException exception =
                assertThrows(NullPointerException.class, () ->
                        library.addBook(invalidBook));

        // assertions & verification
        assertEquals(expectedExceptionMessage, exception.getMessage(),
                "Incorrect exception message!");
    }

    @Test
    @Order(4)
    @DisplayName("Test if book is really removed.")
    void whenRemoveExistingBook_ThenBookIsRemoved() {
        // given
        Book book = new Book("Code Complete", "Steve McConnel");
        int expectedBookCount = 0;

        // when
        library.addBook(book);

        // then
        boolean isBookRemoved = library.removeBook(book);

        // assertions & verification
        assertTrue(isBookRemoved, "Result should be 'true'!");
        assertEquals(expectedBookCount, library.getBookCount(),
                "Shop shouldn't have any books!");
    }

    @Test
    @Order(5)
    @DisplayName("Test removing non-existing book.")
    public void whenRemoveNonExistingBook_ThenBookIsNotRemoved() {
        // given
        Book book = new Book("Mark Twain", "The Adventures of Huckleberry Finn");
        Book toRemove = new Book("Charles Dickens", "Great Expectations");
        int expectedBookCount = 1;

        // when
        library.addBook(book);

        // then
        boolean isBookRemoved = library.removeBook(toRemove);

        // assertions & verification
        assertFalse(isBookRemoved, "Result should be 'false'!");
        assertEquals(expectedBookCount, library.getBookCount(),
                "Shop should have at least 1 book!");
    }

    @Test
    @Order(6)
    @DisplayName("When null book is removing then exception can be thrown.")
    public void whenRemoveNullBook_ThenThrowException() {
        // given
        Book toRemove = null;
        String expectedExceptionMessage =
                "Parameter [book] must not be null!";

        // then
        NullPointerException exception =
                assertThrows(NullPointerException.class,
                        () -> library.removeBook(toRemove));

        // assertions & verification
        assertEquals(expectedExceptionMessage, exception.getMessage(),
                "Incorrect exception message!");
    }

}