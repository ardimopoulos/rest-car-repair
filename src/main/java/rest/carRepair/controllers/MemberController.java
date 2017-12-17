package rest.carRepair.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rest.carRepair.domain.Member;
import rest.carRepair.exceptions.member.MemberExistException;
import rest.carRepair.exceptions.member.MemberNotFoundException;
import rest.carRepair.exceptions.member.MembersNotFoundException;
import rest.carRepair.services.MemberService;

import java.net.URI;
import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllusers() throws MembersNotFoundException {
        List<Member> allMembers = memberService.getMembers();
        return new ResponseEntity<>(allMembers, HttpStatus.OK);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Object> getMember(@PathVariable Long id) throws MemberNotFoundException {
        Member member = memberService.getMemberById(id);
        return new ResponseEntity<>(member,HttpStatus.OK);
    }

    @PostMapping("/members")
    public ResponseEntity<Member> saveMember(@RequestBody Member member) throws MemberExistException{
        Member newMember = memberService.saveMember(member);
        URI location =
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{d}")
                        .buildAndExpand(newMember.getUserId())
                        .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/members/{id}")
    public ResponseEntity updateMember(@PathVariable Long id, @RequestBody Member member) throws MemberNotFoundException {
        memberService.updateMember(id, member);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity deleteMember(@PathVariable Long id) throws MemberNotFoundException{
        memberService.deleteMemberById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
