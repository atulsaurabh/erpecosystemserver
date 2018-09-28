package com.dlinkddns.atulsaurabh.server.controller;


import com.dlinkddns.atulsaurabh.server.dto.AuthResult;
import com.dlinkddns.atulsaurabh.server.dto.LoginDetail;
import com.dlinkddns.atulsaurabh.server.entity.SocietyMember;
import com.dlinkddns.atulsaurabh.server.message.SystemMessages;
import com.dlinkddns.atulsaurabh.server.service.SocietyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
    @Autowired
    private SocietyMemberService societyMemberService;

    /**
     * <h3>Purpose</h3>
     * <p>The method register a new society member</p>
     *
     * @param member an instance of {@link SocietyMember}
     * @return a {@link ResponseEntity}. This encapsulates a system driven message and {@link HttpStatus}.
     */
    @PostMapping("/societymember")
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

   @PostMapping("/memberlogin")
   public ResponseEntity<AuthResult> memberLogin(@RequestBody LoginDetail detail)
   {
       AuthResult result=societyMemberService.isValidMember(detail.getUsername(),detail.getPassword());
       return new ResponseEntity<>(result,HttpStatus.OK);
   }
}
