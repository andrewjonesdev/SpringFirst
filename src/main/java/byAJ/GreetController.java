package byAJ;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/greet")
public class GreetController {
    @Autowired
    private GreetRepository greetRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewGreet(@RequestParam String content)
            {
                Greeting g = new Greeting();
                g.setContent(content);
                greetRepository.save(g);
                return "Saved";
            }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Greeting> getAllUsers()
    {
        return greetRepository.findAll();
    }
    @PostMapping("/add")
    public String addNewerGreet(@RequestParam String content, @Valid Greeting greeting, BindingResult bindingResult)
    {
        
        if (bindingResult.hasErrors()) {
            return "greet";
        }
        Greeting g = new Greeting();
        g.setContent(content);
        greetRepository.save(g);
        return "greetResult";
    }
    @GetMapping("/greet")
    public String greetForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greet";
    }
}
