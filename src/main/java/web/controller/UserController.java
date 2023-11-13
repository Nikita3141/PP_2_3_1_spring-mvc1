package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@RequestMapping("/users")
@Controller
public class UserController {

	private UserService userService;

	public UserController(UserService userService){
		this.userService=userService;
	}

	@GetMapping
	public String get(ModelMap model) {

		model.addAttribute("users",userService.getAllUsers() );
		return "index";
	}

	@GetMapping("/")
	public String show (@RequestParam  Long id, Model model){

		model.addAttribute("user",userService.getUserById(id));
		return "user";
	}

	@GetMapping("/new")
	public String newUser (Model model){
		model.addAttribute("user",new User());
		return "new";
	}
	@PostMapping
	public String create (@ModelAttribute ("user") @Valid User user){
		userService.addUser(user);
		return "redirect:/users";
	}
	@GetMapping("/edit")
	public String edit(Model model, @RequestParam(value = "id") Long id) {
		model.addAttribute("user", userService.getUserById(id));
		return "edit";
	}

	@PatchMapping("/edit")
	public String update(@ModelAttribute("user") User user) {
		userService.updateUser( user);
		return "redirect:/users";
	}
	@DeleteMapping(value = "/delete")
	public String deleteUser(@RequestParam("id") Long id) {
		userService.removeUser(id);
		return "redirect:/users";
	}
}

