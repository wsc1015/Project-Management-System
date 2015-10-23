package TeamGUI;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class TeamService {
	private EntityManager manager;
	public TeamService(EntityManager manager) {
		this.manager = manager;
	}
	public Team createTeam(int team_id, int user_id, int project_id) {
		Team team = new Team();
		team.setTeam_id(team_id);
		team.setUser_id(user_id);
		team.setProject_id(project_id);
		manager.persist(team);
		return team;
	}

	// method to read a record
	public Team readTeam(int team_id) {
		Team team = manager.find(Team.class, team_id);
		return team;
	}

	// method to read all records
	public List<Team> readAll() {
		TypedQuery<Team> query = manager.createQuery(
				"select e from team e", Team.class);
		List<Team> result = query.getResultList();
		return result;
	}

	// method to update a record
	public Team updateTeam(int team_id, int user_id, int project_id) {
		Team team = manager.find(Team.class, team_id);
		if (team != null) {
			team.setUser_id(user_id);
			team.setProject_id(project_id);

		}
		return team;
	}

	// method to delete a record
	public void deleteTeam(int team_id) {
		Team team = manager.find(Team.class, team_id);
		if (team != null) {
			manager.remove(team);
		}
	}
}

