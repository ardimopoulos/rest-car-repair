package rest.carRepair.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import rest.carRepair.domain.Member;

import java.util.List;

@RestController
public class MemberController {

    @GetMapping("/members")
    public List<Member> getAllusers(){

        return null;
    }

    @GetMapping("/members/{id}")
    public Member getMember(@PathVariable String id){

        return null;
    }
}
