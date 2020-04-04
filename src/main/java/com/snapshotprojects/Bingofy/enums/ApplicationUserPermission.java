package com.snapshotprojects.Bingofy.enums;

public enum ApplicationUserPermission {
	NONADMIN_READ("nonadmin:read"), NONADMIN_WRITE("nonadmin:write"), COURSE_WRITE("course:write"),
	COURSE_READ("course:read");

	private final String permission;

	ApplicationUserPermission(String permission) {
		System.out.println("ApplicationUserPermission constructor initialized");
		this.permission = permission;
	}

	public String getPermission() {
		System.out.println("getPermission called from ApplicationUserPermission");
		return permission;
	}
}
