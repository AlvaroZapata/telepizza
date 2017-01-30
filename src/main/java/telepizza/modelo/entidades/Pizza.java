package telepizza.modelo.entidades;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import telepizza.modelo.entidades.Pizza;
import telepizza.modelo.enumeraciones.PizzaCategoria;


@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotEmpty
	private String nombre;

	@NotNull
	private Double precio;

	@NotNull
	@Enumerated(EnumType.STRING)
	private PizzaCategoria categoria;

	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Ingrediente> ingredientes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public PizzaCategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(PizzaCategoria categoria) {
		this.categoria = categoria;
	}

	public Set<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(Set<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
}