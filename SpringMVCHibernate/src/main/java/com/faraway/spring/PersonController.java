package com.faraway.spring;

import com.faraway.spring.model.UserEntity;
import com.faraway.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonController {
	
	private UserService personService;
	private int page = 0, pageCount;
	
	@Autowired(required=true)
	@Qualifier(value="personService")
	public void setPersonService(UserService ps){
		this.personService = ps;
	}
	
	@RequestMapping(value = "/person", method = RequestMethod.GET)
	public String listPersons(Model model) {
		model.addAttribute("person", new UserEntity());
		model.addAttribute("listPersons", this.personService.listPersons(page));
		model.addAttribute("page", page);
		pageCount = this.personService.listPersons().size() / 10 +
				((this.personService.listPersons().size() % 10 > 0) ? 1 : 0);
		model.addAttribute("pageCount", pageCount);
		return "person";
	}
	
	//For add and update person both
	@RequestMapping(value= "/person/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") UserEntity p){
		if(p.getId() == 0){
			//new person, add it
			this.personService.addPerson(p);
		}else{
			//existing person, call update
			this.personService.updatePerson(p);
		}
		
		return "redirect:/person";
		
	}

	//For prev page
	@RequestMapping(value= "/person/prev", method = RequestMethod.POST)
	public String prevPage(){
		page--;
		if (page < 0) page = 0;
		return "redirect:/person";
	}

	//For next page
	@RequestMapping(value= "/person/next", method = RequestMethod.POST)
	public String nextPage(){
		page++;
		if (page > pageCount)  page--;
		return "redirect:/person";
	}
	
	@RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){
		
        this.personService.removePerson(id);
        return "redirect:/person";
    }
 
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }
	
}
