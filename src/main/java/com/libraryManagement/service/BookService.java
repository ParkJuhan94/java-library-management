package com.libraryManagement.service;

import com.libraryManagement.domain.Book;
import com.libraryManagement.repository.Repository;

import java.util.List;

import static com.libraryManagement.domain.BookStatus.*;
import static com.libraryManagement.domain.ChangeBookStatus.*;
import static com.libraryManagement.domain.ChangeBookStatus.APPLYDELETE;

public class BookService {
    private final Repository repository;

    public BookService(Repository repository) {
        this.repository = repository;
    }

    public void insertBook(Book book) {
        repository.insertBook(book);
    }

    public List<Book> findBooks() {
        return repository.findAllBooks();
    }

    public List<Book> findBookByTitle(String str) {
        return repository.findBooksByTitle(str);
    }

    // bookStatus 를 반환 -> update 에러 원인을 알려주기 위해서 (대여 신청일 때만 사용)
    public String updateBookStatus(String updateType, long id) {

        if(updateType.equals(APPLYRENT.name())){
            if(isPossibleUpdateBookStatus(updateType, id)){    // 대여할 수 있다면
                repository.updateBookStatus(id, NOPOSSIBLERENT.getName());
            }else{
                return repository.findBookById(id).getStatus();
            }
        }else if(updateType.equals(APPLYRETURN.name())) {
            if(isPossibleUpdateBookStatus(updateType, id)){    // 반납할 수 있다면
                repository.updateBookStatus(id, READY.getName());
            }
        }else if(updateType.equals(APPLYLOST.name())) {
            if(isPossibleUpdateBookStatus(updateType, id)){    // 분실처리할 수 있다면
                repository.updateBookStatus(id, LOST.getName());
            }
        }else if(updateType.equals(APPLYDELETE.name())) {
            if(isPossibleUpdateBookStatus(updateType, id)){    // 삭제처리할 수 있다면
                repository.updateBookStatus(id, DELETE.getName());
            }
        }

        return null;
    }

    public Boolean isPossibleUpdateBookStatus(String updateType, long id) {
        if(updateType.equals(APPLYRENT.name())){
            // 대여가능일 때만 대여가능
            if(repository.findBookById(id).getStatus().equals(POSSIBLERENT.getName())){
                return true;
            }
            return false;
        }else if(updateType.equals(APPLYRETURN.name())) {
            // 대여중일 때만 반납 불가
            if(repository.findBookById(id).getStatus().equals(POSSIBLERENT.getName())){
                return false;
            }
            return true;
        }else if(updateType.equals(APPLYLOST.name())) {
            // 분실됨일 때만 분실처리 불가
            if(repository.findBookById(id).getStatus().equals(LOST.getName())){
                return false;
            }
            return true;
        }else if(updateType.equals(APPLYDELETE.name())) {
            // 존재하지 않는 도서일 때만 삭제처리 불가
            if(repository.findBookById(id).getStatus().equals(DELETE.getName())){
                return false;
            }
            return true;
        }

        return null;
    }
}
