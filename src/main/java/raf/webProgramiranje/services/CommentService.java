package raf.webProgramiranje.services;

import raf.webProgramiranje.entities.Comment;
import raf.webProgramiranje.entities.News;

import java.util.List;

public interface CommentService {
    Comment addComment(Comment comment);

    Comment deleteComment(Comment comment);

    List<Comment> getAllCommentsForNews(News news);
}
