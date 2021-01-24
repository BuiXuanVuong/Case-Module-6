package com.mvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    public User () {
//        this.isNonBanned= true;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "image")
    private String image;

//    @Column(name = "is_non_banned", columnDefinition="Boolean default 'true'")
//    private boolean isNonBanned;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "friendships",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<User> friends;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "friendships",
            joinColumns = @JoinColumn(name = "friend_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userFriends;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "invitations",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<User> invitedFriends;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "invitations",
            joinColumns = @JoinColumn(name = "friend_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> invitedUserFriends;


    @OneToMany(mappedBy = "userPost", fetch = FetchType.EAGER)
    private List<Status> statusList;

    @OneToMany(mappedBy = "userWhoRepliedToStatus")
    private List<StatusReply> statusReplyList;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<StatusLike> statusLikes;

    public Set<StatusReplyLike> getStatusReplyLikes() {
        return statusReplyLikes;
    }

    public void setStatusReplyLikes(Set<StatusReplyLike> statusReplyLikes) {
        this.statusReplyLikes = statusReplyLikes;
    }

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<StatusReplyLike> statusReplyLikes;

    public Set<StatusLike> getStatusLikes() {
        return statusLikes;
    }

    public void setStatusLikes(Set<StatusLike> statusLikes) {
        this.statusLikes = statusLikes;
    }
//    @OneToMany(mappedBy = "messagePoster", fetch = FetchType.LAZY)
//    private List<Message>

//    public boolean isNonBanned() {
//        return isNonBanned;
//    }
//
//    public void setNonBanned(boolean nonBanned) {
//        this.isNonBanned = nonBanned;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<User> getUserFriends() {
        return userFriends;
    }

    public void setUserFriends(List<User> userFriends) {
        this.userFriends = userFriends;
    }

    public List<Status> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Status> statusList) {
        this.statusList = statusList;
    }

    public List<User> getInvitedFriends() {
        return invitedFriends;
    }

    public void setInvitedFriends(List<User> invitedFriends) {
        this.invitedFriends = invitedFriends;
    }

    public List<User> getInvitedUserFriends() {
        return invitedUserFriends;
    }

    public void setInvitedUserFriends(List<User> invitedUserFriends) {
        this.invitedUserFriends = invitedUserFriends;
    }

    public List<StatusReply> getStatusReplyList() {
        return statusReplyList;
    }

    public void setStatusReplyList(List<StatusReply> statusReplyList) {
        this.statusReplyList = statusReplyList;
    }
}
