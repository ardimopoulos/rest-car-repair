package rest.carRepair.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import rest.carRepair.domain.Member;
import rest.carRepair.services.MemberService;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllusers(){
        List<Member> allMembers = memberService.getMembers();
        return new ResponseEntity<>(allMembers, HttpStatus.OK);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getMember(@PathVariable String id){
        Long memberId = Long.valueOf(id);
        Member member = memberService.getMemberById(memberId);
        return new ResponseEntity<>(member,HttpStatus.OK);
    }
}
