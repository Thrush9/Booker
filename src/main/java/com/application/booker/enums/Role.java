package com.application.booker.enums;

import java.util.Set;

public enum Role {
    ADMIN(Set.of(Permissions.BOOKER_READ,Permissions.BOOKER_WRITE,Permissions.BOOKER_UPDATE,Permissions.BOOKER_DELETE)),
    USER(Set.of(Permissions.BOOKER_READ));


    private final Set<Permissions> permissions;

     Role(Set<Permissions> permissions){
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions(){
         return permissions;
    }
}
