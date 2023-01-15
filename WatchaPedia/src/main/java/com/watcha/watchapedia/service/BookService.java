package com.watcha.watchapedia.service;
import com.watcha.watchapedia.model.entity.Book;
import com.watcha.watchapedia.model.network.Header;
import com.watcha.watchapedia.model.network.response.BookApiResponse;
import com.watcha.watchapedia.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    private BookApiResponse response(Book book) {
        BookApiResponse bookApiResponse = BookApiResponse.builder()
                .bookIdx(book.getBookIdx())
                .bookTitle(book.getBookTitle())
                .bookWriter(book.getBookWriter())
                .bookAtDate(book.getBookAtDate())
                .bookCategory(book.getBookCategory())
                .bookPage(book.getBookPage())
                .build();
        return bookApiResponse;
    }

    public Header<BookApiResponse> read(Long id) {
        return bookRepository.findById(id).map(book -> response(book))
                .map(Header::OK).orElseGet(()->Header.ERROR("데이터 없음"));
    }
}
