package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;
import domain.Plan;

@Transactional
@Service
public class CommentService {

	// Managed repository-----------------------

	@Autowired
	private CommentRepository commentRepository;

	// Supporting services -----------------
	@Autowired
	private PlanService planService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private UserService userService;

	@Autowired
	private AdministratorService administratorService;

	// Constructors --------------------------
	public CommentService() {
		super();
	}

	// Simple CRUD methods -----------------
	/**
	 * Constructor por defecto de CommentService
	 * 
	 * @return Comment comment
	 */
	public Comment create(int idPlan) {
		Comment comment = new Comment();
		comment.setDate(new Date());
		Plan plan = planService.findOne(idPlan);
		comment.setPlan(plan);

		// Customer customer = customerService.findByPrincipal();
		// Administrator admin = administratorService.findByPrincipal();
		// if (customer != null && admin == null) {
		// comment.setUser(customer);
		// }
		// if (customer == null && admin != null) {
		// comment.setUser(admin);
		// }

		return comment;
	}

	/**
	 * Devuelve una colección con todos los objetos de tipo Comment
	 * 
	 * @return Collection<Comment> comments
	 */
	public Collection<Comment> findAll() {
		return commentRepository.findAll();
	}

	/**
	 * Devuelve una instancia de un objetos de tipo Comment En caso de no
	 * encontrarse, devuelve null
	 * 
	 * @return Comment comment
	 */
	public Comment findOne(int commentId) {
		return commentRepository.findOne(commentId);
	}

	/**
	 * Persiste (guarda o crea) el objeto de tipo Comment en la base de datos a
	 * través del repositorio CommentRepository
	 * 
	 * @return void
	 */
	public void save(Comment comment) {
		Assert.notNull(comment);
		// TODO Restricciones de Save
		commentRepository.save(comment);
	}

	/**
	 * Elimina el objeto de tipo Comment de la base de datos a través del
	 * repositorio CommentRepository
	 * 
	 * @return void
	 */
	public void delete(Comment comment) {
		Assert.notNull(comment);
		// TODO Restricciones de Borrado

		commentRepository.delete(comment);
	}

	// Other business methods ----------------

	public Collection<Comment> findCommentByPlan(int planId) {
		Assert.notNull(planService.findOne(planId));
		return commentRepository.findCommentByPlan(planId);
	}

	// Assertions

}
