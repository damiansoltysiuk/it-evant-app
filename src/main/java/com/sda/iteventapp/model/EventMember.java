package com.sda.iteventapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "event_members")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private RoleUserEvent roleUserEvent;

    public EventMember(Event event, User user, RoleUserEvent roleUserEvent) {
        this.event = event;
        this.user = user;
        this.roleUserEvent = roleUserEvent;
    }
}