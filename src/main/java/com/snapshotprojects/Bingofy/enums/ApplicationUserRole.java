package com.snapshotprojects.Bingofy.enums;

import static com.snapshotprojects.Bingofy.enums.ApplicationUserPermission.COURSE_READ;
import static com.snapshotprojects.Bingofy.enums.ApplicationUserPermission.COURSE_WRITE;
import static com.snapshotprojects.Bingofy.enums.ApplicationUserPermission.NONADMIN_READ;
import static com.snapshotprojects.Bingofy.enums.ApplicationUserPermission.NONADMIN_WRITE;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum ApplicationUserRole {
	NONADMIN(Sets.newHashSet(NONADMIN_READ, COURSE_READ,NONADMIN_WRITE)), ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, NONADMIN_READ, NONADMIN_WRITE)),
	ADMINTRAINEE(Sets.newHashSet(COURSE_READ, NONADMIN_READ));

	private final Set<ApplicationUserPermission> permissions;

	ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}

	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("Role_" + this.name()));
		System.out.println("getGrantedAuthorities method perrmission values are : " + getPermissions());
		return permissions;
	}
}