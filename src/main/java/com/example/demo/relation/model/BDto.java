package com.example.demo.relation.model;

import lombok.Builder;
import lombok.Getter;

public class BDto {

    @Getter
    public static class BReq {
        private String b01;
        private String b02;

        public B toEntity(Long aIdx) {
            return B.builder()
                    .b01(this.b01)
                    .b02(this.b02)
                    .a(A.builder()
                            .idx(aIdx)
                            .build())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class BRes {
        private Long idx;
        private String b01;
        private String b02;

        public static BDto.BRes form(B entity) {
            return BDto.BRes.builder()
                    .idx(entity.getIdx())
                    .b01(entity.getB01())
                    .b02(entity.getB02())
                    .build();
        }
    }
}
