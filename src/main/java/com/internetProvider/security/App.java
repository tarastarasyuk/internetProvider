package com.internetProvider.security;

public class App {
    public static class Constants {
        public static final int ADMIN_ROLE_ID = 1;
        public static final int USER_ROLE_ID = 2;

        public static final String SESSION_USER = "user";

        public static final String ADMIN_PANEL_URL = "adminPanel";
        public static final String CLIENT_PANEL_URL = "clientPanel";
        public static final String LOGIN_URL = "login";
        public static final String TARIFFS_URL = "tariffs";

        public static final String DONT_HAVE_ACCESS_JSP = "error.jsp";

        public static final String SCE_INSTANCE_CONNECTION = "dbConnection";

        public static final String ADMIN_JSP = "WEB-INF/jsp/admin/admin.jsp";
        public static final String LOGIN_JSP = "login.jsp";
        public static final String MANAGE_TARIFFS_JSP = "WEB-INF/jsp/admin/manageTariffs.jsp";
        public static final String TARIFF_CREATION_FORM_JSP = "WEB-INF/jsp/admin/tariffCreationForm.jsp";
        public static final String MANAGE_CLIENTS_JSP = "WEB-INF/jsp/admin/manageClients.jsp";
        public static final String CLIENT_CREATION_FORM_JSP = "WEB-INF/jsp/shared/clientCreationForm.jsp";

        public static final String MANAGE_TARIFFS_URL = "manageTariffs";
        public static final String MANAGE_CLIENTS_URL = "manageClients";
        public static final String CLIENT_CREATION_FORM_URL = "clientCreationForm";
        public static final String TARIFF_CREATION_FORM_URL = "tariffCreationForm";

        public static final String EDIT_CLIENT_FORM_URL = "editClientForm";
        public static final String PAYMENT_URL = "payment";

    }

}
