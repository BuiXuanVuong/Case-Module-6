package com.mvc.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "status_reply_likes")
public class StatusReplyLike {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "created_date", nullable = false)
    private Date createdDate;
    @Column(name = "modify_date", nullable = false)
    private Date modifyDate;

    @ManyToOne
    @JoinColumn(name = "status_reply_id", referencedColumnName = "id", nullable = false)
    private StatusReply statusReply;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public StatusReply getStatusReply() {
        return statusReply;
    }

    public void setStatusReply(StatusReply statusReply) {
        this.statusReply = statusReply;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
