package com.watcha.watchapedia.controller.page;

import com.watcha.watchapedia.dto.UserDto;
import com.watcha.watchapedia.dto.response.UserResponse;
import com.watcha.watchapedia.model.dto.CommentDto;
import com.watcha.watchapedia.model.dto.QnaDto;
import com.watcha.watchapedia.model.entity.Comment;
import com.watcha.watchapedia.model.entity.Qna;
import com.watcha.watchapedia.model.entity.User;
import com.watcha.watchapedia.model.entity.type.FormStatus;
import com.watcha.watchapedia.model.network.Header;
import com.watcha.watchapedia.model.network.request.QnaRequest;
import com.watcha.watchapedia.model.network.response.CommentResponse;
import com.watcha.watchapedia.model.network.response.MovieApiResponse;
import com.watcha.watchapedia.model.network.response.QnaResponse;
import com.watcha.watchapedia.model.repository.CommentRepository;
import com.watcha.watchapedia.model.repository.QnaRepository;
import com.watcha.watchapedia.model.repository.UserRepository;
import com.watcha.watchapedia.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class PageController {
    @Autowired
    public MovieApiLogicService movieApiLogicService;

    @Autowired
    public TvApiLogicService tvApiLogicService;

    @Autowired
    public BookApiLogicService bookApiLogicService;

    @Autowired
    public WebtoonApiLogicService webtoonApiLogicService;

    //로그인을 하지 않고 url로 관리페이지로 뚥고 들어오는 것을 방지 (로그인으로 돌려보냄)
    public ModelAndView loginCheck(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        String id = null;
        String name = null;

        if(session == null){
            System.out.println("세션이 없습니다");
            return new ModelAndView("/0_login/Login");
        }else{
            id = (String) session.getAttribute("id");
            name = (String) session.getAttribute("name");
        }
        return null;
    }

    @GetMapping(path="")
    public ModelAndView index(){
        return new ModelAndView("/index");
    }

    @GetMapping(path="/login")
    public ModelAndView login(){
        return new ModelAndView("/0_login/Login");
    }

    @GetMapping(path="/notice")
    public ModelAndView notice(){
        return new ModelAndView("/1_notice/Notice");
    }

    @GetMapping(path="/notice_edit")
    public ModelAndView notice_edit(HttpServletRequest request){
        // 로그인 Check 시작!
        ModelAndView loginCheck = loginCheck(request);
        if(loginCheck != null){
            return loginCheck;
        }
        // 로그인 Check 끝!

        return new ModelAndView("/1_notice/Notice_Edit");

    }

    @GetMapping(path="/notice_view")
    public ModelAndView notice_view(){
        return new ModelAndView("/1_notice/Notice_View");
    }

    @GetMapping(path="/notice_write")
    public ModelAndView notice_write(HttpServletRequest request){
        // 로그인 Check 시작!
        ModelAndView loginCheck = loginCheck(request);
        if(loginCheck != null){
            return loginCheck;
        }
        // 로그인 Check 끝!
        return new ModelAndView("/1_notice/Notice_Write");
    }

    // qna 리스트
    private final QnaService qnaService;
    @GetMapping(path="/qna")
    public String qna(ModelMap map){
        map.addAttribute("qnas", qnaService.searchQnas());
        return "/2_qna/QnA";
    }

    // qna 리스트 답글 작성 부분
    final QnaRepository qnaRepository;
    @GetMapping(path="/qna/{qnaIdx}")
    public String qnadetail(@PathVariable Long qnaIdx, ModelMap map){
        Optional<Qna> qna = qnaRepository.findById(qnaIdx);
        QnaResponse qnaResponse = QnaResponse.from(QnaDto.from(qna.get()));
        map.addAttribute("qna", qnaResponse);
        return "/2_qna/QnA_Reply";
    }

    // qna 답글 완료 부분
    @GetMapping("/qnaview")
    public String QnaView(ModelMap map){
        map.addAttribute("view" , FormStatus.CREATE);
        return "/2_qna/QnA_View";
    }

    @PostMapping ("/qnaview")
    public String postqnaview(QnaRequest qnaRequest) {
        qnaService.saveQna(qnaRequest.toDto());
        return "redirect:/qna";
    }

    // qna 답글 완료 데이터 보내기
    @GetMapping("/qna/{qnaIdx}/qnaview")
    public String updateQnaVieW(@PathVariable Long qnaIdx, ModelMap map){
        QnaResponse qna = QnaResponse.from(qnaService.getQna(qnaIdx));
        map.addAttribute("qna", qna);
        map.addAttribute("formStatus", FormStatus.UPDATE);
        return "/2_qna/QnA_View";
    }

    // qna 답글 완료 데이터 보내기
   @PostMapping("/qna/{qnaIdx}/qnaview")
   public String updateQna(@PathVariable Long qnaIdx, @RequestParam(required = false)String qnaText){
        qnaService.updateQna(qnaIdx, qnaText);
       return "redirect:/qna/{qnaIdx}/qnaview";
   }
    /*   @RequestParam Spring MVC에서 쿼리 스트링 정보를 쉽게 가져오는데 사용할 수 있다,
    적용된 필드가 없으면 에러가 발생할 수 있지만 @RequestParam(required = false)를 사용하여
    required 속성을 추가하면 해당 필드가 쿼리스트링에 존재하지 않아도 예외가 발생하지 않는다
    String qnaText를 html 부분 name = qnaText로 두고 해당 정보를 qnaview로 넘겨줄 수 있다
     */

    @GetMapping(path="/contents/book")
    public ModelAndView book(){
        ModelAndView view = new ModelAndView("/3_contents/book/book");
        view.addObject("books",bookApiLogicService.bookList());
        return view;
    }
    @GetMapping(path="/contents/book_edit")
    public ModelAndView bookEdit(HttpServletRequest request){
        // 로그인 Check 시작!
        ModelAndView loginCheck = loginCheck(request);
        if(loginCheck != null){
            return loginCheck;
        }
        // 로그인 Check 끝!
        return new ModelAndView("/3_contents/book/book_edit");
    }

    @GetMapping(path="/contents/book_write")
    public ModelAndView bookWrite(HttpServletRequest request){
        // 로그인 Check 시작!
        ModelAndView loginCheck = loginCheck(request);
        if(loginCheck != null){
            return loginCheck;
        }
        // 로그인 Check 끝!
        return new ModelAndView("/3_contents/book/book_write");
    }
    @GetMapping(path="/contents/book_detail")
    public ModelAndView bookDetail(){
        return new ModelAndView("/3_contents/book/book_detail");
    }



    @GetMapping(path="/contents/movie")
    public ModelAndView movie(){
        ModelAndView view = new ModelAndView("/3_contents/movie/movie");
        view.addObject("movies",movieApiLogicService.movieList());
        return view;
    }
    @GetMapping(path="/contents/movie_edit")
    public ModelAndView movieEdit(){
        return new ModelAndView("/3_contents/movie/movie_edit");
    }
    @GetMapping(path="/contents/movie_write")
    public ModelAndView movieWrite(){
        return new ModelAndView("/3_contents/movie/movie_write");
    }
    @GetMapping(path="/contents/movie_detail/{movIdx}")
    public ModelAndView movieDetail(@PathVariable Long movIdx){
        ModelAndView view = new ModelAndView("/3_contents/movie/movie_detail");
        Header<MovieApiResponse> api = movieApiLogicService.read(movIdx);
        view.addObject("movie",api.getData());
        return view;
    }


    @GetMapping(path="/contents/tv")
    public ModelAndView tv(){
        ModelAndView view = new ModelAndView("/3_contents/tv/tv");
        view.addObject("tvs",tvApiLogicService.tvList());
        return view;
    }
    @GetMapping(path="/contents/tv_edit")
    public ModelAndView tvEdit(){
        return new ModelAndView("/3_contents/tv/tv_edit");
    }
    @GetMapping(path="/contents/tv_write")
    public ModelAndView tvWrite(){
        return new ModelAndView("/3_contents/tv/tv_write");
    }
    @GetMapping(path="/contents/tv_detail")
    public ModelAndView tvDetail(){
        return new ModelAndView("/3_contents/tv/tv_detail");
    }



    @GetMapping(path="/contents/webtoon")
    public ModelAndView webtoon(){
        ModelAndView view = new ModelAndView("/3_contents/webtoon/webtoon");
        view.addObject("webtoons",webtoonApiLogicService.webtoonList());
        return view;

    }
    @GetMapping(path="/contents/webtoon_edit")
    public ModelAndView webtoonEdit(){
        return new ModelAndView("/3_contents/webtoon/webtoon_edit");
    }
    @GetMapping(path="/contents/webtoon_write")
    public ModelAndView webtoonWrite(){
        return new ModelAndView("/3_contents/webtoon/webtoon_write");
    }
    @GetMapping(path="/contents/webtoon_detail")
    public ModelAndView webtoonDetail(){
        return new ModelAndView("/3_contents/webtoon/webtoon_detail");
    }






    @GetMapping(path="/comment/report_page")
    public ModelAndView report(){
        return new ModelAndView("/4_comment/reported/report_page");
    }

    @GetMapping(path="/comment/reportdetail_comment")
    public ModelAndView reportdetailcomment(){
        return new ModelAndView("/4_comment/reported/reportdetail_comment");
    }

    @GetMapping(path="/comment/reportdetail_reply")
    public ModelAndView reportdetailreply(){
        return new ModelAndView("/4_comment/reported/reportdetail_reply");
    }

    // comment 리스트 출력
    private final CommentService commentService;
    @GetMapping(path="/comment/search_list")
    public String comment(ModelMap map){
        map.addAttribute("comments", commentService.searchComments());
        return "/4_comment/search/commentSearchList";
    }

    // commentDetail 출력 (내용, 이미지)
    final CommentRepository commentRepository;
    @GetMapping(path="/comment/{commentIdx}")
    public String commentdetail(@PathVariable Long commentIdx, ModelMap map){
        Optional<Comment> comment = commentRepository.findById(commentIdx);
        CommentResponse commentResponse = CommentResponse.from(CommentDto.from(comment.get()));
        map.addAttribute("comment", commentResponse);
        return "/4_comment/search/commentSearchDetail";
    }
    @GetMapping(path="/character_detail")
    public ModelAndView characterdetail(){
        return new ModelAndView("/5_character/characterdetail");
    }
    @GetMapping(path="/character_manage")
    public ModelAndView charactermanage(){
        return new ModelAndView("/5_character/charactermanage");
    }
    @GetMapping(path="/character_register")
    public ModelAndView characterregister(){
        return new ModelAndView("/5_character/characterregister");
    }

    // 멤버 디테일
    final UserRepository userRepository;
    @GetMapping(path="/member/{userIdx}")
    public String memberdetail(@PathVariable Long userIdx, ModelMap map){
        Optional<User> user = userRepository.findById(userIdx);
        UserResponse userResponse = UserResponse.from(UserDto.from(user.get()));
        map.addAttribute("user", userResponse);
        return "/6_member/memberdetail";
    }

    // 멤버 리스트
    final UserService userService;
    @GetMapping(path="/member")
    public String membermanage(ModelMap map){
        List<UserResponse> users = userService.searchUsers().stream().map(UserResponse::from).toList();
        map.addAttribute("users", users);
        return "/6_member/membermanage";
    }

    // 유저 인플루언서 수정
    @GetMapping("/member/{userIdx}/{userType}")
    public String updateUser(
             @PathVariable Long userIdx,
             @PathVariable String userType){
        Optional<User> user = userRepository.findById(userIdx);
        user.ifPresent(
                selectUser -> {
                    selectUser.setUserType(userType);
                    userRepository.save(selectUser);
                }
        );
        return "redirect:/member/"+userIdx;
    }

    @GetMapping(path="/advertisement_main")
    public ModelAndView admain(){
        return new ModelAndView("/7_advertisement/adMain");
    }
    @GetMapping(path="/advertisement_regist")
    public ModelAndView adregist(){
        return new ModelAndView("/7_advertisement/adRegist");
    }

    @GetMapping(path="/hradmin/accountdetail")
    public ModelAndView hradminaccountdetail(){
        return new ModelAndView("/8_admin/hradmin/accountdetail");
    }
    @GetMapping(path="/hradmin/createaccount")
    public ModelAndView hradmincreateaccount(){
        return new ModelAndView("/8_admin/hradmin/createaccount");
    }
    @GetMapping(path="/hradmin/modifyaccount")
    public ModelAndView hradminmodifyaccount(){
        return new ModelAndView("/8_admin/hradmin/modifyaccount");
    }
    @GetMapping(path="/hradmin/searchaccount")
    public ModelAndView hradminsearchaccount(){
        return new ModelAndView("/8_admin/hradmin/searchaccount");
    }

    @GetMapping(path="/admin_myinfo")
    public ModelAndView myinfo(){
        return new ModelAndView("/8_admin/admin/Myinfo");
    }

    @GetMapping(path="/admin_myinfomodify")
    public ModelAndView myinfomodify(){
        return new ModelAndView("/8_admin/admin/Myinfomodify");
    }




}
