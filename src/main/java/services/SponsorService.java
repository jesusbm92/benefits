package services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

	@Autowired
	private AdministratorService administratorService;

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
		Assert.isTrue(administratorService.IAmAnAdmin());
		return sponsorRepository.findAll();
	}

	public Map<String, Integer> findAllIdName() {
		Assert.isTrue(administratorService.IAmAnAdmin());
		Map<String, Integer> map = new HashMap<String, Integer>();

		Collection<Sponsor> sponsors = sponsorRepository.findAll();

		for (Sponsor aux : sponsors) {
			map.put(aux.getName(), aux.getId());
		}

		return map;
	}

	/**
	 * Devuelve una instancia de un objetos de tipo Sponsor En caso de no
	 * encontrarse, devuelve null
	 * 
	 * @return Sponsor sponsor
	 */
	public Sponsor findOne(int sponsorId) {
		Assert.isTrue(administratorService.IAmAnAdmin());
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
		Assert.isTrue(administratorService.IAmAnAdmin());

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
		Assert.isTrue(administratorService.IAmAnAdmin());
		sponsorRepository.delete(sponsor);
	}

	// Other business methods ----------------

	// Assertions

}
