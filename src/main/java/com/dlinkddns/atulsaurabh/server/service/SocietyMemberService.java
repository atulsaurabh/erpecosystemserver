package com.dlinkddns.atulsaurabh.server.service;


import com.dlinkddns.atulsaurabh.server.entity.SocietyMember;
import org.springframework.stereotype.Service;

@Service
public interface SocietyMemberService
{
    public boolean registerMember(SocietyMember member);
    public boolean isMemberPresent(String houseType,int houseNumber);
}
