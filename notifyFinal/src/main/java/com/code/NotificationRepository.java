package com.code;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

public interface NotificationRepository extends CrudRepository<Notification, Integer>
{
	@Query(value = "SELECT notification_id FROM notifications WHERE username = :reqUser", nativeQuery=true)
	public @ResponseBody ArrayList<Integer> getAllIDs(@Param("reqUser") String reqUser);
	
	@Query(value = "SELECT body FROM templates WHERE template_id = :reqTemplate", nativeQuery=true)
	public @ResponseBody String getBody(@Param("reqTemplate") int reqTemplate);
	
	@Query(value = "SELECT template_id, timestamp, is_read FROM notifications WHERE notification_id = :reqNotif", nativeQuery=true)
	public @ResponseBody String lazyNotification(@Param("reqNotif") int reqNotif);
	
	@Query(value = "SELECT * FROM notifications WHERE notification_id = :reqNotif", nativeQuery=true)
	public @ResponseBody Notification getNotification(@Param("reqNotif") int reqNotif);
	
	@Query(value = "SELECT subj FROM templates WHERE template_id = :reqTemplate", nativeQuery=true)
	public @ResponseBody String getSubject(@Param("reqTemplate") int reqTemplate);
	
	@Query(value = "SELECT notification_id FROM notifications WHERE username = :reqUser AND is_read = 0", nativeQuery=true)
	public @ResponseBody ArrayList<Integer> getUnreadNotifs(@Param("reqUser") String reqUser);
	
	@Query(value = "SELECT * FROM notifications WHERE username = :reqUser ORDER BY timestamp DESC", nativeQuery=true)
	public @ResponseBody Collection<Notification> getNewestNotifs(@Param("reqUser") String reqUser);
	
	@Query(value = "SELECT * FROM notifications WHERE username = :reqUser AND is_email = true ORDER BY timestamp DESC", nativeQuery=true)
	public @ResponseBody Collection<Notification> getNewestEmails(@Param("reqUser") String reqUser);
	
	@Query(value = "SELECT * FROM notifications WHERE username = :reqUser AND is_email = true ORDER BY timestamp ASC", nativeQuery=true)
	public @ResponseBody Collection<Notification> getOldestEmails(@Param("reqUser") String reqUser);
	
	@Query(value = "SELECT * FROM notifications WHERE username = :reqUser AND is_email = false ORDER BY timestamp DESC", nativeQuery=true)
	public @ResponseBody Collection<Notification> getNewestTexts(@Param("reqUser") String reqUser);
	
	@Query(value = "SELECT * FROM notifications WHERE username = :reqUser AND is_email = false ORDER BY timestamp ASC", nativeQuery=true)
	public @ResponseBody Collection<Notification> getOldestTexts(@Param("reqUser") String reqUser);
	
	@Query(value = "SELECT notification_id FROM notifications WHERE username = :reqUser AND is_email = true ORDER BY timestamp DESC", nativeQuery=true)
	public @ResponseBody ArrayList<Integer> loadEmails(@Param("reqUser") String reqUser);
	
	@Query(value = "SELECT notification_id FROM notifications WHERE username = :reqUser AND is_email = false ORDER BY timestamp DESC", nativeQuery=true)
	public @ResponseBody ArrayList<Integer> loadTexts(@Param("reqUser") String reqUser);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE notifications SET is_visible =:vis_status WHERE notification_id = :reqNotif", nativeQuery=true)
	public @ResponseBody int setVisibility(@Param("reqNotif") int reqNotif, @Param("vis_status") int vis_status);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE notifications SET is_read =:read_status WHERE notification_id = :reqNotif", nativeQuery=true)
	public @ResponseBody int setRead(@Param("reqNotif") int reqNotif, @Param("read_status") int read_status);
}
