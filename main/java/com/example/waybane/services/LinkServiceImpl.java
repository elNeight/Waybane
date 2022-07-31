package com.example.waybane.services;

import com.example.waybane.models.Link;
import com.example.waybane.repositories.LinkRepository;
import com.example.waybane.services.shortener.Shortener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService{

    private final LinkRepository linkRepository;
    private final Shortener shortener;

    @Override
    public Link add(Link link) {

        String token;
        Long tail = 0L;

        do{
            token = shortener.shorten(link.getToken(), tail);
            tail++;
        } while (linkRepository.findByToken(token).isPresent());

        link.setToken(token);
        linkRepository.save(link);

        return link;
    }

}
