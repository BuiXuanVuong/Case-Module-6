package com.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "status")
public class Status {
    public Status() {

    }

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String content;

    @Column
    private long wallId;

    private Date createdAt;

    private Date updatedAt;

    @Column
    private String imageURL;

    public Integer getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(Integer totalLikes) {
        this.totalLikes = totalLikes;
    }

    @Column
    private Integer totalLikes = 0;
    private Integer totalComments = 0;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userPost;


    @OneToMany(mappedBy = "statusReplyingTo", fetch = FetchType.EAGER)
    private List<StatusReply> repliedStatusMessages;
    @OneToMany(mappedBy = "status")
    @JsonIgnore
    private Set<StatusLike> likes;

    public Integer getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(Integer totalComments) {
        this.totalComments = totalComments;
    }

    public Set<StatusLike> getLikes() {
        return likes;
    }

    public void setLikes(Set<StatusLike> likes) {
        this.likes = likes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getWallId() {
        return wallId;
    }

    public void setWallId(long wallId) {
        this.wallId = wallId;
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

    public User getUserPost() {
        return userPost;
    }

//    public String getImages() {
//        return images;
//    }
//
//    public void setImages(String images) {
//        this.images = images;
//    }


    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setUserPost(User userPost) {
        this.userPost = userPost;
    }

    public List<StatusReply> getRepliedStatusMessages() {
        return repliedStatusMessages;
    }

    public void setRepliedStatusMessages(List<StatusReply> repliedStatusMessages) {
        this.repliedStatusMessages = repliedStatusMessages;
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
