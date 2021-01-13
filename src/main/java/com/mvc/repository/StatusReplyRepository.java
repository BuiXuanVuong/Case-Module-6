//package com.mvc.repository;
//
//import com.mvc.model.StatusReply;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//public interface StatusReplyRepository extends JpaRepository<StatusReply, Long> {
//    @Query(value = "DELETE FROM statuse_replies WHERE status_id = ?1, nativeQuery=true")
//    void removeStatusReplies(Long status_id);
//}
