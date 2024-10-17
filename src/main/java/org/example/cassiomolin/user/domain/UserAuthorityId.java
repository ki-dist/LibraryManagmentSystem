package org.example.cassiomolin.user.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserAuthorityId implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="user_id")
		private Long user_id;
		public Long getUser_id() {
			return user_id;
		}
		public void setUser_id(Long user_id) {
			this.user_id = user_id;
		}
		public String getAuthority() {
			return authority;
		}
		public void setAuthority(String authority) {
			this.authority = authority;
		}
		 @Column(name="authority")
		private String authority;
		 @Override
		    public boolean equals(Object o) {
		        if (this == o) return true;
		        if (!(o instanceof UserAuthorityId)) return false;
		        UserAuthorityId that = (UserAuthorityId) o;
		        return Objects.equals(getUser_id(), that.getUser_id()) &&
		                Objects.equals(this.getAuthority(), that.getAuthority());
		  }
		 
		    @Override
		    public int hashCode() {
		        return Objects.hash(this.getUser_id(), this.getAuthority());
		    }
}
