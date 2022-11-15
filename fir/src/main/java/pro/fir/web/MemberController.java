package pro.fir.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pro.fir.domain.members.Member;
import pro.fir.service.MemberService;
import pro.fir.web.dto.MemberFormDto;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    public String memberForm(MemberFormDto memberFormDto){
//        model.addAttribute("memberFormDto",new MemberFormDto());
        log.info("Controller newGet");
        return "member/memberForm";
    }

    @PostMapping("/new")
    public String memberForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult,Model model){
        log.info("Controller newPost");
        if (bindingResult.hasErrors()){
            log.info("post요청시 넘어온 값에 Validation에 걸리는 경우.");
            model.addAttribute("memberFormDto",memberFormDto);


            return "member/memberForm";
        }

        try{
            log.info("아이디 저장전");
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            log.info("생성 후 저장전");
            memberService.saveMember(member);
            log.info("저장완료");
        }catch(IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            log.info("이미 있는 유저에러");
            return "member/memberForm";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginMember(){
        return "/member/memberLoginForm";
    }

    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요.");
        return "/member/memberLoginForm";
    }
}
