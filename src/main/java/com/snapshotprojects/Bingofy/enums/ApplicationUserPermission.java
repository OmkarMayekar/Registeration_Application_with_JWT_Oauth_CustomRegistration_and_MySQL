package com.snapshotprojects.Bingofy.enums;

public enum ApplicationUserPermission {
	NONADMIN_READ("nonadmin:read"), NONADMIN_WRITE("nonadmin:write"), COURSE_WRITE("course:write"),
	COURSE_READ("course:read");

	private final String permission;

	ApplicationUserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
}
