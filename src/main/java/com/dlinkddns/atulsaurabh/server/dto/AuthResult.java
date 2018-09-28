package com.dlinkddns.atulsaurabh.server.dto;

import com.dlinkddns.atulsaurabh.server.entity.MemberRole;

import java.util.List;
import java.util.Set;

public class AuthResult
{
    public Set<MemberRole> roles;
    public boolean result;

    public Set<MemberRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<MemberRole> roles) {
        this.roles = roles;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
