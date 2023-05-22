package raf.webProgramiranje.repositories.specifications;

import raf.webProgramiranje.entities.Tag;

import java.util.List;

public interface TagRepository {
    Tag addTag(Tag tag);

    Tag deleteTag(Tag tag);

    List<Tag> getAllTags();
}
