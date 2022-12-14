package com.example.waybane.services;

import com.example.waybane.models.Link;
import com.example.waybane.models.User;
import com.example.waybane.repositories.LinkRepository;
import com.example.waybane.services.shortener.Shortener;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;
    private final Shortener shortener;

    @Override
    public boolean add(Link link) {

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (linkRepository.findByUserAndUrl(user, link.getUrl()).isPresent())
            return false;

        String token;
        Long tail = 0L;

        do {
            token = shortener.shorten(link.getUrl(), tail);
            tail++;
        } while (linkRepository.findByToken(token).isPresent());

        link.setToken(token);
        link.setUser(user);
        linkRepository.save(link);

        return true;
    }

    @Override
    public Page<Link> getLinks(Pageable pageable) {
        return linkRepository.findByUser(
                (User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal(), pageable);
    }

    @Override
    public Optional<Link> findByToken(String token) {
        return linkRepository.findByToken(token);
    }

    @Override
    public void deleteByToken(String token) {
        Optional<Link> link = linkRepository.findByToken(token);
        link.ifPresent(linkRepository::delete);
    }
}
