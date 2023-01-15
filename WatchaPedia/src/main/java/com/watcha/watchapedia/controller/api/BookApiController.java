package com.watcha.watchapedia.controller.api;


import com.watcha.watchapedia.controller.CrudController;
import com.watcha.watchapedia.model.entity.Book;
import com.watcha.watchapedia.model.network.Header;
import com.watcha.watchapedia.model.network.request.BookApiRequest;
import com.watcha.watchapedia.model.network.response.BookApiResponse;
import com.watcha.watchapedia.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookApiController extends CrudController<BookApiRequest,BookApiResponse, Book> {
    private final BookService bookService;

    @Override
    @GetMapping("Idx") // http://localhost:8888/api/user/{id} (get)
    public Header<BookApiResponse> read(@PathVariable(name="Idx") Long id) {
        return bookService.read(id);
    }
}
