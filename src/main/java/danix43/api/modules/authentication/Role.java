package danix43.api.modules.authentication;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long role_id;
	
    @Enumerated(EnumType.STRING)
    @NaturalId
	private RoleName name;
    
    @ManyToMany(fetch = FetchType.LAZY,
    			mappedBy = "roles")
    private Set<User> users = new HashSet<>();
    
	public Role(RoleName name) {
		this.name = name;
	}
	
	public Role() {
		
	}

	public Long getId() {
		return role_id;
	}

	public RoleName getName() {
		return name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public void setName(RoleName name) {
		this.name = name;
	}

	
}
