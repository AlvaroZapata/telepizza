package telepizza.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import telepizza.modelo.entidades.Ingrediente;
import telepizza.modelo.enumeraciones.IngredienteCategoria;
import telepizza.modelo.repositorios.IngredienteRepositorio;

@Controller
@RequestMapping("/ingredientes")
public class IngredienteController {

	@Autowired
	private IngredienteRepositorio ingredienteRepo;

	@RequestMapping(method = RequestMethod.GET)
	public String ListarIngredientes(Model model) {

		Iterable<Ingrediente> lista = ingredienteRepo.findAll();
		model.addAttribute("titulo", "Listado de Ingredientes");
		model.addAttribute("ingredientes", lista);
		model.addAttribute("categorias", IngredienteCategoria.values());
		return "ingredientes/listado"; // WEB-INF/ingredientes/listado.jsp
	}

	@RequestMapping(method = RequestMethod.POST)
	public String guardar(Model model, @Valid @ModelAttribute Ingrediente ingrediente, BindingResult bindingResult) {

		ingredienteRepo.save(ingrediente);
		model.addAttribute("titulo", "Listado de Ingredientes");
		model.addAttribute("ingredientes", ingredienteRepo.findAll());
		model.addAttribute("categorias", IngredienteCategoria.values());
		return "ingredientes/listado";
	}
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> borrar(@PathVariable Long id){
		try {
			ingredienteRepo.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			
		}
	}
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	@ResponseBody
	public Ingrediente buscarIngrediente(@PathVariable Long id){
		Ingrediente ingrediente = ingredienteRepo.findOne(id);
		return ingrediente;
	}
}