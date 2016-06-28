package io.mackness.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.mackness.repositories.ContactsRepository;
import models.Contact;

@Controller
@RequestMapping("/")
public class HomeController {

	private ContactsRepository contactsRepository;
	
	@Autowired
	public HomeController(ContactsRepository contactsRepository) {
		this.contactsRepository = contactsRepository;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String home(Map<String,Object> model){
		List<Contact> contacts = contactsRepository.findAll();;
		model.put("contacts", contacts);
		return "home";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String submit(Contact contact){
		contactsRepository.save(contact);
		return "redirect:/";
	}
	
}	
