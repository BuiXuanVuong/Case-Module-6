package com.mvc.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="statuses_replies")
public class StatusReply {

    public StatusReply() {

    }

    @Id
    @GeneratedValue
    public long id;

    private String statusReplyBody;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User userWhoRepliedToStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="status_id")
    private Status statusReplyingTo;

    private Date createdAt;
    private Date updatedAt;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatusReplyBody() {
        return statusReplyBody;
    }

    public void setStatusReplyBody(String statusReplyBody) {
        this.statusReplyBody = statusReplyBody;
    }

    public User getUserWhoRepliedToStatus() {
        return userWhoRepliedToStatus;
    }

    public void setUserWhoRepliedToStatus(User userWhoRepliedToStatus) {
        this.userWhoRepliedToStatus = userWhoRepliedToStatus;
    }

    public Status getStatusReplyingTo() {
        return statusReplyingTo;
    }

    public void setStatusReplyingTo(Status statusReplyingTo) {
        this.statusReplyingTo = statusReplyingTo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}
