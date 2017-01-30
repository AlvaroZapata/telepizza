package telepizza.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import telepizza.modelo.entidades.Ingrediente;
import telepizza.propertyeditors.IngredientePropertyEditor;
import telepizza.modelo.entidades.Pizza;
import telepizza.modelo.enumeraciones.PizzaCategoria;
import telepizza.modelo.repositorios.IngredienteRepositorio;
import telepizza.modelo.repositorios.PizzaRepositorio;


@Controller
@RequestMapping("/pizzas")
public class PizzaController {
	
	@Autowired
	private PizzaRepositorio pizzaRepo;
	
	@Autowired
	private IngredienteRepositorio ingredienteRepo;
	@Autowired private IngredientePropertyEditor ingredientePropertyEditor;
	
	@RequestMapping(method=RequestMethod.GET)
	
	public String listarPizzas(Model model){
		Iterable<Pizza> lista = pizzaRepo.findAll();
		model.addAttribute("titulo", "Listado de Pizzas");
		model.addAttribute("pizzas", lista);
		model.addAttribute("categorias", PizzaCategoria.values());
		model.addAttribute("ingredientes", ingredienteRepo.findAll());
		
		return "pizzas/listadopizza";
	}
	
	@RequestMapping ("/detalle/{id}")
	@ResponseBody
	public String detallePizza(@PathVariable int id){
		
		return "Estas consultando la pizza "+id;
	}
	
	@RequestMapping ("/editar/{id}")
	@ResponseBody
	public String editarePizza(@PathVariable int id){
		
		return "Estas editando la pizza "+id;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String guardar(Model model, @Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) {

		pizzaRepo.save(pizza);
		model.addAttribute("titulo", "Listado de Pizzas");
		model.addAttribute("pizzas", pizzaRepo.findAll());
		model.addAttribute("categorias", PizzaCategoria.values());
		return "pizzas/listadopizza";
	}
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> borrar(@PathVariable Long id){
		try {
			pizzaRepo.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			
		}
	}
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	@ResponseBody
	public Pizza buscarPizza(@PathVariable Long id){
		Pizza pizza = pizzaRepo.findOne(id);
		return pizza;
	}
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.registerCustomEditor(Ingrediente.class, ingredientePropertyEditor);
	}
}