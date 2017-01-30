package telepizza.modelo.servicios;

import org.springframework.beans.factory.annotation.Autowired;

import telepizza.modelo.entidades.Pizza;
import telepizza.modelo.repositorios.PizzaRepositorio;

public class PizzaServicio {

	@Autowired
	private PizzaRepositorio repo;	
	
	public Iterable<Pizza> listar(){
		return repo.findAll();
	}

}
