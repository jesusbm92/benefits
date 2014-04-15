package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import domain.Sponsor;

@Transactional
@Service
public class SponsorService {

	// Managed repository-----------------------

	@Autowired
	private SponsorRepository sponsorRepository;

	// Supporting services -----------------

	// Constructors --------------------------
	public SponsorService() {
		super();
	}

	// Simple CRUD methods -----------------
	/**
	 * Constructor por defecto de SponsorService
	 * 
	 * @return Sponsor sponsor
	 */
	public Sponsor create() {
		Sponsor sponsor = new Sponsor();

		return sponsor;
	}

	/**
	 * Devuelve una colección con todos los objetos de tipo Sponsor
	 * 
	 * @return Collection<Sponsor> sponsors
	 */
	public Collection<Sponsor> findAll() {
		return sponsorRepository.findAll();
	}

	/**
	 * Devuelve una instancia de un objetos de tipo Sponsor En caso de no
	 * encontrarse, devuelve null
	 * 
	 * @return Sponsor sponsor
	 */
	public Sponsor findOne(int sponsorId) {
		return sponsorRepository.findOne(sponsorId);
	}

	/**
	 * Persiste (guarda o crea) el objeto de tipo Sponsor en la base de datos a
	 * través del repositorio SponsorRepository
	 * 
	 * @return void
	 */
	public void save(Sponsor sponsor) {
		// TODO Restricciones de Save

		sponsorRepository.save(sponsor);
	}

	/**
	 * Elimina el objeto de tipo Sponsor de la base de datos a través del
	 * repositorio SponsorRepository
	 * 
	 * @return void
	 */
	public void delete(Sponsor sponsor) {
		Assert.notNull(sponsor);
		// TODO Restricciones de Borrado

		sponsorRepository.delete(sponsor);
	}

	// Other business methods ----------------

	// Assertions

}
