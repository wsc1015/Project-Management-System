package ProjectGUI;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;



public class ProjectService {
	private EntityManager manager;

	public ProjectService(EntityManager manager) {
		this.manager = manager;
	}

	public Project createProject(int project_id, String project_name,
			String project_description, String project_desired_outcome
			) {
		Project project = new Project();
		project.setProject_id(project_id);
		project.setProject_name(project_name);
		project.setProject_description(project_description);
		project.setProject_desired_outcome(project_desired_outcome);
		manager.persist(project);
		return project;
	}

	// method to read a record
	public Project readProject(int project_id) {
		Project project = manager.find(Project.class, project_id);
		return project;
	}

	// method to read all records
	public List<Project> readAll() {
		TypedQuery<Project> query = manager.createQuery(
				"select e from project e", Project.class);
		List<Project> result = query.getResultList();
		return result;
	}

	// method to update a record
	public Project updateProject(int project_id, String project_name,
			String project_description, String project_desired_outcome
			) {
		Project project = manager.find(Project.class, project_id);
		if (project != null) {
			project.setProject_name(project_name);
			project.setProject_description(project_description);
			project.setProject_desired_outcome(project_desired_outcome);
		}
		return project;
	}

	// method to delete a record
	public void deleteProject(int project_id) {
		Project project = manager.find(Project.class, project_id);
		if (project != null) {
			manager.remove(project);
		}
	}
}
