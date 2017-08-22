/**
 * 
 */
package com.bebo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bebo.elastic.Book;
import com.bebo.elastic.BookService;

/**
 * @author anthakur
 *
 */
@Controller
public class BookContainer {

	@Autowired
	public BookService bookService;

	@RequestMapping(value = { "/library" }, method = RequestMethod.GET)
	public ModelAndView library() {
		ModelAndView model = new ModelAndView();
		Iterable<Book> booksIterable = bookService.findAll();
		List<Book> books = new ArrayList<Book>();
		CollectionUtils.addAll(books, booksIterable.iterator());
		model.addObject("books", books);
		model.setViewName("library");
		return model;

	}

	@RequestMapping(value = { "/addBook" }, method = RequestMethod.GET)
	public ModelAndView addBook() {
		ModelAndView model = new ModelAndView();
		model.addObject("book", new Book());
		model.setViewName("addBook");
		return model;

	}

	@RequestMapping(value = { "/processBook" }, method = RequestMethod.POST)
	public ModelAndView processBook(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("book") Book book) {
		bookService.save(book);
		ModelAndView model = new ModelAndView();
		Iterable<Book> booksIterable = bookService.findAll();
		List<Book> books = new ArrayList<Book>();
		CollectionUtils.addAll(books, booksIterable.iterator());
		model.addObject("books", books);
		model.setViewName("library");
		return model;

	}
}
