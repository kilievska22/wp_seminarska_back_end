package com.example.demo.web;

import com.example.demo.model.Member;
import com.example.demo.service.MembersRestService;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/members", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)

public class MembersApi {

    private final MembersRestService membersRestService;

    public MembersApi(MembersRestService membersRestService) {

        this.membersRestService = membersRestService;
    }

    @GetMapping
    public Page<Member> getAllMembers(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                          @RequestParam(name = "page-size", defaultValue = "5", required = false) int size){
        return this.membersRestService.getAllMembers(page,size);

    }
    @GetMapping("/{Id}")
    public Optional<Member> getMemberById(@PathVariable("Id")Integer id){
        return this.membersRestService.findMemberById(id);
    }
    @DeleteMapping("/{Id}")
    public void deleteMember(@PathVariable("Id")Integer id){
        this.membersRestService.delete_Member(id);
    }
    @PatchMapping("/{Id}")
    public Member editMember(@PathVariable("ESSN")Integer ESSN, @RequestParam ("name")String name,
                                @RequestParam ("membership_start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate membership_start, @RequestParam("membership_expiration")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate membership_expiration, @RequestParam("email")String email, @RequestParam("phone")String phone,@RequestParam(value = "ESSN", defaultValue = "0", required = false) Integer ESSN1){






        return this.membersRestService.editMember(ESSN, name, membership_start,membership_expiration,email,phone);
    }
    @GetMapping("/search")
    public List<Member> search(@RequestParam("term") String term){

        return this.membersRestService.searchAllMembers(term);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Member createMember( @RequestParam("ESSN")Integer ESSN, @RequestParam ("name")String name,
                                    @RequestParam ("membership_start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate membership_start, @RequestParam("membership_expiration") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate membership_expiration, @RequestParam("email")String email, @RequestParam("phone")String phone){



        Member result=this.membersRestService.createMember(new Member (ESSN, name,membership_start,membership_expiration,email,phone));
        //response.setHeader("Location", builder.path("/books/{id}").buildAndExpand(result.getName()).toUriString());
        return result;

    }

}
