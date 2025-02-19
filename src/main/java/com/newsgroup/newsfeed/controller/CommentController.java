package com.newsgroup.newsfeed.controller;

import com.newsgroup.newsfeed.config.GetLoginUser;
import com.newsgroup.newsfeed.dto.request.comment.CommentRequest;
import com.newsgroup.newsfeed.dto.request.comment.CommentSaveReqDto;
import com.newsgroup.newsfeed.dto.response.comment.CommentResponse;
import com.newsgroup.newsfeed.dto.response.comment.CommentSaveRespDto;
import com.newsgroup.newsfeed.entity.Comment;
import com.newsgroup.newsfeed.entity.Users;
import com.newsgroup.newsfeed.service.comment.CommentService;

import com.newsgroup.newsfeed.service.posts.PostService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.newsgroup.newsfeed.config.GetLoginUser.getLoginUser;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final PostService postService;//추가
    private final CommentService commentService;

    @PostMapping("/new/{postId}")
    public ResponseEntity<CommentSaveRespDto> newComment(@RequestBody String contents,
                                                         @PathVariable Long postId,
                                                         HttpSession session) {
        /* postById 로직 */

        Users user = getLoginUser(session);
        Comment comment = new Comment(user, contents);
        CommentSaveRespDto commentSaveRespDto = commentService.saveComment(comment);

        postService.addCommentAndUpdateCount(postId, comment);

        return ResponseEntity.ok().body(commentSaveRespDto);
    }

    /**
     * 특정 게시물의 댓글 목록 조회
     */
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentResponse>> getComments(
            HttpSession session,
            @PathVariable Long postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(defaultValue = "createdDate") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {

        Users user = getLoginUser(session); // 세션에서 로그인한 사용자 정보 가져오기
        List<CommentResponse> comments = commentService.getComments(postId, user, page, size, sortBy, direction);
        return ResponseEntity.ok(comments);
    }

    /**
     * 댓글 수정 (작성자만 가능)
     */
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            HttpSession session,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentRequest request
    ) {
        Users user = getLoginUser(session); // 세션에서 로그인한 사용자 정보 가져오기
        return ResponseEntity.ok(commentService.updateComment(user, commentId, request));
    }

    /**
     * 댓글 삭제 (작성자만 가능)
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            HttpSession session,
            @PathVariable Long commentId
    ) {
        Users user = getLoginUser(session); // 세션에서 로그인한 사용자 정보 가져오기
        commentService.deleteComment(user, commentId);
        return ResponseEntity.noContent().build();
    }
}