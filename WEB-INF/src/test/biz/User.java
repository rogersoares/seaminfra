package test.biz;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.jboss.seam.annotations.Name;

@Entity
@Table(name="userinfo")
@Name("user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String fullName;
	private String username;
	private String password;
	private List<Profile> profileList;

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    @OneToMany
    @JoinTable(
            name="userinfo_profile",
            joinColumns = @JoinColumn( name="userinfo_id"),
            inverseJoinColumns = @JoinColumn( name="profile_id")
    )
	public List<Profile> getProfileList() {
		return profileList;
	}

	public void setProfileList(List<Profile> profileList) {
		this.profileList = profileList;
	}

}
