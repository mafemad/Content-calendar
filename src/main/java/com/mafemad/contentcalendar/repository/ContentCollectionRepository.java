package com.mafemad.contentcalendar.repository;

import com.mafemad.contentcalendar.model.Content;
import com.mafemad.contentcalendar.model.Status;
import com.mafemad.contentcalendar.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {

    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository(){
    };

    public List<Content> findAll(){
        return contentList;
    }

    public Optional<Content> findById(int id){
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    public void save(Content content) {
        contentList.removeIf(c -> c.id().equals(content.id()));
        contentList.add(content);
    }

    public boolean existsByID(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).count() != 1;
    }


    public void delete(Integer id) {
        contentList.removeIf(c -> c.id().equals(id));
    }

    @PostConstruct
    private void init(){
        Content c = new Content(1,
                "first post",
                "first post",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                "");

        contentList.add(c);
    }
}
