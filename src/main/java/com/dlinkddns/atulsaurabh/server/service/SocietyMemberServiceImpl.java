package com.dlinkddns.atulsaurabh.server.service;

import com.dlinkddns.atulsaurabh.server.dto.AuthResult;
import com.dlinkddns.atulsaurabh.server.entity.SocietyMember;
import com.dlinkddns.atulsaurabh.server.repository.SocietyMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SocietyMemberServiceImpl implements SocietyMemberService
{
    @Autowired
    private SocietyMemberRepository societyMemberRepository;
    @Override
    public boolean registerMember(SocietyMember member)
    {
        return societyMemberRepository.saveAndFlush(member) != null;
    }

    @Override
    public boolean isMemberPresent(String houseType, int houseNumber) {
        SocietyMember societyMember = societyMemberRepository.findAllByHousetypeAndHousenumber(houseType,houseNumber);
        return societyMember != null;
    }

    @Override
    public AuthResult isValidMember(String userName, String passphrase) {
        AuthResult result=null;
        SocietyMember member = societyMemberRepository.findByUsernameAndPassphrase(userName,passphrase);
          if (member != null)
          {
              result = new AuthResult();
              result.setRoles(member.getMemberRoles());
              result.setResult(true);
          }
          else
          {
             result=new AuthResult();
             result.setResult(false);
             result.setRoles(null);
          }
          return result;
    }
}
