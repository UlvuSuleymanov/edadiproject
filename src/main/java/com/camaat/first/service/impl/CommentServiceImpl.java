package com.camaat.first.service.impl;

import com.camaat.first.entity.Comment;
import com.camaat.first.entity.CommentVote;
import com.camaat.first.entity.Post;
import com.camaat.first.entity.User;
import com.camaat.first.model.request.CommentRequestModel;
import com.camaat.first.model.response.CommentResponseModel;
import com.camaat.first.repository.CommentRepository;
import com.camaat.first.repository.PostRepository;
import com.camaat.first.repository.UserRepository;
import com.camaat.first.service.CommentService;
import com.camaat.first.service.ImageService;
import com.camaat.first.utility.DataParser;
 import com.camaat.first.repository.CommentVoteRepository;
import com.camaat.first.utility.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl  implements CommentService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final ImageService imageService;
    private final CommentVoteRepository commentVoteRepository;


    @Autowired
    public CommentServiceImpl(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository, ImageService imageService, CommentVoteRepository commentVoteRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.imageService = imageService;
        this.commentVoteRepository = commentVoteRepository;
    }

    @Override
    public Comment commentBuilder(CommentRequestModel commentRequestModel, Long postId) {

       Object idObj= SecurityContextHolder.getContext().getAuthentication().getCredentials();
       Long id = DataParser.objectToLong(idObj);

       Post post = postRepository.getOne(postId);
       User user = userRepository.getOne(id);


         Comment comment = new Comment();
         comment.setDate(new Date());
         comment.setUser(user);
         comment.setPost(post);
         comment.setCommentText(commentRequestModel.getCommentText());
         return comment;

    }

    @Override
    public CommentResponseModel commentResponseBuilder(Comment comment) {
        System.out.println(ImageUtil.generatePhotoUrl(comment.getUser().getPhotoUrl()));
        CommentResponseModel commentResponseModel =new CommentResponseModel();
        commentResponseModel.setAuthor(comment.getUser().getUsername())
                .setAuthorPhotoUrl(ImageUtil.generatePhotoUrl(comment.getUser().getPhotoUrl()))
                .setBirthDay(comment.getDate())
                .setCommentId(comment.getId())
                .setLikeCount(comment.getCommentVotes().size())
                .setCommentText(comment.getCommentText());
        return commentResponseModel;
    }

    @Override
    public List<CommentResponseModel> getComments(Long postId, int page, int size, String sort) {

         Pageable pageable= PageRequest.of(page,size);
         List<CommentResponseModel> commentResponseModelList =commentRepository.findByPost_id(postId,pageable)
                .stream().map(comment ->commentResponseBuilder(comment))
                .collect(Collectors.toList());
        return commentResponseModelList;
    }

    @Override
    public Long likeComment(Long commentId, Long userId) {
        CommentVote commentVote = new CommentVote();
        commentVote.setDate(new Date());
        commentVote.setUser(userRepository.getOne(userId));
        commentVote.setComment(commentRepository.getOne(commentId));
        return commentVoteRepository.save(commentVote).getId();
    }

    @Override
    public void disLikeComment(Long commentId, Long userId){
        CommentVote commentVote = commentVoteRepository.getCommentVoteByIds(userId,commentId);
        if(commentVote!=null){
            commentVoteRepository.delete(commentVote);
        }

     }
}
