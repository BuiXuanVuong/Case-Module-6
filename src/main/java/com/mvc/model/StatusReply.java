package com.mvc.model;


import javax.persistence.*;

@Entity
@Table(name="statuses_replies")
public class StatusReply {

    public StatusReply() {

    }

    @Id
    @GeneratedValue
    public long id;

    private String statusReplyBody;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User userWhoRepliedToStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="status_id")
    private Status statusReplyingTo;


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
}
