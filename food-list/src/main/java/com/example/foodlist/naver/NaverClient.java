package com.example.foodlist.naver;


import com.example.foodlist.naver.dto.SearchImageReq;
import com.example.foodlist.naver.dto.SearchImageRes;
import com.example.foodlist.naver.dto.SearchLocalReq;
import com.example.foodlist.naver.dto.SearchLocalRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;

@Component
public class NaverClient {

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverClientSecret;

    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    @Value("${naver.url.search.image}")
    private String naverImageSearchUrl;


    public SearchLocalRes searchLocal(SearchLocalReq searchLocalReq) {
        var uri = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)
                .queryParams(searchLocalReq.toMultiValueMap())
                .build()
                .encode(Charset.forName("UTF-8"))
                .toUri();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", naverClientId)
                .header("X-Naver-Client-Secret", naverClientSecret)
                .build();

        var responseEntity = new RestTemplate().exchange(req, SearchLocalRes.class);

        return responseEntity.getBody();
    }

    public SearchImageRes searchImage(SearchImageReq searchImageReq) {
        var uri = UriComponentsBuilder.fromUriString(naverImageSearchUrl)
                .queryParams(searchImageReq.toMultiValueMap())
                .build()
                .encode(Charset.forName("UTF-8"))
                .toUri();

        var header = new HttpHeaders();
        header.set("X-Naver-Client-Id", naverClientId);
        header.set("X-Naver-Client-Secret", naverClientSecret);
        header.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(header);
        var responseType = new ParameterizedTypeReference<SearchImageRes>() {};

        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return responseEntity.getBody();
    }
}
