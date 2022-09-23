package com.simple.basic.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyDTO {
   private int reply_num; //pk
   private int c_num; //게시물 번호
   private String u_id; // 작성자
   private String reply_contents; //내용
   private LocalDateTime reply_date; //작성일자
}