package com.watcha.watchapedia.controller;


import com.watcha.watchapedia.model.network.Header;

import java.io.IOException;

public interface CrudInterface<Req, Res> {
    Header<Res> create(Header<Req> request) throws IOException;
    Header<Res> read(Long id);
    Header<Res> update(Header<Req> request);
    Header<Res> delete(Long id);
}
