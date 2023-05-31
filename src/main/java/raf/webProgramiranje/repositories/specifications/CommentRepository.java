package raf.webProgramiranje.repositories.specifications;

import raf.webProgramiranje.entities.Comment;
import raf.webProgramiranje.entities.News;

import java.util.List;

public interface CommentRepository {


    Comment addComment(Comment comment);

    Comment deleteComment(Comment comment);

    List<Comment> getAllCommentsForNews(Integer newsID);
}
