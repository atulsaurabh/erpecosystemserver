package com.dlinkddns.atulsaurabh.server.controller;


import com.dlinkddns.atulsaurabh.server.entity.SocietyMember;
import com.dlinkddns.atulsaurabh.server.message.SystemMessages;
import com.dlinkddns.atulsaurabh.server.service.SocietyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/societymember")
public class UserRegistrationController
{
    @Autowired
    private SocietyMemberService societyMemberService;
   @PostMapping
    public ResponseEntity addSocietyMember(@RequestBody  SocietyMember member)
   {
         if (societyMemberService.isMemberPresent(member.getHousetype(),member.getHousenumber()))
         {
             return new ResponseEntity(SystemMessages.MEMBER_ALREADY_REGISTERED,HttpStatus.FOUND);
         }
         else
         {
             if (societyMemberService.registerMember(member))
             {
                 return new ResponseEntity(SystemMessages.MEMBER_ADDED_SUCCESSFULLY,HttpStatus.CREATED);
             }
             else
                 return new ResponseEntity(SystemMessages.UNABLE_TO_ADD_MEMBER,HttpStatus.INTERNAL_SERVER_ERROR);
         }
   }
}
