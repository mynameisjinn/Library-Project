package com.korit.library.repository;

import com.korit.library.entity.BookMst;
import com.korit.library.entity.SearchBook;
import com.korit.library.web.dto.SearchBookReqDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface SearchRepository {
    public int getUserSearchBookTotalCount(SearchBookReqDto searchBookReqDto);
    public List<SearchBook> userSearchBook(SearchBookReqDto searchBookReqDto);
}
