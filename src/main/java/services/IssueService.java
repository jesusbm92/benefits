package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.IssueRepository;
import domain.Issue;


@Transactional
@Service
public class IssueService{
		
		// Managed repository-----------------------

		@Autowired
		private IssueRepository issueRepository;

		// Supporting services -----------------
		
		// Constructors --------------------------
		public IssueService() {
			super();
		}

		// Simple CRUD methods -----------------
		/**
		 * Constructor por defecto de IssueService
		 * @return Issue issue
		 */
		 public Issue create() {
			Issue issue = new Issue();

			return issue;
		}

		/**
		 * Devuelve una colecci�n con todos los objetos de tipo Issue
		 * @return Collection<Issue> issues
		 */
		public Collection<Issue> findAll() {
			return issueRepository.findAll();
		}

		/**
		 * Devuelve una instancia de un objetos de tipo Issue
		 * En caso de no encontrarse, devuelve null
		 * @return Issue issue
		 */
		public Issue findOne(int issueId) {
			return issueRepository.findOne(issueId);
		}
		
		/**
		 * Persiste (guarda o crea) el objeto de tipo Issue en la base de datos
		 * a trav�s del repositorio IssueRepository
		 * @return void
		 */
		public void save(Issue issue) {
			Assert.notNull(issue);
			//TODO Restricciones de Save
			
			issueRepository.save(issue);
		}
		
		/**
		 * Elimina el objeto de tipo Issue de la base de datos
		 * a trav�s del repositorio IssueRepository
		 * @return void
		 */
		public void delete(Issue issue) {
			Assert.notNull(issue);
			//TODO Restricciones de Borrado
			
			issueRepository.delete(issue);
		}


		// Other business methods ----------------

			
		//Assertions
				
}


