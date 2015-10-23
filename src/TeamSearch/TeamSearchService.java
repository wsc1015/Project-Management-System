package TeamSearch;

import java.util.*;

import ProjectGUI.*;
import TeamGUI.*;
import UserGUI.*;

public class TeamSearchService {
	public static TeamTableModel teamModel = new TeamTableModel();
	public static TeamService teamService = new TeamService(teamModel.manager);
	public static List<Team> team = teamService.readAll();
	public static UserListTableModel userModel = new UserListTableModel();
	public static UserListService userService = new UserListService(
			userModel.manager);
	public static List<UserList> user = userService.readAll();
	public static ProjectTableModel projectModel = new ProjectTableModel();
	public static ProjectService projectService = new ProjectService(
			projectModel.manager);
	public static List<Project> project = projectService.readAll();

	public List<TeamSearchResult> searchTeam(String p_name, int p_id,
			String u_name, int u_id) {
		ArrayList<TeamSearchResult> result = new ArrayList<TeamSearchResult>(0);
		ArrayList<Project> tempP = new ArrayList<Project>();
		if (!p_name.equals("")) {
			for (int i = 0; i < project.size(); i++) {
				if (project.get(i).getProject_name().equals(p_name))
					tempP.add(project.get(i));
			}
			if (p_id != -1) {
				for (int i = 0; i < tempP.size(); i++) {
					if (tempP.get(i).getProject_id() != p_id)
						tempP.remove(i);
				}
			}
		} else {
			if (p_id != -1) {
				for (int i = 0; i < project.size(); i++) {
					if (project.get(i).getProject_id() == p_id)
						tempP.add(project.get(i));
				}
			}
		}
		ArrayList<Team> tempT = new ArrayList<Team>();
		for (int i = 0; i < tempP.size(); i++) {
			int pId = tempP.get(i).getProject_id();
			for (int j = 0; j < team.size(); j++) {
				if (team.get(j).getProject_id() == pId)
					tempT.add(team.get(j));
			}
		}
		System.out.println("TempT size" + tempT.size());
		// deal User Table
		ArrayList<UserList> tempU = new ArrayList<UserList>();
		if (!u_name.equals("")) {
			System.out.println("hahaa");

			System.out.println("hahaa!!!");
			System.out.println(user.size());
			for (int i = 0; i < user.size(); i++) {
				if (user.get(i).getUser_name().equals(u_name)) {
					tempU.add(user.get(i));
					System.out.println("hi");
				}
			}

			System.out.println(tempU.size());
			if (u_id != -1) {
				System.out.println(tempU.size());
				int i = 0;
				while (i < tempU.size()) {
					System.out.println(tempU.get(0).getUser_ID() != 800001);
					if (tempU.get(i).getUser_ID() != u_id) {
						System.out.println("delete");
						tempU.remove(i);
					} else {
						i++;
					}
				}
			}
			System.out.println(tempU.size());
		} else {
			if (u_id != -1) {
				for (int i = 0; i < user.size(); i++) {
					if (user.get(i).getUser_ID() == u_id)
						tempU.add(user.get(i));
				}
			}
		}
		if ((!u_name.equals("")) || (u_id != -1)) {
			// System.out.println(tempU.size());
			// System.out.println(p_name + p_id);
			// System.out.println(p_name.equals(""));
			if ((p_name.equals("") == true) && p_id == -1) {
				for (int i = 0; i < tempU.size(); i++) {
					int pId = tempU.get(i).getUser_ID();
					for (int j = 0; j < team.size(); j++) {
						if (team.get(j).getUser_id() == pId)
							tempT.add(team.get(j));
					}
				}
				System.out.println(tempT.size());
			} else {
				for (int i = 0; i < tempT.size(); i++) {
					int pId = tempT.get(i).getUser_id();
					boolean isEx = false;
					for (int j = 0; j < tempU.size(); j++) {
						if (tempU.get(j).getUser_ID() == tempT.get(i)
								.getUser_id()) {
							isEx = true;
							break;
						}
					}
					if (!isEx)
						tempT.remove(i);
				}
			}
		}
		HashSet<Team> tempTHash = new HashSet<Team>(0);
		tempTHash.addAll(tempT);
		tempT.clear();
		tempT.addAll(tempTHash);
		System.out.println(tempT.size());
		for (int i = 0; i < tempT.size(); i++) {
			TeamSearchResult newResult = new TeamSearchResult();
			for (int j = 0; j < project.size(); j++) {
				if (project.get(j).getProject_id() == tempT.get(i)
						.getProject_id()) {
					newResult.projectName = project.get(j).getProject_name();
					break;
				}
			}
			for (int j = 0; j < user.size(); j++) {
				if (user.get(j).getUser_ID() == tempT.get(i).getUser_id()) {
					newResult.memberName = user.get(j).getUser_name();
					newResult.memberType = user.get(j).getUser_type();
					newResult.memberPhone = user.get(j).getPhone();
					newResult.memberEmail = user.get(j).getEmail();
					break;
				}
			}
			result.add(newResult);
		}
		return result;
	}

	public static void main(String args[]) {
		System.out.println(team.get(0).getTeam_id());
		System.out.println(user.get(0).getUser_name());
		List<TeamSearchResult> a = new TeamSearchService().searchTeam("",
				-1, "", 800001);
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i).projectName);
		}
	}
}
