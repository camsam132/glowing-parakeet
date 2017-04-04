package com.elderstudios.service;

import com.elderstudios.domain.blogPost;
import com.elderstudios.domain.blogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class blogPostService {

    @Autowired
    protected blogPostRepository blogPostRepository;

    public List<blogPost> findAll() {
        return blogPostRepository.findAll();
    }

    public blogPost save(blogPost entry) {
        return blogPostRepository.save(entry);
    }

    public void delete(Long id) {
        blogPostRepository.delete(id);
    }

    public blogPost findOne(Long id) {
        return blogPostRepository.findOne(id);
    }
}
