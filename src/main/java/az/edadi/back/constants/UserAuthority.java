package az.edadi.back.constants;

    public enum UserAuthority {
        ROOT("root"),
        USER_READ("user:read"),
        USER_UPDATE("user:update"),
        ADMIN_READ("admin:read"),
        ADMIN_UPDATE("admin:update");

        private final String permission;

        UserAuthority(String permission) {
            this.permission = permission;
        }

        public String getPermission() {
            return permission;
        }
}
