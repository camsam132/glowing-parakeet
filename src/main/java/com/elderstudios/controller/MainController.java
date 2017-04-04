
package com.elderstudios.controller;


import com.elderstudios.domain.blogPost;
import com.elderstudios.service.blogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController {

	@Autowired
	protected blogPostService blogPostService;

	private static final String blogPost_FORM = "guestbook";
	private static final String ENTRIES_KEY = "entries";
	private static final String FORM_KEY = "form";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayGuestbook( Model model ) {

		model.addAttribute(ENTRIES_KEY, blogPostService.findAll());
		model.addAttribute(FORM_KEY, new blogPost());

		return blogPost_FORM;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String changeblogPost(
			Model model,
			@Valid @ModelAttribute(FORM_KEY) blogPost form,
			BindingResult bindingResult ) {

		if ( bindingResult.hasErrors() ) {
			model.addAttribute(ENTRIES_KEY, blogPostService.findAll());
			return blogPost_FORM;
		}

		blogPostService.save(form);

		return "redirect:/";
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public String deleteEntry (Model model, @PathVariable Long id) {

		blogPostService.delete (id);

		return "redirect:/";
	}

	@RequestMapping (value="/edit/{id}", method = RequestMethod.GET)
	public String editEntry (Model model, @PathVariable Long id) {
		model.addAttribute (FORM_KEY, blogPostService.findOne (id));
		return blogPost_FORM;
	}

	@RequestMapping (value="/edit/{id}", method = RequestMethod.POST)
	public String editSaveblogPost (Model model,
									 @PathVariable Long id,
									 @Valid @ModelAttribute(FORM_KEY) blogPost form,
									 BindingResult bindingResult ) {

		if ( bindingResult.hasErrors() ) {
			model.addAttribute(ENTRIES_KEY, blogPostService.findAll());
			return blogPost_FORM;
		}

		blogPost existing = blogPostService.findOne (id);
		existing.setFirstName (form.getFirstName());
		existing.setPost(form.getPost());
		existing.setAge(form.getAge());
		blogPostService.save (existing);

		return "redirect:/";
	}


}