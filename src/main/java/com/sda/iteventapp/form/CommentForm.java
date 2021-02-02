package com.sda.iteventapp.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentForm {
    private Long userId;
    private Long eventId;
    private String message;
}
