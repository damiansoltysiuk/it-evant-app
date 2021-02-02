package com.sda.iteventapp.form;

import com.sda.iteventapp.model.RoleUserEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventMemberForm {
    private Long userId;
    private Long eventId;
    private RoleUserEvent roleUserEvent;
}
