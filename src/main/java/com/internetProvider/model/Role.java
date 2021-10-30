package com.internetProvider.model;

public enum Role {
    ADMIN, CLIENT;

    public static Role getRole(int i) {
        switch (i) {
            case 1:
                return ADMIN;
            case 2:
                return CLIENT;
            //todo there is no such role exception...
//                default:
            default:
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return null;
    }


}
