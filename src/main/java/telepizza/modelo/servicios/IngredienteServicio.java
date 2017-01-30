package telepizza.modelo.servicios;

import org.springframework.beans.factory.annotation.Autowired;

import telepizza.modelo.entidades.Ingrediente;
import telepizza.modelo.repositorios.IngredienteRepositorio;

public class IngredienteServicio {

	@Autowired
	private IngredienteRepositorio repo;	
	
	public Iterable<Ingrediente> listar(){
		return repo.findAll();
	}

}
