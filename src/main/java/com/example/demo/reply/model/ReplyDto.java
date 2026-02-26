package com.example.demo.reply.model;

import com.example.demo.relation.model.A;
import com.example.demo.relation.model.ADto;
import com.example.demo.relation.model.BDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class ReplyDto {
    @Builder
    @Getter
    public static class ReplyRes {
        private Long idx;
        private String contents;
        private String writer;

        public static ReplyDto.ReplyRes from(Reply entity) {
            return ReplyRes.builder()
                    .idx(entity.getIdx())
                    .contents(entity.getContents())
                    .writer(entity.getUser().getName())
                    .build();
        }
    }
}
