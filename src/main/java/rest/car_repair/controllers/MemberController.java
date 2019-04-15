package rest.car_repair.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rest.car_repair.domain.Member;
import rest.car_repair.dto.MemberDTO;
import rest.car_repair.exceptions.member.MemberExistException;
import rest.car_repair.exceptions.member.MemberNotFoundException;
import rest.car_repair.services.MemberService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

@RestController
@Validated
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/members")
    public ResponseEntity<List<MemberDTO>> getMembers() throws MemberNotFoundException {
        List<Member> allMembers = memberService.getMembers();

        Type listType = new TypeToken<List<MemberDTO>>() {}.getType();
        List<MemberDTO> allMembersDTO = modelMapper.map(allMembers, listType);

        return new ResponseEntity<>(allMembersDTO, HttpStatus.OK);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberDTO> getMember(@PathVariable @Min(1) Long id) throws MemberNotFoundException {
        Member member = memberService.getMemberById(id);
        MemberDTO memberDTO = modelMapper.map(member, MemberDTO.class);

        return new ResponseEntity<>(memberDTO, HttpStatus.OK);
    }

    @PostMapping("/members")
    public ResponseEntity<Member> saveMember(@Valid @RequestBody MemberDTO memberDTO) throws MemberExistException {
        Member member = modelMapper.map(memberDTO, Member.class);
        Member newMember = memberService.saveMember(member);

        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{d}")
                        .buildAndExpand(newMember.getUserId())
                        .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/members/{id}")
    public ResponseEntity updateMember(@PathVariable @Min(1) Long id, @Valid @RequestBody MemberDTO memberDTO) throws MemberNotFoundException {
        Member member = modelMapper.map(memberDTO, Member.class);
        memberService.updateMember(id, member);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity deleteMember(@PathVariable @Min(1) Long id) throws MemberNotFoundException{
        memberService.deleteMemberById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
