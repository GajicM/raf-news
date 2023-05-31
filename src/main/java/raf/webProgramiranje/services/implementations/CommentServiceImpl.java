package raf.webProgramiranje.services.implementations;

import raf.webProgramiranje.entities.Comment;
import raf.webProgramiranje.entities.News;
import raf.webProgramiranje.repositories.specifications.CommentRepository;
import raf.webProgramiranje.services.CommentService;

import javax.inject.Inject;
import java.util.List;

public class CommentServiceImpl  implements CommentService {
    @Inject
    private CommentRepository commentRepository;
    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.addComment(comment);
    }

    @Override
    public Comment deleteComment(Comment comment) {
        return commentRepository.deleteComment(comment);
    }

    @Override
    public List<Comment> getAllCommentsForNews(Integer newsID) {
        return commentRepository.getAllCommentsForNews(newsID);
    }
}
