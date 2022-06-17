package projectCRM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String toHome() {
		return "home";
	}
	
	
	@GetMapping("/customer")
	public String toCostumers() {
		return "customer";
	}
	
	
	@GetMapping("/offer")
	public String toOffers() {
		return "offer";
	}
	
	
	@GetMapping("/quotation")
	public String toQuotations() {
		return "quotation";
	}
}
